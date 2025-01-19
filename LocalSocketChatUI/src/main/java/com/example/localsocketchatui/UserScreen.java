package com.example.localsocketchatui;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.layout.VBox;

public class UserScreen {

    @FXML
    private Button addButton;

    @FXML
    private TextField channelName;

    @FXML
    private VBox chatContainer;

    @FXML
    private VBox chatdemo;

    @FXML
    private Button deleteButton;

    @FXML
    private TextArea inputTextField;

    @FXML
    private Label reciverId;

    @FXML
    private Label reciverId1;

    @FXML
    private Label reciverId11;

    @FXML
    private Button sendButton;
    @FXML
    private ListView<String> channelList;


    @FXML
    void OnClickAddChannel(ActionEvent event) {
        channelList.getItems().add(channelName.getText());
    }

    @FXML
    void OnClickDeleteChannel(ActionEvent event) {
        channelList.getItems().remove(channelName.getText());
    }

    @FXML
    void OnSendMessageButton(ActionEvent event) {
        String message = inputTextField.getText();
        Label userMsg = new Label(message);
        userMsg.setMaxWidth(Double.MAX_VALUE);
        userMsg.setAlignment(Pos.CENTER_RIGHT);
        chatContainer.getChildren().add(userMsg);
    }

}
