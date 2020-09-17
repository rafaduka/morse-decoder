package br.com.rhoracio.decoder.morse.service;

import static org.assertj.core.api.Assertions.assertThat;

import br.com.rhoracio.decoder.morse.domain.request.Morse2TextRequest;
import br.com.rhoracio.decoder.morse.domain.response.DecoderResponse;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class MorseDecoderMorseToTextImplTest {

    public static final String EXPECTED = "HOLA MELI";
    public static final String INPUT = ".... --- .-.. .-   -- . .-.. ..";
    private MorseDecoder decoder;
    private Morse2TextRequest request;


    @BeforeEach
    void setUp() {
        decoder = new MorseDecoderMorseToTextImpl();
        request = createDecoderRequest();
    }

    private static Morse2TextRequest createDecoderRequest() {
        Morse2TextRequest request = new Morse2TextRequest();
        request.setText(INPUT);
        return request;
    }

    @Test
    void decode() {
        DecoderResponse response = decoder.decode(request);
        assertThat(response.getResponse()).isEqualTo(EXPECTED);
    }

}