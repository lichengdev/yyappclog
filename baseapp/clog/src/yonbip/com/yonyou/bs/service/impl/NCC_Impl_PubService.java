package com.yonyou.bs.service.impl;

import java.util.HashMap;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.yonyou.bs.entity.ClogEntity;
import com.yonyou.bs.mapper.MyLogMapper;
import com.yonyou.bs.mapper.YonBipMapper;
import com.yonyou.bs.service.NCC_Itf_PubService;

import pers.bc.utils.pub.StringUtil;

/**
 * </br>
 * &#064;qualiFild: NC_Impl_PubService &#064;version 1.0 <br>
 * &#064;author：libencheng <br>
 * &#064;user：licheng <br>
 * &#064;date: Created on 2024/11/16 <br>
 */
@Service
public class NCC_Impl_PubService implements NCC_Itf_PubService
{
    
    // @Autowired
    @Resource
    YonBipMapper yonBipMapper;
    
    @Resource
    MyLogMapper logMapper;
    
    @Override
    public String demoMap(Map<String, Object> paraMap) throws Throwable
    {
        Map<String, Object> logMap = new HashMap<>();
        logMap.put("pk_log", "20250802100000000001");
        logMap.put("state", "SUCCESS");
        logMap.put("message", "调用成功");
        try
        {
            int rows = logMapper.insertMyLog(paraMap);
            // .... 这里写代码
            
        }
        catch (Exception e)
        {
            logMap.put("restcode", "500");
            logMap.put("state", "FAILED");
            logMap.put("thwmsg", StringUtil.toString(e));
            logMap.put("message", "xxxxc程序错误/异常!!! ");
        }
        finally
        {
            logMapper.updateMyLog(logMap);
        }
        
        return null;
    }
    
    @Override
    public String demoEntity(Map<String, Object> paraMap) throws Throwable
    {
        ClogEntity clog = new ClogEntity();
        clog.setPk_log("20250802100000000001");
        clog.setCode("PAY");
        clog.setName("支付日志");
        
        try
        {
            int rows = logMapper.insertMyLog(clog);
            // .... 这里写代码
        }
        catch (Exception e)
        {
            clog.setState("500");
            clog.setRestcode("500");
            clog.setMessage("xxxxc程序错误/异常!!! ");
            clog.setThwmsg(StringUtil.toString(e));
            
        }
        finally
        {
            logMapper.updateMyLog(clog);
        }
        
        return null;
    }
}
