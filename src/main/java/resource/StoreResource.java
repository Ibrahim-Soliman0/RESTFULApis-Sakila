package resource;

import dto.StoreDto;
import entity.Store;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.GenericEntity;
import jakarta.ws.rs.core.Response;
import service.StoreService;

import java.util.List;
import java.util.Optional;

@Path("stores")
public class StoreResource {

    private final StoreService storeService = new StoreService();

    @GET
    @Produces({ "application/xml", "application/json" })
    public Response getAllStores(@QueryParam("page") @DefaultValue("0") int page,
            @QueryParam("size") @DefaultValue("10") int size) {
        List<StoreDto> storeDtos = storeService.getAllDtos(page, size);
        return Response.ok(new GenericEntity<>(storeDtos) {
        }).build();
    }

    @GET
    @Path("/{id}")
    @Produces({ "application/xml", "application/json" })
    public Response getStoreById(@PathParam("id") Short id) {
        Optional<StoreDto> storeDto = storeService.getByIdDto(id);
        if (storeDto.isPresent()) {
            return Response.ok(storeDto.get()).build();
        } else {
            return Response.status(Response.Status.NOT_FOUND).build();
        }
    }

    @POST
    @Consumes({ "application/xml", "application/json" })
    @Produces({ "application/xml", "application/json" })
    public Response createStore(StoreDto storeDto) {
        StoreDto savedDto = storeService.saveDto(storeDto);
        return Response.status(Response.Status.CREATED).entity(savedDto).build();
    }

    @PUT
    @Path("/{id}")
    @Consumes({ "application/xml", "application/json" })
    @Produces({ "application/xml", "application/json" })
    public Response updateStore(@PathParam("id") Short id, StoreDto storeDto) {
        Optional<StoreDto> updatedDto = storeService.updateDto(id, storeDto);
        if (updatedDto.isPresent()) {
            return Response.ok(updatedDto.get()).build();
        } else {
            return Response.status(Response.Status.NOT_FOUND).build();
        }
    }

    @DELETE
    @Path("/{id}")
    public Response deleteStore(@PathParam("id") Short id) {
        Optional<Store> store = storeService.getById(id);
        if (store.isPresent()) {
            storeService.delete(store.get());
            return Response.noContent().build();
        } else {
            return Response.status(Response.Status.NOT_FOUND).build();
        }
    }
}