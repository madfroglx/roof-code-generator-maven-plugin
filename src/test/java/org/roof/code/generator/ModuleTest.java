package org.roof.code.generator;

import org.junit.Test;

import static org.junit.Assert.*;

/**
 * @author liuxin
 * @since 2018-12-10
 */
public class ModuleTest {
    @Test
    public void testModule() {
        Module module = new Module("org.roof.db.mybatis.test.ticket.entity.Ticket");
        assertEquals("org.roof.db.mybatis.test.ticket", module.getModulePackage());
        assertEquals("org.roof.db.mybatis.test.ticket.mapper.TicketMapper", module.getMapperFullName());
        assertEquals("org.roof.db.mybatis.test.ticket.service.TicketService", module.getServiceInterfaceFullName());
        assertEquals("org.roof.db.mybatis.test.ticket.service.TicketServiceImpl", module.getServiceImplFullName());
        assertEquals("org.roof.db.mybatis.test.ticket.controller.TicketController", module.getControllerFullName());
    }

}