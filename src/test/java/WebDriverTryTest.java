import io.github.bonigarcia.wdm.ChromeDriverManager;
import io.qameta.allure.Issue;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.chrome.ChromeDriver;

public class WebDriverTryTest {

    private WebDriverTry aTry;

    @Before
    public void setUp() throws Exception {
        ChromeDriverManager.getInstance().setup();
        aTry = new WebDriverTry(new ChromeDriver());
    }

    @Test
    @Issue("ISSUE-2")
    public void searchTest() throws Exception {
        aTry.openMainPage();
        aTry.makeScreenShot();
        aTry.GetStartedForFree();
        aTry.makeScreenShot();
        aTry.FillInEmail("abcdef+wpt@wriketask.qaa");
        aTry.CreateMyWrikeAcc();
        aTry.makeScreenShot();

        aTry.quit();
    }
}