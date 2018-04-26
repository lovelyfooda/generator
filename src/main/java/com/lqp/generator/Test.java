/*
 * @Project Name: generator
 * @File Name: Test.java
 * @Package Name: com.lqp.generator
 * @Date: 2018年4月25日上午11:24:02
 * @Creator: longqingping-1118
 * @line------------------------------
 * @修改人:
 * @修改时间:
 * @修改内容:
 */

package com.lqp.generator;

import java.io.File;
import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.mybatis.generator.api.MyBatisGenerator;
import org.mybatis.generator.config.Configuration;
import org.mybatis.generator.config.Context;
import org.mybatis.generator.config.xml.ConfigurationParser;
import org.mybatis.generator.exception.InvalidConfigurationException;
import org.mybatis.generator.exception.XMLParserException;
import org.mybatis.generator.internal.DefaultShellCallback;

/**
 * TODO
 * @author longqingping-1118
 * @date 2018年4月25日上午11:24:02
 * @see
 */
public class Test {

	public static void main(String[] args)
			throws IOException, XMLParserException, InvalidConfigurationException, SQLException, InterruptedException {
		// 代码存放路径
		StringBuilder sb = new StringBuilder();
		sb.append(System.getProperty("user.dir"));
		sb.append("\\src\\main\\java");
		// 配置文件路径
		String configUrl = Test.class.getResource("/").getPath().replaceAll("%20", " ");
		configUrl += "generatorConfig.xml";
		//
		List<String> warnings = new ArrayList<String>();
		boolean overwrite = true;
		File configFile = new File(configUrl);
		ConfigurationParser cp = new ConfigurationParser(warnings);
		Configuration config = cp.parseConfiguration(configFile);
		String targetProject = sb.toString();
		for (Context ctx : config.getContexts()) {
			ctx.getJavaClientGeneratorConfiguration().setTargetProject(targetProject);
			ctx.getJavaModelGeneratorConfiguration().setTargetProject(targetProject);
			ctx.getSqlMapGeneratorConfiguration().setTargetProject(targetProject);
		}
		DefaultShellCallback callback = new DefaultShellCallback(overwrite);
		MyBatisGenerator myBatisGenerator = new MyBatisGenerator(config, callback, warnings);
		myBatisGenerator.generate(null);
	}
}
