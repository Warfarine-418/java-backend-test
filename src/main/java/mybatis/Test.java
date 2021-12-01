package mybatis;

import java.util.List;

import db.dao.ProductsMapper;
import db.dao.CategoriesMapper;
import db.model.Categories;
import db.model.Products;
import db.model.ProductsExample;

public class Test {
    public static void main(String[] args) {

        MyBatisDbService dbService = new MyBatisDbService();
        ProductsMapper mapper = dbService.getProductsMapper();
        CategoriesMapper mapperCat = dbService.getCategoriesMapper();

        Products product = mapper.selectByPrimaryKey(1L);
        System.out.println(product);

        // Все продукты 1ой категории
        ProductsExample example1 = new ProductsExample();
        example1.createCriteria()
                .andCategoryIdEqualTo(1l);
        List<Products> products1 = mapper.selectByExample(example1);
        System.out.println(products1);

        // Все продукты дешевле 1000
        ProductsExample example2 = new ProductsExample();
        example2.createCriteria()
                .andPriceLessThan(1000);
        List<Products> products2 = mapper.selectByExample(example2);
        System.out.println(products2);

        // Все продукты от a до h
        ProductsExample example3 = new ProductsExample();
        example3.createCriteria()
                .andTitleBetween("A", "H");
        List<Products> products3 = mapper.selectByExample(example3);
        System.out.println(products3);

        //создание 3х продуктов
        Products product1 = new Products();
        product1.setTitle("Orange");
        product1.setPrice(50);
        product1.setCategoryId(1L);
        mapper.insert(product1);
        System.out.println(product1);

        Products product2 = new Products();
        product2.setTitle("Apple");
        product2.setPrice(45);
        product2.setCategoryId(1L);
        mapper.insert(product2);
        System.out.println(product2);

        Products product3 = new Products();
        product3.setTitle("Juice");
        product3.setPrice(250);
        product3.setCategoryId(1L);
        mapper.insert(product3);
        System.out.println(product3);

        //создание категории
        Categories categories = new Categories();
        categories.setTitle("Household goods");
        mapperCat.insert(categories);
        System.out.println(categories);


        ProductsExample all = new ProductsExample();
        all.createCriteria()
                .andIdIsNotNull();
        List<Products> allProducts = mapper.selectByExample(all);
        System.out.println(allProducts);

    }
}
