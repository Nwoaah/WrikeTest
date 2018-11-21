package scenario;

import io.github.bonigarcia.wdm.ChromeDriverManager;
import io.qameta.allure.Issue;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.chrome.ChromeDriver;
import page.wrike.WrikeMainPage;
import page.wrike.WrikeResendPage;
import step.action.WrikeUIActions;
import step.assertion.WrikeUIAssertion;
import util.generate.StringGeenerator;

public class WebDriverTryTest {

    private WrikeUIActions wAct;
    private WrikeUIAssertion wAsn;

    @Before
    public void setUp() throws Exception {
        ChromeDriverManager.getInstance().setup();
        ChromeDriver driver = new ChromeDriver();
        wAct = new WrikeUIActions(driver);
        wAsn = new WrikeUIAssertion(driver);
    }

    @Test
    @Issue("ISSUE-2")
    public void searchTest() throws Exception {
        // Move to main page
        WrikeMainPage pageWm = wAct.goToMainPage();
        // Register with new email
        String email = StringGeenerator.generateEmail();
        WrikeResendPage pageWr = wAct.startFreeForTodayWithEmail(pageWm, email);
        // Check successful registration
        pageWr.waitForPageToLoad();
        // Check current page
        wAsn.checkIfCurrentPageIsWrikeResend();



    }
}