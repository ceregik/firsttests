
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.logging.LogEntry;
import org.openqa.selenium.support.ui.WebDriverWait;


import java.util.List;
import java.util.concurrent.TimeUnit;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;


public class Logi {

    private WebDriver driver;
    private WebDriverWait wait;

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
        driver.manage().timeouts().implicitlyWait(10000, TimeUnit.MILLISECONDS);
    }

    @Test
    public void LogiTest() throws InterruptedException {
        driver.get("http://localhost/litecart/admin/login.php");
        driver.findElement(By.name("username")).sendKeys("admin");
        driver.findElement(By.name("password")).sendKeys("admin");
        driver.findElement(By.name("login")).click();

        Thread.sleep(500);
        driver.findElement(By.xpath("//span[contains(text(),'Catalog')]")).click();
        Thread.sleep(500);
        driver.findElement(By.xpath("//a[contains(text(),'Rubber Ducks')]")).click();
        driver.findElement(By.cssSelector("input[type=search]")).sendKeys("duck",Keys.ENTER);
        List<WebElement> ducks = driver.findElements(By.cssSelector("table.dataTable tr"));
        for(int i=1;i<ducks.size()-1;i++){
           // Thread.sleep(500);
            ducks.get(i).findElement(By.cssSelector("a")).click();
            Thread.sleep(500);
            driver.findElement(By.cssSelector("button[name=save]")).click();
            Thread.sleep(500);
            driver.findElement(By.cssSelector("input[type=search]")).sendKeys("duck",Keys.ENTER);
            ducks = driver.findElements(By.cssSelector("table.dataTable tr"));
            
        }
        for (LogEntry l : driver.manage().logs().get("browser").getAll()) {
            System.out.println(l);
        }
       // Thread.sleep(100000);
    }

    @After
    public void stop(){
        driver.quit();
        driver = null;
    }
}




