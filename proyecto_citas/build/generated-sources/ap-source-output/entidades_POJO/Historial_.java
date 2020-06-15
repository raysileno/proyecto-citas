package entidades_POJO;

import entidades_POJO.Empleado;
import entidades_POJO.Tramites;
import entidades_POJO.Usuario;
import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="EclipseLink-2.5.2.v20140319-rNA", date="2020-06-12T10:22:00")
@StaticMetamodel(Historial.class)
public class Historial_ { 

    public static volatile SingularAttribute<Historial, String> fechaCita;
    public static volatile SingularAttribute<Historial, String> horaCita;
    public static volatile SingularAttribute<Historial, Empleado> dniEmpleado;
    public static volatile SingularAttribute<Historial, Boolean> realizado;
    public static volatile SingularAttribute<Historial, String> horaFinalizado;
    public static volatile SingularAttribute<Historial, Integer> idHistorial;
    public static volatile SingularAttribute<Historial, Tramites> tipoTramite;
    public static volatile SingularAttribute<Historial, Usuario> dniUsuario;

}