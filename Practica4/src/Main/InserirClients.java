/*
    Tot el codi relacionat amb inserir clients i validar també dades inserides
 */
package Main;

import Utils.files;
import Utils.utils;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.RandomAccessFile;

/**
 *
 * @author SergiMS03
 */
public class InserirClients {

    /**
     * Demana les dades dels clients per afegir i crida a algunes funcions per
     * validar les dades
     *
     * @param c
     * @param adreca
     * @throws IOException
     */
    static void Dades_Client(Main.Client c) throws IOException {
        demanarCodi(c);
        c.nom = utils.LlegirString("Nom: ");
        c.cognoms = utils.LlegirString("Cognom: ");
        demanarData(c);
        c.adreca_postal = utils.LlegirString("Adreça postal: ");
        c.email = utils.LlegirString("EMail: ");
        c.VIP = utils.LlegirBoolean("Vip (S/N): ");
    }

    /**
     * Demana codi cada cop que el codi inserit ja existeix
     *
     * @param c
     * @throws FileNotFoundException
     * @throws IOException
     */
    static void demanarCodi(Main.Client c) throws FileNotFoundException, IOException {
        boolean trobat = true;
        int codiComprovar = 0;
        while (trobat) {
            codiComprovar = utils.LlegirInt("Codi: ");
            trobat = validarCodi(codiComprovar, c);
        }
        c.codi = codiComprovar;
    }

    /**
     * Comprova que el codiComprovar sigui diferent a els diferents codis que hi
     * ha estan als fitxers, si no existeixen dades al fitxer no comprova
     *
     * @param codiComprovar
     * @param c
     * @return
     * @throws FileNotFoundException
     * @throws IOException
     */
    private static boolean validarCodi(int codiComprovar, Main.Client c) throws FileNotFoundException, IOException {
        //RandomAccessFile file = new RandomAccessFile(Main.ADRECA, "rw");
        RandomAccessFile index = new RandomAccessFile(Main.ADRECA_INDEX, "rw");
        boolean trobat = false;
        int numClients = GestionIndex.num_Clients_Index();
        boolean actiu;
        //long posicio_inici;
        int j = 0;
        while (j < numClients && !trobat) {
            actiu = index.readBoolean();
            j = Comparacio_Codis(actiu, c, index, j);
            if (codiComprovar == c.codi) {
                trobat = true;
            }
        }
        return trobat;
    }

    /**
     * S'encarrega de comparar els codis que es pasen a validar codi
     * @param actiu
     * @param c
     * @param index
     * @param j
     * @return
     * @throws IOException 
     */
    static int Comparacio_Codis(boolean actiu, Main.Client c, RandomAccessFile index, int j) throws IOException {
        long posicio_inici;
        if (actiu) {
            c.codi = index.readInt();
            posicio_inici = index.readLong();
            ConsultarClients.Llegir_Camps_Clients(c, posicio_inici);
            j++;
        }
        else{
            index.readInt();
            index.readLong();
        }
        return j;
    }

    /**
     * Demana dia, mes i any i pasa per el validador del utils, si torna true,
     * està bé, si no es torna a preguntar dia, mes i any
     *
     * @param c
     */
    static void demanarData(Main.Client c) {
        boolean correcto = false;
        while (!correcto) {
            c.dia = utils.LlegirInt("Dia naixement:");
            c.mes = utils.LlegirInt("Mes naixement:");
            c.any = utils.LlegirInt("Any naixement:");
            correcto = utils.validarFecha(c.dia, c.mes, c.any, "La data escrita no és valida");
        }
    }

    /**
     * Insereix les dades que hi han guardades a la clase client que s'ha
     * omplert avants a Dades_Client
     *
     * @param c
     * @param ADRECA
     * @return 
     * @throws IOException
     */
    public static long Inserir(Main.Client c, String ADRECA) throws IOException {
        RandomAccessFile file = new RandomAccessFile(Main.ADRECA, "rw");
        long inici_registre = file.length();
        file.seek(file.length());
        files.FileBinaryWriterInt(ADRECA, c.codi, true);
        files.FileBinaryWriterString(ADRECA, c.nom, true);
        files.FileBinaryWriterString(ADRECA, c.cognoms, true);
        files.FileBinaryWriterInt(ADRECA, c.dia, true);
        files.FileBinaryWriterInt(ADRECA, c.mes, true);
        files.FileBinaryWriterInt(ADRECA, c.any, true);
        files.FileBinaryWriterString(ADRECA, c.adreca_postal, true);
        files.FileBinaryWriterString(ADRECA, c.email, true);
        files.FileBinaryWriterBoolean(ADRECA, c.VIP, true);
        System.out.println("");
        return inici_registre;
    }

}
