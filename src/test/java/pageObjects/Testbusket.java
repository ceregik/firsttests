package pageObjects;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.openqa.selenium.interactions.Actions;
//import sun.tools.jconsole.Tab;


import java.util.List;
import java.util.concurrent.TimeUnit;

import static org.junit.Assert.*;


public class Testbusket extends TestBase {

    @Test
    public void basketTest() throws InterruptedException {
        app.GoToShop();
        app.AddNewProduct(3);
        app.GoToBusket();
        app.DeleteAllFromBusket();
    }

}





