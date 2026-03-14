package nc.bs.util;

import java.util.HashMap;
import java.util.Map;

import javax.xml.bind.annotation.XmlRootElement;

import pers.bc.utils.pub.JsonUtil;
import pers.bc.utils.pub.JudgeAssertUtil;
import pers.bc.utils.pub.StringUtil;

@XmlRootElement
public class ResultEntity
{
    public ResultEntity()   {   }
    
    private Map<String, Object> map = new HashMap<String, Object>();
      
    public static ResultEntity success()
    {
        // 相当于调用下面的map 然后把值存map里面 。
        ResultEntity entity = new ResultEntity();
        entity.getMap().put("statusCode", 200);
        entity.getMap().put("message", "接口响应成功");
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
        entity.getMap().put("message", "接口响应成功");
        entity.getMap().put("code", 200);
        entity.getMap().put("status", 201);
        entity.getMap().put("msg", message);
        entity.getMap().put("thwMsg", message);
        
        return entity;
    }
    
    // 用来存值，对象，集合
    public ResultEntity put(String key, Object value)
    {
        this.getMap().put(key, value);
        return this;
    }
    
    public Map<String, Object> getMap()  {  return map;  }
    
    public void setMap(Map<String, Object> map)  { this.map = map;  }
    
    @Override
    public String toString()
    {
        try
        {
            // return CollectionUtil.transObj2Josn(this.getMap()).replace("\r\n", "").replace("\n",
            // "").replace("\"[", "[").replace("]\"",
            // "]");
            return "nc.bs.util.ResultEntity.ResultEntity = " + JsonUtil.compressJson(JsonUtil.toJSONString(this.getMap()));
            
        }
        catch (Throwable e)
        {
            System.err.println(e.getMessage());
            return StringUtil.toString(this.getMap());
        }
        // return super.toString();
    }
}
