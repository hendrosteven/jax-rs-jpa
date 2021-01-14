/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.kelaskoding;

import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.container.AsyncResponse;
import javax.ws.rs.container.Suspended;
import javax.ws.rs.core.Response;

/**
 *
 * @author jarvis
 */
@Path("/items")
@ApplicationScoped
public class ItemController {
    
    @Inject
    private ItemRepository itemRepository;
    
    private static ExecutorService executor = Executors.newFixedThreadPool(10);
    
    @POST
    public Response createItem(Item item){
        itemRepository.create(item);
        return Response.ok(item).build();
    }
    
    @GET
    public List<Item> findAll(){
        List<Item> items = itemRepository.findAll();
        return items;
    }
    
    @GET
    @Path("/async")
    public void findAllAsync(@Suspended final AsyncResponse asyncResponse){
        executor.execute(() -> {         
             List<Item> items = itemRepository.findAll();
             asyncResponse.resume(items);
        });
    }
    
    @GET
    @Path("/{id}")
    public Response findOne(@PathParam("id") Long id){
        Item item = itemRepository.findOne(id);
        if(item == null){
            return Response.status(Response.Status.NOT_FOUND).build();
        }else{
            return Response.ok(item).build();
        }
    }
    
    
    
}
