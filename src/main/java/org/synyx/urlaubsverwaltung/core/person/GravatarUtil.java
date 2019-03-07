package org.synyx.urlaubsverwaltung.core.person;

import org.apache.commons.codec.binary.Hex;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

import static java.nio.charset.StandardCharsets.UTF_8;


/**
 * This class creates a Gravatar URL for a mail address.
 *
 * @author  Aljona Murygina
 */
final class GravatarUtil {

    private static final String BASE_URL = "https://gravatar.com/avatar/";
    private static final String DIGEST_ALGORITHM = "MD5";

    private static final Logger LOG = LoggerFactory.getLogger(GravatarUtil.class);

    private GravatarUtil() {

        // Hide constructor for util classes
    }

    /**
     * This method generates the complete Gravatar URL to the given mail address.
     *
     * @param  email  String
     *
     * @return  complete Gravatar URL
     */
    static String createImgURL(String email) {

        String normalizedEmail = email == null ? "" : email.trim().toLowerCase();

        return BASE_URL + createHash(normalizedEmail);
    }


    private static String createHash(String string) {

        String encryptedString = null;

        try {

            MessageDigest md5 = MessageDigest.getInstance(DIGEST_ALGORITHM);
            final byte[] emailAsBytes = string.getBytes(UTF_8);

            encryptedString = Hex.encodeHexString(md5.digest(emailAsBytes));
        } catch (NoSuchAlgorithmException ex) {
            LOG.error("Creation of message digest failed.", ex);
        }

        return encryptedString;
    }
}
