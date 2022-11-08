package com.ajcompare.resources;

import com.ajcompare.domain.Product;
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
@Path("/api/shoppingListProducts")
public class ShoppingListProductResource {

    @Inject
    ShoppingListProductService shoppingListProductService;

    public ShoppingListProductResource(){
    }

    @GET
    public List<ShoppingListProduct> allProducts() {
        return shoppingListProductService.allProducts();
    }

    @GET
    @Path("/{productId}")
    public ShoppingListProduct getProductById(Integer productId) {
        return shoppingListProductService.getProductById(productId);
    }

    @DELETE
    @Path("/{productId}")
    public Long deleteProduct(Integer productId) { return shoppingListProductService.deleteProduct(productId); }

    @PUT
    @Path("/{productId}")
    public ShoppingListProduct updateProduct(ShoppingListProduct shoppingListProduct) {
        return shoppingListProductService.updateProduct(shoppingListProduct);
    }

    @POST
    public Response addProduct(ShoppingListProduct shoppingListProduct) {
        ShoppingListProduct shoppingListProductWithId = shoppingListProductService.addProduct(shoppingListProduct);
        return Response.created(URI.create("/api/shoppingListProducts/" + shoppingListProductWithId.getId())).build();
    }
}
