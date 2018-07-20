package org.amathon.ksr.application;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

public class Utility {

  public byte[] displayText(InputStream input) throws IOException {
    // Read one text line at a time and display.
    try {

      BufferedReader reader = new BufferedReader(new InputStreamReader(input));

      String lines = "";
      while (true) {

        String line = reader.readLine();
        lines += line;

        if (line == null) {
          break;
        }
        //System.out.println("    " + line);
      }

      return lines.getBytes();
    } catch (Exception e) {
      e.printStackTrace();
      return null;
    }
  }
}
