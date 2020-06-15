/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package beans;

import entidades_POJO.Citas;
import entidades_POJO.TramiteAltas;
import entidades_POJO.Tramites;
import entidades_POJO.Usuario;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import java.util.Random;
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
public class UsuarioEJB {

    @PersistenceUnit
    EntityManagerFactory emf;
    
    public static Usuario usu;
    
    public boolean existeUsuario(Usuario u) {
        EntityManager em = emf.createEntityManager();
        Usuario encontrada = em.find(Usuario.class, u.getDni());
        em.close();
        return encontrada != null;
    }
       public boolean existeCita() {
       EntityManager em = emf.createEntityManager();
       Query q = em.createQuery("SELECT c FROM Citas c WHERE c.dniUsuario = :dniUsuario");
        q.setParameter("dniUsuario", usu);
        List<Citas> result = q.getResultList();
        em.close();
        if (result.isEmpty()){
            return false;
        }
        return true;
    }
    
    public Usuario findUsuarioByDni (String dni){
        Query q = emf.createEntityManager().createNamedQuery("Usuario.findByDni");
        q.setParameter("dni", dni);
        List<Usuario> result = q.getResultList();
        if(result.isEmpty()){
            return null;
        }else{
           Iterator iter = result.iterator();
        Usuario u = (Usuario) iter.next();
        usu=u;
        return u; 
        } 
        
    }
    
      public Citas verCita() {
       EntityManager em = emf.createEntityManager();
       Query q = em.createQuery("SELECT c FROM Citas c WHERE c.dniUsuario = :dniUsuario");
        q.setParameter("dniUsuario", usu);
        List<Citas> result = q.getResultList();
        em.close();
       return result.get(0);
    }
      
    public int numeroCitas() {
           EntityManager em = emf.createEntityManager();
           Query q = em.createQuery("SELECT c FROM Citas c");
          //  q.setParameter("dniUsuario", usu);
            List<Citas> result = q.getResultList();
            em.close();
            return result.size()+1;
        }
    
    public Citas dameCita (String tramite){
      EntityManager em = emf.createEntityManager();
      Citas cita=new Citas();
      
      do{
      Tramites id=idCita(tramite);
      String fechaHora=obtenerfecha();
      String [] extraccion = fechaHora.split(" ");
      String fecha=extraccion [0];
      String hora=extraccion [1];   
      String codigo=random(id.getId());  
     

      int conteo = numeroCitas();
      cita=new Citas(codigo,usu,fecha,hora,false,id, conteo) ;
      }while(existeCita(cita));
       
     
      return cita;
   
    }    
    
     public void insertarCita(Citas c){
           EntityManager em = emf.createEntityManager();  
           em.persist(c);
           em.close();
           
           
           
       }
 
    public String obtenerfecha(){
         DateFormat fechaHora= new SimpleDateFormat ("yyyy/MM/dd HH:mm:ss");
         Date date =new Date();
         
         String dato =fechaHora.format(date);
         return dato;
         
     }
    
    public boolean existeAlta(){
        EntityManager em = emf.createEntityManager();
        Query q = em.createQuery("SELECT t FROM TramiteAltas t WHERE t.dniUsuario = :dniUsuario");
        q.setParameter("dniUsuario", usu);
        List<TramiteAltas> result = q.getResultList();
        em.close();
        if (result.isEmpty()){
            return false;
        }
        return true;
    
    }
    
        public  Tramites idCita(String tramite){
          int id;
        if(tramite.equalsIgnoreCase("alta")){
           if(existeAlta() == false){
               id=1; 
           }else{
               id=5;
           }
           
        }
        else if(tramite.equalsIgnoreCase("baja"))
        {
          if(existeAlta() == false){
               id=6; 
           }else{
               id=2;
           } 
        }
        else if(tramite.equalsIgnoreCase("suspende"))
        {    
            if(existeAlta() == false){
               id=7; 
           }else{
               id=3;
           }
        }
        else {
            id=4;
        }
        Tramites t=new Tramites(id);
        return t;
    }
        
        public String random(int id){
           
        String codigo;
        Random aleatorio = new Random();
        int ale=aleatorio.nextInt(1000);
            if(id==1){
                
              codigo="ALT";  
              codigo=codigo+ale;
              
            }
            
            else if(id==2){
                 codigo="BAJ"; 
                 codigo=codigo+ale;
            }
            
             else if(id==3){
                
              codigo="SUS"; 
              codigo=codigo+ale;
            }
             else{
                 codigo="SUP"; 
                  codigo=codigo+ale;
            }

            return codigo;
        }
        
        
       public boolean existeCita(Citas c) {
        EntityManager em = emf.createEntityManager();
        Citas encontrada = em.find(Citas.class, c.getCodigoCita());
        em.close();
        return encontrada != null;
    }
       
       public void nuevoUsuario(Usuario u){
           EntityManager em = emf.createEntityManager();  
           em.persist(u);
           em.close();
           
           
           
       }

    public void borrarCita() {
        EntityManager em = emf.createEntityManager();
        Query q = em.createQuery("SELECT c FROM Citas c WHERE c.dniUsuario = :dniUsuario");
        q.setParameter("dniUsuario", usu);
        List<Citas> result = q.getResultList();
        Citas c = result.get(0);
        em.remove(c);
        em.close();
    }

    
        
}