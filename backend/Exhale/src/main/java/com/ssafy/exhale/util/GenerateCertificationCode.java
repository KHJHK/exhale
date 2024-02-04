package com.ssafy.exhale.util;

import java.security.SecureRandom;
import java.util.Random;

public class GenerateCertificationCode {
    public static String getCertificationNumber(){
        String certificationNumber = "";
        for(int cnt = 0; cnt<6; cnt++){
            certificationNumber += (int) (Math.random()*10);
        }
        return certificationNumber;
    }

    public static String getRandomPassword() {
        int length = 10;
        String CHARACTERS = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789!@#$%^&*()-_=+";


        Random random = new SecureRandom();
        StringBuilder password = new StringBuilder();

        for (int i = 0; i < length; i++) {
            int randomIndex = random.nextInt(CHARACTERS.length());
            password.append(CHARACTERS.charAt(randomIndex));
        }

        return password.toString();
    }
}
