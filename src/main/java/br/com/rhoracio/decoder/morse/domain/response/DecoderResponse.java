package br.com.rhoracio.decoder.morse.domain.response;

import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class DecoderResponse {

    @ApiModelProperty(name = "Response do valor codificado")
    private String response;
}
