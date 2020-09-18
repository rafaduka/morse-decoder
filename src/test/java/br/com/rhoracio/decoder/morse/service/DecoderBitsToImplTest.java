package br.com.rhoracio.decoder.morse.service;

import static org.assertj.core.api.Assertions.assertThat;

import br.com.rhoracio.decoder.morse.domain.request.Bits2MorseRequest;
import br.com.rhoracio.decoder.morse.domain.response.DecoderResponse;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class DecoderBitsToImplTest {

    public static final String EXPECTED = ".... ---  .-..  .- --  . .-.. ..";
    public static final String INPUT =  "000000001101101100111"         +   //H
                                        "0000011111100011111100111111"  +   //O
                                        "000000011101111111101110111"   +   //L
                                        "000000011000111111"            +   //A
                                        "00000"                         +   ///
                                        "11111100111111"                +   //M
                                        "000000011"                     +   //E
                                        "00001101111111101110111"       +   //L
                                        "00000011011100000000000"           //I
                                        ;

    private Decoder decoder;
    private Bits2MorseRequest request;


    @BeforeEach
    void setUp() {
        decoder = new DecoderBitsToMorseImpl();
        request = createDecoderRequest();
    }

    private static Bits2MorseRequest createDecoderRequest() {
        Bits2MorseRequest request = new Bits2MorseRequest();
        request.setText(INPUT);
        return request;
    }

    @Test
    void decode() {
        DecoderResponse response = decoder.decode(request);
        assertThat(response.getResponse()).isEqualTo(EXPECTED);
    }

}