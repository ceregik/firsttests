
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


public class stickers {

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
    public void stickersTest() {
        driver.get("http://localhost/litecart");
        List<WebElement> Box = driver.findElements(By.cssSelector("#box-most-popular li.product.column.shadow.hover-light"));
        for(int i=1;i<=Box.size();i++){
            assertTrue(isElementPresent(By.cssSelector("#box-most-popular li.product.column.shadow.hover-light:nth-child(" + i + ") div.sticker")));
        }

        Box = driver.findElements(By.cssSelector("#box-campaigns li.product.column.shadow.hover-light"));
        for(int i=1;i<=Box.size();i++){
            assertTrue(isElementPresent(By.cssSelector("#box-campaigns li.product.column.shadow.hover-light:nth-child(" + i + ") div.sticker")));
        }

         Box = driver.findElements(By.cssSelector("#box-latest-products li.product.column.shadow.hover-light"));
        for(int i=1;i<=Box.size();i++){
            assertTrue(isElementPresent(By.cssSelector("#box-latest-products li.product.column.shadow.hover-light:nth-child(" + i + ") div.sticker")));
        }

    }

    @After
    public void stop(){
        driver.quit();
        driver = null;
    }
}




