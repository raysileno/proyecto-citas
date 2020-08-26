# proyecto-citas
Proyecto java de citas, gestión de trámites y consultas
HOLA!!

Para el correcto funcionamiento de las apps es necesario primero crear la base de datos
con el archivo sql que adjunto. Ésta contiene las tablas necesarias que van a 
consultar las aplicaciones. Hemos utilizado mysql en su puerto por defecto 3306, con el
usuario "root" y sin contraseña. Si usas otra configuración, hay que modificarlo también
en las tres aplicaciones, en el archivo de configuración llamado "glassfish-resources.xml"
dentro de la carpeta "Configuration Files".


Después, los tres programas funcionan de manera independiente, aunque interactúan entre
ellos mediante la base de datos. Se pueden abrir los tres en el orden que se quiera. La
app nombrada "Proyecto_Llamada" se corresponde con la pantalla informativa del turno de
la cita, el de "Proyecto_Usuario" sería para el registro de usuarios y pedir citas, y el
de "Proyecto_Citas" es el que estaría dirigido para los empleados de la empresa que se
encargan de llamar al siguiente turno, realizar trámites y realizar consultas.


MUCHAS GRACIAS!!
