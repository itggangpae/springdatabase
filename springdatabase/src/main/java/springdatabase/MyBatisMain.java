package springdatabase;


import org.springframework.context.support.GenericXmlApplicationContext;

import springdatabase.mybatis.service.GoodService;


public class MyBatisMain {

	public static void main(String[] args) {
		GenericXmlApplicationContext context = 
				new GenericXmlApplicationContext("classpath:applicationContext.xml");
		GoodService goodService = context.getBean(GoodService.class);
		//goodService.insertGood();
		//goodService.getGood();
		
		//goodService.updateGood();
		//goodService.deleteGood();
		goodService.listGood();
		
		context.close();
		System.exit(0);
	}
}
