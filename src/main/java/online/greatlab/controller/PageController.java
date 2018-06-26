package online.greatlab.controller;

import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import jssc.SerialPortList;
import online.greatlab.MyStreamPumper;
import org.apache.commons.exec.*;
import org.apache.commons.exec.util.StringUtils;
import org.springframework.stereotype.Controller;

import java.io.*;

@Controller
public class PageController implements online.greatlab.controller.Controller {
    public Label helloWorld;
    public TextArea textArea;

    private Node node;

    @Override
    public Node getView() {
        return node;
    }

    @Override
    public void setView(Node node) {
        this.node = node;
    }

    @FXML
    public void sayHelloWorld(ActionEvent actionEvent) {
        Thread thread = new Thread(() -> getPorts());
        thread.setDaemon(true);
        thread.start();
    }

    private void getPorts() {
        Platform.runLater(() -> helloWorld.setText("Hello мир!"));
        StringBuilder sb = new StringBuilder();
        String[] portNames = SerialPortList.getPortNames();
        for (int i = 0; i < portNames.length; i++) {
            sb.append(portNames[i] + "\n");
        }

        Platform.runLater(() -> textArea.setFont(Font.font("Verdana", FontWeight.BOLD, 10)));
        Platform.runLater(() -> textArea.setText("Доступные порты:\n" + sb.toString()));

        String line = "ping ya.ru";
        CommandLine cmdLine = CommandLine.parse(line);
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        Executor executor = new DefaultExecutor();
        executor.setStreamHandler(new PumpStreamHandler() {
            @Override
            protected Thread createPump(InputStream is, OutputStream os, boolean closeWhenExhausted) {
                final Thread result = new Thread(new MyStreamPumper(is, s -> {
                    try {
                        String result1 = new String(s.getBytes("Windows-1251"), "IBM866");
                        Platform.runLater(() -> textArea.appendText(result1 + "\n"));
                        System.out.println(result1);
                    } catch (UnsupportedEncodingException e) {
                        e.printStackTrace();
                    }
                }));
                result.setName("MyStreamPumper Thread");
                result.setDaemon(true);
                return result;
            }
        });

        try {
            executor.execute(cmdLine);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
