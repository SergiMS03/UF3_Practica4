/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Main;

import static Main.Main.ADRECA_INDEX;
import Utils.files;
import java.io.EOFException;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.RandomAccessFile;

/**
 *
 * @author ausias
 */
public class GestionIndex {

    /**
     * Insereix en el index el registre corresponent amb boolean true, codi del client i posicio del fitxer on escomença aquell client
     * @param inici_registre
     * @param codi
     * @throws FileNotFoundException
     * @throws IOException 
     */
    public static void guardarRegistros(long inici_registre, int codi) throws FileNotFoundException, IOException {
        files.FileBinaryWriterBoolean(ADRECA_INDEX, true, true);
        files.FileBinaryWriterInt(Main.ADRECA_INDEX, codi, true);
        files.FileBinaryWriterLong(Main.ADRECA_INDEX, inici_registre, true);
    }

    /**
     * Conta els clients actius que hi han al index, tots els clients fins el final del fitxer
     * @return
     * @throws FileNotFoundException
     * @throws IOException 
     */
    static int num_Clients_Index() throws FileNotFoundException, IOException {
        RandomAccessFile llegirClients = new RandomAccessFile(ADRECA_INDEX, "r");
        int numClients = 0;
        try {
            while (true) {
                boolean actiu = llegirClients.readBoolean();
                llegirClients.readInt();
                llegirClients.readLong();
                if(actiu){
                    numClients++;
                }
            }
        } catch (EOFException e) {
            //Final fitxer
        }
        return numClients;
    }


}
