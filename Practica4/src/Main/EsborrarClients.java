/*
    Tot el codi relacionat amb esborrar clients
 */
package Main;

import static Main.ConsultarClients.Llegir_Camps_Clients;
import Utils.files;
import Utils.utils;
import java.io.DataInputStream;
import java.io.EOFException;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.RandomAccessFile;

/**
 *
 * @author SergiMS03
 */
public class EsborrarClients {

    /**
     * Crida a les funcions necessaries per esborrar un client segons la linea
     * del fitxer
     *
     * @throws IOException
     */
    static void Esborrar_Client(Main.Client c) throws IOException {
        int esborrar = utils.LlegirInt("Quin client vols esborrar: ");
        final String ADRECA_AUX = "./auxiliar.dat";
        File f = new File(ADRECA_AUX);
        f.createNewFile();
        Esborrar(esborrar, c, ADRECA_AUX);
    }

    /**
     * Copia totes les lineas a un fitxer auxiliar.dat menys la que volem borrar
     *
     * @param codiEsborrar
     * @param c
     * @param adreca
     * @throws IOException
     */
    static void Esborrar(int codiEsborrar, Main.Client c, String adreca) throws IOException {
        RandomAccessFile index = new RandomAccessFile(Main.ADRECA_INDEX, "rw");
        int quantitatClients = AccesoAleatorio.num_Clients_Index();
        boolean actiu;
        long posByte = 0;
        int i = 0;
        int posicionCliente = 0;
        while ( i < quantitatClients) {
            actiu = index.readBoolean();
            int codi = index.readInt();
            posByte = index.readLong(); 
            if (codiEsborrar == codi && actiu) {
                long posicion_Bool = posicionCliente * 13;//i (num vegades que hem fet el bucle) 13 (tamany de boolean + int + long)
                index.seek(posicion_Bool);
                index.writeBoolean(false);
                i++;
            }else if(!actiu){
                //Si esta esborrat no conta com client, no sumem
            }else{
                i++;
            }
            posicionCliente++;
        }
    }
}
