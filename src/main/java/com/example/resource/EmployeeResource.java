package com.example.resource;

import com.example.model.Employee;


import jakarta.ejb.EJB;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;

import java.util.List;

@Path("/employees")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
public class EmployeeResource {

    @EJB
    private EmployeeService service;

    // CREATE
    @POST
    public Response create(Employee employee) {
        if (employee.getName() == null || employee.getDepartment() == null) {
            return Response.status(Response.Status.BAD_REQUEST)
                    .entity("name and department are required")
                    .build();
        }
        Employee created = service.create(employee);
        return Response.status(Response.Status.CREATED).entity(created).build();
    }

    // READ ALL
    @GET
    public List<Employee> getAll() {
        return service.findAll();
    }

    // READ ONE
    @GET
    @Path("/{id}")
    public Response getById(@PathParam("id") long id) {
        Employee emp = service.findById(id);
        if (emp == null) return Response.status(Response.Status.NOT_FOUND).build();
        return Response.ok(emp).build();
    }

    // UPDATE
    @PUT
    @Path("/{id}")
    public Response update(@PathParam("id") long id, Employee employee) {
        Employee updated = service.update(id, employee);
        if (updated == null) return Response.status(Response.Status.NOT_FOUND).build();
        return Response.ok(updated).build();
    }

    // DELETE
    @DELETE
    @Path("/{id}")
    public Response delete(@PathParam("id") long id) {
        boolean deleted = service.delete(id);
        if (!deleted) return Response.status(Response.Status.NOT_FOUND).build();
        return Response.noContent().build();
    }
}
