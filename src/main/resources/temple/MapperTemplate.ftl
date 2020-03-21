package ${mapperPackage};

import org.apache.ibatis.annotations.Param;
import org.mybatis.spring.annotation.MapperScan;
import ${entityFullName};
import java.util.List;

@MapperScan
public interface ${entitySimpleName}Mapper {
    /**
    * 保存实体类返回对象id
    */
    long save (${entitySimpleName} ${entityVariateName});

    /**
    * 删除对象返回删除数量
    */
    long delete(@Param("id")long id);

    /**
    * 通过匹配条件删除对象,返回删除数量
    */
    long deleteByExample(${entitySimpleName} ${entityVariateName});

    /**
    * 根据对象id更新对象,空值会被设置为空,返回更新数量
    */
    long update(${entitySimpleName} ${entityVariateName});

    /**
    * 根据对象id更新对象,空值将会被忽略,返回更新数量
    */
    long updateIgnoreNull(${entitySimpleName} ${entityVariateName});

    /**
    * 根据对象id加载对象
    */
    ${entitySimpleName} load(@Param("id")long id);

    /**
    * 通过匹配条件查询对象
    */
    List<${entitySimpleName}> select(${entitySimpleName} ${entityVariateName});

    /**
    * 根据匹配条件查询对象条数
    */
    int selectCount(${entitySimpleName} ${entityVariateName});

    /**
    * 带分页参数查询
    */
    List<${entitySimpleName}> selectForPage(Object ${entityVariateName});
}
