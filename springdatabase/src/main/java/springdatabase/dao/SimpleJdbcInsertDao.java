package springdatabase.dao;

import java.util.HashMap;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.simple.SimpleJdbcInsert;
import org.springframework.stereotype.Repository;

//@Repository
public class SimpleJdbcInsertDao {
	//@Autowired
	private SimpleJdbcInsert jdbcTemplate;

	public int insert(HashMap<String, Object> map) {
		jdbcTemplate.withTableName("contact");
		return jdbcTemplate.execute(map);
	}
}

