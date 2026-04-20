package resource;

import dto.FilmCategoryDto;
import entity.FilmCategory;
import entity.FilmCategoryId;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.GenericEntity;
import jakarta.ws.rs.core.Response;
import mapper.FilmCategoryMapper;
import service.FilmCategoryService;

import java.util.List;
import java.util.stream.Collectors;

@Path("film-categories")
public class FilmCategoryResource {

    private final FilmCategoryService filmCategoryService = new FilmCategoryService();
    private final FilmCategoryMapper filmCategoryMapper = FilmCategoryMapper.INSTANCE;

    @GET
    @Produces({ "application/xml", "application/json" })
    public Response getAllFilmCategories(@QueryParam("page") @DefaultValue("0") int page,
            @QueryParam("size") @DefaultValue("10") int size) {
        List<FilmCategory> filmCategories = filmCategoryService.getAll(page, size);
        List<FilmCategoryDto> filmCategoryDtos = filmCategories.stream()
                .map(filmCategoryMapper::toDto)
                .collect(Collectors.toList());
        return Response.ok(new GenericEntity<>(filmCategoryDtos) {
        }).build();
    }

    @POST
    @Consumes({ "application/xml", "application/json" })
    @Produces({ "application/xml", "application/json" })
    public Response createFilmCategory(FilmCategoryDto filmCategoryDto) {
        FilmCategory filmCategory = filmCategoryMapper.toEntity(filmCategoryDto);
        FilmCategory savedFilmCategory = filmCategoryService.save(filmCategory);
        FilmCategoryDto savedDto = filmCategoryMapper.toDto(savedFilmCategory);
        return Response.status(Response.Status.CREATED).entity(savedDto).build();
    }

    @DELETE
    @Path("/{filmId}/{categoryId}")
    public Response deleteFilmCategory(@PathParam("filmId") Integer filmId, @PathParam("categoryId") Short categoryId) {
        FilmCategoryId id = new FilmCategoryId();
        id.setFilmId(filmId);
        id.setCategoryId(categoryId);
        FilmCategory filmCategory = new FilmCategory();
        filmCategory.setId(id);
        filmCategoryService.delete(filmCategory);
        return Response.noContent().build();
    }
}