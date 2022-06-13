package springdatabase.mybatis.domain;

import java.sql.Date;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Good {
	private int code;
	private String name;
	private String manufacture;
	private int price;
	private Date shelflife;
	private byte [] image;
}
