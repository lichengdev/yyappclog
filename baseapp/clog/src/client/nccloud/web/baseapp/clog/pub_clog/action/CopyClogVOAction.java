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
 * 编辑Action
 */
public class CopyClogVOAction extends BaseAction{
	@Override
	public Object doAction(IRequest request, RequstParamWapper paramWapper) throws Throwable{
		//json数据转换
		RequestDTO param = VOTransform.toVO(paramWapper.requestString,RequestDTO.class);
		//获取参数
		String pk = param.getPk();
		
		IClogVOService service = ServiceLocator.find(IClogVOService.class);
		
		AggClogVO vo = service.copyAggClogVO(pk);
		
		if(vo == null){
			return null;
		}
		
		return buildResult(param,true,null,vo);
	}
	
}