package br.edu.utfpr.pb.oo24s.controller;

import br.edu.utfpr.pb.oo24s.dao.ClienteDao;
import br.edu.utfpr.pb.oo24s.dao.QuartoDao;
import br.edu.utfpr.pb.oo24s.dao.ReservaDao;
import br.edu.utfpr.pb.oo24s.model.Cliente;
import br.edu.utfpr.pb.oo24s.model.Quarto;
import br.edu.utfpr.pb.oo24s.model.EMotivo;
import br.edu.utfpr.pb.oo24s.model.Reserva;
import br.edu.utfpr.pb.oo24s.model.Usuario;
import java.io.ByteArrayInputStream;
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
import javafx.scene.image.Image;
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
    private TextField tfUsuario;
    @FXML
    private ListView list;
    @FXML
    private TextField tfNome;

    // lista de hospedes
    List<Reserva> reservas = new ArrayList<>();
    List<Cliente> clientes = new ArrayList<>();
    List<Quarto> quartos = new ArrayList<>();
    List<Cliente> hospedes = new ArrayList<>();
    ObservableList<String> items = FXCollections.observableArrayList();

    Reserva reserva = new Reserva();
    ReservaDao reservaDao = new ReservaDao();
    QuartoDao quartoDao = new QuartoDao();
    ClienteDao clienteDao = new ClienteDao();
    private Stage stage;
    int q = 0;

    Usuario usuario;// = new Usuario();

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
        setUsuarioAutenticado(usuario);
    }

    public void loadCampos() {
        List<EMotivo> motivos = new ArrayList<>();

        motivos.add(EMotivo.PASSEIO);
        motivos.add(EMotivo.TRABALHO);
        motivos.add(EMotivo.TURISMO);

        ClienteDao clienteDao = new ClienteDao();
        clientes = clienteDao.getAll();
        List<Long> clientesid = new ArrayList<>();
        int i;
        for (i = 0; i < clientes.size(); i++) {
            clientesid.add(clientes.get(i).getId());
        }

        ReservaDao reservaDao = new ReservaDao();
        reservas = reservaDao.getAll();
        List<Long> mostraquarto = new ArrayList<>();
        List<Integer> reservasid = new ArrayList<>();
        for (i = 0; i < reservas.size(); i++) {
            reservasid.add(Integer.parseInt(reservas.get(i).getQuarto().getId().toString()));
        }

        QuartoDao quartoDao = new QuartoDao();
        quartos = quartoDao.getAll();
        List<Long> quartosid = new ArrayList<>();
        List<Integer> iguais = new ArrayList<>();
        int existe;
        int x = 0;
        for (i = 0; i < quartos.size(); i++) {
            existe = -1;
            quartosid.add(quartos.get(i).getId());

            for (int j = 0; j < reservasid.size(); j++) {
                if (reservasid.get(j).equals(Integer.parseInt(quartosid.get(i).toString()))) {
                    existe = i;
                    break;
                }
            }
            if (existe == -1) {
                mostraquarto.add(quartos.get(i).getId());
            }
        }

        cbCliente.setItems(FXCollections.observableArrayList(clientesid));
        cbQuarto.setItems(FXCollections.observableArrayList(mostraquarto));
        cbMotivo.setItems(FXCollections.observableArrayList(motivos));

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
            if (i == 0) {
                String str = JOptionPane.showInputDialog("Informe o id do cliente");
                Cliente cliente = this.clienteDao.getById(Long.parseLong(str));
                hospedes.add(cliente);
                items.add(str + " - cliente");
            } else {
                String str = JOptionPane.showInputDialog("Informe o id do hospede " + i);
                items.add(str);
                Cliente cliente = this.clienteDao.getById(Long.parseLong(str));
                hospedes.add(cliente);
            }

        }
        tfDiaria.setText(String.valueOf(quartos.get(q).getValor()));

        list.setItems(items);
        System.out.println(tfDataReserva.getValue());
    }

    public void setDialogStage(Stage stage) {
        this.stage = stage;
    }

    @FXML
    private void save() {
        Cliente cliente = new Cliente();
        cliente = clienteDao.getById(Long.parseLong(cbCliente.getValue().toString()));
        Quarto quarto = new Quarto();
        quarto = quartoDao.getById(Long.parseLong(cbQuarto.getValue().toString()));
        
        reserva.setCliente(cliente);
        reserva.setValordiaria(Double.parseDouble(tfDiaria.getText()));
        reserva.setQuarto(quarto);
        reserva.setHospedes(hospedes);
        reserva.setDataReserva(tfDataReserva.getValue());
        reserva.setDataEntrada(tfDataEntrada.getValue());
        reserva.setDataSaida(tfDataSaida.getValue());
        reserva.setMotivo((EMotivo) cbMotivo.getValue());
        reserva.setUsuario(this.usuario);
        this.reservaDao.save(reserva);

        //this.stage.close();
    }

    /*private void CarregaUsuario() {
        FXMLLoader loader = new FXMLLoader();
            loader.setLocation(this.getClass().getResource("/fxml/FXMLPrincipal.fxml"));
                FXMLPrincipalController controller = loader.getController();
                this.usuario = controller.getUsuarioAutenticado();
                tfUsuario.setText(usuario.getId().toString());
    }*/
    void setUsuarioAutenticado(Usuario usuario) {
        this.usuario = usuario;
        System.out.println(usuario.getId());
        tfUsuario.setText(usuario.getId().toString());
    }
}
