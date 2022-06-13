package springdatabase.dao;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.simple.SimpleJdbcInsert;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

//@Repository
public class TransactionDao {
	// 맵을 이용해서 데이터를 추가하는 클래스의 변수
	//@Autowired
	private SimpleJdbcInsert template;
	
	//@Transactional
	public void insert() {
			template.withTableName("goods");
			Map<String, Object> map = new HashMap<String, Object>();
			map.put("code", 100);
			map.put("name", "쌀");
			map.put("manufacture", "이천");
			map.put("price", 50000);

			template.execute(map);
			template.execute(map);
	}

}
