package br.com.rhoracio.decoder.morse.controller;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import br.com.rhoracio.decoder.morse.domain.request.Bits2MorseRequest;
import br.com.rhoracio.decoder.morse.domain.request.Morse2TextRequest;
import br.com.rhoracio.decoder.morse.domain.request.Text2MorseRequest;
import br.com.rhoracio.decoder.morse.service.DecoderBitsToImplTest;
import br.com.rhoracio.decoder.morse.service.DecoderToTextImplTest;
import br.com.rhoracio.decoder.morse.service.DecoderTextToImplTest;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

@SpringBootTest
@WebAppConfiguration
public class MorseControllerTest {

    @Autowired
    private WebApplicationContext wac;

    private MockMvc mock;

    private ObjectMapper mapper;


    @BeforeEach
    public void setUp() {
        mapper = new ObjectMapper();
        mock = MockMvcBuilders.webAppContextSetup(this.wac).build();
    }

    @Test
    public void testText2Morse() throws Exception {
        Text2MorseRequest request = new Text2MorseRequest();
        request.setText(DecoderTextToImplTest.INPUT);
        mock.perform(post("/api/v1/decoder/text2morse")
                .contentType(MediaType.APPLICATION_JSON)
                .content(mapper.writeValueAsString(request))
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.response").value(DecoderTextToImplTest.EXPECTED))
        .andExpect(status().isOk());
    }

    @Test
    public void testText2MorseWithInputInvalid() throws Exception {
        Text2MorseRequest request = new Text2MorseRequest();
        request.setText("01010");
        mock.perform(post("/api/v1/decoder/text2morse")
                .contentType(MediaType.APPLICATION_JSON)
                .content(mapper.writeValueAsString(request))
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isBadRequest());
    }

    @Test
    public void testMorse2Text() throws Exception {
        Morse2TextRequest request = new Morse2TextRequest();
        request.setText(DecoderToTextImplTest.INPUT);
        mock.perform(post("/api/v1/decoder/morse2text")
                .contentType(MediaType.APPLICATION_JSON)
                .content(mapper.writeValueAsString(request))
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.response").value(DecoderToTextImplTest.EXPECTED))
                .andExpect(status().isOk());
    }

    @Test
    public void testMorse2TextWithInvalidInput() throws Exception {
        Morse2TextRequest request = new Morse2TextRequest();
        request.setText("Wrong Value");
        mock.perform(post("/api/v1/decoder/morse2text")
                .contentType(MediaType.APPLICATION_JSON)
                .content(mapper.writeValueAsString(request))
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isBadRequest());
    }

    @Test
    public void testBits2Morse() throws Exception {
        Bits2MorseRequest request = new Bits2MorseRequest();
        request.setText(DecoderBitsToImplTest.INPUT);
        mock.perform(post("/api/v1/decoder/bits2morse")
                .contentType(MediaType.APPLICATION_JSON)
                .content(mapper.writeValueAsString(request))
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.response").value(DecoderBitsToImplTest.EXPECTED))
                .andExpect(status().isOk());
    }

    @Test
    public void testBits2MorseWithInvalidInput() throws Exception {
        Bits2MorseRequest request = new Bits2MorseRequest();
        request.setText("Wrong Value");
        mock.perform(post("/api/v1/decoder/bits2morse")
                .contentType(MediaType.APPLICATION_JSON)
                .content(mapper.writeValueAsString(request))
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isBadRequest());
    }
}