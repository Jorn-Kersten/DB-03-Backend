package com.ajcompare.resources;

import com.ajcompare.domain.ShoppingList;
import com.ajcompare.service.ShoppingListService;

import javax.inject.Inject;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.net.URI;
import java.util.List;

@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
@Path("/api/shoppingLists")
public class ShoppingListResource {

    @Inject
    ShoppingListService shoppingListService;

    public ShoppingListResource(){
    }

    @GET
    public List<ShoppingList> allShoppingLists() {
        return shoppingListService.allShoppingLists();
    }

    @GET
    @Path("/{shoppingListId}")
    public ShoppingList getShoppingListById(Long shoppingListId) {
        return shoppingListService.getShoppingListById(shoppingListId);
    }

    @POST
    public Response addShoppingList(ShoppingList shoppingList) {
        ShoppingList shoppingListWithId = shoppingListService.addShoppingList(shoppingList);
        return Response.created(URI.create("/api/shoppingLists/" + shoppingListWithId.getId())).build();
    }
}
