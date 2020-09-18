package br.com.rhoracio.decoder.morse.domain.request;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;

import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Bits2MorseRequest {

    @NotBlank(message = "Preencha os bits, não pode ser vazio: (0,1) apenas.")
    @Pattern(regexp = "^[01 ]*", message = "Somente bits [0] e [1] é permitido nesta versão")
    @ApiModelProperty(name = "Valor em Bits", example = "000000001101101100111000001111110001111110011111100000001110111111110111011100000001100011111100000111111001111110000000110000110111111110111011100000011011100000000000")
    private String text;

}
