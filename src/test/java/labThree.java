import DataController.DataControllerSMSModule;
import Models.SMSModel.GetSMSRequestModel;
import Models.SMSModel.GetSMSResponseModel;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import java.sql.SQLException;
import java.util.List;

import static io.restassured.RestAssured.given;

public class labThree {


    @DataProvider(name = "getSmsRequestModels")
    public Object [] [] getSmsRequestModels() throws SQLException {
        List<GetSMSRequestModel> getSMSRequestModels = DataControllerSMSModule.getSMSRequestModels(DataControllerSMSModule.queryGetNum);
        Object[][] reqData = DataControllerSMSModule.getSmsRequestModelObjects(getSMSRequestModels);

        return reqData;
    }


    @Test(dataProvider = "getSmsRequestModels")
    public void secondTest(String telNumber, String personId, String consent) throws SQLException {

        List<GetSMSRequestModel> getSMSRequestModels = DataControllerSMSModule.getSMSRequestModels(DataControllerSMSModule.queryGetNum);


        for (int i = 0; i <getSMSRequestModels.size() ; i++) {
            Response response =
                    given().
                            header("Conten-Type", "application/json")
                            .contentType(ContentType.JSON)
                            .accept(ContentType.JSON)
                            .when()
                            .get("http://10.195.105.66:7000/api/Consent?TelNumber=" + telNumber);
            GetSMSResponseModel getSMSResponseModel = response.as(GetSMSResponseModel.class);
            Assert.assertEquals(consent, getSMSResponseModel.getData().getConsentStatusId());


        }
    }
}
