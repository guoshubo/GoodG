package com.GoodG.app.model;

public class Mp3Info {
	//����id
	private long id;
	
	//���ֱ��⣬����
	private  String title;
	
	//����
	private  String artist;
	
	//ʱ��
	private long duration;
	
	//�ļ���С
	private long size;
	
	//�ļ�·��
	private String url;
	
	public void setId(long id) {
		this.id = id;
	}
	
	public void setTitle(String title) {
		this.title = title;
	}
	
	public void setArtist(String artist) {
		this.artist = artist;
	}
	
	public void setDuration(long duration) {
		this.duration = duration;
	}
	
	public void setSize(long size) {
		this.size = size;
	}
	
	public void setUrl(String url) {
		this.url = url;
	}
	
	public long getId() {
		return id;
	}
	
	public String getTitle() {
		return title;
	}
	
	public String getArtist() {
		return artist;
	}
	
	public long getDuration() {
		return duration;
	}
	
	public long getSize() {
		return size;
	}
	
	public String getUrl() {
		return url;
	}

}
