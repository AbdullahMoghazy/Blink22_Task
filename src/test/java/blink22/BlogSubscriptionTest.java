package blink22;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.Test;

public class BlogSubscriptionTest extends BaseTest {

    @Test(priority = 1)
    public void extractPlaceholders() throws InterruptedException {
        Thread.sleep(3000);

        WebElement nameField = driver.findElement(By.id("fullname"));
        String namePlaceholder = nameField.getAttribute("placeholder");
        System.out.println("Name Placeholder: " + namePlaceholder);
        Assert.assertEquals(namePlaceholder, "Type your name", "Full Name placeholder mismatch");

        WebElement emailField = driver.findElement(By.id("email"));
        String emailPlaceholder = emailField.getAttribute("placeholder");
        System.out.println("Email Placeholder: " + emailPlaceholder);
        Assert.assertEquals(emailPlaceholder, "Your email...", "Email placeholder mismatch");

        System.out.println("All placeholders are correct.");
    }

    @Test(priority = 2)
    public void validateRequiredFields() throws InterruptedException {
        WebElement nameField = driver.findElement(By.id("fullname"));
        nameField.sendKeys("Abdullahmoghazy");
        WebElement submitButton = driver.findElement(By.className("_submit"));
        submitButton.click();

        Thread.sleep(1000);

        boolean errormessage = driver.findElements(By.className("_error-inner")).size() > 0;

        Assert.assertTrue(errormessage, "Form was submitted even though email is missing!");

        System.out.println("Form not submitted when required field is missing and error message appeared.");
    }

    @Test(priority = 3)
    public void validateInvalidEmail() throws InterruptedException {
        WebElement nameField = driver.findElement(By.id("fullname"));
        nameField.clear();
        nameField.sendKeys("Abdullahmoghazy");

        WebElement emailField = driver.findElement(By.id("email"));
        emailField.clear();
        emailField.sendKeys("abc@");

        WebElement submitButton = driver.findElement(By.className("_submit"));
        submitButton.click();

        Thread.sleep(1000);

        WebElement error = driver.findElement(By.xpath("//*[contains(text(),'Enter a valid email address.')]"));
        Assert.assertTrue(error.isDisplayed());
        System.out.println("Form not submitted when entering invalid email address and error message appeared.");

    }

    @Test(priority = 4)
    public void validSubmission() throws InterruptedException {
        WebElement nameField = driver.findElement(By.id("fullname"));
        nameField.clear();
        nameField.sendKeys("Abdullahmoghazy");

        WebElement emailField = driver.findElement(By.id("email"));
        emailField.clear();
        emailField.sendKeys("abdullahmoghazy@icloud.com");

        WebElement submitButton = driver.findElement(By.className("_submit"));
        submitButton.click();
        Thread.sleep(3000);

        WebElement success = driver.findElement(By.className("_form-thank-you"));
        System.out.println("Success Message: " + success.getText());
        Assert.assertTrue(success.isDisplayed());
    }
}
