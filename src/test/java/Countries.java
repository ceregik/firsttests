
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.WebDriverWait;


import java.util.List;
import java.util.concurrent.TimeUnit;

import static org.junit.Assert.*;


public class Countries {

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
    public void CountriesTest() {
        driver.get("http://localhost/litecart/admin/login.php");
        driver.findElement(By.name("username")).sendKeys("admin");
        driver.findElement(By.name("password")).sendKeys("admin");
        driver.findElement(By.name("login")).click();

        assertFalse(areElementsPresent(By.name("XXX")));

        driver.findElement(By.xpath("//span[contains(text(),'Countries')]")).click();
        List<WebElement> AllCountries = driver.findElements(By.cssSelector("td:nth-child(5)"));
        List<WebElement> CountriesWithZones = driver.findElements(By.xpath("//td[6][not(contains(text(),'0'))] /../ td[5] /a"));
        List<WebElement> Zones;
        String str,str1;

        for (int i=0;i<AllCountries.size()-1;i++){
            str = AllCountries.get(i).getText();
            str1 = AllCountries.get(i+1).getText();

            if (str.compareTo(str1)>0){
                fail();
            }
        }

        for (int i=0;i<CountriesWithZones.size();i++){
            assertFalse(areElementsPresent(By.name("XXX")));
            CountriesWithZones.get(i).click();
            Zones = driver.findElements(By.cssSelector("#table-zones td:nth-child(3)"));
                for (int y=0;y<Zones.size()-2;y++){
                    str = Zones.get(y).getText();
                    str1 = Zones.get(y+1).getText();
                    if (str.compareTo(str1)>0){
                        fail();
                    }
                }
            driver.findElement(By.cssSelector("button[name = save]")).click();
            CountriesWithZones = driver.findElements(By.xpath("//td[6][not(contains(text(),'0'))] /../ td[5] /a"));
        }

        assertFalse(areElementsPresent(By.name("XXX")));
        driver.findElement(By.xpath("//span[contains(text(),'Geo Zones')]")).click();
        CountriesWithZones = driver.findElements(By.cssSelector("tr.row td:nth-child(3) a"));

        for (int i=0;i<CountriesWithZones.size();i++){
            CountriesWithZones.get(i).click();
            Zones = driver.findElements(By.cssSelector("#table-zones td:nth-child(3) option[selected = selected]"));
            for (int y=0;y<Zones.size()-1;y++){
                str = Zones.get(y).getText();
                str1 = Zones.get(y+1).getText();
                if (str.compareTo(str1)>0){
                    fail();
                }
            }
         //   assertFalse(areElementsPresent(By.name("XXX")));
            driver.findElement(By.cssSelector("button[name = save]")).click();
            CountriesWithZones = driver.findElements(By.cssSelector("tr.row td:nth-child(3) a"));
        }

    }

    @After
    public void stop(){
        driver.quit();
        driver = null;
    }
}




