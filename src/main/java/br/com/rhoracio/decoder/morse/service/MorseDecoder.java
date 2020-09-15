package br.com.rhoracio.decoder.morse.service;

import br.com.rhoracio.decoder.morse.domain.request.DecoderRequest;
import br.com.rhoracio.decoder.morse.domain.response.DecoderResponse;

public interface MorseDecoder {
    DecoderResponse decode(DecoderRequest request);
}
