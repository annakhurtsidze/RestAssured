package Steps.SMSModule;

import Models.SMSModel.GetConsent.GetSMSRequestModel;
import Models.SMSModel.PostConsent.PostSMSRequestModel;
import io.restassured.http.ContentType;
import io.restassured.response.Response;

import static io.restassured.RestAssured.given;

public class ConsentCalls {
    public Response GetConsent(GetSMSRequestModel getSMSRequestModel){
        return given().
                header("Conten-Type", "application/json")
                .contentType(ContentType.JSON)
                .accept(ContentType.JSON)
                .when()
                .get("http://10.195.105.66:7000/api/Consent?TelNumber=" + getSMSRequestModel.getTelNumber());

    }

    public Response PostConsent(PostSMSRequestModel postSMSRequestModel){
        return given().
                header("Conten-Type", "application/json")
                .contentType(ContentType.JSON)
                .accept(ContentType.JSON)
                .when()
                .body(postSMSRequestModel)
                .post("http://10.195.105.66:7000/api/Consent" );

    }
}
