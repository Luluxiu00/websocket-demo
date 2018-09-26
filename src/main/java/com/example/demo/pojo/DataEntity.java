package com.example.demo.pojo;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Id;

import org.springframework.format.annotation.DateTimeFormat;

import com.alibaba.fastjson.annotation.JSONField;

public class DataEntity implements Serializable {

	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Id
	private Long id;
	
	/**
	 * 备注
	 */
	private String remark;

	/**
	 * <fg>删除标记   '0': 未删除; '1': 被删除
	 */
	@Column(name = "del_flag")
	private String delFlag;

	/**
	 * 创建人员
	 */
	@Column(name = "create_by_id")
	private Long createById;

	/**
	 * 创建时间
	 */
	@JSONField (format="yyyy-MM-dd HH:mm:ss")  
	@DateTimeFormat(pattern="yyyy-MM-dd HH:mm:ss")
	@Column(name = "create_time")
	private Date createTime;

	/**
	 * 修改人员
	 */
	@Column(name = "update_by_id")
	private Long updateById;

	/**
	 * 修改时间
	 */
	@JSONField (format="yyyy-MM-dd HH:mm:ss")  
	@DateTimeFormat(pattern="yyyy-MM-dd HH:mm:ss")
	@Column(name = "update_time")
	private Date updateTime;

	/**
	 * 获取备注
	 *
	 * @return remark - 备注
	 */
	public String getRemark() {
		return remark;
	}

	/**
	 * 设置备注
	 *
	 * @param remark 备注
	 */
	public void setRemark(String remark) {
		this.remark = remark == null ? null : remark.trim();
	}

	/**
	 * 获取<fg>删除标记   '0': 未删除; '1': 被删除
	 *
	 * @return del_flag - <fg>删除标记   '0': 未删除; '1': 被删除
	 */
	public String getDelFlag() {
		return delFlag;
	}

	/**
	 * 设置<fg>删除标记   '0': 未删除; '1': 被删除
	 *
	 * @param delFlag <fg>删除标记   '0': 未删除; '1': 被删除
	 */
	public void setDelFlag(String delFlag) {
		this.delFlag = delFlag == null ? null : delFlag.trim();
	}

	/**
	 * 获取创建人员
	 *
	 * @return create_by_id - 创建人员
	 */
	public Long getCreateById() {
		return createById;
	}

	/**
	 * 设置创建人员
	 *
	 * @param createById 创建人员
	 */
	public void setCreateById(Long createById) {
		this.createById = createById;
	}

	/**
	 * 获取创建时间
	 *
	 * @return create_time - 创建时间
	 */
	public Date getCreateTime() {
		return createTime;
	}

	/**
	 * 设置创建时间
	 *
	 * @param createTime 创建时间
	 */
	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}

	/**
	 * 获取修改人员
	 *
	 * @return update_by_id - 修改人员
	 */
	public Long getUpdateById() {
		return updateById;
	}

	/**
	 * 设置修改人员
	 *
	 * @param updateById 修改人员
	 */
	public void setUpdateById(Long updateById) {
		this.updateById = updateById;
	}

	/**
	 * 获取修改时间
	 *
	 * @return update_time - 修改时间
	 */
	public Date getUpdateTime() {
		return updateTime;
	}

	/**
	 * 设置修改时间
	 *
	 * @param updateTime 修改时间
	 */
	public void setUpdateTime(Date updateTime) {
		this.updateTime = updateTime;
	}
	
}
