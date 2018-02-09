package com.winder.dao.mapper;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Update;

import com.winder.bean.entity.Tag;
import com.winder.dao.BaseMapper;

public interface TagMapper extends BaseMapper<Tag> {
	/**
	 * 添加标签记录忽略主键冲突
	 * @param map
	 * @return
	 */
	@Insert("INSERT IGNORE INTO `tag` (`tag_nm`, `tag_num`, `add_time`) VALUES (#{tagNm}, #{tagNum}, #{addTime})")
	public int insertIgnore (Tag tag);
	/**
	 * 修改标签文章数量
	 * 加或者减
	 * @param tag
	 * @return
	 */
	@Update("UPDATE `tag` SET `tag_num` = `tag_num` + #{tagNum} WHERE `tag_nm` = #{tagNm}")
	public int updateTagNumber(Tag tag);
}
