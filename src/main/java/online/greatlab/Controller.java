package online.greatlab;

import javafx.event.ActionEvent;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import jssc.SerialPortList;

public class Controller {
    public Label helloWorld;
    public TextArea textArea;

    public void sayHelloWorld(ActionEvent actionEvent) {
        helloWorld.setText("Hello мир!");
        StringBuilder sb = new StringBuilder();
        String[] portNames = SerialPortList.getPortNames();
        for(int i = 0; i < portNames.length; i++){
            sb.append(portNames[i] + "\n");
        }
        System.out.println("ЫФвавыа");

        textArea.setFont(Font.font("Verdana", FontWeight.BOLD, 10));
        textArea.setText("Доступные порты:\n" + sb.toString());
    }

}
