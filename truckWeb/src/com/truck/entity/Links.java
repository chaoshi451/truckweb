package com.truck.entity;

import java.io.Serializable;

import org.nutz.dao.entity.annotation.Column;
import org.nutz.dao.entity.annotation.Id;
import org.nutz.dao.entity.annotation.Table;

/**
 * ”—«È¡¥Ω”
 * @author shiChao
 *
 */
@Table("t_links")
public class Links implements Serializable {

	private static final long serialVersionUID = 1L;

	@Column
	@Id
	private int id;

	@Column
	private String linkName;

	@Column
	private String linkUrl;

	@Column
	private int flag;
	
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getLinkName() {
		return linkName;
	}

	public void setLinkName(String linkName) {
		this.linkName = linkName;
	}

	public String getLinkUrl() {
		return linkUrl;
	}

	public void setLinkUrl(String linkUrl) {
		this.linkUrl = linkUrl;
	}

	public int getFlag() {
		return flag;
	}

	public void setFlag(int flag) {
		this.flag = flag;
	}

}
