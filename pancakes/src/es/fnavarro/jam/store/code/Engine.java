package es.fnavarro.jam.store.code;

import java.io.File;
import java.util.Date;

public class Engine {
	private Pancakes document;
	private File file;
	private File output;

	public static void main (String args[]) throws Exception{
		File f = parseInputArgs(args);
		
		new Engine(f).read().write(f.getName());
	}

	private static File parseInputArgs(String[] args) throws Exception{
		
		if(args.length==0){
			throw new Exception("One file as argument must to be provided");
		}
		
		File f = new File(args[0]+".in");
		
		if(!f.exists()){
			throw new Exception("The file "+f.getAbsolutePath()+" does not exist.");
		}
		
		
		return f;
	}
	
	public Engine(File file) throws Exception{
		this.file = file;
		output = File.createTempFile("instructions",".tmp");
		
	}
	
	public Engine read() throws Exception{
		System.out.println(new Date()+ " Reading file...");
		this.document = Reader.read(file);
		System.out.println(new Date()+ " File readed");
		return this;
	}
	
	public void write(String name) throws Exception{
		
		new Writer(document, output, name).write();		
		System.out.println("Writed "+output.getAbsolutePath());
		
		output.delete();
	}
	
}
