import org.junit.Test;

public class test {

    @Test
    public void inter()
        {

        String str1 = "rgba(102, 0, 0, 1)";
        // 102 int k = Integer.parseInt(str1.substring(5,8));
        // 102 int k = Integer.parseInt(str1.substring(10,13));
         int k = Integer.parseInt(str1.substring(13,14));
            System.out.println(k);
        }

}