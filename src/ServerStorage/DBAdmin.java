package ServerStorage;

import java.sql.ResultSet;

/**
 * @author 김솔이
 * 2016.11.20 
 * SE 팀프로젝트 - fuse
 * 컴과 장비 관리&예약 프로그램
 * version 1.0
 */

public class DBAdmin {
	String AdminId;
	String AdminPw;
	boolean result;

	public boolean idPwCheck(String id, String pw) {
		try {
			ResultSet rs = DBEquip.getDBInstance().stmt.executeQuery("select * from administrator");
			while (rs.next()) {
				AdminId = rs.getString(1);
				AdminPw = rs.getString(2);
			}
			if (id.equals(AdminId) && pw.equals(AdminPw)) {
				result = true;
			} else {
				result = false;
			}

		} catch (Exception e) {
			e.printStackTrace();
		}
		return result;
	}
}
