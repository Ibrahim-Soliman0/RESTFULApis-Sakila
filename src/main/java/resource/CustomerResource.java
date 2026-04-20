package resource;

import dto.CustomerDto;
import entity.Address;
import entity.Customer;
import entity.Store;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.Context;
import jakarta.ws.rs.core.GenericEntity;
import jakarta.ws.rs.core.HttpHeaders;
import jakarta.ws.rs.core.Response;
import service.AddressService;
import service.CustomerService;
import service.StoreService;

import java.util.List;
import java.util.Optional;

@Path("customers")
public class CustomerResource {

    private final CustomerService customerService = new CustomerService();
    private final StoreService storeService = new StoreService();
    private final AddressService addressService = new AddressService();

    @GET
    @Produces({ "application/xml", "application/json" })
    public Response getAllCustomers(@QueryParam("page") @DefaultValue("0") int page,
            @QueryParam("size") @DefaultValue("10") int size) {
        List<CustomerDto> customerDtos = customerService.getAllDtos(page, size);
        return Response.ok(new GenericEntity<>(customerDtos) {
        }).build();
    }

    @GET
    @Path("/{id}")
    @Produces({ "application/xml", "application/json" })
    public Response getCustomerById(@PathParam("id") Integer id) {
        Optional<CustomerDto> customerDto = customerService.getByIdDto(id);
        if (customerDto.isPresent()) {
            return Response.ok(customerDto.get()).build();
        } else {
            return Response.status(Response.Status.NOT_FOUND).build();
        }
    }

    @POST
    @Consumes({ "application/xml", "application/json" })
    @Produces({ "application/xml", "application/json" })
    public Response createCustomer(CustomerDto customerDto) {
        Optional<Store> store = storeService.getById(customerDto.getStoreId());
        if (store.isEmpty()) {
            return Response.status(Response.Status.BAD_REQUEST).entity("Invalid storeId").build();
        }
        Optional<Address> address = addressService.getById(customerDto.getAddressId());
        if (address.isEmpty()) {
            return Response.status(Response.Status.BAD_REQUEST).entity("Invalid addressId").build();
        }
        CustomerDto savedCustomerDto = customerService.saveDto(customerDto);
        return Response.status(Response.Status.CREATED).entity(savedCustomerDto).build();
    }

    @PUT
    @Path("/{id}")
    @Consumes({ "application/xml", "application/json" })
    @Produces({ "application/xml", "application/json" })
    public Response updateCustomer(@PathParam("id") Integer id, CustomerDto customerDto) {
        Optional<Store> store = storeService.getById(customerDto.getStoreId());
        if (store.isEmpty()) {
            return Response.status(Response.Status.BAD_REQUEST).entity("Invalid storeId").build();
        }
        Optional<Address> address = addressService.getById(customerDto.getAddressId());
        if (address.isEmpty()) {
            return Response.status(Response.Status.BAD_REQUEST).entity("Invalid addressId").build();
        }
        Optional<CustomerDto> updatedDto = customerService.updateDto(id, customerDto);
        if (updatedDto.isPresent()) {
            return Response.ok(updatedDto.get()).build();
        } else {
            return Response.status(Response.Status.NOT_FOUND).build();
        }
    }

    @DELETE
    @Path("/{id}")
    public Response deleteCustomer(@PathParam("id") Integer id) {
        Optional<Customer> customer = customerService.getById(id);
        if (customer.isPresent()) {
            customerService.delete(customer.get());
            return Response.noContent().build();
        } else {
            return Response.status(Response.Status.NOT_FOUND).build();
        }
    }
}