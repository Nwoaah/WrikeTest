package page.wrike;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.Clock;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.SystemClock;
import page.base.BasePage;
import util.TestConstants;


public class WrikeResendPage extends BasePage {
    public static final By closeGoogleIframeOnResendPage = By.xpath("/html/body/c-wiz/div/div/div[2]/div[4]/span[1]/div");

    public WrikeResendPage(WebDriver driver) {
        super(driver);
    }

    @Override
    public void waitForPageToLoad() {
        //wait for page
        wait.until(ExpectedConditions.urlToBe(TestConstants.WRIKE_URL_RESEND));
        super.waitForPageToLoad();

        // wait for the iframe
        wait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(By.cssSelector("iframe[id^=I0_")));
        // check for the googleIframe
        if (driver.findElements(By.tagName("iframe")).size() > 1) {
            //if it is, switch to
            driver.switchTo().frame(1);

            //click NO
            driver.findElement(closeGoogleIframeOnResendPage).click();
        }

    }

}

