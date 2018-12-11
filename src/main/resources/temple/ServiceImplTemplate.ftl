package ${serviceImplPackage};

import java.io.Serializable;
import java.util.List;
import org.roof.db.IDaoSupport;
import org.roof.db.Page;
import org.roof.db.PageQueryFactory;

import ${entityFullName};
import ${mapperFullName};
import ${mapperExtFullName};

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

@Service
public class ${serviceImplSimpleName} implements ${serviceInterfaceSimpleName} {

	@Autowired
	private IDaoSupport daoSupport;

	@Autowired
	private PageQueryFactory pageQueryFactory;

	@Autowired
    private ${mapperSimpleName} ${mapperVariateName};

    @Autowired
    private ${mapperExtSimpleName} ${mapperExtVariateName};

	public Serializable save(${entitySimpleName} ${entityVariateName}){
		return daoSupport.save(${entityVariateName});
	}

	public void delete(${entitySimpleName} ${entityVariateName}){
	    daoSupport.delete(${entityVariateName});
	}

	public void delete(Serializable id){
	    daoSupport.delete(id, ${entitySimpleName}.class);
	}

	public void deleteByExample(${entitySimpleName} ${entityVariateName}){
	    daoSupport.deleteByExample(${entityVariateName});
	}

	public void update(${entitySimpleName} ${entityVariateName}){
	    daoSupport.update(${entityVariateName});
	}

	public void updateIgnoreNull(${entitySimpleName} ${entityVariateName}){
	    daoSupport.updateIgnoreNull(${entityVariateName});
	}

	public void updateByExample(${entitySimpleName} ${entityVariateName}){
	    daoSupport.update(${entityVariateName});
	}

	public ${entitySimpleName} load(${entitySimpleName} ${entityVariateName}){
		return (${entitySimpleName}) daoSupport.load(${entityVariateName});
	}

	public ${entitySimpleName} load(Serializable id){
	    return daoSupport.load(id, ${entitySimpleName}.class);
	}

	public ${entitySimpleName} selectForObject(${entitySimpleName} ${entityVariateName}){
		return (${entitySimpleName}) daoSupport.selectByExample(${entityVariateName});
	}

	public List<${entitySimpleName}> selectForList(${entitySimpleName} ${entityVariateName}){
		return (List<${entitySimpleName}>) daoSupport.selectByExample(${entityVariateName});
	}

	public Page page(Page page, ${entitySimpleName} ${entityVariateName}) {
	    return pageQueryFactory.getEntityPageQuery(page).select(${entityVariateName});
	}
}
