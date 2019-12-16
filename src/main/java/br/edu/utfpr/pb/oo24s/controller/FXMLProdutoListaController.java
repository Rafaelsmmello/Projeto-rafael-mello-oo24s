package br.edu.utfpr.pb.oo24s.controller;

import br.edu.utfpr.pb.oo24s.dao.ProdutoDao;
import br.edu.utfpr.pb.oo24s.model.Produto;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.animation.FadeTransition;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.SelectionMode;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.util.Duration;

public class FXMLProdutoListaController implements Initializable {

    @FXML
    private TableView<Produto> tableData;
    @FXML
    private TableColumn<Produto, Long> columnId;
    @FXML
    private TableColumn<Produto, String> columnNome;
    @FXML
    private TableColumn<Produto, String> columnCategoria;
    @FXML
    private TableColumn<Produto, Double> columnValor;
    @FXML
    private Button buttonEdit;
    @FXML
    private Button buttonNovo;
    @FXML
    private Button buttonRemover;
    @FXML
    private VBox vboxPrincipal;

    private ProdutoDao produtoDao;
    private ObservableList<Produto> list
            = FXCollections.observableArrayList();

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        this.produtoDao = new ProdutoDao();
        this.tableData.getSelectionModel()
                .setSelectionMode(
                        SelectionMode.SINGLE);
        setColumnProperties();
        loadData();
        
        this.buttonNovo.setOnAction(
            (t) -> {
                loadProduto();
            }
        );
    }

    private void setColumnProperties() {
        this.columnId.setCellValueFactory(
                new PropertyValueFactory<>("idproduto")
        );
        this.columnNome.setCellValueFactory(
                new PropertyValueFactory<>("descricao")
        );
        this.columnCategoria.setCellValueFactory(
                new PropertyValueFactory<>("categoria")
        );
        this.columnValor.setCellValueFactory(
                new PropertyValueFactory<>("valor")
        );
    }

    private void loadData() {
        this.list.clear();
        this.list.addAll(this.produtoDao.getAll());
        tableData.setItems(list);
    }

    private void openForm(Produto produto,
            ActionEvent event) {
        try {
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(
                    this.getClass()
                            .getResource("/fxml/FXMLCadastroProduto.fxml"));
            AnchorPane pane = (AnchorPane) loader.load();

            Stage dialogStage = new Stage();
            dialogStage.setTitle("Cadastro de Produto");
            dialogStage.initModality(Modality.WINDOW_MODAL);
            dialogStage.initOwner(
                    ((Node) buttonEdit)
                            .getScene().getWindow());
            Scene scene = new Scene(pane);
            dialogStage.setScene(scene);

            FXMLCadastroProdutoController controller = loader.getController();
            controller.setProduto(produto);
            controller.setDialogStage(dialogStage);
            dialogStage.showAndWait();

        } catch (Exception e) {
            e.printStackTrace();
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Erro");
            alert.setHeaderText("Ocorreu um erro ao abrir "
                    + "a janela de cadastro!");
            alert.setContentText("Por favor, tente realizar "
                    + "a operação novamente!");
            alert.showAndWait();
        }
        loadData();
    }

    @FXML
    private void edit(ActionEvent event) {
        Produto produto
                = tableData.getSelectionModel()
                        .getSelectedItem();
        this.openForm(produto, event);
    }

    @FXML
    private void newRecord(ActionEvent event) {
        this.openForm(new Produto(), event);
    }

    @FXML
    private void delete(ActionEvent event) {
        if (tableData.getSelectionModel()
                .getSelectedIndex() >= 0) {
            try {
                Produto produto = tableData
                        .getSelectionModel().getSelectedItem();
                produtoDao.delete(produto.getId());
                tableData.getItems().remove(
                        tableData.getSelectionModel()
                                .getSelectedIndex());

            } catch (Exception e) {
                e.printStackTrace();
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("Erro");
                alert.setHeaderText("Ocorreu um erro "
                        + " ao remover o registro!");
                alert.setContentText("Por favor, tente realizar "
                        + "a operação novamente!");
                alert.showAndWait();
            }
        } else {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Erro");
            alert.setHeaderText("Nenhum registro "
                    + "selecionado");
            alert.setContentText("Por favor, "
                    + "selecione um registro "
                    + "na tabela!");
            alert.showAndWait();
        }
    }
    private void loadProduto() {
        try {
            setDataPane(fadeAnimate("/fxml/FXMLCadastroProduto.fxml") );
        } catch (Exception e) {
            e.printStackTrace();
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle(" .: JavaFX :. ");
            alert.setHeaderText("Atenção, ocorreu um erro!");
            alert.setContentText("Falha ao abrir a tela de cadastro.");
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
