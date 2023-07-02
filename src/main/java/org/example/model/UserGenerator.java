package org.example.model;
import java.util.Random;

public class UserGenerator {
    public static User getDefault(){
        String[] login = new String[]{"5353@yandex.com", "53535@mail.com", "35@google.com"};
        String[] password = new String[]{"hi111", "111", "111"};
        String[] firstName = new String[]{"Masha1", "111", "111"};
        int randomLogin = new Random().nextInt(login.length);
        int randomPassword = new Random().nextInt(password.length);
        int randomFirstName = new Random().nextInt(firstName.length);
        return new User( login[randomLogin], password[randomPassword], firstName[randomFirstName]);
    }
}
