package mybatis;

import db.dao.ProductsMapper;
import db.model.Products;

import java.io.IOException;

public class Test {
    public static void main(String[] args) throws IOException {

        MyBatisDbService dbService = new MyBatisDbService();
        ProductsMapper mapper= dbService.getProductsMapper();

        Products products = mapper.selectByPrimaryKey(2);
        System.out.println(products);
    }
}
