package ${entityPackage};

${imports}
<#if useLombok == true>
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
</#if>

import java.io.Serializable;
/**
*${tableComment}
**/
<#if useLombok == true>
@Data
@NoArgsConstructor
@AllArgsConstructor
</#if>
public class ${entitySimpleName} implements Serializable {
    private static final long serialVersionUID = ${serialVersionUID};
    ${fields}
    <#if useLombok == false>
    ${accessMethods!}
    </#if>
}
