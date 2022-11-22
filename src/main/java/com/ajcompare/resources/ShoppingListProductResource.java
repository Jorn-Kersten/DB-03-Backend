package com.ajcompare.resources;

import com.ajcompare.domain.Product;
import com.ajcompare.domain.ShoppingList;
import com.ajcompare.domain.ShoppingListProduct;
import com.ajcompare.service.ProductService;
import com.ajcompare.service.ShoppingListProductService;

import javax.inject.Inject;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.net.URI;
import java.util.List;

@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
@Path("/api/shoppingList/products")
public class ShoppingListProductResource {

    @Inject
    ShoppingListProductService shoppingListProductService;

    public ShoppingListProductResource(){
    }

    @GET
    @Path("/user/{userId}")
    public List<ShoppingListProduct> allShoppingListProducts(Integer userId) {
        return shoppingListProductService.allShoppingListProducts(userId);
    }

    @GET
    @Path("/user/{userId}/{shoppingListId}")
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
    @Path("/user/{userId}")
    public Response addShoppingListProduct(Integer userId, ShoppingListProduct shoppingListProduct) {
        ShoppingListProduct shoppingListProductWithId = shoppingListProductService.addShoppingListProduct(userId, shoppingListProduct);
        return Response.created(URI.create("/api/shoppingList/products/" + shoppingListProductWithId.getId())).build();
    }
}
