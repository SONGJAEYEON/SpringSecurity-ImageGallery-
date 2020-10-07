package spring.fullstack.user.model;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import spring.fullstack.user.dtos.LogRecordDto;
import spring.fullstack.user.dtos.UserDto;

@Service
public class UserServiceImpl implements UserIService {

	private Logger log = LoggerFactory.getLogger(this.getClass());
	
	@Autowired
	private UserIDao dao;
	
	@Override
	public List<UserDto> allUser() {
		log.info("UserServiceImpl allUser 모든회원조회");
		return dao.allUser();
	}

	@Override
	public String idChk(String id) {
		log.info("UserServiceImpl idChk 아이디 중복체크 : 중복체크값 > {} ",id);
		return dao.idChk(id);
	}

	@Override
	public boolean userJoin(UserDto dto) {
		log.info("UserServiceImpl userJoin 회원가입 : \n 회원정보 > {}",dto);
		return dao.userJoin(dto);
	}

	@Override
	public UserDto login(String id) {
		log.info("UserServiceImpl login 로그인 : 사용자 아이디 > {} ",id);
		return dao.login(id);
	}

	@Override
	public UserDto getLoginUser(String id) {
		log.info("UserServiceImpl getLoginUser 사용자 로그인정보 가져오기 > {} ",id);
		return dao.getLoginUser(id);
	}

	@Override
	public boolean logInsert(LogRecordDto dto) {
		log.info("UserServiceImpl logInsert 사용자 로그정보 입력 > {} ",dto);
		return dao.logInsert(dto);
	}

	@Override
	public List<LogRecordDto> getAllLogRecord() {
		log.info("UserServiceImpl getAllLogRecord 모든 로그정보 가져오기 > {} ");
		return dao.getAllLogRecord();
	}





}
