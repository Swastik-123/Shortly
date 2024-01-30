package com.shortly.demo.Entity;

import java.time.LocalDateTime;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.Lob;

@Entity
public class Url {
	@Id
	@GeneratedValue
	private long id;
	
	@Lob
	private String shortLink;
	private String originalLink;
	private LocalDateTime creationDate;
	private LocalDateTime expirationDate;
	public Url() {
		super();
		// TODO Auto-generated constructor stub
	}
	public Url(long id, String shortLink, String originalLink, LocalDateTime creationTime, LocalDateTime expirationDate) {
		super();
		this.id = id;
		this.shortLink = shortLink;
		this.originalLink = originalLink;
		this.creationDate = creationTime;
		this.expirationDate = expirationDate;
	}
	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}
	public String getShortLink() {
		return shortLink;
	}
	public void setShortLink(String shortLink) {
		this.shortLink = shortLink;
	}
	public String getOriginalLink() {
		return originalLink;
	}
	public void setOriginalLink(String originalLink) {
		this.originalLink = originalLink;
	}
	public LocalDateTime getCreationDate() {
		return creationDate;
	}
	public void setCreationDate(LocalDateTime localDateTime) {
		this.creationDate = localDateTime;
	}
	public LocalDateTime getExpirationDate() {
		return expirationDate;
	}
	public void setExpirationDate(LocalDateTime expirationDate) {
		this.expirationDate = expirationDate;
	}
	
	@Override
	public String toString() {
		return "Url [id=" + id + ", shortLink=" + shortLink + ", originalLink=" + originalLink + ", creationTime="
				+ creationDate + ", expirationDate=" + expirationDate + "]";
	}
	
	
}
