
//importamos las librerias que vasmoa a utilizar para este programa
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
                repetirPrograma = MensajeRepetir();
            }
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
        return modo;
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
