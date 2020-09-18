package br.com.rhoracio.decoder.morse.service;

import br.com.rhoracio.decoder.morse.domain.MorseDictionary;
import br.com.rhoracio.decoder.morse.domain.request.Text2MorseRequest;
import br.com.rhoracio.decoder.morse.domain.response.DecoderResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Service;

@Log4j2
@Service("textToMorseService")
@RequiredArgsConstructor
public class DecoderTextToMorseImpl implements Decoder<Text2MorseRequest> {

    public static final int ZERO = 0;
    public static final String SPACE_BETWEEN_WORDS = "  ";
    public static final String SPACE_BETWEEN_LETTERS = " ";

    @Override
    public DecoderResponse decode(final Text2MorseRequest request) {

        String input = request.getText().trim().toUpperCase();
        String[] letters =  input.split("");

        String output = "";

        for (String letter : letters) {
            if (letter.trim().length() == ZERO) {
                output += SPACE_BETWEEN_WORDS;
                continue;
            }
            output += MorseDictionary.getMorseByTextKey(letter) + SPACE_BETWEEN_LETTERS;
        }
        log.info("Decoded from: [{}] to: [{}]", input, output);
        return new DecoderResponse(output.trim());
    }
}
