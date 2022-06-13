package springdatabase.mybatis.dao;

import springdatabase.mybatis.domain.Good;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

//@Repository
public class GoodDao {
	//@Autowired
	private SqlSession sqlSession;

	public void setSqlSession(SqlSession sqlSession) {
		this.sqlSession = sqlSession;
	}
	
	public int insertGood(Good good){
		return sqlSession.insert("insertGood", good);
	}
	
	/*
	public List<Good> listGood(){
		return sqlSession.selectList("good.listGood");
	}
	
	public Good getGood(int code){
		return sqlSession.selectOne("good.getGood", code);
	}
	*/
	
	public List<Good> listGood(){
		Map<String,Object> map = new HashMap<String,Object>();
		sqlSession.selectList("good.listGood", map);
		@SuppressWarnings("unchecked")
		List<Good> list = (List<Good>)map.get("vcursor");
		return list;
	}

	public Good getGood(int code){
		Map<String,Object> map = new HashMap<String,Object>();
		map.put("vcode", code);
		sqlSession.selectList("good.getGood", map);
		@SuppressWarnings("unchecked")
		List<Good> list = (List<Good>)map.get("vcursor");
		return list.get(0);
	}	


	public int updateGood(Good good) {
		return sqlSession.update("updateGood", good);
	}

	public int deleteGood(int code){
		return sqlSession.delete("good.deleteGood", code);
	}

}