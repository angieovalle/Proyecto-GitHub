package cliente;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.util.ArrayList;
import java.util.Properties;
import java.util.Scanner;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class OperacionDB {

    /* USUARIOS */
    /*public static Usuario obtenerDatosUsuario(int serie_cliente, String login) throws Exception {
     Socket socket = obtenerConexion();
     ObjectOutputStream salida = new ObjectOutputStream(socket.getOutputStream());
     ObjectInputStream entrada = new ObjectInputStream(socket.getInputStream());
     salida.writeObject("LOGIN_USER");
     System.out.println("SE ENVIO: LOGIN_USER");
     salida.writeObject(serie_cliente);
     System.out.println("SE ENVIO: " + serie_cliente);
     salida.writeObject(login);
     System.out.println("SE ENVIO: " + login);
     Usuario usuario = (Usuario) entrada.readObject();
     System.out.println("SE RECIBIO: " + usuario);
     socket.close();
     return usuario;
     }*/
    public static int cambiarPassword(int serie_cliente, String pwd_anterior, String pwd_nuevo) throws Exception {
        Socket socket = obtenerConexion();
        ObjectOutputStream salida = new ObjectOutputStream(socket.getOutputStream());
        ObjectInputStream entrada = new ObjectInputStream(socket.getInputStream());
        salida.writeObject("CHANGE_USERPASSWORD");
        System.out.println("SE ENVIO: CHANGE_USERPASSWORD");
        salida.writeObject(serie_cliente);
        System.out.println("SE ENVIO: " + serie_cliente);
        salida.writeObject(pwd_anterior);
        System.out.println("SE ENVIO: " + pwd_anterior);
        salida.writeObject(pwd_nuevo);
        System.out.println("SE ENVIO: " + pwd_nuevo);
        int r = (Integer) entrada.readObject();
        socket.close();
        return r;
    }
    public static ObjectInputStream envioDatosMultiSms(ArrayList<String> Tabla) throws Exception {
        Socket socket = obtenerConexion();
        ObjectOutputStream salida = new ObjectOutputStream(socket.getOutputStream());
        ObjectInputStream entrada = new ObjectInputStream(socket.getInputStream());
        salida.writeObject("SEND_MULTI");
        salida.writeObject(Tabla);
        socket.close();
        return entrada;
    }
    
    public static void envioDatosSms(JSONObject numero,JSONObject sms,JSONObject cliente) throws Exception {
        Socket socket = obtenerConexion();
        ObjectOutputStream salida=new ObjectOutputStream(socket.getOutputStream());
        ObjectInputStream entrada = new ObjectInputStream(socket.getInputStream());
        salida.writeObject("SEND_ONE");
        salida.writeObject(numero);
        salida.writeObject(sms);
        salida.writeObject(cliente);
        socket.close();
        //return null;
    }
    //se pone dentro de envioMultiSMS.jsp
    public static ArrayList<String> leerArchivo() {
        ArrayList<String> Tabla = new ArrayList<String>();
        String ruta="D:\\datos.csv";
        try {
            FileReader fr = new FileReader(ruta);
            BufferedReader br = new BufferedReader(fr);

            String linea;
            while ((linea = br.readLine()) != null) {
                System.out.println(linea);
                Tabla.add(linea);
            }fr.close();
        } catch (Exception e) {
            System.out.println("Excepcion leyendo Archivo   " + e);
        }
        return Tabla;
    }
 private static Socket obtenerConexion() throws Exception {
        Properties prop = new Properties();
        FileInputStream archivo = new FileInputStream("D:\\Smscliente-test\\Smscliente.properties");
        prop.load(archivo);
        archivo.close();
        Socket socket = new Socket("localhost",12002);
        socket.setSoTimeout(20000);
        //Socket socket = new Socket(prop.getProperty("HOST"), Integer.parseInt(prop.getProperty("BIND_PORT")));
        //socket.setSoTimeout(Integer.parseInt(prop.getProperty("TIMEOUT")));
        return socket;
    }


}
