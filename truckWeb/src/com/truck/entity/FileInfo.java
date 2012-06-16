package com.truck.entity;

import org.nutz.dao.entity.annotation.Column;
import org.nutz.dao.entity.annotation.Id;
import org.nutz.dao.entity.annotation.Table;

@Table("t_fileinfo")
public class FileInfo {

	/**
	 * 唯一id 
	 */
	@Column
	@Id
	private int id;
	/**
	 * 上传文件的原名
	 */
	@Column
	private String srcFileName;
	/**
	 * 存放文件的路径
	 */
	@Column
	private String path;
	
	@Column
	private int proId;
	
	@Column
	private int fsort;
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getSrcFileName() {
		return srcFileName;
	}
	public void setSrcFileName(String srcFileName) {
		this.srcFileName = srcFileName;
	}
	public String getPath() {
		return path;
	}
	public void setPath(String path) {
		this.path = path;
	}
	public int getProId() {
		return proId;
	}
	public void setProId(int proId) {
		this.proId = proId;
	}
	public int getFsort() {
		return fsort;
	}
	public void setFsort(int fsort) {
		this.fsort = fsort;
	}
	
}
