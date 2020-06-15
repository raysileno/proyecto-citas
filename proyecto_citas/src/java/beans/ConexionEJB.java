/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package beans;

import com.sun.javafx.scene.control.skin.VirtualFlow;
import entidades_POJO.Citas;
import entidades_POJO.Empleado;
import entidades_POJO.Historial;
import entidades_POJO.TramiteAltas;
import entidades_POJO.TramiteBajas;
import entidades_POJO.TramiteSuspensiones;
import entidades_POJO.Tramites;
import entidades_POJO.Usuario;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.PersistenceUnit;
import javax.persistence.Query;

/**
 *
 * @author Eric
 */
@Stateless
public class ConexionEJB {

    @PersistenceUnit
    EntityManagerFactory emf;
    
    public static Empleado empleado;
    public static Citas cit;
    public static Usuario usu;
    public static TramiteAltas traAlt;
    public static String inicio;
    
    public boolean existeEmpleado(Empleado e) {
        EntityManager em = emf.createEntityManager();
        Empleado encontrada = em.find(Empleado.class, e.getDni());
        em.close();
        return encontrada != null;
    }
    
    public boolean existeUsuario(Usuario u) {
        EntityManager em = emf.createEntityManager();
        Usuario encontrada = em.find(Usuario.class, u.getDni());
        em.close();
        return encontrada != null;
    }
    
    
    public Empleado findEmpleadoByDni (String dni){
        Query q = emf.createEntityManager().createNamedQuery("Empleado.findByDni");
        q.setParameter("dni", dni);
        List<Empleado> result = q.getResultList();
        if(result.isEmpty()){
            return null;
        }else{
           Iterator iter = result.iterator();
        Empleado e = (Empleado) iter.next();
        return e; 
        }         
    }
    
    public List<Citas> findCitaByTipo (Empleado e){       
        Query q = emf.createEntityManager().createNamedQuery("Citas.findAll");
        List<Citas> lista = q.getResultList();
        List<Citas> listaTipo = new ArrayList<>();
        for (int i = 0; i < lista.size(); i++){
            if(lista.get(i).getTipoTramite().getId() == e.getTipoTramite()){
                listaTipo.add(lista.get(i));
            }
        }
        Collections.sort(listaTipo, (Citas c1, Citas c2) -> new Integer(c1.getOrden()).compareTo(c2.getOrden()));        
        return listaTipo;
    }
    
    public List<TramiteAltas> findAllTramiteAltas(){
        return emf.createEntityManager().createNamedQuery("TramiteAltas.findAll").getResultList();
    }
    
    public String obtenerFecha(){
        DateFormat fecha = new SimpleDateFormat("yyyy/MM/dd");
        Date date = new Date();
        String convertido = fecha.format(date);
        return convertido;
    }
    
    public String obtenerHora(){
        DateFormat hora = new SimpleDateFormat("HH:mm:ss");
        Date date = new Date();
        String convertido = hora.format(date);
        return convertido;
    }
    
    public void insertarTramiteAlta (){
        EntityManager em = emf.createEntityManager();
        em.persist(traAlt);
        em.close();
    }
    
    public List<Historial> findAllHistorial(){
        return emf.createEntityManager().createNamedQuery("Historial.findAll").getResultList();
    }
    
    public void insertarHistorial (Historial h){
        EntityManager em = emf.createEntityManager();
        em.persist(h);
        em.close();
    }
    
    public void eliminarCita() {
        EntityManager em = emf.createEntityManager();
        Citas aux = em.find(Citas.class, cit.getCodigoCita());
        em.remove(aux);
        em.close();
    }

    public List<TramiteBajas> findAllTramiteBajas() {
        return emf.createEntityManager().createNamedQuery("TramiteBajas.findAll").getResultList();
    }
    
    public void insertarBaja (TramiteBajas b){
        EntityManager em = emf.createEntityManager();
        em.persist(b);
        em.close();
    }

    public void eliminarUsuario() {
        EntityManager em = emf.createEntityManager();
        Usuario aux = em.find(Usuario.class, usu.getDni());
        em.remove(aux);
        em.close();
    }

    public List<TramiteSuspensiones> findAllTramiteSuspensiones() {
        return emf.createEntityManager().createNamedQuery("TramiteSuspensiones.findAll").getResultList();
    }

    public void insertarSuspension(TramiteSuspensiones traSu) {
        EntityManager em = emf.createEntityManager();
        em.persist(traSu);
        em.close();
    }
    
    public Usuario findUsuarioByDni(String dni){
        Query q = emf.createEntityManager().createNamedQuery("Usuario.findByDni");
        q.setParameter("dni", dni);
        List<Usuario> result = q.getResultList();
        if(result.isEmpty()){
            return null;
        }else{
           Iterator iter = result.iterator();
        Usuario u = (Usuario) iter.next();
        return u; 
        }       
    }
    
    public TramiteAltas findAltaByDni(Usuario u){
        EntityManager em = emf.createEntityManager();
        Query q = em.createQuery("SELECT t FROM TramiteAltas t WHERE t.dniUsuario = :dniUsuario");
        q.setParameter("dniUsuario", u);
        List<TramiteAltas> lista = q.getResultList();
        if(lista.isEmpty()){
            return null;
        }else{
            TramiteAltas traAl = lista.get(0);
            return traAl;
        }        
    }
    
    public TramiteSuspensiones findSuspensionByDni (Usuario u){
        EntityManager em = emf.createEntityManager();
        Query q = em.createQuery("SELECT t FROM TramiteSuspensiones t WHERE t.dniUsuario = :dniUsuario");
        q.setParameter("dniUsuario", u);
        List<TramiteSuspensiones> lista = q.getResultList();
        if(lista.isEmpty()){
            return null;
        }else{
            TramiteSuspensiones traSus = lista.get(0);
            return traSus;
        }        
    }
    
    public void modificarUsuario(Usuario us) {
        EntityManager em = emf.createEntityManager();                
        if(existeUsuario(us)){
            Usuario aux = em.find(Usuario.class, us.getDni());
            aux.setNombre(us.getNombre());
            aux.setApellidos(us.getApellidos());
            aux.setPassword(us.getPassword());
            aux.setCuentaBancaria(us.getCuentaBancaria());
            aux.setDireccion(us.getDireccion());
            aux.setEstado(us.getEstado());
            aux.setFechaNacimiento(us.getFechaNacimiento());
            em.persist(aux);
        }else{
            em.persist(us);
        }        
        em.close();    
    }
    
    public void modificarPlan(TramiteAltas tra) {
        EntityManager em = emf.createEntityManager();
        TramiteAltas aux = em.find(TramiteAltas.class, tra.getIdAlta());        
        aux.setTipoPlan(tra.getTipoPlan());
        aux.setPrecio(tra.getPrecio());
        em.persist(aux);
        em.close();    
    }

    public void modificarFechaFinSuspension(TramiteSuspensiones tra) {
        EntityManager em = emf.createEntityManager();
        TramiteSuspensiones aux = em.find(TramiteSuspensiones.class, tra.getIdSuspension());
        aux.setFechaFinSus(tra.getFechaFinSus());
        em.persist(aux);
        em.close();
    }

    public void modificarCorrientePago(TramiteAltas tra) {
        EntityManager em = emf.createEntityManager();
        TramiteAltas aux = em.find(TramiteAltas.class, tra.getIdAlta());
        aux.setCorrientePago(tra.getCorrientePago());
        em.persist(aux);
        em.close();
    }
    
    public boolean existeAlta(Usuario u) {
        EntityManager em = emf.createEntityManager();
        TramiteAltas encontrada = findAltaByDni(u);
        em.close();
        return encontrada != null;
    }

    public void eliminarAlta(Usuario u) {
        EntityManager em = emf.createEntityManager();
        TramiteAltas aux = findAltaByDni(u);
        int id = aux.getIdAlta();
        TramiteAltas t = em.find(TramiteAltas.class, id);
        em.remove(t);
        em.close();
    }

    public void insertarEmpleado(Empleado e) {
        EntityManager em = emf.createEntityManager();
        em.persist(e);
        em.close();
    }

    public void modificarEmpleado(Empleado e) {
        EntityManager em = emf.createEntityManager();
        Empleado aux = em.find(Empleado.class, e.getDni());
        aux.setNombre(e.getNombre());
        aux.setApellidos(e.getApellidos());
        aux.setPassword(e.getPassword());
        aux.setMesa(e.getMesa());
        aux.setTipoTramite(e.getTipoTramite());
        em.persist(aux);
        em.close();
    }

    public void eliminarEmpleado(String parameter) {
        EntityManager em = emf.createEntityManager();
        Empleado aux = em.find(Empleado.class, parameter);
        em.remove(aux);
        em.close();
    }

    public void modificarCita() {
        EntityManager em = emf.createEntityManager();
        Citas aux = em.find(Citas.class, cit.getCodigoCita());
        aux.setLlamado(true);
        em.persist(aux);
        em.close();
    }

    public int dameCitasTotal() {
        Query q = emf.createEntityManager().createNamedQuery("Citas.findAll");
        List<Citas> lista = q.getResultList();
        return lista.size();
    }

    public List<Historial> damePersAtt() {
        String fechaHoy = obtenerFecha();
        EntityManager em = emf.createEntityManager();
        Query q = em.createQuery("SELECT h FROM Historial h WHERE h.fechaCita = :fecha");
        q.setParameter("fecha", fechaHoy);
        List<Historial> lista = q.getResultList();
        return lista;
    }
    
    public int damePersAttSu(){
        String fechaHoy = obtenerFecha();
        EntityManager em = emf.createEntityManager();
        Empleado empl = findEmpleadoByDni("45632548l");
        Query q = em.createQuery("SELECT h FROM Historial h WHERE h.fechaCita = :fecha and h.dniEmpleado = :empleado");
        q.setParameter("fecha", fechaHoy);
        q.setParameter("empleado", empl);
        List<Historial> lista = q.getResultList();
        return lista.size();
    }

    public int dameNoPresentados() {
        EntityManager em = emf.createEntityManager();
        Query q = em.createQuery("SELECT h FROM Historial h WHERE h.realizado = false");
        List<Historial> lista = q.getResultList();
        return lista.size();
    }
    
    public int dameNoPresentadosHoy() {
        String fechaHoy = obtenerFecha();
        EntityManager em = emf.createEntityManager();
        Query q = em.createQuery("SELECT h FROM Historial h WHERE h.realizado = false and h.fechaCita = :fecha");
        q.setParameter("fecha", fechaHoy);
        List<Historial> lista = q.getResultList();
        return lista.size();
    }

    public int dameAltasHoy() {
        String fechaHoy = obtenerFecha();
        EntityManager em = emf.createEntityManager();
        Query q = em.createQuery("SELECT t FROM TramiteAltas t WHERE t.fechaAlta = :fecha");
        q.setParameter("fecha", fechaHoy);
        List<Historial> lista = q.getResultList();
        return lista.size();
    }

    public int dameUsuRegis() {
        Query q = emf.createEntityManager().createNamedQuery("Usuario.findAll");
        List<Usuario> lista = q.getResultList();
        return lista.size();
    }

    public ArrayList<String> realizaConsultaTramite(String[] checkTra) {
        ArrayList<String> tramites = new ArrayList<>();
        List<TramiteAltas> listaAltas;
        List<TramiteBajas> listaBajas;
        List<TramiteSuspensiones> listaSuspensiones;
        if(checkTra == null){
            tramites.add("<h2>No se ha seleccionado ningún trámite</h2>");
        }else{
            for (String tra : checkTra) {
                switch(tra){
                    case "1":
                        listaAltas = findAllTramiteAltas();
                        for(TramiteAltas tAlt : listaAltas){
                            tramites.add(tAlt.toString());
                        }
                        break;
                    case "2":
                        listaBajas = findAllTramiteBajas();
                        for(TramiteBajas tBaj : listaBajas){
                            tramites.add(tBaj.toString());
                        }
                        break;
                    case "3":
                        listaSuspensiones = findAllTramiteSuspensiones();
                        for(TramiteSuspensiones tSus : listaSuspensiones){
                            tramites.add(tSus.toString());
                        }
                        break;
                }
            }
        }
        return tramites;
    }   

    public ArrayList<String> realizaConsultaUsuario(String usuario) {
        ArrayList<String> listadoUsuarios = new ArrayList<>();
        if(usuario.equalsIgnoreCase("")){
            listadoUsuarios.add("<h2>No se ha seleccionado a ningún usuario</h2>");
        }else{
            Usuario u = findUsuarioByDni(usuario);
            if(u == null){
                listadoUsuarios.add("<h2>Este usuario no está registrado en la base de datos</h2>");
            }else{
                listadoUsuarios.add("Datos del usuario: " +u.toString());
                TramiteAltas tAlt = findAltaByDni(u);
                if(tAlt != null){
                    listadoUsuarios.add("Ficha de alta: " +tAlt.toString());
                }
                TramiteBajas tBaj = findBajaByDni(u);
                if(tBaj != null){
                    listadoUsuarios.add("Ficha de baja: " +tBaj.toString());
                }
                TramiteSuspensiones tSus = findSuspensionByDni(u);
                if(tSus != null){
                    listadoUsuarios.add("Ficha de suspensión: " +tSus.toString());
                }
            }
        }
        return listadoUsuarios;
    }

    private TramiteBajas findBajaByDni(Usuario u) {
        EntityManager em = emf.createEntityManager();
        Query q = em.createQuery("SELECT t FROM TramiteBajas t WHERE t.dniUsuario = :dniUsuario");
        q.setParameter("dniUsuario", u);
        List<TramiteBajas> lista = q.getResultList();
        if(lista.isEmpty()){
            return null;
        }else{
            TramiteBajas traBa = lista.get(0);
            return traBa;
        } 
    }

    public ArrayList<String> realizaConsultaEmpleado(String empleado) {
        ArrayList<String> listadoEmpleado = new ArrayList<>();        
        if(empleado.equalsIgnoreCase("-")){
            listadoEmpleado.add("<h2>No se ha seleccionado a ningún empleado</h2>");
        }else{
            Empleado e = findEmpleadoByDni(empleado);
            listadoEmpleado.add("Datos del empleado: " + e.toString());
            List <TramiteAltas> listaAlt = findAltaByDniEmpl(empleado);
            if(!listaAlt.isEmpty()){
                listadoEmpleado.add("Trámites de alta realizados: ");
                listaAlt.forEach((tr) -> {
                    listadoEmpleado.add(tr.toString());
                });
            }
            List <TramiteBajas> listaBaj = findBajaByDniEmpl(empleado);
            if(!listaBaj.isEmpty()){
                listadoEmpleado.add("Trámites de baja realizados: ");
                listaBaj.forEach((tr) -> {
                    listadoEmpleado.add(tr.toString());
                });
            }
            List <TramiteSuspensiones> listaSus = findSuspensionByDniEmpl(empleado);
            if(!listaSus.isEmpty()){
                listadoEmpleado.add("Trámites de suspensión realizados: ");
                listaSus.forEach((tr) -> {
                    listadoEmpleado.add(tr.toString());
                });
            }
        }
        return listadoEmpleado;
    }

    public List<Empleado> finAllEmpleado(){
        Query q = emf.createEntityManager().createNamedQuery("Empleado.findAll");
        List<Empleado> listaEmpleados = q.getResultList();
        return listaEmpleados;
    }

    private List<TramiteAltas> findAltaByDniEmpl(String empleado) {
        EntityManager em = emf.createEntityManager();
        Empleado e = findEmpleadoByDni(empleado);
        Query q = em.createQuery("SELECT t FROM TramiteAltas t WHERE t.dniEmpleado = :e");
        q.setParameter("e", e);
        List<TramiteAltas> lista = q.getResultList();
        return lista;
    }
    
    private List<TramiteBajas> findBajaByDniEmpl(String empleado) {
        EntityManager em = emf.createEntityManager();
        Empleado e = findEmpleadoByDni(empleado);
        Query q = em.createQuery("SELECT t FROM TramiteBajas t WHERE t.dniEmpleado = :e");
        q.setParameter("e", e);
        List<TramiteBajas> lista = q.getResultList();
        return lista;
    }
    
    private List<TramiteSuspensiones> findSuspensionByDniEmpl(String empleado) {
        EntityManager em = emf.createEntityManager();
        Empleado e = findEmpleadoByDni(empleado);
        Query q = em.createQuery("SELECT t FROM TramiteSuspensiones t WHERE t.dniEmpleado = :e");
        q.setParameter("e", e);
        List<TramiteSuspensiones> lista = q.getResultList();
        return lista;
    }

    public ArrayList<String> realizaConsultaMesa(String[] checkMes) {
        ArrayList<String> listadoMesas = new ArrayList<>();
        if(checkMes == null){
            listadoMesas.add("<h2>No se ha seleccionado ninguna opción</h2>");
        }else if(checkMes.length == 2){
            listadoMesas.add("Número de citas pendientes de ser atendidas: <b>" + dameCitasTotal() + "</b>");
            listadoMesas.add("Número de trámites realizados hoy: <b>" + damePersAtt().size() + "</b>");
            listadoMesas.add("Número de trámites realizados en total: <b>" + findAllHistorial().size() + "</b>");
        }else{
            Empleado e = findEmpleadoByMesa(4);
            int numTra = 0;
            List<Historial> perAttHoy = damePersAtt();
            List<Historial> perAtt = findAllHistorial();
            int traSup = 0;
            for(Historial h : perAttHoy){
                if(h.getDniEmpleado() == e){
                    numTra++;
                }
            }
            for(Historial h : perAtt){
                if(h.getDniEmpleado() == e){
                    traSup++;
                }
            }
            if(checkMes[0].equalsIgnoreCase("4")){                
                listadoMesas.add("Número de citas pendientes de ser atendidas por la mesa del supervisor: <b>" + findCitaByTipo(e).size() + "</b>"); 
                listadoMesas.add("Número de trámites realizados hoy por la mesa del supervisor: <b>" + numTra + "</b>");
                listadoMesas.add("Número de trámites realizados en total por la mesa del supervisor: <b>" + traSup + "</b>");
            }else{
                listadoMesas.add("Número de citas pendientes de ser atendidas por las mesas de los operarios: <b>" + (dameCitasTotal() - findCitaByTipo(e).size()) + "</b>"); 
                listadoMesas.add("Número de trámites realizados hoy por las mesas de los operarios: <b>" + (damePersAtt().size() - numTra) + "</b>");
                listadoMesas.add("Número de trámites realizados en total por las mesas de los operarios: <b>" + (findAllHistorial().size() - traSup) + "</b>");
            }    
        }
        return listadoMesas;
    }

    private Empleado findEmpleadoByMesa(int mesa) {
        Query q = emf.createEntityManager().createNamedQuery("Empleado.findByMesa");
        q.setParameter("mesa", mesa);
        List<Empleado> listaEmpleados = q.getResultList();
        return listaEmpleados.get(0);
    }

    public void eliminarSuspension(TramiteSuspensiones tra) {
        EntityManager em = emf.createEntityManager();
        int id = tra.getIdSuspension();
        TramiteSuspensiones t = em.find(TramiteSuspensiones.class, id);
        em.remove(t);
        em.close();
    }
}
