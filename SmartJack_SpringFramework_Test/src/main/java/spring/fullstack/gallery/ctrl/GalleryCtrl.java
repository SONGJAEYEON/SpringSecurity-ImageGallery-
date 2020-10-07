package spring.fullstack.gallery.ctrl;

import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.UUID;

import javax.annotation.Resource;
import javax.inject.Inject;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import com.google.gson.JsonArray;
import com.google.gson.JsonObject;

import spring.fullstack.gallery.dtos.GalleryDto;
import spring.fullstack.gallery.model.GalleryIService;

@Controller
public class GalleryCtrl {
	
	 private static final Logger logger = LoggerFactory.getLogger(GalleryCtrl.class);
	 
	 @Autowired
	 private GalleryIService service;

	 private String UPLOAD_PATH="C:\\nobrand\\workspace_spring\\SmartJack_SpringFramework_Test\\src\\main\\webapp\\resources\\upload";
	
	@RequestMapping(value="/imgUploadForm.do",method =RequestMethod.GET)
	public String imgUpload() { //뭔가 아규먼트가 좀 있어야 하지 않겠니,,?
		return "gallery/imgUpload";
	}
	
	@RequestMapping(value="/imgDetail.do",method =RequestMethod.GET)
	public String imgDetail(@RequestParam("seq") String seq,Model model) { //뭔가 아규먼트가 좀 있어야 하지 않겠니,,?
		System.out.println("■■■■■■■■■■■■■■"+seq+"■■■■■■■■■■■■■");
		GalleryDto imgInfo=service.getImgDetail(seq);
		model.addAttribute("imgInfo", imgInfo);
		return "gallery/imgDetail";
	}
	
	
	@ResponseBody
	@RequestMapping(value="/getAllImage.do",method =RequestMethod.POST)
	public  JsonArray getAllImage() { //뭔가 아규먼트가 좀 있어야 하지 않겠니,,?
		logger.info("GalleryCtrl getAllImage 모든 이미지 가져오기 ");
		List<GalleryDto> imgList=service.getAllImg();
		JsonArray jList=makeJson(imgList);
		return jList;
	}
	@SuppressWarnings("unchecked")
	private JsonArray makeJson(List<GalleryDto> lists) {
		JsonArray jLists = new JsonArray(); // [{"":""},{"":""},{"":""}]
		JsonObject jdto = new JsonObject(); // {"":[{"":""},{"":""},{"":""}...]}
		for (GalleryDto dto : lists) {
			jdto = new JsonObject();
			jdto.addProperty("image_seq", dto.getImage_seq());
			jdto.addProperty("id",dto.getId());
			jdto.addProperty("image_name",dto.getImage_name());
			jdto.addProperty("stored_name",dto.getStored_name());
			jdto.addProperty("file_size",dto.getFile_size());
			jdto.addProperty("upload_time",dto.getUpload_time());
			jLists.add(jdto); // [{"":""},{"":""},{"":""}] 형태
		}
		return jLists;
	}
	
	@RequestMapping(value = "/fileupload.do",method = RequestMethod.POST)
	public String upload(MultipartFile uploadfile,Authentication user ,Model model){
	    logger.info("upload() POST 호출");
	    logger.info("파일 이름: {}", uploadfile.getOriginalFilename());
	    logger.info("파일 크기: {}", uploadfile.getSize());
	    String user_id = ((UserDetails)user.getPrincipal()).getUsername();
	    logger.info("아이디: {}", user_id);
	    
	    String saveFileName = saveFile(uploadfile);
	    String size=uploadfile.getSize()+"";
	    GalleryDto dto = new GalleryDto("0", user_id,uploadfile.getOriginalFilename() ,saveFileName, size, "");
	    logger.info("이미지 업로드 성공 : {} ",service.upLoadImg(dto));
	    
	    if(saveFileName !=null){ // 파일 저장 성공
	        model.addAttribute("result", saveFileName);
	    } else { // 파일 저장 실패
	        model.addAttribute("result","fail");
	    }
	    
	    return "gallery/imgUpload";
	    

	}

	private String saveFile(MultipartFile file){
	    // 파일 이름 변경
	    UUID uuid = UUID.randomUUID();
	    String saveName = uuid + "_" + file.getOriginalFilename();

	    logger.info("saveName: {}",saveName);

	    // 저장할 File 객체를 생성(껍데기 파일)ㄴ
	    File saveFile = new File(UPLOAD_PATH,saveName); // 저장할 폴더 이름, 저장할 파일 이름

	    try {
	        file.transferTo(saveFile); // 업로드 파일에 saveFile이라는 껍데기 입힘
	    } catch (IOException e) {
	        e.printStackTrace();
	        return null;
	    }

	    return saveName;
	}

}
