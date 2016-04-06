package com.example.backend;

import static javax.persistence.GenerationType.IDENTITY;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table( name = "posts" )
public class BlogPost {
    private int id;
    private String title;
    private String body;
    private Date createdDate;
    private Date updateDate;
    
    public BlogPost(){}
    
    public BlogPost (int id, String title, String body, Date createdDate, Date updateDate){
	this.id = id;
	this.title = title;
	this.body = body;
	this.createdDate = createdDate;
	this.updateDate = updateDate;
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

    @Column(name = "title")
    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
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
    
    
}
