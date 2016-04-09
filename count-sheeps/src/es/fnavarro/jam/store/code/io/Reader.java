package es.fnavarro.jam.store.code.io;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;

import es.fnavarro.jam.store.code.domain.Sheeps;


public class Reader {
	
	
	
	public static Sheeps read(File f) throws Exception {
		
		BufferedReader reader = null;
		
		try{
			reader = new BufferedReader(new FileReader(f));
			
			String line = reader.readLine();
			int cases = Integer.parseInt(line);
			
			System.out.println( "Reading file "+f.getAbsolutePath()+"\n\t ");
			Sheeps document = new Sheeps(cases);
			
			for(int i=0;i<cases;i++){
				document.addCase(reader.readLine());
			}
			
			return document;
			
		}finally{
			reader.close();
		}
	}

}
