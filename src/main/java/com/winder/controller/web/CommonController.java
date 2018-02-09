package com.winder.controller.web;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.winder.bean.constant.CommonConstant;
import com.winder.bean.model.WangModel;
import com.winder.configure.properties.CustomPropertiesConfig;
import com.winder.utils.FileUtil;
import com.winder.utils.StringUtil;

/**
 * 首页控制器
 * @author user
 *
 */
@RestController
@RequestMapping("/common")
public class CommonController extends WebBaseController {
	
	/**
	 * http://blog.csdn.net/kilua_way/article/details/54601195 SpringBoot jar上传文件问题 
	 * @param file 上传的文件
	 * @param from 上传的来源 eg:"editor"
	 * @return
	 */
	@RequestMapping("/upload/{from}.json")
	public Object upload(List<MultipartFile> file, @PathVariable("from") String from){
		WangModel model = new WangModel();
		//上传文件存放的位置，没使用springboot的默认位置，存放在磁盘的另外一个地方
		String uploadRootPath = CustomPropertiesConfig.getProperty(CommonConstant.UPLOAD_ROOT_PATH);
		int uploadIndex = uploadRootPath.lastIndexOf("/");
		//本次上传文件存放的文件夹
		String fileWholePath = null;
		InputStream in = null;
		FileOutputStream out = null;
		try {
			File folder = null;
			String savedFileName = null;
			List<String> returnPaths = new ArrayList<String>();
			String returnFilePath = null;
			for (MultipartFile f : file) {
				fileWholePath = uploadRootPath;
				in = f.getInputStream();
				if(FileUtil.isImage(in)){//上传的文件是否是图片
					//图片保存的路径 uploadRootPath + uploadImagePath
					String uploadImagePath = CustomPropertiesConfig.getProperty(CommonConstant.UPLOAD_IMAGE_PATH);
					fileWholePath += uploadImagePath;
				}
				folder = new File(uploadRootPath);
				if(!folder.exists()) folder.mkdirs();
				savedFileName = StringUtil.uuid(false) + "." + FileUtil.getExtensionName(f.getOriginalFilename());
				FileUtil.writeFile(f, fileWholePath, savedFileName);
				if(CommonConstant.UPLOAD_FROM_EDITOR.equalsIgnoreCase(from)){
					//返回的地址为 upload/***.jpg
					returnFilePath = fileWholePath.substring(uploadIndex) + "/" + savedFileName;
					returnPaths.add(returnFilePath);
				}
			}
			model.setData(returnPaths.toArray(new String[returnPaths.size()]));
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
		return model;
	}
	
	/**
	 * 删除文件
	 * @param path 文件路径
	 * @return
	 */
	@RequestMapping("/file/del.json")
	public void delete(String path){
		String uploadRootPath = CustomPropertiesConfig.getProperty(CommonConstant.UPLOAD_ROOT_PATH);
		int uploadIndex = uploadRootPath.lastIndexOf("/");
		File file = new File(uploadRootPath.substring(0, uploadIndex) + "/" + path);
		if(file.exists())
			file.delete();
	}
}
