package spring.fullstack.user.model;

import java.util.List;

import org.mybatis.spring.SqlSessionTemplate;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Repository;

import spring.fullstack.user.dtos.LogRecordDto;
import spring.fullstack.user.dtos.UserDto;

@Repository
public class UserDaoImpl implements UserIDao{
	
	private Logger log = LoggerFactory.getLogger(this.getClass());
	
	@Autowired
	private SqlSessionTemplate sqlSession;
	
	@Autowired
	private PasswordEncoder passwordEncoder;
	
	private final String NS="spring.fullstack.user.";

	@Override
	public List<UserDto> allUser() {
		log.info("UserDaoImpl allUser");
		return sqlSession.selectList(NS+"allUser");
	}

	@Override
	public String idChk(String id) {
		log.info("UserDaoImpl idChk 아이디 중복체크 : {}",id);
		return sqlSession.selectOne(NS+"idChk",id);
	}

	@Override
	public boolean userJoin(UserDto dto) {
		log.info("UserDaoImpl userJoin 회원가입 (로그인정보) : {}",dto);
		String enPW = passwordEncoder.encode(dto.getPw());
		dto.setPw(enPW);
		log.info("UserDaoImpl joinLogin 회원가입 pw 암호화 (로그인정보) : {}",dto);
		return (sqlSession.insert(NS+"userJoin", dto)>0)?true:false;
	}

	@Override
	public UserDto login(String id) {
		log.info("UserDaoImpl login 로그인(시큐리티 사용) : {}",id);
		return sqlSession.selectOne(NS+"login", id);
	}

	@Override
	public UserDto getLoginUser(String id) {
		log.info("UserDaoImpl getLoginUser 회원 로그인 정보 가져오기 : {}",id);
		return sqlSession.selectOne(NS+"getLoginUser", id);
	}

	@Override
	public boolean logInsert(LogRecordDto dto) {
		log.info("UserDaoImpl logInsert 사용자 로그정보 입력 > {} ",dto);
		return (sqlSession.insert(NS+"logInsert", dto)>0)?true:false;
	}

	@Override
	public List<LogRecordDto> getAllLogRecord() {
		log.info("UserDaoImpl getLoginUser 모든 로그정보 가져오기 : {}");
		return  sqlSession.selectList(NS+"getAllLogRecord");
	}

	
	

}
