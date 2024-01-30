package com.shortly.demo.Reposotary;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.shortly.demo.Entity.Url;

@Repository
public interface UrlReposotory extends JpaRepository<Url,Long>{
	public Url findByShortLink(String shortLink);
}

