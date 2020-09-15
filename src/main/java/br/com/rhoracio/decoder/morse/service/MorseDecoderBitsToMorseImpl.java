package br.com.rhoracio.decoder.morse.service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Stream;

import br.com.rhoracio.decoder.morse.domain.request.DecoderRequest;
import br.com.rhoracio.decoder.morse.domain.response.DecoderResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Service;

@Log4j2
@Service("bits2MorseService")
@RequiredArgsConstructor
public class MorseDecoderBitsToMorseImpl implements MorseDecoder {

    public static final String LONG_PULSE = "-";
    public static final String SHORT_PULSE = ".";
    public static final String SHORT_SPACE = "";
    public static final String LONG_SPACE = " ";
    public static final String BIT_PULSE = "1";
    public static final String BIT_PAUSE = "0";

    @Override
    public DecoderResponse decode(DecoderRequest request) {
        String input = request.getText().trim();
        ;

        List<String> pulsoList = joinUntilDifferentOcurrence(input);
        String inputWithoutNoise = String.join("", pulsoList);

        int maxPause = Stream.of(inputWithoutNoise.split(BIT_PULSE))
                .filter(p -> !p.isEmpty())
                .mapToInt(String::length)
                .max()
                .orElse(0);

        int minPause = Stream.of(inputWithoutNoise.split(BIT_PULSE))
                .filter(p -> !p.isEmpty())
                .mapToInt(String::length)
                .min()
                .orElse(0);

        int maxPulse = Stream.of(inputWithoutNoise.split(BIT_PAUSE))
                .filter(p -> !p.isEmpty())
                .mapToInt(String::length)
                .max()
                .orElse(0);

        int minPulse = Stream.of(inputWithoutNoise.split(BIT_PAUSE))
                .filter(p -> !p.isEmpty())
                .mapToInt(String::length)
                .min()
                .orElse(0);

        String[] pulsos = new String[pulsoList.size()];
        pulsos = pulsoList.toArray(pulsos);

        int avgPause = (maxPause + minPause) / 2;
        int avgPulse = (maxPulse + minPulse) / 2;

        String output = "";

        for (int i = 0; i < pulsos.length; i++) {
            String pulso = pulsos[i];

            if (pulso.startsWith(BIT_PAUSE)) {
                if (pulso.length() <= avgPause) {
                    output += SHORT_SPACE;
                } else {
                    output += LONG_SPACE;
                }
            }

            if (pulso.startsWith(BIT_PULSE)) {
                if (pulso.length() <= avgPulse) {
                    output += SHORT_PULSE;
                } else {
                    output += LONG_PULSE;
                }
            }
        }

        return new DecoderResponse(output.trim());
    }

    private List<String> joinUntilDifferentOcurrence(String input) {

        String[] bitsArray = input.split("");
        List<String> pulsoList = new ArrayList<>();
        String previousBit = "";
        String sequencia = "";

        int start = 0, end = input.length() -1;
        while(start < input.length() && input.charAt(start) == '0') start++;
        while(end >= 0 && input.charAt(end) == '0') end --;

        for (int i = start; i <= end; i++) {

            String currentBit = bitsArray[i];

            if (i == start) {
                previousBit = currentBit;
                sequencia = currentBit;
                continue;
            }

            if (currentBit.equals(previousBit)) {
                sequencia = sequencia + currentBit;
                previousBit = currentBit;

                if (i <= end) {
                    continue;
                }

            } else {
                pulsoList.add(sequencia);
                sequencia = currentBit;
                previousBit = currentBit;
            }

            if (end == i) {
                pulsoList.add(sequencia);
            }
        }

        return pulsoList;
    }

    public int getTimingFromBitSentence(String bitSentence) {

        int ret = 9999;
        String lastChar = "0";
        int count = 0;

        String[] bitArr = bitSentence.split("");
        for (int i = 0; i < bitArr.length; i++) {
            if (bitArr[i].equals("1")) {
                count++;
                lastChar = "1";
            } else if (bitArr[i].equals("0")) {
                if (lastChar == "1") {
                    if (count < ret) {
                        ret = count;
                    }
                }
                lastChar = "0";
                count = 0;
            }
        }

        return ret;
    }

    public String shrinkBitSentence(String bitSentence, int timing) {
        String ret = "";
        String[] bitArr = bitSentence.split("");
        for (int i = 0; i < bitArr[i].length(); i++) {
            if (i % timing == 0) {
                ret += bitArr[i];
            }
        }

        return ret;
    }

}


