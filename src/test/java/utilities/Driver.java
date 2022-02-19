package utilities;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.opera.OperaDriver;
import org.openqa.selenium.safari.SafariDriver;

import java.time.Duration;

public class Driver {



      public   Driver(){  //eger baska yerden driver ulasilmasin istersek obje uretilmesin istersek bunu koyariz drivere
            //alttaki yeri private yapariz singlon pattern kullanimi bu  sekilde
        }

        static WebDriver driver;

        public static WebDriver  getDriver(){

            /*if icine sarti koymasak her bu method cagirildiginda new celisir ve yeni bir browser acilir.cunku bir her isimizi artik kendi
             * olusturdugumuz getDriver methodu cagirarak yapacagiz.Eger driver bos degilse new(yeni) browser acma devam et,yok bossa ici nullsa yani bir
             * browser ac demek   */

            if(driver==null){

                switch (ConfigReader.getProperty("browser")){

                    case"chrome":
                        WebDriverManager.chromedriver().setup();
                        driver=new ChromeDriver();
                        break;
                    case"firefox":
                        WebDriverManager.chromedriver().setup();
                        driver=new FirefoxDriver();
                        break;
                    case"opera":
                        WebDriverManager.chromedriver().setup();
                        driver=new OperaDriver();
                        break;
                    case"safari":
                        WebDriverManager.chromedriver().setup();
                        driver=new SafariDriver();
                        break;

                    case "edge":
                        WebDriverManager.edgedriver().setup();
                        driver=new EdgeDriver();
                        break;

                    default:
                        WebDriverManager.chromedriver().setup();
                        driver=new ChromeDriver();
                }


            }

            driver.manage().window().maximize();
            driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(15));

            return  driver;
        }

        public  static  void  closeDriver(){
            if(driver!=null){   //eger driver icinde birsey varsa yani bos(null) degilse
                driver.quit();    // o var olanlari sil
                driver=null;    //eger driver ici bos ise kapatma
            }

        }



}
