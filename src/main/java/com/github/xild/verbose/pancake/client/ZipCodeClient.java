package com.github.xild.verbose.pancake.client;

import javax.ws.rs.core.Response;

import feign.Param;
import feign.RequestLine;

public interface ZipCodeClient {

    @RequestLine("GET /buscaCep/{cep}")
    Response findAddress(@Param("cep") String cep);
}
