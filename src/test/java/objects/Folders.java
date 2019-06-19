package objects;

import io.qameta.allure.Step;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class Folders {

    private static final String url = "https://anotepad.com";
    private static final By manageFoldersBtn = By.cssSelector("#folderOption > li > a");
    private static final By folderName = By.id("newFolder");
    private static final By createNewBtn = By.cssSelector("#manageFolderContent input[type=button]");
    private static final By closeBtn = By.cssSelector("#manageFolderModal .modal-footer button");
    private static final By createdFolder = By.xpath("//li[contains(@id,'folder_')][3]//a");
    private static final By modalRow = By.xpath("//div[contains(@id,'row_')]");

    private WebDriver driver;
    private WebDriverWait wait;

    public Folders(WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(this.driver, 5);
    }

    @Step
    public Folders open() {
        driver.get(url);
        return this;
    }

    @Step
    public Folders manageFolders() {
        driver.findElement(manageFoldersBtn).click();
        wait.until(ExpectedConditions.visibilityOfElementLocated(folderName));
        return this;
    }

    @Step
    public Folders enterFolderName(String name) {
        WebElement element = wait.until((WebDriver d ) -> d.findElement(folderName));
        element.sendKeys(name);
        return this;
    }

    @Step
    public Folders save() {
        WebElement element = wait.until((WebDriver d ) -> d.findElement(createNewBtn));
        element.click();
        return this;
    }

    @Step
    public Folders close() {
        WebElement rowDiv =  wait.until((WebDriver d ) -> d.findElement(modalRow));
        WebElement folderName = rowDiv.findElement(By.className("col-xs-8"));
        wait.until(ExpectedConditions.visibilityOf(folderName));
        driver.findElement(closeBtn).click();
        return this;
    }

    public String getCreatedFolderName() {
        WebElement element = wait.until((WebDriver d ) -> d.findElement(createdFolder));
        return element.getText();
    }



}
