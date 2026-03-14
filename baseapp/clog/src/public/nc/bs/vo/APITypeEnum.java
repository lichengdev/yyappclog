package nc.bs.vo;

public enum APITypeEnum
{
    receive(0, "接收请求：我方接手"),
    send(1, "发送请求：我方发送"), 
    other(-1, "其他(本地操作也可以记录)");
    
    /** 函数值 */
    private Integer enumValue;
    /** 函数描述 */
    private String description;
    
    APITypeEnum(Integer enumValue, String description)
    {
        this.enumValue = enumValue;
        this.description = description;
    }
    
    public Integer getEnumValue()
    {
        return enumValue;
    }
    
    public void setEnumValue(Integer enumValue)
    {
        this.enumValue = enumValue;
    }
    
    public String getDescription()
    {
        return description;
    }
    
    public void setDescription(String description)
    {
        this.description = description;
    }
    
}
