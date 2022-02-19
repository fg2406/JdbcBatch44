package utilities;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

public class ConfigReader {

    public  static Properties properties;

    static {
        String path = "configuration.properties";  //once bu calisacak

        try {

            FileInputStream fis = new FileInputStream(path);  //bu try-catch arasinda sorun yoksa callisir varsa
            properties=new Properties();
            properties.load(fis);
            fis.close();


        } catch (IOException e) {
            e.printStackTrace();   //burasi calisir hata ne ise verir

        }

    }

    public static  String getProperty(String key){

        return  properties.getProperty(key);
    }





}
