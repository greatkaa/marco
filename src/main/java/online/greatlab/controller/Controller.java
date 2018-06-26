package online.greatlab.controller;

import javafx.scene.Node;

/**
 * @author a.kotov
 * @since 25.06.2018
 */
public interface Controller {
    Node getView();
    void setView(Node node);
}
