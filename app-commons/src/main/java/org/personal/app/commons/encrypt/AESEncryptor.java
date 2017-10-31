package org.personal.app.commons.encrypt;

import org.apache.commons.codec.binary.Base64;
import org.apache.commons.codec.binary.Hex;
import org.personal.app.commons.logger.AppLogger;
import org.personal.app.commons.utils.AppStringUtils;

import javax.crypto.Cipher;
import javax.crypto.KeyGenerator;
import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;
import java.lang.reflect.Field;
import java.security.NoSuchAlgorithmException;
import java.security.Permission;
import java.security.PermissionCollection;
import java.util.HashMap;
import java.util.Map;

/**
 * Created at: 2017-10-26 23:09
 *
 * @author guojing
 */
public class AESEncryptor {

    private static String defaultAesKey = "aBB0DkTYOZkxTp49ZRNgTg==";
    private static AESEncryptor INSTANCE;
    private static Map<String, AESEncryptor> INSTANCES = new HashMap<>();
    private SecretKey aesKey;

    private AESEncryptor() {
        aesKey = loadAesKey();
    }

    private AESEncryptor(String aes) {
        aesKey = loadAesKey(aes);
    }

    public static AESEncryptor getInstance() {
        if (INSTANCE == null) {
            synchronized (defaultAesKey) {
                if (INSTANCE == null) {
                    INSTANCE = new AESEncryptor();
                }
            }
        }
        return INSTANCE;
    }

    public static AESEncryptor getInstance(String aes) {
        if (INSTANCES.get(aes) == null) {
            synchronized (defaultAesKey) {
                if (INSTANCES.get(aes) == null) {
                    INSTANCES.put(aes, new AESEncryptor(aes));
                }
            }
        }
        return INSTANCES.get(aes);
    }

    private static SecretKey loadAesKey() {
        return loadAesKey(defaultAesKey);
    }

    private static SecretKey loadAesKey(String aesKeyStr) {
        byte[] keyStr = Base64.decodeBase64(aesKeyStr);
        SecretKeySpec aesKey = new SecretKeySpec(keyStr, "AES");
        return aesKey;
    }

    public static String generateRandomKey() {
        try {
            KeyGenerator keyGen = KeyGenerator.getInstance("AES");
            keyGen.init(128); // for example
            SecretKey secretKey = keyGen.generateKey();
            return AppStringUtils.fromBytesUTF8(Base64.encodeBase64(secretKey.getEncoded(), false));
        } catch (NoSuchAlgorithmException e) {
            throw new RuntimeException(e);
        }

    }

    public String encrypt(String msg) {
        try {
            Cipher ecipher = Cipher.getInstance("AES");
            ecipher.init(Cipher.ENCRYPT_MODE, aesKey);
            return Hex.encodeHexString(ecipher.doFinal(msg.getBytes()));
        } catch (Exception e) {
            String errMsg = "decrypt error, data:" + msg;
            throw new EncrypterException(errMsg, e);
        }
    }

    public byte[] decrypt(String msg) {
        try {
            Cipher dcipher = Cipher.getInstance("AES");
            dcipher.init(Cipher.DECRYPT_MODE, aesKey);
            return dcipher.doFinal(Hex.decodeHex(msg.toCharArray()));
        } catch (Exception e) {
            String errMsg = "decrypt error, data:" + msg;
            throw new EncrypterException(errMsg, e);
        }
    }

    public String decryptAsString(String msg) {
        return new String(this.decrypt(msg));
    }


    /**
     * 移除对AES key的限制
     * http://stackoverflow.com/questions/1179672/how-to-avoid-installing-unlimited-strength-jce-policy-files-when-deploying-an
     */
    public static void removeCryptographyRestrictions() {
        if (!isRestrictedCryptography()) {
            AppLogger.debug("Cryptography restrictions removal not needed");
            return;
        }
        try {
        /*
         * Do the following, but with reflection to bypass access checks:
         *
         * JceSecurity.isRestricted = false;
         * JceSecurity.defaultPolicy.perms.clear();
         * JceSecurity.defaultPolicy.add(CryptoAllPermission.INSTANCE);
         */
            final Class<?> jceSecurity = Class.forName("javax.crypto.JceSecurity");
            final Class<?> cryptoPermissions = Class.forName("javax.crypto.CryptoPermissions");
            final Class<?> cryptoAllPermission = Class.forName("javax.crypto.CryptoAllPermission");

            final Field isRestrictedField = jceSecurity.getDeclaredField("isRestricted");
            isRestrictedField.setAccessible(true);
            isRestrictedField.set(null, false);

            final Field defaultPolicyField = jceSecurity.getDeclaredField("defaultPolicy");
            defaultPolicyField.setAccessible(true);
            final PermissionCollection defaultPolicy = (PermissionCollection) defaultPolicyField.get(null);

            final Field perms = cryptoPermissions.getDeclaredField("perms");
            perms.setAccessible(true);
            ((Map<?, ?>) perms.get(defaultPolicy)).clear();

            final Field instance = cryptoAllPermission.getDeclaredField("INSTANCE");
            instance.setAccessible(true);
            defaultPolicy.add((Permission) instance.get(null));

            AppLogger.debug("Successfully removed cryptography restrictions");
        } catch (final Exception e) {
            AppLogger.warn("Failed to remove cryptography restrictions", e);
        }
    }

    public static boolean isRestrictedCryptography() {
        // This simply matches the Oracle JRE, but not OpenJDK.
        return "Java(TM) SE Runtime Environment".equals(System.getProperty("java.runtime.name"));
    }

}
