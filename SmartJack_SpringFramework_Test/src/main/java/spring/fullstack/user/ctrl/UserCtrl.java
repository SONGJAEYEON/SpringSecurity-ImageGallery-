package spring.fullstack.user.ctrl;

import java.io.IOException;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.google.gson.JsonArray;
import com.google.gson.JsonObject;

import spring.fullstack.gallery.dtos.GalleryDto;
import spring.fullstack.user.dtos.LogRecordDto;
import spring.fullstack.user.dtos.UserDto;
import spring.fullstack.user.model.UserIService;

@Controller
public class UserCtrl {
	
	private Logger log = LoggerFactory.getLogger(this.getClass());
	
	@Autowired
	private UserIService service;
	@Autowired
	private JavaMailSender mailSender;
	
	private String key;//생성되는 인증키를 담음
	private String setFrom = "shw991027@gmail.com";//발신자
	private String title="이메일 인증 코드 발송 ";//메일 제목
	
	@RequestMapping(value = "/loginForm.do", method = { RequestMethod.GET, RequestMethod.POST })
	public String loginForms(@RequestParam(value = "error", required = false) String error, Model model,
			Authentication user, HttpServletRequest request) {
		System.out.println(service.getAllLogRecord().toString());
		if (user != null) {
			UserDetails userD = (UserDetails) user.getPrincipal();

			model.addAttribute("userD", userD.toString());
		}
		return "login/login";
	}
	
	@RequestMapping(value = "/signUpForm.do",method = RequestMethod.GET)
	public String registerForm() {
		return "/login/signUp";
	}
	
	
	@RequestMapping(value = "/emailChkForm.do", method = RequestMethod.GET)
	public String mail() {
		log.info("UserCtrl 이메일 인증 Form : \t {}", new Date());
		return "login/emailChk";
	}

	private String makeKey() {
		String key = null;
		Random rnd = new Random();
		StringBuffer buffer = new StringBuffer();
		for (int i = 0; i < 12; i++) {
			if (rnd.nextBoolean()) {
				buffer.append((char) (int) (rnd.nextInt(26) + 65)); // 랜덤한 대문자
			} else {
				buffer.append((rnd.nextInt(10)));
			}
		}
		key = buffer.toString();
		log.info("UserCtrl makeKey 이메일 인증 키 생성 : \t {}", key);
		return key;
	}


	/**
	 * 메일 전송 Ajax 처리
	 * @param String user_email 화면에서 사용자에게 입력받은 이메일
	 * @return Map<String, String> map 
	 * <br> : available > 중복되지 않은 사용가능한 메일일 시 "true" / 사용 불가한 메일일 시 "false"
	 * <br> : isc > 메일 전송에 성공했을 시 "true" / 메일 전송 실패 시 "false" 
	 * @throws IOException
	 * @throws MessagingException 
	 */
	@ResponseBody
	@RequestMapping(value = "/sendEmail.do", method = RequestMethod.POST)
	public String mailSend(String user_email) throws IOException{
		log.info("UserCtrl_Mail 메일 전송 시도 : \t {}", user_email);

		String isc="false";
			this.key = makeKey();
			String content = "<div style='font-size:15px;'>홈페이지 이메일 인증 코드는 [ " + key + " ] 입니다. 정확히 입력해 주세요.</div>";

			log.info("UserCtrl_Mail 이메일 전송 결과 : \t {} : {} : {}", user_email, title, content);
			// toEmail 받는사람 주소, title 메일 제목, content 메일 내용

			MimeMessage message = mailSender.createMimeMessage();

			try {
				MimeMessageHelper messageHelper = new MimeMessageHelper(message, true, "UTF-8");
				messageHelper.setFrom(setFrom);
				messageHelper.setTo(user_email);
				messageHelper.setSubject(title);
				messageHelper.setText(content, true);

				mailSender.send(message);
				isc="true";

			} catch (MessagingException e) {
				e.printStackTrace();
			}

		return isc;
	}
	
	
	/**
	 * 이메일로 전송받은 인증키가 일치하는지 판단하는 Ajax처리
	 * @param emKey 화면에서 사용자에게 입력받은 인증키
	 * @return String 인증키 일치시 "true" / 불일치 시 "false"
	 */
	@ResponseBody
	@RequestMapping(value = "/emailCheck.do", method = RequestMethod.POST)
	public String emailCheck(String emKey) {
		String isc = "false";
		if (emKey.trim().equals(key)) {
			isc = "true";
		}
		return isc;
	}
	

	// 아이디 중복검사창 띄우기
	@RequestMapping(value="/idChkForm.do", method = {RequestMethod.GET, RequestMethod.POST})
	public String idChkForm() {
		log.info("UserCtrl_Login 아이디 중복 체크 화면 : \t {}",new Date());
		return "login/idChk";
	}

	@ResponseBody
	@RequestMapping(value="/idChk.do", method = RequestMethod.POST)
	public String idChk(String id) {
		log.info("UserCtrl_User_Ajax 아이디 중복 체크 아작스 처리: \t {}",id);
		String userID = service.idChk(id);
		String isc = "true";
		if(userID != null) {
			isc = "false";
		}
		return isc;
	}
	
	@RequestMapping(value = "/signUpSuccess.do", method = RequestMethod.POST)
	public String maingo(String id, String pw, String email,Model model) {
		log.info("UserCtrl signUpSuccess.do 회원가입 시도");
		String url = null;
		UserDto dto = new UserDto();
		dto.setId(id);
		dto.setPw(pw);
		dto.setEmail(email);
		log.info("UserCtrl_Login 회원가입 정보 : \t userdata:{}",dto);
		System.out.println("UserCtrl 회원가입 정보 : " +dto.toString());
		
		boolean isc =service.userJoin(dto);
		if(isc){ // 회원가입 성공
			model.addAttribute("msg", "환영합니다.");
			url = "login/login";
		}else { // 회원가입이 되지 않았다면
			model.addAttribute("msg", "회원 가입에 실패하였습니다.");
			url = "error";
		}
		return url ;
	}

	@RequestMapping(value = "/loginResult.do", method = {RequestMethod.GET, RequestMethod.POST})
	public String goGallery(Authentication user, Model model, HttpSession session) {
		UserDto dto = (UserDto) session.getAttribute("user");
		log.info("UserCtrl goGallery 갤러리 입장(유저확인) : \t{}",dto.toString());
		return "gallery/gallery";
	}
	
	// 권한이 없는 곳으로 접근했을 때
	@RequestMapping(value = "/access_denied_page.do", method = RequestMethod.GET)
	public String authError(Model model, HttpSession session) {
		UserDto iDto = (UserDto)session.getAttribute("user");
		log.info("UserCtrl_Login 권한이 미승인된 접근 : \t 접근자 {}",iDto.getId());
		return "login/authError";
	}
	
	@RequestMapping(value="/gologinRecord.do",method =RequestMethod.GET)
	public String gologinRecord(Model model) { //뭔가 아규먼트가 좀 있어야 하지 않겠니,,?
		System.out.println("■■■■■■■■■■■■■■■■■■■로그인 기록■■■■■■■■■■■■■■■■■■■■■");
		List<LogRecordDto> logList=service.getAllLogRecord();
		model.addAttribute("logList", logList);
		return "admin/loginRecord";
	}
	

	
}
