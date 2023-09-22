package ru.edu.bsu;


public class Main {
    public static void main(String[] args) {
        System.out.println("Application start...");

        Moodle moodle = new Moodle(
                "https://pegas.bsu.edu.ru",
                "1227123",
                "***"
        );
        moodle.connectBigBlueButton(1604232, true);

    }
}