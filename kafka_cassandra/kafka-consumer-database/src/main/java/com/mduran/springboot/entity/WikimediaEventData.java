package com.mduran.springboot.entity;

import com.datastax.oss.driver.api.core.type.DataType;
import com.datastax.oss.driver.api.core.type.DataTypes;
import com.datastax.oss.driver.api.core.data.UdtValue;
import lombok.Data;

import org.springframework.data.cassandra.core.mapping.CassandraType;
import org.springframework.data.cassandra.core.mapping.*;

import java.util.Objects;

import static java.util.Objects.requireNonNull;


@Data
@Table("wikimediaevent_recentchange")

public final class WikimediaEventData { // don't want any extensions of this class

    @Column
    @PrimaryKey
    private String name;

    @Column(value = "wiki_event_data")
    private String wikiEventData;

    @Column(value = "web_url")
    private String webUrl;

    // inserting in my User Defined Type (UDT)
    // Does not work
//    @Frozen
//    @Column
////    @CassandraType(type = CassandraType.Name.UDT , userTypeName = "metadataUDT")
//    @CassandraType(type = new , userTypeName = "metadataUDT")
//    private MetadataUDT entityMetadataUDT;



    public WikimediaEventData(WikimediaEventDataBuilder builder) {

        requireNonNull(builder, "builder for WikiMediaEventData should not be null");

        this.name = builder.name;
        this.wikiEventData = builder.wikiEventData;
        this.webUrl = builder.webUrl;
//        this.entityMetadataUDT = builder.entityMetadataUDT;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getWikiEventData() {
        return wikiEventData;
    }

    public void setWikiEventData(String wikiEventData) {
        this.wikiEventData = wikiEventData;
    }

    public String getWebUrl() {
        return webUrl;
    }

    public void setWebUrl(String webUrl) {
        this.webUrl = webUrl;
    }

//    public MetadataUDT getEntityMetadataUDT() {
//        return entityMetadataUDT;
//    }
//
//    public void setEntityMetadataUDT(MetadataUDT entityMetadataUDT) {
//        this.entityMetadataUDT = entityMetadataUDT;
//    }

    /**
     * Builder helper methods, that will call the static nested class to create the immutable
     * POJO
     */

    // build from scratch
    public static WikimediaEventDataBuilder newBuilder() { return new WikimediaEventDataBuilder(); }

    // build using a copy
    public static WikimediaEventDataBuilder newBuilder(WikimediaEventData copy) {

        requireNonNull(copy, "WikimediaEventData copy should not be null");

        WikimediaEventDataBuilder builder = new WikimediaEventDataBuilder();
        builder.withName(copy.getName());
        builder.withWebUrl(copy.getWebUrl());
        builder.withWikiEventData(copy.getWikiEventData());
//        builder.withUDT(copy.getEntityMetadataUDT());

        return builder;
    }


    /**
     * Static Inner class builder, which allows me to build immutable WikiMediaEventData
     * objects. This would be necessary if I were doing some concurrent activities.
     */
    public static final class WikimediaEventDataBuilder {

        private String name;
        private String wikiEventData;
        private String webUrl;
//        private MetadataUDT entityMetadataUDT;

        // create an empty POJO
        private WikimediaEventDataBuilder() {}

        // Setters for all of the instance vars
        public WikimediaEventDataBuilder withName(String name) {
            this.name = name;
            return this;
        }

        public WikimediaEventDataBuilder withWikiEventData(String event) {
            this.wikiEventData = event;
            return this;
        }

        public WikimediaEventDataBuilder withWebUrl(String url) {
            this.webUrl = url;
            return this;
        }

//        public WikimediaEventDataBuilder withUDT(MetadataUDT udt) {
//            this.entityMetadataUDT = udt;
//            return this;
//        }

        // final build method that will create the immutable object
        public WikimediaEventData build() { return new WikimediaEventData(this); }
    }

    @Override
    public String toString() {
        return "WikimediaEventData{" +
                "name='" + name + '\'' +
                ", wikiEventData='" + wikiEventData + '\'' +
                ", webUrl='" + webUrl + '\'' +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        WikimediaEventData that = (WikimediaEventData) o;
        return Objects.equals(name, that.name) && Objects.equals(wikiEventData, that.wikiEventData) && Objects.equals(webUrl, that.webUrl);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, wikiEventData, webUrl);
    }
}
