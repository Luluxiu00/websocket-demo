package com.example.demo.service.impl;

import org.springframework.stereotype.Service;

import com.example.demo.pojo.MessageEntity;
import com.example.demo.service.BaseService;
import com.example.demo.service.IMessageService;

@Service
public class MessageService extends BaseService<MessageEntity> implements IMessageService{

}
