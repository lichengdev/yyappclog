package nccloud.web.baseapp.clog.pub_clog.action;

import nccloud.web.platform.print.AbstractPrintAction;

public class PrintClogVOAction extends AbstractPrintAction{
 
	@Override
	public String getPrintServiceModule() {
		return "baseapp";
	}
	
	@Override
	public String getPrintServiceName() {
		return "nc.impl.baseapp.clog.pub_clog.AggClogVOPrintServiceImpl";
	}
}