package com.ajcompare.resources;

import com.ajcompare.domain.User;
import com.ajcompare.service.UserService;
import io.quarkus.security.Authenticated;

import javax.inject.Inject;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.net.URI;
import java.util.List;

@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
@Path("api/user")
@Authenticated
public class UserResource {

    @Inject
    UserService userService;

    @GET
    @Produces(MediaType.TEXT_PLAIN)
    public String user() {
        return "is user";
    }

    public UserResource(){
    }

    @GET
    public User getUser(Integer id) {
        return userService.getUser(id);
    }

    @POST
    public Response addProduct(User user) {
        User newUser = userService.createUser(user);
        return Response.created(URI.create("/api/user/" + newUser.getId())).build();
    }
}
