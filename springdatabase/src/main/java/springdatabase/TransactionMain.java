package springdatabase;
import springdatabase.dao.TransactionDao;

import org.springframework.context.support.GenericXmlApplicationContext;

public class TransactionMain {
	public static void main(String[] args) {
		GenericXmlApplicationContext context = 
				new GenericXmlApplicationContext("classpath:applicationContext.xml");
		TransactionDao tDao = context.getBean(TransactionDao.class);
		tDao.insert();
		context.close();
	}
}
