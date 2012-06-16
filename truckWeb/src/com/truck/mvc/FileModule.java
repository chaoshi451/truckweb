package com.truck.mvc;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import javax.servlet.http.HttpServletRequest;

import org.nutz.dao.Cnd;
import org.nutz.ioc.annotation.InjectName;
import org.nutz.mvc.annotation.AdaptBy;
import org.nutz.mvc.annotation.At;
import org.nutz.mvc.annotation.Fail;
import org.nutz.mvc.annotation.Ok;
import org.nutz.mvc.annotation.Param;
import org.nutz.mvc.upload.FieldMeta;
import org.nutz.mvc.upload.TempFile;
import org.nutz.mvc.upload.UploadAdaptor;

import com.truck.entity.FileInfo;
import com.truck.service.AttributeService;

@At("/f")
@AdaptBy(type=UploadAdaptor.class, args={"${app.root}/WEB-INF/tmp" })
@InjectName("fileModule")
@Fail("json")
public class FileModule {
	
	private AttributeService attributeService;
	
	private static final int BUFFER_SIZE = 16 * 1024;	//缓冲区大小
	
	@At
	public String upload(@Param("file") File tempFile) {
		return "upload 1 file = " + tempFile.getAbsolutePath() + " size=" +tempFile.length();
	}
	
	@At
	@Ok("json")
	@SuppressWarnings("unchecked")
	public String upload_mu(@Param("..") Map<String,Object> allFields, HttpServletRequest request) {
		// 文件储存地址
		String contextPath = request.getSession().getServletContext().getRealPath("");
		int proId = Integer.parseInt(allFields.get("proId").toString());
		for (Entry<String, Object> entry : allFields.entrySet()) {
			if (entry.getValue() instanceof List<?>) {
				for (TempFile file : (List<TempFile>) entry.getValue()) {
					addFileData(file, contextPath, proId);
				}
			} else if ( entry.getValue() instanceof TempFile) {
				addFileData((TempFile) entry.getValue(), contextPath, proId);
			}
		}
		return "ok";
	}
	
	private void addFileData(TempFile file, String contextPath, int proId){
		FieldMeta fieldMeta = file.getMeta();
		FileInfo fileInfo = new FileInfo();
		fileInfo.setSrcFileName(fieldMeta.getFileLocalName());
		String imageFileName = new Date().getTime() + "-"
				+ (fieldMeta.getFileLocalName()); // 重新生成的文件名称
		File t = new File(contextPath + File.separator + "UploadFiles");
		if (!t.exists())
			t.mkdirs(); // 创建“UploadImages”文件夹
		File imageFile = new File(t.getAbsolutePath() + File.separator
				+ imageFileName);
		copy(file.getFile(), imageFile);
		// 删除临时文件
		file.getFile().delete();
		// 保存文件信息到fileInfo表
		fileInfo.setPath("UploadFiles/" + imageFile.getName());
		fileInfo.setProId(proId);
		attributeService.dao().insert(fileInfo);
	}
	/**
	 * 复制文件
	 * @param src
	 * @param dst
	 */
	private static void copy(File src, File dst) {
		try {
			InputStream in = null;
			OutputStream out = null;
			try {
				in = new BufferedInputStream(new FileInputStream(src),
						BUFFER_SIZE);
				out = new BufferedOutputStream(new FileOutputStream(dst),
						BUFFER_SIZE);
				byte[] buffer = new byte[BUFFER_SIZE];
				while (in.read(buffer) > 0) {
					out.write(buffer);
				}
			} finally {
				if (null != in) {
					in.close();
				}
				if (null != out) {
					out.close();
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@At
	@Ok("json")
	public void delFileInfo(@Param("proId") int proId, @Param("fileIds") int[] fileIds) {
		List<FileInfo> fileInfos = attributeService.dao().query(FileInfo.class,
				Cnd.where("proId", "=", proId), null);
		for (int fileId : fileIds) {
			boolean isExists = false;
			for (FileInfo fileInfo : fileInfos) {
				if (fileInfo.getId() == fileId) {
					isExists = true;
					break;
				}
			}
			if (!isExists) {
				attributeService.dao().delete(FileInfo.class, fileId);
			}
		}
	}
	
	public AttributeService getAttributeService() {
		return attributeService;
	}

	public void setAttributeService(AttributeService attributeService) {
		this.attributeService = attributeService;
	}
	
}
