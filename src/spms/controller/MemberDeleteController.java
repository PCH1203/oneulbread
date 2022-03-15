package spms.controller;

import java.util.Map;

import javax.servlet.http.HttpSession;

import spms.bind.DataBinding;
import spms.dao.MySqlMemberDAO;

import spms.annotation.Component;

@Component("/member/delete.do")
public class MemberDeleteController implements Controller, DataBinding {
	MySqlMemberDAO memberDAO = null;
	
	public MemberDeleteController setMemberDAO(MySqlMemberDAO memberDAO) {
		this.memberDAO = memberDAO;
		return this;
	}
	
	@Override
	public Object[] getDataBinders() {
		//key 값과 이름으로 매핑하여 자동으로 생성해야 되는 클래스 타입 지정
		return new Object[] {
				"no", Integer.class
		};
	}	
	
	@Override
	public String execute(Map<String, Object> model) throws Exception {		
		Integer no = (Integer)model.get("no");
		memberDAO.delete(no);
		HttpSession session = (HttpSession)model.get("session");
		session.invalidate();
		
		return "redirect:/index.jsp";
	}
}
