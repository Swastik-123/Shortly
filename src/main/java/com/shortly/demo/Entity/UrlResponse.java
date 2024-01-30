package com.shortly.demo.Entity;

import java.time.LocalDateTime;

//enter the generated output into this class. you can skip this class
// and directly send the data to the view page.
public class UrlResponse {
	private String oirginalUrl;
	private String shortUrl;
	private LocalDateTime expireDate;
	public UrlResponse() {
		super();
		// TODO Auto-generated constructor stub
	}
	public UrlResponse(String oirginalUrl, String shortUrl, LocalDateTime expireDate) {
		this.oirginalUrl = oirginalUrl;
		this.shortUrl = shortUrl;
		this.expireDate = expireDate;
	}
	
	public String getOirginalUrl() {
		return oirginalUrl;
	}
	
	public void setOirginalUrl(String oirginalUrl) {
		this.oirginalUrl = oirginalUrl;
	}
	
	public String getShortUrl() {
		return shortUrl;
	}
	
	public void setShortUrl(String shortUrl) {
		this.shortUrl = shortUrl;
	}
	public LocalDateTime getExpireDate() {
		return expireDate;
	}
	
	public void setExpireDate(LocalDateTime expireDate) {
		this.expireDate = expireDate;
	}
	
}
