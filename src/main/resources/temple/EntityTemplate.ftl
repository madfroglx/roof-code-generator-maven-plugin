package ${entityPackage};

${imports}
import java.io.Serializable;
/**
*${tableComment}
**/
public class ${entitySimpleName} implements Serializable {
    private static final long serialVersionUID = ${serialVersionUID};
    ${fields}

    ${accessMethods}
}
