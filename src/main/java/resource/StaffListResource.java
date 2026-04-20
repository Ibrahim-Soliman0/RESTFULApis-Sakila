package resource;

import dto.StaffListDto;
import entity.StaffList;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.GenericEntity;
import jakarta.ws.rs.core.Response;
import mapper.StaffListMapper;
import service.StaffListService;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Path("staff-lists")
public class StaffListResource {

    private final StaffListService staffListService = new StaffListService();
    private final StaffListMapper staffListMapper = StaffListMapper.INSTANCE;

    @GET
    @Produces({ "application/xml", "application/json" })
    public Response getAllStaffLists(@QueryParam("page") @DefaultValue("0") int page,
            @QueryParam("size") @DefaultValue("10") int size) {
        List<StaffList> staffLists = staffListService.getAll(page, size);
        List<StaffListDto> staffListDtos = staffLists.stream()
                .map(staffListMapper::toDto)
                .collect(Collectors.toList());
        return Response.ok(new GenericEntity<>(staffListDtos) {
        }).build();
    }

    @GET
    @Path("/{id}")
    @Produces({ "application/xml", "application/json" })
    public Response getStaffListById(@PathParam("id") Short id) {
        Optional<StaffList> staffList = staffListService.getById((short) id.intValue());
        if (staffList.isPresent()) {
            StaffListDto staffListDto = staffListMapper.toDto(staffList.get());
            return Response.ok(staffListDto).build();
        } else {
            return Response.status(Response.Status.NOT_FOUND).build();
        }
    }

    @POST
    @Consumes({ "application/xml", "application/json" })
    @Produces({ "application/xml", "application/json" })
    public Response createStaffList(StaffListDto staffListDto) {
        // StaffList is immutable, creation not supported
        return Response.status(Response.Status.METHOD_NOT_ALLOWED).build();
    }

    @PUT
    @Path("/{id}")
    @Consumes({ "application/xml", "application/json" })
    @Produces({ "application/xml", "application/json" })
    public Response updateStaffList(@PathParam("id") Short id, StaffListDto staffListDto) {
        // StaffList is immutable, update not supported
        return Response.status(Response.Status.METHOD_NOT_ALLOWED).build();
    }

    @DELETE
    @Path("/{id}")
    public Response deleteStaffList(@PathParam("id") Short id) {
        // StaffList is immutable, delete not supported
        return Response.status(Response.Status.METHOD_NOT_ALLOWED).build();
    }
}