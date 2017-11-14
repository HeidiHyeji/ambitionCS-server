package ServerPresentation;

import java.awt.*;
import java.util.Vector;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import ServerStorage.DBBorrow;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

/**
 * @author 김솔이
 * 2016.11.20 
 * SE 팀프로젝트 - fuse
 * 컴과 장비 관리&예약 프로그램
 * version 1.0
 * 대여기록 팬
 */

public class BorrowPane extends JPanel {
	private static BorrowPane rp = null;

	public static BorrowPane getRPInstance() {
		if (rp == null)
			rp = new BorrowPane();
		return rp;
	}

	public DefaultTableModel borrowTableModel = new DefaultTableModel();
	private JScrollPane borrowScrollPane = null;
	JTable tbBorrowTable = null;
	public Vector<String> titleBorrow = new Vector<String>();
	BorrowInfoDialog bInfoD = new BorrowInfoDialog();

	private BorrowPane() {
		setBackground(Color.WHITE);
		setLayout(null);
		
		titleBorrow.add("대여번호");
		titleBorrow.add("학생이름");
		titleBorrow.add("장비이름");
		titleBorrow.add("상태");

		// 대여 리스트
		Vector result = DBBorrow.getDbBInstance().selectComRejRetAll();
		borrowTableModel.setDataVector(result, titleBorrow);
		tbBorrowTable = new JTable(borrowTableModel);
		tbBorrowTable.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				bInfoD.dispose();
				bInfoD = new BorrowInfoDialog();
				int row = tbBorrowTable.getSelectedRow();
				String s = (String) tbBorrowTable.getValueAt(row, 0);
				Vector<String> res = new Vector<String>();
				res = DBBorrow.getDbBInstance().selectOne(s);
				bInfoD.tfBorrowNum.setText(res.elementAt(0));
				bInfoD.tfSName.setText(res.elementAt(1));
				bInfoD.tfSNum.setText(res.elementAt(2));
				bInfoD.tfSPhone.setText(res.elementAt(3));
				bInfoD.tfAdminNum.setText(res.elementAt(4));
				bInfoD.tfEName.setText(res.elementAt(5));
				bInfoD.tfType.setText(res.elementAt(6));
				bInfoD.tfRequestDate.setText(res.elementAt(8));
				bInfoD.tfAcceptDate.setText(res.elementAt(9));
				bInfoD.tfComDate.setText(res.elementAt(10));
				bInfoD.tfRetDate.setText(res.elementAt(11));
				bInfoD.tfStatus.setText(res.elementAt(12));
				
				bInfoD.setVisible(true);
				
			}
		});
		tbBorrowTable.setFont(new Font("맑은 고딕", Font.PLAIN, 20));
		tbBorrowTable.setBackground(Color.WHITE);
		tbBorrowTable.getTableHeader().setReorderingAllowed(false);
		tbBorrowTable.setRowHeight(30);

		borrowScrollPane = new JScrollPane(tbBorrowTable);
		borrowScrollPane.setBounds(10, 0, 920, 600);
		add(borrowScrollPane);
	}
}
