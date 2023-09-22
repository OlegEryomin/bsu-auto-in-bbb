package ru.edu.bsu;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;

public class Moodle {
    private String url;
    private String username;
    private String password;

    private WebDriver driver;

    public Moodle(String url, String username, String password) {
        this.url = url;
        this.username = username;
        this.password = password;
        this.driver = new ChromeDriver();
        login();
    }

    private void login(){

        driver.get(this.url + "/login/index.php");

        writeValueInput("username", this.username);
        writeValueInput("password", this.password);
        buttonClick("loginbtn");
    }

    protected void connectBigBlueButton(int id, boolean isMicrophone){
        driver.get(url + "/mod/bigbluebuttonbn/view.php?id=" + id);
        buttonClick("join_button_input");

    }
    private void writeValueInput(String idInput, String value){
        WebElement input = driver.findElement(By.id(idInput));

        new Actions(driver)
                .click(input)
                .perform();

        new Actions(driver)
                .sendKeys(value)
                .perform();
    }

    private void buttonClick(String idButton){
        WebElement button = driver.findElement(By.id(idButton));

        new Actions(driver)
                .click(button)
                .perform();
    }

}
