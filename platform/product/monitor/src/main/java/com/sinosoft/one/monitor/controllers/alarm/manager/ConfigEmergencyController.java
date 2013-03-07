package com.sinosoft.one.monitor.controllers.alarm.manager;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.sinosoft.one.monitor.action.model.MailAction;
import com.sinosoft.one.monitor.action.repository.MailActionRepository;
import com.sinosoft.one.monitor.application.domain.ApplicationService;
import com.sinosoft.one.monitor.application.model.Application;
import com.sinosoft.one.monitor.attribute.domain.AttributeCache;
import com.sinosoft.one.monitor.attribute.domain.AttributeService;
import com.sinosoft.one.monitor.attribute.model.Attribute;
import com.sinosoft.one.monitor.attribute.model.AttributeAction;
import com.sinosoft.one.monitor.attribute.repository.AttributeActionRepository;
import com.sinosoft.one.monitor.attribute.repository.AttributeRepository;
import com.sinosoft.one.monitor.common.ResourceType;
import com.sinosoft.one.monitor.db.oracle.model.Info;
import com.sinosoft.one.monitor.db.oracle.repository.InfoRepository;
import com.sinosoft.one.monitor.os.linux.model.Os;
import com.sinosoft.one.monitor.os.linux.repository.OsRepository;
import com.sinosoft.one.monitor.threshold.model.SeverityLevel;
import com.sinosoft.one.mvc.web.Invocation;
import com.sinosoft.one.mvc.web.annotation.Param;
import com.sinosoft.one.mvc.web.annotation.Path;
import com.sinosoft.one.mvc.web.annotation.rest.Get;
import com.sinosoft.one.mvc.web.annotation.rest.Post;
import com.sinosoft.one.mvc.web.instruction.reply.Reply;
import com.sinosoft.one.mvc.web.instruction.reply.Replys;
import com.sinosoft.one.uiutil.Gridable;
import com.sinosoft.one.uiutil.UIType;
import com.sinosoft.one.uiutil.UIUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;

import java.util.ArrayList;
import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: zfb
 * Date: 13-3-5
 * Time: 上午10:56
 * To change this template use File | Settings | File Templates.
 */
@Path("configemergency")
public class ConfigEmergencyController {
    @Autowired
    AttributeService attributeService;
    @Autowired
    ApplicationService applicationService;
    @Autowired
    InfoRepository infoRepository;
    @Autowired
    OsRepository osRepository;
    @Autowired
    AttributeCache attributeCache;
    @Autowired
    AttributeRepository attributeRepository;
    @Autowired
    MailActionRepository mailActionRepository;
    @Autowired
    AttributeActionRepository attributeActionRepository;

    @Get("config")
    public String configEmergencyForm(Invocation inv){
        return "setEmergency";
    }

    //配置告警页面，选择监视器类型时，得到相应类型下的所有可用的监视器
    @Post("monitornames/{resourceType}")
    public Reply getMonitorNames(@Param("resourceType") String resourceType, Invocation inv) throws Exception {
        JSONArray jsonArray = new JSONArray();
        String jsonMonitorNames="";
        //@todo 使用枚举类型判断
        if ("应用系统".equals(resourceType)) {
            List<Application> applications = applicationService.findAllApplicationNames();
            if (applications != null) {
                for (Application application : applications) {
                    JSONObject jsonObject = new JSONObject();
                    jsonObject.put("monitorId",application.getId());
                    //获取应用中文名
                    jsonObject.put("monitorName", application.getCnName());
                    jsonArray.add(jsonObject);
                }
                jsonMonitorNames = jsonArray.toJSONString();
                return Replys.with(jsonMonitorNames);
            }
            return null;
        }else if("数据库".equals(resourceType)){
            List<Info> dbInfos= (List<Info>) infoRepository.findAll();
            if (dbInfos!=null){
                for (Info dbInfo : dbInfos) {
                    JSONObject jsonObject = new JSONObject();
                    jsonObject.put("monitorId",dbInfo.getId());
                    //获取GE_MONITOR_ORACLE_INFO表的NAME字段值
                    jsonObject.put("monitorName", dbInfo.getName());
                    jsonArray.add(jsonObject);
                }
                jsonMonitorNames = jsonArray.toJSONString();
                return Replys.with(jsonMonitorNames);
            }
            return null;
        }else if("操作系统".equals(resourceType)){
            List<Os> oses= (List<Os>) osRepository.findAll();
            if (oses!=null){
                for (Os os : oses) {
                    JSONObject jsonObject = new JSONObject();
                    jsonObject.put("monitorId",os.getOsInfoId());
                    //获取GE_MONITOR_OS表的NAME字段值
                    jsonObject.put("monitorName", os.getName());
                    jsonArray.add(jsonObject);
                }
                jsonMonitorNames = jsonArray.toJSONString();
                return Replys.with(jsonMonitorNames);
            }
            return null;
        }else {
            return null;
        }
    }

    @Post("attributenames/{resourceType}")
    public void getAttributeNames(@Param("resourceType") String resourceType,Invocation inv) throws Exception {
        String dbResourceType=getResourceEnumString(resourceType);
        if(!"".equals(dbResourceType)){
            List<Attribute> attributes=attributeService.findAllAttributesWithResourceType(dbResourceType);
            Page page=new PageImpl(attributes);
            Gridable<Attribute> gridable=new Gridable<Attribute>(page);
            gridable.setIdField("id");
            gridable.setCellStringField("attributeCn,threshold,action");
            try {
                UIUtil.with(gridable).as(UIType.Json).render(inv.getResponse());
            } catch (Exception e) {
                throw new Exception("Json数据转换出错!",e);
            }
        }
        Replys.with(null);
    }

    public String getResourceEnumString(String resourceName){
        String dbResourceType="";
        ResourceType[] resourceTypes=ResourceType.values();
        for (ResourceType newResourceType:resourceTypes){
            if (newResourceType.cnName().equals(resourceName)){
                dbResourceType=newResourceType.name();
                break;
            }
        }
        return dbResourceType;
    }

    //获得监视器名称，属性名称
    @Get("health/{resourceType}/{monitorId}/{attributeName}")
    public String setHealthForm(@Param("resourceType") String resourceType,@Param("monitorId") String monitorId,
                                @Param("attributeName") String attributeName,Invocation inv){
        //获得监视器名称（也就是应用中文名）
        inv.addModel("monitorName",applicationService.findApplication(monitorId).getCnName());
        //写回应用id
        inv.getRequest().setAttribute("monitorId",monitorId);
        /*inv.addModel("monitorId",monitorId);*/
        String attributeId= attributeCache.getAttributeId(getResourceEnumString(resourceType),attributeName);
        //写回属性id
        inv.getRequest().setAttribute("attributeId",attributeId);
        /*inv.addModel("attributeId",attributeId);*/
        //获得属性名字
        inv.addModel("attributeName",attributeRepository.findOne(attributeId).getAttributeCn());
        inv.getRequest().setAttribute("resourceType",resourceType);
        //返回配置告警--健康度页面
        return "setHealth";
    }

    //获得所有动作,如果当前属性有关联的动作，放入右边框中
    @Post("actions/{monitorId}/{attributeId}")
    public Reply getAllActions(@Param("monitorId") String monitorId,@Param("attributeId") String attributeId,Invocation inv){
        List<MailAction> mailActions= (List<MailAction>) mailActionRepository.findAll();
        if (mailActions!=null){
            JSONArray jsonArray = new JSONArray();
            String jsonActionNames="";
            for (MailAction mailAction : mailActions) {
                JSONObject jsonObject = new JSONObject();
                List<String> severityLevels=new ArrayList<String>();
                severityLevels=attributeActionRepository.findAllSeverityWithCurrentAttribute(monitorId,attributeId,mailAction.getId());
                JSONArray jsonSeverityArray=new JSONArray();
                if(severityLevels!=null&&severityLevels.size()>0){
                    for(String severityLevel:severityLevels){
                        JSONObject jsonSeverityObject=new JSONObject();
                        String severityLevelName=SeverityLevel.CRITICAL.name();
                        if(SeverityLevel.CRITICAL.name().equals(severityLevel)){
                            jsonSeverityObject.put("several","严重");
                        }else if(SeverityLevel.WARNING.name().equals(severityLevel)){
                            jsonSeverityObject.put("several","警告");
                        }else if(SeverityLevel.INFO.name().equals(severityLevel)){
                            jsonSeverityObject.put("several","正常");
                        }
                        jsonSeverityArray.add(jsonSeverityObject);
                    }
                }
                //获取动作关联属性的严重程度
                jsonObject.put("actionSeverity",jsonSeverityArray);
                jsonObject.put("actionId",mailAction.getId());
                jsonObject.put("actionName", mailAction.getName());
                jsonArray.add(jsonObject);
            }
            jsonActionNames = jsonArray.toJSONString();
            return Replys.with(jsonActionNames);
        }
        return null;
    }

    @Post("save/{monitorId}/{attributeId}")
    public String saveHealthConfig(@Param("monitorId") String monitorId,
                                   @Param("attributeId") String attributeId,Invocation inv){
        //相应的动作的id
        String[] criticalIds=inv.getRequest().getParameterValues("CRITICAL[]");
        String[] warningIds=inv.getRequest().getParameterValues("WARNING[]");
        String[] infoIds=inv.getRequest().getParameterValues("INFO[]");
        List<AttributeAction> attributeActions=new ArrayList<AttributeAction>();
        if(criticalIds!=null&&criticalIds.length>0){
            for(String criticalId:criticalIds){
                AttributeAction attributeAction=new AttributeAction();
                attributeAction.setResourceId(monitorId);
                //需要得到属性id
                attributeAction.setAttributeId(attributeId);
                attributeAction.setActionId(criticalId);
                attributeAction.setSeverity(SeverityLevel.CRITICAL);
                attributeActions.add(attributeAction);
            }
        }
        if(warningIds!=null&&warningIds.length>0){
            for(String warningId:warningIds){
                AttributeAction attributeAction=new AttributeAction();
                attributeAction.setResourceId(monitorId);
                //需要得到属性id
                attributeAction.setAttributeId(attributeId);
                attributeAction.setActionId(warningId);
                attributeAction.setSeverity(SeverityLevel.WARNING);
                attributeActions.add(attributeAction);
            }
        }
        if(infoIds!=null&&infoIds.length>0){
            for(String infoId:infoIds){
                AttributeAction attributeAction=new AttributeAction();
                attributeAction.setResourceId(monitorId);
                //需要得到属性id
                attributeAction.setAttributeId(attributeId);
                attributeAction.setActionId(infoId);
                attributeAction.setSeverity(SeverityLevel.INFO);
                attributeActions.add(attributeAction);
            }
        }
        if(attributeActions!=null&&attributeActions.size()>0){
            List<AttributeAction> dbAttributeActions=new ArrayList<AttributeAction>();
            dbAttributeActions=attributeActionRepository.findAllAttributeActionsWithAttributeId(attributeId);
            //如果db中已经有当前属性关联的记录，那么将这些记录全部删除
            attributeActionRepository.delete(dbAttributeActions);
            //之后，保存当前属性新关联的动作
            for(AttributeAction attributeAction:attributeActions){
                attributeActionRepository.save(attributeAction);
            }
        }
        return "setEmergency";
    }
}
