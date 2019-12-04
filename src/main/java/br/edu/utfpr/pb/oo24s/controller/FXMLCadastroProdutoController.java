package br.edu.utfpr.pb.oo24s.controller;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import br.edu.utfpr.pb.oo24s.dao.ProdutoDao;
import br.edu.utfpr.pb.oo24s.model.ECategoria;
import br.edu.utfpr.pb.oo24s.model.EMotivo;
import br.edu.utfpr.pb.oo24s.model.Produto;
import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.nio.file.Files;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;
import javafx.animation.FadeTransition;
import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.VBox;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import javafx.util.Duration;
import javax.swing.JOptionPane;

/**
 * FXML Controller class
 *
 * @author Rafael Mello
 */
public class FXMLCadastroProdutoController implements Initializable {

    @FXML
    private TextField tfId;
    @FXML
    private TextField tfNome;
    @FXML
    private ComboBox cbCategoria;
    @FXML
    private TextArea taDescricao;
    @FXML
    private TextField tfValor;
    @FXML
    private VBox vboxPrincipal;
    @FXML
    private Button buttonCancelar;
    @FXML
    private Button buttonSalvar;
    
    @FXML
    private ImageView imageFoto;
    @FXML
    private Button buttonFoto;
    
    private Produto produto;
    private ProdutoDao produtoDao;
    private Stage dialogStage;
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        this.produtoDao = new ProdutoDao();
        this.produto = new Produto();
        
            List<String> categorias = new ArrayList<>();

            categorias.add("Produtos");
            categorias.add("Serviços");
            cbCategoria.setItems(FXCollections.observableArrayList(categorias));
            this.buttonCancelar.setOnAction(
                (t) -> {
                    loadVoltar();
                }
            );
            this.buttonFoto.setOnAction(
            (final ActionEvent e) -> {
                loadImage();
            });
    }
    
    public void setProduto(Produto produto) {
        this.produto = produto;
        if (produto.getId() != null) {
            tfId.setText(produto.getId().toString());
            tfNome.setText(produto.getNome());
            System.out.println(FXCollections.observableArrayList(produto.getCategoria().getId()).toString());
            cbCategoria.setItems(FXCollections.observableArrayList(produto.getCategoria().getId()));
            taDescricao.setText(produto.getDescricao());
            try {
                if (produto.getFoto() != null) {
                    Image image = new Image(
                            new ByteArrayInputStream(
                                    produto.getFoto())
                    );
                    imageFoto.setImage(image);
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    void setDialogStage(Stage dialogStage) {
        this.dialogStage = dialogStage;
    }
    
    private void loadImage() {
        try {
            final FileChooser fileChooser
                    = new FileChooser();
            File file = fileChooser
                    .showOpenDialog(dialogStage);
            if (file != null) {
                Image image = new Image(
                        file.toURI().toString());
                imageFoto.setImage(image);
                produto.setFoto(
                        Files.readAllBytes(file.toPath())
                );
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    
    @FXML
    private void cancel() {
        this.dialogStage.close();
    }

    @FXML
    private void save() {
            produto.setNome(tfNome.getText());
            produto.setValor(Double.parseDouble(tfValor.getText()));
            produto.setDescricao(taDescricao.getText());
            produto.setCategoria(ECategoria.findById(cbCategoria.getValue().toString()));
        if (this.produtoDao.isValid(produto)) {
            this.produtoDao.save(produto);
            JOptionPane.showMessageDialog(null, "Cadastrado com sucesso!");
            this.dialogStage.close();
            loadVoltar();
        } else {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Erro");
            alert.setHeaderText("Nenhum registro "
                    + "selecionado");
            alert.setContentText(
                    this.produtoDao.getErrors(produto)
            );
            alert.showAndWait();
        }
    }
    
    private void loadVoltar() {
        try {

            setDataPane(fadeAnimate("/fxml/FXMLProdutoLista.fxml"));
        } catch (Exception e) {
            e.printStackTrace();
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle(" .: JavaFX :. ");
            alert.setHeaderText("Atenção, ocorreu um erro!");
            alert.setContentText("Falha ao abrir a tela de checkin.");
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
