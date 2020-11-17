
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

        String PriceBeforeSize = link.findElement(By.cssSelector("s.regular-price")).getCssValue("font-size");
        String PriceSaleSize = link.findElement(By.cssSelector("strong.campaign-price")).getCssValue("font-size");
        if (PriceBeforeSize.compareTo(PriceSaleSize)>0){
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

        RegPrice = (link.findElement(By.cssSelector("s.regular-price")).getCssValue("text-decoration-line").equals("line-through")&
                link.findElement(By.cssSelector("s.regular-price")).getCssValue("color").equals("rgba(102, 102, 102, 1)"));
        if (!RegPrice){
            fail();
        }

        CamPrice = (link.findElement(By.cssSelector("strong.campaign-price")).getCssValue("font-weight").equals("700")&
                link.findElement(By.cssSelector("strong.campaign-price")).getCssValue("color").equals("rgba(204, 0, 0, 1)"));
        if (!CamPrice){
            fail();
        }

        PriceBeforeSize = link.findElement(By.cssSelector("s.regular-price")).getCssValue("font-size");
        PriceSaleSize = link.findElement(By.cssSelector("strong.campaign-price")).getCssValue("font-size");
        if (PriceBeforeSize.compareTo(PriceSaleSize)>0){
            fail();
        }

    }

    @After
    public void stop(){
        driver.quit();
        driver = null;
    }
}




