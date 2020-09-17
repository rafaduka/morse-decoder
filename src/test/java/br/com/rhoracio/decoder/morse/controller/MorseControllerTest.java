package br.com.rhoracio.decoder.morse.controller;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import br.com.rhoracio.decoder.morse.domain.request.Bits2MorseRequest;
import br.com.rhoracio.decoder.morse.domain.request.Morse2TextRequest;
import br.com.rhoracio.decoder.morse.domain.request.Text2MorseRequest;
import br.com.rhoracio.decoder.morse.service.MorseDecoderBitsToMorseImplTest;
import br.com.rhoracio.decoder.morse.service.MorseDecoderMorseToTextImplTest;
import br.com.rhoracio.decoder.morse.service.MorseDecoderTextToMorseImplTest;
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
        request.setText(MorseDecoderTextToMorseImplTest.INPUT);
        mock.perform(post("/api/v1/translate/2text")
                .contentType(MediaType.APPLICATION_JSON)
                .content(mapper.writeValueAsString(request))
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.response").value(MorseDecoderTextToMorseImplTest.EXPECTED))
        .andExpect(status().isOk());
    }

    @Test
    public void testMorse2Text() throws Exception {
        Morse2TextRequest request = new Morse2TextRequest();
        request.setText(MorseDecoderMorseToTextImplTest.INPUT);
        mock.perform(post("/api/v1/translate/2morse")
                .contentType(MediaType.APPLICATION_JSON)
                .content(mapper.writeValueAsString(request))
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.response").value(MorseDecoderMorseToTextImplTest.EXPECTED))
                .andExpect(status().isOk());
    }

    @Test
    public void testBits2Morse() throws Exception {
        Bits2MorseRequest request = new Bits2MorseRequest();
        request.setText(MorseDecoderBitsToMorseImplTest.INPUT);
        mock.perform(post("/api/v1/translate/bits2morse")
                .contentType(MediaType.APPLICATION_JSON)
                .content(mapper.writeValueAsString(request))
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.response").value(MorseDecoderBitsToMorseImplTest.EXPECTED))
                .andExpect(status().isOk());
    }
}