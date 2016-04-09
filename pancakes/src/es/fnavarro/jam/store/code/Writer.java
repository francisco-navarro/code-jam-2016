package es.fnavarro.jam.store.code;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.util.Collection;
import java.util.HashSet;

import es.fnavarro.jam.store.code.Pancakes.Stack;

public class Writer {
	
	private static final boolean WRITE_COUNT = false;
	
	private Pancakes document;
	private int countLines = 0;
	private File output;
	FileWriter writer = null;
	private String inputName;

	public Writer(Pancakes document, File output, String inputName) {
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
	
	
	private void writeLine(int i, Stack stack) throws Exception {
		countLines++;
		writer.write("Case #"+(1+i)+": ");
		System.out.println("Case #"+(1+i)+" "+stack);
		writer.write(caseResult(stack));
		newLine();
	}

	
	private String caseResult(Stack stack) {
		
		while(!stack.allSidesHappy()){
			makeMovement(stack);
			System.out.println(stack.getFlips() + " " + stack);
		}
		return Integer.toString(stack.getFlips());		
	}
	

	private void makeMovement(Stack stack) {

		if(stack.first()) {
			//Primero es positivo - flip until first black-1
			stack.flip(stack.firstBlank()-1);
		}else{
			stack.flip(stack.lastBlank());
		}
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
		
		String[] cmd = { "/bin/sh", "-c", "cat " + output.getAbsolutePath() + " > ../solutions/"+inputName+".solution.out."+System.currentTimeMillis() };
		Process p = Runtime.getRuntime().exec(cmd);
		p = Runtime.getRuntime().exec(cmd);
		p.waitFor();
	}
	
	

}
