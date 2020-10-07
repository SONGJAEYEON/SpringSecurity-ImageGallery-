package spring.fullstack.gallery.model;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import spring.fullstack.gallery.dtos.GalleryDto;

@Service
public class GalleryServiceImpl implements GalleryIService {

	private Logger log = LoggerFactory.getLogger(this.getClass());
	
	@Autowired
	private GalleryIDao dao;
	
	@Override
	public boolean upLoadImg(GalleryDto dto) {
		log.info("GalleryServiceImpl upLoadImg 이미지 올리기  : {}",dto);
		return dao.upLoadImg(dto);
	}

	@Override
	public List<GalleryDto> getAllImg() {
		log.info("GalleryServiceImpl getAllImg 모든 이미지 가져오기 ");
		return dao.getAllImg();
	}

	@Override
	public GalleryDto getImgDetail(String seq) {
		log.info("GalleryServiceImpl getImgDetail 이미지 상세내용  : {}",seq);
		return dao.getImgDetail(seq);
	}

	@Override
	public boolean deleteImg(String seq) {
		log.info("GalleryServiceImpl deleteImg 이미지 삭제  : {}",seq);
		return dao.deleteImg(seq);
	}

}
