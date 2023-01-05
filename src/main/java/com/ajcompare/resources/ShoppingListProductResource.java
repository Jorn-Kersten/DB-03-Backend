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
    @Path("/{userName}")
    public List<ShoppingListProduct> allShoppingListProducts(String userName) {
        return shoppingListProductService.allShoppingListProducts(userName);
    }

    @GET
    @Path("/{userName}/{shoppingListId}")
    public List<ShoppingListProduct> getOldShoppingListProducts(String userName, Integer shoppingListId) {
        return shoppingListProductService.getOldShoppingListProducts(userName, shoppingListId);
    }

    @GET
    @Path("/{userName}/{productId}")
    public ShoppingListProduct getShoppingListProductById(String userName, Integer productId) {
        return shoppingListProductService.getShoppingListProductById(userName, productId);
    }

    @DELETE
    @Path("/{userName}/{productId}")
    public Long deleteShoppingListProduct(String userName, Integer productId) {
        return shoppingListProductService.deleteShoppingListProduct(userName, productId);
    }

    @PUT
    @Path("/{userName}/{productId}")
    public ShoppingListProduct updateShoppingListProduct(String userName, Integer productId, ShoppingListProduct shoppingListProduct) {
        return shoppingListProductService.updateShoppingListProduct(userName, productId, shoppingListProduct);
    }

    @POST
    @Path("/{userName}")
    public Response addShoppingListProduct(String userName, ShoppingListProduct shoppingListProduct) {
        ShoppingListProduct shoppingListProductWithId = shoppingListProductService.addShoppingListProduct(userName, shoppingListProduct);
        return Response.created(URI.create("/api/shoppingList/products/" + shoppingListProductWithId.getId())).build();
    }
}
