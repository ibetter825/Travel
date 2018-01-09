package com.travel.configure.mybatis.provider;

import java.util.Set;
import org.apache.ibatis.mapping.MappedStatement;
import tk.mybatis.mapper.entity.EntityColumn;
import tk.mybatis.mapper.mapperhelper.EntityHelper;
import tk.mybatis.mapper.mapperhelper.MapperHelper;
import tk.mybatis.mapper.mapperhelper.MapperTemplate;
import tk.mybatis.mapper.mapperhelper.SqlHelper;
import tk.mybatis.mapper.provider.base.BaseInsertProvider;

/**
 * 自定义插入sql生成工具
 * @author user
 *
 */
public class MyInsertProvider extends MapperTemplate {
	private Class<?> mClass;
    private MapperHelper mHelper;
	public MyInsertProvider(Class<?> mapperClass, MapperHelper mapperHelper) {
		super(mapperClass, mapperHelper);
		this.mClass = mapperClass;
		this.mHelper = mapperHelper;
	}
	
	/**
     * 插入不为null的字段,这段代码比较复杂，这里举个例子
     * CountryU生成的insertOrUpdateSelective方法结构如下：
     * <pre>
     &lt;bind name="countryname_bind" value='@java.util.UUID@randomUUID().toString().replace("-", "")'/&gt;
     INSERT INTO country_u
     &lt;trim prefix="(" suffix=")" suffixOverrides=","&gt;
     &lt;if test="id != null"&gt;id,&lt;/if&gt;
     countryname,
     &lt;if test="countrycode != null"&gt;countrycode,&lt;/if&gt;
     &lt;/trim&gt;
     VALUES
     &lt;trim prefix="(" suffix=")" suffixOverrides=","&gt;
     &lt;if test="id != null"&gt;#{id,javaType=java.lang.Integer},&lt;/if&gt;
     &lt;if test="countryname != null"&gt;#{countryname,javaType=java.lang.String},&lt;/if&gt;
     &lt;if test="countryname == null"&gt;#{countryname_bind,javaType=java.lang.String},&lt;/if&gt;
     &lt;if test="countrycode != null"&gt;#{countrycode,javaType=java.lang.String},&lt;/if&gt;
     &lt;/trim&gt;
     </pre>
     * 这段代码可以注意对countryname的处理
     *
     * @param ms
     * @return
     */
    public String insertOrUpdateSelective(MappedStatement ms) {
        Class<?> entityClass = getEntityClass(ms);
        StringBuilder sql = new StringBuilder();
        //获取全部列
        Set<EntityColumn> columnList = EntityHelper.getColumns(entityClass);
        String selective = new BaseInsertProvider(mClass, mHelper).insertSelective(ms);
        sql.append(selective);
        //添加新的代码
        sql.append("<trim prefix=\"ON DUPLICATE KEY UPDATE\" suffix=\"\" suffixOverrides=\",\">");
        for (EntityColumn column : columnList) {
            if (!column.isId() && column.isUpdatable())
            	sql.append(SqlHelper.getIfNotNull(null, column, column.getColumnEqualsHolder(null) + ",", isNotEmpty()));
        }
        sql.append("</trim>");
        return sql.toString();
    }
    
    /**
     * 插入，主键id，自增
     *
     * @param ms
     */
    public String insertSelectiveUseGeneratedKeys(MappedStatement ms) {
    	String sql = new BaseInsertProvider(mClass, mHelper).insertSelective(ms);
    	return sql;
    }
}
