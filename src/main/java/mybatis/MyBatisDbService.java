package mybatis;

import db.dao.CategoriesMapper;
import db.dao.ProductsMapper;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

public class MyBatisDbService {

    private SqlSession session;

    public MyBatisDbService() {
        SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder()
                .build(getClass().getResourceAsStream("mybatisConfig.xml"));
        session = sqlSessionFactory.openSession();
    }

    public ProductsMapper getProductsMapper() {
        return session.getMapper(ProductsMapper.class);
    }

    public CategoriesMapper getCategoriesMapper() {
        return session.getMapper(CategoriesMapper.class);
    }
}
