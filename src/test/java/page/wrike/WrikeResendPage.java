package page.wrike;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import page.base.BasePage;
import util.TestConstants;

public class WrikeResendPage extends BasePage {
    public static final By closeGoogleIframeOnResendPage = By.xpath("/html/body/c-wiz/div/div/div[2]/div[4]/span[1]/div");

    public WrikeResendPage(WebDriver driver) {
        super(driver);
    }

    @Override
    public void waitForPageToLoad() {

        wait.until(ExpectedConditions.urlToBe(TestConstants.WRIKE_URL_RESEND));
        super.waitForPageToLoad();
        // check for the googleIframe
        if (driver.findElements(By.tagName("iframe")).size() > 1) {
            //if it is, switch to
            driver.switchTo().frame(1);
            //wait until it is visible
            wait.until(ExpectedConditions.visibilityOfElementLocated(By.className("nzQUNc")));
            wait.until(ExpectedConditions.visibilityOfElementLocated(By.className("Pv7Rnc")));
            wait.until(ExpectedConditions.visibilityOfElementLocated(By.className("SxbKmc")));
            wait.until(ExpectedConditions.visibilityOfElementLocated(By.className("CwaK9")));
            wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("gcr-logo")));
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            //click NO
            driver.findElement(closeGoogleIframeOnResendPage).click();
        }

    }


}

