package com.mduran.springboot.entity;


import org.springframework.data.cassandra.core.mapping.UserDefinedType;


@UserDefinedType(value = "metadataUDT")
public final class MetadataUDT {

    private String uri;
    private String request_id;
    private String topic;
    private String dt;

    public MetadataUDT(String uri, String request_id, String topic, String dt) {
        this.uri = uri;
        this.request_id = request_id;
        this.topic = topic;
        this.dt = dt;
    }

    public String getUri() {
        return uri;
    }

    public void setUri(String uri) {
        this.uri = uri;
    }

    public String getRequest_id() {
        return request_id;
    }

    public void setRequest_id(String request_id) {
        this.request_id = request_id;
    }

    public String getTopic() {
        return topic;
    }

    public void setTopic(String topic) {
        this.topic = topic;
    }

    public String getDt() {
        return dt;
    }

    public void setDt(String dt) {
        this.dt = dt;
    }
}
