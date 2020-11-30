package pageObjects;

import org.junit.After;
import org.junit.Before;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.concurrent.TimeUnit;

public class Application  {

    private WebDriver driver;
    private WebDriverWait wait;

    private MainPage mainPage;
    private BusketPage busketPage;
    private ProductPage productPage;

    public Application() {
        driver = new ChromeDriver();
        mainPage = new MainPage(driver);
        busketPage = new BusketPage(driver);
        productPage = new ProductPage(driver);
        wait = new WebDriverWait(driver,10);
        driver.manage().timeouts().implicitlyWait(600, TimeUnit.MILLISECONDS);
    }

    public void quit(){
        driver.quit();}


    public boolean areElementsPresent(By locator){ return driver.findElements(locator).size() > 0; }

    public void GoToShop() throws InterruptedException {
        mainPage.open();
    }

    public void AddNewProduct(int HowManyItems) throws InterruptedException {
        for (int y =0; y<HowManyItems;y++){
            mainPage.FirstProduct.click();
            Thread.sleep(500);
            productPage.AddProductInBusket("Small");
            productPage.MainPageButton.click();
        }
        Thread.sleep(500);
    }
    public void GoToBusket() throws InterruptedException {
        mainPage.Busket.click();
        Thread.sleep(500);
    }

    public void DeleteAllFromBusket() {
        while (!(areElementsPresent(busketPage.EndBusket()))) {
            busketPage.DeleteElement();
        }

    }
    public String CheckBusket() {
        return (productPage.TextBusket.getText());
    }
}
