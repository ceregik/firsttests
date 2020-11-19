import net.bytebuddy.utility.RandomString;
import org.junit.Test;

import java.util.ArrayList;

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

    @Test
    public void inter()
        {
            int n=10;
            System.out.println(RandomString(n));
        }

}