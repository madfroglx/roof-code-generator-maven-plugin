package ${serviceInterfacePackage};

import java.util.List;
import java.io.Serializable;

import org.roof.db.Page;
import ${entityFullName};

public interface ${serviceInterfaceSimpleName} {

    /**
    * 将对象保存，返回该条记录的操作数量，保存成功之后，将主键填充到参数对象中
    */
    Serializable save(${entitySimpleName} ${entityVariateName});

    /**
    * 按对象中的主键进行删除，如果是DRDS，还需要添加拆分键
    */
    void delete(${entitySimpleName} ${entityVariateName});

    /**
    * 按对象中的主键进行删除，如果是DRDS，还需要添加拆分键
    */
    void delete(Serializable id);

    /**
    * 按对象中的非空属性作为条件，进行删除
    */
    void deleteByExample(${entitySimpleName} ${entityVariateName});

    /**
    * 按对象中的主键进行所有属性的修改，如果是DRDS，还需要添加拆分键
    */
    void update(${entitySimpleName} ${entityVariateName});

    /**
    * 按对象中的主键进行所有非空属性的修改，如果是DRDS，还需要添加拆分键
    */
    void updateIgnoreNull(${entitySimpleName} ${entityVariateName});

    /**
    * 按对象中的非空属性作为条件，进行修改
    */
    void updateByExample(${entitySimpleName} ${entityVariateName});

    /**
    * 按对象中的主键进行数据加载，如果是DRDS，还需要添加拆分键
    */
    ${entitySimpleName} load(${entitySimpleName} ${entityVariateName});

    /**
    * 按对象中的主键进行数据加载，如果是DRDS，还需要添加拆分键
    */
    ${entitySimpleName} load(Serializable id);

    /**
    * 按对象中的非空属性作为条件，进行查询实体
    */
    ${entitySimpleName} selectForObject(${entitySimpleName} ${entityVariateName});

    /**
    * 按对象中的非空属性作为条件，进行查询列表
    */
    List<${entitySimpleName}> selectForList(${entitySimpleName} ${entityVariateName});

    /**
    * 按对象中的非空属性作为条件，进行分页查询
    */
    Page page(Page page, ${entitySimpleName} ${entityVariateName});

}