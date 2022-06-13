package springdatabase.mybatis.service;

import springdatabase.mybatis.dao.GoodDao;
import springdatabase.mybatis.dao.GoodMapper;
import springdatabase.mybatis.domain.Good;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Calendar;
import java.sql.Date;
import java.util.GregorianCalendar;
import java.util.List;
import java.util.Scanner;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

//@Service
public class GoodServiceImpl implements GoodService {

	// @Autowired
	// private GoodDao goodDao;

//	@Autowired
	private GoodMapper goodDao;


	public void insertGood() {
		Scanner sc = new Scanner(System.in);
		while(true) {
			System.out.print("삽입할 코드를 정수로 입력하세요(종료는 -1):");
			String input = sc.nextLine();
			int code = -1;
			try {
				code = Integer.parseInt(input);
			} catch (Exception e) {
				System.out.println("코드는 숫자로 입력하세요!!!!");
				continue;
			}

			if (code == -1) {
				break;
			}

			System.out.print("이름을 입력하세요:");
			String name = sc.nextLine();
			System.out.print("삽입할 원산지를 입력하세요:");
			String manufacture = sc.nextLine();
			System.out.print("삽입할 가격를 입력하세요:");
			String price = sc.nextLine();

			System.out.print("유통 기한을 입력하세요(YYYY-MM-DD 형식):");
			String life = sc.nextLine();

			int year = Integer.parseInt(life.substring(0, 4));
			int month = Integer.parseInt(life.substring(5,7));
			int date = Integer.parseInt(life.substring(8));
			Calendar cal = new GregorianCalendar(year, month-1, date);
			Date shelflife = new Date(cal.getTimeInMillis());
			
			byte [] image = null;
			try {
				URL url = new URL("http://www.onlifezone.com/files/attach/images/962811/376/321/005/2.jpg");
				HttpURLConnection con = (HttpURLConnection)url.openConnection();
				int len = con.getContentLength();
				InputStream is = con.getInputStream();
				FileOutputStream fos = new FileOutputStream("2.jpg");
				while(true) {
					byte[] raster = new byte[len];
					int read = is.read(raster);
					if (read <= 0) {
						break;
					}
					fos.write(raster,0, read);
				}
				is.close();
				fos.close();
				con.disconnect();
				
				FileInputStream fis = new FileInputStream("2.jpg");
				image = new byte[fis.available()];
				fis.read(image);
				fis.close();

			}catch(Exception e) {
				System.out.println(e.getMessage());
				System.out.println("이미지 가져오기 실패");
			}
			Good insert = new Good(code, name, manufacture, Integer.parseInt(price), shelflife, image);
			int r = goodDao.insertGood(insert);
			if (r > 0)
				System.out.println("삽입 성공");
			else
				System.out.println("삽입 실패");
			break;
		}
		sc.close();
	}
	
	public void listGood() {
		List<Good> list = goodDao.listGood();
		for (Good good : list) {
			System.out.println(good);
		}
	}
	
	public void getGood() {
	Scanner sc = new Scanner(System.in);
	while (true) {
		System.out.print("검색할 코드를 입력하세요(종료는 -1):");
		String input = sc.nextLine();
		int code = -1;
		try {
			code = Integer.parseInt(input);
		} catch (Exception e) {
			System.out.println("코드는 숫자로 입력하세요!!!!");
			continue;
		}

		if (code == -1) {
			break;
		}
		Good good = goodDao.getGood(code);
		if (good == null) {
			System.out.println("없는 코드 번호입니다.");
		} else {
			System.out.println(good);
			try {
				if(good.getImage() != null) {
					FileOutputStream fos = new FileOutputStream("2.jpg");
					fos.write(good.getImage());
					fos.close();
				}
			}catch(Exception e) {
				System.out.println("파일의 내용이 없습니다.");
			}
		}
		break;
	}
	sc.close();
}
	
	public void updateGood() {
		Scanner sc = new Scanner(System.in);
		while (true) {
			System.out.print("수정할 코드를 정수로 입력하세요(종료는 -1):");
			String input = sc.nextLine();
			int code = -1;
			try {
				code = Integer.parseInt(input);
			} catch (Exception e) {
				System.out.println("코드는 숫자로 입력하세요!!!!");
				continue;
			}

			if (code == -1) {
				break;
			}
			Good good = goodDao.getGood(code);
			if (good != null) {
				System.out.print("수정할 이름을 입력하세요:");
				String name = sc.nextLine();
				System.out.print("수정할 원산지를 입력하세요:");
				String manufacture = sc.nextLine();
				System.out.print("수정할 가격를 입력하세요:");
				String price = sc.nextLine();
				Good update = new Good(code, name, manufacture, Integer.parseInt(price), null, null);
				int r = goodDao.updateGood(update);
				if (r > 0)
					System.out.println("수정 성공");
				else
					System.out.println("수정 실패");
				break;
			} else {
				System.out.println("존재하지 않는 코드 번호 입니다.");
				continue;
			}
		}
		sc.close();
	}
	
	public void deleteGood() {
		Scanner sc = new Scanner(System.in);
		while (true) {
			System.out.print("삭제할 코드를 정수로 입력하세요(종료는 -1):");
			String input = sc.nextLine();
			int code = -1;
			try {
				code = Integer.parseInt(input);
			} catch (Exception e) {
				System.out.println("코드는 숫자로 입력하세요!!!!");
				continue;
			}

			if (code == -1) {
				break;
			}

			
			 int r = goodDao.deleteGood(code);
			if (r != 0)
				System.out.println("삭제 성공");
			else
				System.out.println("삭제 실패");
			break;
		}
		sc.close();
	}

}