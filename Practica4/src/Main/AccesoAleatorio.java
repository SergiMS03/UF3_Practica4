/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Main;

import static Main.ConsultarClients.Llegir_Camps_Clients;
import Utils.files;
import java.io.DataInputStream;
import java.io.EOFException;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;

/**
 *
 * @author ausias
 */
public class AccesoAleatorio {
    
    static class Index{
        int codi;
        long inici_registre;
    }
    
    public static void guardarRegistros(long inici_registre, int codi) throws FileNotFoundException, IOException{
        files.FileBinaryWriterInt(Main.ADRECA_INDEX, codi, true);
        files.FileBinaryWriterLong(Main.ADRECA_INDEX, inici_registre, true);
    }

    static int num_Clients_Index() throws FileNotFoundException, IOException {
        FileInputStream fis = new FileInputStream(Main.ADRECA);
        DataInputStream dis = new DataInputStream(fis);
        int numClients = 0;
        try{
            while(true){
                files.FileBinaryReaderInt(Main.ADRECA_INDEX, dis);
                files.FileBinaryReaderLong(Main.ADRECA_INDEX, dis);
                numClients++;
            }
        }catch(EOFException e){
            //Final fitxer
        }
        return numClients;
    }
    
    static void Ordenar(Main.Client c) throws IOException{
        int numClients = num_Clients_Index();
        Index [] Index_Array = new Index [numClients];
        llenar_Index_Array(Index_Array);
        int n = Index_Array.length;  
         for(int i=0; i < n; i++){  
                 for(int j=1; j < (n-i); j++){  
                          if(Index_Array[j-1].codi > Index_Array[j].codi){  
                                 //swap elements  
                                 Index Index_Array_Aux = new Index();
                                 Index_Array_Aux = Index_Array[j-1];  
                                 Index_Array[j-1] = Index_Array[j];  
                                 Index_Array[j] = Index_Array_Aux;  
                         }  
                          
                 }  
         }
        FileInputStream fis = new FileInputStream(Main.ADRECA);
        DataInputStream dis = new DataInputStream(fis);
        for (int i = 0; i < Index_Array.length; i++) {
            ConsultarClients.Llegir_Camps_Clients(c, dis, Index_Array[i].inici_registre);
            ConsultarClients.print_Clients(c);
        }
        
    }
    
    public static void llenar_Index_Array(Index Index_Array []) throws FileNotFoundException, IOException {
        FileInputStream fis = new FileInputStream(Main.ADRECA);
        DataInputStream dis = new DataInputStream(fis);
        try{
            for (int i = 0; i < Index_Array.length; i++) {
                Index_Array[i] = new Index();
                Index_Array[i].codi = files.FileBinaryReaderInt(Main.ADRECA_INDEX, dis);
                Index_Array[i].inici_registre = files.FileBinaryReaderInt(Main.ADRECA_INDEX, dis);
            }
        }catch(EOFException e){
            //Final fitxer
        }
        System.out.println("");
    }
    
    
}
