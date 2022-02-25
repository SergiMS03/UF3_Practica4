/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Main;

import static Main.ConsultarClients.Llegir_Camps_Clients;
import static Main.Main.ADRECA_INDEX;
import Utils.files;
import java.io.DataInputStream;
import java.io.EOFException;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.RandomAccessFile;

/**
 *
 * @author ausias
 */
public class AccesoAleatorio {

    static class Index {

        int codi;
        long inici_registre;
    }

    public static void guardarRegistros(long inici_registre, int codi) throws FileNotFoundException, IOException {
        files.FileBinaryWriterInt(Main.ADRECA_INDEX, codi, true);
        files.FileBinaryWriterLong(Main.ADRECA_INDEX, inici_registre, true);
    }

    static int num_Clients_Index() throws FileNotFoundException, IOException {
        RandomAccessFile llegirClients = new RandomAccessFile(ADRECA_INDEX, "r");
        int numClients = 0;
        try {
            while (true) {
                int codi = llegirClients.readInt();
                long posByte = llegirClients.readLong();
                numClients++;

            }
        } catch (EOFException e) {
            //Final fitxer
        }
        return numClients;
    }

    static void Ordenar(Main.Client c) throws IOException {
        int numClients = num_Clients_Index();
        Index[] Index_Array = new Index[numClients];
        llenar_Index_Array(Index_Array);
        
           Index Index_Array_aux = new Index();
        for (int i = 0; i < Index_Array.length - 1; i++) {
            int minimo = i;
            for (int j = i; j < Index_Array.length; j++) {
                if (Index_Array[j].codi < Index_Array[minimo].codi) {
                    minimo = j;
                }
            }
           Index_Array_aux = Index_Array[i];
            Index_Array[i] = Index_Array[minimo];
            Index_Array[minimo] = Index_Array_aux;
        }

        for (int i = 0; i < Index_Array.length; i++) {
            ConsultarClients.Llegir_Camps_Clients(c,Index_Array[i].inici_registre);
            ConsultarClients.print_Clients(c);
        }

    }

    public static void llenar_Index_Array(Index Index_Array[]) throws FileNotFoundException, IOException {
        RandomAccessFile llegirClients = new RandomAccessFile(ADRECA_INDEX, "r");
        try {
            for (int i = 0; i < Index_Array.length; i++) {
                Index_Array[i] = new Index();
                Index_Array[i].codi = llegirClients.readInt();
                Index_Array[i].inici_registre = llegirClients.readLong();
            }
        } catch (EOFException e) {
            //Final fitxer
        }
        System.out.println("");
    }

}
