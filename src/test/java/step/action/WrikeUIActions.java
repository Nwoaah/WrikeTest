package step.action;

import io.qameta.allure.Step;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import page.wrike.WrikeMainPage;
import page.wrike.WrikeResendPage;
import util.TestConstants;
import util.generate.StringGeenerator;
import util.http.TwitterIconMatching;

import java.util.List;
import java.util.Random;

public class WrikeUIActions {
    public static final By answerVeryInterested = By.xpath("/html/body/div[1]/main/div/div/div[2]/div/div[2]/div/form/div[1]/label[1]/button");
    public static final By answerJustLooking = By.xpath("/html/body/div[1]/main/div/div/div[2]/div/div[2]/div/form/div[1]/label[2]/button");
    public static final By divTeamMembers = By.cssSelector("div[data-code='team_members']");
    public static final By ButtonsOnForm = By.cssSelector("button");
    public static final By divPrimaryBusiness = By.cssSelector("div[data-code='primary_business']");
    public static final By switchInput = By.className("switch__input");
    public static final By SubmitButton = By.cssSelector(".section-resend-main .submit");
    public static final By form = By.className("survey");
    public static final By ResendEmailButton = By.xpath("/html/body/div[1]/main/div/div/div[2]/div/div[1]/p[3]/button");
    public static final String STYLE_ATTRIBUTE = "style";
    public static final String FOOTER_SOCIAL_ITEM = "wg-footer__social-item";
    public static final String FOOTER_SOCIAL_LINK = "wg-footer__social-link";
    public static final String HREF = "href";
    public static final String XLINK_HREF = "xlink:href";
    public static final String USE = "use";
    public static final String TWITTER_COM_WRIKE = "https://twitter.com/wrike";
    public static final String PATH_TO_TWITTER_ICON = "/content/themes/wrike/dist/img/sprite/vector/footer-icons.symbol.svg?v1#twitter";


    private WebDriver webDriver;

    public WrikeUIActions(WebDriver webDriver) {
        this.webDriver = webDriver;
    }

    @Step
    public WrikeMainPage goToMainPage() {
        this.webDriver.get(TestConstants.WRIKE_URL_MAIN);
        this.webDriver.manage().window().maximize();
        return new WrikeMainPage(this.webDriver);
    }

    @Step
    public void clickGetStartedForFree(WrikeMainPage mainPage) {
        mainPage.click(WrikeMainPage.getStartedForFreeBtn);
    }

    @Step
    public WrikeResendPage startFreeForTodayWithEmail(WrikeMainPage mainPage, String email) {
        // Click Start for free
        //  mainPage.click(WrikeMainPage.getStartedForFreeBtn);
        // Fill email form
        mainPage.writeText(WrikeMainPage.newEmailModalText, email);
        // Click submit
        mainPage.click(WrikeMainPage.newEmailSubmitModalBtn);
        // Wait for resend page to load
        return new WrikeResendPage(webDriver);

    }

    @Step
    public void FillingTheQASection(WrikeResendPage resendPage) {
        Random random = new Random();

        if (random.nextInt(2) == 1) {
            resendPage.click(answerVeryInterested);
        } else {
            resendPage.click(answerJustLooking);
        }

        WebElement elements = resendPage.findElement(divTeamMembers);
        List<WebElement> elementList = elements.findElements(ButtonsOnForm);
        int n = random.nextInt(elementList.size());
        int counter = 0;
        for (WebElement element : elementList) {
            if (n == counter) {
                element.click();
            }
            counter++;
        }

        elements = resendPage.findElement(divPrimaryBusiness);
        elementList = elements.findElements(ButtonsOnForm);
        n = random.nextInt(elementList.size());
        counter = 0;

        for (WebElement element : elementList) {
            if (n == counter) {
                element.click();
                if (n == 2) {
                    element.findElement(switchInput).sendKeys(StringGeenerator.generateRandomString());
                }
            }
            counter++;
        }
    }

    @Step
    public void SubmitResults(WrikeResendPage resendPage) {
        resendPage.click(SubmitButton);
        resendPage.wait.until(ExpectedConditions.attributeToBeNotEmpty(resendPage.driver.findElement(form), STYLE_ATTRIBUTE));
    }

    @Step
    public void ResendEMail(WrikeResendPage resendPage) {
        resendPage.click(ResendEmailButton);
    }


    @Step
    public boolean chekSiteFooterForCorrectTwitterButton(WrikeResendPage resendPage) {
        boolean flag = false;

        List<WebElement> li_All = resendPage.driver.findElements(By.className(FOOTER_SOCIAL_ITEM));
//
        for (WebElement element : li_All) {
            String urlToSite = element.findElement(By.className(FOOTER_SOCIAL_LINK)).getAttribute(HREF);
            String imgLink = element.findElement(By.cssSelector(USE)).getAttribute(XLINK_HREF);
            if (urlToSite.equals(TWITTER_COM_WRIKE)) {
                if (imgLink.equals(PATH_TO_TWITTER_ICON)) {
                    flag = TwitterIconMatching.isItTwitterIcon(PATH_TO_TWITTER_ICON);
                }
            }
        }
        return flag;
    }

//      For the future
//    public WebElement expandRootElement(WebElement element, WrikeResendPage resendPage) {
//        WebElement ele = (WebElement) ((JavascriptExecutor) resendPage.driver)
//                .executeScript("return arguments[0].shadowRoot", element);
//        return ele;
//    }

}
