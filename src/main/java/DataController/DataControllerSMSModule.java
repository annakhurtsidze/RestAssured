package DataController;
import DataBaseAccessSQL.DataBaseAccessSQL;
import Models.SMSModel.GetConsent.GetSMSRequestModel;
import Models.SMSModel.PostConsent.PostSMSRequestModel;

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


    public static String queryPostNum = """
            use SMSModuleDB
            
                              IF OBJECT_ID('tempdb..#tmp') IS NOT NULL
                                  DROP TABLE #tmp
                              select *, ROW_NUMBER() over (order by a.personId asc) as rowNums
                              into #tmp
                              from(
                              select top 3
                               a.PersonId,
                               cast(a.TelNumber as varchar) as TelNumber,
                              iif(a.Consent = 1, 3, 1) as Consent,
                              a.Channel
                              from [SMSModuleDB].[dbo].[AdSMSConsent] as a
                              where a.PersonId is not null
                              and a.TelNumber is not null
                              and a.Channel is not null
                              union
                              select top 1  a.PersonId, a.Contact, '3' as Consent, '106102' as Channel  from CredoBnk.Person.Contact as a
                              left join [SMSModuleDB].[dbo].[AdSMSConsent] as b on a.PersonId = b.PersonId
                              left join [SMSModuleDB].[dbo].[AdSMSConsent] as c on a.Contact = c.TelNumber
                              where b.PersonId is  null
                              and c.PersonId is null) as a
            
                              update a set a.personid = null from #tmp as a where rowNums = 1
                              update a set a.TelNumber = null from #tmp as a where rowNums = 2
                              update a set a.Channel = null from #tmp as a where rowNums = 3
            
                              select * from #tmp
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


    public static Object [] [] getSmsRequestModelObjects(List<GetSMSRequestModel> getSMSRequestModels) throws SQLException {
        Object[][] reqData = new Object[getSMSRequestModels.size()][3];
        for (int i = 0; i < getSMSRequestModels.size(); i++) {
            reqData[i][0]= getSMSRequestModels.get(i).getTelNumber();
            reqData[i][1]= getSMSRequestModels.get(i).getPersonId();
            reqData[i][2]= getSMSRequestModels.get(i).getConsent();
        }
        return reqData;
    }

    public static List<PostSMSRequestModel> postSMSRequestModels(String query) throws SQLException {
        //ბაზიდან დაბურნებულ მნიშვნელობებს ვინახავთ ამ ლისტში
        List<PostSMSRequestModel>  postSMSRequestModels = new ArrayList<>();
        Connection dataBaseAccessSQL = DataBaseAccessSQL.getConnectionSMS();
        //resultset არის ბაზიდან დაბრუნებული ცხრილის ტიპის ობიექტი
        ResultSet resultSet;
        PreparedStatement preparedStatement = dataBaseAccessSQL.prepareStatement(query);
        resultSet = preparedStatement.executeQuery();
        while (resultSet.next()){
            PostSMSRequestModel postSMSRequestModel = new PostSMSRequestModel();
            postSMSRequestModel.setPersonId(resultSet.getString("personId"));
            postSMSRequestModel.setTelNumber(resultSet.getString("TelNumber"));
            postSMSRequestModel.setStatus(resultSet.getString("Consent"));
            postSMSRequestModel.setChannelId(resultSet.getString("Channel"));
            postSMSRequestModels.add(postSMSRequestModel);

        }

        return postSMSRequestModels;
    }


    public static Object [] [] postSMSRequestModelObjects(List<PostSMSRequestModel> postSMSRequestModels) throws SQLException {
        Object[][] reqData = new Object[postSMSRequestModels.size()][1];
        for (int i = 0; i < postSMSRequestModels.size(); i++) {
            reqData[i][0]= postSMSRequestModels.get(i);
        }
        return reqData;
    }

}
