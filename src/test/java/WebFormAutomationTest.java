import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import utils.Utils;

import java.io.File;
import java.time.Duration;

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public class WebFormAutomationTest {
    WebDriver driver;

    @BeforeAll
    public void setup() {
        driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(30));
    }

    @Test
    public void testSubmissionFile() throws InterruptedException {
        driver.get("https://www.digitalunite.com/practice-webform-learners");
        // Fill-up form
        driver.findElement(By.id("edit-name")).sendKeys("Rafi");
        driver.findElement(By.id("edit-number")).sendKeys("01234567890");

        WebElement txtCalenderElem = driver.findElement(By.id("edit-date"));
        txtCalenderElem.sendKeys(Keys.CONTROL, "a");
        txtCalenderElem.sendKeys(Keys.BACK_SPACE);
        txtCalenderElem.sendKeys("11/18/2024");

        driver.findElement(By.id("edit-email")).sendKeys("rafi@example.com");
        driver.findElement(By.id("edit-tell-us-a-bit-about-yourself-")).sendKeys("I am a student, I am learning Selenium for automation testing");
        //Scroll

        Utils.scroll(driver, 500);

        String relativePath = "\\src\\test\\resources\\test case.xlsx";
        String absolutePath = System.getProperty("user.dir") + relativePath;
        driver.findElement(By.id("edit-uploadocument-upload")).sendKeys(absolutePath);

        Thread.sleep(6000);
        // submit

        driver.findElement(By.id("edit-age")).click();
        driver.findElement(By.id("edit-submit")).click();


        //Assert message
        String actualResult = driver.findElement(By.id("block-pagetitle-2")).getText();
        String textExpected = "Thank you for your submission!";
        Assertions.assertTrue(actualResult.contains(textExpected));


    }


    public void finishTest() {
        driver.quit();
    }

}
