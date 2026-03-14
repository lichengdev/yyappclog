package nc.bs.util;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import nc.bs.framework.common.InvocationInfoProxy;
import nc.bs.framework.server.ISecurityTokenCallback;
import nc.bs.itf.NC_Itf_ApiDBService;
import nc.bs.vo.APITypeEnum;
import nc.bs.vo.ClogVO;
import nc.vo.pub.BusinessException;
import nc.vo.pub.lang.UFBoolean;
import nc.vo.pub.lang.UFDate;
import nc.vo.pub.lang.UFDateTime;
import pers.bc.utils.pub.JudgeAssertUtil;
import pers.bc.utils.pub.PubEnvUtil;
import pers.bc.utils.pub.StringUtil;
import pers.bc.utils.yonyou.InvocationInfoUtils;
import pers.bc.utils.yonyou.YonYouUtilbc;

/**
 * 接口调用日志工具
 ** 
 * @qualiFild nc.bs.vo.ApiLogUtil.java<br>
 * @author：LiBencheng<br>
 * @date Created on 2024-7-31<br>
 * @version 1.0<br>
 */
public class ApiLogUtil
{
    private static ThreadLocal<ApiLogUtil> ApiUtil = new ThreadLocal<ApiLogUtil>();
    
    private static String sccMsg = "调用成功！！！";
    
    /**
     * *********************************************************** <br>
     * *说明： 接收方保存日志<br>
     * @see <br>
     * @param request
     * @param res
     * @return
     * @throws BusinessException <br>
     * @ClogVO <br>
     * @methods nc.bs.vo.ApiUtilService#insertReceiveLog <br>
     * @author LiBencheng <br>
     * @date Created on 2024-7-28 <br>
     * @time 下午10:44:35 <br>
     * @version 1.0 <br>
     *************************************************************          <br>
     */
    @SuppressWarnings("restriction")
    public ClogVO insertReceiveLog(HttpServletRequest request, HttpServletResponse res)
    {
        JudgeAssertUtil.checkNull(request, "传入的参数HttpServletRequest不能为空！！！");
        String parameter = request.getParameter("data");
        // 确保请求的Content-Type是application/json
        ClogVO logvo = new ClogVO();
        setDefaultValue(logvo);
        logvo.setDatatype(request.getContentType() + "@" + request.getCharacterEncoding());
        logvo.setIp(getClientIP(request) + ":" + request.getRemotePort());
        // logvo.setIp("ClientIP："+getClientIP(request));
        logvo.setUrl(StringUtil.valueOfEmpty(request.getRequestURL()));
        logvo.setAction(request.getMethod());
        logvo.setToken(request.getHeader("Authorization"));
        logvo.setSessionid(request.getSession().getId());
        // 检查Authorization头部 
        logvo.setSrc_sys("OTW");
        logvo.setRes_direct(APITypeEnum.receive.getEnumValue());
        logvo.setPk_log(YonYouUtilbc.NCLocator(NC_Itf_ApiDBService.class).insertLogvo_RequiresNew(logvo));
        
        // YonYouUtilbc.getBaseDAO().insertVOWithPK(logvo);
        return logvo;
    }
    
    public ClogVO insertReceiveLog(HttpServletRequest request, HttpServletResponse res, String Src_sysCode)
    {
        JudgeAssertUtil.checkNull(request, "传入的参数HttpServletRequest不能为空！！！");
        String parameter = request.getParameter("data");
        // 确保请求的Content-Type是application/json
        ClogVO logvo = new ClogVO();
        setDefaultValue(logvo);
        logvo.setDatatype(request.getContentType() + "@" + request.getCharacterEncoding());
        logvo.setIp(getClientIP(request) + ":" + request.getRemotePort());
        // logvo.setIp("ClientIP："+getClientIP(request));
        logvo.setUrl(StringUtil.valueOfEmpty(request.getRequestURL()));
        logvo.setAction(request.getMethod());
        logvo.setToken(request.getHeader("Authorization"));
        logvo.setSessionid(request.getSession().getId());
        // 检查Authorization头部
        logvo.setSrc_sys(Src_sysCode);
        logvo.setRes_direct(APITypeEnum.receive.getEnumValue());
        logvo.setPk_log(YonYouUtilbc.NCLocator(NC_Itf_ApiDBService.class).insertLogvo_RequiresNew(logvo));
        
        // YonYouUtilbc.getBaseDAO().insertVOWithPK(logvo);
        return logvo;
    }
    
    /**
     * *********************************************************** <br>
     * *说明： 接收方保存日志<br>
     * @see <br>
     * @param logvo
     * @return
     * @throws BusinessException <br>
     * @ClogVO <br>
     * @methods nc.bs.vo.ApiUtilService#insertReceiveLog <br>
     * @author LiBencheng <br>
     * @date Created on 2024-7-28 <br>
     * @time 下午10:45:12 <br>
     * @version 1.0 <br>
     *************************************************************          <br>
     */
    public ClogVO insertReceiveLog(ClogVO logvo) throws BusinessException
    {
        YonYouUtilbc.NCLocator(ISecurityTokenCallback.class).token("NCSystem".getBytes(), "#UAP#".getBytes());
        setDefaultValue(logvo);
        logvo.setRes_direct(APITypeEnum.receive.getEnumValue());
        logvo.setBilldate(new UFDate());
        logvo.setSrc_sys("OTW");
        
        logvo.setPk_log(YonYouUtilbc.NCLocator(NC_Itf_ApiDBService.class).insertLogvo_RequiresNew(logvo));
        // YonYouUtilbc.getBaseDAO().insertVOWithPK(logvo);
        return logvo;
    }
    
    /**
     * *********************************************************** <br>
     * *说明： 发送方保存日志<br>
     * @see <br>
     * @param logvo
     * @return
     * @throws BusinessException <br>
     * @ClogVO <br>
     * @methods nc.bs.vo.ApiUtilService#insertSendLog <br>
     * @author LiBencheng <br>
     * @date Created on 2024-7-28 <br>
     * @time 下午10:45:19 <br>
     * @version 1.0 <br>
     *************************************************************          <br>
     */
    public ClogVO insertSendLog(ClogVO logvo) throws BusinessException
    {
        if (PubEnvUtil.isNullObj(logvo)) logvo = new ClogVO();
        logvo.setSrc_sys("NC");
        logvo.setRes_direct(APITypeEnum.send.getEnumValue());
        
        logvo.setPk_log(YonYouUtilbc.NCLocator(NC_Itf_ApiDBService.class).insertLogvo_RequiresNew(logvo));
        // YonYouUtilbc.getBaseDAO().insertVOWithPK(logvo);
        return logvo;
    }
    
    /**
     * *********************************************************** <br>
     * *说明： 更新日志信息<br>
     * @see <br>
     * @param logvo
     * @return
     * @throws BusinessException <br>
     * @ClogVO <br>
     * @methods nc.bs.vo.ApiUtilService#updateLog <br>
     * @author LiBencheng <br>
     * @date Created on 2024-7-28 <br>
     * @time 下午10:45:38 <br>
     * @version 1.0 <br>
     *************************************************************          <br>
     */
    public ClogVO updateLog(ClogVO logvo)
    {
        logvo.setModifiedtime(new UFDateTime());
        logvo.setEndtime(StringUtil.toString(System.currentTimeMillis()));
        logvo.setRuntime(StringUtil.toString(Long.valueOf(logvo.getEndtime()) - Long.valueOf(logvo.getStartime())));

        YonYouUtilbc.NCLocator(NC_Itf_ApiDBService.class).updateLogvo_RequiresNew(logvo);
        return logvo;
    }
    
    public static void setDefaultValue(ClogVO logvo)
    {
        if (PubEnvUtil.isNullObj(logvo)) logvo = new ClogVO();
        logvo.setSrc_sys("NC");
        logvo.setRestcode("200");
        logvo.setBilldate(new UFDate());
        logvo.setLogdate(new UFDateTime());
        logvo.setCreator("NC_USER0000000000000");
        logvo.setCreationtime(new UFDateTime());
        logvo.setStartime(StringUtil.toString(System.currentTimeMillis()));
        logvo.setUserid(InvocationInfoUtils.getInvocationInfo().getUserId());
        logvo.setPk_org(InvocationInfoUtils.getInvocationInfo().getGroupId());
        logvo.setPk_group(InvocationInfoUtils.getInvocationInfo().getGroupId());
        logvo.setPk_org_v(InvocationInfoUtils.getInvocationInfo().getGroupId());
        logvo.setIp(InvocationInfoProxy.getInstance().getClientHost());
        logvo.setEnable(UFBoolean.TRUE);
        // logvo.setStatus(200);
        logvo.setState("200");
        logvo.setMessage(sccMsg);
        logvo.setThwmsg(sccMsg);
    }
    
    public static ClogVO newInitCLog()
    {
        return new ClogVO()
        {
            private static final long serialVersionUID = 1909599980430852958L;
            {
                setRestcode("200");
                setSrc_sys("NC");
                setBilldate(new UFDate());
                setLogdate(new UFDateTime());
                setCreator(InvocationInfoUtils.getInvocationInfo().getUserId());
                setCreationtime(new UFDateTime());
                setStartime(StringUtil.toString(System.currentTimeMillis()));
                setUserid(InvocationInfoUtils.getInvocationInfo().getUserId());
                setPk_org(InvocationInfoUtils.getInvocationInfo().getGroupId());
                setPk_group(InvocationInfoUtils.getInvocationInfo().getGroupId());
                setPk_org_v(InvocationInfoUtils.getInvocationInfo().getGroupId());
                setIp(InvocationInfoProxy.getInstance().getClientHost());
                setRequest(sccMsg);
                setEnable(UFBoolean.TRUE);
                // logvo.setStatus(200);
                setState("200");
                setMessage(sccMsg);
                setThwmsg(sccMsg);
            }
        };
    }
    
    @SuppressWarnings("restriction")
    public static ClogVO newInitCLog(final HttpServletRequest rest, HttpServletResponse res)
    {
        return new ClogVO()
        {
            private static final long serialVersionUID = 1L;
            {
                setUrl(StringUtil.valueOfEmpty(rest.getRequestURL()));
                setDatatype(rest.getContentType() + "@" + rest.getCharacterEncoding());
                setIp(getClientIP(rest) + ":" + rest.getRemotePort());
                // setIp("ClientIP："+getClientIP(rest));
                setAction(rest.getMethod());
                setToken(rest.getHeader("Authorization"));
                setSessionid(rest.getSession().getId());
                setUserid(rest.getRemoteUser());
                setReferer(rest.getHeader("Referer"));
                setLocaladdr(rest.getLocalAddr());
                setClientip(rest.getRemoteAddr());
                
                setRestcode("200");
                setSrc_sys("NC");
                setBilldate(new UFDate());
                setLogdate(new UFDateTime());
                setCreationtime(new UFDateTime());
                setStartime(StringUtil.toString(System.currentTimeMillis()));
                setPk_org(InvocationInfoUtils.getInvocationInfo().getGroupId());
                setPk_group(InvocationInfoUtils.getInvocationInfo().getGroupId());
                setPk_org_v(InvocationInfoUtils.getInvocationInfo().getGroupId());
                setRequest(sccMsg);
                setEnable(UFBoolean.TRUE);
                // setStatus(200);
                setState("200");
                setMessage(sccMsg);
                setThwmsg(sccMsg);
            }
        };
    }
    
    @SuppressWarnings("restriction")
    public static ClogVO newInitCLog(final HttpServletRequest rest, HttpServletResponse res, final String Src_sysCode)
    {
        ClogVO cLog = newInitCLog(rest, res);
        cLog.setSrc_sys(Src_sysCode);
        
        return cLog;
    }
    
    public static String getClientIP(HttpServletRequest request)
    {
        String remoteAddr = "";
        if (request != null)
        {
            remoteAddr = request.getHeader("X-FORWARDED-FOR");
            if (PubEnvUtil.isEmpty(remoteAddr)) remoteAddr = request.getRemoteAddr();
        }
        
        return remoteAddr;
    }
    
    /**
     * *********************************************************** <br>
     * *说明： <br>
     * @see <br>
     * @return <br>
     * @ApiLogUtil <br>
     * @methods nc.bs.vo.ApiLogUtil#getInstance <br>
     * @author LiBencheng <br>
     * @date Created on 2024-7-28 <br>
     * @time 下午11:16:36 <br>
     * @version 1.0 <br>
     *************************************************************          <br>
     */
    public static ApiLogUtil getInstance()
    {
        ApiLogUtil df = ApiUtil.get();
        // if (null == df)
        // {
        // synchronized (ApiLogUtil.class)
        // {
        if (null == df)
        {
            df = new ApiLogUtil();
            ApiUtil.set(df);
        }
        // }
        // }
        return df;
    }
}
