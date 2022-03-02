/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Main;

import java.io.EOFException;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.RandomAccessFile;

/**
 *
 * @author SergiMS03
 */
public class Ordenacions {

    static class Index {
        boolean actiu;
        int codi;
        long inici_registre;
    }
    
    /**
     * Ordena el index en memoria
     * @param c
     * @throws IOException 
     */
    static void Ordenar(Main.Client c) throws IOException {
        int numClients = GestionIndex.num_Clients_Index();
        Index[] Index_Array = new Index[numClients];
        llenar_Index_Array(Index_Array);
        for (int i = 0; i < Index_Array.length - 1; i++) {
            int minimo = i;
            for (int j = i; j < Index_Array.length; j++) {
                if (Index_Array[j].codi < Index_Array[minimo].codi) {
                    minimo = j;
                }
            }
            Moure_Variables_Index(Index_Array, i, minimo);
        }
        Mandar_Imprimir_Clientes(Index_Array, c);
    }

    /**
     * Imprimeix els clients que estan actius
     * @param Index_Array
     * @param c
     * @throws IOException 
     */
    static void Mandar_Imprimir_Clientes(Index[] Index_Array, Main.Client c) throws IOException {
        for (int i = 0; i < Index_Array.length; i++) {
            ConsultarClients.Llegir_Camps_Clients(c, Index_Array[i].inici_registre);
            ConsultarClients.print_Clients(c);
        }
        System.out.println("");
    }
    
    /**
     * Mou les posicions de l'array per que el index quedi ordenat per codi de més petit a més gran
     * @param Index_Array
     * @param i
     * @param minimo 
     */
    static void Moure_Variables_Index(Index[] Index_Array, int i, int minimo) {
        Index Index_Array_aux;
        Index_Array_aux = Index_Array[i];
        Index_Array[i] = Index_Array[minimo];
        Index_Array[minimo] = Index_Array_aux;
    }

    /**
     * Insereix totes les dades de Index a l'array
     * @param Index_Array
     * @throws FileNotFoundException
     * @throws IOException 
     */
    public static void llenar_Index_Array(Index[] Index_Array) throws FileNotFoundException, IOException {
        RandomAccessFile llegirClients = new RandomAccessFile(Main.ADRECA_INDEX, "r");
        try {
            int i = 0;
            while (i < Index_Array.length) {
                Index_Array[i] = new Index();
                Index_Array[i].actiu = llegirClients.readBoolean();
                if (Index_Array[i].actiu) {
                    Index_Array[i].codi = llegirClients.readInt();
                    Index_Array[i].inici_registre = llegirClients.readLong();
                    i++;
                } else {
                    llegirClients.readInt();
                    llegirClients.readLong();
                }
            }
        } catch (EOFException e) {
            //Final fitxer
        }
    }
    
}
