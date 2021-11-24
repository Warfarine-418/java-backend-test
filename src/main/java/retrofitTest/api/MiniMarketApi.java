package retrofitTest.api;

import retrofit2.Call;
import retrofit2.http.*;
import retrofitTest.model.Product;

import java.util.List;

public interface MiniMarketApi {

    @GET("api/v1/products")
    Call<List<Product>> getProducts();

    @GET("api/v1/products/{id}")
    Call<Product> getProduct(@Path("id") Long id);

    @POST("api/v1/products")
    Call<Product> createProduct(@Body Product product);

    @PUT("api/v1/products")
    Call<Product> modifyProducts(@Body Product product);

    @DELETE("api/v1/products/{id}")
    Call<Void> deleteProduct(@Path("id") Long id);
}
