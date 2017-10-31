package org.personal.app.commons.auth;

import org.apache.commons.lang.math.NumberUtils;
import org.personal.app.commons.encrypt.AESEncryptor;
import org.personal.app.commons.logger.AppLogger;
import org.personal.app.commons.utils.AppStringUtils;
import org.personal.app.commons.utils.Base62Utils;

/**
 * Created at: 2017-10-27 08:26
 *
 * @author guojing
 */
public class TokenUtils {

    public static Token checkToken(String token) {
        if (token == null) {
            return null;
        }

        String[] encrypts = AppStringUtils.split(token, ".");
        if (encrypts.length != 2) {
            return null;
        }

        Token result = null;
        try {
            AccessTokenVersion atv = AccessTokenVersion.valueOf(NumberUtils.toInt(encrypts[0]));
            AESEncryptor aesEncrypter = AESEncryptor.getInstance(atv.getSecret());
            String decryptStr = aesEncrypter.decryptAsString(encrypts[1]);
            if (decryptStr == null) {
                return null;
            }
            String[] tokenParts = decryptStr.split("-");
            if (tokenParts.length != 4) {
                return null;
            }
            AuthGrantType grantType = AuthGrantType.parse(NumberUtils.toByte(tokenParts[0]));
            if (grantType == null) {
                return null;
            }

            long time = Base62Utils.decode(tokenParts[1]);
            String uid = tokenParts[2];
            switch (grantType) {
                case client_token:
                    String udid = tokenParts[3];
                    result = new ClientToken(uid, udid, time, token);
                    break;
                case server_token:
                    String serverID = tokenParts[3];
                    result = new ServerToken(uid, serverID, time, token);
                    break;
                default:
                    break;
            }
        } catch (Exception e) {
            AppLogger.warn("checkToken", e);
        }
        return result;
    }

    public static String generateClientToken(String uid, String udid) {
        if (AppStringUtils.isBlank(uid) || AppStringUtils.isBlank(udid)) {
            return null;
        }

        long time = System.currentTimeMillis() - AuthGrantType.START_TIMESTAMP;
        StringBuilder token = new StringBuilder();
        token.append(AuthGrantType.client_token.getValue());
        token.append("-" + Base62Utils.encode(time));
        token.append("-" + uid);
        token.append("-" + udid);

        AccessTokenVersion atv = AccessTokenVersion.currentVersion;
        AESEncryptor encrypter = AESEncryptor.getInstance(atv.getSecret());
        String encrypt = encrypter.encrypt(token.toString());
        return atv.getVersion() + "." + encrypt;
    }

    public static String generateServerToken(String uid, String serverID) {
        if (AppStringUtils.isBlank(uid) || AppStringUtils.isBlank(serverID)) {
            return null;
        }

        long time = System.currentTimeMillis() - AuthGrantType.START_TIMESTAMP;
        StringBuilder token = new StringBuilder();
        token.append(AuthGrantType.server_token.getValue());
        token.append("-" + Base62Utils.encode(time));
        token.append("-" + uid);
        token.append("-" + serverID);

        AccessTokenVersion atv = AccessTokenVersion.currentVersion;
        AESEncryptor encrypter = AESEncryptor.getInstance(atv.getSecret());
        String encrypt = encrypter.encrypt(token.toString());
        return atv.getVersion() + "." + encrypt;
    }

    public static String refreshToken(Token oldToken) {
        AuthGrantType grantType = oldToken.getGrantType();
        String newToken = null;
        switch (grantType) {
            case client_token:
                ClientToken clientToken = (ClientToken) oldToken;
                newToken = generateClientToken(clientToken.getUid(), clientToken.getUdid());
                break;
            case server_token:
                ServerToken serverToken = (ServerToken) oldToken;
                newToken = generateServerToken(serverToken.getUid(), serverToken.getServerID());
                break;
            default:
                break;
        }
        return newToken;
    }

}
