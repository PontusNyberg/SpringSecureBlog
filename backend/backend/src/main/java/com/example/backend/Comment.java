package com.example.backend;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

import static javax.persistence.GenerationType.IDENTITY;

@Entity
@Table( name = "comments" )
public class Comment {
    private int id;
    private String name;
    private String body;
    private Date createdDate;
    private Date updateDate;
    private int postId;
    
    public Comment(){}
    
    public Comment (int id, String name, String body, Date createdDate, Date updateDate, int postId){
	this.id = id;
	this.name = name;
	this.body = body;
	this.createdDate = createdDate;
	this.updateDate = updateDate;
	this.postId = postId;
    }

    @Id
    @GeneratedValue(strategy = IDENTITY)
    
    @Column(name = "id")
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @Column(name = "name")
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Column(name = "body")
    public String getBody() {
        return body;
    }

    public void setBody(String body) {
        this.body = body;
    }
    
    @Column(name = "created")
    public Date getCreatedDate(){
	return this.createdDate;
    }
    
    public void setCreatedDate(Date createDate){
	this.createdDate = createDate;
    }

    @Column(name = "updated")
    public Date getUpdateDate() {
        return updateDate;
    }

    public void setUpdateDate(Date updateDate) {
        this.updateDate = updateDate;
    }
    
    @Column(name = "post_id")
    public int getPostId() {
        return postId;
    }

    public void setPostId(int postId ) {
        this.postId = postId;
    }
    
}
