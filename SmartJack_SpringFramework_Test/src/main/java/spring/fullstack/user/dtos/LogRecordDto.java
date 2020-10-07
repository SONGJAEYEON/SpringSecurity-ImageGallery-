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
@EqualsAndHashCode(of = "log_seq")
@Builder 
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class LogRecordDto {

	private String log_seq   ;
	private String id        ;
	private String in_or_out ;
	private String log_time  ;
}
