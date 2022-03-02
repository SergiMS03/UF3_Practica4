/*
    Tot el codi relacionat amb modificar els clients, demanar codi per modificar, copiar, esborrar, etc
 */
package Main;

import Utils.utils;
import java.io.DataInputStream;
import java.io.EOFException;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;

/**
 *
 * @author SergiMS03
 */
public class ModificarClients {

    /**
     * Crida a les funcions necessaries per esborrar un client segons la linea
     * del fitxer
     *
     * @param c
     * @throws IOException
     */
    static void Modificar_Client(Main.Client c) throws IOException {
        
        int esborrar = utils.LlegirInt("Quin client vols esborrar: ");
        int MenuModificar= utils.LlegirIntLimitat("Quina dada vols modificar?", 1, 7);
        switch (MenuModificar) {
            case 1:
                break;
            case 2:
                break;
            case 3:
                break;
            case 4:
                break;
            case 5:
                break;
            case 6:
                break;
            case 7:
                break;
        }
        
    }
    private static void imprimirMenu() {
        System.out.println("1- Modificar Codi");
        System.out.println("2- Modificar Nom");
        System.out.println("3- Modificar Cognom");
        System.out.println("4- Modificar data");
        System.out.println("5- Modificar adreça");
        System.out.println("6- Modificar mail");
        System.out.println("7-Modificar Mail");
    }

}
