package pageObjects;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class MainPage extends page {
    public MainPage(WebDriver driver) {super(driver);}

    public void open() {driver.get("http://localhost/litecart");}

    public WebElement FirstProduct() {return driver.findElement(By.cssSelector(".product"));}
    public WebElement Busket() {return driver.findElement(By.xpath("//a [contains(text(),'Checkout »')]"));}
    public WebElement CountProducts() {return driver.findElement(By.xpath("//span [contains(@class, 'quantity')]"));}
}
