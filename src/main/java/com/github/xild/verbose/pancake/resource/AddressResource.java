package com.github.xild.verbose.pancake.resource;

import static javax.ws.rs.core.Response.Status.BAD_REQUEST;

import java.util.Optional;

import javax.inject.Inject;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.container.AsyncResponse;
import javax.ws.rs.container.Suspended;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import com.github.xild.verbose.pancake.client.ZipCodeClient;
import com.github.xild.verbose.pancake.exception.AddressResourceException;
import com.github.xild.verbose.pancake.model.Address;
import com.github.xild.verbose.pancake.model.to.AddressInput;
import com.github.xild.verbose.pancake.model.to.AddressOutput;
import com.github.xild.verbose.pancake.model.to.ErrorOutput;
import com.github.xild.verbose.pancake.services.AddressServices;

@Path("/")
@Produces({ MediaType.APPLICATION_JSON })
@Consumes({ MediaType.APPLICATION_JSON })
public class AddressResource {

	private AddressServices services;

	private ZipCodeClient client;
	
	@Inject
	public AddressResource(AddressServices services, ZipCodeClient client) {
		this.services = services;
		this.client = client;
	}

	@POST
	@Path("/address")
	public void saveAddress(AddressInput input, @Suspended AsyncResponse async) {
		Response output = client.findAddress(input.getCep());

		if (output.getStatus() == Response.Status.BAD_REQUEST.getStatusCode()) {
			ErrorOutput errorOutput = (ErrorOutput) output.getEntity();
			async.resume(Response.status(BAD_REQUEST).entity(errorOutput).build());
		}
		
		services.saveAddress(input);
		Response.ok().entity("OK").build();
	}

	@PUT
	@Path("/address")
	public void updateAddress(@Suspended AsyncResponse async) {
		Response.ok().entity("OK").build();
	}

	@DELETE
	@Path("/address/{id}")
	public void deleteAddress(@PathParam("id") Long id, @Suspended AsyncResponse async) {
		try {
			services.deleteAddressById(id);
		} catch (AddressResourceException e) {
			ErrorOutput errorOutput = new ErrorOutput.Builder() //
					.message(e.getMessage()) //
					.errors(BAD_REQUEST.name()) //
					.httpStatus(BAD_REQUEST.getStatusCode()) //
					.build();
			async.resume(Response.status(BAD_REQUEST).entity(errorOutput).build());
		}
		async.resume(Response.status(Response.Status.NO_CONTENT).build());
	}

	@GET
	@Path("/address/{id}")
	public void getAddressById(@PathParam("id") Long id, @Suspended AsyncResponse async) {
		Optional<Address> optionalAddress = Optional.empty();
		optionalAddress = services.findById(id);

		if (optionalAddress.isPresent()) {
			Address address = optionalAddress.get();
			AddressOutput output = new AddressOutput.Builder() //
					.bairro(address.getNeighborhood()) //
					.cidade(address.getCity())//
					.estado(address.getState())//
					.rua(address.getStreet())//
					.numero(address.getAddressNumber()) //
					.cep(address.getZipCode())//
					.complemento(address.getAddressNumber())//
					.build();
			async.resume(Response.ok().entity(output).build());
			return;
		}

		async.resume(Response.status(Response.Status.NO_CONTENT)//
				.build());
	}

}