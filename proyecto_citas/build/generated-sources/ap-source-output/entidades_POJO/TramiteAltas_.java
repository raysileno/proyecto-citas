package entidades_POJO;

import entidades_POJO.Empleado;
import entidades_POJO.Tramites;
import entidades_POJO.Usuario;
import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="EclipseLink-2.5.2.v20140319-rNA", date="2020-06-12T10:22:00")
@StaticMetamodel(TramiteAltas.class)
public class TramiteAltas_ { 

    public static volatile SingularAttribute<TramiteAltas, Integer> precio;
    public static volatile SingularAttribute<TramiteAltas, String> fechaAlta;
    public static volatile SingularAttribute<TramiteAltas, Empleado> dniEmpleado;
    public static volatile SingularAttribute<TramiteAltas, String> tipoPlan;
    public static volatile SingularAttribute<TramiteAltas, Boolean> corrientePago;
    public static volatile SingularAttribute<TramiteAltas, Tramites> tipoTramite;
    public static volatile SingularAttribute<TramiteAltas, Integer> idAlta;
    public static volatile SingularAttribute<TramiteAltas, Usuario> dniUsuario;

}