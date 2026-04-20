package resource;

import dto.LanguageDto;
import entity.Language;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.GenericEntity;
import jakarta.ws.rs.core.Response;
import service.LanguageService;

import java.util.List;
import java.util.Optional;

@Path("languages")
public class LanguageResource {

    private final LanguageService languageService = new LanguageService();

    @GET
    @Produces({ "application/xml", "application/json" })
    public Response getAllLanguages(@QueryParam("page") @DefaultValue("0") int page,
            @QueryParam("size") @DefaultValue("10") int size) {
        List<LanguageDto> languageDtos = languageService.getAllDtos(page, size);
        return Response.ok(new GenericEntity<>(languageDtos) {
        }).build();
    }

    @GET
    @Path("/{id}")
    @Produces({ "application/xml", "application/json" })
    public Response getLanguageById(@PathParam("id") Short id) {
        Optional<LanguageDto> languageDto = languageService.getByIdDto(id);
        if (languageDto.isPresent()) {
            return Response.ok(languageDto.get()).build();
        } else {
            return Response.status(Response.Status.NOT_FOUND).build();
        }
    }

    @POST
    @Consumes({ "application/xml", "application/json" })
    @Produces({ "application/xml", "application/json" })
    public Response createLanguage(LanguageDto languageDto) {
        LanguageDto savedDto = languageService.saveDto(languageDto);
        return Response.status(Response.Status.CREATED).entity(savedDto).build();
    }

    @PUT
    @Path("/{id}")
    @Consumes({ "application/xml", "application/json" })
    @Produces({ "application/xml", "application/json" })
    public Response updateLanguage(@PathParam("id") Short id, LanguageDto languageDto) {
        Optional<LanguageDto> updatedDto = languageService.updateDto(id, languageDto);
        if (updatedDto.isPresent()) {
            return Response.ok(updatedDto.get()).build();
        } else {
            return Response.status(Response.Status.NOT_FOUND).build();
        }
    }

    @DELETE
    @Path("/{id}")
    public Response deleteLanguage(@PathParam("id") Short id) {
        Optional<Language> language = languageService.getById(id);
        if (language.isPresent()) {
            languageService.delete(language.get());
            return Response.noContent().build();
        } else {
            return Response.status(Response.Status.NOT_FOUND).build();
        }
    }
}