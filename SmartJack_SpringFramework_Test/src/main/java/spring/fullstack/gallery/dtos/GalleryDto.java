package spring.fullstack.gallery.dtos;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter 
@EqualsAndHashCode(of = "image_seq")
@Builder 
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class GalleryDto {
	private String image_seq  ;
	private String id         ;
	private String image_name ;
	private String stored_name;
	private String file_size  ;
	private String upload_time;
}
