package pageObjects;

import io.cucumber.java8.En;
import org.junit.Assert;

import javax.xml.transform.Result;

public class Steps implements En  {

    private static Application app = new Application();
    static {
        Runtime.getRuntime().addShutdownHook(new Thread(()-> {app.quit();app = null;}));
    }


    public Steps() {

        When("Go to the shop", () ->{
            app.GoToShop();
        });
        And("Add {string} first product in buscket",(String count) ->{
            app.AddNewProduct(Integer.parseInt(count));
        });
        And("Go to the buscket", () ->{
            app.GoToBusket();
        });
        And("Delete all products", () ->{
            app.DeleteAllFromBusket();
        });

        Then("There no items", () ->{
            app.GoToShop();
            Assert.assertEquals(app.CheckBusket(),"0");
        });
    }
}
