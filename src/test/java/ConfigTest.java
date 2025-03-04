import DataController.DataControllerSMSModule;
import Models.SMSModel.GetConsent.GetSMSRequestModel;
import Models.SMSModel.PostConsent.PostSMSRequestModel;
import org.testng.annotations.DataProvider;

import java.sql.SQLException;
import java.util.List;

public class ConfigTest {

    @DataProvider(name = "getSmsRequestModels")
    public Object [] [] getSmsRequestModels() throws SQLException {
        List<GetSMSRequestModel> getSMSRequestModels = DataControllerSMSModule.getSMSRequestModels(DataControllerSMSModule.queryGetNum);
        Object[][] reqData = DataControllerSMSModule.getSmsRequestModelObjects(getSMSRequestModels);

        return reqData;
    }

    @DataProvider(name = "postSmsRequestModels")
    public Object [] [] postSmsRequestModels() throws SQLException {
        List<PostSMSRequestModel> postSMSRequestModels = DataControllerSMSModule.postSMSRequestModels(DataControllerSMSModule.queryPostNum);
        Object[][] reqData = DataControllerSMSModule.postSMSRequestModelObjects(postSMSRequestModels);

        return reqData;
    }
}
