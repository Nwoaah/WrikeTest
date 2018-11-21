package step.action;

import io.qameta.allure.Step;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import page.wrike.WrikeMainPage;
import page.wrike.WrikeResendPage;
import util.TestConstants;
import util.generate.StringGeenerator;

import java.util.List;
import java.util.Random;

public class WrikeUIActions {
    public static final By footerFollowUs = By.cssSelector(".wg-footer__group--social");
    public static final By answerVeryInterested = By.xpath("/html/body/div[1]/main/div/div/div[2]/div/div[2]/div/form/div[1]/label[1]/button");
    public static final By answerJustLooking = By.xpath("/html/body/div[1]/main/div/div/div[2]/div/div[2]/div/form/div[1]/label[2]/button");
    public static final By DIV_TEAM_MEMBERS = By.cssSelector("div[data-code='team_members']");
    public static final By BUTTON = By.cssSelector("button");
    public static final By DIV_PRIMARY_BUSINESS = By.cssSelector("div[data-code='primary_business']");
    public static final By SWITCH__INPUT = By.className("switch__input");
    public static final By SUBMIT_BUTTON = By.cssSelector(".section-resend-main .submit");

    private WebDriver webDriver;

    public WrikeUIActions(WebDriver webDriver){
        this.webDriver = webDriver;
    }

    @Step
    public WrikeMainPage goToMainPage(){
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
            resendPage.driver.findElement(answerVeryInterested).click();
        } else {
            resendPage.driver.findElement(answerJustLooking).click();
        }

        WebElement elements = resendPage.driver.findElement(DIV_TEAM_MEMBERS);
        List<WebElement> elementList = elements.findElements(BUTTON);
        int n = random.nextInt(elementList.size());
        int counter = 0;
        for (WebElement element : elementList) {
            if (n == counter) {
                element.click();
            }
            counter++;
        }

        elements = resendPage.driver.findElement(DIV_PRIMARY_BUSINESS);
        elementList = elements.findElements(BUTTON);
        System.out.println(elementList.size());
        n = random.nextInt(elementList.size());
        counter = 0;

        for (WebElement element : elementList) {
            if (n == counter) {
                element.click();
                if (n == 2) {
                    element.findElement(SWITCH__INPUT).sendKeys(StringGeenerator.generateRandomString());
                }
            }
            counter++;
        }
    }

    @Step
    public void SubmitResults(WrikeResendPage resendPage) {
        resendPage.driver.findElement(SUBMIT_BUTTON).click();

        resendPage.wait.until(ExpectedConditions.attributeToBeNotEmpty(resendPage.driver.findElement(By.className("survey")),"style"));
        System.out.println(resendPage.driver.findElement(By.className("survey-success")).getAttribute("style"));
        System.out.println(resendPage.driver.findElement(By.className("survey")).getAttribute("style"));

    }
    //    @Step
    //    public void chekSiteFooterForCorrectTwitterButton() {
//        if (driver.findElement(footerFollowUs).isEnabled()) {
//            System.out.println(driver.findElements(footerFollowUs));
//            WebElement elem3 = driver.findElement(By.cssSelector("/html/body/div[1]/div/div[3]/div/div[1]/div/ul/li[1]/a/svg/use"));
//            System.out.println(elem3.getText());
//            ShadowRoot shadowRoot =  driver.findElement(By.cssSelector("/html/body/div[1]/div/div[3]/div/div[1]/div/ul/li[1]/a/svg/use"));
//            System.out.println(driver.findElement(By.className("wg-footer__social-item")).getText());
//            List<WebElement> li_All = driver.findElements(By.className("wg-footer__social-item"));
//            List<WebElement> li_All2 = driver.findElements(By.className("wg-footer__social-icon"));
//            System.out.println(li_All.size());
//
//            for(WebElement element : li_All){
//                System.out.println(element.findElement(By.className("wg-footer__social-link")).getAttribute("href"));
//                System.out.println(element.findElement(By.xpath("//*[name()='use']")).getAttribute("xlink:href"));
//                WebElement elem2 = element.findElement(By.xpath("//*[name()='use']"));
//                elem2.findElement(By.cssSelector("use"));
//                WebElement elem = expandRootElement(element.findElement(By.cssSelector("use")));
//                System.out.println(elem.getText());
//
////                System.out.println(element.findElement(By.xpath("//*[name()='path']")).getAttribute("d"));
////                System.out.println(element.findElement(By.cssSelector(".wg-footer__social-icon")).getText());
////                System.out.println(element.findElement(By.cssSelector(".wg-footer__social-icon")).getAttribute("xlink:href"));
//              //  System.out.println(element.findElement(By.xpath("//div/a")).getAttribute("xlink:href"));
//            }
//
////            /html/body/div[1]/div/div[3]/div/div[1]/div/ul/li[3]/a/svg/use
////            /html/body/div[1]/div/div[3]/div/div[1]/div/ul/li[1]
//            for(WebElement element : li_All2){
//                System.out.println(element.getText());
//            }
//        }
//
//    }

}
