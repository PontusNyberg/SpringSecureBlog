package com.example.backend;

import java.util.Date;

public class BlogPost {
    
    private int id;
    private String title;
    private String body;
    private Date createdDate;
    private Date updateDate;
    
    public BlogPost (int id, String title, String body, Date createdDate, Date updateDate){
	this.id = id;
	this.title = title;
	this.body = body;
	this.createdDate = createdDate;
	this.updateDate = updateDate;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
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
    
    public void setCreatedDate(Date createDate){
	this.createdDate = createDate;
    }

    public Date getUpdateDate() {
        return updateDate;
    }

    public void setUpdateDate(Date updateDate) {
        this.updateDate = updateDate;
    }
    
    
}
