
package nc.itf.baseapp.clog.pub_clog;

import java.util.Map;

import nc.vo.pub.BusinessException;

import nc.bs.vo.AggClogVO;
import nc.bs.vo.ClogVO;

public interface  IClogVOService{

	/**
	 * API调用日志的AGGVO查询操作
	 * 根据主键条件查询Agg数组
	 * @param pk主键 
	 * @return 结果数组
	 */
	public AggClogVO[] listAggClogVOByPk(String... pks) throws BusinessException;
	
	/**
	 * API调用日志的AGGVO查询操作
	 * 根据主键条件查询Agg数组
	 * @param pk主键
	 * @return 结果数组
	 */
	public AggClogVO[] listAggClogVOByPk(boolean blazyLoad,String... pks) throws BusinessException;
	
	/**
	 * API调用日志的AGGVO查询操作
	 * 根据主键条件查询Agg对象
	 * @param pk 主键
	 * @return 结果对象
	 */
	public  AggClogVO findAggClogVOByPk(String pk) throws BusinessException;
	
	/**
	 * API调用日志的AGGVO查询操作
	 * 根据条件字符串查询Agg数组
	 * @param condition 主键
	 * @return 结果数组
	 */
	public  AggClogVO[] listAggClogVOByCondition(String condition) throws BusinessException;
	
	/**
	 * API调用日志的AGGVO查询操作
	 * 根据条件字符串查询Agg数组
	 * @param condition 条件
	 * @param orderPath 排序集合
	 * @return 结果数组
	 */
	public  AggClogVO[] listAggClogVOByCondition(String condition,String[] orderPath) throws BusinessException;
	
	/**
	 * API调用日志的主表VO查询操作
	 * 根据主键条件查询Agg数组
	 * @param pk主键
	 * @return 结果数组
	 */
	public ClogVO[] listClogVOByPk(String...pks) throws BusinessException;
	
	/**
	 * API调用日志的主表VO查询操作
	 * 根据主键条件查询Agg对象
	 * @param pk 主键
	 * @return 结果对象
	 */
	public  ClogVO findClogVOByPk(String pk) throws BusinessException;
	
	/**
	 * API调用日志的主表VO查询操作
	 * 根据条件字符串查询Agg数组
	 * @param condition 主键
	 * @return 结果数组
	 */
	public  ClogVO[] listClogVOByCondition(String condition) throws BusinessException;
	/**
	 * API调用日志的主表VO查询操作
	 * 根据条件字符串查询Agg数组
	 * @param condition 主键
	 * @return 结果数组
	 */
	public  ClogVO[] listClogVOByCondition(String condition,String[] orderPath) throws BusinessException;

	/**
	 * API调用日志的主表pk查询操作
	 * 根据条件字符串查询pk数组数组
	 * @param condition 查询方案+
	 * @return 结果数组
	 */
	public String[] listClogVOPkByCond(String condition) throws BusinessException;
	
	/**
	 * API调用日志的主表pk查询操作
	 * 根据条件字符串查询pk数组数组
	 * @param condition 查询方案+
	 * @return 结果数组
	 */
	public String[] listClogVOPkByCond(String condition,String[] orderPath) throws BusinessException;
	/**
	 * 给主实体vo设置默认值
	 * @param vo
	 */
	public void initDefaultData(ClogVO vo);
	
	
	/**
	 * 预新增操作API调用日志数据
	 * @param userJson  新增时需要的扩展参数对象
	 */
	public AggClogVO preAddAggClogVO(Map<String,Object> userJson) throws BusinessException;
	
	public AggClogVO preAddAggClogVO(AggClogVO vo,Map<String,Object> userJson) throws BusinessException;
	 /**
	 * 预编辑操作API调用日志数据
	 * @param userJson  新增时需要的扩展参数对象
	 */
	public AggClogVO preEditAggClogVO(String pk) throws BusinessException;
	
	 /**
	 * 复制操作API调用日志数据
	 * 
	 */
	public AggClogVO copyAggClogVO(String pk) throws BusinessException;
	/**
	 * 保存操作API调用日志数据
	 * @param vos 保存对象
	 * @return @
	 */
	public AggClogVO[] saveAggClogVO(AggClogVO vo) throws BusinessException;

	public AggClogVO[] saveAggClogVO(AggClogVO[] vos) throws BusinessException;
	
	
	/**
	 * 删除操作API调用日志数据
	 * @param vos 删除对象
	 * @return @
	 */
	public AggClogVO[] deleteAggClogVOs(Map<String,String> tsMap) throws BusinessException;
	
	/**
	 * 加载树类型数据API调用日志
	 * @param vos 对象
	 * @return @
	 */
	public <T> T[] loadTreeData(Class<T> clazz,Map<String,Object> userJson) throws BusinessException;

}