/*
Fes un programa que permeti gestionar un fitxer binari de clients amb les
següents operacions:
xa) Alta d’un client (registrar un client que no existia abans al fitxer)
xb) Consulta d’un client per posició b) a) Accedeixi de forma directa a un registre segons la seva posició

xc) Consulta d’un client per codi b) Accedeixi de forma directa a un registre segons el seu codi
d) Modificar un client d) No hagi de reconstruir el fitxer quan es modifica un registre
e) Esborrar un client c) No hagi de reconstruir el fitxer quan s’esborra un registre

xf) Llistat de tots els clients e) Llisti els clients ordenats per codi
*/
package Main;

import Utils.files;
import Utils.utils;
import java.io.IOException;
import java.text.ParseException;

/**
 *
 * @author SergiMS03 i marsanbla
 */
public class Main {
//rutes on es guarden els fitxers
    public static final String ADRECA = "./clients.dat";
    public static final String ADRECA_INDEX = "./index.dat";

  
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
     *
     * @param args
     * @throws ParseException
     * @throws IOException
     */
    public static void main(String[] args) throws ParseException, IOException {
        Client c = new Client();
        files.IfNotExistCreateFile(ADRECA);
        files.IfNotExistCreateFile(ADRECA_INDEX);
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
        System.out.println("6- Llistar tots els clients ordenats per codi");
    }

    /**
     * Switch que esculleix la opció del menú i crida a les altres funcions
     *
     * @param c
     * @return
     * @throws ParseException
     * @throws IOException
     */
    private static int elegirOpc(Client c) throws ParseException, IOException {
        int opc = utils.LlegirIntLimitat("Escull una opció: ", 0, 7);
        switch (opc) {
            case 0:
                System.out.println("Tancant programa...");
                break;
            case 1:
                InserirClients.Dades_Client(c);
                long inici_registre = InserirClients.Inserir(c, ADRECA);
                GestionIndex.guardarRegistros(inici_registre, c.codi);
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
                EsborrarClients.Pregunta_Esborrar_Client(c);
                break;
            case 6:
                Ordenacions.Ordenar(c);
                break;
        }
        return opc;
    }
}
