import io.github.bonigarcia.wdm.WebDriverManager;
import io.qameta.allure.junit4.DisplayName;
import objects.Folders;
import objects.LoginPage;
import objects.NavBar;
import objects.NotePad;
import org.junit.*;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

public class NotePadTestsApp {
    WebDriver driver;
    NotePad notePad;
    LoginPage loginPage;
    Folders folders;

    String title = "My New Super Title";
    String note = "Note content\nLine2\nLine3";

    String registerEmail = "test1@gmail.com";
    String registerPwd = "123456";

    String loginEmail = "test1@gmail.com";
    String loginPwd = "123456";

    String folderName = "My Folder";

    @Before
    public void openBrowser() {
        WebDriverManager.chromedriver().setup();
        driver = new ChromeDriver();
        notePad = new NotePad(driver);
        loginPage = new LoginPage(driver);
        folders = new Folders(driver);
    }

    @Test
    @DisplayName("Test notepad content is correct")
    public void notepadTest() {
        notePad
                .open()
                .setTitle(title)
                .setContent(note)
                .save();
        Assert.assertEquals(note, notePad.getNoteContent());
    }

    @Test
    @DisplayName("Test title content is okay")
    public void notepadTest2() {
        notePad
                .open()
                .setTitle(title)
                .setContent(note)
                .save();
        Assert.assertEquals(title, notePad.getTitleContent());
    }

    @Test
    @DisplayName("GL-502: F-40 Sign Up for free account, F-170 Creating an account")
    public void createAccountTest() {

        loginPage
                .open()
                .enterCreateEmail(registerEmail)
                .enterCreatePassword(registerPwd)
                .createAccount();

        Assert.assertEquals(driver.findElement(NavBar.settingsBtn).isDisplayed(), true);

    }

    @Test
    @DisplayName("GL-568: F-180 Logging with correct Email and Password")
    public void loginTest() {

        loginPage
                .open()
                .enterLoginEmail(loginEmail)
                .enterLoginPassword(loginPwd)
                .login();

        Assert.assertEquals(driver.findElement(NavBar.settingsBtn).isDisplayed(), true);

    }


    @Test
    @DisplayName("Create folder")
    public void createFolder() {

        loginPage
                .open()
                .enterLoginEmail(loginEmail)
                .enterLoginPassword(loginPwd)
                .login();


        folders
                .manageFolders()
                .enterFolderName(folderName)
                .save()
                .close();

        Assert.assertEquals(folders.getCreatedFolderName(), folderName);

    }

    @After
    public void closeBrowser() {
        driver.quit();
    }
}
