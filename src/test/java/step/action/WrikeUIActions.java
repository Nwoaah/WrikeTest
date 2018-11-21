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

import java.util.List;
import java.util.Random;

public class WrikeUIActions {
    public static final By footerFollowUs = By.cssSelector(".wg-footer__group--social");
    public static final By answerVeryInterested = By.xpath("/html/body/div[1]/main/div/div/div[2]/div/div[2]/div/form/div[1]/label[1]/button");
    public static final By answerJustLooking = By.xpath("/html/body/div[1]/main/div/div/div[2]/div/div[2]/div/form/div[1]/label[2]/button");
    public static final By divTeamMembers = By.cssSelector("div[data-code='team_members']");
    public static final By ButtonsOnForm = By.cssSelector("button");
    public static final By divPrimaryBusiness = By.cssSelector("div[data-code='primary_business']");
    public static final By switchInput = By.className("switch__input");
    public static final By SubmitButton = By.cssSelector(".section-resend-main .submit");
    public static final By form = By.className("survey");
    public static final String STYLE_ATTRIBUTE = "style";
    public static final By ResendEmailButton = By.xpath("/html/body/div[1]/main/div/div/div[2]/div/div[1]/p[3]/button");

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
        public void chekSiteFooterForCorrectTwitterButton(WrikeResendPage resendPage) {

        if (resendPage.findElement(footerFollowUs).isEnabled()) {
            System.out.println(resendPage.driver.findElements(footerFollowUs).size());

            System.out.println(resendPage.driver.findElement(By.className("wg-footer__social-item")).getText());
            List<WebElement> li_All = resendPage.driver.findElements(By.className("wg-footer__social-item"));
            List<WebElement> li_All2 = resendPage.driver.findElements(By.className("wg-footer__social-icon"));
            System.out.println(li_All.size());

            for(WebElement element : li_All){
                System.out.println(element.findElement(By.className("wg-footer__social-link")).getAttribute("href"));
                System.out.println(element.findElement(By.xpath("//*[name()='use']")).getAttribute("xlink:href"));
                WebElement elem2 = element.findElement(By.xpath("//*[name()='use']"));
                System.out.println(resendPage.findElement(By.cssSelector(":host(use[xlink:href^=/content])")));
                //  WebElement elem = expandRootElement(element.findElement(By.cssSelector("use")),resendPage);
             //   System.out.println(elem.getText());

//                System.out.println(element.findElement(By.xpath("//*[name()='path']")).getAttribute("d"));
//                System.out.println(element.findElement(By.cssSelector(".wg-footer__social-icon")).getText());
//                System.out.println(element.findElement(By.cssSelector(".wg-footer__social-icon")).getAttribute("xlink:href"));
              //  System.out.println(element.findElement(By.xpath("//div/a")).getAttribute("xlink:href"));
            }

//            /html/body/div[1]/div/div[3]/div/div[1]/div/ul/li[3]/a/svg/use
//            /html/body/div[1]/div/div[3]/div/div[1]/div/ul/li[1]
            for(WebElement element : li_All2){
                System.out.println(element.getText());
            }
        }

    }

    public WebElement expandRootElement(WebElement element,WrikeResendPage resendPage) {
        WebElement ele = (WebElement) ((JavascriptExecutor) resendPage.driver)
                .executeScript("return arguments[0].shadowRoot", element);
        return ele;
    }

}
