package springdatabase.mybatis.dao;

import java.util.List;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;
import org.springframework.stereotype.Repository;

import springdatabase.mybatis.domain.Good;


//@Repository
public interface GoodMapper {
	public List<Good> listGood();

	public Good getGood(int code);

	@Insert("insert into goods(code, name, manufacture, price, shelflife, image) values(#{code}, #{name}, #{manufacture}, #{price}, #{shelflife}, #{image})")
	public int insertGood(Good good);
	
	@Update("update goods set name=#{name}, manufacture=#{manufacture}, price = #{price} where code = #{code}")
	public int updateGood(Good good);
	
	@Delete("delete from goods where code = #{code}")
	public int deleteGood(int code);
}

