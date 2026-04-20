package resource;

import dto.InventoryDto;
import entity.Film;
import entity.Inventory;
import entity.Store;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.Context;
import jakarta.ws.rs.core.GenericEntity;
import jakarta.ws.rs.core.HttpHeaders;
import jakarta.ws.rs.core.Response;
import service.FilmService;
import service.InventoryService;
import service.StoreService;

import java.util.List;
import java.util.Optional;

@Path("inventories")
public class InventoryResource {

    private final InventoryService inventoryService = new InventoryService();
    private final FilmService filmService = new FilmService();
    private final StoreService storeService = new StoreService();

    @Context
    private HttpHeaders httpHeaders;

    @GET
    @Produces({ "application/xml", "application/json" })
    public Response getAllInventories(@QueryParam("page") @DefaultValue("0") int page,
            @QueryParam("size") @DefaultValue("10") int size) {
        List<InventoryDto> inventoryDtos = inventoryService.getAllDtos(page, size);
        return Response.ok(new GenericEntity<>(inventoryDtos) {
        }).build();
    }

    @GET
    @Path("/{id}")
    @Produces({ "application/xml", "application/json" })
    public Response getInventoryById(@PathParam("id") Integer id) {
        Optional<InventoryDto> inventoryDto = inventoryService.getByIdDto(id);
        if (inventoryDto.isPresent()) {
            return Response.ok(inventoryDto.get()).build();
        } else {
            return Response.status(Response.Status.NOT_FOUND).build();
        }
    }

    @POST
    @Consumes({ "application/xml", "application/json" })
    @Produces({ "application/xml", "application/json" })
    public Response createInventory(InventoryDto inventoryDto) {
        Optional<Film> film = filmService.getById(inventoryDto.getFilmId());
        if (film.isEmpty()) {
            return Response.status(Response.Status.BAD_REQUEST).entity("Invalid filmId").build();
        }
        Optional<Store> store = storeService.getById(inventoryDto.getStoreId());
        if (store.isEmpty()) {
            return Response.status(Response.Status.BAD_REQUEST).entity("Invalid storeId").build();
        }
        InventoryDto savedInventoryDto = inventoryService.saveDto(inventoryDto);
        return Response.status(Response.Status.CREATED).entity(savedInventoryDto).build();
    }

    @PUT
    @Path("/{id}")
    @Consumes({ "application/xml", "application/json" })
    @Produces({ "application/xml", "application/json" })
    public Response updateInventory(@PathParam("id") Integer id, InventoryDto inventoryDto) {
        Optional<Film> film = filmService.getById(inventoryDto.getFilmId());
        if (film.isEmpty()) {
            return Response.status(Response.Status.BAD_REQUEST).entity("Invalid filmId").build();
        }
        Optional<Store> store = storeService.getById(inventoryDto.getStoreId());
        if (store.isEmpty()) {
            return Response.status(Response.Status.BAD_REQUEST).entity("Invalid storeId").build();
        }
        Optional<InventoryDto> updatedDto = inventoryService.updateDto(id, inventoryDto);
        if (updatedDto.isPresent()) {
            return Response.ok(updatedDto.get()).build();
        } else {
            return Response.status(Response.Status.NOT_FOUND).build();
        }
    }

    @DELETE
    @Path("/{id}")
    public Response deleteInventory(@PathParam("id") Integer id) {
        Optional<Inventory> inventory = inventoryService.getById(id);
        if (inventory.isPresent()) {
            inventoryService.delete(inventory.get());
            return Response.noContent().build();
        } else {
            return Response.status(Response.Status.NOT_FOUND).build();
        }
    }
}