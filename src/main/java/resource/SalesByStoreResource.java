package resource;

import dto.SalesByStoreDto;
import entity.SalesByStore;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.GenericEntity;
import jakarta.ws.rs.core.Response;
import mapper.SalesByStoreMapper;
import service.SalesByStoreService;

import java.util.List;
import java.util.stream.Collectors;

@Path("sales-by-stores")
public class SalesByStoreResource {

    private final SalesByStoreService salesByStoreService = new SalesByStoreService();
    private final SalesByStoreMapper salesByStoreMapper = SalesByStoreMapper.INSTANCE;

    @GET
    @Produces({ "application/xml", "application/json" })
    public Response getAllSalesByStores(@QueryParam("page") @DefaultValue("0") int page,
            @QueryParam("size") @DefaultValue("10") int size) {
        List<SalesByStore> salesByStores = salesByStoreService.getAll(page, size);
        List<SalesByStoreDto> salesByStoreDtos = salesByStores.stream()
                .map(salesByStoreMapper::toDto)
                .collect(Collectors.toList());
        return Response.ok(new GenericEntity<>(salesByStoreDtos) {
        }).build();
    }
}