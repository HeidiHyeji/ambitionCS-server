package ServerStorage;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.Vector;

/**
 * @author 김솔이
 * 2016.11.20 
 * SE 팀프로젝트 - fuse
 * 컴과 장비 관리&예약 프로그램
 * version 1.0
 */

public class DBBorrow {
	private static DBBorrow dbBorrow;

	@SuppressWarnings("rawtypes")
	Vector dataB = new Vector<>();

	private PreparedStatement pstmtUpdate = null;
	private PreparedStatement pstmtAdd = null;

	public static DBBorrow getDbBInstance() {
		if (dbBorrow == null)
			dbBorrow = new DBBorrow();
		return dbBorrow;
	}
	
	DBEquip dbconn = DBEquip.getDBInstance();
	
	// 대여목록 가져오기
	// request, accept->신청리스트에
	// reject, return, complete->대여리스트에
	public Vector<Object> selectComRejRetAll() {
		Vector<Object> data = new Vector<>();
		data.clear();
		try {
			ResultSet rs = dbconn.stmt.executeQuery(
					"select bNum, sName, eName, bStatus "
							+ "from student, equipment,"
							+ "(select bNum , bStatus, "
							+ "studentNum, eNum from borrow "
							+ "where bStatus = 'return' "
							+ "OR bStatus = 'complete' "
							+ "OR bStatus = 'reject') b "
							+ "where student.sNum=b.studentNum "
							+ "AND b.eNum=equipment.adminNum");
			while (rs.next()) {
				Vector<String> in = new Vector<String>(); // 1개의 레코드 저장하는 벡터 생성

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
		// 전체 데이터 저장하는 data 벡터 리턴
		return data;
	}

	public Vector<Object> selectRequAccAll() {
		Vector<Object> data = new Vector<>();
		data.clear();
		try {
			ResultSet rs = dbconn.stmt.executeQuery(
					"select bNum , sName, eName, requestDate, bStatus "
							+ "from student, equipment,"
							+ "(select bNum , requestDate, bStatus, "
							+ "studentNum, eNum from borrow "
							+ "where bStatus = 'request' OR bStatus = 'accept') b "
							+ "where student.sNum=b.studentNum "
							+ "AND b.eNum=equipment.adminNum");
			while (rs.next()) {
				Vector<String> in = new Vector<String>(); // 1개의 레코드 저장하는 벡터 생성

				in.add(rs.getString(1));
				in.add(rs.getString(2));
				in.add(rs.getString(3));
				in.add(rs.getString(4));
				in.add(rs.getString(5));

				// 전체 데이터를 저장하는 벡터에 in(1명의 데이터 저장) 벡터 추가
				data.add(in);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		// 전체 데이터 저장하는 data 벡터 리턴
		return data;
	}

	public Vector<String> selectOne(String s) {
		Vector<String> in = new Vector<String>(); // 1개의 레코드 저장하는 벡터 생성
		try {
			ResultSet rs = dbconn.stmt.
					executeQuery("select bNum , sName, sNum, "
							+ "sPhone, eNum, eName, type,"
							+ " image, requestDate, acceptDate, "
							+ "completeDate, returnDate, bStatus "
							+ "from student, equipment, borrow "
							+ "where student.sNum=borrow.studentNum "
							+ "AND borrow.eNum=equipment.adminNum "
							+ "AND bNum = "+ s);
			while (rs.next()) {

				in.add(rs.getString(1));
				in.add(rs.getString(2));
				in.add(rs.getString(3));
				in.add(rs.getString(4));
				in.add(rs.getString(5));
				in.add(rs.getString(6));
				in.add(rs.getString(7));
				in.add(rs.getString(8));
				in.add(rs.getString(9));
				in.add(rs.getString(10));
				in.add(rs.getString(11));
				in.add(rs.getString(12));
				in.add(rs.getString(13));
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		// 전체 데이터 저장하는 data 벡터 리턴
		return in;
	}

	// 대여 승인&거절 후 수정
	public void UpdateBorrow(String reqNum, String bStatus, String date) {
		try {
			pstmtUpdate = dbconn.con.prepareStatement(
							"update borrow set bStatus = ?, "
							+ "acceptDate = ? where bNum = ?");
			pstmtUpdate.setString(1, bStatus);
			pstmtUpdate.setString(2, date);
			pstmtUpdate.setString(3, reqNum);
			pstmtUpdate.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	// 대여 완료 후 수정
		public void comUpdateBorrow(String reqNum, 
				String bStatus, String date) {
			try {
				pstmtUpdate = dbconn.con.prepareStatement(
								"update borrow set bStatus = ?, "
								+ "completeDate = ? where bNum = ?");
				pstmtUpdate.setString(1, bStatus);
				pstmtUpdate.setString(2, date);
				pstmtUpdate.setString(3, reqNum);
				pstmtUpdate.executeUpdate();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	
	// 대여 추가
	public void insertBorrow(String reqN, String sNum, String eNum,
			String bStatus, String reqD, String aD, 
			String cD, String retD) {
		try {
			pstmtAdd = dbconn.con.prepareStatement(
					"insert into borrow values(?,?,?,?,?,?,?,?)");

			pstmtAdd.setString(1, reqN);
			pstmtAdd.setString(2, sNum);
			pstmtAdd.setString(3, eNum);
			pstmtAdd.setString(4, bStatus);
			pstmtAdd.setString(5, reqD);
			pstmtAdd.setString(6, aD);
			pstmtAdd.setString(7, cD);
			pstmtAdd.setString(8, retD);

			pstmtAdd.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
