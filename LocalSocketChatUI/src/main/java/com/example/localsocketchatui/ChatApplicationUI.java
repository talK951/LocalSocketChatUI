package com.example.localsocketchatui;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.Objects;


public class ChatApplicationUI extends Application {


    @Override
    public void start(Stage stage) throws IOException {

        FXMLLoader fxmlLoader = new FXMLLoader(ChatApplicationUI.class.getResource("UserScreen.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 500, 500);
        stage.setTitle("ChatApplication");
        stage.setScene(scene);
        stage.show();
        UserSocketSingleton.getInstance().StartUpUser();
    }

    public static void main(String[] args) {
        launch();
    }
}