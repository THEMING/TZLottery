/**
 * <pre>
 * Title: 		SignatureHelper.java
 * Author:		pengfangliang
 * Create:	 	2014-6-19 上午09:58:51
 * Copyright: 	Copyright (c) 2014
 * Company:		CaiSo
 * <pre>
 */
package com.xsc.lottery.util;

import java.io.UnsupportedEncodingException;
import java.security.GeneralSecurityException;
import java.security.KeyFactory;
import java.security.KeyPair;
import java.security.KeyPairGenerator;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.security.PrivateKey;
import java.security.PublicKey;
import java.security.SecureRandom;
import java.security.Signature;
import java.security.interfaces.RSAPrivateKey;
import java.security.interfaces.RSAPublicKey;
import java.security.spec.PKCS8EncodedKeySpec;
import java.security.spec.X509EncodedKeySpec;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Random;

import org.apache.commons.codec.binary.Hex;

/**
 * <pre>
 * 签名工具类
 * </pre>
 * @author pengfangliang
 * @version 1.0, 2014-6-19
 */
public class SignatureHelper
{
	private static final char[] bcdLookup = { '0', '1', '2', '3', '4', '5', '6', '7', '8', '9', 'a', 'b', 'c', 'd',
			'e', 'f' };

	public static void main(String[] args) throws GeneralSecurityException
	{
		System.out.println(SignatureHelper.generateMD5Signture("123456是", "utf-8"));
	}

	/**
	 * 将字节数组转换为16进制字符串的形式.
	 */
	private static final String bytesToHexStr(byte[] bcd)
	{
		StringBuffer s = new StringBuffer(bcd.length * 2);

		for (int i = 0; i < bcd.length; i++)
		{
			s.append(bcdLookup[(bcd[i] >>> 4) & 0x0f]);
			s.append(bcdLookup[bcd[i] & 0x0f]);
		}

		return s.toString();
	}

	public static String generateSHASignature(String src, String encoding)
			throws GeneralSecurityException
	{
		MessageDigest md;
		try
		{
			md = MessageDigest.getInstance("SHA");
		}
		catch (NoSuchAlgorithmException e)
		{
			throw new RuntimeException(e);
		}
		if (encoding == null)
		{
			encoding = "ISO-8859-1";
		}
		byte[] digest;
		try
		{
			digest = md.digest(src.getBytes(encoding));
		}
		catch (UnsupportedEncodingException e)
		{
			throw new GeneralSecurityException(e);
		}

		String encodedString = new String(Hex.encodeHex(digest));
		return encodedString;
	}

	/**
	 * <pre>
	 * 使用SHA1withRSA签名算法进行签名
	 * </pre>
	 * @param priKey 签名时使用的私钥(16进制编码)
	 * @param src 签名的原字符串
	 * @param encoding 字符编码
	 * @return 签名的返回结果(16进制编码)
	 * @throws GeneralSecurityException
	 */
	public static String generateRSASignature(String priKey, String src, String encoding)
			throws GeneralSecurityException
	{
		Signature sigEng = Signature.getInstance("SHA1withRSA");
		byte[] pribyte = hexStrToBytes(priKey.trim());
		PKCS8EncodedKeySpec keySpec = new PKCS8EncodedKeySpec(pribyte);
		KeyFactory fac = KeyFactory.getInstance("RSA");

		RSAPrivateKey privateKey = (RSAPrivateKey) fac.generatePrivate(keySpec);
		sigEng.initSign(privateKey);
		if (encoding == null)
		{
			encoding = "ISO-8859-1";
		}
		try
		{
			sigEng.update(src.getBytes(encoding));
		}
		catch (UnsupportedEncodingException e)
		{
			throw new GeneralSecurityException(e);
		}

		byte[] signature = sigEng.sign();
		return bytesToHexStr(signature);
	}

	/**
	 * <pre>
	 * 生成1024位RSA公私钥对
	 * </pre>
	 * @return 公私钥对数组，第一个元素是公钥，第二个是私钥
	 * @throws GeneralSecurityException 签名异常
	 */
	public static String[] genRSAKeyPair() throws GeneralSecurityException
	{
		KeyPairGenerator rsaKeyGen = null;
		KeyPair rsaKeyPair = null;
		rsaKeyGen = KeyPairGenerator.getInstance("RSA");
		SecureRandom random = new SecureRandom();
		String valueOf = String.valueOf(System.currentTimeMillis());
		List<String> list = new ArrayList<String>();
		for (int i = 0; i < valueOf.length(); i++)
		{
			list.add(String.valueOf(valueOf.charAt(i)));
		}
		Collections.shuffle(list, new Random(System.currentTimeMillis()));
		StringBuffer sb = new StringBuffer();
		for (String string : list)
		{
			sb.append(string);
		}
		random.setSeed(sb.toString().getBytes());
		rsaKeyGen.initialize(1024, random);
		rsaKeyPair = rsaKeyGen.genKeyPair();
		PublicKey rsaPublic = rsaKeyPair.getPublic();
		PrivateKey rsaPrivate = rsaKeyPair.getPrivate();

		String[] keys = new String[2];
		keys[0] = bytesToHexStr(rsaPublic.getEncoded());
		keys[1] = bytesToHexStr(rsaPrivate.getEncoded());
		return keys;
	}

	/**
	 * 将16进制字符串还原为字节数组.
	 */
	private static final byte[] hexStrToBytes(String s)
	{
		byte[] bytes;

		bytes = new byte[s.length() / 2];

		for (int i = 0; i < bytes.length; i++)
		{
			bytes[i] = (byte) Integer.parseInt(s.substring(2 * i, 2 * i + 2), 16);
		}

		return bytes;
	}

	/**
	 * <pre>
	 * 使用RSA签名算法验证签名
	 * </pre>
	 * @param pubKey 验证签名时使用的公钥(16进制编码)
	 * @param sign 签名结果(16进制编码)
	 * @param src 签名的原字符串
	 * @param encoding 字符编码
	 * @return 签名的返回结果(16进制编码)
	 * @throws GeneralSecurityException 签名异常
	 */
	public static boolean verifyRSASignature(String pubKey, String sign, String src, String encoding)
			throws GeneralSecurityException
	{
		Signature sigEng = Signature.getInstance("SHA1withRSA");
		byte[] pubbyte = hexStrToBytes(pubKey.trim());
		X509EncodedKeySpec keySpec = new X509EncodedKeySpec(pubbyte);
		KeyFactory fac = KeyFactory.getInstance("RSA");
		RSAPublicKey rsaPubKey = (RSAPublicKey) fac.generatePublic(keySpec);

		sigEng.initVerify(rsaPubKey);
		if (encoding == null)
		{
			encoding = "ISO-8859-1";
		}
		try
		{
			sigEng.update(src.getBytes(encoding));
		}
		catch (UnsupportedEncodingException e)
		{
			throw new GeneralSecurityException(e);
		}

		byte[] sign1 = hexStrToBytes(sign);
		return sigEng.verify(sign1);
	}

	/** * The jce MD5 message digest generator. */
	private static MessageDigest md5;

	/**
	 * * Retrieves a hexidecimal character sequence representing the MD5 * digest of the specified character sequence,
	 * using the specified * encoding to first convert the character sequence into a byte sequence. * If the specified
	 * encoding is null, then ISO-8859-1 is assumed * *
	 * @param string the string to encode. *
	 * @param encoding the encoding used to convert the string into the * byte sequence to submit for MD5 digest *
	 * @return a hexidecimal character sequence representing the MD5 * digest of the specified string *
	 * @throws HsqlUnsupportedOperationException if an MD5 digest * algorithm is not available through the *
	 *         java.security.MessageDigest spi or the requested * encoding is not available
	 */
	public static final String generateMD5Signture(String string, String encoding) throws RuntimeException
	{
		return bytesToHexStr(digestMD5String(string, encoding));
	}

	/**
	 * * Retrieves a byte sequence representing the MD5 digest of the * specified character sequence, using the
	 * specified encoding to * first convert the character sequence into a byte sequence. * If the specified encoding is
	 * null, then ISO-8859-1 is * assumed. * *
	 * @param string the string to digest. *
	 * @param encoding the character encoding. *
	 * @return the digest as an array of 16 bytes. *
	 * @throws HsqlUnsupportedOperationException if an MD5 digest * algorithm is not available through the *
	 *         java.security.MessageDigest spi or the requested * encoding is not available
	 */
	private static byte[] digestMD5String(String string, String encoding) throws RuntimeException
	{
		byte[] data;
		if (encoding == null)
		{
			encoding = "ISO-8859-1";
		}
		try
		{
			data = string.getBytes(encoding);
		}
		catch (UnsupportedEncodingException x)
		{
			throw new RuntimeException(x.toString());
		}
		return digestMD5Bytes(data);
	}

	/**
	 * * Retrieves a byte sequence representing the MD5 digest of the * specified byte sequence. * *
	 * @param data the data to digest. *
	 * @return the MD5 digest as an array of 16 bytes. *
	 * @throws HsqlUnsupportedOperationException if an MD5 digest * algorithm is not available through the *
	 *         java.security.MessageDigest spi
	 */
	private static final byte[] digestMD5Bytes(byte[] data) throws RuntimeException
	{
		synchronized (SignatureHelper.class)
		{
			if (md5 == null)
			{
				try
				{
					md5 = MessageDigest.getInstance("MD5");
				}
				catch (NoSuchAlgorithmException e)
				{
					throw new RuntimeException(e.toString());
				}
			}
			return md5.digest(data);
		}
	}
}
