package resource;

import dto.RentalDto;
import entity.Rental;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.GenericEntity;
import jakarta.ws.rs.core.Response;
import service.RentalService;

import java.util.List;
import java.util.Optional;

@Path("rentals")
public class RentalResource {

    private final RentalService rentalService = new RentalService();

    @GET
    @Produces({ "application/xml", "application/json" })
    public Response getAllRentals(@QueryParam("page") @DefaultValue("0") int page,
            @QueryParam("size") @DefaultValue("10") int size) {
        List<RentalDto> rentalDtos = rentalService.getAllDtos(page, size);
        return Response.ok(new GenericEntity<>(rentalDtos) {
        }).build();
    }

    @GET
    @Path("/{id}")
    @Produces({ "application/xml", "application/json" })
    public Response getRentalById(@PathParam("id") Integer id) {
        Optional<RentalDto> rentalDto = rentalService.getByIdDto(id);
        if (rentalDto.isPresent()) {
            return Response.ok(rentalDto.get()).build();
        } else {
            return Response.status(Response.Status.NOT_FOUND).build();
        }
    }

    @POST
    @Consumes({ "application/xml", "application/json" })
    @Produces({ "application/xml", "application/json" })
    public Response createRental(RentalDto rentalDto) {
        RentalDto savedDto = rentalService.saveDto(rentalDto);
        return Response.status(Response.Status.CREATED).entity(savedDto).build();
    }

    @PUT
    @Path("/{id}")
    @Consumes({ "application/xml", "application/json" })
    @Produces({ "application/xml", "application/json" })
    public Response updateRental(@PathParam("id") Integer id, RentalDto rentalDto) {
        Optional<RentalDto> updatedDto = rentalService.updateDto(id, rentalDto);
        if (updatedDto.isPresent()) {
            return Response.ok(updatedDto.get()).build();
        } else {
            return Response.status(Response.Status.NOT_FOUND).build();
        }
    }

    @DELETE
    @Path("/{id}")
    public Response deleteRental(@PathParam("id") Integer id) {
        Optional<Rental> rental = rentalService.getById(id);
        if (rental.isPresent()) {
            rentalService.delete(rental.get());
            return Response.noContent().build();
        } else {
            return Response.status(Response.Status.NOT_FOUND).build();
        }
    }
}