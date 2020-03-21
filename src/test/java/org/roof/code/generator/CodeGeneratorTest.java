package org.roof.code.generator;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

/**
 * @author liuxin
 * @since 2020/3/19
 */
@SpringBootTest
public class CodeGeneratorTest {
    @Autowired
    private CodeGenerator codeGenerator;

    @Test
    public void generate() {
        codeGenerator.generate();
    }
}