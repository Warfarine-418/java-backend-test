package com.geekbrains.miniMarketApiTest;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import org.junit.jupiter.api.*;
import retrofitTest.api.MiniMarketApiService;
import retrofitTest.model.Product;
import java.io.IOException;
import java.lang.reflect.Type;
import java.net.URISyntaxException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

@TestMethodOrder(MethodOrderer.OrderAnnotation.class)

public class MiniMarketApiTest {

    private static MiniMarketApiService apiService;
    private static Gson gson;
    private static Integer id = 0;

    @BeforeAll
    static void beforeAll() {
        apiService = new MiniMarketApiService();
        gson = new Gson();
    }

    @DisplayName("Тест на добавление продукта")
    @Test
    @Order(2)
    void testCreateProduct() throws IOException {
        Product product = Product.builder()
                .categoryTitle("Food")
                .price(70)
                .title("Orange")
                .build();
        id = apiService.createProduct(product);
        Product expected = apiService.getProduct(id);
        Assertions.assertEquals(id, expected.getId());
    }

    @DisplayName("Тест на получение информации о продуктах")
    @Test
    @Order(1)
    void testGetProducts() throws IOException, URISyntaxException {
        Type type = new TypeToken<ArrayList<Product>>() {
        }.getType();
        String json = getJsonResources("testGetProducts/expected.json");
        List<Product> expected = gson.fromJson(json, type);
        List<Product> actually = apiService.getProducts();
        Assertions.assertEquals(expected.size(), actually.size());
        for (int i = 0; i < expected.size(); i++) {
            assertProduct(expected.get(i), actually.get(i));
        }
    }

    String getJsonResources(String resource) throws IOException, URISyntaxException {
        byte[] bytes = Files.readAllBytes(Paths.get(getClass().getResource(resource).toURI()));
        return new String(bytes, StandardCharsets.UTF_8);
    }

    void assertProduct(Product expected, Product actually) {
        Assertions.assertEquals(expected.getId(), actually.getId());
        Assertions.assertEquals(expected.getTitle(), actually.getTitle());
        Assertions.assertEquals(expected.getPrice(), actually.getPrice());
        Assertions.assertEquals(expected.getCategoryTitle(), actually.getCategoryTitle());
    }
//    @DisplayName("Тест на обновление информации о продукте")
//    @Test
//    @Order(3)
//    void testModifyProduct() throws IOException {
//        apiService.modifyProducts(Product.builder().build());
//        //TODO
//    }

        @DisplayName("Тест на получение информации о продукте по Id")
    @Test
    @Order(4)
    void testGetProductById() throws IOException {
        Product product = apiService.getProduct(id);
        assertProduct(apiService.getProduct(id),product);
    }

    @DisplayName("Тест на получение информации о продукте по несуществующиму Id")
    @Test
    @Order(5)
    void testGetProductByIdProductNotExist() {
        Assertions.assertThrows(RuntimeException.class, () -> {
            Product product = apiService.getProduct(1000);
        });
    }

    @DisplayName("Тест на удаление продукта")
    @Test
    @Order(6)
    void testDeleteProduct() throws IOException {
        apiService.deleteProduct(id);
        Assertions.assertThrows(RuntimeException.class, () -> {
            apiService.getProduct(id);
        });
    }


    @DisplayName("Тест на удаление отстутствующего продукта")
    @Test
    @Order(7)
    void testDeleteNullProduct() throws IOException {
        apiService.deleteProduct(100);
        Assertions.assertThrows(RuntimeException.class, () -> {
            apiService.getProduct(100);
        });
    }


}
