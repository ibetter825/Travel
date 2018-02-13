package com.winder.dao.mapper;

import java.util.Map;
import org.apache.ibatis.annotations.Param;
import com.winder.bean.dto.UserDto;
import com.winder.bean.entity.User;
import com.winder.dao.BaseMapper;

public interface UserMapper extends BaseMapper<User> {
	/**
	 * 根据条件查询用户
	 * @param rq
	 * @return
	 */
	public UserDto selectUserWithInfoByRq(@Param("rq") Map<String, Object> rq);
}
