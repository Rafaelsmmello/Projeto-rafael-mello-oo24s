package br.edu.utfpr.pb.oo24s.dao;


import br.edu.utfpr.pb.oo24s.model.Reserva;
import br.edu.utfpr.pb.oo24s.model.Usuario;
import javax.persistence.Query;

/**
 *
 * @author Rafael Mello
 */
public class ReservaDao extends GenericDao<Reserva, Long> {

    public ReservaDao() {
        super(Reserva.class);
    }
    
    public Reserva findAtivoQuery(){
        Query query = em.createNamedQuery(
                Reserva.FIND_BY_ATIVO);
        //query.setParameter("descricao", descricao);
        
        return (Reserva) query.getSingleResult();
    }
}
