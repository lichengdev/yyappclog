package nccloud.web.baseapp.clog.pub_clog.action;
import java.util.HashMap;
import java.util.Map;
 
import nc.bs.vo.AggClogVO;
import nc.itf.baseapp.clog.pub_clog.IClogVOService;
import nccloud.framework.service.ServiceLocator;
import nccloud.framework.web.container.IRequest;
import nccloud.web.codeplatform.framework.action.base.BaseAction;
import nccloud.web.codeplatform.framework.action.base.RequestDTO;
import nccloud.web.codeplatform.framework.action.base.RequstParamWapper;
import nccloud.web.codeplatform.framework.action.base.VOTransform;


/**
 * 新增Action
 */
public class AddClogVOAction extends BaseAction{

	@Override
	public Object doAction(IRequest request, RequstParamWapper paramWapper) throws Throwable{
		//json数据转换
		RequestDTO param = VOTransform.toVO(paramWapper.requestString,RequestDTO.class);
		//获取参数
		Map<String,Object> userJson = param.getUserJson();
		
		
		String formid = param.getFormId();
		String[] tableid = param.getTableId();
		String tailid = param.getTailId();
		String pagecode = param.getPageCode();
		VOTransform tf = new VOTransform(null, pagecode);
		
		AggClogVO[] vos = tf.toVO(param.getBillCard(),formid,tableid,tailid,AggClogVO.class);
		
		//获得实体VO
		IClogVOService service = ServiceLocator.find(IClogVOService.class);
		AggClogVO vo = service.preAddAggClogVO(vos[0],userJson);
		Map<String,Object> externalData = new HashMap<>();
		
		return buildResult(param,true,externalData,vo);
	}

}