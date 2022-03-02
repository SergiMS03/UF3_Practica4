/*
    Tot el codi relacionat amb Consultes, tant especifiques com generals
 */
package Main;

import Utils.utils;
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
    static void Pregunta_Consulta_Linea(Main.Client c) throws IOException {
        int quantitatClients = GestionIndex.num_Clients_Index();
        if (quantitatClients > 0) {
            System.out.println(quantitatClients + " lineas disponibles");
            int lineaConsulta = utils.LlegirIntLimitat("Quina és la linea que vols consultar: ", 1, quantitatClients);
            Consultar_Linea(lineaConsulta, c, quantitatClients);
        }
        System.out.println("");
    }
    /**
     * Busca el codi del client que es volia consultar i crida a una funció que
     * seguit els imprimirà per pantalla
     *
     * @param buffer
     * @param codiConsulta
     * @throws IOException
     */
    static void Consultar_Codi(int codiConsulta, Main.Client c) throws IOException {
        RandomAccessFile index = new RandomAccessFile(Main.ADRECA_INDEX, "rw");
        int quantitatClients = GestionIndex.num_Clients_Index();
        long posByte = -1;
        int i = 0;
        while (i < quantitatClients) {
            boolean actiu = index.readBoolean();
            int codi = index.readInt();
            if (codiConsulta == codi && actiu) {
                posByte = index.readLong();
                i++;
            }else if(!actiu){
                long readLong = index.readLong();
            }
            else{
                index.readLong();
                i++;
            }
        }
        Resultado_Consulta(posByte, c);
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
    private static void Consultar_Linea(int lineaConsulta, Main.Client c, int quantitatClients) throws FileNotFoundException, IOException {
        RandomAccessFile index = new RandomAccessFile(Main.ADRECA_INDEX, "rw");
        long posByte = -1;
        int i = 1;
        while (i <= quantitatClients) {
            boolean actiu = index.readBoolean();
            index.readInt();
            if (lineaConsulta == i && actiu) {
                posByte = index.readLong();
                i++;
            }else if(!actiu){
                index.readLong();
            }
            else{
                index.readLong();
                i++;
            }
        }
        Resultado_Consulta(posByte, c);
    }

    /**
     * Comprova si s'ha trobat resultat, i depenent d'aixó treu un missatge o imprimeix el client que es buscaba
     * @param posByte
     * @param c
     * @throws IOException 
     */
    static void Resultado_Consulta(long posByte, Main.Client c) throws IOException {
        if(posByte == -1){
            System.out.println("No s'ha trobat la linea que es buscaba");
        }
        else{
            Llegir_Camps_Clients(c, posByte);
            print_Clients(c);
        }
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
        c.adreca_postal = file.readUTF();
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

}
