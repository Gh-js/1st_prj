package manager;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class CarManagerTabEvt implements ActionListener {
	private CarManagerTab cmt;
	
	public CarManagerTabEvt(CarManagerTab cmt) {
		this.cmt=cmt;
		setCarInfoTable();
	}
	

	
	public void setCarInfoTable(){
		CarManagerDAO cmDAO = CarManagerDAO.getInstance();
		
		String strDate = cmt.getJtfStartDate().getText();
		String endDate =  cmt.getJtfEndDate().getText();
		List<CarManagerVO> list = null;
		
		
        // "YYYY-MM-DD" 형식의 정규식
        String regex = "\\d{4}-\\d{2}-\\d{2}";

        // 정규식 패턴을 컴파일합니다.
        Pattern pattern = Pattern.compile(regex);

        // 입력 문자열과 패턴을 매칭합니다.
        Matcher strMatcher = pattern.matcher(strDate);
        Matcher endMatcher = pattern.matcher(endDate);
        
        //정규식 패턴에 일치하지 않으면 ""으로 처리
        if(!strMatcher.matches() || !endMatcher.matches()) {
        	strDate="";
        	endDate="";
        }//end if
		
		try {
			list = cmDAO.selectCarInfo(strDate, endDate);

			//JTable의 칼럼이 0개라면 칼럼명 추가
			if(cmt.getJtbCarInfoTable().getColumnCount()==0) {
				//JTable 칼럼명 설정
				String[] columnNames = {"정비번호","차량번호", "모델명", "수리 내역", "입고일", "출고일", "비고"};
				for(int i=0;i<columnNames.length;i++) {
					cmt.getDtm().addColumn(columnNames[i]);
				}//end for
			}//end if
			
			//JTable 데이터 삭제
			cmt.getDtm().setRowCount(0);
			
			//JTable 행 정보 추가
			String[] arrRow = new String[7];
			for(int i=0;i<list.size();i++) {
				CarManagerVO cmVO = list.get(i);
				arrRow[0] = String.valueOf(cmVO.getHistoryNo());
				arrRow[1] = cmVO.getCarNo();
				arrRow[2] = cmVO.getCarName();
				arrRow[3] = cmVO.getMaintenanceDetail();
				arrRow[4] = cmVO.getReceivedDay();
				arrRow[5] = cmVO.getReleaseDay();
				arrRow[6] = cmVO.getNote();
				cmt.getDtm().addRow(arrRow);
			}//end for
			
		} catch (SQLException e) {
			e.printStackTrace();
		}//end catch
	}//setCarInfoTable
	
	@Override
	public void actionPerformed(ActionEvent e) {
		if(e.getSource()==cmt.getJbDateSearch()) {
			setCarInfoTable();
		}//end if
	}//actionPerformed
	
	
	

} //class