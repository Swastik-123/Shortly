package com.shortly.demo.Controller;

import java.net.URI;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import com.shortly.demo.Entity.UrlResponse;
import com.shortly.demo.Entity.Url;
import com.shortly.demo.Entity.UrlDto;
import com.shortly.demo.Services.UrlServices;

@Controller
public class ShortlyViewController {

	@Autowired
	private UrlServices urlServices;
	
	// http://localhost:8080/shortly
	
	private String shortLink;
	
	@GetMapping("/shortly")
	public String getUrlFromViewPage(Model model) {
		UrlDto urlDto = new UrlDto();
		model.addAttribute("url_link",urlDto);
		return "ShortlyView";
	}
	
	@PostMapping("/shortly/url")
	public String generateShortLink(Model model,@ModelAttribute("url_link") UrlDto urlDto) {
		
		Url urlToRet = urlServices.generateShortLink(urlDto);
		UrlResponse response = new UrlResponse();//you can skip UrlResponse class and directly return from urlToRet object .
		if(urlToRet!=null) {
			response.setOirginalUrl(urlToRet.getOriginalLink());
			response.setShortUrl(urlToRet.getShortLink());
			response.setExpireDate(urlToRet.getExpirationDate());
			System.out.println("%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%");
			System.out.println(response.getShortUrl());
			this.shortLink = response.getShortUrl();
			System.out.println("%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%");
			model.addAttribute("urlData",response);
		}else {
			return "";//here we return a error page
		}
		return "ShortlyView";
	}
	
	@GetMapping("/{shortLink}")
	public ResponseEntity<?> redirectToOriginalPage(){
		
		Url urlToRet = urlServices.getEncodeUrl(shortLink);
		
		return ResponseEntity.status(HttpStatus.FOUND)
					.location(URI.create(String.valueOf(urlToRet.getOriginalLink())))
					.build();
	}
	
	
}
