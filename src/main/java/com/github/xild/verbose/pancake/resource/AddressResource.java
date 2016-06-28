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

import com.github.xild.verbose.pancake.exception.AddressResourceException;
import com.github.xild.verbose.pancake.exception.ZipCodeInvalidException;
import com.github.xild.verbose.pancake.model.Address;
import com.github.xild.verbose.pancake.model.to.AddressOutput;
import com.github.xild.verbose.pancake.model.to.AddressTO;
import com.github.xild.verbose.pancake.model.to.ErrorOutput;
import com.github.xild.verbose.pancake.services.AddressServices;
import com.github.xild.verbose.pancake.utils.JsonUtils;

@Path("/")
@Produces({ MediaType.APPLICATION_JSON })
@Consumes({ MediaType.APPLICATION_JSON })
public class AddressResource {

	private AddressServices services;

	@Inject
	public AddressResource(AddressServices services) {
		this.services = services;
	}

	@POST
	@Path("/address")
	public void saveAddress(AddressTO input, @Suspended AsyncResponse async) {
		try {
			Address address = services.saveAddress(input);

			AddressOutput output = new AddressOutput.Builder()//
					.cidade(address.getCity())//
					.cep(address.getZipCode())//
					.bairro(address.getNeighborhood()) //
					.id(address.getId()) //
					.complemento(address.getAddressDetail())//
					.numero(address.getAddressNumber())//
					.estado(address.getState())//
					.rua(address.getStreet())//
					.build();

			async.resume(Response.status(Response.Status.CREATED)//
					.entity(output) //
					.build());
			
		} catch (ZipCodeInvalidException fe) {
			ErrorOutput errorOutput = JsonUtils.toObject(fe.getResponse().body().toString(), ErrorOutput.class);
			async.resume(Response.status(BAD_REQUEST)//
					.entity(errorOutput) //
					.build());
			return;
		}

	}

	@PUT
	@Path("/address")
	public void updateAddress(AddressTO input, @Suspended AsyncResponse async) {
		try {

			Address address = services.updateAddress(input);
			AddressOutput output = new AddressOutput.Builder()//
					.cidade(address.getCity())//
					.cep(address.getZipCode())//
					.bairro(address.getNeighborhood()) //
					.id(address.getId()) //
					.complemento(address.getAddressDetail())//
					.numero(address.getAddressNumber())//
					.estado(address.getState())//
					.rua(address.getStreet())//
					.build();

			async.resume(Response.ok().entity(output).build());

		} catch (ZipCodeInvalidException fe) {
			ErrorOutput errorOutput = JsonUtils.toObject(fe.getResponse().body().toString(), ErrorOutput.class);
			async.resume(Response.status(BAD_REQUEST)//
					.entity(errorOutput) //
					.build());
			return;
		} catch (AddressResourceException ae) {
			ErrorOutput errorOutput = new ErrorOutput.Builder().errors(ae.getLocalizedMessage())//
					.httpStatus(BAD_REQUEST.getStatusCode())//
					.message(ae.getMessage()).build();//

			async.resume(Response.status(BAD_REQUEST)//
					.entity(errorOutput) //
					.build());
			return;
		}
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
					.id(address.getId()).bairro(address.getNeighborhood()) //
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