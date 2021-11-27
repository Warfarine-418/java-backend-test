package mybatis;

import db.dao.CategoriesMapper;
import db.dao.ProductsMapper;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

import java.io.*;

public class MyBatisDbService {

    private SqlSession session;

    public MyBatisDbService() throws IOException {
        String resource = "C:\\Users\\Warfarin\\Desktop\\java-backend-test\\src\\main\\resources\\mybatisConfig.xml";
        File file = new File(resource);
        System.out.println(file.exists());
        InputStream inputStream = Resources.getResourceAsStream(resource);
        SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(inputStream);
//        SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder()
//                .build(getClass().getResourceAsStream("src/main/resources/com/geekbrains/mybatis/mybatisConfig.xml"));
//        session = sqlSessionFactory.openSession();
    }
//    String resource = "org/mybatis/example/mybatisConfig.xml";
//    InputStream inputStream = Resources.getResourceAsStream(resource);
//    SqlSessionFactory sqlSessionFactory =
//            new SqlSessionFactoryBuilder().build(inputStream);

    public ProductsMapper getProductsMapper(){
        return session.getMapper(ProductsMapper.class);
    }
    public CategoriesMapper getCategoriesMapper(){
        return session.getMapper(CategoriesMapper.class);
    }
}
