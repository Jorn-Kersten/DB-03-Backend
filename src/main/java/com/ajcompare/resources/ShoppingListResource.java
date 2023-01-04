package com.ajcompare.resources;

import com.ajcompare.domain.ShoppingList;
import com.ajcompare.service.ShoppingListService;
import io.quarkus.security.Authenticated;

import javax.inject.Inject;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.net.URI;
import java.util.List;

@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
@Authenticated
@Path("/api/user/shoppingLists")
public class ShoppingListResource {

    @Inject
    ShoppingListService shoppingListService;

    public ShoppingListResource(){
    }

    @GET
    @Path("/{userName}")
    public List<ShoppingList> allShoppingLists(String userName) {
        return shoppingListService.allShoppingLists();
    }

    @GET
    @Path("{userName}/{shoppingListId}")
    public ShoppingList getShoppingListById(String userName, Integer shoppingListId) {
        return shoppingListService.getShoppingListById(userName, shoppingListId);
    }

    @POST
    @Path("/{userName}")
    public Response addShoppingList(String userName, ShoppingList shoppingList) {
        ShoppingList shoppingListWithId = shoppingListService.addShoppingList(userName, shoppingList);
        return Response.created(URI.create("/api/shoppingLists/" + shoppingListWithId.getId())).build();
    }
}
