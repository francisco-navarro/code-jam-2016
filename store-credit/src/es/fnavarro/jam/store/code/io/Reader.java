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
			int cases = Integer.parseInt(line);
			
			System.out.println( "Reading file "+f.getAbsolutePath()+"\n\t with "+cases+" cases");
			Document document = new Document(cases);
			
			for(int i =0; i<cases; i++){			
				//document....
				String credit = reader.readLine();
				String nItems = reader.readLine();
				String products = reader.readLine();
				
				document.addCase(credit,nItems,products);
			}
			
			return document;
			
		}finally{
			reader.close();
		}		
	}



}
