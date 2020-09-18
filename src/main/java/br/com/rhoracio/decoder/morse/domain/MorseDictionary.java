package br.com.rhoracio.decoder.morse.domain;

import java.util.HashMap;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * Tabela de código morse com o respectivo letra do alfabeto
 * @author Rafael Horácio
 */
public final class MorseDictionary {

    private final static Map<String, String> morseHashMap = new HashMap<>();

    private MorseDictionary() {}

    static {
        morseHashMap.put(".-",   "A");
        morseHashMap.put("-...", "B");
        morseHashMap.put("-.-.", "C");
        morseHashMap.put("-..",  "D");
        morseHashMap.put(".",    "E");
        morseHashMap.put("..-.", "F");
        morseHashMap.put("--.",  "G");
        morseHashMap.put("....", "H");
        morseHashMap.put("..",   "I");
        morseHashMap.put(".---", "J");
        morseHashMap.put("-.-",  "K");
        morseHashMap.put(".-..", "L");
        morseHashMap.put("--",   "M");
        morseHashMap.put("-.",   "N");
        morseHashMap.put("---",  "O");
        morseHashMap.put(".--.", "P");
        morseHashMap.put("--.-", "Q");
        morseHashMap.put(".-.",  "R");
        morseHashMap.put("...",  "S");
        morseHashMap.put("-",    "T");
        morseHashMap.put("..-",  "U");
        morseHashMap.put("...-", "V");
        morseHashMap.put(".--",  "W");
        morseHashMap.put("-..-", "X");
        morseHashMap.put("-.--", "Y");
        morseHashMap.put("--..", "Z");
        morseHashMap.put(".----","1");
        morseHashMap.put("..---","2");
        morseHashMap.put("...--","3");
        morseHashMap.put("....-","4");
        morseHashMap.put(".....","5");
        morseHashMap.put("-....","6");
        morseHashMap.put("--...","7");
        morseHashMap.put("---..","8");
        morseHashMap.put("----.","9");
        morseHashMap.put("-----","0");
    }

    public static String getLetterByMorseKey(final String morseKey) {
        return morseHashMap.get(morseKey);
    }

    /**
     * Inverte o hashmap para retornar o código morse.
     * Apesar de ter menos performance, do que criar um novo hashmap, garantimos que o hashmap sempre será centralizado
     * para facilitar adições de novos valores e não perdemos a sincronia com 2 hashmaps
     *
     * @param letter Letra do alfabeto
     * @return Código Morse de acordo a Letra correspondente
     */
    public static String getMorseByTextKey(final String letter) {
                return morseHashMap.entrySet()
                        .stream()
                        .collect(Collectors.toMap(Map.Entry::getValue, Map.Entry::getKey))
                        .get(letter);
    }

}
