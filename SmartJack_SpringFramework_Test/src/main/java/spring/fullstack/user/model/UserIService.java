package spring.fullstack.user.model;

import java.util.List;

import spring.fullstack.user.dtos.LogRecordDto;
import spring.fullstack.user.dtos.UserDto;

public interface UserIService {
	
	public List<UserDto> allUser();
	
	//회원가입
	public boolean userJoin(UserDto dto);
	
	//아이디 중복검사
	public String idChk(String id);
	
	// 로그인 (시큐리티 사용)
	public UserDto login(String id);
	
	//유저 로그인 정보(UserDto) 가져오기
	public UserDto getLoginUser(String id);
	
	//로그인/로그아웃 기록
	public boolean logInsert(LogRecordDto dto);

	//모든 로그 가져오기
	public List<LogRecordDto> getAllLogRecord();

}
