package es.fnavarro.jam.store.code.io;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;

import es.fnavarro.jam.store.code.domain.Document;


public class Reader {
	
	
	private static boolean[] buffer;
	
	public static Document read(File f) throws Exception {
		
		BufferedReader reader = null;
		
		try{
			reader = new BufferedReader(new FileReader(f));
			
			String line = reader.readLine();
			
			System.out.println( "Reading file "+f.getAbsolutePath()+"\n\t ");
			Document document = new Document();
			
			while((line=reader.readLine())!=null){
				//document....
			}
			
			return document;
			
		}finally{
			reader.close();
		}
		
		
	}



}
