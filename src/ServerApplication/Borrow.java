package ServerApplication;

import java.text.SimpleDateFormat;
import java.util.*;

/**
 * @author 김솔이
 * 2016.11.20 
 * SE 팀프로젝트 - fuse
 * 컴과 장비 관리&예약 프로그램
 * version 1.0
 */

public class Borrow {
	private static Borrow b = null;

	public static Borrow getBorrowInstance() {
		if (b == null)
			b = new Borrow();
		return b;
	}
	
	public static String requNum = "0";
	String sNum = null;
	String eNum = null;
	String bSatus = null;
	String requDate = null;
	String accDate = null;
	String comDate = null;
	String retDate = null;
	
	//대여번호 자동 계산
	//1.클라이언트가 신청할경우 이 함수 접근하여 대여번호 자동 생성시켜주어야함.
	//2.또는 현장대여시 이 함수에 접근하여 자동 생성
	public String getRequNum(){
		Integer i = Integer.parseInt(requNum);
		i++;
		requNum = ""+i;
		return requNum;
	}
	
	//신청일을 현재 날짜로 계산해줌
	public String calRequDate(){
		// 현장대여시 모든 날짜는 오늘날짜로 세팅*반납날짜만 계산해줌*
		SimpleDateFormat form = new SimpleDateFormat("yyyy-MM-dd",
				Locale.KOREA);
		Date currentTime = new Date();
		String dTime = form.format(currentTime);
		this.requDate = dTime;
		return requDate;
	}
	public String calAccDate(){
		SimpleDateFormat form = new SimpleDateFormat("yyyy-MM-dd",
				Locale.KOREA);
		Date currentTime = new Date();
		String dTime = form.format(currentTime);
		this.accDate = dTime;
		
		return accDate;
	}
	//승인후 3일뒤 완료 날짜임 -> 이날까지 장비 찾아 갈것
	public String calComDate(String accDate){
		String[] values = accDate.split("-");
		
		int year = Integer.parseInt(values[0]);
		int month =Integer.parseInt(values[1]);
		int day =Integer.parseInt(values[2]);
		Calendar cal = Calendar.getInstance();

		cal.set(year, month-1, day);
		cal.add(Calendar.DATE, +3);
		java.util.Date after = cal.getTime();
		SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd",Locale.getDefault());
		comDate = formatter.format(after);
		return comDate;
	}
	//3주뒤 반납날짜임
	public String calRetDate(String comDate){
		String[] values = comDate.split("-");
		
		int year = Integer.parseInt(values[0]);
		int month =Integer.parseInt(values[1]);
		int day =Integer.parseInt(values[2]);
		Calendar cal = Calendar.getInstance();

		cal.set(year, month-1, day);
		cal.add(Calendar.DATE, +21);
		java.util.Date after = cal.getTime();
		SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd",Locale.getDefault());
		retDate = formatter.format(after);
		
		return retDate;
	}
}
