import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.Select;
import utils.Utils;

import java.time.Duration;
import java.util.List;

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public class GuestRegistrationTest {
    WebDriver driver;

    @BeforeAll
    public void setup() {
        driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(30));
    }

    @Test
    public void testRegistration() throws InterruptedException {
        driver.get("https://demo.wpeverest.com/user-registration/guest-registration-form/");

        driver.findElement(By.id("first_name")).sendKeys("Sara");
        driver.findElement(By.id("last_name")).sendKeys("Shorab");
        //Email
        driver.findElement(By.id("user_email")).sendKeys("saara@example.com");
        //password
        driver.findElement(By.id("user_pass")).sendKeys("123sara&$@");
        //Gender
        driver.findElement(By.id("radio_1665627729_Female")).click();
        //Nationality
        driver.findElement(By.id("input_box_1665629217")).sendKeys("Bangladeshi");
        //phone
        List<WebElement> phoneNumber = driver.findElements(By.id("phone_1665627880"));
        phoneNumber.get(1).sendKeys("0123456789");

        Thread.sleep(6000);

        //Date of Birth
        WebElement dateField = driver.findElement(By.cssSelector("[data-id='date_box_1665628538']"));
        dateField.click();

        // Select Year (e.g., 2015)
        driver.findElement(By.cssSelector(".flatpickr-current-month")).click();
        WebElement yearDropdown = driver.findElement(By.className("numInput"));
        yearDropdown.clear();
        yearDropdown.sendKeys("2015");
        yearDropdown.sendKeys(Keys.ENTER);

        // Select Month and Day
        WebElement monthDropdown = driver.findElement(By.className("flatpickr-monthDropdown-months"));
        Select selectMonth = new Select(monthDropdown);
        selectMonth.selectByVisibleText("October");
        driver.findElement(By.xpath("//span[@aria-label='October 5, 2015']")).click();

        //country dropdown
        Select select = new Select(driver.findElement(By.id("country_1665629257")));
        select.selectByVisibleText("Bangladesh");
        select.selectByIndex(18);

        Thread.sleep(6000);

        Utils.scroll(driver, 1500);

        Thread.sleep(3000);
        //terms and conditions
        driver.findElement(By.id("privacy_policy_1665633140")).click();
        Thread.sleep(3000);
        //submit
        driver.findElement(By.className("ur-submit-button")).click();
        Thread.sleep(5000);

        //Assert Message

        String actualResult = driver.findElement(By.id("ur-submit-message-node")).getText();
        String textExpected = "User successfully registered";
        Assertions.assertTrue(actualResult.contains(textExpected));



    }

    public void finishTest() {
        driver.quit();
    }
}
