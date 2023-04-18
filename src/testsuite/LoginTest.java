package testsuite;

import browserfactory.BaseTest;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

public class LoginTest extends BaseTest {

    final String baseUrl = "https://demo.nopcommerce.com/";

    @Before
    public void setUp() {openBrowser(baseUrl);
    }

    @Test
    public void userShouldNavigateToLoginPageSuccessfully() {
       //Find login link and click on login link
        WebElement loginLink = driver.findElement(By.linkText("Log in"));
        loginLink.click();
        String expectedMessage = "Welcome, Please Sign In!";
        WebElement actualTextElement = driver.findElement(By.xpath("//h1[contains(text(), 'Welcome, Please Sign In!')]"));
        String actualMessage = actualTextElement.getText();
        Assert.assertEquals("Not redirected to login page", expectedMessage, actualMessage);
    }

    @Test
    public void userShouldLoginSuccessfullyWithValidCredentials() {
        String expectedMessage = "Log out";
        WebElement loginLink = driver.findElement(By.linkText("Log in"));
        loginLink.click();

        WebElement userName = driver.findElement(By.id("Email"));
        userName.sendKeys("asd@gmail.com");

        WebElement password = driver.findElement(By.name("Password"));
        password.sendKeys("Asd@123");

        WebElement login = driver.findElement(By.xpath("//button[contains(text(),'Log in')]"));
        login.click();

        WebElement actualMessageelement = driver.findElement(By.xpath("//a[contains(text(),'Log out')]"));
        String actualMessage = actualMessageelement.getText();

        Assert.assertEquals("Logout text is not matching", actualMessage, expectedMessage);
    }


    public void verifyTheErrorMessage() {
        String expectedMessage = "Login was unsuccessful. please correct the error and try again.\n"
                + "No customer account found";
        WebElement loginLink = driver.findElement(By.linkText("Log in"));
        loginLink.click();


        WebElement password = driver.findElement(By.name("Password"));
        password.sendKeys("Asd@123");

        WebElement login = driver.findElement(By.xpath("//button[contains(text(),'Log in')]"));
        login.click();

        WebElement actualMessageElement = driver.findElement(By.xpath("/html/body/div[6]/div[3]/div/div/div/div[2]/div[1]/div[2]/form/div[3]/button"));
        String actualMessage = actualMessageElement.getText();

        Assert.assertEquals("Error Message not displayed",actualMessage,expectedMessage);

    }

    @After
    public void tearDown() {closeBrowser();
    }
}
