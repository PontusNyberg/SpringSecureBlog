package com.example.backend;

import java.util.List;

public interface BlogService {
    public List<BlogPost> findAll();
    public BlogPost getPost(int id);
    public void createNewPost(String body);
    public BlogPost updatePost(String id, String body);
}
