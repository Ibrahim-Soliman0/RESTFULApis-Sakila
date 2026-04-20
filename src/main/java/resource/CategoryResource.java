package resource;

import dto.CategoryDto;
import entity.Category;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.GenericEntity;
import jakarta.ws.rs.core.Response;
import service.CategoryService;

import java.util.List;
import java.util.Optional;

@Path("categories")
public class CategoryResource {

    private final CategoryService categoryService = new CategoryService();

    @GET
    @Produces({ "application/xml", "application/json" })
    public Response getAllCategories(@QueryParam("page") @DefaultValue("0") int page,
            @QueryParam("size") @DefaultValue("10") int size) {
        List<CategoryDto> categoryDtos = categoryService.getAllDtos(page, size);
        return Response.ok(new GenericEntity<>(categoryDtos) {
        }).build();
    }

    @GET
    @Path("/{id}")
    @Produces({ "application/xml", "application/json" })
    public Response getCategoryById(@PathParam("id") Short id) {
        Optional<CategoryDto> categoryDto = categoryService.getByIdDto(id);
        if (categoryDto.isPresent()) {
            return Response.ok(categoryDto.get()).build();
        } else {
            return Response.status(Response.Status.NOT_FOUND).build();
        }
    }

    @POST
    @Consumes({ "application/xml", "application/json" })
    @Produces({ "application/xml", "application/json" })
    public Response createCategory(CategoryDto categoryDto) {
        CategoryDto savedDto = categoryService.saveDto(categoryDto);
        return Response.status(Response.Status.CREATED).entity(savedDto).build();
    }

    @PUT
    @Path("/{id}")
    @Consumes({ "application/xml", "application/json" })
    @Produces({ "application/xml", "application/json" })
    public Response updateCategory(@PathParam("id") Short id, CategoryDto categoryDto) {
        Optional<CategoryDto> updatedDto = categoryService.updateDto(id, categoryDto);
        if (updatedDto.isPresent()) {
            return Response.ok(updatedDto.get()).build();
        } else {
            return Response.status(Response.Status.NOT_FOUND).build();
        }
    }

    @DELETE
    @Path("/{id}")
    public Response deleteCategory(@PathParam("id") Short id) {
        Optional<Category> category = categoryService.getById(id);
        if (category.isPresent()) {
            categoryService.delete(category.get());
            return Response.noContent().build();
        } else {
            return Response.status(Response.Status.NOT_FOUND).build();
        }
    }
}