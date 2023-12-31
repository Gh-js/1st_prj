package manager.login;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.sql.SQLException;

import javax.swing.JOptionPane;

import manager.carmanager.CarManager;

public class LoginEvt extends WindowAdapter implements ActionListener {

	private LoginVO lVO;

	private LoginDesign lg;
	boolean flag;

	public LoginEvt(LoginDesign lg) {
		this.lg = lg;
		lVO = new LoginVO();
		flag = true;
	}

	@Override
	public void actionPerformed(ActionEvent ae) {
		if (ae.getSource() == lg.getJbLogin()) {
			chkEmpty();
			System.out.println("login");
		}
		if (flag) {
			if (ae.getSource() == lg.getJpwPass()) {
				chkEmpty();
				flag = false;
			}
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
		String ijId = injectionBlock(id);// id와 비밀번호에 SQLInjection해당 하는 값이 존재하면 삭제시킨다
		String ijPass = injectionBlock(pass);
		LoginVO lVO = new LoginVO();
		lVO.setEmpno(ijId);
		lVO.setPassword(ijPass);
		loginCheck(lVO);
	}

	public String injectionBlock(String sql) {
		String resultSql = sql;
		// 공백을 허용하지 않음, SQL주석 막기 , 쿼리문에 해당하는 문자열 막기
		resultSql = sql.replaceAll(" ", "").replaceAll("--", "").replaceAll("select", "").replaceAll("insert", "")
				.replaceAll("delete", "");

		return resultSql;

	}

	private void loginCheck(LoginVO VO) {
		LoginDAO ItDAO = LoginDAO.getInstance();
		try {
			LoginVO lVO = ItDAO.selectLogin(VO);
			if (lVO.getEmpname() == null) {
				JOptionPane.showMessageDialog(lg, "아이디 혹은 비밀번호를 확인해주세요!");
				return;
			}

			lg.dispose();
			JOptionPane.showMessageDialog(lg, lVO.getEmpname() + "님으로 로그인 되었습니다.");
			new CarManager(lVO);
		} catch (SQLException e) {
			JOptionPane.showMessageDialog(lg, "아이디나 비밀번호를 입력해주세요");
			e.printStackTrace();
		}
	}

}
