package com.gallery.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import com.gallery.entities.Gallery;


@RestController
@RequestMapping("/")
public class HomeController {

	@Autowired
	private RestTemplate restTemplate;
	
	@Autowired
	private Environment env;
	
	@RequestMapping("/")
	public String home() {
		
		return "Hello from Gallery Service running at port: "+ env.getProperty("local.server.port");
	}
	
	@RequestMapping("/{id}")
	public Gallery getGallery(@PathVariable final int id) {
		Gallery gallery  = new Gallery();
		gallery.setId(id);
		
		List<Object> images = restTemplate.getForObject("http://image-service/images/", List.class);
		gallery.setImages(images);
		
		return gallery;
		
		
	}
	
	//only accessed by users with users with 'admin' role
	
	@RequestMapping("/admin")
	public String homeAdmin() {
		return "This is the admin area Gallery service running at port: " + env.getProperty("local.server.port");
	}
	
}
