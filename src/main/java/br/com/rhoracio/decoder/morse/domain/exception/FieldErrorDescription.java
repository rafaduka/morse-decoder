package br.com.rhoracio.decoder.morse.domain.exception;

import io.swagger.annotations.ApiModelProperty;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Builder
@Getter
@Setter
public class FieldErrorDescription {

	@ApiModelProperty(example = "Campo que ocorreu o erro")
	private String field;

	@ApiModelProperty(example = "Informações inválidas sobre consulta.")
	private String description;

}