package Steps.SMSModule;

import Models.SMSModel.GetConsent.GetSMSRequestModel;
import Models.SMSModel.GetConsent.GetSMSResponseModel;
import Models.SMSModel.PostConsent.PostSMSRequestModel;
import com.fasterxml.jackson.annotation.JsonTypeInfo;
import io.restassured.response.Response;
import org.testng.Assert;

import java.sql.ResultSet;

public class ConsentSteps {



    public GetSMSResponseModel GetConsent(GetSMSRequestModel getSMSRequestModel){
        GetSMSResponseModel getSMSResponseModel = new GetSMSResponseModel();
        ConsentCalls consentCalls = new ConsentCalls();
        Response response = consentCalls.GetConsent(getSMSRequestModel);
        int statusCode = response.getStatusCode();
        if(statusCode == 200){
            getSMSResponseModel = response.as(GetSMSResponseModel.class);
            Assert.assertEquals(statusCode, 200);
        }else {
            Assert.assertNotEquals(statusCode, 200);
        }

        return getSMSResponseModel;
    }

    public void PostConsent(PostSMSRequestModel postSMSRequestModel){
        ConsentCalls consentCalls = new ConsentCalls();
        Response response = consentCalls.PostConsent(postSMSRequestModel);
        int statusCode = response.getStatusCode();
        if(statusCode == 200){
            Assert.assertEquals(statusCode, 200);
        }else {
            Assert.assertNotEquals(statusCode, 200);
        }


    }

    public void CompareConsent(GetSMSResponseModel getSMSResponseModel, PostSMSRequestModel postSMSRequestModel){
        Assert.assertEquals(getSMSResponseModel.getData().getConsentStatus(), postSMSRequestModel.getStatus());
    }
}
