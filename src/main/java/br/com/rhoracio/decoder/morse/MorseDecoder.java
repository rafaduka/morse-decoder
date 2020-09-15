package br.com.rhoracio.decoder.morse;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class MorseDecoder {

    public static Map<String, String> morseMap = new HashMap<>();

    static {
        morseMap.put(".-", "A");
        morseMap.put("-...","B");
        morseMap.put("-.-.","C");
        morseMap.put("-..","D");
        morseMap.put(".","E");
        morseMap.put("..-.","F");
        morseMap.put("--.","G");
        morseMap.put("....","H");
        morseMap.put("..","I");
        morseMap.put(".---","J");
        morseMap.put("-.-","K");
        morseMap.put(".-..","L");
        morseMap.put("--","M");
        morseMap.put("-.","N");
        morseMap.put("---","O");
        morseMap.put(".--.","P");
        morseMap.put("--.-","Q");
        morseMap.put(".-.","R");
        morseMap.put("...","S");
        morseMap.put("-","T");
        morseMap.put("..-","U");
        morseMap.put("...-","V");
        morseMap.put(".--","W");
        morseMap.put("-..-","X");
        morseMap.put("-.--","Y");
        morseMap.put("--..","Z");
        morseMap.put(".----","1");
        morseMap.put("..---","2");
        morseMap.put("...--","3");
        morseMap.put("....-","4");
        morseMap.put(".....","5");
        morseMap.put("-....","6");
        morseMap.put("--...","7");
        morseMap.put("---..","8");
        morseMap.put("----.","9");
        morseMap.put("-----","0");

    }

    public static void main(String[] args) {
        final String MORSE_STREAM = "00000000110110110011100000111111000111111001111110000000111011111111011101110000000" +
                                    "110001111110000011111100111111000000001100001101111111101110111000000110111"; //HOlA MELI
        MorseDecoder m = new MorseDecoder();
        System.out.println("bin 2 morse -> " + m.decodeBitsToMorse(MORSE_STREAM));
        System.out.println("morse 2 text -> " + m.transformMorse2Text(m.decodeBitsToMorse(MORSE_STREAM)));
        System.out.println("text 2 morse -> " + m.transformText2Morse(".... --- .-.. .-   -- . .-.. .."));
    }

    public String decodeBitsToMorse(String bitStream) {

        String[] bitsArray = bitStream.split("");

        int maxPause = Stream.of(bitStream.split("1"))
                .filter(p -> !p.isEmpty())
                .mapToInt(String::length)
                .max()
                .orElse(0);

        int minPause = Stream.of(bitStream.split("1"))
                .filter(p -> !p.isEmpty())
                .mapToInt(String::length)
                .min()
                .orElse(0);

        int maxPulse = Stream.of(bitStream.split("0"))
                .filter(p -> !p.isEmpty())
                .mapToInt(String::length)
                .max()
                .orElse(0);

        int minPulse = Stream.of(bitStream.split("0"))
                .filter(p -> !p.isEmpty())
                .mapToInt(String::length)
                .min()
                .orElse(0);


        List<String> pulsoList = joinUntilDifferentOcurrence(bitsArray);

        //pulsoList.stream().forEach(System.out::println);

        String[] pulsos = new String[pulsoList.size()];
        pulsos = pulsoList.toArray(pulsos);

        int avgPause = (maxPause + minPause) / 2;
        int avgPulse = (maxPulse + minPulse) / 2;

        String decoded = "";
        for (int i = 0; i < pulsos.length; i++) {
            String pulso = pulsos[i];

            if (pulso.startsWith("0")) {
                if (pulso.length() >= avgPause) {
                    decoded += "  ";
                } else {
                    decoded += "";
                }
            }

            if (pulso.startsWith("1")) {
                if (pulso.length() < avgPulse) {
                   decoded += ".";
                } else {
                    decoded += "-";
                }
            }
        }

        return decoded;
    }

    private List<String> joinUntilDifferentOcurrence(String[] bitsArray) {
        List<String> pulsoList = new ArrayList<>();
        String previousBit = "";
        String sequencia = "";

        for (int i = 0; i < bitsArray.length; i++) {

            String currentBit = bitsArray[i];

            if (i == 0) {
                previousBit = currentBit;
                sequencia = currentBit;
                continue;
            }

            if (currentBit.equals(previousBit)) {
                sequencia = sequencia + currentBit;
                previousBit = currentBit;

                if (i < (bitsArray.length - 1)) {
                    continue;
                }

            } else {
                pulsoList.add(sequencia);
                sequencia = currentBit;
                previousBit = currentBit;
            }

            if ((bitsArray.length - 1) == i) {
                pulsoList.add(sequencia);
            }
        }

        return pulsoList;
    }

    public String transformMorse2Text(String morse) {
        String[] morseStr =  ".... --- .-.. .-   -- . .-.. ..".split("\\s{1}");

        String decoded = "";
        int countSpaceInSequence = 0;

        for (int i = 0 ; i < morseStr.length; i++) {

            if (morseStr[i].length() == 0) {
                countSpaceInSequence++;
                continue;
            } else {
                if (countSpaceInSequence > 0) {
                    decoded += " ";
                }
                countSpaceInSequence = 0;
            }

            decoded += morseMap.get(morseStr[i]);
        }

        return decoded;
    }

    public String transformText2Morse(String text) {
        String[] morseStr =  "HOLA MELI".split("");

        String decoded = "";

        for (String letter : morseStr) {
            if (letter.trim().length() == 0) {
                decoded += "  ";
                continue;
            }
            decoded += getMorseByLetter(letter) + " ";
        }

        return decoded;
    }

    private static String getLetterByMorse(String morseKey) {
        return morseMap.get(morseKey);
    }

    private static String getMorseByLetter(String letter) {
        Map<String, String> mapInversed =
                morseMap.entrySet()
                        .stream()
                        .collect(Collectors.toMap(Map.Entry::getValue, Map.Entry::getKey));
        return mapInversed.get(letter);
    }
}
