package ${controllerPackage};

import java.util.List;
import java.util.Map;
import com.google.common.collect.Maps;
import javax.servlet.http.HttpServletRequest;
import org.roof.db.Page;
import org.roof.db.PageUtils;
import org.roof.spring.Result;
import ${entityFullName};
import ${serviceInterfaceFullName};
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("${projectName}")
public class ${entitySimpleName}Controller {

    @Autowired
	private ${serviceInterfaceSimpleName} ${serviceInterfaceVariateName};

	@RequestMapping(value = "${entityVariateName}/base", method = {RequestMethod.GET})
	public Result<Map<String,Object>> base(HttpServletRequest request) {
		Map<String,Object> map = Maps.newHashMap();
		return new Result(Result.SUCCESS, map);
	}

    @RequestMapping(value = "${entityVariateName}", method = {RequestMethod.GET})
    public Result<Page> list(${entitySimpleName} ${entityVariateName}, HttpServletRequest request) {
	    Page page = PageUtils.createPage(request);
	    page = ${serviceInterfaceVariateName}.page(page, ${entityVariateName});
	    return new Result(Result.SUCCESS, page);
	}


	@RequestMapping(value = "${entityVariateName}", method = {RequestMethod.POST})
	public Result create(@RequestBody ${entitySimpleName} ${entityVariateName}) {
		${serviceInterfaceVariateName}.save(${entityVariateName});
		return new Result("保存成功!");
	}

    @RequestMapping(value = "${entityVariateName}/{id}", method = {RequestMethod.GET})
    public Result<${entitySimpleName}> load(@PathVariable Long id) {
        ${entitySimpleName} ${entityVariateName} = ${serviceInterfaceVariateName}.load(id);
        return new Result(Result.SUCCESS,${entityVariateName});
    }

	@RequestMapping(value = "${entityVariateName}/{id}", method = {RequestMethod.PUT})
	public Result update(@PathVariable Long id ,@RequestBody ${entitySimpleName} ${entityVariateName}) {
		${entityVariateName}.setId(id);
		${serviceInterfaceVariateName}.updateIgnoreNull(${entityVariateName});
		return new Result("保存成功!");
	}

	@RequestMapping(value = "${entityVariateName}/{id}", method = {RequestMethod.DELETE})
	public Result delete(@PathVariable Long id ) {
		// TODO 有些关键数据是不能物理删除的，需要改为逻辑删除
        ${serviceInterfaceVariateName}.delete(id);
		return new Result("删除成功!");
	}

}
