package org.personal.app.commons.auth;

import org.junit.Assert;
import org.junit.Test;

/**
 * Created at: 2017-10-28 21:21
 *
 * @author guojing
 */
public class TokenUtilsTest {

    @Test
    public void testGenerateClientToken() throws Exception {
        String uid = "testuser1";
        String udid = "testudid1";
        String clientTokenStr = TokenUtils.generateClientToken(uid, udid);
        Token clientToken = TokenUtils.checkToken(clientTokenStr);
        Assert.assertNotNull(clientToken);
        Assert.assertTrue(clientToken instanceof ClientToken);
        Assert.assertEquals(AuthGrantType.client_token, clientToken.grantType);
        Assert.assertEquals(uid, clientToken.getUid());
        Assert.assertEquals(udid, ((ClientToken) clientToken).getUdid());
    }

    @Test
    public void testGenerateServerToken() throws Exception {
        String uid = "testuser1";
        String serverID = "testServerID1";
        String serverTokenStr = TokenUtils.generateServerToken(uid, serverID);
        Token serverToken = TokenUtils.checkToken(serverTokenStr);
        Assert.assertNotNull(serverToken);
        Assert.assertTrue(serverToken instanceof ServerToken);
        Assert.assertEquals(AuthGrantType.server_token, serverToken.grantType);
        Assert.assertEquals(uid, serverToken.getUid());
        Assert.assertEquals(serverID, ((ServerToken) serverToken).getServerID());
    }

}