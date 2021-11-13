package com.modelo;
import java.security.*;
import javax.crypto.*;
import javax.crypto.spec.*;
import java.util.Base64;

public class Cifrado {
    private final String claveCifradoContra = "Pr0gr4Ma5i0N";
    

    public String getClaveCifradoContra() {
        return claveCifradoContra;
    }
    
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
    
    public String desencriptar(String escondido)throws Exception{
        String desencriptado = "";
        
        try{
            //Fuente confiable de numeros aleatorios
            SecureRandom sr = new SecureRandom();
            
            //Objeto DESKeySpec a partir de los datos clave originales
            DESKeySpec dks = new DESKeySpec(claveCifradoContra.getBytes());
            
            //Hacemos un objeto SecretKeyFactory que va a crear la clave a partir de nuestro objeto DESKeySpec
            SecretKeyFactory skf = SecretKeyFactory.getInstance("DES");
            SecretKey llave = skf.generateSecret(dks);
            
            Cipher descifrado = Cipher.getInstance("DES");
            descifrado.init(Cipher.DECRYPT_MODE, llave, sr);
            
            byte[] datoDES = Base64.getDecoder().decode(escondido);
            byte[] datoFinal = descifrado.doFinal(datoDES);
            
            desencriptado = new String(datoFinal, "UTF-8");
        }
        catch(Exception e){
            throw e;
        }
        
        return desencriptado;
    }
}
