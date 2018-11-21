package deprecated;

import io.qameta.allure.Attachment;
import io.qameta.allure.Step;
import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.Iterator;
import java.util.concurrent.TimeUnit;


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
        WebElement input = driver.findElement(By.xpath("/html/body/div[1]/header/div[3]/div[2]/div/div/div[2]/div/form/button"));
        input.click();
    }

    @Step
    public void FillInEmail(String email) {
        WebElement input = driver.findElement(By.cssSelector(".modal-form-trial__input"));
        input.sendKeys(email);
    }

    @Step
    public void CreateMyWrikeAcc() {

        driver.findElement(By.cssSelector(".modal-form-trial__submit")).click();

        try {
            Thread.sleep(3000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }



//        System.out.println(driver.getCurrentUrl());
       // System.out.println(driver.getPageSource());
//        System.out.println();
      //  driver.findElement(By.xpath("/html/body/c-wiz/div/div/div[2]/div[4]/span[1]/div/content/span")).click();

        System.out.println(driver.findElements(By.tagName("iframe")).size());
        driver.switchTo().frame(1);
        driver.findElement(By.xpath("/html/body/c-wiz/div/div/div[2]/div[4]/span[1]/div")).click();

        //  System.out.println(driver.findElement(By.tagName("iframe")));
       // driver.findElement(By.tagName("iframe")).findElement(By.className("CwaK9")).click();

       // driver.switchTo().frame("iframe").close();
        // driver.findElement(By.xpath("/html/body/c-wiz/div/div/div[2]/div[4]/span[1]/div")).click();

//        WebElement iFrame= driver.findElement(By.className("SxbKmc"));
//        iFrame.findElement(By.cssSelector(".ZFr60d")).click();

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
