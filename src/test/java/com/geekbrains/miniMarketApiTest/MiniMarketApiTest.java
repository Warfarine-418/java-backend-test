package com.geekbrains.miniMarketApiTest;

import org.apache.http.client.HttpResponseException;
import org.junit.jupiter.api.*;
import retrofit2.HttpException;
import retrofitTest.api.MiniMarketApiService;
import retrofitTest.model.Product;

import java.io.EOFException;
import java.io.IOException;
import java.util.List;

@TestMethodOrder(MethodOrderer.OrderAnnotation.class)

public class MiniMarketApiTest {

    private static MiniMarketApiService apiService;

    @BeforeAll
    static void beforeAll() {
        apiService = new MiniMarketApiService();
    }

    private static Long id;

    @DisplayName("Тест на добавление продукта")
    @Test
    @Order(1)
    void testCreateProduct() throws IOException {
        Product product = Product.builder()
                .categoryTitle("Food")
                .price(70)
                .title("Orange")
                .build();
        id = apiService.createProduct(product);
        Assertions.assertEquals("Orange", product.getTitle());
        Assertions.assertEquals("Food", product.getCategoryTitle());
        System.out.println(id);
    }

    @DisplayName("Тест на получение информации о продуктах")
    @Test
    @Order(2)
    void testGetProducts() throws IOException {
        List<Product> expected = apiService.getProducts();
        //TODO
    }

    @DisplayName("Тест на обновление информации о продукте")
    @Test
    @Order(3)
    void testModifyProduct() throws IOException {
        apiService.modifyProducts(Product.builder().build());
        //TODO
    }

    @DisplayName("Тест на получение информации о продукте по Id")
    @Test
    @Order(4)
    void testGetProductById() throws IOException {
        Product product = apiService.getProduct(id);
        Assertions.assertEquals(id, product.getId());
        Assertions.assertEquals("Orange", product.getTitle());
        Assertions.assertEquals("Food", product.getCategoryTitle());
    }

    @DisplayName("Тест на удаление продукта")
    @Test
    @Order(5)
    void testDeleteProduct() throws IOException {

        Assertions.assertThrows(EOFException.class, () ->{
            apiService.deleteProduct(id);
        });

        Assertions.assertThrows(RuntimeException.class, () ->{
                apiService.getProduct(id);
        });
    }


    @DisplayName("Тест на удаление отстутствующего продукта")
    @Test
    @Order(6)
    void testDeleteNullProduct() throws IOException {
        Assertions.assertThrows(EOFException.class, () ->{
            apiService.deleteProduct(100);
        });

        Assertions.assertThrows(RuntimeException.class, () ->{
            apiService.getProduct(100);
        });
    }

}
