package springdatabase.domain;

import java.sql.Date;

import lombok.Data;

@Data
public class Contact {
	private int id;
	private String name;
	private String phoneNumber;
	private Date birthday;
}
