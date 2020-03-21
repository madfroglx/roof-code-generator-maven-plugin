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

	@Override
	public Serializable save(${entitySimpleName} ${entityVariateName}){
		return daoSupport.save(${entityVariateName});
	}

	@Override
	public void delete(${entitySimpleName} ${entityVariateName}){
	    daoSupport.delete(${entityVariateName});
	}

	@Override
	public void delete(Serializable id){
	    daoSupport.delete(id, ${entitySimpleName}.class);
	}

	@Override
	public void deleteByExample(${entitySimpleName} ${entityVariateName}){
	    daoSupport.deleteByExample(${entityVariateName});
	}

	@Override
	public void update(${entitySimpleName} ${entityVariateName}){
	    daoSupport.update(${entityVariateName});
	}

	@Override
	public void updateIgnoreNull(${entitySimpleName} ${entityVariateName}){
	    daoSupport.updateIgnoreNull(${entityVariateName});
	}

	@Override
	public void updateByExample(${entitySimpleName} ${entityVariateName}){
	    daoSupport.update(${entityVariateName});
	}

	@Override
	public ${entitySimpleName} load(${entitySimpleName} ${entityVariateName}){
		return (${entitySimpleName}) daoSupport.load(${entityVariateName});
	}

	@Override
	public ${entitySimpleName} load(Serializable id){
	    return daoSupport.load(id, ${entitySimpleName}.class);
	}

	@Override
	public ${entitySimpleName} selectForObject(${entitySimpleName} ${entityVariateName}){
		return (${entitySimpleName}) daoSupport.selectByExampleForObject(${entityVariateName});
	}

	@Override
	public List<${entitySimpleName}> selectForList(${entitySimpleName} ${entityVariateName}){
		return (List<${entitySimpleName}>) daoSupport.selectByExample(${entityVariateName});
	}

	@Override
	public Page page(Page page, ${entitySimpleName} ${entityVariateName}) {
	    return pageQueryFactory.getPageQuery(page, "${mapperFullName}.selectForPage", "${mapperFullName}.selectCount").select(${entityVariateName});
	}
}
