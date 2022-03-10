package com.springbook.biz.board;

import java.util.Date;

import org.springframework.web.multipart.MultipartFile;

public class BoardVO {
	private int art_id;
	private int party_id; 
	private String art_title; 
	private String user_id;
	private String art_content;
	private MultipartFile art_img;
	private String art_img_path;
	private int art_view_cnt; 
	private Date art_reg_date;
	private Date art_mod_date;
	private boolean art_available; 
	
	
	
	public int getArt_id() {
		return art_id;
	}
	public void setArt_id(int art_id) {
		this.art_id = art_id;
	}
	public int getParty_id() {
		return party_id;
	}
	public void setParty_id(int party_id) {
		this.party_id = party_id;
	}
	public String getArt_title() {
		return art_title;
	}
	public void setArt_title(String art_title) {
		this.art_title = art_title;
	}
	
	public String getUser_id() {
			return user_id;
	}
	
    public void setUser_id(String user_id) {
			this.user_id = user_id;	
		
	}
	public String getArt_content() {
		return art_content;
	}
	public void setArt_content(String art_content) {
		this.art_content = art_content;
	}
	public String getArt_img_path() {
		return art_img_path;
	}
	public void setArt_img_path(String art_img_path) {
		this.art_img_path = art_img_path;
	}
	public int getArt_view_cnt() {
		return art_view_cnt;
	}
	public void setArt_view_cnt(int art_view_cnt) {
		this.art_view_cnt = art_view_cnt;
	}
	public Date getArt_reg_date() {
		return art_reg_date;
	}
	public void setArt_reg_date(Date art_reg_date) {
		this.art_reg_date = art_reg_date;
	}
	public Date getArt_mod_date() {
		return art_mod_date;
	}
	public void setArt_mod_date(Date art_mod_date) {
		this.art_mod_date = art_mod_date;
	}
	public boolean isArt_available() {
		return art_available;
	}
	public void setArt_available(boolean art_available) {
		this.art_available = art_available;
	}
	public MultipartFile getArt_img() {
		return art_img;
	}
	public void setArt_img(MultipartFile art_img) {
		this.art_img = art_img;
	}
	
	
	
	

}
