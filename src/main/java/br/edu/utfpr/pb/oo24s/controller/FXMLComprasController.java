package br.edu.utfpr.pb.oo24s.controller;

import br.edu.utfpr.pb.oo24s.dao.ProdutoConsumidoDao;
import br.edu.utfpr.pb.oo24s.dao.ProdutoDao;
import br.edu.utfpr.pb.oo24s.dao.ReservaDao;
import br.edu.utfpr.pb.oo24s.model.Produto;
import br.edu.utfpr.pb.oo24s.model.ProdutosConsumidos;
import br.edu.utfpr.pb.oo24s.model.Reserva;
import java.io.ByteArrayInputStream;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import javax.swing.JOptionPane;

public class FXMLComprasController implements Initializable {

    @FXML
    private Button buttoncomprar;
    @FXML
    private VBox vboxprincipal;
    @FXML
    public DatePicker tfData;
    @FXML
    private ComboBox cbCliente;
    @FXML
    private ComboBox cbproduto;
    @FXML
    private TextField tfquantidade;
    @FXML
    private TextField tfid;
    @FXML
    private TextField tfcategoria;
    @FXML
    private TextField tfvalor;
    @FXML
    private TextField tfdescricao;
    @FXML
    private ImageView imageview;
    
    
    ProdutoDao produtoDao = new ProdutoDao();
    ReservaDao reservaDao = new ReservaDao();
    ProdutosConsumidos consumidos = new ProdutosConsumidos();
    
    
    List<Reserva> clientes = new ArrayList<>();
    List<Produto> produtos = new ArrayList<>();
    private Stage stage;
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        loadCampos();
    }
    
    private void loadCampos() {
        clientes = reservaDao.getAll();
        List<Long> clientesid = new ArrayList<>();
        int i;
        for (i = 0; i < clientes.size(); i++) {
            clientesid.add(clientes.get(i).getId());
        }
        produtos = produtoDao.getAll();
        List<String> produtosnome = new ArrayList<>();
        for (i = 0; i < produtos.size(); i++) {
            produtosnome.add(produtos.get(i).getDescricao());
        }
        cbCliente.setItems(FXCollections.observableArrayList(clientesid));
        cbproduto.setItems(FXCollections.observableArrayList(produtosnome));
    }
    
    @FXML
    private void carregaProduto() {
        Produto produto = new Produto();
        produto = produtoDao.findByDescricaoQuery(cbproduto.getValue().toString());
        tfid.setText(produto.getId().toString());
        tfcategoria.setText(produto.getCategoria().toString());
        tfdescricao.setText(produto.getDescricao().toString());
        double result = produto.getValor() * Double.parseDouble(tfquantidade.getText());
        tfvalor.setText(String.valueOf(result));
        try {
                if (produto.getFoto() != null) {
                    Image image = new Image(
                            new ByteArrayInputStream(
                                    produto.getFoto())
                    );
                    imageview.setImage(image);
                }
            } catch (Exception e) {
                JOptionPane.showMessageDialog(null,"produto nao tem imagem");
                e.printStackTrace();
            }
    }
    
    @FXML
    private void save() {
        ProdutoConsumidoDao consumidosDao = new ProdutoConsumidoDao();
        Reserva reserva = new Reserva();
        Produto produto = new Produto();
        reserva = reservaDao.getById(Long.parseLong(cbCliente.getValue().toString()));
        produto = produtoDao.getById(Long.parseLong(tfid.getText()));
        consumidos.setIdReserva(reserva);
        consumidos.setIdProduto(produto);
        consumidos.setData_compra(tfData.getValue());
        consumidos.setQuantidade(Integer.parseInt(tfquantidade.getText()));
        consumidosDao.save(consumidos);
        
        JOptionPane.showMessageDialog(null, "compra efetivada!");
        
        this.stage.close();
    }


}
