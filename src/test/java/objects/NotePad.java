package objects;

import io.qameta.allure.Step;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class NotePad {

    private static final By noteTitle = By.id("edit_title");
    private static final By noteContent = By.id("edit_textarea");
    private static final By saveTitleButton = By.xpath("//*[@id='btnSaveNote']");
    private static final By deleteNote = By.cssSelector(".delete");

    private WebDriver driver;
    private WebDriverWait wait;

    public NotePad(WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(this.driver, 5);
    }


    @Step
    public NotePad open() {
        driver.get("https://anotepad.com/");
        return this;
    }

    @Step
    public NotePad setTitle(String title) {
        driver.findElement(noteTitle).sendKeys(title);
        return this;
    }

    @Step
    public NotePad addContent(String content) {
        driver.findElement(noteContent).sendKeys(content);
        return this;
    }

    @Step
    public NotePad setContent(String content) {
        driver.findElement(noteContent).clear();
        driver.findElement(noteContent).sendKeys(content);
        return this;
    }

    @Step
    public NotePad save() {
        driver.findElement(saveTitleButton).click();
        wait.until(ExpectedConditions.textToBePresentInElementLocated(By.cssSelector(".alert.alert-warning"), "You have saved your note as a"));
        return this;
    }

    @Step
    public String getNoteContent() {
        return driver.findElement(noteContent).getAttribute("value");
    }

    @Step
    public String getTitleContent() {
        return driver.findElement(noteTitle).getAttribute("value");
    }


}
