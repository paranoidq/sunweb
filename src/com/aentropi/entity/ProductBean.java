package com.aentropi.entity;

import java.util.Arrays;
import java.util.List;


/**
 * product information bean
 * @author paranoid
 * @since 2014.07.24
 */
public class ProductBean {
	
	private int id;
	private String name;
	// brief description
	private String desc;
	// brief images
	private String images;
	
	
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
	public String getDesc() {
		return desc;
	}
	public void setDesc(String desc) {
		this.desc = desc;
	}
	public String getImages() {
		return images;
	}
	public void setImages(String images) {
		this.images = images;
	}
	
	
	// format images according to splitter
	public static final String SPLITTER = ",";
	public static List<String> formatImages(String images) {
		if (images == null || images.isEmpty()) {
			return null;
		}
		return Arrays.asList(images.split(SPLITTER));
	}
	
}
