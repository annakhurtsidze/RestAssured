import io.restassured.RestAssured;
import io.restassured.response.Response;
import org.testng.Assert;
import org.testng.annotations.Test;
import java.util.LinkedHashMap;
import java.util.List;

import static io.restassured.RestAssured.authentication;
import static io.restassured.RestAssured.given;

public class firstLab {


    @Test
    public void getListOfUsers(){
        Response response = RestAssured.get("https://reqres.in/api/users?page=2");


        //1. სტატუს კოდი
        System.out.println("სტატუს კოდი არის: " +response.getStatusCode() );

        //2. data მასივის სიგრძე
        System.out.println("data მასივის სიგრძეა " +response.jsonPath().getList("data").size());

        //3.data მასივი ყველა email ველი
//        System.out.println(response.jsonPath().get("data"));
        List<LinkedHashMap<String, String>>   dataArr = response.jsonPath().getList("data");
        for(int i=0; i<dataArr.size(); i++){
            System.out.println(dataArr.get(i).get("email"));
        }


        //4.შეამოწმეთ email ველი შეიცავს თუარა ‘’@’’ სიმბოლოს
        List<LinkedHashMap<String, String>> dataList = response.jsonPath().getList("data");
        for(int i = 0; i< dataList.size(); i++){
            Assert.assertTrue(dataList.get(i).get("email").contains("@"));
        }

        //5.  შეამოწმეთ response header  არის თუარა ‘'Application-Json’
        String responseHeader = response.getHeader("Content-type");
        Assert.assertTrue(responseHeader.contains("application/json"));
    }

}
