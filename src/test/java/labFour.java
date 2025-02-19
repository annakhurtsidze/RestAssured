import DataController.DataControllerSMSModule;
import Models.SMSModel.GetConsent.GetSMSRequestModel;
import Models.SMSModel.GetConsent.GetSMSResponseModel;
import Models.SMSModel.PostConsent.PostSMSRequestModel;
import Steps.SMSModule.ConsentSteps;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import java.sql.SQLException;
import java.util.List;

public class labFour {


    ConsentSteps consentSteps = new ConsentSteps();


    @DataProvider(name = "postSmsRequestModels")
    public Object [] [] postSmsRequestModels() throws SQLException {
        List<PostSMSRequestModel> postSMSRequestModels = DataControllerSMSModule.postSMSRequestModels(DataControllerSMSModule.queryPostNum);
        Object[][] reqData = DataControllerSMSModule.postSMSRequestModelObjects(postSMSRequestModels);

        return reqData;
    }

    @Test(dataProvider = "postSmsRequestModels")
    public void postConsentTest(PostSMSRequestModel postSMSRequestModel)  {
            consentSteps.PostConsent(postSMSRequestModel);
            GetSMSRequestModel getSMSRequestModel = new GetSMSRequestModel();
            getSMSRequestModel.setTelNumber(postSMSRequestModel.getTelNumber());
        GetSMSResponseModel getSMSResponseModel = consentSteps.GetConsent(getSMSRequestModel);
        consentSteps.CompareConsent(getSMSResponseModel, postSMSRequestModel);

    }
}
