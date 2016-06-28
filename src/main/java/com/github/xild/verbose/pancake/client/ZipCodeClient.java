package com.github.xild.verbose.pancake.client;

import com.github.xild.verbose.pancake.model.to.AddressOutput;

import feign.Param;
import feign.RequestLine;

public interface ZipCodeClient {

    @RequestLine("GET /buscaCep/{cep}")
    AddressOutput findAddress(@Param("cep") String cep);
}
