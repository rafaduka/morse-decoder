package br.com.rhoracio.decoder.morse.service;

import br.com.rhoracio.decoder.morse.domain.MorseDictionary;
import br.com.rhoracio.decoder.morse.domain.request.Morse2TextRequest;
import br.com.rhoracio.decoder.morse.domain.response.DecoderResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Service;

@Log4j2
@Service("morseToTextService")
@RequiredArgsConstructor
public class MorseDecoderMorseToTextImpl implements MorseDecoder<Morse2TextRequest> {

    public static final String SPACE_BETWEEN_CHAR = "";
    public static final String SPACE_BETWEEN_MORSE_CODE = " ";
    public static final int ZERO = 0;

    @Override
    public DecoderResponse decode(final Morse2TextRequest request) {
        String input = request.getText().trim();
        String[] morseArray =  input.split("\\s{1}");

        String output = SPACE_BETWEEN_CHAR;
        int countSpaceInSequence = 0;

        for (int i = 0 ; i < morseArray.length; i++) {

            if (morseArray[i].length() == ZERO) {
                countSpaceInSequence++;
                continue;
            } else {
                if (countSpaceInSequence > 0) {
                    output += SPACE_BETWEEN_MORSE_CODE;
                }
                countSpaceInSequence = 0;
            }

            output += MorseDictionary.getLetterByMorseKey((morseArray[i]));
        }
        log.info("Decoded from: [{}] to: [{}]", input, output);
        return new DecoderResponse(output);
    }
}
