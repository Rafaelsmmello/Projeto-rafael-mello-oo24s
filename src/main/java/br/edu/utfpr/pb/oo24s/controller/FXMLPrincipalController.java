package br.edu.utfpr.pb.oo24s.controller;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
import br.edu.utfpr.pb.oo24s.db.DatabaseConnection;
import br.edu.utfpr.pb.oo24s.model.Usuario;
import br.edu.utfpr.pb.oo24s.report.GenerateReport;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.time.LocalDate;
import java.util.HashMap;
import java.util.Map;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.animation.FadeTransition;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.VBox;
import javafx.util.Duration;
import javax.swing.JOptionPane;
import net.sf.jasperreports.view.JasperViewer;

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
    private Button buttonCompras;
    @FXML
    private Button buttonRelatorio;
    @FXML
    private VBox vboxPrincipal;
    @FXML
    private VBox vbox;
    @FXML
    private DatePicker tfData;
    @FXML
    private TextField tfNome;
    @FXML
    private ImageView imageview;
    private Usuario usuario;

    public void setUsuarioAutenticado(Usuario usuario) {
        this.usuario = usuario;
        tfNome.setText(usuario.getNome());
        try {
            if (usuario.getFoto() != null) {
                Image image = new Image(
                        new ByteArrayInputStream(
                                usuario.getFoto())
                );
                imageview.setImage(image);
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "usuario nao tem imagem");
            e.printStackTrace();
        }
    }

    public Usuario getUsuarioAutenticado() {
        return usuario;
    }

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
        this.buttonHospedes.setOnAction(
                (t) -> {
                    loadHospedes();
                }
        );
        this.buttonProdutos.setOnAction(
                (t) -> {
                    loadProdutos();
                }
        );
        this.buttonUsuarios.setOnAction(
                (t) -> {
                    loadUsuarios();
                }
        );
        this.buttonCompras.setOnAction(
                (t) -> {
                    loadCompras();
                }
        );
        tfData.setValue(LocalDate.now());
    }

    private void loadCheckin2() {
        try {

            setDataPane(fadeAnimate("/fxml/FXMLReserva.fxml"));
        } catch (Exception e) {
            e.printStackTrace();
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle(" .: JavaFX :. ");
            alert.setHeaderText("Atenção, ocorreu um erro!");
            alert.setContentText("Falha ao abrir a tela de checkin.");
            alert.showAndWait();
        }
    }
    
    @FXML
    private void showReport() {
        JOptionPane.showInputDialog("Selecione o código da reserva");
        GenerateReport generateReport = new GenerateReport();
        InputStream file = this.getClass().getResourceAsStream("/report/Checkout.jasper");

        Map<String, Object> parameters = new HashMap<>();
        parameters.put("TITULO", "Relatório de Checkout");
        
        DatabaseConnection conn = DatabaseConnection.getInstance();
        try {
            JasperViewer viewer = generateReport.getReport(
                    conn.getConnection(), parameters, file);
            viewer.setVisible(true);
        } catch (Exception e) {
            e.printStackTrace();
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Erro");
            alert.setHeaderText("Falha ao exibir relatório!");
            alert.setContentText("Falha ao exibir relatório!");
            alert.showAndWait();
        }
    }

    @FXML
    private void loadCheckin() {
        try {
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(
                    this.getClass()
                            .getResource("/fxml/FXMLReserva.fxml"));
            VBox v = (VBox) loader.load();

            FadeTransition ft = new FadeTransition(Duration.millis(1500));
            ft.setNode(v);
            ft.setFromValue(0.1);
            ft.setToValue(1);
            ft.setCycleCount(1);
            ft.setAutoReverse(false);
            ft.play();

            vbox.getChildren().setAll(v);
            FXMLReservaController controller
                    = loader.getController();
            controller.setUsuarioAutenticado(usuario);
           // setDataPane(fadeAnimate("/fxml/FXMLReserva.fxml"));
            
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void loadHospedes() {
        try {
            setDataPane(fadeAnimate("/fxml/FXMLClienteLista.fxml"));
        } catch (Exception e) {
            e.printStackTrace();
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle(" .: JavaFX :. ");
            alert.setHeaderText("Atenção, ocorreu um erro!");
            alert.setContentText("Falha ao abrir a tela de cadastro.");
            alert.showAndWait();
        }
    }

    private void loadUsuarios() {
        try {
            setDataPane(fadeAnimate("/fxml/FXMLUsuarioLista.fxml"));
        } catch (Exception e) {
            e.printStackTrace();
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle(" .: JavaFX :. ");
            alert.setHeaderText("Atenção, ocorreu um erro!");
            alert.setContentText("Falha ao abrir a tela de cadastro.");
            alert.showAndWait();
        }
    }

    private void loadProdutos() {
        try {
            setDataPane(fadeAnimate("/fxml/FXMLProdutoLista.fxml"));
        } catch (Exception e) {
            e.printStackTrace();
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle(" .: JavaFX :. ");
            alert.setHeaderText("Atenção, ocorreu um erro!");
            alert.setContentText("Falha ao abrir a tela de cadastro.");
            alert.showAndWait();
        }
    }

    private void loadCompras() {
        try {
            setDataPane(fadeAnimate("/fxml/FXMLCompras.fxml"));
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
        vbox.getChildren().setAll(node);
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
