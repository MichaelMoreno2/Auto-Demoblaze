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
import java.io.IOException;
import java.util.Properties;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;

public class steps {

    public WebDriver driver;
    public login login;
    public purchaseFlow flujoCompra;

    //Variables reporte
    public static ExtentReports extent;
    public static ExtentTest test;
    public static ExtentSparkReporter spark;

    Properties prop = new Properties();

    @Before(order = 0)
    public void initExtentReports() {
        if (extent == null) {
            spark = new ExtentSparkReporter("report.html");
            extent = new ExtentReports();
            extent.attachReporter(spark);
        }
    }
    @Before(order = 1)
    public void setUp() {
        try {

            test = extent.createTest("Flujo de Compra Test", "Automatizacion compra");

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
        extent.flush();
    }

    @Given("The user logs into the page")
    public void the_user_logs_into_the_page() {

        login.logueo();

    }


    @When("the user enter the user {string} and password {string}")
    public void the_user_enter_the_user_and_password(String user, String password) {
        try {
            login.fillingInformation(user, password);
            test.pass("Se ingreso informacion correcta al login");
        } catch (Exception e) {
            test.fail("Error en el logueo: " + e.getMessage());
            e.printStackTrace();
        }

    }

    @When("the user enter correctly and select the category {string}")
    public void theUserEnterCorrectlyAndSelectTheCategory(String category) {
        try {
            login.statusLogin();
            flujoCompra.selectCategory(category);
            test.pass("Se ingreso correctamente y se agrego la categoria");
        } catch (Exception e) {
            test.fail("Error al seleccionar la categoria: " + e.getMessage());
            e.printStackTrace();
        }
    }

    @When("the user add the {string} products to the cart")
    public void theUserAddTheProductsToTheCart(String product) {

        try {
            flujoCompra.selectProduct(product);
            test.pass("Se selecciono correctamente los productos");
        } catch (Exception e) {
            test.fail("Error al seleccionar los productos: " + e.getMessage());
            e.printStackTrace();
        }

    }

    @When("the user proceed to checkout")
    public void the_user_proceed_to_checkout() {

        try {
            flujoCompra.addProductCard();
            test.pass("Se realizo correctamente el checkout");
        } catch (Exception e) {
            test.fail("Error al realizar el proceso de checkout: " + e.getMessage());
            e.printStackTrace();
        }

    }

    @And("the user complete the purchase with name {string}, country {string}, city {string}, creditcard{string}, month {string}, year {string}")
    public void theUserCompleteThePurchaseWithNameCountryCityCreditcardMonthYear(String name, String country, String city, String creditcard, String month, String year) {

        try {
            flujoCompra.selectCart();
            flujoCompra.FillForm(name, country, city, creditcard, month, year);
            test.pass("Se realizo la compra");
        } catch (Exception e) {
            test.fail("Error al realizar el proceso de compra: " + e.getMessage());
            e.printStackTrace();
        }
    }

    @Then("the user should see a confirmation message")
    public void the_user_should_see_a_confirmation_message() {
        test.pass("Se realizo la confirmacion");

    }


}
