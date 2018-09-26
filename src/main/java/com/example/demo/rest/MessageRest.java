package com.example.demo.rest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.example.demo.pojo.MessageEntity;
import com.example.demo.service.impl.MessageService;

@Controller
@RequestMapping("/message")
public class MessageRest {

	@Autowired
	private MessageService messageService;

	@GetMapping("/")
	String test() { 
		return "index";
	}
	
	@ResponseBody
	@RequestMapping(value = "/save", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE)
	public String save(@RequestBody MessageEntity entity) {

		entity.setCreateById(1l);
		messageService.save(entity);
		return "保存成功";
	}
}
