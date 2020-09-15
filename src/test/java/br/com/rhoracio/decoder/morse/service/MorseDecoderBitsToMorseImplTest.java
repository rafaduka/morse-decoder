package br.com.rhoracio.decoder.morse.service;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

import br.com.rhoracio.decoder.morse.domain.request.DecoderRequest;
import br.com.rhoracio.decoder.morse.domain.response.DecoderResponse;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class MorseDecoderBitsToMorseImplTest {

    public static final String EXPECTED = ".... --- .-.. .-   -- . .-.. ..";

//    public static final String INPUT = "00000000110110110011100000111111000111111001111110000000111011111111011101110000" +
//                                        "000110001111110000011111100111111000000001100001101111111101110111000000110111";

    public static final String INPUT = "000000001010101000";
    private MorseDecoder decoder;
    private DecoderRequest request;


    @BeforeEach
    void setUp() {
        decoder = new MorseDecoderBitsToMorseImpl();
        request = createDecoderRequest();
    }

    private static DecoderRequest createDecoderRequest() {
        DecoderRequest request = new DecoderRequest();
        request.setText(INPUT);
        return request;
    }

    @Test
    void decode() {
        DecoderResponse response = decoder.decode(request);
        assertThat(response.getResponse()).isEqualTo(EXPECTED);
    }

}