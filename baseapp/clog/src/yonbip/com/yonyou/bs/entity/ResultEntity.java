package com.yonyou.bs.entity;

import java.util.LinkedHashMap;
import java.util.Map;

import javax.xml.bind.annotation.XmlRootElement;
 
import pers.bc.utils.pub.CollectionUtil;
import pers.bc.utils.pub.JudgeAssertUtil;
import pers.bc.utils.pub.StringUtil;

@XmlRootElement
public class ResultEntity
{
    private Map<String, Object> result = new LinkedHashMap<String, Object>();
    
    public static ResultEntity success()
    {
        // 相当于调用下面的map 然后把值存map里面 。
        ResultEntity entity = new ResultEntity();
        entity.getMap().put("statusCode", 200);
        entity.getMap().put("message", "操作成功!!!");
        entity.getMap().put("thwMsg", "操作成功，没有异常!!!");
        entity.getMap().put("code", 200);
        entity.getMap().put("status", 200);
        entity.getMap().put("msg", "接口响应成功");
        
        return entity;
    }
    
    public static ResultEntity failed(String message)
    { 
        message = JudgeAssertUtil.processMessage(message).replace("nc.vo.pub.BusinessException:", "");
        // message = JudgeAssertUtil.processMsg(message);
        
        // 相当于调用下面的map 然后把值存map里面 。
        ResultEntity entity = new ResultEntity();
        entity.getMap().put("statusCode", 200);
        entity.getMap().put("message", message);
        entity.getMap().put("code", 200);
        entity.getMap().put("status", 201);
        entity.getMap().put("msg", "接口响应成功");
        entity.getMap().put("thwMsg", message);
        
        return entity;
    }
    
    public void putThwMsg(Object value){ this.getMap().put("thwMsg", value); }
    
    // 用来存值，对象，集合
    public void put(String key, Object value){ this.getMap().put(key, value); }
    
    public Map<String, Object> getMap()  {return result;}
    
    public void setMap(Map<String, Object> map){this.result = map;}
    
    @Override
    public String toString()
    {
        try
        {
            // String mapStr = StringUtil.toString(this.getMap()).replace(":", "");
            // JSONObject js = (JSONObject) JSONObject.parse(JsonUtil.compressJson(mapStr));
            // return "nc.bs.vo.ResultEntity = " + CollectionUtil.transObj2Josn(this.getMap()).replace("\r\n",
            // "");
            // return JSON.toJSONString(this.getMap());
            return CollectionUtil.transObj2Josn(this.getMap())
                    .replace("\r\n", "").replace("\n", "")
                    .replace("\"[", "[").replace("]\"", "]");
        }
        catch (Throwable e)
        {
            System.err.println(e.getMessage());
            return StringUtil.toString(this.getMap());
        }
        // return super.toString();
    }
}
