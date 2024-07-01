package org.example.interfaces;

import java.io.IOException;

public interface Conversor {
    String converter(String de, String para) throws IOException, InterruptedException;
}
