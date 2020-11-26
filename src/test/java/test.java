import net.bytebuddy.utility.RandomString;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.ArrayList;
import java.util.concurrent.TimeUnit;

/*public class RandomString {



    // функция для генерации случайной строки длиной n

    static String getAlphaNumericString(int n)

    {
        // выбрал символ случайный из этой строки
        String AlphaNumericString = "ABCDEFGHIJKLMNOPQRSTUVWXYZ"
                + "0123456789"
                + "abcdefghijklmnopqrstuvxyz";
        // создаем StringBuffer размером AlphaNumericString
        StringBuilder sb = new StringBuilder(n);
        for (int i = 0; i < n; i++) {
            // генерируем случайное число между
            // 0 переменной длины AlphaNumericString
            int index
                    = (int)(AlphaNumericString.length()
                    * Math.random());
            // добавляем символ один за другим в конец sb
            sb.append(AlphaNumericString
                    .charAt(index));
        }
        return sb.toString();
    }
    public static void main(String[] args)
    {
        // Получаем размер n
        int n = 20;
        // Получить и отобразить буквенно-цифровую строку
        System.out.println(RandomString
                .getAlphaNumericString(n));
    }

}*/

public class test {

    private WebDriver driver;
    private WebDriverWait wait;

    String RandomString(int n)

    {
        String AlphaNumericString = "0123456789"
                + "abcdefghijklmnopqrstuvxyz";
        StringBuilder sb = new StringBuilder(n);
        for (int i = 0; i < n; i++) {
            int index
                    = (int)(AlphaNumericString.length()
                    * Math.random());
            sb.append(AlphaNumericString
                    .charAt(index));
        }
        return sb.toString();
    }

    @Before
    public void start() {
        driver = new ChromeDriver();
        wait = new WebDriverWait(driver, 1);
        driver.manage().timeouts().implicitlyWait(10000, TimeUnit.MILLISECONDS);
    }

    @Test
    public void inter() throws InterruptedException {


            driver.get("https://strelka.com/ru");
    //    Thread.sleep(2000);
        wait.until(ExpectedConditions.visibilityOf(driver.findElement(By.cssSelector("div.sc-daURTG"))));
            driver.findElement(By.cssSelector("div.sc-daURTG")).click();
            Thread.sleep(100000);
            //System.out.println(RandomString(n));
        }


        @After

        public void stop(){
            driver.quit();
            driver = null;
        }
}