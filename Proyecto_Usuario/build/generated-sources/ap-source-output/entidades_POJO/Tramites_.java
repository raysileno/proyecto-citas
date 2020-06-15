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

@Generated(value="EclipseLink-2.5.2.v20140319-rNA", date="2020-06-10T13:41:37")
@StaticMetamodel(Tramites.class)
public class Tramites_ { 

    public static volatile CollectionAttribute<Tramites, TramiteAltas> tramiteAltasCollection;
    public static volatile CollectionAttribute<Tramites, Historial> historialCollection;
    public static volatile CollectionAttribute<Tramites, TramiteBajas> tramiteBajasCollection;
    public static volatile SingularAttribute<Tramites, String> nombreTramite;
    public static volatile CollectionAttribute<Tramites, Citas> citasCollection;
    public static volatile SingularAttribute<Tramites, Integer> id;
    public static volatile CollectionAttribute<Tramites, TramiteSuspensiones> tramiteSuspensionesCollection;

}