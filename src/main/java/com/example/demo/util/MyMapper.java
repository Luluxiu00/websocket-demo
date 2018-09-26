package com.example.demo.util;

import org.springframework.stereotype.Component;

import tk.mybatis.mapper.common.IdsMapper;
import tk.mybatis.mapper.common.Mapper;
import tk.mybatis.mapper.common.MySqlMapper;

/**
 * Created by yongjin on 2016-11-8.
 */
@Component
public interface MyMapper<T>  extends Mapper<T>,MySqlMapper<T>, IdsMapper<T> {
}
