package Steps.SMSModule;

import Models.SMSModel.GetConsent.GetSMSRequestModel;
import Models.SMSModel.PostConsent.PostSMSRequestModel;
import Utils.APIRequestSpec;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

import static io.restassured.RestAssured.given;

public class ConsentCalls {

    RequestSpecification requestSpecification = APIRequestSpec.getRequestSpec();

    public Response GetConsent(GetSMSRequestModel getSMSRequestModel){
      Response response = given()
                .when()
                .spec(requestSpecification)
                .contentType(ContentType.JSON)
                .accept(ContentType.JSON)
                .when()
                .get("/api/Consent?TelNumber=" + getSMSRequestModel.getTelNumber());

      response.then().spec(APIRequestSpec.getResponseSpec());
      return response;
    }

    public Response PostConsent(PostSMSRequestModel postSMSRequestModel){
        return given()
                .when()
                .spec(requestSpecification)
                .contentType(ContentType.JSON)
                .accept(ContentType.JSON)
                .when()
                .body(postSMSRequestModel)
                .post("/api/Consent" );

    }
}
