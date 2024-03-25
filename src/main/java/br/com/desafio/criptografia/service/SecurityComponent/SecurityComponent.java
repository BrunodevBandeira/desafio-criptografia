package br.com.desafio.criptografia.service.SecurityComponent;

import javax.crypto.Cipher;
import javax.crypto.spec.SecretKeySpec;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import java.util.Base64;

//A interface PasswordEncoder define métodos para codificar e verificar senhas
@Component
public class SecurityComponent implements PasswordEncoder{

    @Value("${key.value}")
    private String SECRET_KEY;

    @Override
    public String encode(CharSequence rawPassword) {
        
        System.out.println("=> 01 " + SECRET_KEY);
        System.out.println("=> 02 " + rawPassword);
        // O método encode é usado para codificar uma senha, por exemplo, para armazená-la de forma segura no banco de dados.
        try {
            Cipher cipher = Cipher.getInstance("AES/ECB/PKCS5Padding"); //O código obtém uma instância do Cipher com o algoritmo AES/ECB/PKCS5Padding.
            System.out.println("=> 03 " + cipher);

            SecretKeySpec secretKey = new SecretKeySpec(SECRET_KEY.getBytes(), "AES"); //Em aplication.properties temos a secrete key que é convettida para ser usada pela SecretKeySpec para ser usada na criptografia AES.
            System.out.println("=> 04 " + secretKey);

            cipher.init(Cipher.ENCRYPT_MODE, secretKey); // Cipher é inicializado


            byte[] encryptedTextBytes = cipher.doFinal(rawPassword.toString().getBytes("UTF-8"));
            System.out.println("=> 05 " + Base64.getEncoder().encodeToString(encryptedTextBytes));

            return Base64.getEncoder().encodeToString(encryptedTextBytes);

        } catch (Exception e) {
            throw new RuntimeException("=> Erro ao criptografar <=", e);
        }
    }

    @Override
    public boolean matches(CharSequence rawPassword, String encodedPassword) {
        System.out.println("=> 06 " + rawPassword);      
        System.out.println("=> 07 " + encodedPassword);   // O método matches é usado para verificar se uma senha fornecida pelo usuário corresponde à senha armazenada após a codificação.
        return false;
        // Nesse caso não temos a necessiadade de usar esse método
    }

    public String decrypt(String encryptedPassword) {
        System.out.println("=> 08 " + encryptedPassword);
        try {
            Cipher cipher = Cipher.getInstance("AES/ECB/PKCS5PADDING");
            SecretKeySpec secretKey = new SecretKeySpec(SECRET_KEY.getBytes(), "AES");
            cipher.init(Cipher.DECRYPT_MODE, secretKey);
            byte[] decryptedTextBytes = cipher.doFinal(Base64.getDecoder().decode(encryptedPassword));
            return new String(decryptedTextBytes);
        } catch (Exception e) {
            throw new RuntimeException("=> Erro ao descriptografar <=", e);
        }
    }

    public String encryptFields(String fieldValue) {
        System.out.println("=> 09 " + fieldValue);
        return encode(fieldValue);
    }
    
    public String decryptFields(String encryptedValue) {
        System.out.println("=> 10 " + encryptedValue);
        return decrypt(encryptedValue);
    }
    
    
}

/*
 * O modo ECB não é recomendado para uso em sistemas de produção devido a vulnerabilidades de segurança, mas é usado aqui para fins educacionais.
 */