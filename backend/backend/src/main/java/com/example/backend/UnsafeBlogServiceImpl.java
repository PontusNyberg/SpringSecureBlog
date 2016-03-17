package com.example.backend;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
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
    
    public List<Comment> findAllComments(int postId) {
	List<Comment> comments = new ArrayList<Comment>();
	try {
	    statement = connection.createStatement();
	    resultSet = statement.executeQuery("SELECT * FROM comments where post_id=" + postId);
	    Comment comment = null;
	    while(resultSet.next()){
		comment = new Comment(
			resultSet.getInt("id"),
			resultSet.getString("name"),
			resultSet.getString("body"),
			resultSet.getTimestamp("created"),
			resultSet.getTimestamp("updated"),
			resultSet.getInt("post_id")
			);
		comments.add(comment);
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
	
	return comments;
    }

    public Comment getComment(int id) {
	Comment comment = null;
	try {
	    statement = connection.createStatement();
	    resultSet = statement.executeQuery(""
	    	+ "SELECT * FROM comment where id = " + id + ";");
	    while(resultSet.next()){
		comment = new Comment(
			resultSet.getInt("id"),
			resultSet.getString("name"),
			resultSet.getString("body"),
			resultSet.getTimestamp("created"),
			resultSet.getTimestamp("updated"),
			resultSet.getInt("post_id")
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
	return comment;
    }
    
    public void createNewComment(String body) {
	Comment comment = new Gson().fromJson(body, Comment.class);
	try{
	    statement = connection.createStatement();
	    SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
	    statement.executeUpdate("INSERT INTO comments VALUES("
	    	+ "null, " 
		+ "'" + comment.getName() + "', " 
		+ "'" + comment.getBody() + "', " 
	    	+ "'" + sdf.format(new Date().getTime()) + "', " 
	    	+ "null,"
	    	+ "'" + comment.getPostId() + "');");
	    statement.close();
	} catch (SQLException e){
	    e.printStackTrace();
	}
	
    }

    public Comment updateComment(String id, String body) {
	Comment comment = new Gson().fromJson(body, Comment.class);
	try{
	    statement = connection.createStatement();
	    statement.executeUpdate("UPDATE comments SET "
	    	+ "name = '" + comment.getName() + "', "
	    	+ "body = '" + comment.getBody() + "', "
	    	+ "created = '"  + sdf.format(comment.getCreatedDate())+ "', "
	    	+ "updated = '" + sdf.format(new Date().getTime()) + "'"
	    	+ " WHERE id = " + id + ";");
	    statement.close();
	} catch (SQLException e){
	    e.printStackTrace();
	}
	
	return comment;
    }
}
