package resource;

import dto.StaffDto;
import entity.Staff;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.GenericEntity;
import jakarta.ws.rs.core.Response;
import service.StaffService;

import java.util.List;
import java.util.Optional;

@Path("staff")
public class StaffResource {

    private final StaffService staffService = new StaffService();

    @GET
    @Produces({ "application/xml", "application/json" })
    public Response getAllStaff(@QueryParam("page") @DefaultValue("0") int page,
            @QueryParam("size") @DefaultValue("10") int size) {
        List<StaffDto> staffDtos = staffService.getAllDtos(page, size);
        return Response.ok(new GenericEntity<>(staffDtos) {
        }).build();
    }

    @GET
    @Path("/{id}")
    @Produces({ "application/xml", "application/json" })
    public Response getStaffById(@PathParam("id") Short id) {
        Optional<StaffDto> staffDto = staffService.getByIdDto(id);
        if (staffDto.isPresent()) {
            return Response.ok(staffDto.get()).build();
        } else {
            return Response.status(Response.Status.NOT_FOUND).build();
        }
    }

    @POST
    @Consumes({ "application/xml", "application/json" })
    @Produces({ "application/xml", "application/json" })
    public Response createStaff(StaffDto staffDto) {
        StaffDto savedDto = staffService.saveDto(staffDto);
        return Response.status(Response.Status.CREATED).entity(savedDto).build();
    }

    @PUT
    @Path("/{id}")
    @Consumes({ "application/xml", "application/json" })
    @Produces({ "application/xml", "application/json" })
    public Response updateStaff(@PathParam("id") Short id, StaffDto staffDto) {
        Optional<StaffDto> updatedDto = staffService.updateDto(id, staffDto);
        if (updatedDto.isPresent()) {
            return Response.ok(updatedDto.get()).build();
        } else {
            return Response.status(Response.Status.NOT_FOUND).build();
        }
    }

    @DELETE
    @Path("/{id}")
    public Response deleteStaff(@PathParam("id") Short id) {
        Optional<Staff> staff = staffService.getById(id);
        if (staff.isPresent()) {
            staffService.delete(staff.get());
            return Response.noContent().build();
        } else {
            return Response.status(Response.Status.NOT_FOUND).build();
        }
    }
}