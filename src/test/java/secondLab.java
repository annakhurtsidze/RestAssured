import DataController.DataControllerSMSModule;
import Models.SMSModel.GetConsent.GetSMSRequestModel;
import Models.SMSModel.GetConsent.GetSMSResponseModel;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.sql.SQLException;
import java.util.List;

import static io.restassured.RestAssured.given;

public class secondLab {


    @Test
    public void secondTest() throws SQLException {

        List<GetSMSRequestModel> getSMSRequestModels = DataControllerSMSModule.getSMSRequestModels(DataControllerSMSModule.queryGetNum);


        for (int i = 0; i <getSMSRequestModels.size() ; i++) {
            Response response =
                given().
                        header("Conten-Type", "application/json")
                        .contentType(ContentType.JSON)
                        .accept(ContentType.JSON)
                        .when()
                        .get("http://10.195.105.66:7000/api/Consent?TelNumber=" + getSMSRequestModels.get(i).getTelNumber());
            GetSMSResponseModel getSMSResponseModel = response.as(GetSMSResponseModel.class);
            Assert.assertEquals(getSMSRequestModels.get(i).getConsent(), getSMSResponseModel.getData().getConsentStatusId());


        }
    }
}
