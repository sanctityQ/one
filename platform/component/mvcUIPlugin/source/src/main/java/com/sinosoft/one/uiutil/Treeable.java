package com.sinosoft.one.uiutil;

import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: Administrator
 * Date: 12-10-12
 * Time: 上午10:52
 * To change this template use File | Settings | File Templates.
 */
public class Treeable<T> implements UIable {
    private String idField;
    private String titleField;
    private String classField;
    private String urlField;
    private String childrenField;
    private String stateField;
    private String typeField;
    private List<T> content;

    public static class Builder<T> {
        private List<T> content;
        private String idField;
        private String titleField;
        private String childrenField;
        private String stateField;
        private String classField;
        private String urlField;
        private String typeField;

        public Builder(List<T> content, String idField, String titleField, String childrenField, String stateField) {
            this.content = content;
            this.idField = idField;
            this.titleField = titleField;
            this.childrenField = childrenField;
            this.stateField = stateField;
        }

        public Builder classField(String val) {
            classField = val;
            return this;
        }

        public Builder urlField(String val) {
            urlField = val;
            return this;
        }

        public Builder typeField(String val) {
            typeField = val;
            return this;
        }

        public Treeable builder() {
            return new Treeable(this);
        }
    }

    private Treeable(Builder builder) {
        content = builder.content;
        idField = builder.idField;
        titleField = builder.titleField;
        childrenField = builder.childrenField;
        stateField = builder.stateField;
        classField = builder.classField;
        urlField = builder.urlField;
        typeField = builder.typeField;
    }

    public String getIdField() {
        return idField;
    }

    public void setIdField(String idField) {
        this.idField = idField;
    }

    public String getClassField() {
        return classField;
    }

    public void setClassField(String classField) {
        this.classField = classField;
    }

    public String getStateField() {
        return stateField;
    }

    public void setStateField(String stateField) {
        this.stateField = stateField;
    }

    public List<T> getContent() {
        return content;
    }

    public void setContent(List<T> content) {
        this.content = content;
    }

    public String getUrlField() {
        return urlField;
    }

    public void setUrlField(String urlField) {
        this.urlField = urlField;
    }

    public String getChildrenField() {
        return childrenField;
    }

    public void setChildrenField(String childrenField) {
        this.childrenField = childrenField;
    }

    public String getTitleField() {
        return titleField;
    }

    public void setTitleField(String titleField) {
        this.titleField = titleField;
    }

    public String getTypeField() {
        return typeField;
    }

    public void setTypeField(String typeField) {
        this.typeField = typeField;
    }

    public Render getRender() {
        return new TreeRender(new TreeConverter(), this);
    }
}
