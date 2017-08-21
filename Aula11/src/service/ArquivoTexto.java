package service;

import java.util.*;
import java.io.*;

public class ArquivoTexto 
{ 
	private String diretorio;
	private String nomeDoArquivo;
	
	public ArquivoTexto(String diretorio, String nomeDoArquivo)
	{
		this.diretorio = diretorio;
		this.nomeDoArquivo = nomeDoArquivo;
	}
	
	public ArquivoTexto(String nomeDoArquivo)
	{
		this.nomeDoArquivo = nomeDoArquivo;
	}
	
	public void criar() 
	{
		File arquivo;		
		try 
		{
			arquivo = retornaArquivo();
			
			if (!arquivo.exists()) // se o arquivo não existir ele ira ser criado
			{
				Formatter saida = new Formatter(arquivo);
				saida.close();
			}
		}
		catch (Exception ex) 
		{
			ex.printStackTrace();
		}
	}
	
	public void escrever(String mensagem)
	{
		OutputStream os;
		OutputStreamWriter osw;
		BufferedWriter bw;
		
		try 
		{
			os = new FileOutputStream(retornaArquivo(), true);
			osw = new OutputStreamWriter(os);
	        bw = new BufferedWriter(osw);

	        bw.write(mensagem);
	        bw.newLine();

	        bw.close();
		} 
		catch (Exception e) 
		{
			e.printStackTrace();
		}
	}
	
	public void escrever(String[] mensagem)
	{
		OutputStream os;
		OutputStreamWriter osw;
		BufferedWriter bw;
		
		try 
		{
			os = new FileOutputStream(retornaArquivo(), true);
			osw = new OutputStreamWriter(os);
	        bw = new BufferedWriter(osw);

	        for (int i = 0; i < mensagem.length; i++)
	        {
	        	bw.write(mensagem[i]);
	        	bw.newLine();
	        }

	        bw.close();

		} 
		catch (Exception e) 
		{
			e.printStackTrace();
		}
	}
	
	public String ler()
	{
		File arquivo;
		FileReader fileReader;
		BufferedReader bufferedReader;
		
		try
		{
			arquivo = retornaArquivo();
			fileReader = new FileReader(arquivo);;
			bufferedReader = new BufferedReader(fileReader);
			
			if(arquivo.exists())
			{
				String saida = new String();
				
				while(true)
				{
					saida = bufferedReader.readLine();
					if(saida == null) 
					{
						bufferedReader.close();
						return new String();
					}
					
					saida += String.format("%s\n", saida);
				}
			}
			
			bufferedReader.close();
		} 
		catch (Exception e) 
		{
			e.printStackTrace();
		}
		
		return new String();
	}
	
	private File retornaArquivo() throws Exception
	{
		if(diretorio != null)
			return new File(String.format("%s/%s", diretorio, nomeDoArquivo));
		else
			return new File(nomeDoArquivo);
	}
	
}
