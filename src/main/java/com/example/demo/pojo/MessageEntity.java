package com.example.demo.pojo;

import javax.persistence.Table;

@Table(name="message")
public class MessageEntity extends DataEntity {

	/**
	 * 
	 */
	private static final long serialVersionUID = -1735543393141300612L;
	
	private String content;

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}
	
	
}
