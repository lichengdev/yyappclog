
package nc.impl.baseapp.clog.pub_clog;

import java.io.File;
import java.util.List;
import java.util.Map;
 
import nc.bs.framework.common.NCLocator;
import nc.bs.vo.AggClogVO;
import nc.itf.baseapp.clog.pub_clog.IClogVOService;
import nc.vo.platform.appsystemplate.AreaVO;
import nc.vo.pub.BusinessException;
import nc.vo.pub.ExtendedAggregatedValueObject;
import nccloud.bs.excel.process.AbstractExcelOutputProcessor;
import nccloud.bs.trade.excelimport.convertor.DefaultDataConvertor4ncc;
import nccloud.itf.trade.excelimport.ExportDataInfo;
import nccloud.itf.trade.excelimport.convertor.IData2AggVOConvertor;
import nccloud.ui.trade.excelimport.InputItem;
import nccloud.vo.excel.scheme.BillDefination;
/*
 *	导出
 */
public class AggClogVOExcelOutputProcessor extends AbstractExcelOutputProcessor {

	@Override
	public File writeExportData(String filename, Object[] values, List<InputItem> inputitems,
			BillDefination billDefination) throws Exception {
		return super.writeExportData(filename, values, inputitems, billDefination);
	}

	@Override
	public File writeExportData(String filename, Object[] values, List<InputItem> inputitems,
			BillDefination billDefination, boolean isExportByTemp, Map<String, AreaVO> areamap) throws Exception {
		return super.writeExportData(filename, values, inputitems, billDefination, isExportByTemp, areamap);
	}

	private IData2AggVOConvertor convertor;

	@Override
	public Object[] getObjectValueByPks(String[] pks) throws BusinessException {
		// 要改成调用接口根据前端传过来的pks参数查询对应VO返回
		AggClogVO[] queryResult = getService().listAggClogVOByPk(pks);	
		if (queryResult == null || queryResult.length <= 0) {
			return null;
		}
		return queryResult;
	}

	@Override
	protected ExportDataInfo getValue(Object[] vos, List<InputItem> exportItems, BillDefination billdefination)
			throws BusinessException {
		ExtendedAggregatedValueObject[] aggvos = getConvertorForTemp().convertDataFromEditorData(billdefination, vos,
				exportItems);
		return new ExportDataInfo(aggvos);
	}

	@Override
	public IData2AggVOConvertor getConvertorForTemp() {
		if (convertor == null) {
			convertor = new DefaultDataConvertor4ncc();
		}
		return convertor;
	}

	@Override
	public void setAreamap(Map<String, AreaVO> areamap) {
		super.setAreamap(areamap);
	}
	
	private IClogVOService getService() {
		return NCLocator.getInstance().lookup(IClogVOService.class);
	}


}
