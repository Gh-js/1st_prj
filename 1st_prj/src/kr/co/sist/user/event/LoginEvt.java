package kr.co.sist.user.event;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.sql.SQLException;

import javax.swing.JOptionPane;

import kr.co.sist.user.dao.LoginDAO;
import kr.co.sist.user.design.ClientMainDesign;
import kr.co.sist.user.design.JoinDesign;
import kr.co.sist.user.design.LoginDesign;
import kr.co.sist.user.design.NotificateDesign;
import kr.co.sist.user.vo.LoginResultVO;
import kr.co.sist.user.vo.LoginVO;

public class LoginEvt extends WindowAdapter implements ActionListener {

	
	LoginVO lVO = new LoginVO();

	private LoginDesign lg;

	public LoginEvt(LoginDesign lg) {
		this.lg = lg;
		
		// 아이디 필드에 액션 이벤트 리스너 추가
        lg.getJtfId().addActionListener(this);

        // 비밀번호 필드에 액션 이벤트 리스너 추가
        lg.getJpwPass().addActionListener(this);
	}

	@Override
	public void actionPerformed(ActionEvent ae) {
		if (ae.getSource() == lg.getJbLogin()) {
			chkEmpty();
		}
		if (ae.getSource() == lg.getJbjoin()) {
			new JoinDesign(lg);
		}
	}// actionPerformed

	@Override
	public void windowClosing(WindowEvent e) {
		lg.dispose();
	}

	private void chkEmpty() {
		String id = lg.getJtfId().getText().trim();
		// 아이디가 비어있다면 경고창으로 아이디 입력을 보여준다.
		if ("".equals(id)) {
			JOptionPane.showMessageDialog(lg, "아이디는 필수 입력");
			lg.getJtfId().requestFocus();
			return;
		}
		// 비밀번호가 비어있다면 경고창으로 비밀번호입력을 보여준다.
		String pass = new String(lg.getJpwPass().getPassword());
		if ("".equals(pass)) {
			JOptionPane.showMessageDialog(lg, "비밀번호는 필수입력");
			lg.getJpwPass().requestFocus();
			return;
		}

		// 그렇지 않으면 loginCheck()를 호출한다.
//		id=injectionBlock(id);//id와 비밀번호에 SQLInjection해당 하는 값이 존재하면 삭제시킨다 
//		pass=injectionBlock(pass);
		LoginVO lVO = new LoginVO(id, pass);
		loginCheck(lVO);
	}

	public String injectionBlock(String sql) {
		String resultSql = sql;
		// 공백을 허용하지 않음, SQL주석 막기 , 쿼리문에 해당하는 문자열 막기
		resultSql = sql.replaceAll(" ", "").replaceAll("--", "").replaceAll("select", "").replaceAll("insert", "")
				.replaceAll("delete", "");

		return resultSql;

	}

	private void loginCheck(LoginVO lVO) {
		LoginDAO ItDAO = LoginDAO.getInstance();
		try {
			LoginResultVO lrVO = ItDAO.login(lVO);
			if (lrVO == null) {
				JOptionPane.showMessageDialog(lg, "아이디 혹은 비밀번호를 확인해주세요!");
				return;
			}
			JOptionPane.showMessageDialog(lg, lrVO.getName() + "님으로 로그인 되었습니다.");
			lg.dispose(); //로그인 성공 시 로그인 화면이 종료된다.
			
			UserData.id = lVO.getId();
			UserData.name = lrVO.getName();
			UserData.pass = lVO.getPassword(); //로그인시 유저id, 이름, 패스워드 정보가 UserData 클래스 내의 static 변수에 저장된다.
			
			ClientMainDesign cmd = new ClientMainDesign(lg); //로그인 성공 시 메인 화면에 접속한다.
			new NotificateDesign(cmd); //알림톡 화면도 동시에 나타난다.
			

		} catch (SQLException e) {
			JOptionPane.showMessageDialog(lg, "아이디나 비밀번호를 입력해주세요");
			e.printStackTrace();
		}
	}

}
