package br.com.rhoracio.decoder.morse.service;

import br.com.rhoracio.decoder.morse.domain.response.DecoderResponse;

public interface MorseDecoder <T> {
    DecoderResponse decode(final T request);
}
