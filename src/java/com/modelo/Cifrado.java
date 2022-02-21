package com.modelo;
import java.security.*;
import javax.crypto.*;
import javax.crypto.spec.*;
import java.util.Base64;

/**
 * 
 * @author IrminDev
 * 
 * Clase cifrado con métodos para realizar un cifrado y descifrado apartir del algoritmo DES
 */

public class Cifrado {
    //Declaramos la variable que será la clave que se usará para el cifrado
    private final String claveCifradoContra = "Pr0gr4Ma5i0N";
    
    //Método para encriptar una cadena de caracteres con cifrado DES
    public String encriptar(String texto)throws Exception{
        //Declaranos lo que nos va a regresar
        String nuevaCad = "";
        try{
           //Fuente confiable de numeros aleatorios
           SecureRandom sr = new SecureRandom();
           //Objeto DESKeySpec a partir de los datos de nuestra clave
           DESKeySpec dks = new DESKeySpec(claveCifradoContra.getBytes());
           //Crea claves a partir de objetos DESKeySpec
           SecretKeyFactory skf = SecretKeyFactory.getInstance("DES");
           
           //Generamos nuestra clave secreta
           SecretKey key1 = skf.generateSecret(dks);
           
           //El objeto cifrado se crea para poder finalizar la tarea de cifrado
           Cipher cifrado = Cipher.getInstance("DES");
           
           //Inicializamos el objeto con la clave y la fuente de numeros aleatorios
           cifrado.init(Cipher.ENCRYPT_MODE , key1, sr);
           
           //Obtenemos los bytes cifrados del dato original
           byte datoCifrado[] = cifrado.doFinal(texto.getBytes("UTF-8"));
           
           //Codificamos el dato a través de base 64
           nuevaCad = Base64.getEncoder().encodeToString(datoCifrado);
        }
        catch(Exception e){
            throw e;
        }
        
        return nuevaCad;
    }
    
    //Método para descifrar cadenas cifradas con el algoritmo DES
    public String desencriptar(String escondido)throws Exception{
        //Declaramos el string qu contendrá la cadena descifrada
        String desencriptado = "";
        
        try{
            //Fuente confiable de numeros aleatorios
            SecureRandom sr = new SecureRandom();
            
            //Objeto DESKeySpec a partir de los datos clave originales
            DESKeySpec dks = new DESKeySpec(claveCifradoContra.getBytes());
            
            //Hacemos un objeto SecretKeyFactory que va a crear la clave a partir de nuestro objeto DESKeySpec
            SecretKeyFactory skf = SecretKeyFactory.getInstance("DES");
            SecretKey llave = skf.generateSecret(dks);
            
            //Instanciamos la clase Cipher con el algoritmo DES
            Cipher descifrado = Cipher.getInstance("DES");
            
            //El objeto de la clase cipher lo inicializamos con modo de descifrar, la secretKey y una fuente confiable de números aleatorios
            descifrado.init(Cipher.DECRYPT_MODE, llave, sr);
            
            //Creamos un array de bytes dónde primero desciframos el texto que se encriptó en base 64
            byte[] datoDES = Base64.getDecoder().decode(escondido);
            //El array de bytes del texto nuevo ahora lo desciframos con el algoritmo DES
            byte[] datoFinal = descifrado.doFinal(datoDES);
            
            //A la cadena que contendrá el texto desencriptado será ese array de bytes completamente desenciptado, y usaremos la codificación UTF-8 para caracteres especiales
            desencriptado = new String(datoFinal, "UTF-8");
        }
        catch(Exception e){
            //Tiramos los errores si es que hubo uno en el proceso
            throw e;
        }
        
        //Retornamos el texto desencriptado
        return desencriptado;
    }
}
