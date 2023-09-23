package ru.edu.bsu;

public class Moodle extends Syte{
    private String url;
    private String username;
    private String password;

    public Moodle(String url, String username, String password) {
        this.url = url;
        this.username = username;
        this.password = password;
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


}
