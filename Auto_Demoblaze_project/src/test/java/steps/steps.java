package steps;

import io.cucumber.java.After;
import io.cucumber.java.Before;
import io.cucumber.java.en.*;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import pages.*;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.Locale;
import java.util.Properties;

public class steps {

    public WebDriver driver;
    public login login;
    public purchaseFlow flujoCompra;
    Properties prop = new Properties();

    @Before
    public void setUp() {
        try {
            FileInputStream configFile = new FileInputStream("src/test/resources/config.properties");
            prop.load(configFile);

            String Browser = prop.getProperty("browser", "chrome").trim();
            String URL = prop.getProperty("URL");


            switch (Browser.toLowerCase()) {
                case "chrome":
                    System.out.println("Entro Chrome");
                    WebDriverManager.chromedriver().setup();
                    driver = new ChromeDriver();

                    break;

                case "firefox":
                    System.out.println("entro firefox ");
                    WebDriverManager.firefoxdriver().setup();
                    driver = new FirefoxDriver();

                    break;

                default:

                    System.out.println("Navegador Invalido");
                    break;
            }

            driver.manage().window().maximize();
            driver.get(URL);
            login = new login(driver);
            flujoCompra = new purchaseFlow(driver);

        } catch (IOException e) {
            throw new RuntimeException(e);
        }


    }

    @After
    public void after() {
        driver.quit();
    }

    @Given("The user logs into the page")
    public void the_user_logs_into_the_page() {
        login.logueo();
    }


    @When("the user enter the user {string} and password {string}")
    public void the_user_enter_the_user_and_password(String user, String password) {

        login.fillingInformation(user, password);
    }

    @When("the user enter correctly and select the category {string}")
    public void theUserEnterCorrectlyAndSelectTheCategory(String category) {

        login.statusLogin();
        flujoCompra.selectCategory(category);
    }

    @When("the user add the {string} products to the cart")
    public void theUserAddTheProductsToTheCart(String product) {
        flujoCompra.selectProduct(product);
    }

    @When("the user proceed to checkout")
    public void the_user_proceed_to_checkout() {
        flujoCompra.addProductCard();
    }

    @And("the user complete the purchase with name {string}, country {string}, city {string}, creditcard{string}, month {string}, year {string}")
    public void theUserCompleteThePurchaseWithNameCountryCityCreditcardMonthYear(String name, String country, String city, String creditcard, String month, String year) {
        flujoCompra.selectCart();
        flujoCompra.FillForm(name, country, city, creditcard, month, year);
    }

    @Then("the user should see a confirmation message")
    public void the_user_should_see_a_confirmation_message() {

    }


}
