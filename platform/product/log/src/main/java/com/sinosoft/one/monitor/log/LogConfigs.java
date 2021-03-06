package com.sinosoft.one.monitor.log;


import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;

import com.sinosoft.one.monitor.notification.NotificationConfiguration;
import com.sinosoft.one.monitor.notification.NotificationService;
import com.sinosoft.one.monitor.notification.NotificationServiceFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.jmx.export.annotation.*;

import javax.annotation.PostConstruct;
import java.util.*;

@ManagedResource(objectName = LogConfigs.MBEAN_NAME, description = "Log Config Management Bean")
public class LogConfigs {
    public static final String MBEAN_NAME = "log:name=LogConfigs";
    private Logger logger = LoggerFactory.getLogger(getClass());
    private List<LogUrl> urls = new ArrayList<LogUrl>();
	private String applicationName = "";

    @PostConstruct
    public void init() {
        try {
            initData();
        } catch (Exception e) {
            logger.error("init log config exception.", e);
        }
    }


    private void initData() {
		NotificationService notificationService = NotificationServiceFactory.buildNotificationService();
	    String initUrls = notificationService.getUrlData();
	    if(initUrls == null || initUrls.equals("")) {
		    return;
	    }
	    JSONArray urlArray = JSON.parseArray(initUrls);
	    for(int i=0, urlSize=urlArray.size(); i<urlSize; i++) {
		    JSONObject urlObject = urlArray.getJSONObject(i);
		    LogUrl logUrl = new LogUrl(urlObject.getString("urlId"), urlObject.getString("urlAddress"));
		    JSONArray methodArray = urlObject.getJSONArray("methods");
		    for(int j=0, methodSize=methodArray.size(); j<methodSize; j++ ) {
			    JSONObject methodObject = methodArray.getJSONObject(j);
			    logUrl.addLogMethod(new LogMethod(methodObject.getString("methodId"), methodObject.getString("className"), methodObject.getString("methodName")));
		    }
		    urls.add(logUrl);
	    }
	    applicationName = notificationService.getApplicationName();
    }

	public String isMonitorUrl(String url) {
		Iterator<LogUrl> iterator = urls.iterator();
		while (iterator.hasNext()) {
			LogUrl logUrl = iterator.next();
			String tempUrl = logUrl.getUrl().startsWith("/") ? logUrl.getUrl() : "/" + logUrl.getUrl();
			tempUrl = tempUrl.endsWith("/") ? tempUrl : tempUrl + "/";
			if(!url.startsWith("/")) {
				url = "/" + url;
			}
			if(!url.endsWith("/")) {
				url = url + "/";
			}
			if(("/" + applicationName  + tempUrl).equalsIgnoreCase(url)) {
				return logUrl.getId();
			}
		}
		return null;
	}

	public String getRealPath(String url) {
		return url.replace("/" + applicationName, "");
	}

	public Set<LogMethod> getLogMethods(String urlId) {
		Iterator<LogUrl> iterator = urls.iterator();
		while (iterator.hasNext()) {
			LogUrl logUrl = iterator.next();
			if(logUrl.getId().equalsIgnoreCase(urlId)) {
				return logUrl.getLogMethodSet();
			}
		}
		return Collections.EMPTY_SET;
	}
    @ManagedOperation(description = "Add a new log url.")
    @ManagedOperationParameters({
		@ManagedOperationParameter(name = "id", description = "Request id of log url."),
        @ManagedOperationParameter(name = "url", description = "Request url of log url.")
    })
    public void addLogUrl(String id, String url) {
	    Iterator<LogUrl> iterator = urls.iterator();
	    while (iterator.hasNext()) {
		    LogUrl logUrl = iterator.next();
		    if(logUrl.getId().equalsIgnoreCase(id)) {
			    logUrl.setUrl(url);
			    return;
		    }
	    }
        urls.add(new LogUrl(id, url));
    }

    @ManagedOperation(description = "Remove a url.")
    @ManagedOperationParameters({@ManagedOperationParameter(name = "id", description = "Url id to remove.")})
    public void removeLogUrl(String id) {
        Iterator<LogUrl> iterator = urls.iterator();
        while (iterator.hasNext()) {
            if(iterator.next().getId().equalsIgnoreCase(id)) {
                iterator.remove();
                return;
            }
        }
    }

    @ManagedOperation(description = "Add a new log method.")
    @ManagedOperationParameters({
		@ManagedOperationParameter(name = "urlId", description = "Method id of log method."),
		@ManagedOperationParameter(name = "methodId", description = "Method id of log method."),
        @ManagedOperationParameter(name = "className", description = "Class name of log method."),
        @ManagedOperationParameter(name = "methodName", description = "Method name of log method."),
    })
    public void addLogMethod(String urlId, String methodId, String className, String methodName) {
	    Iterator<LogUrl> iterator = urls.iterator();
	    while (iterator.hasNext()) {
		    LogUrl logUrl = iterator.next();
		    if(logUrl.getId().equals(urlId)) {
			    Set<LogMethod> logMethods = logUrl.getLogMethodSet();
			    Iterator<LogMethod> logMethodIterator = logMethods.iterator();
			    while(logMethodIterator.hasNext()) {
				    LogMethod logMethod = logMethodIterator.next();
				    if(logMethod.getId().equals(methodId)) {
					    logMethod.setClassName(className);
					    logMethod.setMethodName(methodName);
					    return;
				    }
			    }
			    logUrl.addLogMethod(new LogMethod(methodId, className, methodName));
		    }
	    }
    }


    @ManagedOperation(description = "Remove a log method.")
    @ManagedOperationParameters({
        @ManagedOperationParameter(name = "urlId", description = "Url id of log method to remove."),
        @ManagedOperationParameter(name = "methodId", description = "Method id of log method to remove.")
    })
    public void removeLogMethod(String urlId, String methodId) {
        Iterator<LogUrl> iterator = urls.iterator();
        while (iterator.hasNext()) {
            LogUrl logUrl = iterator.next();
            if(logUrl.getId().equals(urlId)) {
		        Set<LogMethod> logMethods = logUrl.getLogMethodSet();
		        Iterator<LogMethod> logMethodIterator = logMethods.iterator();
	            while(logMethodIterator.hasNext()) {
		            LogMethod logMethod = logMethodIterator.next();
		            if(logMethod.getId().equals(methodId)) {
			            logMethodIterator.remove();
			            return;
		            }
	            }
            }
        }
    }

	@ManagedOperation(description = "Remove application.")
	public void removeApplication() {
		NotificationConfiguration.getInstance().removeApplication();
	}
}
