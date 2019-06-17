package objects;

import io.qameta.allure.Step;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class Folders {

    private static final String url = "https://anotepad.com";
    private static final By manageFoldersBtn = By.cssSelector("#folderOption > li > a");
    private static final By folderName = By.id("newFolder");
    private static final By createNewBtn = By.cssSelector("#manageFolderContent > div > div.col-xs-4 > input[type=button]");
    private static final By closeBtn = By.cssSelector("#manageFolderModal > div > div > div.modal-footer > button");
    private static final By createdFolder = By.xpath("//li[contains(@id,'folder_')][3]//a");

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
        driver.findElement(folderName).sendKeys(name);
        return this;
    }

    @Step
    public Folders save() {
        driver.findElement(createNewBtn).click();
        return this;
    }

    @Step
    public Folders close() {
        driver.findElement(closeBtn).click();
        return this;
    }

    public String getCreatedFolderName() {
        return driver.findElement(createdFolder).getText();
    }



}
