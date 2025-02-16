package com.example.localsocketchatui;

import com.example.localsocketchatui.Helper.HelperFunctions;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.event.EventType;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import javafx.stage.WindowEvent;

import java.io.*;
import java.net.InetAddress;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

import static com.example.localsocketchatui.ChatApplicationUI.GetStage;
import static javafx.stage.WindowEvent.WINDOW_CLOSE_REQUEST;

public class UserScreen implements Initializable {

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
    private Label selectionCheck;


    String currentChannel;

    String deskTopName = InetAddress.getLocalHost().getHostName();
    @FXML
    public ListView<String> channelList;

    PrintWriter out;

    Stage stage = GetStage();

    UserSocketSingleton userSocket = UserSocketSingleton.getInstance();

    public UserScreen() throws IOException {
    }


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        try {
              out = new PrintWriter(userSocket.GetSocket().getOutputStream(),true);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        ClosingWindow(stage);
        HelperFunctions helper = new HelperFunctions(channelList);
        channelList.getSelectionModel().selectedItemProperty().addListener(new ChangeListener<String>() {
            @Override
            public void changed(ObservableValue<? extends String> observableValue, String s, String t1) {


                String tag =  "s";
                currentChannel = channelList.getSelectionModel().getSelectedItem();
                selectionCheck.setText(currentChannel);


                System.out.println(tag + "/" + currentChannel);
                out.println(tag + "/" + currentChannel);
                out.flush();


            }
        });
        helper.start();
    }


    public void ClosingWindow(Stage stage){


        stage.addEventHandler(WINDOW_CLOSE_REQUEST, new EventHandler<WindowEvent>() {

            @Override
            public void handle(WindowEvent windowEvent) {
                out.println("c" + deskTopName);
                out.flush();


            }
        });

    }

    @FXML
    void OnClickAddChannel(ActionEvent event) {
        channelList.getItems().add(channelName.getText());
    }

    @FXML
    void OnClickDeleteChannel(ActionEvent event) {
        channelList.getItems().remove(channelName.getText());
    }

    @FXML
    void OnSendMessageButton(ActionEvent event) throws IOException, InterruptedException {

        String tag =  "m";

         // Reading from user
        String message = inputTextField.getText();
        Label userMsg = new Label(message);

        System.out.println(tag + "/" + currentChannel + "/" + deskTopName + "/" + message + " :HardCoded");
        out.println( tag + "/" + currentChannel + "/" + deskTopName + "/" + message);
        out.flush();


        userMsg.setMaxWidth(Double.MAX_VALUE);
        userMsg.setAlignment(Pos.CENTER_RIGHT);
        chatContainer.getChildren().add(userMsg);

    }


}
