package cn;

import com.baomidou.mybatisplus.generator.FastAutoGenerator;
import com.baomidou.mybatisplus.generator.config.builder.Controller;
import com.baomidou.mybatisplus.generator.config.builder.Entity;
import com.baomidou.mybatisplus.generator.config.builder.Mapper;
import com.baomidou.mybatisplus.generator.config.builder.Service;
import com.baomidou.mybatisplus.generator.engine.FreemarkerTemplateEngine;

import cn.base.BaseController;
import cn.base.BaseModel;


public class CodeGenerator {

	public static void main(String[] args) {
		
	 
		
		String projectPath = System.getProperty("user.dir");
		String author = "zdview";
		String parent = "com.bz.projecname";
		String url = "jdbc:mysql://192.168.1.177:13306/db_esl?useUnicode=true&serverTimezone=GMT&useSSL=false&characterEncoding=utf8";
		String username = "roots";
		String password = "Bowtz@147";
		
		DocModel dm=DocModel.LK;// SWAG  LK
		
		FastAutoGenerator.create(url, username, password)
        .globalConfig(builder -> builder
                .author(author)
                .outputDir(projectPath + "/src/test/java")
                .commentDate("yyyy-MM-dd")
                .enableSwagger()    
        )
        .packageConfig(builder -> builder
                .parent(parent)
                .entity("entity")
                .mapper("mapper")
                .service("service")
                .serviceImpl("service.impl")
                .xml("mapper.xml")
        )
        .strategyConfig(builder -> {
			//开启跳过视图
			builder.enableSkipView();
			
			//Builders
			Entity.Builder 		entityBuilder		=	builder.entityBuilder();		//实体配置
			Mapper.Builder 		mapperBuilder		=	builder.mapperBuilder();		//Mapper属性配置
			Service.Builder 	serviceBuilder		=	builder.serviceBuilder();		//Service属性配置
			Controller.Builder 	controllerBuilder	=	builder.controllerBuilder();	//控制器配置
 
			//...
			controllerBuilder.enableRestStyle();
			
			//覆盖已有文件
			entityBuilder.enableFileOverride();
			mapperBuilder.enableFileOverride();
			serviceBuilder.enableFileOverride();
			controllerBuilder.enableFileOverride();
			
			//自定义继承的类全称
			entityBuilder.superClass(BaseModel.class);
			controllerBuilder.superClass(BaseController.class);
			
			//自定义指定模板路径
			entityBuilder.javaTemplate(dm.getEntity());
			controllerBuilder.template(dm.getController());
			
			
		})
        .templateEngine(new FreemarkerTemplateEngine())
        .execute();

		
		System.out.println("OK");
	}

	
	
	
	public enum DocModel{
		
		SWAG("java"),LK("lk");
		
		
		private String name;
		private final String entity="templates/entity.";
		private final String controller="templates/controller.";
		
		private DocModel(String name) {
			this.setName(name);
		}

		public String getName() {
			return name;
		}
		public String getValue() {
			return name;
		}
		public void setName(String name) {
			this.name = name;
		}
		public String getEntity() {
			
			return entity+name;
		}
		public String getController() {
			return controller+name;
		}
		
	}
 
	
}
