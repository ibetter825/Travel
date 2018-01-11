package com.travel.controller.web;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;

import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.travel.bean.constant.CommonConstant;
import com.travel.bean.model.WangModel;
import com.travel.configure.properties.CustomPropertiesConfig;
import com.travel.utils.FileUtil;
import com.travel.utils.StringUtil;

/**
 * 首页控制器
 * @author user
 *
 */
@RestController
@RequestMapping("/common")
public class CommonController extends WebBaseController {
	
	//
	/**
	 * http://blog.csdn.net/kilua_way/article/details/54601195 SpringBoot jar上传文件问题 
	 * @param file 上传的文件
	 * @param from 上传的来源 eg:"editor"
	 * @return
	 */
	@RequestMapping("/upload/{from}.json")
	public Object upload(MultipartFile file, @PathVariable("from") String from){
		//上传文件存放的位置，没使用springboot的默认位置，存放在磁盘的另外一个地方
		String uploadRootPath = CustomPropertiesConfig.getProperty(CommonConstant.UPLOAD_ROOT_PATH);
		int uploadIndex = uploadRootPath.lastIndexOf("/");
		//本次上传文件存放的文件夹
		String fileWholePath = uploadRootPath;
		InputStream in = null;
		FileOutputStream out = null;
		try {
			in = file.getInputStream();
			if(FileUtil.isImage(in)){//上传的文件是否是图片
				//图片保存的路径 uploadRootPath + uploadImagePath
				String uploadImagePath = CustomPropertiesConfig.getProperty(CommonConstant.UPLOAD_IMAGE_PATH);
				fileWholePath += uploadImagePath;
			}
			File folder = new File(uploadRootPath);
			if(!folder.exists()) folder.mkdirs();
			String savedFileName = StringUtil.uuid(false) + "." + FileUtil.getExtensionName(file.getOriginalFilename());
			FileUtil.writeFile(file, fileWholePath, savedFileName);
			if(CommonConstant.UPLOAD_FROM_EDITOR.equalsIgnoreCase(from)){
				//返回的地址为 upload/***.jpg
				String returnFilePath = fileWholePath.substring(uploadIndex) + "/" + savedFileName;
				return new WangModel().setData(new String[]{returnFilePath});
			}else
				return null;
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			if(in != null){
				try {
					in.close();
					if(out != null) out.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
		return null;
	}
}
