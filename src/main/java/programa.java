
//importamos las librerias que vasmoa a utilizar para este programa
import java.text.SimpleDateFormat;
import java.util.Date;
import javax.swing.JOptionPane;

public class Programa {

    //cramos una bateria predeterminada de carazter double en el caso de que no
    //se añada ninguna bateria de manera manual
    public static double BATERIA = 100;

    public static void main(String[] args) {

        inicioDeSesion();

        //declaracion de variables necesarias para el proyecto
        int habitacionesAniadidas = 0;
        int[] metrosDependencias = new int[4];
        int[] metrosHabAdiconales;
        String[] nombreDependencia = {"Cocina", "Salon", "Baño",
            "Dormitorio"};
        String[] nombreHabAdicional;

        //Anunciamos la configuracion predeterminada
        String answer = JOptionPane.showInputDialog(null, "Estan "
                + "predeterminadas"
                + " las habitaciones: Cocina, salon, cuarto de baño"
                + " y "
                + " dormitorio.\n"
                + "¿Quiere añadir alguna mas?");
        if (answer.equalsIgnoreCase("si")) {
            String respuestaHabtaciones = JOptionPane.showInputDialog(
                    "¿Cuantas habitaciones quiere añadir?");
            habitacionesAniadidas = Integer.parseInt(respuestaHabtaciones);

        }

        //declaramos la lonjitud de los arrays "nombreHabAdicional" y 
        // "metrsoHabAdicionales"
        metrosHabAdiconales = new int[habitacionesAniadidas];
        nombreHabAdicional = new String[habitacionesAniadidas];

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
                    nombreHabAdicional[j] = JOptionPane.showInputDialog(
                            "¿Nombre de la habitacion adicional"
                            + (j + 1) + "?");
                    String metrosHabAniadidas = JOptionPane.showInputDialog(
                            "Dimensiones de la habitacion: "
                            + nombreHabAdicional[j]);
                    metrosHabAdiconales[j] = Integer.parseInt(metrosHabAniadidas);

            }
        }

        //creamos un bucle para el programa
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
                            nombreDependencia, nombreHabAdicional, answer);
                    break;
                case 3:
                    JOptionPane.showMessageDialog(null, "Ha elegido la opcion "
                            + "Aspiracion y fregado");
                    ModoAspiracionFregado(metrosDependencias, metrosHabAdiconales,
                            nombreDependencia, nombreHabAdicional, answer);
                    break;
                case 4:
                    JOptionPane.showMessageDialog(null, "Ha elegido la opcion "
                            + "Carga");
                    BATERIA = Integer.parseInt(JOptionPane.showInputDialog(
                            "Introduzca bateria manualmente:"));
                    JOptionPane.showMessageDialog(null, "Carga actual:"
                            + BATERIA);
                    break;
                case 5:
                    JOptionPane.showMessageDialog(null, "Ha elegido la opcion "
                            + "Estado general");
                    estadoGeneral(metrosDependencias, metrosHabAdiconales,
                            nombreDependencia, nombreHabAdicional, answer,
                            habitacionesAniadidas);
                case 6:

                    break;
            }
            if (modo == 6) {
                repetirPrograma = MensajeRepetir();//actualizacion del bucle para salir o no del programa
            }
        } while (repetirPrograma);

    }

    public static void inicioDeSesion() {

        //declaramos que para iniciar sesion los credenciales deben ser usuario en ambos campos
        final String usuario = "usuario";
        final String contraseña = "usuario";
        boolean repetirInicio = true;
        //bucle mientras que los credenciales sean incorrectos vuelva a pedir que iniciemos secion correctamente
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

    public static int ElijaOpcion() {

        //mostramos por pantalla las opciones del programa
        String opcion = JOptionPane.showInputDialog("Seleccione modo:\n"
                + "1-Carga\n"
                + "2-Modo Aspiracion\n"
                + "3-Modo Aspiracion y fregado\n"
                + "4-Base de carga\n"
                + "5-Estado general\n"
                + "6-Salir del programa");
        int modo = Integer.parseInt(opcion);
        //devolvemos un int con el modo que quermos
        return modo;
    }

    //para esta funcion necesitamos los 4 arrays y la respuesta de si queremos aniadir habitaciones
    public static void ModoAspiracion(int[] metrosDependencias,
            int[] metrosHabAdiconales, String[] nombreDependencia,
            String[] nombreHabAdicional, String answer) {

        //bariables necesarias para la fincion
        int metrosLimpiados = 0;
        double metrosTotalLimpiados = 0;
        double bateriaNecesaria;
        final double DESGASTEBATERIA = 1.5, BATERIAMINIMA = 4, BATERIAMINHABAD = 5.5;

        int modo = Integer.parseInt(JOptionPane.showInputDialog("Seleccione modo"
                + " de limpieza:\n"
                + "1-Modo Completo\n"
                + "2-Modo dependencias"));

        switch (modo) {
            case 1:

                for (int i = 0; i < nombreDependencia.length; i++) {

                    //mostramos por pantalla la habtacion que se va a limpiar
                    JOptionPane.showMessageDialog(null, "La aspiradora va a "
                            + "limpiar la habitación: " + nombreDependencia[i]
                            + "\nCarga:" + BATERIA);

                    //calculamos si la aspiradora es capaz de limpiar dicha 
                    //habitacion
                    bateriaNecesaria = metrosDependencias[i] * DESGASTEBATERIA;
                    if (BATERIA > bateriaNecesaria) {//condicion para limpiar la hab

                        //bucle para limpiar metro a metro la casa
                        for (int limpiar = 0; limpiar < metrosDependencias[i]; limpiar++) {

                            //actualizacion de la bateria
                            BATERIA = BATERIA - DESGASTEBATERIA;
                        }
                        //Mensaje de completado
                        JOptionPane.showMessageDialog(null, "Habitación limpiada\n"
                                + "Bateria restante: " + BATERIA);
                    } else {
                        JOptionPane.showMessageDialog(null, "No se pude limpiar "
                                + "esta habitacion.\nProbrando en la siguiente"
                                + " habitacion...");
                    }
                }
                if (answer.equalsIgnoreCase("si")) {
                    //bucle para limpiar habitacion tras habitacion
                    for (int i = 0; i < nombreHabAdicional.length; i++) {

                        //mostramos por pantalla la habtacion que se va a limpiar
                        JOptionPane.showMessageDialog(null, "La aspiradora va a "
                                + "limpiar la habitación: " + nombreHabAdicional[i]
                                + "\nCarga:" + BATERIA);

                        //calculamos si la aspiradora es capaz de limpiar dicha 
                        //habitacion
                        bateriaNecesaria = metrosHabAdiconales[i] * DESGASTEBATERIA;
                        if (BATERIA > bateriaNecesaria) {//condicion para limpiar la hab

                            //bucle para limpiar metro a metro la habitacion
                            for (int limpiar = 0; limpiar < metrosHabAdiconales[i]; limpiar++) {

                                //actualizacion de la bateria
                                BATERIA = BATERIA - DESGASTEBATERIA;
                            }

                            JOptionPane.showMessageDialog(null, "Habitación limpiada\n"
                                    + "Bateria restante: " + BATERIA);
                        } else {
                            JOptionPane.showMessageDialog(null, "No se pude limpiar "
                                    + "esta habitacion.\nProbrando en la siguiente"
                                    + " habitacion...");
                        }
                    }
                }
                break;

            case 2:

                boolean repetir;
                do {
                    repetir = MensajeLimpiar();

                    if (repetir == true) {

                        int habitacion = Integer.parseInt(JOptionPane.showInputDialog(
                                "¿Que habitaicon quiere limpiar?\n"
                                + "1-Habitaciones predeterminadas"
                                + "2-Habitaciones aniadidas"));

                        switch (habitacion) {
                            case 1:
                                int habitacionDependencia = Integer.parseInt(
                                        JOptionPane.showInputDialog("¿Que habitaicon quiere "
                                                + "limpiar?\n"
                                                + "1-Salon"
                                                + "2-Cocina"
                                                + "3-Baño"
                                                + "4-Dormitorio"));

                                //calculamos si la aspiradora es capaz de limpiar dicha 
                                //habitacion
                                bateriaNecesaria = metrosDependencias[habitacionDependencia]
                                        * DESGASTEBATERIA;
                                if (BATERIA > bateriaNecesaria) {
                                    JOptionPane.showMessageDialog(null, "La aspiradora va a "
                                            + "limpiar la habiracion: "
                                            + nombreDependencia[habitacionDependencia]
                                            + "\nCarga:" + BATERIA);

                                    //bucle para limpiar metro a metro la habitaicon
                                    for (int limpiar = 0; limpiar < metrosDependencias[habitacionDependencia]; limpiar++) {

                                        //actualizacion de la bateria
                                        BATERIA = BATERIA - DESGASTEBATERIA;

                                    }
                                    JOptionPane.showMessageDialog(null, "Habitación limpiada\n"
                                            + "Bateria restante: " + BATERIA);
                                } else {
                                    JOptionPane.showMessageDialog(null, "No se pude limpiar "
                                            + "esta habitacion.\nProbrando en la siguiente"
                                            + " habitacion");
                                }
                                break;
                            case 2:
                                if (answer.equalsIgnoreCase("si")) {
                                    int habitacionAdicional = Integer.parseInt(
                                            JOptionPane.showInputDialog("¿Que habitaicon quiere "
                                                    + "limpiar?\n(0 - "
                                                    + nombreHabAdicional.length + ") habiraciones"));

                                    //calculamos si la aspiradora es capaz de limpiar dicha 
                                    //habitacion
                                    bateriaNecesaria = metrosHabAdiconales[habitacionAdicional]
                                            * DESGASTEBATERIA;
                                    if (BATERIA > bateriaNecesaria) {
                                        JOptionPane.showMessageDialog(null, "La aspiradora va a "
                                                + "limpiar la habiracion: "
                                                + nombreHabAdicional[habitacionAdicional]
                                                + "\nCarga:" + BATERIA);

                                        for (int limpiar = 0; limpiar < metrosDependencias[habitacionAdicional]; limpiar++) {

                                            //actualizacion de la bateria
                                            BATERIA = BATERIA - DESGASTEBATERIA;

                                        }
                                        JOptionPane.showMessageDialog(null, "Habitación limpiada\n"
                                                + "Bateria restante: " + BATERIA);

                                    } else {
                                        JOptionPane.showMessageDialog(null, "No se pude limpiar "
                                                + "esta habitacion.\nProbrando en la siguiente"
                                                + " habitacion");
                                    }
                                } else {
                                    JOptionPane.showMessageDialog(null, "No hay "
                                            + "habitaciones adicionales "
                                            + "(Solo habitacione predeterminadas).");
                                }
                                break;
                        }
                    }

                } while (repetir);
                break;

        }
        //fin de la funcion
        JOptionPane.showMessageDialog(null, "Modo limpieza acabado");

    }

    //para esta funcion necesitamos los 4 arrays y la respuesta de si queremos aniadir habitaciones
    public static void ModoAspiracionFregado(int[] metrosDependencias,
            int[] metrosHabAdiconales, String[] nombreDependencia,
            String[] nombreHabAdicional, String answer) {

        double bateriaNecesaria;
        final double DESGASTEBATERIA = 2.25, BATERIAMINIMA = 3.25, BATERIAMINHABAD = 5.5;

        String opcion = JOptionPane.showInputDialog("Seleccione modo de limpieza:\n"
                + "1-Modo Completo\n"
                + "2-Modo dependencias");

        int modo = Integer.parseInt(opcion);

        switch (modo) {
            case 1:

                for (int i = 0; i < nombreDependencia.length; i++) {

                    //mostramos por pantalla la habtacion que se va a limpiar
                    JOptionPane.showMessageDialog(null, "La aspiradora va a "
                            + "limpiar la habitación: " + nombreDependencia[i]
                            + "\nCarga:" + BATERIA);

                    //calculamos si la aspiradora es capaz de limpiar dicha 
                    //habitacion
                    bateriaNecesaria = metrosDependencias[i] * DESGASTEBATERIA;
                    if (BATERIA > bateriaNecesaria) {//condicion para limpiar la hab

                        //bucle para limpiar metro a metro la casa
                        for (int limpiar = 0; limpiar < metrosDependencias[i]; limpiar++) {

                            //actualizacion de la bateria
                            BATERIA = BATERIA - DESGASTEBATERIA;
                        }
                        //Mensaje de completado
                        JOptionPane.showMessageDialog(null, "Habitación limpiada\n"
                                + "Bateria restante: " + BATERIA);
                    } else {
                        JOptionPane.showMessageDialog(null, "No se pude limpiar "
                                + "esta habitacion.\nProbrando en la siguiente"
                                + " habitacion...");
                    }
                }
                if (answer.equalsIgnoreCase("si")) {
                    for (int i = 0; i < nombreHabAdicional.length; i++) {

                        //mostramos por pantalla la habtacion que se va a limpiar
                        JOptionPane.showMessageDialog(null, "La aspiradora va a "
                                + "limpiar la habitación: " + nombreHabAdicional[i]
                                + "\nCarga:" + BATERIA);

                        //calculamos si la aspiradora es capaz de limpiar dicha 
                        //habitacion
                        bateriaNecesaria = metrosHabAdiconales[i] * DESGASTEBATERIA;
                        if (BATERIA > bateriaNecesaria) {//condicion para limpiar la hab

                            //bucle para limpiar metro a metro la casa
                            for (int limpiar = 0; limpiar < metrosHabAdiconales[i]; limpiar++) {

                                //actualizacion de la bateria
                                BATERIA = BATERIA - DESGASTEBATERIA;
                            }

                            JOptionPane.showMessageDialog(null, "Habitación limpiada\n"
                                    + "Bateria restante: " + BATERIA);
                        } else {
                            JOptionPane.showMessageDialog(null, "No se pude limpiar "
                                    + "esta habitacion.\nProbrando en la siguiente"
                                    + " habitacion...");
                        }
                    }
                }
                break;
            case 2:
                boolean repetir;
                do {
                    repetir = MensajeLimpiar();

                    if (repetir == true) {

                        int habitacion = Integer.parseInt(JOptionPane.showInputDialog(
                                "¿Que habitaicon quiere limpiar?\n"
                                + "1-Habitaciones predeterminadas"
                                + "2-Habitaciones aniadidas"));

                        switch (habitacion) {
                            case 1:
                                int habitacionDependencia = Integer.parseInt(
                                        JOptionPane.showInputDialog("¿Que habitaicon quiere "
                                                + "limpiar?\n"
                                                + "1-Salon"
                                                + "2-Cocina"
                                                + "3-Baño"
                                                + "4-Dormitorio"));

                                //calculamos si la aspiradora es capaz de limpiar dicha 
                                //habitacion
                                bateriaNecesaria = metrosDependencias[habitacionDependencia]
                                        * DESGASTEBATERIA;
                                if (BATERIA > bateriaNecesaria) {
                                    JOptionPane.showMessageDialog(null, "La aspiradora va a "
                                            + "limpiar la habiracion: "
                                            + nombreDependencia[habitacionDependencia]
                                            + "\nCarga:" + BATERIA);

                                    //
                                    for (int limpiar = 0; limpiar < metrosDependencias[habitacionDependencia]; limpiar++) {

                                        //actualizacion de la bateria
                                        BATERIA = BATERIA - DESGASTEBATERIA;

                                    }
                                    JOptionPane.showMessageDialog(null, "Habitación limpiada\n"
                                            + "Bateria restante: " + BATERIA);
                                } else {
                                    JOptionPane.showMessageDialog(null, "No se pude limpiar "
                                            + "esta habitacion.\nProbrando en la siguiente"
                                            + " habitacion");
                                }
                                break;
                            case 2:
                                //condicion para que haya habitaciones es que hayamos querido aniadir habitaciones adiciionales
                                if (answer.equalsIgnoreCase("si")) {
                                    int habitacionAdicional = Integer.parseInt(
                                            JOptionPane.showInputDialog("¿Que habitaicon quiere "
                                                    + "limpiar?\n(0 - "
                                                    + nombreHabAdicional.length + ") habiraciones"));

                                    //calculamos si la aspiradora es capaz de limpiar dicha 
                                    //habitacion
                                    bateriaNecesaria = metrosHabAdiconales[habitacionAdicional]
                                            * DESGASTEBATERIA;
                                    if (BATERIA > bateriaNecesaria) {
                                        JOptionPane.showMessageDialog(null, "La aspiradora va a "
                                                + "limpiar la habiracion: "
                                                + nombreHabAdicional[habitacionAdicional]
                                                + "\nCarga:" + BATERIA);

                                        //
                                        for (int limpiar = 0; limpiar < metrosDependencias[habitacionAdicional]; limpiar++) {

                                            //actualizaxcion de bateria
                                            BATERIA = BATERIA - DESGASTEBATERIA;

                                        }
                                        JOptionPane.showMessageDialog(null, "Habitación limpiada\n"
                                                + "Bateria restante: " + BATERIA);

                                    } else {
                                        JOptionPane.showMessageDialog(null, "No se pude limpiar "
                                                + "esta habitacion.\nProbrando en la siguiente"
                                                + " habitacion");
                                    }
                                } else {
                                    JOptionPane.showMessageDialog(null, "No hay "
                                            + "habitaciones adicionales "
                                            + "(Solo habitacione predeterminadas).");
                                }
                                break;
                        }
                    }

                } while (repetir);
                break;
        }
        //fin de la funcion
        JOptionPane.showMessageDialog(null, "Modo limpieza acabado");
    }

    //para esta funcion necesitamos los 4 arrays y el numero de habitaciones aniadidas
    public static void estadoGeneral(int[] metrosDependencias,
            int[] metrosHabAdiconales, String[] nombreDependencia,
            String[] nombreHabAdicional, String answer, int habitacionesAniadidas) {

        //definimos las fechas
        Date fecha = new Date();
        Date hora = new Date();

        //creamos el forato que queremos
        SimpleDateFormat formatoFecha = new SimpleDateFormat("dd/MM/yyyy");
        SimpleDateFormat formatoHora = new SimpleDateFormat("HH:mm:ss");

        //imprimimos por pantalla la fecha y la hora
        JOptionPane.showMessageDialog(null, "Hoy es: "
                + formatoFecha.format(fecha)
                + "\nHora: "
                + formatoHora.format(hora));

        int metrosTotalesAdicionales = 0;//creamos una variable para las hab aniadidas
        int metrosTotalesCasa;

        //mostramos por pantalla las dimensiones de la casa
        JOptionPane.showMessageDialog(null, "Dimensiones de la casa:\n"
                + nombreDependencia[0] + "= " + metrosDependencias[0]
                + " metros cuadrados." + "\n"
                + nombreDependencia[1] + "= " + metrosDependencias[1]
                + " metros cuadrados." + "\n"
                + nombreDependencia[2] + "= " + metrosDependencias[2]
                + " metros cuadrados." + "\n"
                + nombreDependencia[3] + "= " + metrosDependencias[3]
                + " metros cuadrados.");

        if (answer.equalsIgnoreCase("si")) {
            for (int k = 0; k < habitacionesAniadidas; k++) {
                JOptionPane.showMessageDialog(null, "Dimensiones habitacion "
                        + "añadida:\n" + nombreHabAdicional[k] + "= "
                        + metrosHabAdiconales[k] + " metros cuadrados.");
                metrosTotalesAdicionales = metrosTotalesAdicionales
                        + metrosHabAdiconales[k];//actilizaciond e variable
            }
        }

        //operacion para calcular el total de metrosd e la casa
        metrosTotalesCasa = metrosDependencias[0] + metrosDependencias[1]
                + metrosDependencias[2] + metrosDependencias[3]
                + metrosTotalesAdicionales;
        JOptionPane.showMessageDialog(null, "Dimension total de la casa:\n"
                + metrosTotalesCasa + " m2.");
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
                "¿Quieres salir?",
                "Elige",
                JOptionPane.YES_NO_OPTION,
                JOptionPane.QUESTION_MESSAGE,
                null,
                new Object[]{"SI", "NO"},
                null);

        if (opcion != 1) {
            repite = false;
        }

        return repite;
    }

}
