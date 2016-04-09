package es.fnavarro.jam.store.code.domain;

public class Sheeps {	
	
	private int count;
	private int[] cases;
	
	public Sheeps(int max){
		this.count = 0;
		cases = new int[max];
	}
	
	public void addCase(int n) {
		this.cases[count++] = n;
	}
	public void addCase(String readLine) {
		addCase(Integer.parseInt(readLine));
	}

	public int[] getCases() {
		return cases;
	}

	public int count(){
		return cases.length;
	}

	


}
