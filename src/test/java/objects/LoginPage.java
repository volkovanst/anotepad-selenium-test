package objects;

import io.qameta.allure.Step;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class LoginPage {

    private static final String url = "https://anotepad.com/create_account";
    private static final By registerEmail = By.id("registerEmail");
    private static final By loginEmail = By.id("loginEmail");
    private static final By password = By.id("password");
    private static final By submitBtn = By.id("submit");

    private WebDriver driver;
    private WebDriverWait wait;

    public LoginPage(WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(this.driver, 5);
    }

    @Step
    public LoginPage open() {
        driver.get(url);
        return this;
    }

    @Step
    public LoginPage enterCreateEmail (String email) {
        driver.findElement(registerEmail).sendKeys(email);
        return this;
    }

    @Step
    public LoginPage enterCreatePassword(String pwd) {
        driver.findElements(password).get(0).sendKeys(pwd);
        return this;
    }

    @Step
    public LoginPage createAccount() {
        driver.findElements(submitBtn).get(0).click();
        wait.until(ExpectedConditions.elementToBeClickable(NavBar.logoutBtn));
        return this;
    }

    @Step
    public LoginPage enterLoginEmail (String email) {
        driver.findElement(loginEmail).sendKeys(email);
        return this;
    }

    @Step
    public LoginPage enterLoginPassword(String pwd) {
        driver.findElements(password).get(1).sendKeys(pwd);
        return this;
    }

    @Step
    public LoginPage login() {
        driver.findElements(submitBtn).get(1).click();
        wait.until(ExpectedConditions.elementToBeClickable(NavBar.logoutBtn));
        return this;
    }

}
