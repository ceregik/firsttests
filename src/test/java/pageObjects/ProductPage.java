package pageObjects;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;

import java.util.List;

public class ProductPage extends page {


    public ProductPage(WebDriver driver) {super(driver); PageFactory.initElements(driver, this);}

    @FindBy(css="select[name = 'options[Size]']")
    public List<WebElement> Sizes;
   // public List<WebElement> Sizes() {return driver.findElements(By.cssSelector("select[name = 'options[Size]']"));}

    @FindBy(css="select[name = 'options[Size]']")
    public WebElement Size;
    //public WebElement Size() {return driver.findElement(By.cssSelector("select[name = 'options[Size]']"));}

    @FindBy(css="button[name=add_cart_product]")
    public WebElement AddButton;
   // public WebElement AddButton() {return driver.findElement(By.cssSelector("button[name=add_cart_product]"));}

   @FindBy(css="div#logotype-wrapper")
   public WebElement MainPageButton;
   // public WebElement MainPageButton() {return driver.findElement(By.cssSelector("div#logotype-wrapper"));}

   @FindBy(xpath="//span [contains(@class, 'quantity')]")
   public WebElement TextBusket;
    public By TextBusket() {return By.xpath("//span [contains(@class, 'quantity')]");}

    public void AddProductInBusket(String SizeDucks){
        WebElement busket = TextBusket;
        int CountItems = Integer.parseInt(busket.getText())+1;
        String CountItemsString = "" + CountItems;

        if (Sizes.size()>0) {
            Select select = new Select(Size);
            select.selectByValue(SizeDucks);
        }

        AddButton.click();
        wait.until(ExpectedConditions.textToBe(TextBusket(),CountItemsString));
    }
}
