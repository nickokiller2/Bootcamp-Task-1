import org.testng.annotations.Test;
import io.restassured.RestAssured;
import io.restassured.response.Response;


public class RestAssuredImpl {
    String token;

    @Test (priority = 1)
    public void testLogin() {
        RestAssured.baseURI = "https://whitesmokehouse.com/";
        String requestBody = "{ \n " +
                             "  \"email\": \"albertsimanjuntak12@gmail.com\", \n" +
                             "  \"password\": \"@dmin123\" }";

        Response response = RestAssured.given()
            .contentType("application/json")
            .header("Content-Type", "application/json")
            .body(requestBody)
            .log().all()
            .when()
            .post("/webhook/api/login");

            System.out.println("Response: " + response.asPrettyString());
            token = response.jsonPath().getString("token");
            System.out.println("Token: " + token);
    }

    @Test (dependsOnMethods = "testLogin")   
    public void testGetListAllObjects() {
        RestAssured.baseURI = "https://whitesmokehouse.com/";
        Response response = RestAssured.given()
            .header("Content-Type", "application/json")
            .header("Authorization", "Bearer " + token)
            .log().all()
            .when()
            .get("/webhook/api/objects");

        System.out.println("Response: " + response.asPrettyString());
    }

    @Test (dependsOnMethods = "testGetListAllObjects")
    public void ListOfObjectsByIds() {
        RestAssured.baseURI = "https://whitesmokehouse.com/";
        Response response = RestAssured.given()
            .header("Content-Type", "application/json")
            .header("Authorization", "Bearer " + token)
            .log().all()
            .when()
            .get("/webhook/api/objects?id=48");

        System.out.println("Response: " + response.asPrettyString());
    }
    @Test (dependsOnMethods = "ListOfObjectsByIds")
    public void SingleObject() {
        RestAssured.baseURI = "https://whitesmokehouse.com/";
        Response response = RestAssured.given()
            .header("Content-Type", "application/json")
            .header("Authorization", "Bearer " + token)
            .log().all()
            .when()
            .get("/webhook/8749129e-f5f7-4ae6-9b03-93be7252443c/api/objects/48");

        System.out.println("Response: " + response.asPrettyString());
    }

    @Test (dependsOnMethods = "SingleObject")
    public void AddObject() {
        RestAssured.baseURI = "https://whitesmokehouse.com/";
        String requestBody = "{ \n" +
                    "  \"name\": \"Apple MacBook Pro 16 Nikko\", \n" +
                    "  \"data\": { \n" +
                    "    \"year\": \"2019\", \n" +
                    "    \"price\": 1849.99, \n" +
                    "    \"cpu_model\": \"Intel Core i9\", \n" +
                    "    \"hard_disk_size\": \"1 TB\", \n" +
                    "    \"color\": \"red\", \n" +
                    "    \"capacity\": \"2 cpu\", \n" +
                    "    \"screen_size\": \"14 Inch\" \n" +
                    "  }, \n" +
                    "  \"type\": \"test\" \n" +
                    "}";

        Response response = RestAssured.given()
            .header("Content-Type", "application/json")
            .header("Authorization", "Bearer " + token)
            .body(requestBody)
            .log().all()
            .when()
            .post("/webhook/api/objects");

        System.out.println("Response: " + response.asPrettyString());
    }
    
    @Test (dependsOnMethods = "AddObject")
    public void UpdateObject() {
        RestAssured.baseURI = "https://whitesmokehouse.com/";
        String requestBody = "{ \n" +
                    "  \"name\": \"Apple MacBook Pro 16 Nikko2\", \n" +
                    "  \"data\": { \n" +
                    "    \"year\": \"2019\", \n" +
                    "    \"price\": 1849.99, \n" +
                    "    \"cpu_model\": \"Intel Core i9\", \n" +
                    "    \"hard_disk_size\": \"1 TB\", \n" +
                    "    \"color\": \"red\", \n" +
                    "    \"capacity\": \"2 cpu\", \n" +
                    "    \"screen_size\": \"14 Inch\" \n" +
                    "  }, \n" +
                    "  \"type\": \"test\" \n" +
                    "}";

        Response response = RestAssured.given()
            .header("Content-Type", "application/json")
            .header("Authorization", "Bearer " + token)
            .body(requestBody)
            .log().all()
            .when()
            .put("/webhook/37777abe-a5ef-4570-a383-c99b5f5f7906/api/objects/92");

        System.out.println("Response: " + response.asPrettyString());
    }

    @Test (dependsOnMethods = "UpdateObject")
    public void DeleteObject() {
        RestAssured.baseURI = "https://whitesmokehouse.com/";
        Response response = RestAssured.given()
            .header("Content-Type", "application/json")
            .header("Authorization", "Bearer " + token)
            .log().all()
            .when()
            .delete("/webhook/d79a30ed-1066-48b6-83f5-556120afc46f/api/objects/92");

        System.out.println("Response: " + response.asPrettyString());
    }

    @Test (dependsOnMethods = "DeleteObject")
    public void GetAllDepartement () {
        RestAssured.baseURI = "https://whitesmokehouse.com/";
        Response response = RestAssured.given()
            .header("Content-Type", "application/json")
            .header("Authorization", "Bearer " + token)
            .log().all()
            .when()
            .get("/webhook/api/departments");

        System.out.println("Response: " + response.asPrettyString());
    }

    @Test (dependsOnMethods = "GetAllDepartement")
    public void PartiallyUpdateObject () {
        RestAssured.baseURI = "https://whitesmokehouse.com/";
        String requestBody = "{ \n" +
                    "  \"name\": \"Apple MacBook Pro 16 Nikko4\" \n" +
                    "}";

        Response response = RestAssured.given()
            .header("Content-Type", "application/json")
            .header("Authorization", "Bearer " + token)
            .body(requestBody)
            .log().all()
            .when()
            .patch("/webhook/39a0f904-b0f2-4428-80a3-391cea5d7d04/api/object/48");

        System.out.println("Response: " + response.asPrettyString());
    }

}
