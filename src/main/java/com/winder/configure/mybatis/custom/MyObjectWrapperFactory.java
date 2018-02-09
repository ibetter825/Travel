package com.winder.configure.mybatis.custom;

import java.util.Map;

import org.apache.ibatis.reflection.MetaObject;
import org.apache.ibatis.reflection.wrapper.ObjectWrapper;
import org.apache.ibatis.reflection.wrapper.ObjectWrapperFactory;

/**
 * 暂时没用
 * Map类型结果key值自动转驼峰命名
 * @author user
 *
 */
public class MyObjectWrapperFactory implements ObjectWrapperFactory {

	@Override
	public boolean hasWrapperFor(Object object) {
		return object != null && object instanceof Map;
	}

	@SuppressWarnings("unchecked")
	@Override
	public ObjectWrapper getWrapperFor(MetaObject metaObject, Object object) {
		return new MyMapWrapper(metaObject, (Map<String, Object>) object);
	}

}
