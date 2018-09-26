package com.example.demo.service;


import java.util.ArrayList;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.exception.BusinessException;
import com.example.demo.pojo.DataEntity;
import com.example.demo.util.DataUtils;
import com.example.demo.util.MyMapper;
import com.example.demo.util.StringUtils;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;


/**
 *
 * @param <T>
 */
@Service
public abstract class BaseService<T> {
    @Autowired
    protected MyMapper<T> mapper;

    /**
     * 保存
     */
    public T save(T entity) {
    	if (entity instanceof DataEntity) {
    		DataEntity de = (DataEntity) entity;
    		if(de.getCreateTime() == null){
    			de.setCreateTime(new Date());
    		}
    		if(de.getUpdateTime() == null){
    			de.setUpdateTime(new Date());
    		}

    		//验证参数
    		if (StringUtils.isNotNull(de.getCreateById())) {
    			de.setUpdateById(de.getCreateById());
    		} else {
    			throw new BusinessException("创建人ID不能为NULL或空！请检查createById字段。");
    		}
    	}
        mapper.insertUseGeneratedKeys(entity);
        return entity;
    }
    
    public T addEntity(T entity) {
        mapper.insert(entity);
        return entity;
    }
    
    /**
     * 更新
     */
    public T update(T entity) {
    	if (entity instanceof DataEntity) {
    		DataEntity de = (DataEntity) entity;
            de.setUpdateTime(new Date());
    		//验证参数
    		if (!StringUtils.isNotNull(de.getUpdateById())) {
    			throw new BusinessException("修改人ID不能为NULL或空！请检查updateById字段。");
    		}
    	}
        mapper.updateByPrimaryKey(entity);
        return entity;
    }

    /**
     * 根据主键只更新不空NULL的字段
     * @param entity
     * @return
     */
    public int updateByPrimaryKeySelective(T entity) {
        if (entity instanceof DataEntity) {
            DataEntity de = (DataEntity) entity;
            de.setUpdateTime(new Date());
            if (!StringUtils.isNotNull(de.getUpdateById())) {
                throw new BusinessException("修改人id不能为NULL或空!请检查updateById字段");
            }
        }
        return mapper.updateByPrimaryKeySelective(entity);
    }
    
    /**
     * 根据实体类ID删除
     */
    public int delById(Long id) {
        return mapper.deleteByPrimaryKey(id);
    }

    public int delByIds(Set<Long> idSet) {
        return mapper.deleteByIds(DataUtils.getSeparatorData(idSet, DataUtils.Sepatator.SEP_COMMA));
    }
    
    /**
     * 根据实体类T(泛型)<b>id</b>查询数据
     * @param id
     * @return 对象
     */
    public T selectById(Long id) {
        return mapper.selectByPrimaryKey(id);
    }

    public List<T> selectByEntity(T entity) {
        return mapper.select(entity);
    }

    public List<T> selectByIds(List<Long> idList) {
        if (idList.size() > 0) {
            String ids = DataUtils.getSeparatorData(new HashSet<Long>(idList), DataUtils.Sepatator.SEP_COMMA);
            return mapper.selectByIds(ids);
        }
        return new ArrayList<T>();
    }

    public List<T> selectAll(T entity) {
        return mapper.select(entity);
    }

    public PageInfo<T> selectPage(T entity, PageInfo<T> pageInfo) {
        PageHelper.startPage(pageInfo.getPageNum(),pageInfo.getPageSize());
        List<T> list  =  mapper.select(entity);
        return new PageInfo<T>(list);
    }
    
}
