package pages;

import org.junit.Assert;
import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import javax.xml.xpath.XPath;
import java.time.Duration;

public class purchaseFlow {

    public WebDriver driver;
    public WebDriverWait wait;
    public Alert alert;

    @FindBy(xpath = "//a[.=\"Add to cart\"]")
    private WebElement addToCartButton;

    @FindBy(xpath = "//h2[.=\"Products\"]")
    private WebElement msgSpaCart;

    @FindBy(xpath = "//button[@class=\"btn btn-success\"]")
    private WebElement addtocartButton;

    @FindBy(id = "cartur")
    private WebElement showcartButton;

    @FindBy(id = "name")
    private WebElement nameField;

    @FindBy(id = "city")
    private WebElement cityField;

    @FindBy(id = "country")
    private WebElement countryField;

    @FindBy(id = "card")
    private WebElement cardField;

    @FindBy(id = "month")
    private WebElement monthField;

    @FindBy(id = "year")
    private WebElement yearField;

    @FindBy(xpath = "//button[@onclick=\"purchaseOrder()\"]")
    private WebElement purchaseOrderButton;

    public purchaseFlow(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(5));
    }

    // Metodo donde se puede valida la seleccion del producto
    public void selectProduct(String product) {
        boolean productFound = false;
        try {
            WebElement articulo = wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//a[.=\"" + product + "\"]")));

            if (articulo.isDisplayed()) {
                System.out.println("Se encontro el articulo");
                articulo.click();
                productFound = true;
            } else {
                System.out.println("no se encontro");
            }
        } catch (Exception e) {
            Assert.assertTrue("El producto no fue encontrado", productFound);

        }
    }

    // Metodo donde se selecciona y se agrega el producto al carrito
    public void addProductCard() {

        wait.until(ExpectedConditions.visibilityOf(addToCartButton));
        addToCartButton.click();
        alert = wait.until(ExpectedConditions.alertIsPresent());
        alert.accept();

    }
    // Metodo donde se selecciona la categoria
    public void selectCategory(String category) {
        WebElement categoryOptions = driver.findElement(By.xpath("//a[.=\"" + category + "\"]"));
        categoryOptions.click();
        driver.manage().timeouts().implicitlyWait(2, java.util.concurrent.TimeUnit.SECONDS);

    }

    // Metodo donde se selecciona la opcion de carrito
    public void selectCart() {

        showcartButton.click();
        wait.until(ExpectedConditions.visibilityOf(msgSpaCart));
        addtocartButton.click();
    }

    //Metodo donde se agrega la informacion de la compra
    public void FillForm(String name, String country, String city, String creditCard, String month, String year) {
        nameField.sendKeys(name);
        countryField.sendKeys(country);
        cityField.sendKeys(city);
        cardField.sendKeys(creditCard);
        monthField.sendKeys(month);
        yearField.sendKeys(year);
        purchaseOrderButton.click();

    }
}
