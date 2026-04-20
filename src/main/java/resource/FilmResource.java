package resource;

import dto.FilmDto;
import entity.Film;
import entity.Language;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.Context;
import jakarta.ws.rs.core.GenericEntity;
import jakarta.ws.rs.core.HttpHeaders;
import jakarta.ws.rs.core.Response;
import service.FilmService;
import service.LanguageService;

import java.util.List;
import java.util.Optional;

@Path("films")
public class FilmResource {

    private final FilmService filmService = new FilmService();
    private final LanguageService languageService = new LanguageService();

    @Context
    private HttpHeaders httpHeaders;

    @GET
    @Produces({ "application/xml", "application/json" })
    public Response getAllFilms(@QueryParam("page") @DefaultValue("0") int page,
            @QueryParam("size") @DefaultValue("10") int size) {
        List<FilmDto> filmDtos = filmService.getAllDtos(page, size);
        return Response.ok(new GenericEntity<>(filmDtos) {
        }).build();
    }

    @GET
    @Path("/{id}")
    @Produces({ "application/xml", "application/json" })
    public Response getFilmById(@PathParam("id") Integer id) {
        Optional<FilmDto> filmDto = filmService.getByIdDto(id);
        if (filmDto.isPresent()) {
            return Response.ok(filmDto.get()).build();
        } else {
            return Response.status(Response.Status.NOT_FOUND).build();
        }
    }

    @POST
    @Consumes({ "application/xml", "application/json" })
    @Produces({ "application/xml", "application/json" })
    public Response createFilm(FilmDto filmDto) {
        Optional<Language> language = languageService.getById(filmDto.getLanguageId());
        if (language.isEmpty()) {
            return Response.status(Response.Status.BAD_REQUEST).entity("Invalid languageId").build();
        }
        Optional<Language> originalLanguage = null;
        if (filmDto.getOriginalLanguageId() != null) {
            originalLanguage = languageService.getById(filmDto.getOriginalLanguageId());
            if (originalLanguage.isEmpty()) {
                return Response.status(Response.Status.BAD_REQUEST).entity("Invalid originalLanguageId").build();
            }
        }
        FilmDto savedFilmDto = filmService.saveDto(filmDto);
        return Response.status(Response.Status.CREATED).entity(savedFilmDto).build();
    }

    @PUT
    @Path("/{id}")
    @Consumes({ "application/xml", "application/json" })
    @Produces({ "application/xml", "application/json" })
    public Response updateFilm(@PathParam("id") Integer id, FilmDto filmDto) {
        Optional<Language> language = languageService.getById(filmDto.getLanguageId());
        if (language.isEmpty()) {
            return Response.status(Response.Status.BAD_REQUEST).entity("Invalid languageId").build();
        }
        Optional<Language> originalLanguage = null;
        if (filmDto.getOriginalLanguageId() != null) {
            originalLanguage = languageService.getById(filmDto.getOriginalLanguageId());
            if (originalLanguage.isEmpty()) {
                return Response.status(Response.Status.BAD_REQUEST).entity("Invalid originalLanguageId").build();
            }
        }
        Optional<FilmDto> updatedDto = filmService.updateDto(id, filmDto);
        if (updatedDto.isPresent()) {
            return Response.ok(updatedDto.get()).build();
        } else {
            return Response.status(Response.Status.NOT_FOUND).build();
        }
    }

    @DELETE
    @Path("/{id}")
    public Response deleteFilm(@PathParam("id") Integer id) {
        Optional<Film> film = filmService.getById(id);
        if (film.isPresent()) {
            filmService.delete(film.get());
            return Response.noContent().build();
        } else {
            return Response.status(Response.Status.NOT_FOUND).build();
        }
    }
}