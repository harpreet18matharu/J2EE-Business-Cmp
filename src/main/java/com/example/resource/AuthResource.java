package com.example.resource;

import com.example.service.AuthService;
import com.example.util.JwtUtil;

import jakarta.ejb.EJB;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import java.util.HashMap;
import java.util.Map;


import java.util.Map;

@Path("/login")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
public class AuthResource {

    @EJB
    private AuthService authService;

    @POST
    public Response login(Map<String, String> request) {

        String username = request.get("username");
        String password = request.get("password");

        if (authService.validateUser(username, password)) {
            String token = JwtUtil.generateToken(username);
            Map<String, String> response = new HashMap<>();
            response.put("token", token);

            return Response.ok(response).build();

        }

        return Response.status(Response.Status.UNAUTHORIZED).build();
    }
}
