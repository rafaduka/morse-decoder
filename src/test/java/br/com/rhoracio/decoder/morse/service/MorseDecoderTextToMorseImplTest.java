package br.com.rhoracio.decoder.morse.service;

import static org.assertj.core.api.Assertions.assertThat;

import br.com.rhoracio.decoder.morse.domain.request.Text2MorseRequest;
import br.com.rhoracio.decoder.morse.domain.response.DecoderResponse;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class MorseDecoderTextToMorseImplTest {

    public static final String INPUT = "HOLA MELI";
    public static final String EXPECTED = ".... --- .-.. .-   -- . .-.. ..";
    private MorseDecoder decoder;
    private Text2MorseRequest request;


    @BeforeEach
    void setUp() {
        decoder = new MorseDecoderTextToMorseImpl();
        request = createDecoderRequest();
    }

    private static Text2MorseRequest createDecoderRequest() {
        Text2MorseRequest request = new Text2MorseRequest();
        request.setText(INPUT);
        return request;
    }

    @Test
    void decode() {
        DecoderResponse response = decoder.decode(request);
        assertThat(response.getResponse()).isEqualTo(EXPECTED);
    }
}