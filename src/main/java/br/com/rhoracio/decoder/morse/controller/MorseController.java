package br.com.rhoracio.decoder.morse.controller;

import javax.validation.Valid;

import br.com.rhoracio.decoder.morse.domain.request.Bits2MorseRequest;
import br.com.rhoracio.decoder.morse.domain.request.Text2MorseRequest;
import br.com.rhoracio.decoder.morse.domain.request.Morse2TextRequest;
import br.com.rhoracio.decoder.morse.domain.response.DecoderResponse;
import br.com.rhoracio.decoder.morse.service.Decoder;
import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(path = "/api/v1/decoder",
                        produces = MediaType.APPLICATION_JSON_VALUE)
@RequiredArgsConstructor
public class MorseController {

    private final Decoder<Bits2MorseRequest> bits2MorseService;
    private final Decoder<Morse2TextRequest> morseToTextService;
    private final Decoder<Text2MorseRequest> textToMorseService;

    @PostMapping(path = "/morse2text")
    public ResponseEntity<DecoderResponse> decoderToText(@Valid @RequestBody Morse2TextRequest request) {
        return ResponseEntity.ok(morseToTextService.decode(request));
    }

    @PostMapping(path = "/text2morse")
    public ResponseEntity<DecoderResponse> textToMorse(@Valid @RequestBody Text2MorseRequest request) {
        return ResponseEntity.ok(textToMorseService.decode(request));
    }

    @PostMapping(path = "/bits2morse")
    public ResponseEntity<DecoderResponse> bitsToMorse(@Valid @RequestBody Bits2MorseRequest request) {
        return ResponseEntity.ok(bits2MorseService.decode(request));
    }

}
