package page;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.List;

public class PastebinHomePage {

    private static final String HOMEPAGE_URL = "https://pastebin.com/";
    private final WebDriver driver;
    private final int WAIT_TIMEOUT_SECONDS = 10;

    @FindBy(id = "postform-text")
    private WebElement codeForm;

    @FindBy(id = "postform-name")
    private WebElement nameForm;

    @FindBy(id = "select2-postform-expiration-container")
    private WebElement expirationContainer;

    @FindBy(xpath = "//div[5]//button")
    private WebElement createNewPasteButton;

    public PastebinHomePage(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    public PastebinHomePage openPage() {
        driver.get(HOMEPAGE_URL);
        new WebDriverWait(driver, Duration.ofSeconds(WAIT_TIMEOUT_SECONDS))
                .until(ExpectedConditions
                        .presenceOfElementLocated(By.id("postform-text")));
        return this;
    }

    public PastebinHomePage fillForms(String code, String name) {
        codeForm.sendKeys(code);
        nameForm.sendKeys(name);
        expirationContainer.click();
        driver.findElement(By.xpath("//ul[@class = 'select2-results__options']/li[text()='10 Minutes']")).click();
        createNewPasteButton.click();
        return this;
    }

    public boolean wasPasteSuccessful() {
        List<WebElement> successPageNote = driver.findElements(By.xpath("//div[@class = 'notice -success -post-view']"));
        return successPageNote.size() > 0;
    }
}
