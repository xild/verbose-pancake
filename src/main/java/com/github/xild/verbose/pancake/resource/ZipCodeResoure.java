package com.github.xild.verbose.pancake.resource;

import java.util.Optional;

import javax.inject.Inject;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.container.AsyncResponse;
import javax.ws.rs.container.Suspended;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import org.apache.commons.lang3.StringUtils;

import com.github.xild.verbose.pancake.model.Address;
import com.github.xild.verbose.pancake.model.to.AddressOutput;
import com.github.xild.verbose.pancake.model.to.ErrorOutput;
import com.github.xild.verbose.pancake.services.ZipCodeServices;
import static javax.ws.rs.core.Response.Status.*;

@Path("")
@Produces({ MediaType.APPLICATION_JSON })
@Consumes({ MediaType.APPLICATION_JSON })
public class ZipCodeResoure {

	private ZipCodeServices services;

	@Inject
	public ZipCodeResoure(ZipCodeServices services) {
		this.services = services;
	}

	@GET
	@Path("/buscaCep/{cep}")
	public void getAddressByCep(@PathParam("cep") String zipCode, @Suspended AsyncResponse async) {

		if (!StringUtils.isNumeric(zipCode) || zipCode.length() != 8) {
			ErrorOutput errorOutput = new ErrorOutput.Builder() //
					.message("INVALID INPUT") //
					.errors(BAD_REQUEST.name()) //
					.httpStatus(BAD_REQUEST.getStatusCode()) //
					.build();
			
			async.resume(Response.status(BAD_REQUEST)//
					.entity(errorOutput) //
					.build());
			return;
		}

		Optional<Address> optionalAddress = services.searchZipCode(zipCode);

		if (optionalAddress.isPresent()) {
			Address address = optionalAddress.get();
			AddressOutput output = new AddressOutput.Builder() //
					.bairro(address.getNeighborhood()) //
					.cidade(address.getCity())//
					.estado(address.getState())//
					.rua(address.getStreet())//
					.build();
			async.resume(Response.status(OK).entity(output).build());
			return;
		}

		ErrorOutput errorOutput = new ErrorOutput.Builder() //
				.message("Cep Inválido") //
				.errors(NOT_FOUND.name()) //
				.httpStatus(NOT_FOUND.getStatusCode()) //
				.build();
		async.resume(Response.status(NOT_FOUND).entity(errorOutput).build());
		return;

	}

}