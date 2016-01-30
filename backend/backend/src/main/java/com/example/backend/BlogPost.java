package com.example.backend;

import java.util.Date;

public class BlogPost {
    
    private String id;
    private String title;
    private String body;
    private Date createdDate = new Date();
    private Date updateDate;
    
    public BlogPost (String id, String title, String body){
	this.id = id;
	this.title = title;
	this.body = body;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getBody() {
        return body;
    }

    public void setBody(String body) {
        this.body = body;
    }
    
    public Date getCreatedDate(){
	return this.createdDate;
    }

    public Date getUpdateDate() {
        return updateDate;
    }

    public void setUpdateDate() {
        this.updateDate = new Date();
    }
    
    
}
