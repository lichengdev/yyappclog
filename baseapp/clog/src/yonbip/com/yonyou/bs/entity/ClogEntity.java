package com.yonyou.bs.entity;

import java.io.Serializable;
 
import pers.bc.utils.pub.JsonUtil;
import pers.bc.utils.pub.StringUtil;

/**
 * 接口调用日志表实体
 **
 * @qualiFild com.yonyou.bs.entity.ClogEntity.java<br>
 * @author：LiBencheng<br>
 * @date Created on 2020年8月2日<br>
 * @version 1.0<br>
 */
public class ClogEntity implements Serializable
{

    /** @date 2020年7月24日 */
    private static final long serialVersionUID = 1L;
 
    public static final String PK_LOG = "pk_log";
    public static final String CODE = "code";
    public static final String NAME = "name";
    public static final String SRC_SYS = "src_sys";
    public static final String SRC_ID = "src_id";
    public static final String RES_DIRECT = "res_direct";
    public static final String LOGDATE = "logdate";
    public static final String IP = "ip";
    public static final String URL = "url";
    public static final String ACTION = "action";
    public static final String SESSIONID = "sessionid";
    public static final String TOKEN = "token";
    public static final String USERID = "userid";
    public static final String RESPONSE = "response";
    public static final String REQUEST = "request";
    public static final String MESSAGE = "message";
    public static final String THWMSG = "thwmsg";
    public static final String STATE = "state";
    public static final String RESTCODE = "restcode";
    public static final String STARTIME = "startime";
    public static final String ENDTIME = "endtime";
    public static final String RUNTIME = "runtime";
    public static final String DATATYPE = "datatype";
    public static final String BILLDATE = "billdate";
    public static final String ENABLE = "enable";
    public static final String DEF01 = "def01";
    public static final String DEF02 = "def02";
    public static final String DEF03 = "def03";
    public static final String DEF04 = "def04";
    public static final String DEF05 = "def05";
    public static final String DEF06 = "def06";
    public static final String DEF07 = "def07";
    public static final String DEF08 = "def08";
    public static final String DEF09 = "def09";
    public static final String DEF10 = "def10";
    public static final String DEF11 = "def11";
    public static final String DEF12 = "def12";
    public static final String DEF13 = "def13";
    public static final String DEF14 = "def14";
    public static final String DEF15 = "def15";
    public static final String DEF16 = "def16";
    public static final String DEF17 = "def17";
    public static final String DEF18 = "def18";
    public static final String DEF19 = "def19";
    public static final String DEF20 = "def20";
    public static final String PK_GROUP = "pk_group";
    public static final String PK_ORG = "pk_org";
    public static final String PK_ORG_V = "pk_org_v";
    public static final String CREATOR = "creator";
    public static final String CREATIONTIME = "creationtime";
    public static final String MODIFIER = "modifier";
    public static final String MODIFIEDTIME = "modifiedtime";
    public static final String MEMO = "memo";
    public static final String DR = "dr";
    public static final String TS = "ts";
    
    public static final String REFERER = "referer";
    public static final String LOCALADDR = "localaddr";
    public static final String CLIENTIP = "clientip";
    public static final String COOKES = "cookes"; 
    public static final String TENANCODE = "tenantcode"; 
    public static final String TENANTID = "tenantid"; 
    

    /** 租户id */
    public String tenantid;
    /** 租户id */
    public String tenantcode;

    /** 请求引用来源 */
    public String referer;
    /** 本地IP */
    public String localaddr;
    /** 客户端ip */
    public String clientip;
    /** 请求cookes */
    public Object cookes;
    
    public String getReferer(){return referer; }

    public void setReferer(String referer) { this.referer = referer;}

    public String getLocaladdr()    {return localaddr; }

    public void setLocaladdr(String localaddr)   { this.localaddr = localaddr;  }

    public String getClientip()  { return clientip; }

    public void setClientip(String clientip)  { this.clientip = clientip; }

    public Object getCookes() { return cookes; }

    public void setCookes(Object cookes)    { this.cookes = cookes;    }
    
    public String getTenantid()  {return tenantid; }

    public void setTenantid(String tenantid) { this.tenantid = tenantid; }

    public String getTenantcode(){ return tenantcode; }

    public void setTenantcode(String tenantcode){this.tenantcode = tenantcode; }
    
    
    /** 请求状态 */
    public String state;
    /** 请求URL */
    public String url;
    /** 会话ID */
    public String sessionid;

    public String getState() {  return state; }

    public void setState(String state)  {  this.state = state;  }

    public String getSessionid()  {return sessionid; }
    
    public void setSessionid(String sessionid)  {this.sessionid = sessionid; }
    
    public String getUrl()  {return url; }
    
    public void setUrl(String url)  {this.url = url; }
    
    

    /** 请求方法 */
    public String action;
    /** 单据日期 */
    public String billdate;
    /** 编码 */
    public String code;
    /** 创建时间 */
    public String creationtime;
    /** 创建人 */
    public String creator;
    /** 数据类型 */
    public String datatype;
    /** 自定义01 */
    public String def01;
    /** 自定义02 */
    public String def02;
    /** 自定义03 */
    public String def03;
    /** 自定义04 */
    public String def04;
    /** 自定义05 */
    public String def05;
    /** 自定义06 */
    public String def06;
    /** 自定义07 */
    public String def07;
    /** 自定义08 */
    public String def08;
    /** 自定义09 */
    public String def09;
    /** 自定义10 */
    public String def10;
    /** 自定义11 */
    public String def11;
    /** 自定义12 */
    public String def12;
    /** 自定义13 */
    public String def13;
    /** 自定义14 */
    public String def14;
    /** 自定义15 */
    public String def15;
    /** 自定义16 */
    public String def16;
    /** 自定义17 */
    public String def17;
    /** 自定义18 */
    public String def18;
    /** 自定义19 */
    public String def19;
    /** 自定义20 */
    public String def20;
    /** 是否启用 */
    public String enable;
    /** 运行结束时间 */
    public String endtime;
    /** 请求IP */
    public String ip;
    /** 操作日期 */
    public String logdate;
    /** 备注 */
    public String memo;
    /** 日志信息 */
    public Object message;
    /** 修改时间 */
    public String modifiedtime;
    /** 修改人 */
    public String modifier;
    /** 名称 */
    public String name;
    /** 集团 */
    public String pk_group;
    /** 日志主键 */
    public String pk_log;
    /** 组织 */
    public String pk_org;
    /** 组织版本 */
    public String pk_org_v;
    /** 请求参数 */
    public Object request;
    /** 请求方向 */
    public Integer res_direct;
    /** 请求响应结果 */
    public Object response;
    /** 响应编码 */
    public String restcode;
    /** 运行时长 */
    public String runtime;
    /** 来源系统id */
    public String src_id;
    /** 来源系统 */
    public String src_sys;
    /** 运行开始时间 */
    public String startime;
    /** 异常信息 */
    public Object thwmsg;
    /** 请求token */
    public String token;
    /** 时间戳 */
    public String ts;
    /** 请求用户 */
    public String userid;
    

    /** 获取请求方法    @return 请求方法  */
    public String getAction()  {return this.action; }
    
    /** 设置请求方法    @param action 请求方法  */
    public void setAction(String action)  {this.action = action; }
    
    /** 获取单据日期    @return 单据日期  */
    public String getBilldate()  {return this.billdate; }
    
    /** 设置单据日期    @param billdate 单据日期  */
    public void setBilldate(String billdate)  {this.billdate = billdate; }
    
    /** 获取编码    @return 编码  */
    public String getCode()  {return this.code; }
    
    /** 设置编码    @param code 编码  */
    public void setCode(String code)  {this.code = code; }
    
    /** 获取创建时间    @return 创建时间  */
    public String getCreationtime()  {return this.creationtime; }
    
    /** 设置创建时间    @param creationtime 创建时间  */
    public void setCreationtime(String creationtime)  {this.creationtime = creationtime; }
    
    /** 获取创建人    @return 创建人  */
    public String getCreator()  {return this.creator; }
    
    /** 设置创建人    @param creator 创建人  */
    public void setCreator(String creator)  {this.creator = creator; }
    
    /** 获取数据类型    @return 数据类型  */
    public String getDatatype()  {return this.datatype; }
    
    /** 设置数据类型    @param datatype 数据类型  */
    public void setDatatype(String datatype)  {this.datatype = datatype; }
    
    /** 获取自定义01    @return 自定义01  */
    public String getDef01()  {return this.def01; }
    
    /** 设置自定义01    @param def01 自定义01  */
    public void setDef01(String def01)  {this.def01 = def01; }
    
    /** 获取自定义02    @return 自定义02  */
    public String getDef02()  {return this.def02; }
    
    /** 设置自定义02    @param def02 自定义02  */
    public void setDef02(String def02)  {this.def02 = def02; }
    
    /** 获取自定义03    @return 自定义03  */
    public String getDef03()  {return this.def03; }
    
    /** 设置自定义03    @param def03 自定义03  */
    public void setDef03(String def03)  {this.def03 = def03; }
    
    /** 获取自定义04    @return 自定义04  */
    public String getDef04()  {return this.def04; }
    
    /** 设置自定义04    @param def04 自定义04  */
    public void setDef04(String def04)  {this.def04 = def04; }
    
    /** 获取自定义05    @return 自定义05  */
    public String getDef05()  {return this.def05; }
    
    /** 设置自定义05    @param def05 自定义05  */
    public void setDef05(String def05)  {this.def05 = def05; }
    
    /** 获取自定义06    @return 自定义06  */
    public String getDef06()  {return this.def06; }
    
    /** 设置自定义06    @param def06 自定义06  */
    public void setDef06(String def06)  {this.def06 = def06; }
    
    /** 获取自定义07    @return 自定义07  */
    public String getDef07()  {return this.def07; }
    
    /** 设置自定义07    @param def07 自定义07  */
    public void setDef07(String def07)  {this.def07 = def07; }
    
    /** 获取自定义08    @return 自定义08  */
    public String getDef08()  {return this.def08; }
    
    /** 设置自定义08    @param def08 自定义08  */
    public void setDef08(String def08)  {this.def08 = def08; }
    
    /** 获取自定义09    @return 自定义09  */
    public String getDef09()  {return this.def09; }
    
    /** 设置自定义09    @param def09 自定义09  */
    public void setDef09(String def09)  {this.def09 = def09; }
    
    /** 获取自定义10    @return 自定义10  */
    public String getDef10()  {return this.def10; }
    
    /** 设置自定义10    @param def10 自定义10  */
    public void setDef10(String def10)  {this.def10 = def10; }
    
    /** 获取自定义11    @return 自定义11  */
    public String getDef11()  {return this.def11; }
    
    /** 设置自定义11    @param def11 自定义11  */
    public void setDef11(String def11)  {this.def11 = def11; }
    
    /** 获取自定义12    @return 自定义12  */
    public String getDef12()  {return this.def12; }
    
    /** 设置自定义12    @param def12 自定义12  */
    public void setDef12(String def12)  {this.def12 = def12; }
    
    /** 获取自定义13    @return 自定义13  */
    public String getDef13()  {return this.def13; }
    
    /** 设置自定义13    @param def13 自定义13  */
    public void setDef13(String def13)  {this.def13 = def13; }
    
    /** 获取自定义14    @return 自定义14  */
    public String getDef14()  {return this.def14; }
    
    /** 设置自定义14    @param def14 自定义14  */
    public void setDef14(String def14)  {this.def14 = def14; }
    
    /** 获取自定义15    @return 自定义15  */
    public String getDef15()  {return this.def15; }
    
    /** 设置自定义15    @param def15 自定义15  */
    public void setDef15(String def15)  {this.def15 = def15; }
    
    /** 获取自定义16    @return 自定义16  */
    public String getDef16()  {return this.def16; }
    
    /** 设置自定义16    @param def16 自定义16  */
    public void setDef16(String def16)  {this.def16 = def16; }
    
    /** 获取自定义17    @return 自定义17  */
    public String getDef17()  {return this.def17; }
    
    /** 设置自定义17    @param def17 自定义17  */
    public void setDef17(String def17)  {this.def17 = def17; }
    
    /** 获取自定义18    @return 自定义18  */
    public String getDef18()  {return this.def18; }
    
    /** 设置自定义18    @param def18 自定义18  */
    public void setDef18(String def18)  {this.def18 = def18; }
    
    /** 获取自定义19    @return 自定义19  */
    public String getDef19()  {return this.def19; }
    
    /** 设置自定义19    @param def19 自定义19  */
    public void setDef19(String def19)  {this.def19 = def19; }
    
    /** 获取自定义20    @return 自定义20  */
    public String getDef20()  {return this.def20; }
    
    /** 设置自定义20    @param def20 自定义20  */
    public void setDef20(String def20)  {this.def20 = def20; }
    
    /** 获取是否启用    @return 是否启用  */
    public String getEnable()  {return this.enable; }
    
    /** 设置是否启用    @param enable 是否启用  */
    public void setEnable(String enable)  {this.enable = enable; }
    
    /** 获取运行结束时间    @return 运行结束时间  */
    public String getEndtime()  {return this.endtime; }
    
    /** 设置运行结束时间    @param endtime 运行结束时间  */
    public void setEndtime(String endtime)  {this.endtime = endtime; }
    
    /** 获取请求IP    @return 请求IP  */
    public String getIp()  {return this.ip; }
    
    /** 设置请求IP    @param ip 请求IP  */
    public void setIp(String ip)  {this.ip = ip; }
    
    /** 获取操作日期    @return 操作日期  */
    public String getLogdate()  {return this.logdate; }
    
    /** 设置操作日期    @param logdate 操作日期  */
    public void setLogdate(String logdate)  {this.logdate = logdate; }
    
    /** 获取备注    @return 备注  */
    public String getMemo()  {return this.memo; }
    
    /** 设置备注    @param memo 备注  */
    public void setMemo(String memo)  {this.memo = memo; }
    
    /** 获取日志信息    @return 日志信息  */
    public Object getMessage()  {return this.message; }
    
    /** 设置日志信息    @param message 日志信息  */
    public void setMessage(Object message)  {this.message = message; }
    
    /** 获取修改时间    @return 修改时间  */
    public String getModifiedtime()  {return this.modifiedtime; }
    
    /** 设置修改时间    @param modifiedtime 修改时间  */
    public void setModifiedtime(String modifiedtime)  {this.modifiedtime = modifiedtime; }
    
    /** 获取修改人    @return 修改人  */
    public String getModifier()  {return this.modifier; }
    
    /** 设置修改人    @param modifier 修改人  */
    public void setModifier(String modifier)  {this.modifier = modifier; }
    
    /** 获取名称    @return 名称  */
    public String getName()  {return this.name; }
    
    /** 设置名称    @param name 名称  */
    public void setName(String name)  {this.name = name; }
    
    /** 获取集团    @return 集团  */
    public String getPk_group()  {return this.pk_group; }
    
    /** 设置集团    @param pk_group 集团  */
    public void setPk_group(String pk_group)  {this.pk_group = pk_group; }
    
    /** 获取日志主键    @return 日志主键  */
    public String getPk_log()  {return this.pk_log; }
    
    /** 设置日志主键    @param pk_log 日志主键  */
    public void setPk_log(String pk_log)  {this.pk_log = pk_log; }
    
    /** 获取组织    @return 组织  */
    public String getPk_org()  {return this.pk_org; }
    
    /** 设置组织    @param pk_org 组织  */
    public void setPk_org(String pk_org)  {this.pk_org = pk_org; }
    
    /** 获取组织版本    @return 组织版本  */
    public String getPk_org_v()  {return this.pk_org_v; }
    
    /** 设置组织版本    @param pk_org_v 组织版本  */
    public void setPk_org_v(String pk_org_v)  {this.pk_org_v = pk_org_v; }
    
    /** 获取请求参数    @return 请求参数  */
    public Object getRequest()  {return this.request; }
    
    /** 设置请求参数    @param request 请求参数  */
    public void setRequest(Object request)  {this.request = request; }
    
    /** 获取请求方向    @return 请求方向
     * @see String  */
    public Integer getRes_direct()  {return this.res_direct; }
    
    /** 设置请求方向    @param res_direct 请求方向
     * @see String  */
    public void setRes_direct(Integer res_direct)  {this.res_direct = res_direct; }
    
    /** 获取请求响应结果    @return 请求响应结果  */
    public Object getResponse()  {return this.response; }
    
    /** 设置请求响应结果    @param response 请求响应结果  */
    public void setResponse(Object response)  {this.response = response; }
    
    /** 获取响应编码    @return 响应编码  */
    public String getRestcode()  {return this.restcode; }
    
    /** 设置响应编码    @param restcode 响应编码  */
    public void setRestcode(String restcode)  {this.restcode = restcode; }
    
    /** 获取运行时长    @return 运行时长  */
    public String getRuntime()  {return this.runtime; }
    
    /** 设置运行时长    @param runtime 运行时长  */
    public void setRuntime(String runtime)  {this.runtime = runtime; }
    
    /** 获取来源系统id    @return 来源系统id  */
    public String getSrc_id()  {return this.src_id; }
    
    /** 设置来源系统id    @param src_id 来源系统id  */
    public void setSrc_id(String src_id)  {this.src_id = src_id; }
    
    /** 获取来源系统    @return 来源系统  */
    public String getSrc_sys()  {return this.src_sys; }
    
    /** 设置来源系统    @param src_sys 来源系统  */
    public void setSrc_sys(String src_sys)  {this.src_sys = src_sys; }
    
    /** 获取运行开始时间    @return 运行开始时间  */
    public String getStartime()  {return this.startime; }
    
    /** 设置运行开始时间    @param startime 运行开始时间  */
    public void setStartime(String startime)  {this.startime = startime; }
    
    /** 获取异常信息    @return 异常信息  */
    public Object getThwmsg()  {return this.thwmsg; }
    
    /** 设置异常信息    @param thwmsg 异常信息  */
    public void setThwmsg(Object thwmsg)  {this.thwmsg = thwmsg; }
    
    /** 获取请求token    @return 请求token  */
    public String getToken()  {return this.token; }
    
    /** 设置请求token    @param token 请求token  */
    public void setToken(String token)  {this.token = token; }
    
    /** 获取时间戳    @return 时间戳  */
    public String getTs()  {return this.ts; }
    
    /** 设置时间戳    @param ts 时间戳  */
    public void setTs(String ts)  {this.ts = ts; }
    
    /** 获取请求用户    @return 请求用户  */
    public String getUserid()  {return this.userid; }
    
    /** 设置请求用户    @param userid 请求用户  */
    public void setUserid(String userid)  {this.userid = userid; }
    

    public String getPKFieldName(){ return "pk_log";};
    
    public String getTableName()  {return "pub_clog"; }
     
    @Override
    public String toString()
    {// try { // return "nc.bs.vo.ClogVO = "
        // + CollectionUtil.transObj2Josn(this).replace("\r\n", "");
        // } catch (Throwable e) { // System.err.println("nc.bs.vo.ClogVO = " + StringUtil.toString(e));
        //
        // return super.toString();
        // }
        try
        {     // Object json = JSONObject.toJSON(this);
            return "nc.bs.vo.ClogVO = " + JsonUtil.compressJson(JsonUtil.toJSONString(this));
            // return JSON.toJSONString(this);
        }
        catch (Throwable e)
        {
            System.err.println("nc.bs.vo.ClogVO = " + StringUtil.toString(e));
            return super.toString();
        }

        // return super.toString();
    }

}
