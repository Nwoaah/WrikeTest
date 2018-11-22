package page.wrike;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import page.base.BasePage;
import util.TestConstants;

public class WrikeMainPage extends BasePage {

    // Current page locator
    public By thisPageLocator = null;

    // UI element locators
    public static final By getStartedForFreeBtn = By.xpath("/html/body/div[1]/header/div[3]/div[2]/div/div/div[2]/div/form/button");
    public static final By newEmailModalText = By.cssSelector(".modal-form-trial__input");
    public static final By newEmailSubmitModalBtn = By.cssSelector(".modal-form-trial__submit");


    public WrikeMainPage(WebDriver driver) {
        super(driver);
    }

    @Override
    public void waitForPageToLoad() {
        wait.until(ExpectedConditions.urlToBe(TestConstants.WRIKE_URL_MAIN));
        super.waitForPageToLoad();
    }

}
