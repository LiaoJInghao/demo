package com.example.demo.activiti.config;

import org.activiti.engine.ProcessEngine;
import org.activiti.engine.ProcessEngineConfiguration;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.core.env.Environment;

import org.activiti.spring.SpringProcessEngineConfiguration;
import org.activiti.engine.impl.cfg.StandaloneProcessEngineConfiguration;
import org.activiti.engine.impl.cfg.StandaloneInMemProcessEngineConfiguration;

@Configuration
public class ActivitiCfg {
	
	@Autowired
    private Environment environment;

	@Bean
	public ProcessEngineConfiguration processEngineConfiguration() {
	    ProcessEngineConfiguration cfg = new SpringProcessEngineConfiguration()
	      .setJdbcUrl(environment.getProperty("spring.datasource.url"))
	      .setJdbcUsername(environment.getProperty("spring.datasource.username"))
	      .setJdbcPassword(environment.getProperty("spring.datasource.password"))
	      .setJdbcDriver(environment.getProperty("spring.datasource.driverClassName"))
	      .setDatabaseSchemaUpdate(ProcessEngineConfiguration.DB_SCHEMA_UPDATE_TRUE);
//	    ProcessEngine processEngine = cfg.buildProcessEngine();
//	    String pName = processEngine.getName();
//	    String ver = ProcessEngine.VERSION;
//	    System.out.println("==================>ProcessEngine [" + pName + "] Version: [" + ver + "]");
	    return cfg;
	}
}
