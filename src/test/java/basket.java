
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.openqa.selenium.interactions.Actions;
//import sun.tools.jconsole.Tab;


import java.util.List;
import java.util.concurrent.TimeUnit;

import static org.junit.Assert.*;


public class basket {

    private WebDriver driver;
    private WebDriverWait wait;

    public boolean areElementsPresent(By locator){
        return driver.findElements(locator).size() > 0;
    }

    @Before
    public void start() {
        driver = new ChromeDriver();
        wait = new WebDriverWait(driver, 10);
        driver.manage().timeouts().implicitlyWait(600, TimeUnit.MILLISECONDS);
    }

    @Test
    public void basketTest() {
        driver.get("http://localhost/litecart");
        int i;
        String n;
        for (int y =0; y<3;y++){
            driver.findElement(By.cssSelector(".product")).click();
            assertFalse(areElementsPresent(By.name("XXX")));
            WebElement busket = driver.findElement(By.xpath("//span [contains(@class, 'quantity')]"));
            i = Integer.parseInt(busket.getText())+1;
            n = "" + i;

            if (areElementsPresent(By.cssSelector("select[name = 'options[Size]']"))) {
                Select select = new Select(driver.findElement(By.cssSelector("select[name = 'options[Size]']")));
                select.selectByValue("Small");
            }

            driver.findElement(By.cssSelector("button[name=add_cart_product]")).click();
            wait.until(ExpectedConditions.textToBe(By.xpath("//span [contains(@class, 'quantity')]"),n));
            driver.findElement(By.cssSelector("div#logotype-wrapper")).click();
        }

        assertFalse(areElementsPresent(By.name("XXX")));
        driver.findElement(By.xpath("//a [contains(text(),'Checkout »')]")).click();
        assertFalse(areElementsPresent(By.name("XXX")));

        while (!(areElementsPresent(By.xpath("//em [contains(text(), 'There are no items in your cart.')]")))) {
            wait.until(ExpectedConditions.visibilityOf(driver.findElement(By.cssSelector("li.item button[name = remove_cart_item]"))));
            driver.findElement(By.cssSelector("li.item button[name = remove_cart_item]")).click();
            n = driver.findElement(By.cssSelector("div#order_confirmation-wrapper tr:nth-child(2) td.item")).getText();
            wait.until(ExpectedConditions.stalenessOf(driver.findElement(By.xpath("//td [contains(text(), '" + n + "')]"))));
        }

    }

    @After
    public void stop(){
        driver.quit();
        driver = null;
    }
}




