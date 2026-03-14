package ncc.test.log.api;

import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.yonyou.bs.service.NCC_Itf_PubService;

import nc.bs.util.ResultEntity;
import nc.bs.vo.ApiLogUtil;
import nc.bs.vo.ClogVO;
import pers.bc.utils.aspect.AppMonitorRuning;
import pers.bc.utils.pub.JudgeAssertUtil;
import pers.bc.utils.pub.LoggerUtil;
import pers.bc.utils.pub.PubEnvUtil;
import pers.bc.utils.pub.StringUtil;

/**
 * 
 ** 关联jar：pub_applypluginlbc_2.0.jar
 * @see https://gitee.com/lichengdev/pubapptool.git
 * @qualiFild ncc.test.log.api.ApiDemo.java<br>
 * @author：LiBencheng<br>
 * @date Created on 2016年7月1日<br>
 * @version 1.0<br>
 */
@RestController
@RequestMapping("/bip2NcCtr")
public class ApiDemo 
{
    String folder = "ssolos";
    LoggerUtil logUtil = LoggerUtil.getInstance(folder);
    
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
    
    /**
     * *********************************************************** <br>
     * *说明： <br>
     * @see <br>
     * @return <br>
     * @Object <br>
     * @methods ncc.test.log.api.ApiDemo#doAction <br>
     * @author LiBencheng <br>
     * @date Created on 2016年7月1日 <br>
     * @time 上午12:32:42 <br>
     * @version 1.0 <br>
     *************************************************************          <br>
     */
    public Object doAction()
    {
        ClogVO clogVO = ApiLogUtil.newInitCLog();
        try
        {
            clogVO.setCode(StringUtil.valueOfEmpty(System.currentTimeMillis()));
            clogVO.setName("ApiDemo");
            // setxxx();
            // ........
            ApiLogUtil.getInstance().insertReceiveLog(clogVO);
            // 这里写你的业务代码/方法
        }
        catch (Exception e)
        {
            clogVO.setRestcode("201");
            clogVO.setMessage("xxxxc程序错误/异常!!! ");
            clogVO.setThwmsg(StringUtil.toString(e));
        }
        finally
        {
            ApiLogUtil.getInstance().updateLog(clogVO);
        }
        
        return ResultEntity.success().getMap();
    }
    
    /**
     * *********************************************************** <br>
     * *说明： <br>
     * @see <br>
     * @param request
     * @param res
     * @return <br>
     * @Object <br>
     * @methods ncc.test.log.api.ApiDemo#doAction <br>
     * @author LiBencheng <br>
     * @date Created on 2016年7月1日 <br>
     * @time 上午12:32:53 <br>
     * @version 1.0 <br>
     *************************************************************          <br>
     */
    public Object doAction(HttpServletRequest request, HttpServletResponse res)
    {
        ClogVO clogVO = ApiLogUtil.newInitCLog(request, res);
        try
        {
            // clogVO = ApiLogUtil.getInstance().insertReceiveLog(request, res,"OA");
            // clogVO = ApiLogUtil.getInstance().insertReceiveLog(request, res);
            clogVO.setCode(StringUtil.valueOfEmpty(System.currentTimeMillis()));
            clogVO.setName("ApiDemo");
            // setxxx();
            // .........
            ApiLogUtil.getInstance().insertReceiveLog(clogVO);
            // 这里写你的业务代码/方法
            
        }
        catch (Throwable e)
        {
            clogVO.setRestcode("201");
            clogVO.setMessage("xxxxc程序错误/异常!!! ");
            clogVO.setThwmsg(StringUtil.toString(e));
        }
        finally
        {
            ApiLogUtil.getInstance().updateLog(clogVO);
        }
        
        return ResultEntity.success().getMap();
    }
}
