package cliente;

import java.io.File;
import java.util.ArrayList;
/**
 * 
 * @author Angie Catherine
 */
public class cliente {
     
     public static void main(String arg[]) throws Exception {
         
        ArrayList<String> Tabla = OperacionDB.leerArchivo();
         
         OperacionDB.envioDatosMultiSms(Tabla);
    }
    
    
}
