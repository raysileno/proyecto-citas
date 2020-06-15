/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package beans;

import entidades_POJO.Citas;
import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.PersistenceUnit;
import javax.persistence.Query;

/**
 *
 * @author jluis
 */
@Stateless
public class llamadaEJB {
   @PersistenceUnit
  EntityManagerFactory emf;
    // Add business logic below. (Right-click in editor and choose
    // "Insert Code > Add Business Method")
   public List DameCitas () {
        EntityManager em = emf.createEntityManager();
       Query q = em.createQuery("SELECT c FROM Citas c WHERE c.llamado = :llamado");
       q.setParameter("llamado", true);
       List llamado = q.getResultList();
       em.close();
       return llamado;
    }
}
