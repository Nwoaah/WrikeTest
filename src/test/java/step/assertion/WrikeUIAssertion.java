package step.assertion;

import net.bytebuddy.implementation.bytecode.constant.TextConstant;
import org.junit.Assert;
import org.openqa.selenium.WebDriver;
import util.TestConstants;

public class WrikeUIAssertion {
    private WebDriver webDriver;

    public WrikeUIAssertion(WebDriver webDriver){
        this.webDriver = webDriver;
    }

    public void checkIfCurrentPageIsWrikeResend(){
        Assert.assertEquals(webDriver.getCurrentUrl(), TestConstants.WRIKE_URL_RESEND);
    }
}
