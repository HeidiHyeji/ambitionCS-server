package ServerPresentation;

import java.awt.*;
import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.event.*;

public class StudentDialog extends JDialog {

	JPanel sP = new JPanel();
	
	private JLabel lbSName = new JLabel("이름");
	public JTextField tfSName = new JTextField(15);
	private JLabel lbSNum = new JLabel("학번");
	public JTextField tfSNum = new JTextField(15);
	private JLabel lbPhone  = new JLabel("연락처");
	public JTextField tfPhone = new JTextField(15);
	private JLabel lbPw  = new JLabel("비밀번호");
	public JTextField tfPw = new JTextField(15);
	
	private Font fPlain20 = new Font("맑은 고딕", Font.PLAIN, 20);
	
	JPanel bP = new JPanel();
	JButton btAdd = new JButton("정보등록");
	JButton btUp = new JButton("정보수정");
	JButton btDel = new JButton("정보삭제");
	JButton btQuit = new JButton("종료");

	public StudentDialog(String title) {
		setTitle(title);
		setBounds(1200, 500, 500, 300);
		getContentPane().setLayout(new BorderLayout());
		
		//******************학생정보팬**************************
		sP.setLayout(new GridLayout(0, 2));
		sP.setBorder(new EmptyBorder(5, 5, 5, 5));
		sP.setBackground(Color.WHITE);
		getContentPane().add(sP, BorderLayout.CENTER);
		
		setLabel(lbSName);
		sP.add(lbSName);
		
		tfSName.setFont(fPlain20);
		sP.add(tfSName);
		
		setLabel(lbSNum);
		sP.add(lbSNum);
		
		tfSNum.setFont(fPlain20);
		sP.add(tfSNum);
		
		setLabel(lbPhone);
		sP.add(lbPhone);
		
		tfPhone.setFont(fPlain20);
		sP.add(tfPhone);
		
		setLabel(lbPw);
		sP.add(lbPw);
		
		tfPw.setFont(fPlain20);
		sP.add(tfPw);
		bP.setBorder(new EmptyBorder(0, 5, 5, 5));
		bP.setBackground(Color.WHITE);
		getContentPane().add(bP, BorderLayout.SOUTH);
		bP.setLayout(new FlowLayout(FlowLayout.CENTER, 5, 5));
		
		//학생 정보 등록 버튼
		btAdd.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				//학생등록!
				//학생팬업데이트
			}
		});		
		setButton(btAdd);
		
		//학생 정보 수정 버튼
		btUp.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				//해당학생정보 수정
				//학생팬업데이트
			}
		});		
		setButton(btUp);
		
		//학생 정보 삭제 버튼
		btDel.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				//해당학생 삭제
				//학생팬업데이트
			}
		});		
		setButton(btDel);
		
		//다이얼로그 종료 버튼
		btQuit.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				dispose();
			}
		});		
		setButton(btQuit);
	}
	
	private void setLabel(JLabel label){
		label.setBackground(Color.WHITE);
		label.setFont(fPlain20);
	}
	
	private void setButton(JButton button){
		button.setFont(new Font("맑은 고딕", Font.PLAIN, 21));
		button.setBackground(Color.white);
	}

}
