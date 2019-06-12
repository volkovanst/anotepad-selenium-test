import org.junit.*;
import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class NotePadTestsApp {
    WebDriver driver;
    WebDriverWait wait;

    @BeforeClass
    public static void init() {
        System.setProperty("webdriver.chrome.driver", "/Users/nastia/Documents/chromedriver");
    }

    @Before
    public void openBrowser() {
        driver = new ChromeDriver();
        Dimension size = new Dimension(1600, 900);
        driver.manage().window().setSize(size);
        wait = new WebDriverWait(driver, 5);
    }

    @Test
    public void NoteTest()  {
        driver.get("https://anotepad.com/");
        driver.findElement(By.id("edit_title")).sendKeys("My New Note");
        driver.findElement(By.id("edit_textarea")).sendKeys("My First text");
        driver.findElement(By.id("btnSaveNote")).click();
        wait.until(ExpectedConditions.textToBePresentInElementLocated(
                By.cssSelector("body > div.containerMain > div > p:nth-child(1)"), "You have saved your note as a Guest User.")
        );
        driver.findElement(By.cssSelector("#note_form > div:nth-child(4) > div > span:nth-child(4) > strong > a")).click();

        Alert alert = wait.until(ExpectedConditions.alertIsPresent());
        driver.switchTo().alert();
        alert.accept();

        wait.until(ExpectedConditions.textToBePresentInElementLocated(By.cssSelector("#savedNotes > div"), "No note here."));
        Assert.assertEquals("No note here.", driver.findElement(By.cssSelector("#savedNotes > div")).getText());
    }



    @After
    public void closeBrowser() {
        driver.quit();
    }

}
