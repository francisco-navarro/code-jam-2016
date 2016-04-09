package es.fnavarro.jam.store.code;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;


public class Reader {
	
	
	
	public static Pancakes read(File f) throws Exception {
		
		BufferedReader reader = null;
		
		try{
			reader = new BufferedReader(new FileReader(f));
			
			String line = reader.readLine();
			int cases = Integer.parseInt(line);
			
			System.out.println( "Reading file "+f.getAbsolutePath()+"\n\t ");
			Pancakes document = new Pancakes(cases);
			
			for(int i=0;i<cases;i++){
				document.addCase(reader.readLine());
			}
			
			return document;
			
		}finally{
			reader.close();
		}
	}

}
