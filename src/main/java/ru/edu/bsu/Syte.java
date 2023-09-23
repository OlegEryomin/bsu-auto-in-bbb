package ru.edu.bsu;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;

public abstract class Syte {
    WebDriver driver;

    public Syte() {
        this.driver = new ChromeDriver();
    }

     void writeValueInput(String idInput, String value){
        WebElement input = driver.findElement(By.id(idInput));

        new Actions(driver)
                .click(input)
                .sendKeys(value)
                .perform();
    }

     void buttonClick(String idButton){
        WebElement button = driver.findElement(By.id(idButton));

        new Actions(driver)
                .click(button)
                .perform();
    }
}
