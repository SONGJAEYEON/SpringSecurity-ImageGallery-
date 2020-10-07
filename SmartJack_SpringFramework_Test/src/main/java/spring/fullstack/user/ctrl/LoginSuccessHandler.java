package spring.fullstack.user.ctrl;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.InternalAuthenticationServiceException;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.DefaultRedirectStrategy;
import org.springframework.security.web.RedirectStrategy;
import org.springframework.security.web.WebAttributes;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.security.web.savedrequest.HttpSessionRequestCache;
import org.springframework.security.web.savedrequest.RequestCache;
import org.springframework.security.web.savedrequest.SavedRequest;

import spring.fullstack.user.dtos.LogRecordDto;
import spring.fullstack.user.dtos.UserDto;
import spring.fullstack.user.model.UserIService;



public class LoginSuccessHandler implements AuthenticationSuccessHandler {

	@Autowired
	private UserIService service;
	
	private RequestCache requestCache = new HttpSessionRequestCache();
	private RedirectStrategy redirectStratgy = new DefaultRedirectStrategy();
	   
	private String loginidname;
	private String defaultUrl;
	

	@Override
	public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response,
			Authentication authentication) throws IOException, ServletException {
		  System.out.println("▒▒▒▒▒▒▒▒▒▒로그인 성공 핸들러▒▒▒▒▒▒▒▒▒▒ : 로그인 성공?");
	      System.out.println("▒▒▒▒▒▒▒▒▒▒로그인 성공 핸들러▒▒▒▒▒▒▒▒▒▒ : 기본 mapping url"+defaultUrl);
	      //입력 아이디
	      String user_id = ((UserDetails)authentication.getPrincipal()).getUsername();
	      
	      System.out.println("▒▒▒▒▒▒▒▒▒▒로그인 성공 핸들러▒▒▒▒▒▒▒▒▒▒ : 사용자 id >"+user_id);
	      
	      // 로그인 정보 가져오기
	      UserDto dto = service.getLoginUser(user_id);
	      
	      // 사용자 정보 세션에 담기, 에러세션 지우기
	      clearErrorSession(request, dto);
	      
	      // 로그인 성공날짜 업데이트
//	      service.loginUpdate(user_id);
	      LogRecordDto ldto = new LogRecordDto();
	      ldto.setId(user_id);
	      ldto.setIn_or_out("login");
	      service.logInsert(ldto);
		
	      resultRedirectStrategy(request, response, authentication, dto);
	}
	
	protected void resultRedirectStrategy(HttpServletRequest req, HttpServletResponse resp,
	         Authentication authentication, UserDto dto) throws IOException, ServletException {

	      SavedRequest savedRequest = requestCache.getRequest(req, resp);
	      
	      System.out.println("▒▒▒▒▒▒▒▒▒▒로그인 성공 핸들러▒▒▒▒▒▒▒▒▒▒ : 사용자 권한은 > " + dto.getAuth());
	      if(savedRequest!=null) {
	         String targetUrl = savedRequest.getRedirectUrl();
	         System.out.println("▒▒▒▒▒▒▒▒▒▒로그인 성공 핸들러▒▒▒▒▒▒▒▒▒▒ : 이동 주소 > "+targetUrl);
	         redirectStratgy.sendRedirect(req, resp, targetUrl);
	      } else {
	         redirectStratgy.sendRedirect(req, resp, defaultUrl);
	      }
	}
	
	@SuppressWarnings("unused")
	private void clearErrorSession(HttpServletRequest req, UserDto dto) {
		HttpSession session = req.getSession();
		req.removeAttribute("ERRORMSG");

		System.out.println("▒▒▒▒▒▒▒▒▒▒로그인 성공 핸들러▒▒▒▒▒▒▒▒▒▒ 사용자 정보 세션에 담기 : UserDto");
		UserDto userInfo = service.getLoginUser(dto.getId());
		System.out.println("▒▒▒▒▒▒▒▒▒▒로그인 성공 핸들러▒▒▒▒▒▒▒▒▒▒ 세션 성보 : " + userInfo);
//	    	  //유저 세션 담기
		session.setAttribute("user", userInfo);

		String error = (String) session.getAttribute(WebAttributes.AUTHENTICATION_EXCEPTION);
		System.out.println("▒▒▒▒▒▒▒▒▒▒로그인 성공 핸들러▒▒▒▒▒▒▒▒▒▒ : 에러가 세션에 담겨있는가 ? >" + error);

		if (session == null)
			return;
		session.removeAttribute(WebAttributes.AUTHENTICATION_EXCEPTION);
	}
	
	public String getLoginidname() {
		return loginidname;
	}

	public void setLoginidname(String loginidname) {
		this.loginidname = loginidname;
	}

	public String getDefaultUrl() {
		return defaultUrl;
	}

	public void setDefaultUrl(String defaultUrl) {
		this.defaultUrl = defaultUrl;
	}


}
