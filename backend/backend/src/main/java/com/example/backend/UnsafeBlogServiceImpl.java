package com.example.backend;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import com.google.gson.Gson;
public class UnsafeBlogServiceImpl implements BlogService{
    
    private DatabaseSettings dbSettings;
    private Connection connection;
    private Statement statement;
    private ResultSet resultSet = null;
    private SimpleDateFormat sdf;
    
    public UnsafeBlogServiceImpl(){
	try {
	    Class.forName("com.mysql.jdbc.Driver");
	} catch (ClassNotFoundException e1) {
	    e1.printStackTrace();
	}
	dbSettings = new DatabaseSettings("blog", "blog", "jdbc:mysql://127.0.0.1:3306/blog");

	try {
	    connection = DriverManager.getConnection(dbSettings.getUrl(), dbSettings.getUsername(), dbSettings.getPassword());
	} catch (SQLException e) {
	    e.printStackTrace();
	}
	
	sdf = new SimpleDateFormat("yyyy-MM-dd");
    }

    public List<BlogPost> findAll() {
	List<BlogPost> posts = new ArrayList<BlogPost>();
	try {
	    statement = connection.createStatement();
	    resultSet = statement.executeQuery("SELECT * FROM posts");
	    BlogPost post = null;
	    while(resultSet.next()){
		post = new BlogPost(
			resultSet.getInt("id"),
			resultSet.getString("title"),
			resultSet.getString("body"),
			resultSet.getTimestamp("created"),
			resultSet.getTimestamp("updated"));
		posts.add(post);
	    }
	} catch (SQLException e) {
	    e.printStackTrace();
	} finally {
	    try {
		resultSet.close();
		statement.close();
	    } catch (SQLException e) {
		e.printStackTrace();
	    }
	}
	
	return posts;
    }

    public BlogPost getPost(int id) {
	BlogPost post = null;
	try {
	    statement = connection.createStatement();
	    resultSet = statement.executeQuery(""
	    	+ "SELECT * FROM posts where id = " + id + ";");
	    while(resultSet.next()){
		post = new BlogPost(
			resultSet.getInt("id"),
			resultSet.getString("title"),
			resultSet.getString("body"),
			resultSet.getTimestamp("created"),
			resultSet.getTimestamp("updated")
			);
	    }
	} catch(SQLException e) {
	    e.printStackTrace();
	} finally {
	    try {
		resultSet.close();
		statement.close();
	    } catch (SQLException e) {
		e.printStackTrace();
	    }
	}
	return post;
    }

    public void createNewPost(String body) {
	BlogPost post = new Gson().fromJson(body, BlogPost.class);
	try{
	    statement = connection.createStatement();
	    SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
	    statement.executeUpdate("INSERT INTO posts VALUES("
	    	+ "null, " 
		+ "'" + post.getTitle() + "', " 
		+ "'" + post.getBody() + "', " 
	    	+ "'" + sdf.format(new Date().getTime()) + "', " 
	    	+ "null);");
	    statement.close();
	} catch (SQLException e){
	    e.printStackTrace();
	}
	
    }

    public BlogPost updatePost(String id, String body) {
	BlogPost post = new Gson().fromJson(body, BlogPost.class);
	try{
	    statement = connection.createStatement();
	    statement.executeUpdate("UPDATE posts SET "
	    	+ "title = '" + post.getTitle() + "', "
	    	+ "body = '" + post.getBody() + "', "
	    	+ "created = '"  + sdf.format(post.getCreatedDate())+ "', "
	    	+ "updated = '" + sdf.format(new Date().getTime()) + "'"
	    	+ " WHERE id = " + id + ";");
	    statement.close();
	} catch (SQLException e){
	    e.printStackTrace();
	}
	return post;
    }
}
