package resource;

import dto.AddressDto;
import entity.Address;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.Context;
import jakarta.ws.rs.core.GenericEntity;
import jakarta.ws.rs.core.HttpHeaders;
import jakarta.ws.rs.core.Response;
import service.AddressService;
import service.CityService;

import java.util.List;
import java.util.Optional;

@Path("addresses")
public class AddressResource {

    private final AddressService addressService = new AddressService();
    private final CityService cityService = new CityService();

    @Context
    private HttpHeaders httpHeaders;

    @GET
    @Produces({ "application/xml", "application/json" })
    public Response getAllAddresses(@QueryParam("page") @DefaultValue("0") int page,
            @QueryParam("size") @DefaultValue("10") int size) {
        List<AddressDto> addressDtos = addressService.getAllDtos(page, size);
        return Response.ok(new GenericEntity<>(addressDtos) {
        }).build();
    }

    @GET
    @Path("/{id}")
    @Produces({ "application/xml", "application/json" })
    public Response getAddressById(@PathParam("id") Integer id) {
        Optional<AddressDto> addressDto = addressService.getByIdDto(id);
        if (addressDto.isPresent()) {
            return Response.ok(addressDto.get()).build();
        } else {
            return Response.status(Response.Status.NOT_FOUND).build();
        }
    }

    @POST
    @Consumes({ "application/xml", "application/json" })
    @Produces({ "application/xml", "application/json" })
    public Response createAddress(AddressDto addressDto) {
        AddressDto savedAddressDto = addressService.saveDto(addressDto);
        return Response.status(Response.Status.CREATED).entity(savedAddressDto).build();
    }

    @PUT
    @Path("/{id}")
    @Consumes({ "application/xml", "application/json" })
    @Produces({ "application/xml", "application/json" })
    public Response updateAddress(@PathParam("id") Integer id, AddressDto addressDto) {
        Optional<AddressDto> updatedDto = addressService.updateDto(id, addressDto);
        if (updatedDto.isPresent()) {
            return Response.ok(updatedDto.get()).build();
        } else {
            return Response.status(Response.Status.NOT_FOUND).build();
        }
    }

    @DELETE
    @Path("/{id}")
    public Response deleteAddress(@PathParam("id") Integer id) {
        Optional<Address> address = addressService.getById(id);
        if (address.isPresent()) {
            addressService.delete(address.get());
            return Response.noContent().build();
        } else {
            return Response.status(Response.Status.NOT_FOUND).build();
        }
    }
}