
package nc.impl.baseapp.clog.pub_clog;

import nc.bs.framework.common.NCLocator;
import nc.bs.vo.AggClogVO;
import nc.itf.baseapp.clog.pub_clog.IClogVOService;
import nc.vo.pub.BusinessException;
import nc.vo.pub.SuperVO;
import nc.vo.trade.pub.HYBillVO; 
import nccloud.bs.excel.IXChangeContext;
import nccloud.bs.excel.plugin.AbstractImportProceeWithContext;

public class AggClogVOImportProcess extends AbstractImportProceeWithContext {
	
	@Override
	protected void processBillWithContext(Object vo, IXChangeContext changcontext) throws BusinessException {
		
		AggClogVO targetVO = null;
		SuperVO headVO = (SuperVO)((HYBillVO)vo).getParentVO();
   		targetVO = new AggClogVO();
   		targetVO.setParentVO(headVO);
  		getService().initDefaultData(targetVO.getParentVO());
		getService().saveAggClogVO(targetVO);
	}
	
	private IClogVOService getService() {
		return NCLocator.getInstance().lookup(IClogVOService.class);
	}

}
