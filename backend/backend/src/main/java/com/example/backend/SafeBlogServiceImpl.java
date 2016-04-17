package com.example.backend;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;

import com.google.gson.Gson;

public class SafeBlogServiceImpl implements BlogService {
    private Session session = null;

    @SuppressWarnings("unchecked")
    @Override
    public List<BlogPost> findAll() {
 	List<BlogPost> queryList = (List<BlogPost>) getQueryList("from BlogPost s", null, -1);
	if(queryList != null && queryList.isEmpty()){
	    return new ArrayList<BlogPost>();
	} else {
	    return queryList;  
	}
    }

    @SuppressWarnings("unchecked")
    @Override
    public BlogPost getPost(int id) {
	List<BlogPost> queryList = (List<BlogPost>) getQueryList("from BlogPost s where s.id = :id", "id", id);
	if(queryList != null && queryList.isEmpty()){
	    return new BlogPost();
	} else {
	    return queryList.get(0);
	}
    }

    @Override
    public void createNewPost(String body) {
	BlogPost post = new Gson().fromJson(body, BlogPost.class);
	post.setCreatedDate(new Date());
	
	session = HibernateConnector.getInstance().getSession();
	Transaction transaction = session.beginTransaction();
	
	session.save(post);
	transaction.commit();
	session.close();
    }

    @Override
    public BlogPost updatePost(String id, String body) {
	BlogPost post = new Gson().fromJson(body, BlogPost.class);
	
	session = HibernateConnector.getInstance().getSession();
	session.saveOrUpdate(post);
	session.flush();
	
	return post;
    }

    @SuppressWarnings("unchecked")
    @Override
    public List<Comment> findAllComments(int postId) {
 	List<Comment> queryList = (List<Comment>) getQueryList("from Comment c where c.postId = :postId", "postId", postId);
	    return queryList;
    }

    @SuppressWarnings("unchecked")
    @Override
    public Comment getComment(int id) {
	List<Comment> queryList = (List<Comment>) getQueryList("from Comment c where c.id = :id", "id", id);
	if(queryList != null && queryList.isEmpty()){
	    return new Comment();
	} else {
	    return queryList.get(0);
	}
    }

    @Override
    public void createNewComment(String body) {
	Comment comment = new Gson().fromJson(body, Comment.class);
	
	session = HibernateConnector.getInstance().getSession();
	Transaction transaction = session.beginTransaction();
	
	session.save(comment);
	transaction.commit();
	session.close();
    }

    @Override
    public Comment updateComment(String id, String body) {
	Comment comment = new Gson().fromJson(body, Comment.class);
	
	session = HibernateConnector.getInstance().getSession();
	session.saveOrUpdate(comment);
	session.flush();
	
	return comment;
    }
    
    private List<?> getQueryList(String queryStr, String paramName, int param){
	Query query = createQuery(queryStr);
	if(paramName != null && param != -1){
	    query.setParameter(paramName, param);
	}
	
	List<?> queryList = query.list();
	session.close();
	if(queryList != null && queryList.isEmpty()) {
	    return null;
	}
	else {
	    return queryList;
	}
    }
    
    private Query createQuery(String queryStr){
	Query query = null;
	session = HibernateConnector.getInstance().getSession();
	query = session.createQuery(queryStr);
	
	return query;
    }

}
