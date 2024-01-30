package com.shortly.demo.Services;

import java.nio.charset.StandardCharsets;
import java.time.LocalDateTime;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.google.common.hash.Hashing;
import com.shortly.demo.Entity.Url;
import com.shortly.demo.Entity.UrlDto;
import com.shortly.demo.Reposotary.UrlReposotory;

import io.micrometer.common.util.StringUtils;

@Service
public class UrlServices {
	
	@Autowired
	private UrlReposotory urlReposotory;
	
	//generate Url from urlDto (urlDto is a class which is take the input from user.)
	//Here i pass the input data to the URL class and put it into the database. 
	public Url generateShortLink(UrlDto urlDto) {
		if(StringUtils.isNotEmpty(urlDto.getUrl())) {
			String encodeUrl = encodeUrl(urlDto.getUrl());
			Url urlToParsist = new Url();
			urlToParsist.setCreationDate(LocalDateTime.now());
			urlToParsist.setOriginalLink(urlDto.getUrl());
			urlToParsist.setShortLink(encodeUrl);
			urlToParsist.setExpirationDate(getExpeirationDate(urlDto.getExpirationDate(),urlToParsist.getCreationDate()));
			Url urlToRet = persistShortLink(urlToParsist);//save the url
			if(urlToRet!=null) {
				return urlToRet;
			}
			return null;
		}
		return null;
	}

	//encode the given url and compress into a small url 
	private String encodeUrl(String url) {
		String encodeUrl = "";
		LocalDateTime time = LocalDateTime.now();
		encodeUrl = Hashing.murmur3_32()
				.hashString(url.concat(time.toString()),StandardCharsets.UTF_8).toString();
		return encodeUrl;
	}
	
	private LocalDateTime getExpeirationDate(String expireDate,LocalDateTime creationDate) {
		if(StringUtils.isBlank(expireDate)) {
			return creationDate.plusMinutes(15);
		}
		LocalDateTime ex = LocalDateTime.parse(expireDate);
		return ex;
	}

	//Put the encoded URL into the database.
	private Url persistShortLink(Url urlToParsist) {
		Url url = urlReposotory.save(urlToParsist);
		return url;
	}
	
	//get encoded URL from database.
	public Url getEncodeUrl(String url) {
		Url getEncodeUrl = urlReposotory.findByShortLink(url);
		return getEncodeUrl;
	}
	
	public void deleteShortLink(Url url) {
		urlReposotory.delete(url);
	}
	
	
}
