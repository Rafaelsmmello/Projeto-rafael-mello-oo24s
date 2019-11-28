package br.edu.utfpr.pb.oo24s.dao;


import br.edu.utfpr.pb.oo24s.model.Reserva;

/**
 *
 * @author Rafael Mello
 */
public class ReservaDao extends GenericDao<Reserva, Long> {

    public ReservaDao() {
        super(Reserva.class);
    }
}
