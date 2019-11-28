package br.edu.utfpr.pb.oo24s.controller;

import br.edu.utfpr.pb.oo24s.dao.ClienteDao;
import br.edu.utfpr.pb.oo24s.dao.ReservaDao;
import br.edu.utfpr.pb.oo24s.model.Cliente;
import br.edu.utfpr.pb.oo24s.model.EMotivo;
import br.edu.utfpr.pb.oo24s.model.Reserva;
import java.io.IOException;
import java.net.URL;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;
import javafx.animation.FadeTransition;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.scene.layout.VBox;
import javafx.util.Duration;
import javax.swing.JFormattedTextField;
import javax.swing.JOptionPane;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author Rafael Mello
 */
public class FXMLReservaController implements Initializable {

    @FXML
    private Button buttonVoltar;
    @FXML
    private Button buttonRecarregar;
    @FXML
    private Button buttonSalvar;
    @FXML
    private VBox vboxPrincipal;
    @FXML
    public ComboBox cbCliente;
    @FXML
    private ComboBox cbQuarto;
    @FXML
    private ComboBox cbMotivo;
    @FXML
    private TextField tfDiaria;
    @FXML
    private DatePicker tfDataReserva;
    @FXML
    private DatePicker tfDataEntrada;
    @FXML
    private DatePicker tfDataSaida;
    @FXML
    private TextField tfQuantidade;
    @FXML
    private ListView list;
    // lista de hospedes
    List<Cliente> clientes = new ArrayList<>();
    ObservableList<String> items = FXCollections.observableArrayList();
    
    Reserva reserva = new Reserva();
    ReservaDao reservaDao = new ReservaDao();
    private Stage stage;

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        this.reserva = new Reserva();
        this.reservaDao = new ReservaDao();
        
        this.buttonVoltar.setOnAction(
                (t) -> {
                    loadVoltar();
                }
        );
        loadCampos();
        this.buttonRecarregar.setOnAction(
                (t) -> {
                    loadAtualizaList();
                }
        );
        this.buttonSalvar.setOnAction(
                (t) -> {
                    save();
                }
        );
    }

    public void loadCampos() {
        List<String> quartos = new ArrayList<>();
        List<String> motivos = new ArrayList<>();

        //clientes.add("1");
        //clientes.add("2");
        quartos.add("1");
        motivos.add("Trabalho");
        motivos.add("Passeio");
        motivos.add("Turismo");
        
        ClienteDao clienteDao = new ClienteDao();
        
        clientes = clienteDao.getAll();
        
        
        List<Long> clientesid = new ArrayList<Long>();
        for (int i = 0; i < clientes.size(); i++) {
            clientesid.add(clientes.get(i).getId());
        }
        
        cbCliente.setItems(FXCollections.observableArrayList(clientesid));
        cbQuarto.setItems(FXCollections.observableArrayList(quartos));
        cbMotivo.setItems(FXCollections.observableArrayList(motivos));
        
        tfDiaria.setText("122.20");
        DateFormat format = new SimpleDateFormat("dd--MMMM--yyyy");
        JFormattedTextField dateTextField = new JFormattedTextField(format);
    }

    private void loadVoltar() {
        try {

            setDataPane(fadeAnimate("/fxml/FXMLPrincipal.fxml"));
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

    private void loadAtualizaList() {
        for (int i = 0; i < Integer.parseInt(tfQuantidade.getText()); i++) {
            if(i==0){
                String str = JOptionPane.showInputDialog("Informe o id do cliente");
                items.add(str+" - cliente");
            } else {
                String str = JOptionPane.showInputDialog("Informe o id do hospede "+i);
                items.add(str);
            }
        }
        list.setItems(items);
        System.out.println(tfDataReserva.getValue());
    }
    
    public void setDialogStage(Stage stage) {
        this.stage = stage;
    }
    
    @FXML
    private void save() {
        System.out.println(cbCliente.getValue().toString()+"\n");
        System.out.println(cbMotivo.getValue().toString()+"\n");
        System.out.println(tfQuantidade.getText()+"\n");
        System.out.println(tfDiaria.getText()+"\n");
        System.out.println(tfDataReserva.getValue().toString()+"\n");
        System.out.println(tfDataEntrada.getValue().toString()+"\n");
        System.out.println(tfDataSaida.getValue().toString()+"\n");
        
        reserva.setCliente(Integer.parseInt(cbCliente.getValue().toString()));
        reserva.setValordiaria(Double.parseDouble(tfDiaria.getText()));
        reserva.setQuarto(Integer.parseInt(cbQuarto.getValue().toString()));
        reserva.setHospedes(clientes); //aqui está pegando todos os clientes!
        reserva.setDataReserva(tfDataReserva.getValue());
        reserva.setDataEntrada(tfDataEntrada.getValue());
        reserva.setDataSaida(tfDataSaida.getValue());
        reserva.setMotivo(EMotivo.TURISMO);
        
        this.reservaDao.save(reserva);
        
        //this.stage.close();
       
    }

}
