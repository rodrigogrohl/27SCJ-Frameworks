package com.fiap;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;

import com.lowagie.text.pdf.PdfCopyFields;
import com.lowagie.text.pdf.PdfReader;

public class GeracaoPostScript1 {

	private static final String POSTSCRIPT_STAPLE_TAG = "<</Staple ?>> setpagedevice";
	private static final String LINE_SEPARATOR = "line.separator";
	
	/**
	 * @param args
	 */
	public static void main(String[] args) {
		
		File file5PS = new File("5.ps");
		if (file5PS.exists()){
			file5PS.delete();
		}
		File file6PS = new File("6.ps");
		if (file6PS.exists()){
			file6PS.delete();
		}
		
		String lineContent = null;
		int counter = 0;
		int pdfCounter = 0;
		char postScriptFileClampFlag = '3';
		List pageNumberList = new ArrayList();
		
		try {
			
			long tmpini = System.currentTimeMillis();
			
			//geracao PDF Master
			PdfReader reader1 = new PdfReader("1.pdf");
			PdfReader reader2 = new PdfReader("2.pdf");
			PdfReader reader3 = new PdfReader("3.pdf");
			PdfReader reader4 = new PdfReader("4.pdf");
			pageNumberList.add(new Integer(reader1.getNumberOfPages()));
			pageNumberList.add(new Integer(reader2.getNumberOfPages()));
			pageNumberList.add(new Integer(reader3.getNumberOfPages()));
			pageNumberList.add(new Integer(reader4.getNumberOfPages()));
			
			PdfCopyFields copy = new PdfCopyFields(new FileOutputStream("5.pdf"));
			copy.addDocument(reader1);
			copy.addDocument(reader2);
			copy.addDocument(reader3);
			copy.addDocument(reader4);
			copy.close();
			
			
			
			//conversao para Post Script
			String commandPDF2PS = "pdftops64.exe -paper A4";	
			Runtime.getRuntime().exec(commandPDF2PS.concat(" ").concat("5.pdf"));
			
			//rotina para verificar se o arquivo PS já foi criado.
			File PSFile = new File("5.ps");
			while (!PSFile.exists()){
				System.out.println("Aguardando arquivo PS ser criado...");
			}
			Thread.sleep(2000);
			
			
			//adicao tags impressora
			System.out.println("Lendo arquivo 5.pdf");
			BufferedReader bufferedReader = new BufferedReader(new FileReader(PSFile));
			
			PrintWriter printWriter = new PrintWriter(new BufferedWriter(new FileWriter("6.ps", false)));
			while ((lineContent = bufferedReader.readLine()) != null) {
				if (lineContent.equals("%%PageTrailer") && (++counter == ((Integer)pageNumberList.get(pdfCounter)).intValue())){
					System.out.println("Adicionando tag 'staple'.");
					lineContent = POSTSCRIPT_STAPLE_TAG.replace('?', postScriptFileClampFlag).
						concat(System.getProperty(LINE_SEPARATOR)).concat(lineContent);
					if (postScriptFileClampFlag == '3'){
						postScriptFileClampFlag = '0';
					} else {
						postScriptFileClampFlag = '3';
					}
					System.out.println("Tag 'staple' adicionada com sucesso.");
					pdfCounter++;
					counter = 0;
				}
				printWriter.println(lineContent);
			}
			
			//grava novo arquivo PS com tags 'staple' inclusas
			System.out.println("Gravando novo arquivo[6.ps].");
			printWriter.close();
			System.out.println("Arquivo gerado com sucesso");
			
			
			System.out.println("Tempo total execução: " + (System.currentTimeMillis() - tmpini));

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
