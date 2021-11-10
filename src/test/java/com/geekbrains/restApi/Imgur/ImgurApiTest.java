package com.geekbrains.restApi.Imgur;

import io.restassured.RestAssured;
import org.junit.jupiter.api.*;

import java.io.File;

import static io.restassured.RestAssured.given;
import static org.hamcrest.core.Is.is;

@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class ImgurApiTest {

    @BeforeAll
    static void beforeAll() {
        RestAssured.baseURI = ImgurApiParams.API_URL + "/" + ImgurApiParams.API_VERSION;

    }

    @DisplayName("Тест на получение базовой информации об аккаунте")
    @Test
    @Order(1)
    void testAccountBase() {
        String url = "account/" + "tjg8f7g2780";
        given().when()
                .auth()
                .oauth2(ImgurApiParams.TOKEN)
                .log()
                .all()
                .expect()
                .statusCode(is(200))
                .body("success", is(true))
                .body("status", is(200))
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
                .statusCode(is(200))
                .body("success", is(true))
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
                .statusCode(is(200))
                .body("success", is(true))
                .body("status", is(200))
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
                .formParam("title", "doggy")
                .formParam("description", "auf auf")
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
    @Order(5)
    void testUpdateImageInfoAuth() {
        String imageHash = "GB2RWQk";
        String url = "image/" + imageHash;

        given().when()
                .auth()
                .oauth2(ImgurApiParams.TOKEN)
                .log()
                .all()
                .formParam("title", "doggy")
                .formParam("description", "auf auf")
                .expect()
                .log()
                .all()
                .statusCode(is(200))
                .body("success", is(true))
                .body("data", is(true))
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
