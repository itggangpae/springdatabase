package springdatabase.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.util.Map;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import springdatabase.domain.Contact;


//@Repository
public class JdbcTemplateDao {

	//@Autowired
	private JdbcTemplate jdbcTemplate;

	// 테이블의 데이터 개수를 리턴하는 메서드
	public Map<String, Object> count() {
		return jdbcTemplate.queryForMap("select count(*) from contact");
	}

	// 테이블에서 전체 데이터를 읽어서 List로 리턴하는 메서드
	public List<Contact> select() {
		return jdbcTemplate.query("select * from contact order by id", new RowMapper<Contact>() {
			
			public Contact mapRow(ResultSet rs, int rownum) throws SQLException {
				Contact person = new Contact();
				person.setId(rs.getInt("id"));
				person.setName(rs.getString("name"));
				person.setPhoneNumber(rs.getString("phonenumber"));
				person.setBirthday(rs.getDate("birthday"));
				return person;
			}
		});
	}

	// name을 받아서 name과 일치하는 데이터를 검색해서 1개의 데이터를 리턴하는 메서드
	public Contact selectOne(String name) {
		return jdbcTemplate.queryForObject("select * from contact where name=?", new Object[] { name }, new RowMapper<Contact>() {
			
			public Contact mapRow(ResultSet rs, int rownum) throws SQLException {
				Contact person = new Contact();
				person.setId(rs.getInt("id"));
				person.setName(rs.getString("name"));
				person.setPhoneNumber(rs.getString("phonenumber"));
				person.setBirthday(rs.getDate("birthday"));
				return person;
			}
		});
	}
	
	public int insert(Contact contact) {
		return jdbcTemplate.update("insert into contact values(idseq.nextval, ?,?,?)",
			new Object[] {contact.getName(), contact.getPhoneNumber(), contact.getBirthday() 
		});
	}
}
