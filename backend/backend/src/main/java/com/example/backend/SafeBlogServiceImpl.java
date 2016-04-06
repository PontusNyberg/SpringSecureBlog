package com.example.backend;

import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;

public class SafeBlogServiceImpl implements BlogService {

    @Override
    public List<BlogPost> findAll() {
	Session session = null;
	try{
	    session = HibernateConnector.getInstance().getSession();
	    Query query = session.createQuery("from BlogPost s");
	    
	    List queryList = query.list();
	    if(queryList != null && queryList.isEmpty()) {
		return null;
	    }
	    else {
		return (List<BlogPost>) queryList;
	    }
	} catch (Exception e) {
	    e.printStackTrace();
	    return null;
	} finally {
	    session.close();
	}
    }

    @Override
    public BlogPost getPost(int id) {
	// TODO Auto-generated method stub
	return null;
    }

    @Override
    public void createNewPost(String body) {
	// TODO Auto-generated method stub
	
    }

    @Override
    public BlogPost updatePost(String id, String body) {
	// TODO Auto-generated method stub
	return null;
    }

    @Override
    public List<Comment> findAllComments(int postId) {
	// TODO Auto-generated method stub
	return null;
    }

    @Override
    public Comment getComment(int id) {
	// TODO Auto-generated method stub
	return null;
    }

    @Override
    public void createNewComment(String body) {
	// TODO Auto-generated method stub
	
    }

    @Override
    public Comment updateComment(String id, String body) {
	// TODO Auto-generated method stub
	return null;
    }

}
