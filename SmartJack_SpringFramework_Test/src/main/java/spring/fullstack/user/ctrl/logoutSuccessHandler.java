package spring.fullstack.user.ctrl;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.logout.LogoutSuccessHandler;

import spring.fullstack.user.dtos.LogRecordDto;
import spring.fullstack.user.model.UserIService;

public class logoutSuccessHandler implements LogoutSuccessHandler {
	

	private String logoutidname;
	private String defaultUrl;
	
	@Autowired
	private UserIService service;
	
	@Override
	public void onLogoutSuccess(HttpServletRequest request, HttpServletResponse response, Authentication authentication)
			throws IOException, ServletException {
		if (authentication != null && authentication.getDetails() != null) {
            try {
                 request.getSession().invalidate();
                 System.out.println("▒▒▒▒▒▒▒▒▒▒로그아웃 성공 핸들러▒▒▒▒▒▒▒▒▒▒ : 로그아웃 성공?");
                 System.out.println("▒▒▒▒▒▒▒▒▒▒로그아웃 성공 핸들러▒▒▒▒▒▒▒▒▒▒ : 기본 mapping url"+defaultUrl);
                 String user_id = ((UserDetails)authentication.getPrincipal()).getUsername();
                 LogRecordDto ldto = new LogRecordDto();
     			ldto.setId(user_id);
     			ldto.setIn_or_out("logout");
     			service.logInsert(ldto);
     			System.out.println("■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■logout 로그아웃 성공");
     			
            } catch (Exception e) {
                e.printStackTrace();
            }
        } 
        response.setStatus(HttpServletResponse.SC_OK);
        response.sendRedirect("./loginForm.do");
    }
	
	public String getLogoutidname() {
		return logoutidname;
	}
	public void setLogoutidname(String loginidname) {
		this.logoutidname= loginidname;
	}
	public String getDefaultUrl() {
		return defaultUrl;
	}
	public void setDefaultUrl(String defaultUrl) {
		this.defaultUrl = defaultUrl;
	}
}