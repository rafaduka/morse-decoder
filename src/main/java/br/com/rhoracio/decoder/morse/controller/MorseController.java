package br.com.rhoracio.decoder.morse.controller;

import javax.validation.Valid;

import br.com.rhoracio.decoder.morse.domain.request.DecoderRequest;
import br.com.rhoracio.decoder.morse.domain.response.DecoderResponse;
import br.com.rhoracio.decoder.morse.service.MorseDecoder;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(path = "/api/v1/translate", produces = MediaType.APPLICATION_JSON_VALUE)
@RequiredArgsConstructor
public class MorseController {

    private final MorseDecoder bits2MorseService;
    private final MorseDecoder morseToTextService;
    private final MorseDecoder textToMorseService;

    @PostMapping(path = "/2text")
    public ResponseEntity<DecoderResponse> toText(@Valid @RequestBody DecoderRequest request) {
        return ResponseEntity.ok(textToMorseService.decode(request));
    }

}
