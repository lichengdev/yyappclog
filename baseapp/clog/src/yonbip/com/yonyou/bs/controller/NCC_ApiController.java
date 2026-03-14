package com.yonyou.bs.controller;

import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.yonyou.bs.service.NCC_Itf_PubService;
import com.yonyou.hrcloud.pm.common.web.BaseController;

import pers.bc.utils.aspect.AppMonitorRuning;
import pers.bc.utils.pub.JudgeAssertUtil;
import pers.bc.utils.pub.PubEnvUtil;

/**
 * </br>
 * &#064;qualiFild: 金控对公预付单同步到NC生成对公付款单 &#064;version 1.0 <br>
 * &#064;author：zcy <br>
 * &#064;user：zcy <br>
 * &#064;date: Created on 2024/11/15 <br>
 */
@RestController
@RequestMapping("/bip2NcCtr")
public class NCC_ApiController extends BaseController
{
    
    private String folder = "nc_logs";
    
    @Resource
    private NCC_Itf_PubService ncc_Service;
    
    @AppMonitorRuning(method = "NC_PrepaidController.pushNCPrepaid", funEffect = "金控对公预付单同步到NC生成对公付款单!")
    @RequestMapping(value = "/push-Prepaid", method = {RequestMethod.POST})
    public void demo(@RequestBody Map<String, Object> paraMap, HttpServletRequest request, HttpServletResponse response) throws Throwable
    {
        
        JudgeAssertUtil.checkAssertDir(PubEnvUtil.isNullObj(paraMap), folder, "对公预付单同步到NC生成对公付款单,传参为空!!!");
        String restStr = ncc_Service.demoMap(paraMap);
        // renderJson(response, restStr);
    }
}
