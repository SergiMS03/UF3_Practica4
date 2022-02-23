/*
    Tot el codi relacionat amb esborrar clients
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
public class EsborrarClients {

    /**
     * Crida a les funcions necessaries per esborrar un client segons la linea del fitxer
     * @throws IOException 
     */
    static void Esborrar_Client(Main.Client c) throws IOException {
        int esborrar = utils.LlegirInt("Quin client vols esborrar: ");
        final String ADRECA_AUX = "./auxiliar.dat";
        File f = new File(ADRECA_AUX);
        f.createNewFile();
        CopiantIEsborrant(esborrar, c, ADRECA_AUX);
        Main.Renombrar(Main.ADRECA, f);
    }

    /**
     * Copia totes les lineas a un fitxer auxiliar.dat menys la que volem borrar
     * @param codiEsborrar
     * @param c
     * @param adreca
     * @throws IOException 
     */
    static void CopiantIEsborrant(int codiEsborrar, Main.Client c, String adreca) throws IOException {
        FileInputStream fis = new FileInputStream(Main.ADRECA);
        DataInputStream dis = new DataInputStream(fis);
        try{
            while(true){
                ConsultarClients.Llegir_Camps_Clients(c, dis);
                if(c.codi != codiEsborrar){
                    InserirClients.Inserir(c, adreca);
                }
            }
        }catch(EOFException e){
            //Final fitxer
        }
    }
}
