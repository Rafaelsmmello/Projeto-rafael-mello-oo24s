package br.edu.utfpr.pb.oo24s.controller;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import br.edu.utfpr.pb.oo24s.dao.ClienteDao;
import br.edu.utfpr.pb.oo24s.model.Cliente;
import br.edu.utfpr.pb.oo24s.model.Produto;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author Rafael Mello
 */
public class FXMLCadastroClienteController implements Initializable {
    @FXML
    private TextField tfId;
    @FXML
    private TextField tfNome;
    @FXML
    private TextField tfTelefone;
    @FXML
    private TextField tfEmail;
    @FXML
    private TextField tfCpf;
    @FXML
    private TextField tfRg;
    @FXML
    private TextField tfPassaporte;
    @FXML
    private TextArea taEndereco;
    @FXML
    private Button buttonSalvar;
    @FXML
    private Button buttonCancelar;
    
    private Cliente cliente;
    private ClienteDao clienteDao;
    private Stage dialogStage;
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        this.clienteDao = new ClienteDao();
        this.cliente = new Cliente();
    }    
    
    public void setCliente(Cliente cliente) {
        this.cliente = cliente;
        if (cliente.getId() != null) {
            tfId.setText(cliente.getId().toString());
            tfNome.setText(cliente.getNome());
            tfCpf.setText(cliente.getCpf());
            tfEmail.setText(cliente.getEmail());
            tfPassaporte.setText(cliente.getPassaporte());
            taEndereco.setText(cliente.getEndereco());
            tfRg.setText(cliente.getRg());
            tfTelefone.setText(cliente.getTelefone().toString());
            
        }
    }

    void setDialogStage(Stage dialogStage) {
        this.dialogStage = dialogStage;
    }
    
    
    @FXML
    private void cancel() {
        this.dialogStage.close();
    }

    @FXML
    private void save() {
        
            cliente.setNome(tfNome.getText());
            cliente.setCpf(tfCpf.getText());
            cliente.setEmail(tfEmail.getText());
            cliente.setPassaporte(tfPassaporte.getText());
            cliente.setRg(tfRg.getText());
            cliente.setTelefone(Long.parseLong(tfTelefone.getText()));
        if (this.clienteDao.isValid(cliente)) {
            this.clienteDao.save(cliente);
            this.dialogStage.close();
        } else {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Erro");
            alert.setHeaderText("Nenhum registro "
                    + "selecionado");
            alert.setContentText(
                    this.clienteDao.getErrors(cliente)
            );
            alert.showAndWait();
        }
    }
    
}
