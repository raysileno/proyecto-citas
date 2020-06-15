package entidades_POJO;

import entidades_POJO.Citas;
import entidades_POJO.Historial;
import entidades_POJO.TramiteAltas;
import entidades_POJO.TramiteBajas;
import entidades_POJO.TramiteSuspensiones;
import javax.annotation.Generated;
import javax.persistence.metamodel.CollectionAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="EclipseLink-2.5.2.v20140319-rNA", date="2020-06-12T10:22:00")
@StaticMetamodel(Usuario.class)
public class Usuario_ { 

    public static volatile SingularAttribute<Usuario, String> apellidos;
    public static volatile CollectionAttribute<Usuario, TramiteAltas> tramiteAltasCollection;
    public static volatile SingularAttribute<Usuario, String> estado;
    public static volatile SingularAttribute<Usuario, String> fechaNacimiento;
    public static volatile CollectionAttribute<Usuario, Historial> historialCollection;
    public static volatile SingularAttribute<Usuario, String> direccion;
    public static volatile SingularAttribute<Usuario, String> nombre;
    public static volatile SingularAttribute<Usuario, String> password;
    public static volatile SingularAttribute<Usuario, String> cuentaBancaria;
    public static volatile CollectionAttribute<Usuario, TramiteBajas> tramiteBajasCollection;
    public static volatile CollectionAttribute<Usuario, Citas> citasCollection;
    public static volatile CollectionAttribute<Usuario, TramiteSuspensiones> tramiteSuspensionesCollection;
    public static volatile SingularAttribute<Usuario, String> dni;

}