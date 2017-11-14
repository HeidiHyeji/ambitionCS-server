package ServerStorage;

import java.sql.*;
import java.util.Vector;

/**
 * @author 김솔이
 * 2016.11.20 
 * SE 팀프로젝트 - fuse
 * 컴과 장비 관리&예약 프로그램
 * version 1.0
 */

public class DBEquip {
	
	private static DBEquip db;
	
	public static DBEquip getDBInstance() {
		if ( db == null )
			db = new DBEquip();
		return db;
	}
	
	//장비상태 able, disable
	
	public Connection con = null;
	public Statement stmt = null;
	
	private String Url = "jdbc:mariadb://localhost:3306/fuse"; 
	private String user = "root"; 
	private String password = "se";
	
	//추가(insert) 쿼리 실행하는 PreparedStatement 객체 변수 선언
	private PreparedStatement pstmtAdd    = null;
	//삭제(delete) 쿼리 실행하는 PreparedStatement 객체 변수 선언
	private PreparedStatement pstmtDel    = null;
	//수정(update) 쿼리 실행하는 PreparedStatement 객체 변수 선언
	private PreparedStatement pstmtUpdate = null;
	
	private DBEquip(){
		preDbTreatment();
	}
	
	public void preDbTreatment() {
		try{
			Class.forName("org.mariadb.jdbc.Driver");			
			con = DriverManager.getConnection(Url,user,password);
			stmt = con.createStatement();
		}catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	//모든 장비 불러오기
	public Vector<Object> selectEquipAll() {
		Vector<Object> data = new Vector<>();
		try {
			ResultSet rs = stmt.executeQuery("select * from equipment");
			while (rs.next()) {
				Vector<String> in = new Vector<String>();
				// 벡터에 각각의 값 추가		
				in.add(rs.getString(3));
				in.add(rs.getString(4));
				in.add(rs.getString(1));
				in.add(rs.getString(2));
				in.add(rs.getString(5));
				in.add(rs.getString(6));
				in.add(rs.getString(7));

				// 전체 데이터를 저장하는 벡터에 in(1명의 데이터 저장) 벡터 추가
				data.add(in);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return data; // 전체 데이터 저장하는 data 벡터 리턴
	}
	
	//선택한 하나의 장비 불러오기
	public Vector<String> selectEquipOne(String s) {
		Vector<String> equip = new Vector<String>();
		try {
			ResultSet rs = stmt.executeQuery
					("select * from equipment where"+s);
			while (rs.next()) {		
				equip.add(rs.getString(3));
				equip.add(rs.getString(4));
				equip.add(rs.getString(1));
				equip.add(rs.getString(2));
				equip.add(rs.getString(5));
				equip.add(rs.getString(6));
				equip.add(rs.getString(7));
				
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return equip; // 전체 데이터 저장하는 data 벡터 리턴
	}
	
	//장비추가
	public void insert(String eName, String type, String adminN, 
			String serialN, String eStatus,
			String image, String detail){
		try{
			pstmtAdd = con.prepareStatement
					("insert into equipment values(?,?,?,?,?,?,?)");
	
			pstmtAdd.setString(1, adminN);
			pstmtAdd.setString(2, serialN);
			pstmtAdd.setString(3, eName);
			pstmtAdd.setString(4, type);
			pstmtAdd.setString(5, eStatus);
			pstmtAdd.setString(6, image);
			pstmtAdd.setString(7, detail);		

			//대입받은 쿼리를 실행 -> 입력 (insert)
			pstmtAdd.executeUpdate();

		}catch(Exception e){
			e.printStackTrace();
		}
	}
	
	//장비삭제
	public void delete(String adminN){
		try{
			pstmtDel = con.prepareStatement
					("delete from equipment where adminNum = ?");
 			pstmtDel.setString(1, adminN);

 			//대입받은 쿼리를 실행-> 삭제 (delete)
			pstmtDel.executeUpdate();
		}catch(Exception e){
			e.printStackTrace();
		}
	}
	//장비 수정
	public void update(String eName, String type, 
			String adminN, String serialN,String eStatus, 
			String image, String details){
		try{
			pstmtUpdate = con.prepareStatement
					( "update equipment set adminNum = ?, "
					+ "serialNum = ?, eName = ?, type = ?, eStatus = ?, "
					+ "Image = ?, details =? where adminNum = ?" );
			pstmtUpdate.setString(1, adminN);
			pstmtUpdate.setString(2, serialN);
			pstmtUpdate.setString(3, eName);
			pstmtUpdate.setString(4, type);
			pstmtUpdate.setString(5, eStatus);
			pstmtUpdate.setString(6, image);
			pstmtUpdate.setString(7, details);
			pstmtUpdate.setString(8, adminN);

			pstmtUpdate.executeUpdate();

		}catch(Exception e){
			e.printStackTrace();
		}
	}
	//장비 상태만 수정
	public void updateStatus(String adminN,String eStatus){
		try{

			pstmtUpdate = con.prepareStatement
					("update equipment set eStatus = ? "
							+ "where adminNum = ?");
			pstmtUpdate.setString(1, eStatus);
			pstmtUpdate.setString(2, adminN);

			pstmtUpdate.executeUpdate();

		}catch(Exception e){
			e.printStackTrace();
		}
	}
}
