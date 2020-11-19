
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.openqa.selenium.interactions.Actions;
//import sun.tools.jconsole.Tab;


import java.util.List;
import java.util.concurrent.TimeUnit;

import static org.junit.Assert.*;


public class RegAcc {

    private WebDriver driver;
    private WebDriverWait wait;

    String RandomString(int n)

    {
        String AlphaNumericString = "0123456789"
                + "abcdefghijklmnopqrstuvxyz";
        StringBuilder sb = new StringBuilder(n);
        for (int i = 0; i < n; i++) {
            int index
                    = (int)(AlphaNumericString.length()
                    * Math.random());
            sb.append(AlphaNumericString
                    .charAt(index));
        }
        return sb.toString();
    }

    public boolean isElementPresent(By locator){
        try {
            //  System.out.println("ok");
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
    public void RegAccTest() {
        driver.get("http://localhost/litecart");
        String email = RandomString(10)+ "@a.a";

        driver.findElement(By.xpath("//a [contains(text(),'New customers click here')]")).click();
        assertFalse(areElementsPresent(By.name("XXX")));

        driver.findElement(By.cssSelector("input[name=firstname]")).sendKeys("Bob",Keys.TAB,
                "Hon",Keys.TAB,
                "New-York Linden",Keys.TAB,Keys.TAB,
                "12345",Keys.TAB,
                "New-York",Keys.TAB,Keys.TAB,
                email,Keys.TAB,//sendKeys("united s",Keys.ENTER);
                "+79778085489",Keys.TAB,Keys.TAB,
                "123",Keys.TAB,"123");

        WebElement country =  driver.findElement(By.xpath("//td [contains(text(),'Country ')]"));
        new Actions(driver)
                .moveToElement(country,0,-5)
                .click()
                .sendKeys("United s",Keys.ENTER)
                .perform();

        Select select = new Select(driver.findElement(By.cssSelector("select[name=zone_code]")));
        select.selectByValue("NY");

        driver.findElement(By.cssSelector("button[name = create_account]")).click();

        driver.findElement(By.xpath("//a [contains(text(),'Logout')]")).click();

        driver.findElement(By.cssSelector("input[name=email]")).sendKeys(email,Keys.TAB,
                "123",Keys.ENTER);

        driver.findElement(By.xpath("//a [contains(text(),'Logout')]")).click();

    }

    @After
    public void stop(){
        driver.quit();
        driver = null;
    }
}




