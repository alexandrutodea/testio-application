package me.alextodea.testioapplication.plagiarism;

import java.util.Comparator;

public class StringSizeComparator implements Comparator<String>  {

    @Override
    public int compare(String s1, String s2) {
        return s1.length() - s2.length();
    }
}
