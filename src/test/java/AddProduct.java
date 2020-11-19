
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;


import java.io.File;
import java.util.List;
import java.util.concurrent.TimeUnit;

import static org.junit.Assert.*;


public class AddProduct {

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
        driver.manage().timeouts().implicitlyWait(1000, TimeUnit.MILLISECONDS);
    }

    @Test
    public void AddProductTest() {
        String selectAll = Keys.chord(Keys.CONTROL, "a");
        File file = new File("src/test/resources/pirate_duck.jpg");
        driver.get("http://localhost/litecart/admin/login.php");
        driver.findElement(By.name("username")).sendKeys("admin");
        driver.findElement(By.name("password")).sendKeys("admin");
        driver.findElement(By.name("login")).click();

        assertFalse(areElementsPresent(By.name("XXX")));

        driver.findElement(By.xpath("//span[contains(text(),'Catalog')]")).click();
        assertFalse(areElementsPresent(By.name("XXX")));
        driver.findElement(By.xpath("//a [contains(text(),' Add New Product')]")).click();
        assertFalse(areElementsPresent(By.name("XXX")));
        //General
        driver.findElement(By.cssSelector("input[name=status]")).click();
        driver.findElement(By.cssSelector("input[name='name[en]']")).sendKeys("Pirate duck",Keys.TAB,
                "123",Keys.TAB);
        driver.findElement(By.cssSelector("input[name='quantity']")).sendKeys(selectAll,Keys.BACK_SPACE,"20");
        driver.findElement(By.cssSelector("input[name='date_valid_from']")).sendKeys("01112020");
        driver.findElement(By.cssSelector("input[name='date_valid_to']")).sendKeys("30112020");
        driver.findElement(By.xpath("//td[contains(text(),'Unisex')] /../ td")).click();
        driver.findElement(By.cssSelector("input[data-name = 'Root']")).click();
        driver.findElement(By.cssSelector("input[data-name = 'Rubber Ducks']")).click();
        Select select = new Select(driver.findElement(By.cssSelector("select[name=default_category_id]")));
        select.selectByValue("1");
        driver.findElement(By.cssSelector("input[name='new_images[]']")).sendKeys(file.getAbsolutePath());
        //Information
        driver.findElement(By.xpath("//a [contains(text(),'Information')]")).click();
        assertFalse(areElementsPresent(By.name("XXX")));
        select = new Select(driver.findElement(By.cssSelector("select[name=manufacturer_id]")));
        select.selectByValue("1");
        driver.findElement(By.cssSelector("input[name=keywords]")).sendKeys(
                    "Pirate, duck, pirateduck, water, bath, bathduck",Keys.TAB,
                                "It is pirate duck", Keys.TAB,
                                "It is one of legend pirate duck" , Keys.TAB,
                                "Pirate duck", Keys.TAB,
                                "duck");
        //Prices
        driver.findElement(By.xpath("//a [contains(text(),'Prices')]")).click();
        assertFalse(areElementsPresent(By.name("XXX")));
        driver.findElement(By.cssSelector("input[name=purchase_price]")).sendKeys(selectAll,Keys.BACK_SPACE,"20");
        select = new Select(driver.findElement(By.cssSelector("select[name=purchase_price_currency_code]")));
        select.selectByValue("USD");
        driver.findElement(By.cssSelector("input[name='prices[USD]']")).sendKeys("20");
        driver.findElement(By.cssSelector("input[name='prices[EUR]']")).sendKeys("14.52");

        driver.findElement(By.cssSelector("button[name = save]")).click();

         }

    @After
    public void stop(){
        driver.quit();
        driver = null;
    }
}




