package es.fnavarro.jam.store.code.domain;

public class Document {
	
	
	private int count;
	private Case[] cases;
	
	public Document(int max){
		this.count = 0;
		cases = new Case[max];
	}
	
	public class Case {
		int credit;
		int nItems;
		int[] products;
		int max1,max2;
		
		public Case(int credit, int nItems, String products){
			int count = max1 = max2 = 0;
			this.credit = credit;
			this.nItems = nItems;
			this.products = new int[nItems];
			for(String product : products.split(" ")){
				this.products[count++] = Integer.parseInt(product);
				if(count > 0){
					maximize(count-1);
					orderMax();
				}
			}	
			for(int j = 1 ; j< this.products.length; j++){
				for(int i = 1+j ; i< this.products.length;i++){				
					maximize(i);
					orderMax();
				}
			}
		}
		
		private void maximize(int n) {				
			if( credit(max1, n) > credit(max1,max2) ){
				max2 = n;
			}else if( credit(n, max2) > credit(max1,max2) ){
				max1 = n;
			}
		}
		
		private int credit(int n1, int n2){
			int result = products[n1] + products[n2];
			if(result > credit ){
				return - 1;
			}
			return result;
		}

		private void orderMax() {
			int temp;
			if(max2 < max1){
				temp = max2;
				max2 = max1;
				max1 = temp;
			}
		}
		
		public String toString(){
			return (max1+1) + " " + (max2+1);
		}
	}

	public void addCase(String credit, String nItems, String products) {
		this.cases[count++] = new Case(
				Integer.parseInt(credit), 
				Integer.parseInt(nItems), 
				products);
	}

	public Case[] getCases() {
		return cases;
	}
	
	public int count(){
		return cases.length;
	}


}
