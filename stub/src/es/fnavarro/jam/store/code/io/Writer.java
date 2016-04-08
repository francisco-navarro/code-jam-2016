package es.fnavarro.jam.store.code.io;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.RandomAccessFile;

import es.fnavarro.jam.store.code.domain.Document;

public class Writer {
	
	private static final boolean WRITE_COUNT = false;
	
	private Document document;
	private int countLines = 0;
	private File output;
	FileWriter writer = null;

	public Writer(Document document, File output) {
		this.document = document;
		this.output = output;
	}
	
	public void write() throws Exception {
		
		try{
			writer = new FileWriter(output);
			getContent();
			
		}finally{			
			if(writer!=null){
				writer.close();
			}
		}
		
		if(WRITE_COUNT){
			finalizeFirstLine();
		}else{
			finalizeNormal();
		}
	}

	private void getContent() throws Exception {
		int endLine = 0;
		for(int i = 0; i<10; i++){
			
			writeLine("SOMETHING...");
					
		}
		
		
	}
	
	private void writeLine(String str)throws Exception{
		countLines++;
		writer.write(str);
		newLine();
	}

	
	private void newLine() throws IOException{
		writer.write('\n');
	}

	private void finalizeFirstLine() throws Exception {
		//Finalize the file with a counter in the first line
		
		RandomAccessFile f = new RandomAccessFile(output, "rw");
		f.setLength(f.length() - 1);// Delete last char
		f.close();
		
		String[] cmd = { "/bin/sh", "-c", "echo " + countLines + " > solution.out" };
		Process p = Runtime.getRuntime().exec(cmd);
		p.waitFor();
		cmd[2] =  "cat " + output.getAbsolutePath() + " >> solution.out" ;
		p = Runtime.getRuntime().exec(cmd);
		p.waitFor();

	}
	
	private void finalizeNormal() throws Exception {
		RandomAccessFile f = new RandomAccessFile(output, "rw");
		f.setLength(f.length() - 1);// Delete last char
		f.close();		
		
		String[] cmd = { "/bin/sh", "-c", "cat " + output.getAbsolutePath() + " > solution.out" };
		Process p = Runtime.getRuntime().exec(cmd);
		p = Runtime.getRuntime().exec(cmd);
		p.waitFor();
	}
	
	

}
