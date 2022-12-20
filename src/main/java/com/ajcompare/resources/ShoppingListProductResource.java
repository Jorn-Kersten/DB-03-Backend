package com.ajcompare.resources;

import com.ajcompare.domain.ShoppingListProduct;
import com.ajcompare.service.ShoppingListProductService;
import io.quarkus.security.Authenticated;

import javax.inject.Inject;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.net.URI;
import java.util.List;

@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
@Path("/api/user/shoppingList/products")
@Authenticated
public class ShoppingListProductResource {

    @Inject
    ShoppingListProductService shoppingListProductService;

    public ShoppingListProductResource(){
    }

    @GET
    @Path("/{userId}")
    public List<ShoppingListProduct> allShoppingListProducts(Integer userId) {
        return shoppingListProductService.allShoppingListProducts(userId);
    }

    @GET
    @Path("/{userId}/{shoppingListId}")
    public List<ShoppingListProduct> getOldShoppingListProducts(Integer userId, Integer shoppingListId) {
        return shoppingListProductService.getOldShoppingListProducts(userId, shoppingListId);
    }

    @GET
    @Path("/{productId}")
    public ShoppingListProduct getShoppingListProductById(Integer productId) {
        return shoppingListProductService.getShoppingListProductById(productId);
    }

    @DELETE
    @Path("/{productId}")
    public Long deleteShoppingListProduct(Integer productId) { return shoppingListProductService.deleteShoppingListProduct(productId); }

    @PUT
    @Path("/{productId}")
    public ShoppingListProduct updateShoppingListProduct(ShoppingListProduct shoppingListProduct) {
        return shoppingListProductService.updateShoppingListProduct(shoppingListProduct);
    }

    @POST
    @Path("/{userId}")
    public Response addShoppingListProduct(Integer userId, ShoppingListProduct shoppingListProduct) {
        ShoppingListProduct shoppingListProductWithId = shoppingListProductService.addShoppingListProduct(userId, shoppingListProduct);
        return Response.created(URI.create("/api/shoppingList/products/" + shoppingListProductWithId.getId())).build();
    }
}
