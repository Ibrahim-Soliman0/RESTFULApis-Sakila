package resource;

import dto.FilmTextDto;
import entity.FilmText;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.GenericEntity;
import jakarta.ws.rs.core.Response;
import mapper.FilmTextMapper;
import service.FilmTextService;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Path("film-texts")
public class FilmTextResource {

    private final FilmTextService filmTextService = new FilmTextService();
    private final FilmTextMapper filmTextMapper = FilmTextMapper.INSTANCE;

    @GET
    @Produces({ "application/xml", "application/json" })
    public Response getAllFilmTexts(@QueryParam("page") @DefaultValue("0") int page,
            @QueryParam("size") @DefaultValue("10") int size) {
        List<FilmText> filmTexts = filmTextService.getAll(page, size);
        List<FilmTextDto> filmTextDtos = filmTexts.stream()
                .map(filmTextMapper::toDto)
                .collect(Collectors.toList());
        return Response.ok(new GenericEntity<>(filmTextDtos) {
        }).build();
    }

    @GET
    @Path("/{id}")
    @Produces({ "application/xml", "application/json" })
    public Response getFilmTextById(@PathParam("id") Integer id) {
        Optional<FilmText> filmText = filmTextService.getById(id);
        if (filmText.isPresent()) {
            FilmTextDto filmTextDto = filmTextMapper.toDto(filmText.get());
            return Response.ok(filmTextDto).build();
        } else {
            return Response.status(Response.Status.NOT_FOUND).build();
        }
    }

    @POST
    @Consumes({ "application/xml", "application/json" })
    @Produces({ "application/xml", "application/json" })
    public Response createFilmText(FilmTextDto filmTextDto) {
        FilmText filmText = filmTextMapper.toEntity(filmTextDto);
        FilmText savedFilmText = filmTextService.save(filmText);
        FilmTextDto savedDto = filmTextMapper.toDto(savedFilmText);
        return Response.status(Response.Status.CREATED).entity(savedDto).build();
    }

    @PUT
    @Path("/{id}")
    @Consumes({ "application/xml", "application/json" })
    @Produces({ "application/xml", "application/json" })
    public Response updateFilmText(@PathParam("id") Integer id, FilmTextDto filmTextDto) {
        Optional<FilmText> existing = filmTextService.getById(id);
        if (existing.isPresent()) {
            FilmText filmText = filmTextMapper.toEntity(filmTextDto);
            filmText.setId(id);
            FilmText updated = filmTextService.save(filmText);
            FilmTextDto updatedDto = filmTextMapper.toDto(updated);
            return Response.ok(updatedDto).build();
        } else {
            return Response.status(Response.Status.NOT_FOUND).build();
        }
    }

    @DELETE
    @Path("/{id}")
    public Response deleteFilmText(@PathParam("id") Integer id) {
        Optional<FilmText> filmText = filmTextService.getById(id);
        if (filmText.isPresent()) {
            filmTextService.delete(filmText.get());
            return Response.noContent().build();
        } else {
            return Response.status(Response.Status.NOT_FOUND).build();
        }
    }
}