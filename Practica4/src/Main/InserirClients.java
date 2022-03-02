/*
    Tot el codi relacionat amb inserir clients i validar també dades inserides
 */
package Main;

import Utils.files;
import Utils.utils;
import java.io.DataInputStream;
import java.io.EOFException;
import java.io.FileInputStream;
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
        int numClients = AccesoAleatorio.num_Clients_Index();
        boolean actiu;
        long posicio_inici;
        int codi;
        for (int j = 0; j < numClients; j++) {
            actiu = index.readBoolean();
            if (actiu) {
                codi = index.readInt();
                posicio_inici = index.readLong();
                ConsultarClients.Llegir_Camps_Clients(c, posicio_inici);
            }

            if (codiComprovar == c.codi) {
                trobat = true;
            }
        }
        return trobat;
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
     * @throws IOException
     */
    static void Inserir(Main.Client c, String ADRECA) throws IOException {
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
        AccesoAleatorio.guardarRegistros(inici_registre, c.codi);
    }

}
