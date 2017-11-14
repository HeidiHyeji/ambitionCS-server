package ServerStorage;

import java.sql.ResultSet;
import java.util.Vector;
import java.sql.PreparedStatement;

/**
 * @author 김솔이
 * 2016.11.20 
 * SE 팀프로젝트 - fuse
 * 컴과 장비 관리&예약 프로그램
 * version 1.0
 */

public class DBStudent {

	private static DBStudent db;

	public static DBStudent getSDInstance() {
		if (db == null)
			db = new DBStudent();
		return db;
	}

	String sName = null;
	String sNum = null;
	boolean result = true;

	private PreparedStatement pstmtIn = null;
	private PreparedStatement pstmtUp = null;
	private PreparedStatement pstmtDel = null;

	DBEquip dbconn = DBEquip.getDBInstance();
	
	public boolean sNumCheck(String sNa, String sNu) {
		try {
			ResultSet rs = dbconn.stmt.executeQuery(
					"select sName from student where sNum=" + sNu);
			while (rs.next()) {
				sName = rs.getString(1);
			}
			if (sNa.equals(sName)) {
				result = true;
			} else {
				result = false;
			}

		} catch (Exception e) {
			e.printStackTrace();
		}
		return result;
	}
	
	public Vector<Object> selectStudentAll(){
		Vector<Object> data = new Vector<>();
		try {
			ResultSet rs = dbconn
					.stmt.executeQuery("select * from student");
			while (rs.next()) {
				// 1개의 레코드 저장하는 벡터 생성
				Vector<String> in = new Vector<String>();
				// 벡터에 각각의 값 추가		
				in.add(rs.getString(1));
				in.add(rs.getString(2));
				in.add(rs.getString(3));
				in.add(rs.getString(4));
				
				// 전체 데이터를 저장하는 벡터에 in(1명의 데이터 저장) 벡터 추가
				data.add(in);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return data; // 전체 데이터 저장하는 data 벡터 리턴
	}
	
	public Vector<String> selectStudentOne(String sN){
		Vector<String> student = new Vector<String>();
		try {
			ResultSet rs = dbconn.stmt.executeQuery(
					"select * from student where sNum = "+sN);
			while (rs.next()) {
				student.add(rs.getString(1));
				student.add(rs.getString(2));
				student.add(rs.getString(3));
				student.add(rs.getString(4));
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return student;
	}
	
	// 학생 등록
	public void inStudent(String sName, String sNum, String sPhone,
				String sPw) {
		try {
			pstmtIn = dbconn.con.prepareStatement(
					"insert into student values(?,?,?,?,?)");

			pstmtIn.setString(1, sName);
			pstmtIn.setString(2, sNum);
			pstmtIn.setString(3, sPhone);
			pstmtIn.setString(4, sPw);
			pstmtIn.setString(5, "false");

			pstmtIn.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	// 학생 정보 수정
	public void upStudent(String sName, String sNum,
			String sPhone, String sPw) {
		try {
			pstmtUp = dbconn.con.prepareStatement(
					"update student set sName = ?, sNum = ?, "
					+ "sPhone = ?, sPw = ? where sNum = ?");
			pstmtUp.setString(1, sName);
			pstmtUp.setString(2, sNum);
			pstmtUp.setString(3, sPhone);
			pstmtUp.setString(4, sPw);
			pstmtUp.setString(5, sNum);
			
			pstmtUp.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	// 학생 삭제
	public void delStudent(String sNum) {
		try {
			pstmtDel = dbconn.con.prepareStatement(
					"delete from student where sNum = ?");
			pstmtDel.setString(1, sNum);
			
			pstmtDel.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
