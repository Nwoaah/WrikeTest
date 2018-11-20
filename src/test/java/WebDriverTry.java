import io.qameta.allure.Attachment;
import io.qameta.allure.Step;
import org.openqa.selenium.*;


public class WebDriverTry {

    private WebDriver driver;

    public WebDriverTry(WebDriver driver) {
        this.driver = driver;
    }

    @Step
    public void openMainPage() {
        driver.get("https://www.wrike.com/");
        driver.manage().window().maximize();
    }

    @Step
    public void GetStartedForFree() {

        // driver.findElement(By.className("wg-header__free-trial-button wg-btn wg-btn--green")).submit();
        //   driver.findElement(By.xpath("/html/body/div[1]/header/div[3]/div[2]/div/div/div[2]/div/form/button")).submit();
//        new WebDriverWait(driver, 10)
//                .withMessage("Could not load results page")
//                .until(mainContainLoaded());
        //  new WebDriverWait(driver,2);

        WebElement input = driver.findElement(By.xpath("/html/body/div[1]/header/div[3]/div[2]/div/div/div[2]/div/form/button"));
        input.click();
        //  driver.switchTo().activeElement();

        //new Actions(driver).moveToElement(input).click().perform();

    }

    @Step
    public void FillInEmail(String email) {
//       driver.findElement(By.className("wg-input modal-form-trial__input")).sendKeys(email);
        WebElement input = driver.findElement(By.cssSelector(".modal-form-trial__input"));
        input.sendKeys(email);
        //driver.findElement(By.name("email")).sendKeys(email);

    }

    @Step
    public void CreateMyWrikeAcc() {
        driver.findElement(By.cssSelector(".modal-form-trial__submit")).click();
    }


    @Attachment
    @Step("Make screen shot of results page")
    public byte[] makeScreenShot() {
        return ((TakesScreenshot) driver).getScreenshotAs(OutputType.BYTES);
    }

    public void quit() {
        driver.quit();
    }


}
