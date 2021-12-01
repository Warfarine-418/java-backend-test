package db.dao;

import db.model.Categories;
import db.model.CategoriesExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface CategoriesMapper {
    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table CATEGORIES
     *
     * @mbg.generated Wed Dec 01 19:37:50 MSK 2021
     */
    long countByExample(CategoriesExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table CATEGORIES
     *
     * @mbg.generated Wed Dec 01 19:37:50 MSK 2021
     */
    int deleteByExample(CategoriesExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table CATEGORIES
     *
     * @mbg.generated Wed Dec 01 19:37:50 MSK 2021
     */
    int deleteByPrimaryKey(Long id);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table CATEGORIES
     *
     * @mbg.generated Wed Dec 01 19:37:50 MSK 2021
     */
    int insert(Categories record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table CATEGORIES
     *
     * @mbg.generated Wed Dec 01 19:37:50 MSK 2021
     */
    int insertSelective(Categories record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table CATEGORIES
     *
     * @mbg.generated Wed Dec 01 19:37:50 MSK 2021
     */
    List<Categories> selectByExample(CategoriesExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table CATEGORIES
     *
     * @mbg.generated Wed Dec 01 19:37:50 MSK 2021
     */
    Categories selectByPrimaryKey(Long id);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table CATEGORIES
     *
     * @mbg.generated Wed Dec 01 19:37:50 MSK 2021
     */
    int updateByExampleSelective(@Param("record") Categories record, @Param("example") CategoriesExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table CATEGORIES
     *
     * @mbg.generated Wed Dec 01 19:37:50 MSK 2021
     */
    int updateByExample(@Param("record") Categories record, @Param("example") CategoriesExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table CATEGORIES
     *
     * @mbg.generated Wed Dec 01 19:37:50 MSK 2021
     */
    int updateByPrimaryKeySelective(Categories record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table CATEGORIES
     *
     * @mbg.generated Wed Dec 01 19:37:50 MSK 2021
     */
    int updateByPrimaryKey(Categories record);
}