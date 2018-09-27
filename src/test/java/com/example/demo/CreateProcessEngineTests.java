/**
 * Activiti工作流核心:ProcessEngine的3种创建方式
 */
package com.example.demo;

import org.activiti.engine.ProcessEngine;
import org.activiti.engine.ProcessEngineConfiguration;
import org.activiti.engine.ProcessEngines;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.context.web.WebAppConfiguration;

@RunWith(SpringRunner.class)
@SpringBootTest
@WebAppConfiguration
public class CreateProcessEngineTests {
	/**
     * 创建processEngine方式1：直接使用配置对象，设置数据库连接信息，以及自动建库和建表
     * 
     * @throws Exception
     */
    @Test
    public void testName() throws Exception {
        ProcessEngineConfiguration config = ProcessEngineConfiguration.createStandaloneProcessEngineConfiguration();
        config.setJdbcDriver("com.mysql.jdbc.Driver");
        config.setJdbcUrl("jdbc:mysql://localhost:3306/demo?useUnicode=true&characterEncoding=UTF-8&useSSL=false");
        config.setJdbcUsername("root");
        config.setJdbcPassword("root");
        config.setDatabaseSchemaUpdate(ProcessEngineConfiguration.DB_SCHEMA_UPDATE_TRUE);
        ProcessEngine engine = config.buildProcessEngine();
        System.out.println(engine.getName());
        System.out.println(ProcessEngine.VERSION);
    }

    /**
     * 方式2：使用配置文件进行ProcessEngine的创建
     * 
     * @throws Exception
     */
    @Test
    public void testXml() throws Exception {
        ProcessEngineConfiguration config = ProcessEngineConfiguration
                .createProcessEngineConfigurationFromResource("activiti.cfg.xml");
        ProcessEngine engine = config.buildProcessEngine();
        System.out.println(engine);
    }

    /**
     * 方式3：缺省的方式，需要保证配置文件的名称为activiti.cfg.xml
     * 
     * @throws Exception
     */
    @Test
    public void testdefault() throws Exception {
        ProcessEngine engine = ProcessEngines.getDefaultProcessEngine();
        System.out.println(engine);
    }
}
