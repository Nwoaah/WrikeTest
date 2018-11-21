package step.assertion;

import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import util.TestConstants;

public class WrikeUIAssertion {
    public static final By surveySuccess = By.className("survey-success");
    public static final String styleAttribute = "style";
    public static final By resendEmailButton = By.className("wg-btn-progress");

    private WebDriver webDriver;

    public WrikeUIAssertion(WebDriver webDriver){
        this.webDriver = webDriver;
    }

    public void checkIfCurrentPageIsWrikeResend(){
        Assert.assertEquals(webDriver.getCurrentUrl(), TestConstants.WRIKE_URL_RESEND);
    }

    public void checkIfResultsSubmitted() {
        Assert.assertEquals(webDriver.findElement(surveySuccess).getAttribute(styleAttribute), TestConstants.DISPLAY_BLOCK);
    }

    public void checkResendEmail() {
        Assert.assertEquals(webDriver.findElement(resendEmailButton).getAttribute(styleAttribute), TestConstants.DISPLAY_BLOCK_RESEND_EMAIL);
    }
}
