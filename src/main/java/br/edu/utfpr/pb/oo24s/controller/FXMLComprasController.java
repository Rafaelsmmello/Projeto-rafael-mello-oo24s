package br.edu.utfpr.pb.oo24s.controller;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.scene.layout.VBox;

public class FXMLComprasController implements Initializable {

    @FXML
    private Button buttoncancelar;
    @FXML
    private Button buttoncomprar;
    @FXML
    private VBox vboxprincipal;
    @FXML
    public DatePicker cbdata;
    @FXML
    private ComboBox cbcliente;
    @FXML
    private ComboBox cbproduto;
    @FXML
    private TextField tfquantidade;
    @FXML
    private TextField tfid;
    @FXML
    private TextField tfcategoria;
    @FXML
    private TextField tfestoque;
    @FXML
    private TextField tfdescricao;
    @FXML
    private ImageView imageview;
    
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        this.buttoncancelar.setOnAction(
                (t) -> {
                    loadVoltar();
                }
        );
    }

private void loadVoltar() {
        try {
            setDataPane();
        } catch (Exception e) {
            e.printStackTrace();
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle(" .: JavaFX :. ");
            alert.setHeaderText("Atenção, ocorreu um erro!");
            alert.setContentText("Falha ao abrir a tela de checkin.");
            alert.showAndWait();
        }
    }    
    
    private void setDataPane() {
        vboxprincipal.getChildren().setAll();
    }

}
