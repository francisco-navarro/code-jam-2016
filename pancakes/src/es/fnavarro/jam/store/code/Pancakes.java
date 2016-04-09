package es.fnavarro.jam.store.code;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;

public class Pancakes {	
	
	private int count;
	private Stack[] cases;
	private static HashMap<Character,Boolean> map = new HashMap<>();
	
	public Pancakes(int max){
		this.count = 0;
		cases = new Stack[max];
		map.put('-', false);
		map.put('+', true);
	}
	
	public class Stack{
		
		private char[] line;
		private boolean[] sides;
		private int flipsCount=0;
		
		public Stack (String line ){
			this.line = line.toCharArray();
			sides = new boolean [this.line.length];
			for(int i=0; i<this.line.length; i++){
				sides[i] = map.get(this.line[i]);
			}
		}
		
		public boolean allSidesHappy(){
			for(boolean p : sides ){
				if(!p) return false;
			}
			return true;
		}
		
		public int firstHappy(){
			for(int i=0;i<sides.length;i++){
				if(sides[i]){
					return i;
				}
			}
			return -1;
		}
		
		public int lastHappy(){
			for(int i=sides.length-1;i>=0;i--){
				if(sides[i]){
					return i;
				}
			}
			return -1;
		}
		
		public int firstBlank(){
			for(int i=0;i<sides.length;i++){
				if(!sides[i]){
					return i;
				}
			}
			return -1;
		}
		
		public int lastBlank(){
			for(int i=sides.length-1;i>=0;i--){
				if(!sides[i]){
					return i;
				}
			}
			return -1;
		}
		
		public boolean last(){
			return sides[length()-1];
		}
		
		public boolean first(){
			return sides[0];
		}
		
		public int length(){
			return sides.length;
		}
		
		public void flip(int n){
			if(n<0){
				return;
			}
			flipsCount++;
			
			if(n==0){
				sides[0] = !sides[0];
				return;
			}
			ArrayList<Boolean> top = new ArrayList<>();
			ArrayList<Boolean> bottom = new ArrayList<>();
			for(int i=0;i<length();i++){
				if(i<=n){
					top.add(sides[i]);
				}else{
					bottom.add(sides[i]);
				}
			}
			inverse(top);
			top.addAll(bottom);
			sides = new boolean[top.size()];
			
			for(int i = 0; i<length(); i++){
				sides[i] = top.get(i);
			}
			
		}
		
		private void inverse(ArrayList<Boolean> top) {
			Collections.reverse(top);
			for(int i=0; i<top.size();i++){
				top.set(i,!top.get(i));
			}
		}
		
		public String toString(){
			String result ="";
			for( boolean side : sides ){
				result+= side ? '+':'-';
			}
			return result;
		}
		
		public int getFlips(){
			return flipsCount;
		}

		public boolean side(int n) {
			return sides[n];
		}
	}
	
	public void addCase(String line) {
		cases[count++] = new Stack(line);
	}

	public Stack[] getCases() {
		return cases;
	}

	public int count(){
		return cases.length;
	}

}
