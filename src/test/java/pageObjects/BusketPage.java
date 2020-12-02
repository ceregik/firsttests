package pageObjects;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;

public class BusketPage extends page {
    public BusketPage(WebDriver driver) {super(driver); PageFactory.initElements(driver, this);}

    public By EndBusket() {return By.xpath("//em [contains(text(), 'There are no items in your cart.')]");}

    @FindBy(css="li.item button[name = remove_cart_item]")
    public WebElement FirstInBusket;
   // public WebElement FirstInBusket() {return driver.findElement(By.cssSelector("li.item button[name = remove_cart_item]"));}
   @FindBy(css="div#order_confirmation-wrapper tr:nth-child(2) td.item")
   public WebElement FirstInTable;
   // public WebElement FirstInTable() {return driver.findElement(By.cssSelector("div#order_confirmation-wrapper tr:nth-child(2) td.item"));}

    public WebElement ElementInTable(String NameProduct) {return driver.findElement(By.xpath("//td [contains(text(), '" + NameProduct + "')]"));}

    public void DeleteElement(){
        wait.until(ExpectedConditions.visibilityOf(FirstInBusket));
        String NameProduct = FirstInTable.getText();
        WebElement element = ElementInTable(NameProduct);
        FirstInBusket.click();
        wait.until(ExpectedConditions.stalenessOf(element));
    }

}
