package spring.fullstack.gallery.model;

import java.util.List;

import spring.fullstack.gallery.dtos.GalleryDto;

public interface GalleryIService {

	//	이미지 올리기
	public boolean upLoadImg(GalleryDto dto);

	//	 모든 이미지 가져오기
	public List<GalleryDto> getAllImg();

	//	이미지 상세내용 
	public GalleryDto getImgDetail(String seq);

	//	 이미지 삭제 
	public boolean deleteImg(String seq);

}
