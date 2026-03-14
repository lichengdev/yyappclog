package nc.bs.itf;

import nc.bs.vo.ClogVO;
import nc.vo.pub.BusinessException;

/**
 * CMTEJBServiceHandler
 ** 
 * @qualiFild nc.bs.itf.NC_Itf_ApiDBService.java<br>
 * @author：LiBencheng<br>
 * @date Created on 2008年8月8日<br>
 * @version 1.0<br>
 */
public interface NC_Itf_ApiDBService
{
    
    /**
     * *********************************************************** <br>
     * 说明：新增,支持自定義主鍵<br>
     * @see <br>
     * @param nc.vo.pub.SuperVO
     * @return
     * @throws BusinessException <br>
     * @Object <br>
     * @methods pers.bc.utils.yonyou.YonYouPubDBService#insertLogvo <br>
     * @author 李本城 <br>
     * @date Created on 2020-3-23 <br>
     * @time 下午2:46:21 <br>
     * @version 1.0 <br>
     *************************************************************          <br>
     */
    String insertLogvo_RequiresNew(ClogVO logvo);
    
    /**
     * *********************************************************** <br>
     * 说明：修改,没有主键走新增方法 <br>
     * @see <br>
     * @param nc.vo.pub.SuperVO
     * @return
     * @throws BusinessException <br>
     * @Object <br>
     * @methods pers.bc.utils.yonyou.YonYouPubDBService#updateLogvo <br>
     * @author 李本城 <br>
     * @date Created on 2020-3-23 <br>
     * @time 下午2:46:30 <br>
     * @version 1.0 <br>
     *************************************************************          <br>
     */
    Object updateLogvo_RequiresNew(ClogVO logvo);
    
    /**
     * *********************************************************** <br>
     * 说明：删除 <br>
     * @see <br>
     * @param nc.vo.pub.SuperVO
     * @throws BusinessException <br>
     * @void <br>
     * @methods pers.bc.utils.yonyou.YonYouPubDBService#deleteLogvo <br>
     * @author 李本城 <br>
     * @date Created on 2020-3-23 <br>
     * @time 下午2:46:38 <br>
     * @version 1.0 <br>
     *************************************************************          <br>
     */
    void deleteLogvo_RequiresNew(ClogVO logvo);
    
}
