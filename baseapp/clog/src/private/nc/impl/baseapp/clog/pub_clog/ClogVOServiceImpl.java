package nc.impl.baseapp.clog.pub_clog;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
 
import org.apache.commons.lang3.ArrayUtils;
import org.apache.commons.lang3.StringUtils;

import nc.bs.framework.common.InvocationInfoProxy;
import nc.bs.vo.AggClogVO;
import nc.bs.vo.ClogVO;
import nc.codeplatform.framework.service.ServiceSupport;
import nc.itf.baseapp.clog.pub_clog.IClogVOService;
import nc.vo.pub.BusinessException;
import nc.vo.pub.SuperVO;
import nc.vo.pub.VOStatus;
import nc.vo.pub.lang.UFDateTime;
import pers.bc.utils.pub.PubEnvUtil;
import pers.bc.utils.yonyou.YonYouUtilbc;

public class ClogVOServiceImpl extends ServiceSupport implements IClogVOService
{
    
    @Override
    public AggClogVO[] listAggClogVOByPk(String... pks) throws BusinessException
    {
        return listAggClogVOByPk(false, pks);
    }
    
    @Override
    public AggClogVO[] listAggClogVOByPk(boolean blazyLoad, String... pks) throws BusinessException
    {
        return dao.listByPksWithOrder(AggClogVO.class, pks, blazyLoad);
    }
    
    @Override
    public AggClogVO findAggClogVOByPk(String pk) throws BusinessException
    {
        return dao.findByPk(AggClogVO.class, pk, false);
    }
    
    @Override
    public AggClogVO[] listAggClogVOByCondition(String condition) throws BusinessException
    {
        return listAggClogVOByCondition(condition, new String[]{"pk_log"});
    }
    
    @Override
    public AggClogVO[] listAggClogVOByCondition(String condition, String[] orderPath) throws BusinessException
    {
        return dao.listByCondition(AggClogVO.class, condition, false, false, orderPath);
    }
    
    @Override
    public ClogVO[] listClogVOByPk(String... pks) throws BusinessException
    {
        // ClogVO[] clogVOS = dao.listByPk(ClogVO.class, pks, true);
        // List<SuperVO> vos = GeneralVOUtil.sort(Arrays.asList(clogVOS), "ts", Boolean.TRUE);
        // return vos.toArray(new ClogVO[0]);
        
        return dao.listByPk(ClogVO.class, pks, true);
    }
    
    @Override
    public ClogVO findClogVOByPk(String pk) throws BusinessException
    {
        return dao.findByPk(ClogVO.class, pk, true);
    }
    
    @Override
    public ClogVO[] listClogVOByCondition(String condition) throws BusinessException
    {
        return listClogVOByCondition(condition, new String[]{"pk_log"});
    }
    
    @Override
    public ClogVO[] listClogVOByCondition(String condition, String[] orderPath) throws BusinessException
    {
        return dao.listByCondition(ClogVO.class, condition, false, false, orderPath);
    }
    
    @Override
    public String[] listClogVOPkByCond(String condition) throws BusinessException
    {
        return listClogVOPkByCond(condition, new String[]{"ts", "pk_log"});
    }
    
    @Override
    public String[] listClogVOPkByCond(String condition, String[] orderPath) throws BusinessException
    {
        if (StringUtils.isEmpty(condition)) condition = " 1 = 1 ";
        List vos = (List) YonYouUtilbc.getBaseDAO().retrieveByClause(ClogVO.class, condition, " startime desc ");
        if (PubEnvUtil.getSize(vos) < 1) return null;
        List<String> pks = new ArrayList<String>();
        for (int i = 0; i < PubEnvUtil.getSize(vos); i++)
            pks.add(((nc.bs.vo.ClogVO) vos.get(i)).getPk_log());
        
        return pks.toArray(new String[0]);
    }
    
    @Override
    public void initDefaultData(ClogVO vo)
    {
        if (vo.getAttributeValue("pk_group") == null)
        {
            vo.setAttributeValue("pk_group", InvocationInfoProxy.getInstance().getGroupId());
        }
    }
    
    @Override
    public AggClogVO preAddAggClogVO(AggClogVO vo, Map<String, Object> userJson) throws BusinessException
    {
        
        getMainVO(vo).setStatus(VOStatus.NEW);
        initDefaultData((ClogVO) getMainVO(vo));
        
        // 下面这段要判断是否是树表界面插件
        Map<String, String> data = userJson != null && userJson.get("data") != null ? (Map<String, String>) userJson.get("data") : null;
        if (data != null && data.size() > 0)
        {
            String parentKey = data.get("parentKey");
            String parentPk = data.get("parentPk");
            getMainVO(vo).setAttributeValue(parentKey, parentPk);
        }
        
        return vo;
    }
    
    @Override
    public AggClogVO preAddAggClogVO(Map<String, Object> userJson) throws BusinessException
    {
        AggClogVO result = null;
        
        ClogVO mainvo = new ClogVO();
        // 设置默认值
        initDefaultData(mainvo);
        AggClogVO aggvo = new AggClogVO();
        aggvo.setParent(mainvo);
        result = aggvo;
        return preAddAggClogVO(result, userJson);
    }
    
    @Override
    public AggClogVO preEditAggClogVO(String pk) throws BusinessException
    {
        return dao.findByPk(AggClogVO.class, pk, false);
    }
    
    @Override
    public AggClogVO copyAggClogVO(String pk) throws BusinessException
    {
        
        AggClogVO vo = dao.findByPk(AggClogVO.class, pk, false);
        
        getMainVO(vo).setPrimaryKey(null);
        getMainVO(vo).setStatus(VOStatus.NEW);
        
        getMainVO(vo).setAttributeValue("srcbilltype", null);
        getMainVO(vo).setAttributeValue("srcbillid", null);
        
        getMainVO(vo).setAttributeValue("code", null);
        getMainVO(vo).setAttributeValue("", null);
        getMainVO(vo).setAttributeValue("name", null);
        getMainVO(vo).setAttributeValue("", new UFDateTime(InvocationInfoProxy.getInstance().getBizDateTime()));
        
        return vo;
    }
    
    @Override
    public AggClogVO[] saveAggClogVO(AggClogVO vo) throws BusinessException
    {
        String pk = getVOPrimaryKey(vo);
        setDefaultVal(vo);
        if (StringUtils.isEmpty(pk))
        {
            return dao.insert(vo); // 插入
        }
        else
        {
            return dao.update(vo); // 更新
        }
    }
    
    /**
     * 保存前设置审计信息
     * @param vos
     */
    private void setAuditInfo(AggClogVO... vos) throws BusinessException
    {
        if (ArrayUtils.isNotEmpty(vos))
        {
            UFDateTime now = new UFDateTime();
            for (AggClogVO vo : vos)
            {
                String pk = getVOPrimaryKey(vo);
                if (StringUtils.isEmpty(pk))
                {
                    // 设置创建人创建时间
                    getMainVO(vo).setAttributeValue("creator", InvocationInfoProxy.getInstance().getUserId());
                    getMainVO(vo).setAttributeValue("creationtime", now);
                    getMainVO(vo).setAttributeValue("", now);
                }
                else
                {
                    // 设置修改人修改时间
                    getMainVO(vo).setAttributeValue("modifier", InvocationInfoProxy.getInstance().getUserId());
                    getMainVO(vo).setAttributeValue("modifiedtime", now);
                    getMainVO(vo).setAttributeValue("", now);
                }
            }
        }
    }
    
    /**
     * 保存前处理一些默认值
     * @param vos
     */
    private void setDefaultVal(AggClogVO... vos) throws BusinessException
    {
        setAuditInfo(vos);
        // 其他默认值处理
    }
    
    // 给单表（行编辑表）单独适配
    @Override
    public AggClogVO[] saveAggClogVO(AggClogVO[] vos) throws BusinessException
    {
        setDefaultVal(vos);
        return dao.save(vos, true);
    }
    
    @Override
    public AggClogVO[] deleteAggClogVOs(Map<String, String> tsMap) throws BusinessException
    {
        AggClogVO[] vos = dao.listByPk(AggClogVO.class, tsMap.keySet().toArray(new String[0]));
        validate(vos, tsMap);
        dao.delete(vos, true);
        return vos;
    }
    
    // 校验 包括ts校验
    private void validate(AggClogVO[] vos, Map<String, String> tsMap) throws BusinessException
    {
        
        Boolean isPass = true;
        if (ArrayUtils.isEmpty(vos))
        {
            isPass = false;
        }
        for (AggClogVO vo : vos)
        {
            
            SuperVO mainvo = vo.getParentVO();
            UFDateTime ts = (UFDateTime) mainvo.getAttributeValue("ts");
            if (!StringUtils.equals(tsMap.get(mainvo.getPrimaryKey()), ts.toString()))
            {
                isPass = false;
                break;
            }
        }
        if (!isPass)
        {
            throw new BusinessException("您操作的数据已被他人修改或删除，请刷新后重试！");
        }
    }
    
    @Override
    public <T> T[] loadTreeData(Class<T> clazz, Map<String, Object> userJson) throws BusinessException
    {
        return dao.listByCondition(clazz, " dr = 0 ", false);
    }
    
}
