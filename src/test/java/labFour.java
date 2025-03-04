
import Models.SMSModel.GetConsent.GetSMSRequestModel;
import Models.SMSModel.PostConsent.PostSMSRequestModel;
import Steps.SMSModule.ConsentSteps;

import org.testng.annotations.Test;


public class labFour extends ConfigTest{


    ConsentSteps consentSteps = new ConsentSteps();



    @Test(dataProvider = "postSmsRequestModels")
    public void postConsentTest(PostSMSRequestModel postSMSRequestModel)  {
        consentSteps.PostConsent(postSMSRequestModel);

    }


    @Test(dataProvider = "getSmsRequestModels")
    public  void  getConsentTest(GetSMSRequestModel getSMSRequestModel){
        consentSteps.GetConsent(getSMSRequestModel);
    }
}
