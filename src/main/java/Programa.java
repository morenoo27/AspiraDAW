/*
Nuestro prorama tendra las siguientes especificaciones:

CONFIGURAR EL SISTEMA. 

En esta opción hay que configurar el número de 
dependencias de la casa y el número de metros cuadrados de cada dependencia. 
Por defecto, y para simplificar, la vivienda tiene una cocina, un salón, un 
cuarto de baño y dos dormitorios. De cada depedencia, el sistema pregunta el 
número de metros de cada estancia (entre 1 y 100). Esta opción siempre debe 
ejercutarse la primera vez que se entra en la aplicación.

MEJORA OPCIONAL: 
que se puedan configurar viviendas con otro número de dependencias. 


CARGA. 

Establece el nivel de batería. (entre 0% y 100%).


ASPIRACIÓN.
    Modo completo.
        En este modo, la aspiradora limpia el piso entero. 
        El robot va limpiando habitaciones en función de su batería. Si al 
        entrar en una habitación no le llega la batería para 
        poder completarla entonces la aspiradora se para e informa 
        al usuario que no puede terminar y también informa de las 
        dependencias que ha podido limpiar. 

    Modo dependencias.
        Sólo limpia las habitaciones que se le indiquen, si tiene batería.


En ambos modos, cada metro cuadrado de limpieza agota un 1,5% de batería.


Cada vez que se limpia una habitación se actualiza el estado de la batería,
para controlar si puede limpiar la siguiente habitación.

En ningún caso, si está limpiando la casa o una dependencia, el nivel de batería
no puede ser inferior al 3%, para que el robot tenga autonomía y pueda volver a 
su base de carga.


ASPIRACIÓN Y FREGADO. 

Exactamente igual que el anterior pero agota un 2,25% de batería por cada 
metro cuadrado.


ESTADO GENERAL. 

Esta opción del menú servirá para mostrar:
    la fecha y hora actuales, 
    el nivel de batería del robot,
    lugar donde está parado,
    dependencias y metros cuadrados de la casa.


BASE DE CARGA. 

Le indica al robot que busque su base de carga y entre en modo carga de batería,
hasta que llegue al 100%.


SALIR. 

Con esta opción termina el programa. La aplicación se ejecutará hasta que el 
usuario decida cerrarla. 


El menú anterior es accesible cuando el usuario se autentique en la aplicación 
mediante usuario y contraseña válidos. Estas credenciales se almacenan en la 
aplicación usando constantes.


RECOMENDACIONES

Como puedes observar, esta aplicación debe resolver múltiples situaciones y 
llevar el control de muchos casos de uso diferentes. Debes tener en cuenta, 
para poder realizar realizar un buen proyecto, las siguientes recomendaciones: 
Aplica un diseño descendente, aplicando técnicas “divide y vencerás”. Ve de lo 
general a lo específico, dejando los detalles para el final.
Realiza algoritmos para los distintos casos que encuentres.
Prueba tus algoritmos con distintos casos de prueba.
Una vez probados, debes integrarlos en la aplicación final. 
Utiliza control de versiones, de forma que cada pequeño cambio, nuevo método, 
clase, archivo, etc quede reflejado en Git y GitHub. 
Ten en cuenta que debes aplicar todo lo aprendido hasta la fecha en el módulo.
Opcionalmente, y como posibles mejoras, se pueden usar métodos, para modularizar
mejor el programa, y arrays, con la finalidad de guardar informarción de una 
forma más óptima. 
 */

/**
 *
 * @author aleja
 */


//importamos las librairas que vamos a necesitar para este proyecto
import javax.swing.JOptionPane;
import java.util.Random;
import java.time.LocalDateTime;
import java.time.LocalTime;

public class Programa {

    public static double BATERIA = 100;

    public static void main(String[] args) {
        
        //declaracion de variables necesarias para el proyecto
        int habitacionesAniadidas = 0;
        int[] metrosDependencias = new int[4];
        int[] metrosHabAdiconales;
        String[] nombreDependencia = {"Cocina", "Salon", "Baño", "Dormitorio"};
        String[] nombreHabAdicional;
        
        inicioDeSesion();
        
        //Anunciamos la configuracion predeterminada
            String answer = JOptionPane.showInputDialog(null, "Estan predeterminadas"
                    + " las habitaciones: Cocina, salon, cuarto de baño y "
                    + " dormitorio.\n"
                    + "¿Quiere añadir alguna mas?");
            if (answer.equalsIgnoreCase("si")) {
                String respuestaHabtaciones = JOptionPane.showInputDialog("¿Cuantas "
                        + "habitaciones quiere añadir?");
                habitacionesAniadidas = Integer.parseInt(respuestaHabtaciones);

            }

            //declaramos la lonjitud de los arrays "nombreHabAdicional" y 
            // "metrsoHabAdicionales"
            metrosHabAdiconales = new int[habitacionesAniadidas];
            nombreHabAdicional = new String[habitacionesAniadidas];

            //do{
                //bucle para introducir dimensiones habitaciones predeterminadas
                for (int i = 0; i < 4; i++) {
                    String metrosHab = JOptionPane.showInputDialog("¿Metros cuadrados de"
                            + " la habitacion: "
                            + nombreDependencia[i] + " ?");
                    metrosDependencias[i] = Integer.parseInt(metrosHab);
                }
                if (answer.equalsIgnoreCase("si")) {

                    //bucle para introducir dimensiones habitaciones predeterminadas
                    for (int j = 0; j < habitacionesAniadidas; j++) {
                        nombreHabAdicional[j] = JOptionPane.showInputDialog("¿Nombre de "
                                + "la habitacion adicional" + (j + 1) + "?");
                        String metrosHabAniadidas = JOptionPane.showInputDialog("Dimensiones"
                                + " de la habitacion: " + nombreHabAdicional[j]);
                        metrosHabAdiconales[j] = Integer.parseInt(metrosHabAniadidas);
                    }
                }
            //}while();
        
            JOptionPane.showMessageDialog(null, "Dimensiones de la casa:\n"
                    + nombreDependencia[0] + "= " + metrosDependencias[0] +
                    " metros cuadrados." + "\n"
                    + nombreDependencia[1] + "= " + metrosDependencias[1] +
                    " metros cuadrados." + "\n"
                    + nombreDependencia[2] + "= " + metrosDependencias[2] +
                    " metros cuadrados." + "\n"
                    + nombreDependencia[3] + "= " + metrosDependencias[3] +
                    " metros cuadrados.");
            
            if(answer.equalsIgnoreCase("si")){
                for (int k = 0; k < habitacionesAniadidas; k++) {
                JOptionPane.showMessageDialog(null, "Dimensiones habitacion "
                        + "añadida:\n" + nombreHabAdicional[k] + "= " 
                        + metrosHabAdiconales[k] + " metros cuadrados.");
                }
            }
            
        boolean repetirPrograma = true;

        do {
            //imprimimos por pantalla los modos que tiene el programa
            int modo = ElijaOpcion();

            switch (modo) {
                case 1:
                    JOptionPane.showMessageDialog(null, "La carga actual de la"
                            + " aspiradora es:\n" + BATERIA + "%");
                    break;
                case 2:
                    JOptionPane.showMessageDialog(null, "Ha elegido la opcion "
                            + "Aspiracion.");
                    ModoAspiracion(metrosDependencias, metrosHabAdiconales, 
                            nombreDependencia, nombreHabAdicional);
                    break;
                case 3:
                    JOptionPane.showMessageDialog(null, "Ha elegido la opcion "
                            + "Aspiracion y fregado");
                    ModoAspiracionFregado(metrosDependencias, metrosHabAdiconales, 
                            nombreDependencia, nombreHabAdicional);
                    break;
                case 4:
                    JOptionPane.showMessageDialog(null, "Ha elegido la opcion "
                            + "Carga");
                    BATERIA =  Integer.parseInt(JOptionPane.showInputDialog(
                            "Introduzca bateria manualmente:"));
                    JOptionPane.showMessageDialog(null, "Carga actual:"
                            + BATERIA );
                    break;
                case 5:
                    JOptionPane.showMessageDialog(null, "Ha elegido la opcion "
                            + "Estado general");
                    estadoGeneral();
            }
            repetirPrograma = MensajeRepetir();
        } while (repetirPrograma);

    }

    public static void inicioDeSesion() {
        
        final String usuario = "usuario";
        final String contraseña = "usuario";
        boolean repetirInicio = true;
        do {
            String inicio = JOptionPane.showInputDialog("Nombre de usuario");
            String password = JOptionPane.showInputDialog("Contraseña");
            if (inicio.equals(usuario) && password.equals(contraseña)) {
                repetirInicio = false;
            } else {
                JOptionPane.showMessageDialog(null, "Usuario o contraseña "
                        + "incorrectos.\nIntente de nuevo.");
            }
        } while (repetirInicio);
    }

    public static int ElijaOpcion(){
        
        //mostramos por pantalla las opciones del programa
        String opcion = JOptionPane.showInputDialog("Seleccione modo:\n"
                + "1-Carga\n"
                + "2-Modo Aspiracion\n"
                + "3-Modo Aspiracion y fregado\n"
                + "4-Base de carga"
                + "5-Estado general");
        int modo = Integer.parseInt(opcion);
        return modo;
    }

    public static void ModoAspiracion(int[] metrosDependencias, 
            int[] metrosHabAdiconales, String[] nombreDependencia, 
            String[] nombreHabAdicional) {

        int metrosLimpiados = 0;
        double metrosTotalLimpiados=0;
        double bateriaNecesaria;
        final double DESGASTEBATERIA = 1.5, BATERIAMINIMA = 4, BATERIAMINHABAD = 5.5;

        int modo = Integer.parseInt(JOptionPane.showInputDialog("Seleccione modo"
                + " de limpieza:\n"
                + "1-Modo Completo\n"
                + "2-Modo dependencias"));

        switch (modo) {
            case 1:
                for (int i = 0; i < nombreDependencia.length; i++) {
                    
                    //calculamos si la aspiradora es capaz de limpiar dicha 
                    //habitacion
                    bateriaNecesaria = metrosDependencias[i] * DESGASTEBATERIA;
                    if(BATERIA > bateriaNecesaria){
                        JOptionPane.showMessageDialog(null, "La aspiradora va a "
                                + "limpiar la habiracion: "+ nombreDependencia[i] 
                                + "\nCarga:" + BATERIA);
                        
                        //
                        for (int limpiar = 0; limpiar < metrosDependencias[i]; limpiar++) {
                            if (BATERIA > BATERIAMINIMA) {
                                BATERIA = BATERIA - DESGASTEBATERIA;
                                metrosLimpiados++;
                            } else if(BATERIA==BATERIAMINIMA){
                                    metrosTotalLimpiados = metrosLimpiados + 0.25;
                            }else{
                                JOptionPane.showMessageDialog(null, "Bateria "
                                        + "insuficiente (" + BATERIA + ")\nVolviendo"
                                        + " a base para cargar...\nUltima habitacion"
                                        + " limpiada:" + nombreDependencia[i] + ".\n"
                                        + "Metros limpiados:" + metrosLimpiados 
                                        + "/" + metrosDependencias[i]);
                                break;
                            }
                        }
                        
                    }else{
                        JOptionPane.showMessageDialog(null, "No se pude limpiar "
                                + "esta habitacion.\nProbrando en la siguiente"
                                + " habitacion");
                    }
                }
                //calculamos si la aspiradora es capaz de limpiar dicha 
                //habitacion
                bateriaNecesaria = metrosHabAdiconales[0] * DESGASTEBATERIA;
                if (BATERIA > bateriaNecesaria) {
                    metrosLimpiados = 0;
                    for (int j = 0; j < nombreHabAdicional.length; j++) {
                        JOptionPane.showMessageDialog(null, "La aspiradora va "
                            + "a limpiar la habiracion: " + nombreHabAdicional[j]
                            + "\nCarga:" + BATERIA);
                        //calcualr condicion
                        //meter if    
                        for (int limpiar = 0; limpiar < metrosHabAdiconales[j]; limpiar++) {
                            if (BATERIA > BATERIAMINIMA) {
                                BATERIA = BATERIA - DESGASTEBATERIA;
                                metrosLimpiados++;
                            } else if(BATERIA==BATERIAMINIMA){
                                    metrosTotalLimpiados = metrosLimpiados + 0.25;
                            }else{
                                JOptionPane.showMessageDialog(null, "Bateria "
                                    + "insuficiente (" + BATERIA 
                                    + ")\nVolviendo a base para "
                                    + "cargar...\nUltima habitacion limpiada:"
                                    + nombreHabAdicional[j] + ".\n"
                                    + "Metros limpiados:" + metrosLimpiados 
                                    + "/" + metrosHabAdiconales[j]);
                                break;
                            }
                        }
                    }
                } else {
                    /*Si no tiene bateria suficeinte para meterse en el 
                    siguiente bucle(entrar en las habitaciones adicionales).
                    Por lo tanto se quedaria en la primera habitacion de las 
                    habitaciones adicionales*/
                    JOptionPane.showMessageDialog(null, "Bateria insuficiente ("
                        + BATERIA + ")\nVolviendo a base para cargar...\n"
                        + "Ultima habitacion limpiada:"
                        + nombreHabAdicional[0] + ".\n"
                        + "Metros limpiados:" + "0/" 
                        + metrosHabAdiconales[0]);
                    
                    break;
                }
                break;
            case 2:
                boolean repetirLimpiar=true;
                
                do{
                MensajeLimpiar();
                int habitacion =Integer.parseInt(JOptionPane.showInputDialog(
                        "¿Que habitaicon quiere limpiar?\n"
                        + "1-Habitaciones predeterminadas"
                        + "2-Habitaciones aniadidas")); 
                
                switch(habitacion){
                    case 1:
                        int habitacionDependencia =Integer.parseInt(
                            JOptionPane.showInputDialog("¿Que habitaicon quiere "
                            + "limpiar?\n"
                            + "1-Salon"
                            + "2-Cocina"
                            + "3-Baño"
                            + "4-Dprmitorio"));
                        
                        //calculamos si la aspiradora es capaz de limpiar dicha n  
                        //habitacion
                        bateriaNecesaria = metrosDependencias[habitacionDependencia]
                                * DESGASTEBATERIA;
                        if(BATERIA > bateriaNecesaria){
                            JOptionPane.showMessageDialog(null, "La aspiradora va a "
                                    + "limpiar la habiracion: "
                                    + nombreDependencia[habitacionDependencia] 
                                    + "\nCarga:" + BATERIA);

                            //
                            for (int limpiar = 0; limpiar < metrosDependencias[habitacionDependencia]; limpiar++) {
                                if (BATERIA > BATERIAMINIMA) {
                                    BATERIA = BATERIA - DESGASTEBATERIA;
                                    metrosLimpiados++;
                                } else if(BATERIA==BATERIAMINIMA){
                                    metrosTotalLimpiados = metrosLimpiados + 0.25;
                            }else{
                                    JOptionPane.showMessageDialog(null, "Bateria "
                                            + "insuficiente (" + BATERIA + ")\nVolviendo"
                                            + " a base para cargar...\nUltima habitacion"
                                            + " limpiada:" 
                                            + nombreDependencia[habitacionDependencia] 
                                            + ".\nMetros limpiados:"
                                            + metrosLimpiados + "/" 
                                            + metrosDependencias[habitacionDependencia]);
                                    break;
                                }
                            }

                        }else{
                            JOptionPane.showMessageDialog(null, "No se pude limpiar "
                                    + "esta habitacion.\nProbrando en la siguiente"
                                    + " habitacion");
                        }
                        break;
                    case 2:
                        int habitacionAdicional =Integer.parseInt(
                            JOptionPane.showInputDialog("¿Que habitaicon quiere "
                            + "limpiar?\n(0 - "
                            + nombreHabAdicional.length + ") habiraciones"));
                        
                        //calculamos si la aspiradora es capaz de limpiar dicha 
                        //habitacion
                        bateriaNecesaria = metrosHabAdiconales[habitacionAdicional]
                                * DESGASTEBATERIA;
                        if(BATERIA > bateriaNecesaria){
                            JOptionPane.showMessageDialog(null, "La aspiradora va a "
                                    + "limpiar la habiracion: "
                                    + nombreHabAdicional[habitacionAdicional] 
                                    + "\nCarga:" + BATERIA);

                            //
                            for (int limpiar = 0; limpiar < metrosDependencias[habitacionAdicional]; limpiar++) {
                                if (BATERIA > BATERIAMINIMA) {
                                    BATERIA = BATERIA - DESGASTEBATERIA;
                                    metrosLimpiados++;
                                } else if(BATERIA==BATERIAMINIMA){
                                    metrosTotalLimpiados = metrosLimpiados + 0.25;
                            }else{
                                    JOptionPane.showMessageDialog(null, "Bateria "
                                            + "insuficiente (" + BATERIA + ")\nVolviendo"
                                            + " a base para cargar...\nUltima habitacion"
                                            + " limpiada:" 
                                            + nombreHabAdicional[habitacionAdicional] 
                                            + ".\nMetros limpiados:"
                                            + metrosTotalLimpiados + "/" 
                                            + metrosDependencias[habitacionAdicional]);
                                    break;
                                }
                            }

                        }else{
                            JOptionPane.showMessageDialog(null, "No se pude limpiar "
                                    + "esta habitacion.\nProbrando en la siguiente"
                                    + " habitacion");
                        }
                        break;
                }
                
                }while(repetirLimpiar);
                break;

        }
    }

    public static void ModoAspiracionFregado(int[] metrosDependencias, 
            int[] metrosHabAdiconales, String[] nombreDependencia, 
            String[] nombreHabAdicional) {

        int metrosLimpiados = 0;
        double metrosTotalLimpiados=0;
        double bateriaNecesaria;
        final double DESGASTEBATERIA = 2.25, BATERIAMINIMA = 3.25, BATERIAMINHABAD = 5.5;

        String opcion = JOptionPane.showInputDialog("Seleccione modo de limpieza:\n"
                + "1-Modo Completo\n"
                + "2-Modo dependencias");

        int modo = Integer.parseInt(opcion);

        switch (modo) {
            case 1:
                for (int i = 0; i < nombreDependencia.length; i++) {
                    
                    //calculamos si la aspiradora es capaz de limpiar dicha 
                    //habitacion
                    bateriaNecesaria = metrosDependencias[i] * DESGASTEBATERIA;
                    if(BATERIA > bateriaNecesaria){
                        JOptionPane.showMessageDialog(null, "La aspiradora va a "
                                + "limpiar la habiracion: "+ nombreDependencia[i] 
                                + "\nCarga:" + BATERIA);
                        
                        //
                        for (int limpiar = 0; limpiar < metrosDependencias[i]; limpiar++) {
                            if (BATERIA > BATERIAMINIMA) {
                                BATERIA = BATERIA - DESGASTEBATERIA;
                                metrosLimpiados++;
                            } else if(BATERIA==BATERIAMINIMA){
                                    metrosTotalLimpiados = metrosLimpiados + 0.25;
                            }else{
                                JOptionPane.showMessageDialog(null, "Bateria "
                                        + "insuficiente (" + BATERIA + ")\nVolviendo"
                                        + " a base para cargar...\nUltima habitacion"
                                        + " limpiada:" + nombreDependencia[i] + ".\n"
                                        + "Metros limpiados:" + metrosTotalLimpiados 
                                        + "/" + metrosDependencias[i]);
                                break;
                            }
                        }
                        
                    }else{
                        JOptionPane.showMessageDialog(null, "No se pude limpiar "
                                + "esta habitacion.\nProbrando en la siguiente"
                                + " habitacion");
                    }
                }
                //calculamos si la aspiradora es capaz de limpiar dicha 
                //habitacion
                bateriaNecesaria = metrosHabAdiconales[0] * DESGASTEBATERIA;
                if (BATERIA > bateriaNecesaria) {
                    metrosLimpiados = 0;
                    for (int j = 0; j < nombreHabAdicional.length; j++) {
                        JOptionPane.showMessageDialog(null, "La aspiradora va "
                            + "a limpiar la habiracion: " + nombreHabAdicional[j]
                            + "\nCarga:" + BATERIA);
                        
                        
                        for (int limpiar = 0; limpiar < metrosHabAdiconales[j]; limpiar++) {
                            if (BATERIA > BATERIAMINIMA) {
                                BATERIA = BATERIA - DESGASTEBATERIA;
                                metrosLimpiados++;
                            } else if(BATERIA==BATERIAMINIMA){
                                    metrosTotalLimpiados = metrosLimpiados + 0.25;
                                }else {
                                JOptionPane.showMessageDialog(null, "Bateria "
                                    + "insuficiente (" + BATERIA 
                                    + ")\nVolviendo a base para "
                                    + "cargar...\nUltima habitacion limpiada:"
                                    + nombreHabAdicional[j] + ".\n"
                                    + "Metros limpiados:" + metrosTotalLimpiados 
                                    + "/" + metrosHabAdiconales[j]);
                                break;
                            }
                        }
                    }
                } else {
                    /*Si no tiene bateria suficeinte para meterse en el 
                    siguiente bucle(entrar en las habitaciones adicionales).
                    Por lo tanto se quedaria en la primera habitacion de las 
                    habitaciones adicionales*/
                    JOptionPane.showMessageDialog(null, "Bateria insuficiente ("
                        + BATERIA + ")\nVolviendo a base para cargar...\n"
                        + "Ultima habitacion limpiada:"
                        + nombreHabAdicional[0] + ".\n"
                        + "Metros limpiados:" + "0/" 
                        + metrosHabAdiconales[0]);
                    
                    break;
                }
                break;
            case 2:
                boolean repetir=true;
                
                do{
                MensajeLimpiar();
                int habitacion =Integer.parseInt(JOptionPane.showInputDialog(
                        "¿Que habitaicon quiere limpiar?\n"
                        + "1-Habitaciones predeterminadas"
                        + "2-Habitaciones aniadidas")); 
                
                switch(habitacion){
                    case 1:
                        int habitacionDependencia =Integer.parseInt(
                            JOptionPane.showInputDialog("¿Que habitaicon quiere "
                            + "limpiar?\n"
                            + "1-Salon"
                            + "2-Cocina"
                            + "3-Baño"
                            + "4-Dprmitorio"));
                        
                        //calculamos si la aspiradora es capaz de limpiar dicha 
                        //habitacion
                        bateriaNecesaria = metrosDependencias[habitacionDependencia]
                                * DESGASTEBATERIA;
                        if(BATERIA > bateriaNecesaria){
                            JOptionPane.showMessageDialog(null, "La aspiradora va a "
                                    + "limpiar la habiracion: "
                                    + nombreDependencia[habitacionDependencia] 
                                    + "\nCarga:" + BATERIA);

                            //
                            for (int limpiar = 0; limpiar < metrosDependencias[habitacionDependencia]; limpiar++) {
                                if (BATERIA > BATERIAMINIMA) {
                                    BATERIA = BATERIA - DESGASTEBATERIA;
                                    metrosLimpiados++;
                                } else if(BATERIA==BATERIAMINIMA){
                                    metrosTotalLimpiados = metrosLimpiados + 0.25;
                                }else{
                                    JOptionPane.showMessageDialog(null, "Bateria "
                                            + "insuficiente (" + BATERIA + ")\nVolviendo"
                                            + " a base para cargar...\nUltima habitacion"
                                            + " limpiada:" 
                                            + nombreDependencia[habitacionDependencia] 
                                            + ".\nMetros limpiados:"
                                            + metrosTotalLimpiados + "/" 
                                            + metrosDependencias[habitacionDependencia]);
                                    break;
                                }
                            }

                        }else{
                            JOptionPane.showMessageDialog(null, "No se pude limpiar "
                                    + "esta habitacion.\nProbrando en la siguiente"
                                    + " habitacion");
                        }
                        break;
                    case 2:
                        int habitacionAdicional =Integer.parseInt(
                            JOptionPane.showInputDialog("¿Que habitaicon quiere "
                            + "limpiar?\n(0 - "
                            + nombreHabAdicional.length + ") habiraciones"));
                        
                        //calculamos si la aspiradora es capaz de limpiar dicha 
                        //habitacion
                        bateriaNecesaria = metrosHabAdiconales[habitacionAdicional]
                                * DESGASTEBATERIA;
                        if(BATERIA > bateriaNecesaria){
                            JOptionPane.showMessageDialog(null, "La aspiradora va a "
                                    + "limpiar la habiracion: "
                                    + nombreHabAdicional[habitacionAdicional] 
                                    + "\nCarga:" + BATERIA);

                            //
                            for (int limpiar = 0; limpiar < metrosDependencias[habitacionAdicional]; limpiar++) {
                                if (BATERIA > BATERIAMINIMA) {
                                    BATERIA = BATERIA - DESGASTEBATERIA;
                                    metrosLimpiados++;
                                } else if(BATERIA==BATERIAMINIMA){
                                    metrosTotalLimpiados = metrosLimpiados + 0.25;
                                }else{
                                    JOptionPane.showMessageDialog(null, "Bateria "
                                            + "insuficiente (" + BATERIA + ")\nVolviendo"
                                            + " a base para cargar...\nUltima habitacion"
                                            + " limpiada:" 
                                            + nombreHabAdicional[habitacionAdicional] 
                                            + ".\nMetros limpiados:"
                                            + metrosTotalLimpiados + "/" 
                                            + metrosDependencias[habitacionAdicional]);
                                break;
                                }
                            }

                        }else{
                            JOptionPane.showMessageDialog(null, "No se pude limpiar "
                                    + "esta habitacion.\nProbrando en la siguiente"
                                    + " habitacion");
                        }
                        break;
                }
                
                }while(repetir);
                break;

        }
    }

    public static void estadoGeneral(){
        
    }
            
    public static boolean MensajeLimpiar() {
        boolean repiteLimpiar = true;

        int opcion = JOptionPane.showOptionDialog(null,
                "¿Quieres limpiar alguna habitacion?",
                "Elige",
                JOptionPane.YES_NO_OPTION,
                JOptionPane.QUESTION_MESSAGE,
                null,
                new Object[]{"SI", "NO"},
                null);

        if (opcion != 0) {
            repiteLimpiar = false;
        }

        return repiteLimpiar;
    }

    public static boolean MensajeRepetir() {
        boolean repite = true;

        int opcion = JOptionPane.showOptionDialog(null,
                "¿Quieres volver?",
                "Elige",
                JOptionPane.YES_NO_OPTION,
                JOptionPane.QUESTION_MESSAGE,
                null,
                new Object[]{"SI", "NO"},
                null);

        if (opcion != 0) {
            repite = false;
        }

        return repite;
    }

}
