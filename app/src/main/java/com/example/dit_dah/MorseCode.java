package com.example.dit_dah;

import java.util.HashMap;

public class MorseCode {

    static String[] PLAIN = { "a", "b", "c", "d", "e", "f", "g", "h", "i", "j", "k", "l", "m", "n", "o", "p", "q", "r",
            "s", "t", "u", "v", "w", "x", "y", "z", "1", "2", "3", "4", "5", "6", "7", "8", "9", "0", "!", ",", "?",
            ".", "'", "/", "@" };

    static String[] MORSE = { ".-", "-...", "-.-.", "-..", ".", "..-.", "--.", "....", "..", ".---", "-.-", ".-..",
            "--", "-.", "---", ".--.", "--.-", ".-.", "...", "-", "..-", "...-", ".--", "-..-", "-.--", "--..", ".----",
            "..---", "...--", "....-", ".....", "-....", "--...", "---..", "----.", "-----", "-.-.--", "--..--",
            "..--..", ".-.-.-", ".----.", "-..-.", ".--.-." };

    public static HashMap<String, String> PLAIN_TO_MORSE = new HashMap<>();
    public static HashMap<String, String> MORSE_TO_PLAIN = new HashMap<>();

    static {
        for (int i = 0; i < PLAIN.length  &&  i < MORSE.length; i++) {
            PLAIN_TO_MORSE.put(PLAIN[i], MORSE[i]);
            MORSE_TO_PLAIN.put(MORSE[i], PLAIN[i]);
        }
    }

    public static String morseToPlain(String morseCode) {
        StringBuilder builder = new StringBuilder();
        String[] words = morseCode.trim().split("   ");

        for (String word : words) {
            for (String letter : word.split(" ")) {
                String plain = MORSE_TO_PLAIN.get(letter);
                builder.append(plain);
            }
            builder.append(" ");
        }
        return builder.toString().toUpperCase();
    }

    public static String plainToMorse(String plainAlpha) {
        StringBuilder builder = new StringBuilder();
        String[] words = plainAlpha.trim().split(" ");

        for (String word : words) {
            for (int i = 0; i < word.length(); i++) {
                String morse = PLAIN_TO_MORSE.get(word.substring(i, i + 1).toLowerCase());
                builder.append(morse).append(" ");
            }
            builder.append("  ");
        }
        return builder.toString();
    }
}
