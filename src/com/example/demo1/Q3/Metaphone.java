package com.example.demo1.Q3;

public class Metaphone {
    private static final String VOWELS = "AEIOU";
    private static final String FRONTV = "EIY";
    private static final String VARSON = "CSPTG";

    public static String metaphone(String name) {
        StringBuilder metaph = new StringBuilder();
        StringBuilder ename = new StringBuilder();
        String two;
        int L = 0;

        for (int i = 0; i < name.length(); i++) {
            char c = Character.toUpperCase(name.charAt(i));
            if (Character.isLetterOrDigit(c)) {
                ename.append(c);
            }
        }

        if (ename.length() == 0) {
            return "";
        }

        two = ename.length() >= 2 ? ename.substring(0, 2) : ename.toString();

        if (two.equals("PN") || two.equals("AE") || two.equals("KN") || two.equals("GN") ||
                two.equals("WR") || two.equals("AE")) {
            ename.deleteCharAt(0);
        }

        if (ename.length() > 0 && ename.charAt(0) == 'X') {
            ename.setCharAt(0, 'S');
        }

        if (ename.length() >= 2 && ename.substring(0, 2).equals("WH")) {
            ename.setCharAt(0, 'W');
            ename.deleteCharAt(1);
        }

        L = ename.length();

        for (int i = 0; i < L && metaph.length() < 4; i++) {
            char symb = ename.charAt(i);
            boolean newChar;

            if (symb != 'C' && i > 0 && ename.charAt(i-1) == symb) {
                newChar = false;
            } else {
                newChar = true;
            }

            if (newChar) {
                if (VOWELS.indexOf(symb) != -1 && i == 0) {
                    metaph.append(symb);
                } else if (symb == 'B') {
                    if (i == L - 1 && ename.charAt(i-1) == 'M') {
                        // silent
                    } else {
                        metaph.append(symb);
                    }
                } else if (symb == 'C') {
                    if (i > 0 && ename.charAt(i-1) == 'S' && i+1 < L && FRONTV.indexOf(ename.charAt(i+1)) != -1) {
                        // skip
                    } else if (i+2 < L && ename.charAt(i+1) == 'I' && ename.charAt(i+2) == 'A') {
                        metaph.append('X');
                    } else if (i < L-1 && FRONTV.indexOf(ename.charAt(i+1)) != -1) {
                        metaph.append('S');
                    } else if (i > 0 && i < L-1 && ename.charAt(i+1) == 'H' && ename.charAt(i-1) == 'S') {
                        metaph.append('K');
                    } else if (i < L-1 && ename.charAt(i+1) == 'H') {
                        if (i == 0 && i+2 < L && VOWELS.indexOf(ename.charAt(i+2)) == -1) {
                            metaph.append('K');
                        } else {
                            metaph.append('X');
                        }
                    } else {
                        metaph.append('K');
                    }
                } else if (symb == 'D') {
                    if (i == L-1 && (i > 1 && VARSON.indexOf(ename.charAt(i-2)) != -1 && ename.charAt(i-1) == 'G' || i > 2 && ename.substring(i-2, i).equals("GH") || i > 0 && ename.charAt(i-1) == 'G' && (FRONTV.indexOf(symb) != -1 || i == L-1))) {
                    // silent
                    } else if (i > 0 && ename.charAt(i-1) != 'G') {
                        metaph.append('J');
                    }
                } else if (symb == 'G') {
                    if ((i > 0 && (ename.charAt(i-1) == 'H' || (ename.charAt(i-1) == 'N' && i+1 < L && VOWELS.indexOf(ename.charAt(i+1)) == -1))) || (i == L-1 && ename.charAt(i-1) == 'N')) {
                    // silent
                    } else if (i > 0 && ename.charAt(i-1) == 'G') {
                        // silent
                    } else {
                        metaph.append('K');
                    }
                } else if (symb == 'H') {
                    if (i == 0 || VOWELS.indexOf(ename.charAt(i-1)) == -1) {
                    // silent
                } else {
                    metaph.append('H');
                }
                } else if (symb == 'K') {
                    if (i == 0 || ename.charAt(i-1) != 'C') {
                        metaph.append('K');
                    }
                } else if (symb == 'P') {
                    if (i+1 < L && ename.charAt(i+1) == 'H') {
                        metaph.append('F');
                    } else {
                        metaph.append('P');
                    }
                } else if (symb == 'Q') {
                    metaph.append('K');
                } else if (symb == 'S') {
                    if (i > 1 && ename.substring(i-2, i).equals("IS") && (i+1 == L || ename.charAt(i+1) == 'M' || ename.charAt(i+1) == 'N' || ename.charAt(i+1) == 'L' || ename.charAt(i+1) == 'W')) {
                        // silent
                    } else if (i+1 < L && ename.substring(i, i+2).equals("SH")) {
                        metaph.append('X');
                    } else if (i < L-1 && ename.substring(i, i+2).equals("SC")) {
                        if (i+2 < L && "HAEIOUY".indexOf(ename.charAt(i+2)) != -1) {
                            metaph.append('S');
                        } else {
                            metaph.append('X');
                        }
                    } else if (i < L-1 && ename.substring(i, i+2).equals("SCH")) {
                        metaph.append('X');
                    } else {
                        metaph.append('S');
                    }
                } else if (symb == 'T') {
                    if (i < L-1 && ename.substring(i, i+2).equals("TH")) {
                        metaph.append('0');
                    } else if (i+1 < L && ename.substring(i, i+2).equals("TI")) {
                        if (i+2 < L && ename.charAt(i+2) != 'O' && ename.charAt(i+2) != 'A') {
                            metaph.append('J');
                        } else {
                            metaph.append('X');
                        }
                    } else if (i+2 < L && ename.substring(i, i+3).equals("TCH")) {
                        metaph.append('C');
                        metaph.append('H');
                    } else {
                        metaph.append('T');
                    }
                } else if (symb == 'V') {
                    metaph.append('F');
                } else if (symb == 'W' || symb == 'Y') {
                    if (i < L-1 && VOWELS.indexOf(ename.charAt(i+1)) != -1) {
                        metaph.append(symb);
                    }
                } else if (symb == 'X') {
                    metaph.append('K');
                    metaph.append('S');
                } else if (symb == 'Z') {
                    metaph.append('S');
                }
            }

        }
        return metaph.toString();
    }

}