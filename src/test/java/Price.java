
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.WebDriverWait;


import java.util.List;
import java.util.concurrent.TimeUnit;

import static org.junit.Assert.*;


public class Price {

    private WebDriver driver;
    private WebDriverWait wait;

    @Before
    public void start() {
        driver = new ChromeDriver();
        wait = new WebDriverWait(driver, 1);
        driver.manage().timeouts().implicitlyWait(600, TimeUnit.MILLISECONDS);
    }

    @Test
    public void PriceTest() {
        driver.get("http://localhost/litecart");

        Boolean equalsNP,CamPrice,RegPrice;

        WebElement link = driver.findElement(By.cssSelector("#box-campaigns .product"));
        String name = link.findElement(By.cssSelector("div.name")).getText();
        String PriceBefore = link.findElement(By.cssSelector("s.regular-price")).getText();
        String PriceSale = link.findElement(By.cssSelector("strong.campaign-price")).getText();

        int r,g,b;
        String str;

        str = link.findElement(By.cssSelector("s.regular-price")).getCssValue("color");
        r = Integer.parseInt(str.substring(5,8));
        g = Integer.parseInt(str.substring(10,13));
        b = Integer.parseInt(str.substring(15,18));
        RegPrice = (link.findElement(By.cssSelector("s.regular-price")).getCssValue("text-decoration-line").equals("line-through"))&
                (r == g)& (g == b);
        if (!RegPrice){
            fail();
        }

        str = link.findElement(By.cssSelector("strong.campaign-price")).getCssValue("color");
        g = Integer.parseInt(str.substring(10,11));
        b = Integer.parseInt(str.substring(13,14));
        CamPrice = (link.findElement(By.cssSelector("strong.campaign-price")).getCssValue("font-weight").equals("700")&
                (g == b) & (b == 0));
        if (!CamPrice){
            fail();
        }


        int PriceBeforeSize = Integer.parseInt(link.findElement(By.cssSelector("s.regular-price")).getCssValue("font-size").substring(0,2));
        int PriceSaleSize = Integer.parseInt(link.findElement(By.cssSelector("strong.campaign-price")).getCssValue("font-size").substring(0,2));
        if (PriceBeforeSize>PriceSaleSize){
            fail();
        }

        link.click();

        link = driver.findElement(By.cssSelector("#box-product"));
        String nameIn = link.findElement(By.cssSelector("h1")).getText();
        String PriceBeforeIn = link.findElement(By.cssSelector("s.regular-price")).getText();
        String PriceSaleIn = link.findElement(By.cssSelector("strong.campaign-price")).getText();

        equalsNP = ((name.equals(nameIn))&(PriceBefore.equals(PriceBeforeIn))&(PriceSale.equals(PriceSaleIn)));
        if (!equalsNP){
            fail();
        }

        str = link.findElement(By.cssSelector("s.regular-price")).getCssValue("color");
        r = Integer.parseInt(str.substring(5,8));
        g = Integer.parseInt(str.substring(10,13));
        b = Integer.parseInt(str.substring(15,18));
        RegPrice = (link.findElement(By.cssSelector("s.regular-price")).getCssValue("text-decoration-line").equals("line-through"))&
                (r == g)& (g == b);
        if (!RegPrice){
            fail();
        }


        str = link.findElement(By.cssSelector("strong.campaign-price")).getCssValue("color");
        g = Integer.parseInt(str.substring(10,11));
        b = Integer.parseInt(str.substring(13,14));
        CamPrice = (link.findElement(By.cssSelector("strong.campaign-price")).getCssValue("font-weight").equals("700")&
                (g == b) & (b == 0));
        if (!CamPrice){
            fail();
        }

        PriceBeforeSize = Integer.parseInt(link.findElement(By.cssSelector("s.regular-price")).getCssValue("font-size").substring(0,2));
        PriceSaleSize = Integer.parseInt(link.findElement(By.cssSelector("strong.campaign-price")).getCssValue("font-size").substring(0,2));
        if (PriceBeforeSize>PriceSaleSize){
            fail();
        }

    }

    @After
    public void stop(){
        driver.quit();
        driver = null;
    }
}




