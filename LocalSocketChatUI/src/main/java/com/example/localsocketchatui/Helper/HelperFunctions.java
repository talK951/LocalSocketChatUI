package com.example.localsocketchatui.Helper;

import com.example.localsocketchatui.UserSocketSingleton;
import javafx.application.Platform;
import javafx.scene.control.ListView;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.UnknownHostException;

public class HelperFunctions extends Thread{

    ListView<String> list;
    public HelperFunctions(ListView<String> list){
        this.list = list;
    }
    @Override
    public void run(){

        String line;

        try {
            UserSocketSingleton userSocket = UserSocketSingleton.getInstance();
            BufferedReader in = new BufferedReader(
                    new InputStreamReader(userSocket.GetSocket().getInputStream()));


            while(true){

                if( (line = in.readLine()) != null)
                {
                    String finalLine = line;

                    System.out.println(line);



                    Platform.runLater(() -> {

                        if(finalLine.charAt(0) == 'u') {
                            list.getItems().clear();

                            String[] arrayTemp = finalLine.split("/");
                            System.out.println(arrayTemp);

                            for (int i = 1; i < arrayTemp.length; i++) {

                                list.getItems().add(arrayTemp[i]);
                            }
                        }

                        else if(finalLine.charAt(0) == 'r'){
                            list.getItems().remove(Integer.parseInt(finalLine.substring(1)));
                        }

                        else if(finalLine.charAt(0) == 'm'){
                            System.out.println(finalLine);
                        }
                    });

                }





            }


        } catch (UnknownHostException e) {
            throw new RuntimeException(e);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }


    }
}
