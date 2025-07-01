package blink22;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.Test;

public class BlogSubscriptionTest extends BaseTest {

    @Test(priority = 1)
    public void extractPlaceholders() {
        WebElement nameField = driver.findElement(By.name("name"));
        WebElement emailField = driver.findElement(By.name("email"));

        System.out.println("Name Placeholder: " + nameField.getAttribute("placeholder"));
        System.out.println("Email Placeholder: " + emailField.getAttribute("placeholder"));

        Assert.assertNotNull(nameField.getAttribute("placeholder"));
        Assert.assertNotNull(emailField.getAttribute("placeholder"));
    }

    @Test(priority = 2)
    public void validateRequiredFields() {
        driver.findElement(By.xpath("//button[contains(text(),'Subscribe')]")).click();

        // This assumes some visible error appears – adjust selector as needed
        WebElement nameError = driver.findElement(By.xpath("//*[contains(text(),'name')]"));
        WebElement emailError = driver.findElement(By.xpath("//*[contains(text(),'email')]"));

        Assert.assertTrue(nameError.isDisplayed() || emailError.isDisplayed());
    }

    @Test(priority = 3)
    public void validateInvalidEmail() {
        driver.findElement(By.name("name")).sendKeys("Test User");
        WebElement emailField = driver.findElement(By.name("email"));
        emailField.sendKeys("abc@");

        driver.findElement(By.xpath("//button[contains(text(),'Subscribe')]")).click();

        // Replace with actual validation check
        WebElement error = driver.findElement(By.xpath("//*[contains(text(),'valid email')]"));
        Assert.assertTrue(error.isDisplayed());
    }

    @Test(priority = 4)
    public void validSubmission() throws InterruptedException {
        driver.findElement(By.name("email")).clear();
        driver.findElement(By.name("email")).sendKeys("testuser123@example.com");

        driver.findElement(By.xpath("//button[contains(text(),'Subscribe')]")).click();
        Thread.sleep(2000); // Replace with proper wait

        WebElement success = driver.findElement(By.className("form-success-message"));
        System.out.println("Success Message: " + success.getText());
        Assert.assertTrue(success.isDisplayed());
    }
}
