package br.com.rhoracio.decoder.morse.domain.exception;

import java.util.List;

import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@Builder
public class ErrorDescription {

	@ApiModelProperty(example = "Path do request com erro")
	private String path;

	@ApiModelProperty(example = "Lista de erros ao requisitar API")
	private List<FieldErrorDescription> errors;

}