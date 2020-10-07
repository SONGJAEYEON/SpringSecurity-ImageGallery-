package spring.fullstack.user.ctrl;

import java.util.ArrayList;
import java.util.Collection;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.InternalAuthenticationServiceException;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import spring.fullstack.user.dtos.UserDto;
import spring.fullstack.user.model.UserIService;


public class UserSecurityCtrl implements UserDetailsService {

	
	private Logger log = LoggerFactory.getLogger(this.getClass());
	
	@Autowired
	private UserIService service;
	
	@Override
	public UserDetails loadUserByUsername(String id) throws UsernameNotFoundException {
		
		log.info("UserSecurityCtrl 실행, usrename 확인 : \t {}",id);
		UserDto dto = service.login(id);
		
		if (dto == null) {
            throw new InternalAuthenticationServiceException(id);
      }
		//이거 약간 만져야겠다
		// 유저의 권한을 담는 객체
		Collection<SimpleGrantedAuthority> roles = new ArrayList<SimpleGrantedAuthority>();
		// roles.add(new SimpleGrantedAuthority(유저의 권한 데이터 담기));
		// 유의점 ! 이때 유저의 권한을 담을때는 앞에 반드시 ROLE_를 붙여야만 시큐리티가 인식을 한다. ex> ROLE_Admin
		
		roles.add(new SimpleGrantedAuthority(dto.getAuth()));

		// 아이디, 비밀번호, 권한을 담은 사용자의 객체를 담아 생성
		log.info("UserSecurityCtrl 실행, id, pw, auth 확인 : \t {} : {} : {}",id,dto.getPw(),roles);
		UserDetails user = new User(id,dto.getPw(),roles);
		
		return user;
	}
	
}

