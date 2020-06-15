package entidades_POJO;

import entidades_POJO.Empleado;
import entidades_POJO.Tramites;
import entidades_POJO.Usuario;
import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="EclipseLink-2.5.2.v20140319-rNA", date="2020-06-12T10:22:00")
@StaticMetamodel(TramiteSuspensiones.class)
public class TramiteSuspensiones_ { 

    public static volatile SingularAttribute<TramiteSuspensiones, Empleado> dniEmpleado;
    public static volatile SingularAttribute<TramiteSuspensiones, String> motivoSus;
    public static volatile SingularAttribute<TramiteSuspensiones, String> fechaFinSus;
    public static volatile SingularAttribute<TramiteSuspensiones, Boolean> corrientePago;
    public static volatile SingularAttribute<TramiteSuspensiones, String> fechaIniSus;
    public static volatile SingularAttribute<TramiteSuspensiones, Tramites> tipoTramite;
    public static volatile SingularAttribute<TramiteSuspensiones, Integer> idSuspension;
    public static volatile SingularAttribute<TramiteSuspensiones, Usuario> dniUsuario;

}