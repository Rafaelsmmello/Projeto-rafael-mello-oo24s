package br.edu.utfpr.pb.oo24s.controller;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.animation.FadeTransition;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.layout.VBox;
import javafx.util.Duration;

/**
 * FXML Controller class
 *
 * @author Rafael Mello
 */
public class FXMLPrincipalController implements Initializable {
    
    @FXML
    private Button buttonCheckin;
    @FXML
    private Button buttonCheckout;
    @FXML
    private Button buttonHospedes;
    @FXML
    private Button buttonProdutos;
    @FXML
    private Button buttonUsuarios;
    @FXML
    private Button buttonRelatorio;
    @FXML
    private VBox vboxPrincipal;
    
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        this.buttonCheckin.setOnAction(
            (t) -> {
                loadCheckin();
            }
        );
    }

    private void loadCheckin() {
        try {
            setDataPane(fadeAnimate("/fxml/FXMLReserva.fxml") );
        } catch (Exception e) {
            e.printStackTrace();
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle(" .: JavaFX :. ");
            alert.setHeaderText("Atenção, ocorreu um erro!");
            alert.setContentText("Falha ao abrir a tela de categorias.");
            alert.showAndWait();
        }
    }
    
    private void setDataPane(Node node) {
        vboxPrincipal.getChildren().setAll(node);
    }

    private VBox fadeAnimate(String url) throws IOException {
        VBox v = (VBox) FXMLLoader.load(this.getClass().getResource(url));
        FadeTransition ft = new FadeTransition(Duration.millis(1500));
        ft.setNode(v);
        ft.setFromValue(0.1);
        ft.setToValue(1);
        ft.setCycleCount(1);
        ft.setAutoReverse(false);
        ft.play();
        return v;
    }
    
}
