package com.example.backend;

import java.util.List;

public interface BlogService {
    public List<String> findAll();
    public String getPost(String id);
    public void createNewPost(String title, String body);
    public void updatePost(String id, String title, String body);
}
