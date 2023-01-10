package com.ajcompare.resources;

import com.ajcompare.domain.Product;
import com.ajcompare.service.ProductService;

import javax.inject.Inject;
import javax.ws.rs.*;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.MediaType;
import java.net.URI;
import java.util.List;

@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
@Path("/api/products")
public class ProductResource {

    @Inject
    ProductService productService;

    public ProductResource(){
    }

    @GET
    public List<Product> allProducts() {
        return productService.allProducts();
    }

    @GET
    @Path("/{productId}")
    public Product getProductById(Integer productId) {

        return productService.getProductById(productId);
    }

    @DELETE
    @Path("/{productId}")
    public Long deleteProduct(Integer productId) { return productService.deleteProduct(productId); }

    @PUT
    @Path("/{productId}")
    public Product updateProduct(Product product) { return productService.updateProduct(product); }

    @POST
    public Response addProduct(Product product) {
        Product productWithId = productService.addProduct(product);
        return Response.created(URI.create("/api/products/" + productWithId.getId())).build();
    }
}
