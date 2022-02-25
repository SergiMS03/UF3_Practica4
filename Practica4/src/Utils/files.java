package Utils;

import java.io.BufferedReader;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;

/**
 *
 * @author SergiMS03
 */
public class files {

    /*Variables Globales*/
    static FileReader reader;
    static BufferedReader buffer;
    static FileWriter writer;
    static PrintWriter pw;
    static FileOutputStream fos;
    static DataOutputStream dos;
    /*static FileInputStream fis;
    static DataInputStream dis;*/

    /*Variables Globales*/

 /*Se utilizan variables globales para declarar FileReader, BufferedReader, FileWriter, PrintWriter segun la direccion del fichero.*/
    /**
     *
     * @param adress
     * @throws FileNotFoundException
     */
    private static void declare_FR_BR(String adress) throws FileNotFoundException {
        reader = new FileReader(adress);
        buffer = new BufferedReader(reader);
    }

    /**
     *
     * @param adress
     * @param dontOverwrite
     * @throws FileNotFoundException
     * @throws IOException
     */
    private static void declare_FW_PW(String adress, boolean dontOverwrite) throws FileNotFoundException, IOException {
        writer = new FileWriter(adress, dontOverwrite);
        pw = new PrintWriter(writer);
    }

    /**
     * 
     * @param adress
     * @param dontOverwrite
     * @throws FileNotFoundException
     * @throws IOException 
     */
    private static void declare_FOS_DOS(String adress, boolean dontOverwrite) throws FileNotFoundException, IOException {
        fos = new FileOutputStream(adress, dontOverwrite);
        dos = new DataOutputStream(fos);
    }
    
    /*private static void declare_FIS_DIS(String adress) throws FileNotFoundException, IOException {
        fis = new FileInputStream(adress);
        dis = new DataInputStream(fis);
    }*/
    
    /*---------------------------------------------------------------------------------------------------------------------------*/

    /**
     * Llama declare_FR_BR, una vez tenemos reader y buffer lee el fichero
     * entero
     *
     * @param adress
     * @throws FileNotFoundException
     * @throws IOException
     */
    public static void FileReader(String adress) throws FileNotFoundException, IOException {
        declare_FR_BR(adress);
        String line = buffer.readLine();
        while (line != null) {//Lee hasta que el contenido és NULL
            System.out.println(line);
            line = buffer.readLine();
        }
        reader.close();
        System.out.println("");
    }

    /**
     * Llama declare_FR_BR, una vez tenemos reader y buffer lee el fichero
     * entero, pero solo se guarda y imprime la linea que le hemos pedido
     *
     * @param adress
     * @param message
     * @param defaultResult
     * @throws FileNotFoundException
     * @throws IOException
     */
    public static void FileLineReader(String adress, String message, String defaultResult) throws FileNotFoundException, IOException {
        int llegirLinea = utils.LlegirInt(message);
        declare_FR_BR(adress);
        String line = defaultResult;
        int currentLine = 1;//El fichero empieza en la linea 1, si se colocara un 0 el fichero empezaria en la linea 0 y así consecutivamente
        do {
            if (currentLine == (llegirLinea)) {//Guarda solo la linea que pedimos
                line = buffer.readLine();
            }
            currentLine++;
        } while (buffer.readLine() != null);//Lee hasta que el contenido és NULL
        System.out.println(line);
        System.out.println("");
    }

    /**
     * Llama declare_FW_PW y una vez tenemos el writer i pw, pw.print imprime el
     * mensaje en el archivo que hemos indicado anteriormente
     *
     * @param adress
     * @param message
     * @param dontOverwrite
     * @throws IOException
     */
    public static void FileWriter(String adress, String message, boolean dontOverwrite) throws IOException {
        declare_FW_PW(adress, dontOverwrite);
        pw.println(message);
        pw.flush();
        writer.close();
    }

    public static void FileWriterOneLine(String adress, String message, boolean dontOverwrite) throws IOException {
        declare_FW_PW(adress, dontOverwrite);
        pw.print(message);
        pw.flush();
        writer.close();
    }

    /**
     * Comprueba si el fichero indicado existe
     *
     * @param adress
     * @throws IOException
     */
    public static void IfNotExistCreateFile(final String adress) throws IOException {
        File f = new File(adress);
        if (!f.exists()) {
            f.createNewFile();
        }
    }
    
    public static void FileBinaryWriterInt(final String adress, int num, boolean dontOverwrite) throws IOException {
        declare_FOS_DOS(adress, dontOverwrite);
        dos.writeInt(num);
        dos.flush();
        dos.close();
    }
    
    public static void FileBinaryWriterLong(final String adress, long num, boolean dontOverwrite) throws IOException {
        declare_FOS_DOS(adress, dontOverwrite);
        dos.writeLong(num);
        dos.flush();
        dos.close();
    }
    
    public static void FileBinaryWriterString(final String adress, String message, boolean dontOverwrite) throws IOException {
        declare_FOS_DOS(adress, dontOverwrite);
        dos.writeUTF(message);
        dos.flush();
        dos.close();
    }
    
    public static void FileBinaryWriterBoolean(final String adress, boolean bool, boolean dontOverwrite) throws IOException {
        declare_FOS_DOS(adress, dontOverwrite);
        dos.writeBoolean(bool);
        dos.flush();
        dos.close();
    }
    
    public static int FileBinaryReaderInt(final String adress, DataInputStream dis) throws IOException {
        //declare_FIS_DIS(adress);
        int readingInt = dis.readInt();
        return readingInt;
    }
    
    public static long FileBinaryReaderLong(final String adress, DataInputStream dis) throws IOException {
        //declare_FIS_DIS(adress);
        long readingLong = dis.readLong();
        return readingLong;
    }
    
    public static String FileBinaryReaderString(final String adress, DataInputStream dis) throws IOException {
        //declare_FIS_DIS(adress);
        String readingString = dis.readUTF();
        return readingString;
    }
    
    public static boolean FileBinaryReaderBoolean(final String adress, DataInputStream dis) throws IOException {
        boolean readingBool = dis.readBoolean();
        return readingBool;
    }
    

}
