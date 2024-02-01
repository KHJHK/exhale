package com.ssafy.exhale.util;

public class CertificationNumber {
    public static String getCertificationNumber(){
        String certificationNumber = "";
        for(int cnt = 0; cnt<6; cnt++){
            certificationNumber += (int) (Math.random()*10);
        }
        return certificationNumber;
    }
}
