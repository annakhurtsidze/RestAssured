package DataController;
import DataBaseAccessSQL.DataBaseAccessSQL;
import Models.SMSModel.GetSMSRequestModel;
import Models.SMSModel.GetSMSResponseModel;
import io.restassured.response.Response;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;


public class DataControllerSMSModule {

    //ამ სტრინგში ვინახავთ ბაზის ქუერის, რომლითაც ვფილტრავთ მონაცემებს ბაზაში(ამ სემთხვევაში ყველა ტიპის
    // ტელეფონის ნომერი და შესაბამისი პერსონაიდი
    public static String queryGetNum = """
            use SMSModuleDB
            
            IF OBJECT_ID('tempdb..#temp') IS NOT NULL
                DROP TABLE #temp
                
            select a.lengthOfTelNumber, max(a.personid) as personid
            into #temp
            from(
            select distinct len(a.telnumber) as lengthOfTelNumber,
            a.personid
            from dbo.AdSMSConsent as a
            where a.PersonId IS NOT NULL) as a
            group by a.lengthOfTelNumber
            
            select a.*, b.TelNumber, b.Consent
            from #temp as a
            inner join  dbo.AdSMSConsent as b on a.personid = b.PersonId and a.lengthOfTelNumber = len(b.TelNumber)
            order by 1 asc
            """;

    //ანუ ქვემოთ მოცემული მეთოდით გვინდა ბაზიდან ამოღებული ინფორმაციით შეავდგინოთ ლისტი
    public static List<GetSMSRequestModel> getSMSRequestModels(String query) throws SQLException {
        //ბაზიდან დაბურნებულ მნიშვნელობებს ვინახავთ ამ ლისტში
        List<GetSMSRequestModel>  getSMSRequestModels = new ArrayList<>();
        Connection dataBaseAccessSQL = DataBaseAccessSQL.getConnectionSMS();
        //resultset არის ბაზიდან დაბრუნებული ცხრილის ტიპის ობიექტი
        ResultSet resultSet;
        PreparedStatement preparedStatement = dataBaseAccessSQL.prepareStatement(query);
        resultSet = preparedStatement.executeQuery();
        while (resultSet.next()){
            GetSMSRequestModel getSMSRequestModel = new GetSMSRequestModel();
            getSMSRequestModel.setPersonId(resultSet.getString("personId"));
            getSMSRequestModel.setTelNumber(resultSet.getString("TelNumber"));
            getSMSRequestModel.setConsent(resultSet.getString("Consent"));
            getSMSRequestModels.add(getSMSRequestModel);

        }



        return getSMSRequestModels;
    }

}
