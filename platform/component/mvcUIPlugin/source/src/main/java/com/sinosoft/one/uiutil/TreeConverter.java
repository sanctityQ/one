package com.sinosoft.one.uiutil;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.sinosoft.one.util.reflection.ReflectionUtils;
import org.apache.commons.beanutils.BeanUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import java.util.Collection;

/**
 * Created with IntelliJ IDEA.
 * User: Administrator
 * Date: 12-10-12
 * Time: 上午11:14
 * To change this template use File | Settings | File Templates.
 */
public class TreeConverter<T> implements Converter<Treeable> {
    private static Log log= LogFactory.getLog(TreeConverter.class);
	//@todo 常量需要用大写和下划线定义 ID_ELEMENT = "id"(OK);
    private static final String  ID_ELEMENT= "id";
    private static final String  ATTR_ELEMENT= "attr";
    private static final String  DATA_ELEMENT= "data";
    private static final String  TITLE_ELEMENT= "title";
    private static final String  CLASS_ELEMENT= "class";
    private static final String  HREF_ELEMENT= "href";
    private static final String  CHILDREN_ELEMENT= "children";
    private static final String  STATE_ELEMENT= "state";
    private static final String  CLASS_DEFAULT_VALUE= "";
    private static final String  HREF_DEFAULT_VALUE= "javascript:void(0);";
    private JSONArray jsonArray;

    public String toJson(Treeable treeable) {
        if (treeable == null) {
            log.info("the treeable object is null.");
            return null;
        } else {
			//@todo 同GridConverter处理
            jsonArray = addSubItemObject(treeable.getContent(), treeable);
            return jsonArray.toString();
        }
    }

    private JSONArray addSubItemObject(Object children, Treeable treeable) {
        JSONArray jsonArray = new JSONArray();
        if (children instanceof Collection) {
            Collection subChildren = (Collection) children;
            for (Object obj : subChildren) {
                JSONObject jsonObject = new JSONObject();
                JSONObject jsonAttrObject = new JSONObject();
                JSONObject dataItemObject = new JSONObject();
                JSONObject dataAttrItemObject = new JSONObject();
                try {
                    jsonAttrObject.put(ID_ELEMENT, BeanUtils.getProperty(obj, treeable.getIdField()));
                    jsonObject.put(ATTR_ELEMENT, jsonAttrObject);
                    dataItemObject.put(TITLE_ELEMENT, BeanUtils.getProperty(obj, treeable.getTitleField()));
                    if (BeanUtils.getProperty(obj, treeable.getClassField()) == null || BeanUtils.getProperty(obj, treeable.getClassField()).isEmpty()) {
                        dataAttrItemObject.put(CLASS_ELEMENT, CLASS_DEFAULT_VALUE);
                    } else {
                        dataAttrItemObject.put(CLASS_ELEMENT, BeanUtils.getProperty(obj, treeable.getClassField()));
                    }
                    if (BeanUtils.getProperty(obj, treeable.getUrlField()) == null || BeanUtils.getProperty(obj, treeable.getUrlField()).isEmpty()) {
                        dataAttrItemObject.put(HREF_ELEMENT, HREF_DEFAULT_VALUE);
                    } else {
                        dataAttrItemObject.put(HREF_ELEMENT, BeanUtils.getProperty(obj, treeable.getUrlField()));
                    }
                    dataItemObject.put(ATTR_ELEMENT, dataAttrItemObject);
                    jsonObject.put(DATA_ELEMENT, dataItemObject);
                    if (hasSubNode(obj, treeable)) {
                        Object subSubChildren = ReflectionUtils.getFieldValue(obj, treeable.getChildrenField());
                        jsonObject.put(CHILDREN_ELEMENT, addSubItemObject(subSubChildren, treeable));
                    }
                    jsonObject.put(STATE_ELEMENT, BeanUtils.getProperty(obj, treeable.getStateField()));
                    jsonArray.add(jsonObject);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
            return jsonArray;
        }else {
            log.error("the children node's type must be a 'Collection' type.");
        }
        return jsonArray;
    }

    private Boolean hasSubNode(Object object, Treeable treeable) {
        if (ReflectionUtils.getFieldValue(object, treeable.getChildrenField()) != null) {
            return Boolean.TRUE;
        } else {
            return Boolean.FALSE;
        }
    }

    public String toXml(Treeable treeable) {
        return null;  //To change body of implemented methods use File | Settings | File Templates.
    }
}