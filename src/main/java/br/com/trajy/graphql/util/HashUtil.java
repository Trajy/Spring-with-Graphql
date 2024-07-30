package br.com.trajy.graphql.util;

import static java.nio.charset.StandardCharsets.UTF_8;
import static org.apache.commons.lang3.RandomStringUtils.random;
import static org.bouncycastle.crypto.generators.OpenBSDBCrypt.generate;

import lombok.experimental.UtilityClass;

@UtilityClass
public class HashUtil {

    public boolean isBCriptyMatch(String value, String hashedValue) {
    public String hashPassword(String originalPassword) {
        return generate(originalPassword.toCharArray(), random(16).getBytes(), 1);
    }

}
