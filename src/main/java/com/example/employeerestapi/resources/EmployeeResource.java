package com.example.employeerestapi.resources;

import com.example.employeerestapi.models.Employee;
import com.example.employeerestapi.services.EmployeeService;
import jakarta.annotation.security.RolesAllowed;
import jakarta.inject.Inject;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.*;

import java.util.*;

@Path("/employees")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class EmployeeResource {

    @Inject
    private EmployeeService employeeService;

    // READ: user + admin
    @GET
    @RolesAllowed({"user", "admin"})
    public Collection<Employee> getAllEmployees() {
        return employeeService.getAll();
    }

    @GET
    @Path("/{id}")
    @RolesAllowed({"user", "admin"})
    public Response getEmployee(@PathParam("id") int id) {
        Employee employee = employeeService.getById(id);
        if (employee == null) {
            return Response.status(Response.Status.NOT_FOUND).build();
        }
        return Response.ok(employee).build();
    }

    // CRUD (C/U/D): admin only
    @POST
    @RolesAllowed("admin")
    public Response addEmployee(@Context SecurityContext sc, Employee employee) {
        if (!sc.isUserInRole("admin")) {
            return Response.status(Response.Status.FORBIDDEN).entity("Admin only").build();
        }
        Employee created = employeeService.create(employee);
        return Response.status(Response.Status.CREATED).entity(created).build();
    }

    @PUT
    @Path("/{id}")
    @RolesAllowed("admin")
    public Response updateEmployee(@PathParam("id") int id, Employee employee) {
        Employee updated = employeeService.update(id, employee);
        if (updated == null) {
            return Response.status(Response.Status.NOT_FOUND).build();
        }
        return Response.ok(updated).build();
    }

    @DELETE
    @Path("/{id}")
    @RolesAllowed("admin")
    public Response deleteEmployee(@PathParam("id") int id) {
        boolean deleted = employeeService.delete(id);
        if (!deleted) {
            return Response.status(Response.Status.NOT_FOUND).build();
        }
        return Response.noContent().build();
    }
}
