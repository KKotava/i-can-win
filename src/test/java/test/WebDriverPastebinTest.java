package test;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import page.PastebinHomePage;

public class WebDriverPastebinTest {

    private WebDriver driver;

    @BeforeMethod(alwaysRun = true)
    public void browserSetUp() {
        ChromeOptions options = new ChromeOptions();
        options.addArguments("--remote-allow-origins=*");
        driver = new ChromeDriver(options);
        driver.manage().window().maximize();
    }

    @Test(description = "I can win!")
    public void newPasteIsCreated() {
        Assert.assertTrue(new PastebinHomePage(driver)
                .openPage()
                .fillForms("Hello from WebDriver", "helloweb")
                .wasPasteSuccessful()
        , "Creating new paste failed");
    }

    @AfterMethod(alwaysRun = true)
    public void browserTearDown() {
        driver.quit();
        driver = null;
    }
}
