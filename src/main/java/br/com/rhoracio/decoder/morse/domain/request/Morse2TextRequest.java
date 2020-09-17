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
public class Morse2TextRequest {

    @NotBlank(message = "Preencha o código morse, não pode ser vazio")
    @Pattern(regexp = "^[-. ]*", message = "Somente ponto (.) e hífen (-) é permitido nesta versão.")
    @ApiModelProperty(name = "Código Morse a ser convertido para texto", example = ".... --- .-.. .-   -- . .-.. ..")
    private String text;

}
