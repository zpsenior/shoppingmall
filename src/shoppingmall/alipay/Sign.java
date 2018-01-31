package shoppingmall.alipay;

import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;
import java.io.StringWriter;
import java.security.KeyFactory;
import java.security.PrivateKey;
import java.security.PublicKey;
import java.security.spec.PKCS8EncodedKeySpec;
import java.security.spec.X509EncodedKeySpec;

import org.apache.commons.codec.binary.Base64;

public class Sign {

	public static String rsa256Sign(String content, String privateKey, String charset) throws Exception {
		PrivateKey priKey = getPrivateKeyFromPKCS8("RSA", new ByteArrayInputStream(privateKey.getBytes()));
		java.security.Signature signature = java.security.Signature.getInstance("SHA256WithRSA");
		
		signature.initSign(priKey);
		
		if (isEmpty(charset)) {
			signature.update(content.getBytes());
		} else {
			signature.update(content.getBytes(charset));
		}

		byte[] signed = signature.sign();
		return new String(Base64.encodeBase64(signed));
	}
	
	public static String rsaSign(String content, String privateKey, String charset) throws Exception {
		PrivateKey priKey = getPrivateKeyFromPKCS8("RSA", new ByteArrayInputStream(privateKey.getBytes()));
		java.security.Signature signature = java.security.Signature.getInstance("SHA1WithRSA");

		signature.initSign(priKey);

		if (isEmpty(charset)) {
			signature.update(content.getBytes());
		} else {
			signature.update(content.getBytes(charset));
		}

		byte[] signed = signature.sign();

		return new String(Base64.encodeBase64(signed));
	}

	
	private static PrivateKey getPrivateKeyFromPKCS8(String algorithm, InputStream ins) throws Exception {
		if (ins == null || isEmpty(algorithm)) {
			return null;
		}

		KeyFactory keyFactory = KeyFactory.getInstance(algorithm);

		byte[] encodedKey = readText(ins).getBytes();

		encodedKey = Base64.decodeBase64(encodedKey);

		return keyFactory.generatePrivate(new PKCS8EncodedKeySpec(encodedKey));
	}
	
	private static boolean isEmpty(String value) {
		int strLen;
		if (value == null || (strLen = value.length()) == 0) {
			return true;
		}
		for (int i = 0; i < strLen; i++) {
			if ((Character.isWhitespace(value.charAt(i)) == false)) {
				return false;
			}
		}
		return true;
	}
	
	public static String readText(InputStream in) throws Exception {
		
		Reader reader = new InputStreamReader(in, "utf-8");
		
		StringWriter writer = new StringWriter();

		char[] buffer = new char[1024];
		int amount;

		while ((amount = reader.read(buffer)) >= 0) {
			writer.write(buffer, 0, amount);
		}
        return writer.toString();
    }
	
	public static boolean rsaCheck(String content, String sign, String publicKey, String charset, String signType)throws Exception {
		if ("RSA".equals(signType)) {
			return rsaCheckContent(content, sign, publicKey, charset);
		} else if ("RSA2".equals(signType)) {
			return rsa256CheckContent(content, sign, publicKey, charset);
		}
		throw new RuntimeException("Sign Type is Not Support : signType=" + signType);
	}

	public static boolean rsa256CheckContent(String content, String sign, String publicKey, String charset) throws Exception {
		PublicKey pubKey = getPublicKeyFromX509("RSA", new ByteArrayInputStream(publicKey.getBytes()));

		java.security.Signature signature = java.security.Signature.getInstance("SHA256WithRSA");

		signature.initVerify(pubKey);

		if (isEmpty(charset)) {
			signature.update(content.getBytes());
		} else {
			signature.update(content.getBytes(charset));
		}

		return signature.verify(Base64.decodeBase64(sign.getBytes()));
	}
	
	public static boolean rsaCheckContent(String content, String sign, String publicKey, String charset) throws Exception {
		PublicKey pubKey = getPublicKeyFromX509("RSA", new ByteArrayInputStream(publicKey.getBytes()));

		java.security.Signature signature = java.security.Signature.getInstance("SHA1WithRSA");

		signature.initVerify(pubKey);

		if (isEmpty(charset)) {
			signature.update(content.getBytes());
		} else {
			signature.update(content.getBytes(charset));
		}

		return signature.verify(Base64.decodeBase64(sign.getBytes()));
	}

	private static PublicKey getPublicKeyFromX509(String algorithm, InputStream ins) throws Exception {
		KeyFactory keyFactory = KeyFactory.getInstance(algorithm);

		StringWriter writer = new StringWriter();
		InputStreamReader reader = new InputStreamReader(ins);
		
		char[] buffer = new char[1024];
        int count;
        while ((count = reader.read(buffer)) >= 0) {
        	writer.write(buffer, 0, count);
        }

		byte[] encodedKey = writer.toString().getBytes();

		encodedKey = Base64.decodeBase64(encodedKey);

		return keyFactory.generatePublic(new X509EncodedKeySpec(encodedKey));
	}
}
