package pages;

import org.junit.Assert;
import org.openqa.selenium.Alert;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;


import java.time.Duration;

public class login {
    public static WebDriver driver;
    public WebDriverWait wait;
    Alert alert;

    @FindBy(id = "login2")
    private WebElement loginButton;

    @FindBy(id = "loginusername")
    private WebElement username;

    @FindBy(id = "loginpassword")
    private WebElement password;

    @FindBy(xpath = "//button[.=\"Log in\"]")
    private WebElement submitButton;


    public login(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(20));
    }

    //Llenado de informacion del usuario y contraseña y login
    public void fillingInformation(String user, String pass) {
        wait.until(ExpectedConditions.visibilityOf(username));
        username.sendKeys(user);
        password.sendKeys(pass);
        submitButton.click();


    }

    //Verifica si el usuario con el cual se esta iniciando sesion, es valido
    public void statusLogin() {
        boolean sesion=false;
        wait = new WebDriverWait(driver, Duration.ofSeconds(2));
        try {
            alert = wait.until(ExpectedConditions.alertIsPresent());
            alert.accept();
            System.out.println("Error al iniciar sesión");
            Assert.assertTrue("El producto no fue encontro", sesion);

        } catch (Exception e) {

            System.out.println("Sesión iniciada");

        }
    }

    //Accion de logueo
    public void logueo() {
        loginButton.click();
    }


}
