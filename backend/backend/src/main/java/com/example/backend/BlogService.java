package com.example.backend;

import java.util.List;

public interface BlogService {
    public List<BlogPost> findAll();
    public BlogPost getPost(int id);
    public void createNewPost(String body);
    public BlogPost updatePost(String id, String body);
    public List<Comment> findAllComments(int postId);
    public Comment getComment(int id);
    public void createNewComment(String body);
    public Comment updateComment(String id, String body);
}
