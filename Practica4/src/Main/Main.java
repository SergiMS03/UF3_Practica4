/*
Fes un programa que permeti gestionar un fitxer binari de clients amb les
següents operacions:
X a) Alta d’un client (registrar un client que no existia abans al fitxer)
X b) Consulta d’un client per posició
X c) Consulta d’un client per codi
d) Modificar un client
e) Esborrar un client
X f) Llistat de tots els clients
 */
package Main;

import Utils.files;
import Utils.utils;
import java.io.File;
import java.io.IOException;
import java.text.ParseException;

/**
 *
 * @author SergiMS03 i marsanbla
 */
public class Main {

    static final String ADRECA = "./clients.dat";


    static class Client {
        int codi;
        String nom;
        String cognoms;
        int dia;
        int mes;
        int any;
        String adreca_postal;
        String email;
        boolean VIP;
    }

    /**
     * Crida a les altres funcions que fan funcionar el programa
     * @param args
     * @throws ParseException
     * @throws IOException 
     */
    public static void main(String[] args) throws ParseException, IOException{
        Client c = new Client();
        files.IfNotExistCreateFile(ADRECA);
        int opc;
        do {
            imprimirMenu();
            opc = elegirOpc(c);
        } while (opc != 0);
    }

    /**
     * Imprimeix el menu
     */
    private static void imprimirMenu() {
        System.out.println("1- Alta d'un client");
        System.out.println("2- Consulta posició");
        System.out.println("3- Consulta codi");
        System.out.println("4- Modificar un client");
        System.out.println("5- Esborrar client");
        System.out.println("6- Llistar tots els clients");
    }
    
    /**
     * Switch que esculleix la opció del menú i crida a les altres funcions
     * @param c
     * @return
     * @throws ParseException
     * @throws IOException 
     */
    private static int elegirOpc(Client c) throws ParseException, IOException {
        int opc = utils.LlegirIntLimitat("Escull una opció: ", 0, 6);
        switch (opc) {
            case 0:
                System.out.println("Tancant programa...");
                break;
            case 1:
                InserirClients.Dades_Client(c);
                InserirClients.Inserir(c, ADRECA);
                break;
            case 2:
                ConsultarClients.Pregunta_Consulta_Linea(c);
                break;
            case 3:
                ConsultarClients.Pregunta_Consulta_Codi(c);
                break;
            case 4:
                ModificarClients.Modificar_Client(c);
                break;

            case 5:
                EsborrarClients.Esborrar_Client(c);
                break;

            case 6:
                ConsultarClients.leerFichero(c);
                break;
        }
        return opc;
    }

    /**
     * Cambia els noms dels fitxers que es pasin
     * @param ADRECA
     * @param f 
     */
    static void Renombrar(String ADRECA, File f) {
        File client_Original = new File(ADRECA);
        client_Original.delete();
        f.renameTo(client_Original);
    }

}
