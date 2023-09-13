package manager.booking;

import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.JTableHeader;

public class BookingManagerTab extends JPanel implements ActionListener {
	private BookingManagerEvt bmtEvt;

	private JLabel jlBMName;
	private JTable jtbOrderInfoTable;
	private JScrollPane scrollPane;

	private JLabel omMiddle;
	private JTextField jtfStartDate;
	private JTextField jtfEndDate;
	private JButton jbDateSearch;

	private JButton jbAccept;
	private JButton jbRefusal;
	private JButton jbReservation;
	
	private JButton jbReservationManage;
	private JButton jbSave;

	private DefaultTableModel dtm;

	public BookingManagerTab() {

		// 페이지 이름
		jlBMName = new JLabel("예약 신청 관리");
		Font bmNameFont = new Font(null, Font.BOLD, 20);
		jlBMName.setFont(bmNameFont);

		dtm = new DefaultTableModel();

		// 재고 게시판
		jtbOrderInfoTable = new JTable(dtm) {
			@Override
			public Class<?> getColumnClass(int column) {

				switch (column) {
				case 0:
					return String.class;
				case 1:
					return String.class;
				case 2:
					return String.class;
				case 3:
					return String.class;
				case 4:
					return String.class;
				case 5:
					return String.class;
				case 6:
					return String.class;
				case 7:
					return String.class;
				case 8:
					return Boolean.class;
				}

				return super.getColumnClass(column);
			}
		};
		scrollPane = new JScrollPane(jtbOrderInfoTable);
		// 컬럼네임 크기 조절
		JTableHeader tableHeader = jtbOrderInfoTable.getTableHeader();
		Font headerFont = new Font(null, Font.BOLD, 14);
		tableHeader.setFont(headerFont);
		// 데이터 크기 조절
		DefaultTableCellRenderer renderer = (DefaultTableCellRenderer) jtbOrderInfoTable
				.getDefaultRenderer(Object.class);
		Font dataFont = new Font(null, Font.PLAIN, 14);
		renderer.setFont(dataFont);
		renderer.setHorizontalAlignment(SwingConstants.CENTER); // 데이터 가운데 정렬

		// 날짜, 검색
		omMiddle = new JLabel("~");
		Font omMiddleFont = new Font(null, Font.BOLD, 14);
		omMiddle.setFont(omMiddleFont);

		jtfStartDate = new JTextField();
		jtfEndDate = new JTextField();
		jbDateSearch = new JButton("검색");

		jbAccept = new JButton("수락");
		jbRefusal = new JButton("거절");
		jbReservation = new JButton("예약 사항");
		jbReservationManage = new JButton("예약 신청 관리");
		jbSave = new JButton("저장");

		// 추가
		setLayout(null);

		add("North", jlBMName);
		add("Center", scrollPane);

		add("Center", omMiddle);
		add("Center", jtfStartDate);
		add("Center", jtfEndDate);
		add("Center", jbDateSearch);

		add("Center", jbAccept);
		add("Center", jbRefusal);
		add("Center", jbReservation);
		
		add("Center", jbReservationManage);
		add("Center", jbSave);
		

		bmtEvt = new BookingManagerEvt(this);
		// 클릭 이벤트
		jtfStartDate.addActionListener(bmtEvt);
		jtfEndDate.addActionListener(bmtEvt);
		jbDateSearch.addActionListener(bmtEvt);
		jbReservation.addActionListener(bmtEvt);
		jbReservationManage.addActionListener(bmtEvt);
		jbSave.addActionListener(bmtEvt);

		// 크기 조정 및 배치
		jlBMName.setBounds(10, 6, 140, 20);
		scrollPane.setBounds(65, 50, 800, 400);

		jtfStartDate.setBounds(65, 520, 140, 30); // 시작 날짜 필드의 위치와 크기 설정
		omMiddle.setBounds(215, 520, 20, 20);
		jtfEndDate.setBounds(235, 520, 140, 30); // 종료 날짜 필드의 위치와 크기 설정
		jbDateSearch.setBounds(385, 520, 70, 30); // 검색 버튼의 위치와 크기 설정

		jbAccept.setBounds(485, 520, 120, 30);
		jbRefusal.setBounds(610, 520, 120, 30);
		jbReservation.setBounds(735, 520, 120, 30);
		
		jbSave.setBounds(610, 520, 120, 30);
		jbReservationManage.setBounds(735, 520, 120, 30);

		scrollPane.setVisible(true);
		setVisible(true);

	} // BookingManagerTab


	public BookingManagerEvt getBmtEvt() {
		return bmtEvt;
	}


	public void setBmtEvt(BookingManagerEvt bmtEvt) {
		this.bmtEvt = bmtEvt;
	}


	public JLabel getJlBMName() {
		return jlBMName;
	}


	public void setJlBMName(JLabel jlBMName) {
		this.jlBMName = jlBMName;
	}


	public JTable getJtbOrderInfoTable() {
		return jtbOrderInfoTable;
	}


	public void setJtbOrderInfoTable(JTable jtbOrderInfoTable) {
		this.jtbOrderInfoTable = jtbOrderInfoTable;
	}


	public JScrollPane getScrollPane() {
		return scrollPane;
	}


	public void setScrollPane(JScrollPane scrollPane) {
		this.scrollPane = scrollPane;
	}


	public JLabel getOmMiddle() {
		return omMiddle;
	}


	public void setOmMiddle(JLabel omMiddle) {
		this.omMiddle = omMiddle;
	}


	public JTextField getJtfStartDate() {
		return jtfStartDate;
	}


	public void setJtfStartDate(JTextField jtfStartDate) {
		this.jtfStartDate = jtfStartDate;
	}


	public JTextField getJtfEndDate() {
		return jtfEndDate;
	}


	public void setJtfEndDate(JTextField jtfEndDate) {
		this.jtfEndDate = jtfEndDate;
	}


	public JButton getJbDateSearch() {
		return jbDateSearch;
	}


	public void setJbDateSearch(JButton jbDateSearch) {
		this.jbDateSearch = jbDateSearch;
	}


	public JButton getJbAccept() {
		return jbAccept;
	}


	public void setJbAccept(JButton jbAccept) {
		this.jbAccept = jbAccept;
	}


	public JButton getJbRefusal() {
		return jbRefusal;
	}


	public void setJbRefusal(JButton jbRefusal) {
		this.jbRefusal = jbRefusal;
	}


	public JButton getJbReservation() {
		return jbReservation;
	}


	public void setJbReservation(JButton jbReservation) {
		this.jbReservation = jbReservation;
	}


	public JButton getJbReservationManage() {
		return jbReservationManage;
	}


	public void setJbReservationManage(JButton jbReservationManage) {
		this.jbReservationManage = jbReservationManage;
	}


	public DefaultTableModel getDtm() {
		return dtm;
	}


	public void setDtm(DefaultTableModel dtm) {
		this.dtm = dtm;
	}
	


	public JButton getJbSave() {
		return jbSave;
	}


	public void setJbSave(JButton jbSave) {
		this.jbSave = jbSave;
	}


	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub

	}

} // class
