package com.winder.dao;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import com.winder.configure.mybatis.mapper.MyBaseMapper;

import tk.mybatis.mapper.common.Mapper;
import tk.mybatis.mapper.common.MySqlMapper;

/**
 * MyBatis基础dao层
 * 此类不能被MyBatisMapperScanner扫描到
 * @author user
 * @param <T>
 */
public interface BaseMapper<T> extends Mapper<T>,MySqlMapper<T>,MyBaseMapper<T> {
	/**
	 * 传入sql查询结果 - mine
	 * @param sql
	 * @return
	 */
	@Select("${value}")
	public List<Map<String, Object>> selectBySql(String sql);
	/**
	 * 传入sql修改数据
	 * @param sql
	 * @return
	 */
	@Update("${value}")
	public int updateBySql(String sql);
	/**
	 * 传入sql插入数据
	 * @param sql
	 * @return
	 */
	@Insert("${value}")
	public int insertBySql(String sql);
}
