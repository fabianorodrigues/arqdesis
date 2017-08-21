package service;

import java.io.*;
import javax.crypto.*;
import java.security.*;
import java.security.spec.*;
import java.security.cert.*;

public class Criptografia 
{
	private static void verificaParDeChaves() throws Exception 
	{
		File privada = new File("chave.privada");
		File publica = new File("chave.publica");

		if (!privada.exists() || !publica.exists())
			geraParDeChaves();
	}

	private static void geraParDeChaves() throws IOException, NoSuchAlgorithmException, InvalidAlgorithmParameterException,
			CertificateException, KeyStoreException 
	{
		final int RSAKEYSIZE = 1024;
		KeyPairGenerator kpg = KeyPairGenerator.getInstance("RSA");
		kpg.initialize(new RSAKeyGenParameterSpec(RSAKEYSIZE, RSAKeyGenParameterSpec.F4));
		KeyPair kpr = kpg.generateKeyPair();
		PrivateKey oPriv = kpr.getPrivate();
		PublicKey oPub = kpr.getPublic();

		ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream("chave.publica"));
		oos.writeObject(oPub);
		oos.close();

		oos = new ObjectOutputStream(new FileOutputStream("chave.privada"));
		oos.writeObject(oPriv);
		oos.close();
	}

	private static byte[] geraCifra(byte[] texto)
			throws NoSuchAlgorithmException, NoSuchPaddingException, InvalidKeyException, IllegalBlockSizeException,
			BadPaddingException, InvalidAlgorithmParameterException, IOException, ClassNotFoundException 
	{
		ObjectInputStream ois = new ObjectInputStream(new FileInputStream(new File("chave.publica")));
		PublicKey iPub = (PublicKey) ois.readObject();
		ois.close();
		Cipher rsacf = Cipher.getInstance("RSA");
		rsacf.init(Cipher.ENCRYPT_MODE, iPub);

		return rsacf.doFinal(texto);
	}

	private static byte[] geraDecifra(byte[] texto)
			throws NoSuchAlgorithmException, NoSuchPaddingException, InvalidKeyException, IllegalBlockSizeException,
			BadPaddingException, InvalidAlgorithmParameterException, IOException, ClassNotFoundException 
	{
		ObjectInputStream ois = new ObjectInputStream(new FileInputStream(new File("chave.privada")));
		PrivateKey iPrv = (PrivateKey) ois.readObject();
		ois.close();
		Cipher rsacf = Cipher.getInstance("RSA");
		rsacf.init(Cipher.DECRYPT_MODE, iPrv);

		return rsacf.doFinal(texto);
	}

	public static String criptografar(String texto) throws Exception 
	{
		verificaParDeChaves();
		
		byte[] cifrada = geraCifra(texto.getBytes("ISO-8859-1"));
		String cifra = "";

		cifra = (new String(cifrada, "ISO-8859-1"));

		return cifra;
	}

	public static String descriptografar(String texto) throws Exception 
	{
		verificaParDeChaves();
		
		byte[] vetor = null;

		vetor = geraDecifra(texto.getBytes("ISO-8859-1"));

		return (new String(vetor, "ISO-8859-1"));

	}

}