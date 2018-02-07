package com.developer.nikhil.fillbelly.model;


import java.io.Serializable;

public class CollectionBean implements Serializable {

    public String collection_id;
    public String description;
    public String image_url;
    public String res_count;
    public String share_url;
    public String title;
    public String url;


    public CollectionBean() {
    }

    public CollectionBean(String collection_id, String description, String image_url, String res_count, String share_url,
                     String title, String url) {

        this.collection_id = collection_id;
        this.description = description;
        this.image_url = image_url;
        this.res_count = res_count;
        this.share_url = share_url;
        this.title = title;
        this.url = url;
    }

    public String getCollection_id() {
        return collection_id;
    }

    public void setCollection_id(String collection_id) {
        this.collection_id = collection_id;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getImage_url() {
        return image_url;
    }

    public void setImage_url(String image_url) {
        this.image_url = image_url;
    }

    public String getRes_count() {
        return res_count;
    }

    public void setRes_count(String res_count) {
        this.res_count = res_count;
    }

    public String getShare_url() {
        return share_url;
    }

    public void setShare_url(String share_url) {
        this.share_url = share_url;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }
}
