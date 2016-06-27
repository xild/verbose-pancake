/**
 * @Description:
 * @Author: lvieir9
 * @Since: Jun 23, 2016
 * @Version: 
 */
package com.github.xild.verbose.pancake.model.to;

import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * @author Luis Vieira
 *
 */
public class AddressOutput implements TransferObject {

	private static final long serialVersionUID = 8870027544757295266L;

	@JsonProperty
	private String rua;

	@JsonProperty
	private String cidade;

	@JsonProperty
	private String estado;

	@JsonProperty
	private String numero;

	@JsonProperty
	private String cep;

	@JsonProperty
	private String bairro;

	@JsonProperty
	private String complemento;

	public String getRua() {
		return rua;
	}

	public String getBairro() {
		return bairro;
	}

	public String getCidade() {
		return cidade;
	}

	public String getEstado() {
		return estado;
	}

	public String getNumero() {
		return numero;
	}

	public String getCep() {
		return cep;
	}

	public String getComplemento() {
		return complemento;
	}

	public static class Builder {
		private String rua;
		private String cidade;
		private String estado;
		private String numero;
		private String cep;
		private String bairro;
		private String complemento;

		public Builder rua(String rua) {
			this.rua = rua;
			return this;
		}

		public Builder cidade(String cidade) {
			this.cidade = cidade;
			return this;
		}

		public Builder estado(String estado) {
			this.estado = estado;
			return this;
		}

		public Builder numero(String numero) {
			this.numero = numero;
			return this;
		}

		public Builder cep(String cep) {
			this.cep = cep;
			return this;
		}

		public Builder bairro(String bairro) {
			this.bairro = bairro;
			return this;
		}

		public Builder complemento(String complemento) {
			this.complemento = complemento;
			return this;
		}

		public AddressOutput build() {
			return new AddressOutput(this);
		}
	}

	private AddressOutput(Builder builder) {
		this.rua = builder.rua;
		this.cidade = builder.cidade;
		this.estado = builder.estado;
		this.numero = builder.numero;
		this.cep = builder.cep;
		this.bairro = builder.bairro;
		this.complemento = builder.complemento;
	}
}
