package com.phantom.util;

public class UIDExtractor {
    public static Long appUserIdExtractor(String appUserMessage){
        int lastSpace = -1;
        for(int i=0;i<appUserMessage.length();i++){
            if(appUserMessage.charAt(i) == ' '){
                lastSpace = i;
            }
        }
        return Long.valueOf(appUserMessage.substring(lastSpace+1));
    }
}
