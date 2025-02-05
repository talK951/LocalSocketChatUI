package com.example.localsocketchatui;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.Objects;


public class ChatApplicationUI extends Application {

    public static Stage stage;
    @Override
    public void start(Stage stage) throws IOException {

        ChatApplicationUI.stage = stage;
        FXMLLoader fxmlLoader = new FXMLLoader(ChatApplicationUI.class.getResource("UserScreen.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 500, 500);
        stage.setTitle("ChatApplication");
        stage.setScene(scene);
        stage.show();
        UserSocketSingleton.getInstance().StartUpUser();
    }

    public static Stage GetStage(){
        return stage;
    }
    public static void main(String[] args) {
        launch();
    }
}