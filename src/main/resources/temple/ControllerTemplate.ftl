package ${controllerPackage};

import java.util.List;
import java.util.Map;
import com.google.common.collect.Maps;
import javax.servlet.http.HttpServletRequest;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
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

@Api(value = "${entitySimpleName}Controller", description = "${entitySimpleName}管理")
@RestController
@RequestMapping("${projectName}")
public class ${entitySimpleName}Controller {

    @Autowired
	private ${serviceInterfaceSimpleName} ${serviceInterfaceVariateName};

	@ApiOperation(value = "获得${entitySimpleName}基础信息")
	@RequestMapping(value = "${entityVariateName}/base", method = {RequestMethod.GET})
	public Result<Map<String,Object>> base(HttpServletRequest request) {
		Map<String,Object> map = Maps.newHashMap();
		return new Result(Result.SUCCESS, map);
	}

	@ApiOperation(value = "获得${entitySimpleName}分页列表")
    @RequestMapping(value = "${entityVariateName}", method = {RequestMethod.GET})
    public Result<Page> list(${entitySimpleName} ${entityVariateName}, HttpServletRequest request) {
	    Page page = PageUtils.createPage(request);
	    page = ${serviceInterfaceVariateName}.page(page, ${entityVariateName});
	    return new Result(Result.SUCCESS, page);
	}


    @ApiOperation(value = "新增${entitySimpleName}")
	@RequestMapping(value = "${entityVariateName}", method = {RequestMethod.POST})
	public Result create(@RequestBody ${entitySimpleName} ${entityVariateName}) {
		if (${entityVariateName} != null) {
            ${serviceInterfaceVariateName}.save(${entityVariateName});
			return new Result("保存成功!");
		} else {
			return new Result(Result.FAIL,"数据传输失败!");
		}
	}

    @ApiOperation(value = "根据ID加载${entitySimpleName}")
    @RequestMapping(value = "${entityVariateName}/{id}", method = {RequestMethod.GET})
    public Result<${entitySimpleName}> load(@PathVariable Long id) {
        ${entitySimpleName} ${entityVariateName} = ${serviceInterfaceVariateName}.load(id);
        return new Result(Result.SUCCESS,${entityVariateName});
    }

	@ApiOperation(value = "根据ID更新${entitySimpleName}")
	@RequestMapping(value = "${entityVariateName}/{id}", method = {RequestMethod.PUT})
	public Result update(@PathVariable Long id ,@RequestBody ${entitySimpleName} ${entityVariateName}) {
		if (id != null && ${entityVariateName} != null) {
            ${entityVariateName}.setId(id);
            ${serviceInterfaceVariateName}.updateIgnoreNull(${entityVariateName});
			return new Result("保存成功!");
		} else {
			return new Result(Result.FAIL,"数据传输失败!");
		}
	}

    @ApiOperation(value = "根据ID删除${entitySimpleName}")
	@RequestMapping(value = "${entityVariateName}/{id}", method = {RequestMethod.DELETE})
	public Result delete(@PathVariable Long id ) {
		// TODO 有些关键数据是不能物理删除的，需要改为逻辑删除
        ${serviceInterfaceVariateName}.delete(id);
		return new Result("删除成功!");
	}

}
