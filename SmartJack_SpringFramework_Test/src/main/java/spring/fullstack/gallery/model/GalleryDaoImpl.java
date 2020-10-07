package spring.fullstack.gallery.model;

import java.util.List;

import org.mybatis.spring.SqlSessionTemplate;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import spring.fullstack.gallery.dtos.GalleryDto;

@Repository
public class GalleryDaoImpl implements GalleryIDao {

private Logger log = LoggerFactory.getLogger(this.getClass());
	
	@Autowired
	private SqlSessionTemplate sqlSession;
	
	private final String NS="spring.fullstack.gallery.";
	
	@Override
	public boolean upLoadImg(GalleryDto dto) {
		log.info("GalleryDaoImpl upLoadImg 이미지 올리기 : {}",dto);
		return (sqlSession.insert(NS+"upLoadImg",dto)>0)?true:false;
	}

	@Override
	public List<GalleryDto> getAllImg() {
		log.info("GalleryDaoImpl getAllImg 모든 이미지 가져오기");
		return sqlSession.selectList(NS+"getAllImg");
	}

	@Override
	public GalleryDto getImgDetail(String seq) {
		log.info("GalleryDaoImpl getImgDetail 이미지 상세내용 (이미지번호): {}",seq);
		return sqlSession.selectOne(NS+"getImgDetail",seq);
	}

	@Override
	public boolean deleteImg(String seq) {
		log.info("GalleryDaoImpl deleteImg 이미지 삭제 (이미지번호) : {}",seq);
		return sqlSession.selectOne(NS+"deleteImg",seq);
	}

}
