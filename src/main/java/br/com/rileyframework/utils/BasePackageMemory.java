package br.com.rileyframework.utils;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class BasePackageMemory {
	
	public static void savePackageInMemory(String baseClass) {
		File f = new File("basepackage.txt");
		try {
			FileWriter fw = new FileWriter(f);
			BufferedWriter bw = new BufferedWriter(fw);
			bw.write(baseClass);
			bw.newLine();
			bw.flush();
			bw.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	@SuppressWarnings("resource")
	public static String getPackageInMemory(File f) {
		try {
			FileReader fr = new FileReader(f);
			BufferedReader br = new BufferedReader(fr);
			String s;
			while ((s = br.readLine()) != null) {
				return s;
			}
		} catch (IOException e) {
			System.out.println("###### Erro: "+e.getMessage());
			e.printStackTrace();
		}
		return null;
	}
	
}
