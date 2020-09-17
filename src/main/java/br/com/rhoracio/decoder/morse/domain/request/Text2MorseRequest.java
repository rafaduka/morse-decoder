package br.com.rhoracio.decoder.morse.domain.request;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Text2MorseRequest {

    @NotBlank(message = "Texto não pode ser vazio")
    @Pattern(regexp = "^[A-Za-z ]*", message = "Somente letras é permitido nesta versão, para suporte a números contrate o desenvolvedor ;)")
    @ApiModelProperty(name = "Texto a ser convertido para código morse", example = "HOLA MELI")
    private String text;

}
