package objects;

import org.openqa.selenium.By;

public class NavBar {
    public static final By settingsBtn = By.cssSelector("#bs-example-navbar-collapse-1 > div > ul > li:nth-child(4) > a");
    public static final By logoutBtn = By.cssSelector("#bs-example-navbar-collapse-1 > div > ul > li:nth-child(5) > a");
}
