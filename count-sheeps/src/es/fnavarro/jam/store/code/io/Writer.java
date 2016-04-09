package es.fnavarro.jam.store.code.io;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.util.Collection;
import java.util.HashSet;

import es.fnavarro.jam.store.code.domain.Sheeps;

public class Writer {
	
	private static final boolean WRITE_COUNT = false;
	
	private Sheeps document;
	private int countLines = 0;
	private File output;
	FileWriter writer = null;
	private String inputName;
	private static final int N = 200;

	public Writer(Sheeps document, File output, String inputName) {
		this.document = document;
		this.output = output;
		this.inputName = inputName;
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
		for(int i = 0; i<document.count(); i++){			
			writeLine(i, document.getCases()[i]);					
		}		
	}
	
	
	private void writeLine(int i, int case1) throws Exception {
		countLines++;
		writer.write("Case #"+(1+i)+": ");
		writer.write(caseResult(case1));
		newLine();
	}

	
	private String caseResult(int c) {
		HashSet<Integer> numbers = new HashSet<>();
		for(int i=1;i<N;i++){
			if(getNumbers(c*i, numbers)!=null){
				return Integer.toString(c*i);
			}
		}
		
		return "INSOMNIA";
	}
	
	private int last;
	private Integer getNumbers(int number,HashSet<Integer> counted) {
		String numberStr = Integer.toString(number);		
		for(char c : numberStr.toCharArray()){
			counted.add( Character.getNumericValue(c));
			if(counted.size()==10){
				return Character.getNumericValue(c);
			}
		}
		return null;
	}

	private void newLine() throws IOException{
		writer.write('\n');
	}

	private void finalizeFirstLine() throws Exception {
		//Finalize the file with a counter in the first line
		
		RandomAccessFile f = new RandomAccessFile(output, "rw");
		f.setLength(f.length() - 1);// Delete last char
		f.close();
		
		String[] cmd = { "/bin/sh", "-c", "echo " + countLines + " > ../solutions/"+inputName+".solution.out" };
		Process p = Runtime.getRuntime().exec(cmd);
		p.waitFor();
		cmd[2] =  "cat " + output.getAbsolutePath() + " >> ../solutions/"+inputName+".solution.out" ;
		p = Runtime.getRuntime().exec(cmd);
		p.waitFor();

	}
	
	private void finalizeNormal() throws Exception {
		RandomAccessFile f = new RandomAccessFile(output, "rw");
		f.setLength(f.length() - 1);// Delete last char
		f.close();		
		
		String[] cmd = { "/bin/sh", "-c", "cat " + output.getAbsolutePath() + " > ../solutions/"+inputName+".solution.out" };
		Process p = Runtime.getRuntime().exec(cmd);
		p = Runtime.getRuntime().exec(cmd);
		p.waitFor();
	}
	
	

}
