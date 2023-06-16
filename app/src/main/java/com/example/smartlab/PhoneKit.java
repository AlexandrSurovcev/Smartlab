package com.example.smartlab;

import android.text.Editable;

public class PhoneKit {
    StringBuilder sb = new StringBuilder();
    private final char numPlace = 'X';
    boolean ignore;
    public void phoneKit( Editable editable){
        if(!ignore){
            removeFormat(editable.toString());
            applyFormat(sb.toString());
            ignore=true;
            editable.replace(0,editable.length(),sb.toString());
            ignore = false;
        }
    }

    //ШАБЛОНЫ ДЛЯ НОМЕРА ТЕЛЕФОНА
    private String getTemplate(String text){
        return "+X (XXX) XXX-XX-XX";
    }
    private boolean isNumberChar(char c){return c>='0'&&c<='9';}
    private void removeFormat(String text){
        sb.setLength(0);
        for(int i =0;i<text.length();i++){
            char c = text.charAt(i);
            if(isNumberChar(c)){
                sb.append(c);
            }
        }
    }
    private void applyFormat(String text){
        String template =getTemplate(text);
        sb.setLength(0);
        for(int i =0,textIndex=0;i<template.length()&&textIndex<text.length();i++){
            if(template.charAt(i)==numPlace){
                sb.append(text.charAt(textIndex));
                textIndex++;
            }else{
                sb.append(template.charAt(i));
            }
        }
    }

}
