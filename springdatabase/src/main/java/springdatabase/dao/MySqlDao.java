package springdatabase.dao;

import java.sql.Date;
import java.util.GregorianCalendar;
import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.jdbc.core.simple.SimpleJdbcCall;
import org.springframework.jdbc.core.simple.SimpleJdbcInsert;

//@Repository
public class MySqlDao {
	//@Autowired
	private SimpleJdbcInsert simpleJdbcInsert;
	
	//@Autowired
	private SimpleJdbcCall simpleJdbcCall;

	public void method(){
		simpleJdbcInsert.withTableName("contact");
		simpleJdbcCall.withProcedureName("myproc");
		
		Map<String, Object>map = new HashMap<String, Object>();
		map.put("vname", "윤봉길");
		map.put("vphonenumber", "01031391997");
		GregorianCalendar cal = new GregorianCalendar();
		map.put("vbirthday", new Date(cal.getTimeInMillis()));
		simpleJdbcCall.execute(map);
		
		simpleJdbcInsert.usingGeneratedKeyColumns("id");
		simpleJdbcInsert.usingColumns("name", "phonenumber", "birthday");  
		map.put("name", "안중근");
		map.put("phonenumber", "01010001997");
		cal = new GregorianCalendar();
		map.put("birthday", new Date(cal.getTimeInMillis()));
		Number id = simpleJdbcInsert.executeAndReturnKey(map);
		System.out.println("추가된 인덱스:" + id);
		
	}
}