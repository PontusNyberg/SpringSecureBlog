package com.example.backend;

import java.util.Date;

public class Comment {
    
    private int id;
    private String name;
    private String body;
    private Date createdDate;
    private Date updateDate;
    private int postId;
    
    public Comment (int id, String name, String body, Date createdDate, Date updateDate, int postId){
	this.id = id;
	this.name = name;
	this.body = body;
	this.createdDate = createdDate;
	this.updateDate = updateDate;
	this.postId = postId;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
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
    
    public int getPostId() {
        return postId;
    }

    public void setPostId(int postId ) {
        this.postId = postId;
    }
    
}
