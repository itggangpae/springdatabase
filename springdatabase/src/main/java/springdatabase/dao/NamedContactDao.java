package springdatabase.dao;

import java.sql.*;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.*;
import org.springframework.jdbc.core.namedparam.*;
import org.springframework.stereotype.Repository;

import springdatabase.domain.Contact;

//@Repository
public class NamedContactDao {
	
	//@Autowired
	private NamedParameterJdbcTemplate jdbcTemplate;
	
	public Contact selectOne(String name) {
		//Map<String, Object> map = new HashMap<String, Object>();
		//map.put("name", name);
		
		MapSqlParameterSource map = new MapSqlParameterSource();
		map.addValue("name", name);

		return jdbcTemplate.queryForObject("select * from contact where name=:name", map, new RowMapper<Contact>() {
		
			public Contact mapRow(ResultSet rs, int rownum) throws SQLException {
				Contact contact = new Contact();
				contact.setId(rs.getInt("id"));
				contact.setName(rs.getString("name"));
				contact.setPhoneNumber(rs.getString("phonenumber"));
				contact.setBirthday(rs.getDate("birthday"));
				return contact;
			}
		});
	}
}