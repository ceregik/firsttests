
import org.jetbrains.annotations.Nullable;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.WebDriverWait;


import java.util.List;
import java.util.Set;
import java.util.concurrent.TimeUnit;

import static org.junit.Assert.*;


public class Windows {

    private WebDriver driver;
    private WebDriverWait wait;

    public ExpectedCondition<String>anyWindowOtherThan(Set<String>oldWindows){
        return new ExpectedCondition<String>() {
            public String apply(WebDriver webDriver) {
                Set<String> handles = driver.getWindowHandles();
                handles.removeAll(oldWindows);
                return handles.size()>0 ? handles.iterator().next() : null;
            }
        };
    }

    public boolean isElementPresent(By locator){
        try {
            driver.findElement(locator);
            return true;
        } catch (NoSuchElementException ex){
            System.out.println("Элемент не найден");
            return false;
        }
    }

    public boolean areElementsPresent(By locator){
        return driver.findElements(locator).size() > 0;
    }

    @Before
    public void start() {
        driver = new ChromeDriver();
        wait = new WebDriverWait(driver, 1);
        driver.manage().timeouts().implicitlyWait(600, TimeUnit.MILLISECONDS);
    }

    @Test
    public void WindowsTest() throws InterruptedException {
        driver.get("http://localhost/litecart/admin/login.php");
        driver.findElement(By.name("username")).sendKeys("admin");
        driver.findElement(By.name("password")).sendKeys("admin");
        driver.findElement(By.name("login")).click();

        Thread.sleep(500);

        driver.findElement(By.xpath("//span[contains(text(),'Countries')]")).click();
        Thread.sleep(500);
        driver.findElement(By.cssSelector("td:nth-child(5) a")).click();
        List<WebElement> blank = driver.findElements(By.cssSelector("i.fa.fa-external-link"));
        String originalWindow = driver.getWindowHandle();
        Set<String> oldWindows = driver.getWindowHandles();
        for (int i=0;i<blank.size()-1;i++){
            blank.get(i).click();
            String newWindow = wait.until(anyWindowOtherThan(oldWindows));
            driver.switchTo().window(newWindow);
            driver.close();
            driver.switchTo().window(originalWindow);
        }

    }

    @After
    public void stop(){
        driver.quit();
        driver = null;
    }
}




