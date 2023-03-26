package by.ITacademy.bootcamp;

public class Test {
    public static void main(String[] args) {
        String str= "MATTd@MAIL.RU";

        boolean result = str.matches("^[a-zA-Z0-9+_.-]+@[a-zA-Z0-9.-]+$");
        System.out.println(result);
    }

}
