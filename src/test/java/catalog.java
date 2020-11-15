
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.WebDriverWait;


import java.util.List;
import java.util.concurrent.TimeUnit;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;


public class catalog {

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
            driver.manage().timeouts().implicitlyWait(600, TimeUnit.MILLISECONDS);
        }

        @Test
        public void catalogTest() {
            driver.get("http://localhost/litecart/admin/login.php");
            driver.findElement(By.name("username")).sendKeys("admin");
            driver.findElement(By.name("password")).sendKeys("admin");
            driver.findElement(By.name("login")).click();

            assertFalse(areElementsPresent(By.name("XXX")));

            List<WebElement> MainElements = driver.findElements(By.cssSelector("#app-"));

            for(int i=1;i<=MainElements.size();i++){

                driver.findElement(By.cssSelector("#app-:nth-child(" + i +")")).click();
                assertTrue(isElementPresent(By.cssSelector("#content h1")));
                List<WebElement> SecondElements = driver.findElements(By.cssSelector("#app- li"));

                for(int y=2;y<=SecondElements.size();y++){
                    driver.findElement(By.cssSelector("#app- li:nth-child(" + y +")")).click();
                    assertTrue(isElementPresent(By.cssSelector("#content h1")));
                }

                assertFalse(areElementsPresent(By.name("XXX")));
            }

        }

        @After
        public void stop(){
            driver.quit();
            driver = null;
        }
    }




