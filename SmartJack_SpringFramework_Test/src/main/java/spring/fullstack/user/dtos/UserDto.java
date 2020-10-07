package spring.fullstack.user.dtos;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter 
@EqualsAndHashCode(of = "id")
@Builder 
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class UserDto {
	
	private String id        ;
	private String pw        ;
	private String email     ;
	private String logintime ;
	private String logouttime;
	private String auth      ;
	private String enabled   ;
	
	
}
