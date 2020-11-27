package pageObjects;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import java.util.List;

public class MainPage extends page {
    public MainPage(WebDriver driver) {super(driver); PageFactory.initElements(driver, this);}

    public void open() {driver.get("http://localhost/litecart");}

    @FindBy(css=".product")
    public WebElement FirstProduct;
   // public WebElement FirstProduct() {return driver.findElement(By.cssSelector(".product"));}

   @FindBy(xpath="//a [contains(text(),'Checkout »')]")
   public WebElement Busket;
   // public WebElement Busket() {return driver.findElement(By.xpath("//a [contains(text(),'Checkout »')]"));}

   @FindBy(xpath="//span [contains(@class, 'quantity')]")
   public WebElement CountProducts;
  //  public WebElement CountProducts() {return driver.findElement(By.xpath("//span [contains(@class, 'quantity')]"));}

}
