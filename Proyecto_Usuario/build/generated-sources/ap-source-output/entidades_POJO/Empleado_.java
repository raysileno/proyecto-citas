package entidades_POJO;

import entidades_POJO.Historial;
import entidades_POJO.TramiteAltas;
import entidades_POJO.TramiteBajas;
import entidades_POJO.TramiteSuspensiones;
import javax.annotation.Generated;
import javax.persistence.metamodel.CollectionAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="EclipseLink-2.5.2.v20140319-rNA", date="2020-06-10T13:41:37")
@StaticMetamodel(Empleado.class)
public class Empleado_ { 

    public static volatile SingularAttribute<Empleado, String> apellidos;
    public static volatile CollectionAttribute<Empleado, TramiteAltas> tramiteAltasCollection;
    public static volatile SingularAttribute<Empleado, String> password;
    public static volatile SingularAttribute<Empleado, Integer> mesa;
    public static volatile CollectionAttribute<Empleado, Historial> historialCollection;
    public static volatile CollectionAttribute<Empleado, TramiteBajas> tramiteBajasCollection;
    public static volatile CollectionAttribute<Empleado, TramiteSuspensiones> tramiteSuspensionesCollection;
    public static volatile SingularAttribute<Empleado, String> nombre;
    public static volatile SingularAttribute<Empleado, Integer> tipoTramite;
    public static volatile SingularAttribute<Empleado, String> dni;

}