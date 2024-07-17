package br.com.trajy.graphql.util;

import static java.nio.charset.StandardCharsets.UTF_8;

import lombok.experimental.UtilityClass;
import org.bouncycastle.crypto.generators.OpenBSDBCrypt;

@UtilityClass
public class HashUtil {

    public boolean isBCriptyMatch(String value, String hashedValue) {
        return OpenBSDBCrypt.checkPassword(hashedValue, value.getBytes(UTF_8));
    }

}
