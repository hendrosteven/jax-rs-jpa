/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.kelaskoding;

import java.util.List;
import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.core.Response;

/**
 *
 * @author jarvis
 */
@Path("/categories")
@ApplicationScoped
public class CategoryController {
    
    @Inject
    private CategoryRepository categoryRepository;
    
    @POST
    public Response createCategory(Category category){
        categoryRepository.create(category);
        return Response.ok(category).build();
    }
    
    @GET
    public List<Category> findAll(){
        List<Category> categories = categoryRepository.findAll();
        return categories;
    }
}
