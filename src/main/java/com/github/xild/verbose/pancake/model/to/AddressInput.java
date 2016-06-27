/**
 * @Description:
 * @Author: lvieir9
 * @Since: Jun 26, 2016
 * @Version: 
 */
package com.github.xild.verbose.pancake.model.to;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.github.xild.verbose.pancake.model.Address;

/**
 * @author Luis Vieira
 *
 */
@JsonIgnoreProperties(ignoreUnknown = true)
public class AddressInput {

	@JsonProperty(required = true)
	private String rua;

	@JsonProperty(required = true)
	private String numero;

	@JsonProperty(required = true)
	private String cep;

	@JsonProperty(required = true)
	private String cidade;

	@JsonProperty(required = true)
	private String estado;

	@JsonProperty(required = false)
	private String bairro;

	@JsonProperty(required = false)
	private String complemento;

	public AddressInput(){
		super();
	}
	
	public AddressInput(Address address){
		this.rua = address.getStreet();
		this.numero = address.getAddressNumber();
		this.cep = address.getZipCode();
		this.complemento = address.getAddressDetail();
		this.estado = address.getState();
		this.bairro = address.getNeighborhood();
		this.cidade = address.getCity();
	}

	public String getRua() {
		return rua;
	}

	public void setRua(String rua) {
		this.rua = rua;
	}

	public String getNumero() {
		return numero;
	}

	public void setNumero(String numero) {
		this.numero = numero;
	}

	public String getCep() {
		return cep;
	}

	public void setCep(String cep) {
		this.cep = cep;
	}

	public String getCidade() {
		return cidade;
	}

	public void setCidade(String cidade) {
		this.cidade = cidade;
	}

	public String getEstado() {
		return estado;
	}

	public void setEstado(String estado) {
		this.estado = estado;
	}

	public String getBairro() {
		return bairro;
	}

	public void setBairro(String bairro) {
		this.bairro = bairro;
	}

	public String getComplemento() {
		return complemento;
	}

	public void setComplemento(String complemento) {
		this.complemento = complemento;
	}

}
