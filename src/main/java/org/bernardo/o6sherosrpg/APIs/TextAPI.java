package org.bernardo.o6sherosrpg.APIs;

import java.text.Normalizer;

public class TextAPI {


    private static String removerAcentos(String texto) {
        return Normalizer.normalize(texto, Normalizer.Form.NFD)
                .replaceAll("\\p{InCombiningDiacriticalMarks}", "");
                // .replaceAll("[\\p{InCombiningDiacriticalMarks}]", "");
    }

}
