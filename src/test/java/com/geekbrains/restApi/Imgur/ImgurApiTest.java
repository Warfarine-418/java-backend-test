package com.geekbrains.restApi.Imgur;

import io.restassured.RestAssured;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.builder.ResponseSpecBuilder;
import io.restassured.specification.RequestSpecification;
import io.restassured.specification.ResponseSpecification;
import org.junit.jupiter.api.*;

import java.io.File;

import static io.restassured.RestAssured.*;
import static org.hamcrest.core.Is.is;

@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class ImgurApiTest {
    ResponseSpecification responseSpecification = null;
    RequestSpecification requestSpecification = null;

    @BeforeAll
    static void beforeAll() {
        RestAssured.baseURI = ImgurApiParams.API_URL + "/" + ImgurApiParams.API_VERSION;
    }

    @BeforeEach
    void beforeTest() {
        requestSpecification = new RequestSpecBuilder()
                .addFormParam("title", "doggy")
                .addFormParam("description", "auf auf")
                .build();

        responseSpecification = new ResponseSpecBuilder()
                .expectBody("success", is(true))
                .expectBody("status", is(200))
                .expectStatusCode(200)
                .build();
    }

    @DisplayName("Тест на получение базовой информации об аккаунте")
    @Test
    @Order(1)
    void testAccountBase() {

        ResponseSpecification responseSpecification = new ResponseSpecBuilder()
                .expectBody("success", is(true))
                .expectBody("status", is(200))
                .expectStatusCode(200)
                .build();

        String url = "account/" + "tjg8f7g2780";
        given().when()
                .auth()
                .oauth2(ImgurApiParams.TOKEN)
                .log()
                .all()
                .expect()
                .spec(responseSpecification)
                .log()
                .all()
                .when()
                .get(url);
    }

    @DisplayName("Тест добавление картинки ")
    @Test
    @Order(2)
    void testUploadImage() {
        String url = "upload";
        given().when()
                .auth()
                .oauth2(ImgurApiParams.TOKEN)
                .log()
                .all()
                .multiPart("image", new File("src/test/resources/bjBI56.jpg"))
                .expect()
                .log()
                .all()
                .spec(responseSpecification)
                .body("data.account_url", is("tjg8f7g2780"))
                .when()
                .post(url);
    }

    @DisplayName("Тест получение информации о картинке")
    @Test
    @Order(3)
    void testGetImageInfo() {
        String url = "image/" + "GB2RWQk";
        given().when()
                .auth()
                .oauth2(ImgurApiParams.TOKEN)
                .log()
                .all()
                .expect()
                .spec(responseSpecification)
                .log()
                .all()
                .when()
                .get(url);
    }

    @DisplayName("Тест обновление информации о картинке без авторизации")
    @Test
    @Order(4)
    void testUpdateImageInfoUnAuth() {
        String imageHash = "GB2RWQk";
        String url = "image/" + imageHash;

        given().when()
                .header("Authorization", ImgurApiParams.CLIENT_ID)
                .log()
                .all()
                .spec(requestSpecification)
                .expect()
                .log()
                .all()
                .statusCode(is(403))
                .body("success", is(false))
                .body("data.error", is("Permission denied"))
                .when()
                .post(url);
    }

    @DisplayName("Тест обновления информации о картинке с авторизацией")
    @Test
    @Disabled
    @Order(5)
    void testUpdateImageInfoAuth() {
        String imageHash = "GB2RWQk";
        String url = "image/" + imageHash;

        given().when()
                .auth()
                .oauth2(ImgurApiParams.TOKEN)
                .log()
                .all()
                .spec(requestSpecification)
                .expect()
                .log()
                .all()
                .spec(responseSpecification)
                .when()
                .post(url);
    }

    @DisplayName("Тест удаление картинки из избранного")
    @Test
    @Order(6)
    void testUnFavoriteImage() {
        String imageHash = "GB2RWQk";
        String url = "image/" + imageHash + "/favorite";

        given().when()
                .auth()
                .oauth2(ImgurApiParams.TOKEN)
                .log()
                .all()
                .expect()
                .log()
                .all()
                .statusCode(is(200))
                .body("success", is(true))
                .body("data", is("unfavorited"))
                .when()
                .post(url);
    }

    @DisplayName("Тест добавление картинки в избранное")
    @Test
    @Order(7)
    void testFavoriteImage() {
        String imageHash = "GB2RWQk";
        String url = "image/" + imageHash + "/favorite";

        given().when()
                .auth()
                .oauth2(ImgurApiParams.TOKEN)
                .log()
                .all()
                .expect()
                .log()
                .all()
                .statusCode(is(200))
                .body("success", is(true))
                .body("data", is("favorited"))
                .when()
                .post(url);
    }

    @DisplayName("Тест удаление картинки без авторизации")
    @Test
    @Order(8)
    void testDeletionImageUnAuth() {
        String imageDeleteHash = "pWgjaf7wg4Bq7E6";
        String url = "image/" + imageDeleteHash;

        given().when()
                .header("Authorization", ImgurApiParams.CLIENT_ID)
                .log()
                .all()
                .expect()
                .log()
                .all()
                .statusCode(is(200))
                .body("success", is(true))
                .body("data", is(true))
                .when()
                .post(url);
    }

    @DisplayName("Тест удаление картинки с авторизацией")
    @Test
    @Order(9)
    void testDeletionImageAuth() {
        String imageDeleteHash = "pWgjaf7wg4Bq7E6";
        String url = "image/" + imageDeleteHash;

        given().when()
                .auth()
                .oauth2(ImgurApiParams.TOKEN)
                .log()
                .all()
                .expect()
                .log()
                .all()
                .statusCode(is(200))
                .body("success", is(true))
                .body("data", is(true))
                .when()
                .post(url);
    }
}
