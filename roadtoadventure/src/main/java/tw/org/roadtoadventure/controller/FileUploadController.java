package tw.org.roadtoadventure.controller;

import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;
import java.util.Base64;

import javax.imageio.ImageIO;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import net.sf.json.JSONObject;

@Controller
@RequestMapping("/File")
public class FileUploadController {
	
	@RequestMapping(value = "/UploadImg" , method = RequestMethod.POST)
	public @ResponseBody String uploadImg (@RequestParam MultipartFile[] uploadImage){
		JSONObject o = new JSONObject();
		try{
			if(uploadImage.length>0&&uploadImage[0].getSize()>0){//檢查檔案是否上傳
				MultipartFile importFile=uploadImage[0];
				String name[] = importFile.getOriginalFilename().split("\\.");
				String exName = name[name.length-1].toUpperCase();
				if (exName.equals("JPG")||exName.equals("JPEG")||exName.equals("PNG")){
					BufferedImage img = ImageIO.read( importFile.getInputStream());
					ByteArrayOutputStream bos = new ByteArrayOutputStream();
					
					byte[] imageBytes =null;
					String imageString ="";
					switch (exName){
					case "JPG" :
						ImageIO.write(img, "jpg", bos);
						imageBytes =  bos.toByteArray();
						bos.close();
						imageString ="data:image/jpeg;base64,"+ Base64.getEncoder().encodeToString(imageBytes);
						break;
					case "JPEG":
						ImageIO.write(img, "jpg", bos);
						imageBytes = bos.toByteArray();
						bos.close();
						imageString ="data:image/jpeg;base64,"+ Base64.getEncoder().encodeToString(imageBytes);
						break;
					case "PNG":
						ImageIO.write(img, "png", bos);
						imageBytes = bos.toByteArray();
						bos.close();
						imageString ="data:png/png;base64,"+ Base64.getEncoder().encodeToString(imageBytes);
						break;
					}
					o.put("success", "1");
					o.put("image", imageString);
					return o.toString();
				}else{
					o.put("success", "0");
					o.put("message", "圖片格式錯誤，請選擇JPG或PNG格式。");
					return o.toString();
				}

			}else{
				o.put("success", "0");
				o.put("message", "未選擇封面圖片");
				return o.toString();
			}
		}catch(IllegalArgumentException ex){
			o.put("success", "0");
			o.put("message", "圖片格式錯誤，請選擇JPG或PNG格式。");
			return o.toString();
		}catch(Exception e){
			e.printStackTrace();
			o.put("success", "0");
			o.put("message", "未選擇封面圖片");
			return o.toString();
		}
	}

}
