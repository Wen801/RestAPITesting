package net.bddtrader.unittests;

import io.restassured.RestAssured;
import static org.hamcrest.Matchers.*;

import org.junit.Before;
import org.junit.Test;

public class WhenGettingCompanyDetails {
    @Before
    public void prepare_rest_config(){
        RestAssured.baseURI ="https://bddtrader.herokuapp.com/api";
    }


    @Test
    public void should_return_name_and_sector(){
          RestAssured.given()
                  .pathParam("symbol", "aapl")
                  .when()
                  .get("/stock/{symbol}/company")
                .then()
                .body("companyName", equalTo("Apple, Inc."))
                .body("sector", equalTo("Electronic Technology"));
    }

    @Test
    public void should_return_news_for_a_requested_company(){
        RestAssured.given().queryParam("symbols","fb")
                .when()
                .get("/news")
                .then()
                .body("related",everyItem(containsString("FB")));
    }

}
