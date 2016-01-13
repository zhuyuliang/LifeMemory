package com.zyl.melife.bean;

import org.litepal.crud.DataSupport;

/**
 * everyface的标签实体类
 * @author 育梁
 */
public class EveryfaceTagBean extends DataSupport {

	private int id;
	private String name;
	private String createdtime;
	private String desc;
	private String defaultimg;
	//提醒相关
	private String warn;
	private String warnrate;
	private String iswarn;

	public EveryfaceTagBean() {
	}

	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getCreatedtime() {
		return createdtime;
	}
	public void setCreatedtime(String createdtime) {
		this.createdtime = createdtime;
	}
	public String getDesc() {
		return desc;
	}
	public void setDesc(String desc) {
		this.desc = desc;
	}
	public String getWarn() {
		return warn;
	}
	public void setWarn(String warn) {
		this.warn = warn;
	}
	public String getWarnrate() {
		return warnrate;
	}
	public void setWarnrate(String warnrate) {
		this.warnrate = warnrate;
	}
	public String getIswarn() {
		return iswarn;
	}
	public void setIswarn(String iswarn) {
		this.iswarn = iswarn;
	}
	public String getDefaultimg() {
		return defaultimg;
	}
	public void setDefaultimg(String defaultimg) {
		this.defaultimg = defaultimg;
	}
}
