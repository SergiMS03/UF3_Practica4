/*
    Tot el codi relacionat amb Consultes, tant especifiques com generals
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
public class ConsultarClients {

    /**
     * Pregunta el codi del client que es vol consultar
     *
     * @throws IOException
     */
    static void Pregunta_Consulta_Codi(Main.Client c) throws IOException {
        int codiConsulta = utils.LlegirInt("Quin es el codi que vols consultar: ");
        Consultar_Codi(codiConsulta, c);
        System.out.println("");
    }

    /**
     * Pregunta la linea del fitxer que es vol consultar
     *
     * @param c
     * @throws IOException
     */
    /*static void Pregunta_Consulta_Linea(Main.Client c) throws IOException {
        int numClients = Cantidad_Clientes(c);
        if (numClients > 0) {
            System.out.println(numClients + " lineas disponibles");
            int lineaConsulta = utils.LlegirIntLimitat("Quina és la linea que vols consultar: ", 1, numClients);
            Consultar_Linea(lineaConsulta, c);
        }
        System.out.println("");
    }*/

    /**
     * Busca el codi del client que es volia consultar i crida a una funció que
     * seguit els imprimirà per pantalla
     *
     * @param buffer
     * @param codiConsulta
     * @throws IOException
     */
    static void Consultar_Codi(int codiConsulta, Main.Client c) throws IOException {
        RandomAccessFile file = new RandomAccessFile(Main.ADRECA, "rw");
        int i = 1;
        try {
            while (true) {
                Llegir_Camps_Clients(c,i);
                if (c.codi == codiConsulta) {
                    print_Clients(c);
                    i++;
                }
            }
        } catch (EOFException e) {
            //Final fitxer
        }
    }

    /**
     * Busca quants clients hi ha al fitxer que es volia consultar i crida a una
     * funció que seguit els imprimirà per pantalla
     *
     * @param lineaConsulta
     * @param c
     * @throws FileNotFoundException
     * @throws IOException
     */
    private static void Consultar_Linea(int lineaConsulta, Main.Client c) throws FileNotFoundException, IOException {
           RandomAccessFile file = new RandomAccessFile(Main.ADRECA, "rw");
        try {
            int contador = 1;
            int i = 1;
            while (true) {
                Llegir_Camps_Clients(c,i);
                if (contador == lineaConsulta) {
                    print_Clients(c);
                    i++;
                }
                contador++;
            }
        } catch (EOFException e) {
            //Final fitxer
        }
    }

    /*
     * Llegeix del fitxer fins que no hi ha més contingut i surt a través del catch
     * @param c
     * @throws FileNotFoundException
     * @throws IOException 
     */
    static void leerFichero(Main.Client c) throws FileNotFoundException, IOException {
        RandomAccessFile file = new RandomAccessFile(Main.ADRECA, "rw");
        
        int i = 1;
        try {
            while (true) {
                file.seek(i);
                Llegir_Camps_Clients(c,i);
                print_Clients(c);
                i++;
            }
        } catch (EOFException e) {
            //Final fitxer
        }
        System.out.println("");
    }

    /**
     * Guarda a la clase clients cada camp consusultat a la seva respectiva
     * variable
     *
     * @param c
     * @param dis
     * @throws IOException
     */
    static void Llegir_Camps_Clients(Main.Client c, long inici_registre) throws IOException {
        RandomAccessFile file = new RandomAccessFile(Main.ADRECA, "rw");
        file.seek(inici_registre);
        c.codi = file.readInt();
        c.nom = file.readUTF();
        c.cognoms = file.readUTF();
        c.dia = file.readInt();
        c.mes = file.readInt();
        c.any = file.readInt();
        c.adreca_postal =file.readUTF();
        c.email = file.readUTF();
        c.VIP = file.readBoolean();
    }

    /**
     * Imprimeix el que hi hagi guardad a les variables de la clase Client
     *
     * @param c
     */
    static void print_Clients(Main.Client c) {
        System.out.print(c.codi + " ");
        System.out.print(c.nom + " ");
        System.out.print(c.cognoms + " ");
        System.out.print(c.dia + " ");
        System.out.print(c.mes + " ");
        System.out.print(c.any + " ");
        System.out.print(c.adreca_postal + " ");
        System.out.print(c.email + " ");
        System.out.print(c.VIP + " ");
        System.out.println(" ");
    }

    /**
     * Conta la cuantitat de clients que hi ha al fitxer
     *
     * @param c
     * @return
     * @throws IOException
     * @throws FileNotFoundException
     */
    static int Cantidad_Clientes(Main.Client c) throws IOException, FileNotFoundException {
      RandomAccessFile file = new RandomAccessFile(Main.ADRECA, "rw");
        int numClients = 0;
        try {
            int i = 1;
            while (true) {
                Llegir_Camps_Clients(c,i);
                numClients++;
                i++;
            }
        } catch (EOFException e) {
            //Final fitxer
        }
        return numClients;
    }
}
