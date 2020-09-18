package br.com.rhoracio.decoder.morse.service;

import br.com.rhoracio.decoder.morse.domain.response.DecoderResponse;

/**
 * Abstração para decodificar coisas.
 *
 * Inicialmente pensado apenas para o contexto de código Morse
 *
 * @param <T> Objeto que contenha o input esperado
 *
 * @author Rafael Horácio
 */
public interface Decoder<T> {
    DecoderResponse decode(final T request);
}
