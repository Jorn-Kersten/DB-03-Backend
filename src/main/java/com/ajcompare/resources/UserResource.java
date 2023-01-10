package com.ajcompare.resources;

import com.ajcompare.domain.User;
import com.ajcompare.service.UserService;
import io.quarkus.security.Authenticated;

import javax.inject.Inject;
import javax.transaction.Transactional;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.net.URI;

@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
@Path("/api/user")
@Authenticated
public class UserResource {

    @Inject
    UserService userService;

    public UserResource(){
    }

    @GET
    @Path("/{userName}")
    public User getUser(String userName) {
        return userService.getUser(userName);
    }

    @POST
    @Transactional
    @Path("/register")
    public Response createUser(User user) {
        User newUser = userService.createUser(user);
        System.out.println("temp test");
        return Response.created(URI.create("/api/user/" + newUser.getName())).build();
    }
}
