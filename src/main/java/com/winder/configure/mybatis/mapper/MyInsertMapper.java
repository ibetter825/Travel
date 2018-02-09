package com.winder.configure.mybatis.mapper;

import org.apache.ibatis.annotations.InsertProvider;
import org.apache.ibatis.annotations.Options;

import com.winder.configure.mybatis.provider.MyInsertProvider;

public interface MyInsertMapper<T> {
    /**
     * 保存或修改一个实体，null的属性不会保存，会使用数据库默认值
     * 暂时只适用于MYSQL
     * http://www.2cto.com/database/201308/234179.html
     * @param record
     * @return
     */
    @InsertProvider(type = MyInsertProvider.class, method = "dynamicSQL")
    int insertOrUpdateSelective(T record);
    
    /**
     * 插入数据，限制为实体包含`id`属性并且必须为自增列，实体配置的主键策略无效
     *
     * @param record
     * @return
     */
    @Options(useGeneratedKeys = true, keyProperty = "id")
    @InsertProvider(type = MyInsertProvider.class, method = "dynamicSQL")
    int insertSelectiveUseGeneratedKeys(T record);
}
