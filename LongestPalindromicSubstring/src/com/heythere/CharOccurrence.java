package com.heythere;

public class CharOccurrence {
    private char aChar;
    private int charOccurrenceCount;

    public CharOccurrence(){
        charOccurrenceCount=0;
    }

    public char getAChar(){
        return aChar;
    }

    public void setAChar(char aChar){
        this.aChar=aChar;
    }

    public int getCharCount(){
        return charOccurrenceCount;
    }

    public void setCharCount(int count){
        this.charOccurrenceCount=count;
    }
}
