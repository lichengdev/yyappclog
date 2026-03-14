package nccloud.web.baseapp.clog.pub_clog.action;

import nc.bs.vo.AggClogVO;
import nc.itf.baseapp.clog.pub_clog.IClogVOService;
import nccloud.framework.service.ServiceLocator;
import nccloud.framework.web.container.IRequest;
import nccloud.web.codeplatform.framework.action.base.BaseAction;
import nccloud.web.codeplatform.framework.action.base.RequestDTO;
import nccloud.web.codeplatform.framework.action.base.RequstParamWapper;
import nccloud.web.codeplatform.framework.action.base.VOTransform; 

/**
 * 保存action（主子、单表、单form）
 */
public class SaveClogVOAction extends BaseAction{

	@Override
	public Object doAction(IRequest request, RequstParamWapper paramWapper) throws Throwable{
		RequestDTO param = VOTransform.toVO(paramWapper.requestString,RequestDTO.class);
		
		String formid = param.getFormId();
		String[] tableid = param.getTableId();
		String tailid = param.getTailId();
		String pagecode = param.getPageCode();
		VOTransform tf = new VOTransform(null, pagecode);
		
		
		IClogVOService service = ServiceLocator.find(IClogVOService.class);
		
		AggClogVO[] vos = tf.toVO(param.getBillCard(),formid,tableid,tailid,AggClogVO.class);
		
		vos = service.saveAggClogVO(vos[0]);
		
		return buildResult(param,param.getBillCard().getHead()!=null,null,vos);
	
	}
	
}