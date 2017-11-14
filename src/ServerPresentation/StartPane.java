package ServerPresentation;

import java.awt.SystemColor;
import javax.swing.*;

/**
 * @author 김솔이
 * 2016.11.20 
 * SE 팀프로젝트 - fuse
 * 컴과 장비 관리&예약 프로그램
 * version 1.0
 */

public class StartPane extends JPanel {
	private JLabel lbMainImage = new JLabel();

	public StartPane() {
		setBackground(SystemColor.inactiveCaption);
		setLayout(null);
		lbMainImage.setBounds(249, 80, 432, 484);
		lbMainImage.setIcon(new ImageIcon("cs.png"));
		add(lbMainImage);		
	}

}
