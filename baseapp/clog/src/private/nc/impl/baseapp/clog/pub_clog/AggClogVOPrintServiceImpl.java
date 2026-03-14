
package nc.impl.baseapp.clog.pub_clog;

import nc.ui.pub.print.IDataSource;
import nccloud.pubitf.platform.print.AbstractPrintService;
import nccloud.pubitf.platform.print.IPrintInfo;
import nccloud.pubitf.platform.print.vo.PrintInfo;

public class AggClogVOPrintServiceImpl extends AbstractPrintService {
	
	@Override 
    public IDataSource[] getDataSources(IPrintInfo info) {
        PrintInfo printinfo = (PrintInfo) info;
        String[] ids = printinfo.getIds();
        AggClogVOPrintDataSource ds = new AggClogVOPrintDataSource(ids);
       	return new IDataSource[] { ds };
       
    }
}
