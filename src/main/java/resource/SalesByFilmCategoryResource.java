package resource;

import dto.SalesByFilmCategoryDto;
import entity.SalesByFilmCategory;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.GenericEntity;
import jakarta.ws.rs.core.Response;
import mapper.SalesByFilmCategoryMapper;
import service.SalesByFilmCategoryService;

import java.util.List;
import java.util.stream.Collectors;

@Path("sales-by-film-categories")
public class SalesByFilmCategoryResource {

    private final SalesByFilmCategoryService salesByFilmCategoryService = new SalesByFilmCategoryService();
    private final SalesByFilmCategoryMapper salesByFilmCategoryMapper = SalesByFilmCategoryMapper.INSTANCE;

    @GET
    @Produces({ "application/xml", "application/json" })
    public Response getAllSalesByFilmCategories(@QueryParam("page") @DefaultValue("0") int page,
            @QueryParam("size") @DefaultValue("10") int size) {
        List<SalesByFilmCategory> salesByFilmCategories = salesByFilmCategoryService.getAll(page, size);
        List<SalesByFilmCategoryDto> salesByFilmCategoryDtos = salesByFilmCategories.stream()
                .map(salesByFilmCategoryMapper::toDto)
                .collect(Collectors.toList());
        return Response.ok(new GenericEntity<>(salesByFilmCategoryDtos) {
        }).build();
    }
}