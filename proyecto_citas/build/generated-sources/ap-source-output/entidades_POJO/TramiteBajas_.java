package entidades_POJO;

import entidades_POJO.Empleado;
import entidades_POJO.Tramites;
import entidades_POJO.Usuario;
import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="EclipseLink-2.5.2.v20140319-rNA", date="2020-06-12T10:22:00")
@StaticMetamodel(TramiteBajas.class)
public class TramiteBajas_ { 

    public static volatile SingularAttribute<TramiteBajas, String> fechaBaja;
    public static volatile SingularAttribute<TramiteBajas, String> motivo;
    public static volatile SingularAttribute<TramiteBajas, Empleado> dniEmpleado;
    public static volatile SingularAttribute<TramiteBajas, Boolean> corrientePago;
    public static volatile SingularAttribute<TramiteBajas, Integer> idBaja;
    public static volatile SingularAttribute<TramiteBajas, Tramites> tipoTramite;
    public static volatile SingularAttribute<TramiteBajas, Usuario> dniUsuario;

}