package com.ajcompare.resources;

import com.ajcompare.domain.Product;
import java.util.Collections;
import java.util.LinkedHashMap;
import java.util.Set;
import java.net.URI;

import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.core.Response;


@Path("/api/products")
public class ProductResource {

    private Set<Product> products = Collections.newSetFromMap(Collections.synchronizedMap(new LinkedHashMap<>()));

    public ProductResource(){
        products.add(new Product("","","",0.0));
    }

    @GET
    public Set<Product> allProducts() {
        return products;
    }

    @POST
    public Response addProduct(Product product) {
        products.add(product);
        return Response.created(URI.create("/api/products/" + 1)).build();
    }
}
