
package nccloud.api.baseapp.clogmanage.pub_clog;

import java.lang.reflect.Field;

import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import java.lang.StringBuilder;
import java.util.List;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Iterator;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;
import java.util.HashMap;

import org.json.JSONString;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;

import nccloud.ws.rest.resource.AbstractNCCRestResource;
import pers.bc.utils.pub.PubEnvUtil;
import nccloud.api.rest.utils.ResultMessageUtil;
import nccloud.commons.lang.ArrayUtils;
import nccloud.commons.lang.StringUtils;
import nc.bs.framework.common.NCLocator;
import nc.vo.pub.BusinessException;
import nc.vo.pub.SuperVO;
import nc.vo.pub.VOStatus;
import nc.vo.pub.lang.UFBoolean;
import nc.vo.pub.lang.UFDate;
import nc.vo.pub.lang.UFDateTime;
import nc.vo.pub.lang.UFDouble;
import nc.bs.vo.ClogVO;
import nc.bs.vo.AggClogVO;
import nc.itf.baseapp.clog.pub_clog.IClogVOService;

@Path("baseapp/clogmanage/pub_clog")
public class Pub_clogManageResources extends AbstractNCCRestResource
{ 
    
    private Map<String, String> fieldsNameAndType = new HashMap<>();
    
    private Map<String, Map<String, String>> bodyFieldsNameAndTypes = new HashMap<>();
    
    @Override
    public String getModule()
    {
        return "baseapp";
    }
    
    @POST
    @Path("/addAggClogVO")
    @Consumes({"application/json"})
    @Produces({"application/json"})
    public JSONString addAggClogVO(JSONString json)
    {
        return dealJson(json, "addAggClogVO");
    }
    
    @POST
    @Path("/deleteAggClogVO")
    @Consumes({"application/json"})
    @Produces({"application/json"})
    public JSONString deleteAggClogVO(JSONString json)
    {
        return dealJson(json, "deleteAggClogVO");
    }
    
    @POST
    @Path("/updateAggClogVO")
    @Consumes({"application/json"})
    @Produces({"application/json"})
    public JSONString updateAggClogVO(JSONString json)
    {
        return dealJson(json, "updateAggClogVO");
    }
    
    @POST
    @Path("/queryAggClogVO")
    @Consumes({"application/json"})
    @Produces({"application/json"})
    public JSONString queryAggClogVO(JSONString json)
    {
        return dealJson(json, "queryAggClogVO");
    }
    
    private JSONString dealJson(JSONString json, String methodName)
    {
        if (json == null)
        {
            return ResultMessageUtil.exceptionToJSON(new NullPointerException("输入参数不能为空！"));
        }
        JSONObject obj = JSON.parseObject(json.toJSONString());
        if (methodName.indexOf("add") >= 0)
        {
            StringBuilder builder = requiredFieldsValidate(obj);
            if (builder.length() > 0)
            {
                String addError = "新增时必输项存在空值，为空的必输项有：" + builder.deleteCharAt(builder.length() - 1).toString();
                return ResultMessageUtil.exceptionToJSON(new NullPointerException(addError));
            }
            else
            {
                // 调用接口新增
                try
                {
                    initFields();
                }
                catch (ClassNotFoundException e1)
                {
                    return ResultMessageUtil.exceptionToJSON(e1);
                }
                AggClogVO vo = buildVOs(obj);
                AggClogVO[] result = null;
                try
                {
                    result = getService().saveAggClogVO(vo);
                }
                catch (BusinessException e)
                {
                    return ResultMessageUtil.exceptionToJSON(e);
                }
                JSONObject returnJson = new JSONObject();
                returnJson.put("result", result);
                return ResultMessageUtil.toJSON(returnJson);
            }
        }
        else if (methodName.indexOf("delete") >= 0)
        {
            String entityPk = obj.getString("pk_log");
            if (StringUtils.isEmpty(entityPk))
            {
                return ResultMessageUtil.exceptionToJSON(new NullPointerException("删除时主键为必输项，不能为空！"));
            }
            // 删除需要区分全局、集团和业务单元
            String pk_org = obj.getString("pk_org");
            // 如果没有传pk_org, 默认是全局
            if (StringUtils.isEmpty(pk_org))
            {
                pk_org = "GLOBLE00000000000000";
            }
            // 调用接口删除
            JSONObject returnJson = new JSONObject();
            Map<String, String> tsMap = new HashMap<>();
            ClogVO[] vos = null;
            try
            {
                vos = getService().listClogVOByPk(entityPk);
            }
            catch (BusinessException e)
            {
                ResultMessageUtil.exceptionToJSON(e);
            }
            if (ArrayUtils.isNotEmpty(vos))
            {
                tsMap.put(vos[0].getAttributeValue("pk_log").toString(), vos[0].getTs().toString());
            }
            AggClogVO[] result = null;
            try
            {
                result = getService().deleteAggClogVOs(tsMap);
            }
            catch (BusinessException e)
            {
                ResultMessageUtil.exceptionToJSON(e);
            }
            returnJson.put("result", result);
            return ResultMessageUtil.toJSON(returnJson);
        }
        else if (methodName.indexOf("update") >= 0)
        {
            JSONObject object = (JSONObject) obj.get("pub_clog");
            String entityPk = object.getString("pk_log");
            if (StringUtils.isEmpty(entityPk))
            {
                return ResultMessageUtil.exceptionToJSON(new NullPointerException("修改时主键为必输项，不能为空！"));
            }
            StringBuilder builder = requiredFieldsValidate(obj);
            if (builder.length() > 0)
            {
                String updateError = "修改时必输项存在空值，为空的必输项有：" + builder.deleteCharAt(builder.length() - 1).toString();
                return ResultMessageUtil.exceptionToJSON(new NullPointerException(updateError));
            }
            else
            {
                // 调用接口修改
                try
                {
                    initFields();
                }
                catch (ClassNotFoundException e1)
                {
                    return ResultMessageUtil.exceptionToJSON(e1);
                }
                AggClogVO vo = buildVOs(obj);
                AggClogVO[] result = null;
                JSONObject returnJson = new JSONObject();
                try
                {
                    result = getService().saveAggClogVO(vo);
                }
                catch (BusinessException e)
                {
                    return ResultMessageUtil.exceptionToJSON(e);
                }
                returnJson.put("result", result);
                return ResultMessageUtil.toJSON(returnJson);
            }
        }
        else if (methodName.indexOf("query") >= 0)
        {
            // 查询时需要接收业务单元作为参数吗？
            StringBuilder sqlBuilder = new StringBuilder(" isnull(dr, 0) = 0");
            Set<Entry<String, Object>> entrySet = obj.entrySet();
            Iterator<Entry<String, Object>> iterator = entrySet.iterator();
            while (iterator.hasNext())
            {
                Entry<String, Object> entry = iterator.next();
                if (entry.getValue() != null)
                {
                    sqlBuilder.append(" and " + entry.getKey() + "='" + entry.getValue() + "'");
                }
            }
            AggClogVO[] results = null;
            try
            {
                results = getService().listAggClogVOByCondition(sqlBuilder.toString());
            }
            catch (BusinessException e)
            {
                return ResultMessageUtil.exceptionToJSON(e);
            }
            JSONObject returnJson = new JSONObject();
            returnJson.put("result", results);
            return ResultMessageUtil.toJSON(returnJson);
        }
        return null;
    }
    
    private StringBuilder requiredFieldsValidate(JSONObject obj)
    {
        StringBuilder builder = new StringBuilder();
        List<String> notNullFields = getRequiredFields(obj);
        JSONObject object = (JSONObject) obj.get("pub_clog");
        for (String notNullField : notNullFields)
        {
            Object val = object.get(notNullField);
            if (val == null)
            {
                builder.append(notNullField + ",");
            }
        }
        return builder;
    }
    
    private AggClogVO buildVOs(JSONObject obj)
    {
        if (obj.get("pub_clog") == null)
        {
            throw new NullPointerException("传入参数中不存在主实体数据！");
        }
        AggClogVO aggVO = new AggClogVO();
        SuperVO headVO = buildHeadVO(obj);
        aggVO.setParentVO(headVO);
        return aggVO;
    }
    
    /**
     * 构造表头VO
     * @param obj
     * @return
     */
    private ClogVO buildHeadVO(JSONObject obj)
    {
        JSONObject headJson = (JSONObject) obj.get("pub_clog");
        ClogVO vo = new ClogVO();
        Set<Entry<String, Object>> entrySet = headJson.entrySet();
        Iterator<Entry<String, Object>> iterator = entrySet.iterator();
        while (iterator.hasNext())
        {
            Entry<String, Object> next = iterator.next();
            Object value = getValueByName(next.getKey(), next.getValue(), fieldsNameAndType);
            vo.setAttributeValue(next.getKey(), value);
        }
        if (vo.getPrimaryKey() != null)
        {
            vo.setStatus(VOStatus.UPDATED);
        }
        else
        {
            vo.setStatus(VOStatus.NEW);
        }
        return vo;
    }
    
    /**
     * 构造表体VOs
     * @param obj
     * @return
     */
    private Map<String, SuperVO[]> buildBodyVOs(JSONObject obj, boolean isAdd)
    {
        // 子实体编码，只有继承了AbstractBill类的AggVO可以获取
        Map<String, SuperVO[]> bodyCodeAndVOs = new HashMap<>();
        return bodyCodeAndVOs;
    }
    
    private List<String> getRequiredFields(JSONObject obj)
    {
        List<String> notNullFields = new ArrayList<String>();
        return notNullFields;
    }
    
    /**
     * 初始化字段名称和类型
     * 
     * @throws ClassNotFoundException
     */
    private void initFields() throws ClassNotFoundException
    {
        Class<?> headEntity = Class.forName("nc.bs.vo.ClogVO");
        Field[] declaredFields = headEntity.getDeclaredFields();
        if (ArrayUtils.isNotEmpty(declaredFields))
        {
            for (int i = 0; i < PubEnvUtil.getSize(declaredFields); i++)
            {
                Field field = declaredFields[i];
                if (field != null) fieldsNameAndType.put(field.getName(), field.getType().getName());
            }
        }
        // 存在子实体的情况
    }
    
    /**
     * 获取字段类型
     * 
     * @return
     * @throws ClassNotFoundException
     */
    private Object getValueByName(String key, Object value, Map<String, String> fieldsMap)
    {
        if (value == null)
        {
            return null;
        }
        String type = fieldsMap.get(key);
        switch (type)
        {
            case "java.lang.String" :
                return value.toString();
            case "java.lang.Integer" :
                return Integer.valueOf(value.toString());
            case "nc.vo.pub.lang.UFBoolean" :
                return new UFBoolean(value.toString());
            case "nc.vo.pub.lang.UFDate" :
                return new UFDate(value.toString());
            case "nc.vo.pub.lang.UFDateTime" :
                return new UFDateTime(value.toString());
            case "nc.vo.pub.lang.UFDouble" :
                return new UFDouble(value.toString());
            default :
                return value;
        }
    }
    
    private IClogVOService getService()
    {
        return NCLocator.getInstance().lookup(IClogVOService.class);
    }
    
}
