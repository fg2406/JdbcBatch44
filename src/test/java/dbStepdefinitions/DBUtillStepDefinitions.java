package dbStepdefinitions;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import org.junit.Assert;
import utilities.DBUtils;

import java.sql.SQLException;

public class DBUtillStepDefinitions {


    @Given("kullanici DBUtil ile HMC veri tabanina baglanir")
    public void kullanici_db_util_ile_hmc_veri_tabanina_baglanir() {
        DBUtils.getConnection();  //DBUtils class olusturduk,bundan sonra bununla devam edecegiz,birkez kullandiysak devam etmeliyiz,
        // Driver. yaptigimiz gibi,
    }
    @Given("kullanici DBUtil ile {string} tablosundaki {string} verilerini alir")
    public void kullanici_db_util_ile_tablosundaki_verilerini_alir(String table, String field) {
        // SELECT field FROM table;
        String readQuery="SELECT "+field+" FROM " + table;
        DBUtils.executeQuery(readQuery);
    }
    @Given("kullanici DBUtil ile {string} sutunundaki verileri okur")
    public void kullanici_db_util_ile_sutunundaki_verileri_okur(String field) throws SQLException {
        DBUtils.getResultset().first();
    }


    @Given("DBUtil ile tum {string} degerlerini sira numarasi ile yazdirir")
    public void db_util_ile_tum_degerlerini_sira_numarasi_ile_yazdirir(String field) throws Exception {
        int satirSayisi=DBUtils.getRowCount(); //bu en sona goturuyor
        DBUtils.getResultset().first();        //bizde burda basa getirdik
        int siraNo=1;
        for (int i=1 ; i<satirSayisi ; i++){
            System.out.println(i + ".ci satirdaki"+field+": " + DBUtils.getResultset().getString(field));
            DBUtils.getResultset().next();   //bu bizi bir sonrakine goturur
            siraNo++;                        //sira no degisti
        }
    }



    @Then("DBUtill ile {int}. {string} in {int} oldugunu test eder")
    public void db_utill_ile_in_oldugunu_test_eder(Integer siraNo, String field, Integer expectedPrice) throws SQLException {
        DBUtils.getResultset().absolute(siraNo);
        double actualPrice=DBUtils.getResultset().getDouble(field);
        Assert.assertTrue(actualPrice==expectedPrice);
    }


    @Then("tHotel tablosunda IDHotel degeri {int} olan kaydin Email bilgisini {string} yapar")
    public void thotelTablosundaIDHotelDegeriOlanKaydinEmailBilgisiniYapar(int IDHotel, String yeniEmail) {

        String updateQery="UPDATE tHOTEL SET Email='"+yeniEmail+"' WHERE IDHotel='"+IDHotel+"';";
        //normalde sql de, UPDATE Email FROM tHOTEL WHERE IDHotel='1016'; yapariz

    }
}

