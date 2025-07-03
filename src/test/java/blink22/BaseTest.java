package blink22;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.edge.EdgeDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;

public class BaseTest {
    protected WebDriver driver;

    @BeforeClass
    public void setUp() throws InterruptedException {
        driver = new EdgeDriver();
        driver.manage().window().maximize();
        driver.get("https://www.blink22.com/");
        WebElement BlogTab = driver.findElement(By.linkText("Blog"));
        BlogTab.click();
        Thread.sleep(2000);
        System.out.println("Page Title: " + driver.getTitle());
    }

    @AfterClass
    public void tearDown() {
        driver.quit();
    }
}
