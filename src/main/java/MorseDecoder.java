import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.OptionalInt;
import java.util.stream.Stream;

public class MorseDecoder {

    public static Map<String, String> morseMap = new HashMap<>();

    static {
        morseMap.put("A",".-");
        morseMap.put("B","-...");
        morseMap.put("C","-.-.");
        morseMap.put("D","-..");
        morseMap.put("E",".");
        morseMap.put("F","..-.");
        morseMap.put("G","--.");
        morseMap.put("H","....");
        morseMap.put("I","..");
        morseMap.put("J",".---");
        morseMap.put("K","-.-");
        morseMap.put("L",".-..");
        morseMap.put("M","--");
        morseMap.put("N","-.");
        morseMap.put("O","---");
        morseMap.put("P",".--.");
        morseMap.put("Q","--.-");
        morseMap.put("R",".-.");
        morseMap.put("S","...");
        morseMap.put("T","-");
        morseMap.put("U","..-");
        morseMap.put("V","...-");
        morseMap.put("W",".--");
        morseMap.put("X","-..-");
        morseMap.put("Y","-.--");
        morseMap.put("Z","--..");
        morseMap.put("1",".----");
        morseMap.put("2","..---");
        morseMap.put("3","...--");
        morseMap.put("4","....-");
        morseMap.put("5",".....");
        morseMap.put("6","-....");
        morseMap.put("7","--...");
        morseMap.put("8","---..");
        morseMap.put("9","----.");
        morseMap.put("0","-----");
        morseMap.put(" "," ");
    }

    public static void main(String[] args) {
        final String MORSE_STREAM = "00000000110110110011100000111111000111111001111110000000"; //H
//        final String MORSE_STREAM = "101101"; //H
//        final String MORSE_STREAM = "110"; //H
        MorseDecoder m = new MorseDecoder();
        m.decodeBitsToMorse(MORSE_STREAM);
    }

    public String decodeBitsToMorse(String bitStream) {

        String[] bitsArray = bitStream.split("");
        int streamLength = bitStream.length();

        char dash = '-';
        char dot = '.';
        char longSpace = ' ';

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


        List<String> pulsos = new ArrayList<>();
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
                continue;
            } else {
                pulsos.add(sequencia);
                sequencia = currentBit;
                previousBit = currentBit;
            }

            if ((bitsArray.length - 1) == i) {
                pulsos.add(sequencia);
            }

        }

        pulsos.stream().forEach(System.out::println);

        return null;
    }
}
