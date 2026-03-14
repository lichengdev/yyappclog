package nc.bs.vo;

import nc.vo.pubapp.pattern.model.meta.entity.bill.AbstractBillMeta;

public class AggClogVOMeta extends AbstractBillMeta
{
    
    public AggClogVOMeta()
    {
        this.init();
    }
    
    private void init()
    {
        this.setParent(nc.bs.vo.ClogVO.class);
    }
    
}
