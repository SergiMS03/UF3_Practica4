/*
    Tot el codi relacionat amb modificar els clients, demanar codi per modificar, copiar, esborrar, etc
 */
package Main;

import Utils.utils;
import java.io.IOException;
import java.io.RandomAccessFile;

/**
 *
 * @author SergiMS03
 */
public class ModificarClients {

    
    static int Pregunta_Modificar_Client(Main.Client c) throws IOException {
        int modificar = utils.LlegirInt("Quin client vols modificar: ");
        ConsultarClients.Consultar_Codi(modificar, c);
        return modificar;
    }
    /**
     * demana quin client vols modificar i la linea concreta que vols modificar
     * @param c
     * @throws IOException 
     */
    static void Modificar_Client(Main.Client c) throws IOException {
        int quantitatClients = GestionIndex.num_Clients_Index();
        if(quantitatClients > 0){
            int modificar = Pregunta_Modificar_Client(c);
            Menu_Modificacio(c);
            long inici_registre = InserirClients.Inserir(c, Main.ADRECA);
            ModificarPosicioRegistre(modificar, c, inici_registre);
        }
        else{
            System.out.println("No hi han clients per modificar");
        }
    }

    /**
     * Menu que ens permét modificar dades d'un client fins que introduim opcio 0
     * @param c
     * @throws IOException 
     */
    static void Menu_Modificacio(Main.Client c) throws IOException {
        int MenuModificar = 0;
        do{
            menu_modificacio();
            MenuModificar = utils.LlegirIntLimitat("Quina dada vols modificar?", 0, 7);
            switch (MenuModificar){
                case 0: System.out.println("Client modificat correctament"); break;
                case 1: InserirClients.demanarCodi(c); break;
                case 2: c.nom = utils.LlegirString("Insereix el nom: "); break;
                case 3: c.cognoms = utils.LlegirString("Insereix el cognom: "); break;
                case 4: InserirClients.demanarData(c); break;
                case 5: c.adreca_postal = utils.LlegirString("Insereix adreça: "); break;
                case 6: c.email = utils.LlegirString("Insereix el mail: "); break;
                case 7: c.VIP = utils.LlegirBoolean("VIP (S/N): "); break;
            }
        }while(MenuModificar != 0);
    }

        /**
         * modifica la posició que has demanat
         * @param codiModificar
         * @param c
         * @param inici_registre
         * @throws IOException 
         */
        static void ModificarPosicioRegistre(int codiModificar, Main.Client c, long inici_registre) throws IOException {
        RandomAccessFile index = new RandomAccessFile(Main.ADRECA_INDEX, "rw");
        int quantitatClients = GestionIndex.num_Clients_Index();
        boolean actiu;
        int i = 0;
        int posicionCliente = 0;
        while ( i < quantitatClients) {
            actiu = index.readBoolean();
            int codi = index.readInt();
            index.readLong(); 
            if (codiModificar == codi && actiu) {
                i = Escribint_Modificacio(posicionCliente, index, c, inici_registre, i);
            }else if(!actiu){
                //Si esta esborrat no conta com client, no sumem
            }else{
                i++;
            }
            posicionCliente++;
        }
    }

    static int Escribint_Modificacio(int posicionCliente, RandomAccessFile index, Main.Client c, long inici_registre, int i) throws IOException {
        long posicion_mod = (posicionCliente * 13) + 1;//i (num vegades que hem fet el bucle) 13 (tamany de boolean + int + long) + 1 del bool del client que volem modificar
        index.seek(posicion_mod);
        index.writeInt(c.codi);
        index.writeLong(inici_registre);
        i++;
        return i;
    }
    //mostra el menu de la modificació
    private static void menu_modificacio() {
        System.out.println("1- Modificar Codi");
        System.out.println("2- Modificar Nom");
        System.out.println("3- Modificar Cognom");
        System.out.println("4- Modificar data");
        System.out.println("5- Modificar adreça");
        System.out.println("6- Modificar Mail");
        System.out.println("7-Modificar VIP");
        System.out.println("0- Sortir");
    }

}
