package Utils;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Llibreria d'utilitats
 *
 * @author SergiMS03
 *
 */
public class utils {

    private static Scanner scan = null;

    /**
     *
     * @return <h2>result torna amb un valor que s'ha llegit a través de
     * l'scanner</h2>
     */
    public static int LlegirInt() {
        int result;

        if (scan == null) {
            scan = new Scanner(System.in);
        }
        result = LlegirInt(scan);

        return result;
    }

    /**
     *
     * @param missatge
     * @return <h2>torna el nombre enter llegit per l'scanner</h2>
     */
    public static int LlegirInt(String missatge) {
        int result;

        if (scan == null) {
            scan = new Scanner(System.in);
        }
        result = LlegirInt(scan, missatge);

        return result;
    }

    /**
     *
     * @param scan <h2>es el valor que hem introduit avants en missatge
     * (llegirInt)</h2>
     * @return
     */
    public static int LlegirInt(Scanner scan) {
        return LlegirInt(scan, null);
    }

    /**
     *
     * @param scan
     * @param missatge
     * @param valorMin
     * @param valorMax
     * @return
     */
    public static int LlegirInt(Scanner scan, String missatge, int valorMin, int valorMax) {
        int result = 0;
        do {
            result = LlegirInt(scan, missatge);
        } while (result < valorMin || result > valorMax);

        return result;
    }

    /**
     *
     * @param scan
     * @param missatge
     * @return
     */
    public static int LlegirInt(Scanner scan, String missatge) {
        boolean dadesCorrectes;
        int result = 0;
        do {
            if (missatge != null) {
                System.out.print(missatge);
            }
            dadesCorrectes = scan.hasNextInt();
            if (dadesCorrectes) {
                result = scan.nextInt();
            } else if (scan.hasNext()) {
                scan.nextLine();
            }
        } while (!dadesCorrectes);

        return result;
    }

    public static int LlegirIntLimitat(String missatge, int limitMin, int limitMax) {
        Scanner scann = new Scanner(System.in);
        int result = 0;
        boolean correcte = false;
        while (!correcte) {
            System.out.print(missatge);
            if (scann.hasNextInt()) {
                result = scann.nextInt();
                if (result >= limitMin && result <= limitMax) {
                    correcte = true;
                }
            } else {
                scann.next();
            }
        }
        return result;
    }

    public static int LlegirIntLimitMinim(String missatge, int limitMin) {
        Scanner scann = new Scanner(System.in);
        int result = 0;
        boolean correcte = false;
        while (!correcte) {
            System.out.print(missatge);
            if (scann.hasNextInt()) {
                result = scann.nextInt();
                if (result >= limitMin) {
                    correcte = true;
                }
            } else {
                scann.next();
            }
        }
        return result;
    }

    public static int LlegirIntLimitMaxim(String missatge, int limitMax) {
        Scanner scann = new Scanner(System.in);
        int result = 0;
        boolean correcte = false;
        while (!correcte) {
            System.out.print(missatge);
            if (scann.hasNextInt()) {
                result = scann.nextInt();
                if (result <= limitMax) {
                    correcte = true;
                }
            } else {
                scann.next();
            }
        }
        return result;
    }

    public static double LlegirDouble(String missatge) {
        Scanner scann = new Scanner(System.in);
        double result = 0;
        boolean correcte = false;
        while (!correcte) {
            System.out.print(missatge);
            if (scann.hasNextDouble()) {
                result = scann.nextDouble();
                correcte = true;
            } else {
                scann.next();
            }
        }
        return result;
    }

    public static float LlegirFloat(String missatge) {
        Scanner scann = new Scanner(System.in);
        float result = 0;
        boolean correcte = false;
        while (!correcte) {
            System.out.print(missatge);
            if (scann.hasNextFloat()) {
                result = scann.nextFloat();
                correcte = true;
            } else {
                scann.next();
            }
        }
        return result;
    }

    public static String LlegirString(String missatge) {
        String result;
        System.out.print(missatge);
        Scanner scann = new Scanner(System.in);
        result = scann.nextLine();
        return result;
    }

    public static String LlegirStringMaxCaracters(String missatge, int tamany) {
        String result;
        System.out.print(missatge);
        Scanner scann = new Scanner(System.in);
        do{
        result = scann.nextLine();
        }while(result.length() > tamany);
        return result;
    }

    public static String LlegirDataString(String missatge) {
        boolean correcte = false;
        SimpleDateFormat formatter = new SimpleDateFormat("ddMMyyyy");
        String DateString = null;
        while(!correcte){
            DateString = utils.LlegirString(missatge);
            try {
                Date date = formatter.parse(DateString);
                correcte = true;
            } catch (ParseException ex) {
                correcte = false;
            }
        }
        return DateString;
    }
    
    public static boolean LlegirBoolean(String missatge){
        Scanner scann = new Scanner(System.in);
        String resposta;
        boolean result = false;
        boolean correcte = false;
        while (!correcte) {
            System.out.print(missatge);
            resposta = scann.nextLine().toLowerCase();
            if(resposta.equals("si") || resposta.equals("s")){
                result = true;
                correcte = true;
            }
            else if(resposta.equals("no") || resposta.equals("n")){
                result = false;
                correcte = true;
            }
        }
        return result;
    }
    
    public static boolean validarFecha(int dia, int mes, int anio, String missatge) {
    boolean correcto = false;
    try {
        //Formato de fecha (día/mes/año)
        SimpleDateFormat formatoFecha = new SimpleDateFormat("dd/MM/yyyy");
        formatoFecha.setLenient(false);
        //Comprobación de la fecha
        formatoFecha.parse(dia + "/" + mes + "/" + anio);
        correcto = true;
    } catch (ParseException e) {
        System.out.println(missatge);
        correcto = false;
    }

    return correcto;
}
    
}


    



