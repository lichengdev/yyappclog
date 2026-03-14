package nc.bs.api;

import java.io.File;
import java.io.IOException;
import java.util.Map;

import nc.bs.itf.NC_Itf_ApiDBService;
import nc.bs.vo.ClogVO; 
import nc.vo.pub.BusinessException;
import pers.bc.utils.constant.InumberCons;
import pers.bc.utils.file.FileUtilbc;
import pers.bc.utils.pub.JudgeAssertUtil;
import pers.bc.utils.pub.LoggerUtil;
import pers.bc.utils.pub.PropertiesUtil;
import pers.bc.utils.pub.PubEnvUtil;
import pers.bc.utils.pub.StringUtil;
import pers.bc.utils.yonyou.YonYouUtilbc;

public class NC_Itf_ApiDBServiceImpl implements NC_Itf_ApiDBService
{
    String folderName = "ApiLogs";
    LoggerUtil logger = LoggerUtil.getInstance(folderName);
    
    @Override
    public String insertLogvo_RequiresNew(ClogVO logvo)
    {
        {
            saveLog(logvo);
            try
            {
                if (PubEnvUtil.isNotEmptyObj(logvo.getPk_log())) return YonYouUtilbc.getBaseDAO().insertVOWithPK(logvo);
                return YonYouUtilbc.getBaseDAO().insertVO(logvo);
            }
            catch (BusinessException e)
            {
                JudgeAssertUtil.throwException(e, folderName, "保存API日志到DB失败！！！");
            }
            
            return "********************";
        }
    }
    
    @Override
    public Object updateLogvo_RequiresNew(ClogVO logvo)
    {
        {
            saveLog(logvo);
            try
            {
                if (PubEnvUtil.isEmptyObj(logvo.getPk_log())) insertLogvo_RequiresNew(logvo);
                return YonYouUtilbc.getBaseDAO().updateVO(logvo);
            }
            catch (BusinessException e)
            {
                JudgeAssertUtil.throwException(e, folderName, "更新API日志到DB失败！！！");
            }
            return -1;
        }
    }
    
    @Override
    public void deleteLogvo_RequiresNew(ClogVO logvo)
    {
        {
            logvo.setAttributeValue("dr", InumberCons.ONE);
            saveLog(logvo);
            updateLogvo_RequiresNew(logvo);
        }
    }
    
    /**
     * *********************************************************** <br>
     * *说明： 读取配置文件,操作日志文件<br>
     * @see <br>
     * @param logvo <br>
     * @void <br>
     * @methods nc.bs.api.NC_Itf_ApiDBServiceImpl#saveLog <br>
     * @author LiBencheng <br>
     * @date Created on 2024-7-28 <br>
     * @time 下午11:12:21 <br>
     * @version 1.0 <br>
     *************************************************************          <br>
     */
    private void saveLog(ClogVO logvo)
    {
        try
        {

            String patch = FileUtilbc.getWorkServerPath() + File.separator + "modules" + File.separator + "baseapp" + File.separator
                + "META-INF" + File.separator + "ApiServiceConfig.properties";
            // String value = PropertiesUtil.getValueByKey(patch, "");
            Map<String, String> map = PropertiesUtil.getAllProperties(patch);
            folderName = StringUtil.valueOfDefault(map.get("api.logFileDir"), "ApiLogs");
            if (PubEnvUtil.equals("true", map.get("api.printLog")) || PubEnvUtil.equals("Y", map.get("api.printLog")))
                LoggerUtil.getInstance(folderName).info("本次调用：" + logvo.toString().replace(LoggerUtil.RN, LoggerUtil.EMPTY));
            if (PubEnvUtil.equals("true", map.get("api.printStack")) || PubEnvUtil.equals("Y", map.get("api.printStack")))
                LoggerUtil.getInstance(folderName).debug(
                    "本次调用：" + logvo.toString().replace(LoggerUtil.RN, LoggerUtil.EMPTY) + LoggerUtil.RN + LoggerUtil.getInvokMethodAll());
            
        }
        catch (IOException e)
        {
            LoggerUtil.getInstance().exception("读取配置文件,操作日志文件异常：", e);
        }
        
    }
 
}
