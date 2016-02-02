package com.example.backend;

import static spark.Spark.post;
import static spark.Spark.put;
import static spark.Spark.get;
import static spark.Spark.before;

import spark.Filter;
import spark.Request;
import spark.Response;

public class ApiEndpoints 
{
    private static final String API_CONTEXT = "/api/v1";
    private BlogService blogService = null;
    
    public ApiEndpoints(){
	this.blogService = new UnsafeBlogServiceImpl();
	enableCORS("*", "*", "*");
	setupEndpoints();
    }
    
    private void setupEndpoints(){
	get(API_CONTEXT + "/posts", "application/json", (request, response) 
		-> blogService.findAll(), new JsonTransformer());
	
	post(API_CONTEXT + "/posts", "application/json", (request, response) -> {
	    //Call the correct method to send stuff
	    blogService.createNewPost(request.body());
	    response.status(201);
	    return response;
	}, new JsonTransformer());
	
	put(API_CONTEXT + "/posts/:id", "application/json", (request, response) 
		-> blogService.updatePost(request.params(":id"), request.body()), new JsonTransformer());
    }
    
    private static void enableCORS(final String origin, final String methods, final String headers){
	before(new Filter() {
	    @Override
	    public void handle(Request request, Response response)
		    throws Exception {
		response.header("Access-Control-Allow-Origin", origin);
		response.header("Access-Control-Request-Method", methods);
		response.header("Access-Control-Allow-Headers", headers);
	    }
	});
    }
}
