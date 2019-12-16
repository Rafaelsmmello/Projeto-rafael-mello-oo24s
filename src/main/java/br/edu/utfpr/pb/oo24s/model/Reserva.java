/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.edu.utfpr.pb.oo24s.model;

import br.edu.utfpr.pb.oo24s.converter.MotivoConverter;
import java.time.LocalDate;
import java.util.List;
import java.util.Objects;
import javax.persistence.Column;
import javax.persistence.Convert;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;

@Entity
@NamedQueries({
    @NamedQuery(name = "Reserva.findAtivo",
            query = "from Reserva r "
                + " where r.ativo=true")
})
public class Reserva implements AbstractModel{
    public static final String FIND_BY_ATIVO = "Reserva.findAtivo";
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idReserva;
    
    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "quarto_id")
    private Quarto quarto;
    
    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "cliente_id")
    private Cliente cliente;
    
    @OneToMany
    @Column(nullable = false)
    private List<Cliente> hospedes;
    
    @Column(nullable = false)
    private LocalDate dataReserva;
    
    @Column(nullable = false)
    private LocalDate dataEntrada;
    
    @Column(nullable = false)
    private LocalDate dataSaida;
    
    @Convert(converter = MotivoConverter.class)
    @Column(nullable = false)
    private EMotivo motivo;
    
    @Column(nullable = false)
    private double valordiaria;
    
    @OneToOne
    @JoinColumn(name = "usuario_id")
    private Usuario usuario;
    
    private Boolean ativo;

    public Boolean getAtivo() {
        return ativo;
    }

    public void setAtivo(Boolean ativo) {
        this.ativo = ativo;
    }
    
    
    /*
    private int dias;

    public int getDias() {
        return dias;
    }

    public void setDias() {
        this.dias = Period.between(dataEntrada, dataSaida).getDays(); 
    }
    */
    
    public Reserva() {
        setAtivo(Boolean.TRUE);
    }

    public Long getId() {
        return idReserva;
    }

    public void setId(Long idReserva) {
        this.idReserva = idReserva;
    }

    public Usuario getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }

    public Quarto getQuarto() {
        return quarto;
    }

    public void setQuarto(Quarto quarto) {
        this.quarto = quarto;
    }

    public Cliente getCliente() {
        return cliente;
    }

    public void setCliente(Cliente cliente) {
        this.cliente = cliente;
    }

    public List<Cliente> getHospedes() {
        return hospedes;
    }

    public void setHospedes(List<Cliente> hospedes) {
        this.hospedes = hospedes;
    }
    public LocalDate getDataReserva() {
        return dataReserva;
    }

    public void setDataReserva(LocalDate dataReserva) {
        this.dataReserva = dataReserva;
    }

    public LocalDate getDataEntrada() {
        return dataEntrada;
    }

    public void setDataEntrada(LocalDate dataEntrada) {
        this.dataEntrada = dataEntrada;
    }

    public LocalDate getDataSaida() {
        return dataSaida;
    }

    public void setDataSaida(LocalDate dataSaida) {
        this.dataSaida = dataSaida;
    }

    public EMotivo getMotivo() {
        return motivo;
    }

    public void setMotivo(EMotivo motivo) {
        this.motivo = motivo;
    }

    public double getValordiaria() {
        return valordiaria;
    }

    public void setValordiaria(double valordiaria) {
        this.valordiaria = valordiaria;
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 97 * hash + Objects.hashCode(this.idReserva);
        return hash;
    }
    
    
    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final Reserva other = (Reserva) obj;
        if (this.idReserva != other.idReserva) {
            return false;
        }
        return true;
    }
    
}
