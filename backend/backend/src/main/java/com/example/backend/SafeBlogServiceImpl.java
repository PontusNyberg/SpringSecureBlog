package com.example.backend;

import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;

public class SafeBlogServiceImpl implements BlogService {
    private Session session = null;

    @SuppressWarnings("unchecked")
    @Override
    public List<BlogPost> findAll() {
 	List<BlogPost> queryList = (List<BlogPost>) getQueryList("from BlogPost s", null, -1);
	if(queryList != null && queryList.isEmpty()){
	    return null;
	} else {
	    return queryList;  
	}
    }

    @SuppressWarnings("unchecked")
    @Override
    public BlogPost getPost(int id) {
	List<BlogPost> queryList = (List<BlogPost>) getQueryList("from BlogPost s where s.id = :id", "id", id);
	if(queryList != null && queryList.isEmpty()){
	    return null;
	} else {
	    return queryList.get(0);
	}
    }

    @Override
    public void createNewPost(String body) {
	
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
