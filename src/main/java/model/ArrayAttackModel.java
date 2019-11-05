package model;

import java.util.ArrayList;
import java.util.Random;

import view.ArrayAttackView;

public class ArrayAttackModel {
	
	private ArrayList<Integer> array;
	private ArrayAttackView view;
	private int score;
	private Random rand;
	private int curIndex;

	//Merge variables
	private ArrayList<Integer> array2;
	private int s1i1, s1i2, s2i1, s2i2;
	
	public ArrayAttackModel(ArrayAttackView view) {
		this.view = view;
		rand = new Random();
		score = 0;
		curIndex = 0;
	}
	
	private void generateRandomUnsorted() {
		array = new ArrayList<>();
		array2 = new ArrayList<>();
		do {
			array.clear();
			array2.clear();
			for(int i = 0; i < 8; i++) {
				array.add(rand.nextInt(100));
				array2.add(null);
			}
		} while(sorted());
	}

	private boolean sorted() {
		for(int i = 0; i < 7; i++) {
			if(array.get(i) > array.get(i+1))
				return false;
		}
		return true;
	}
	
	public int getScore() {
		return score;
	}
	
	//Bubble sort methods
	public void startBubble() {
		generateRandomUnsorted();
		view.updateBubble(array, curIndex, curIndex + 1, score);
	}

	public void runBubble(boolean swap) {
		if(swap)
			swap();
		curIndex++;
		if (sorted()) {
			score++;
			generateRandomUnsorted();
			curIndex = 0;
		}
		else if(curIndex > 6)
			curIndex = 0;
		view.updateBubble(array, curIndex, curIndex + 1, score);	
	}
	
	private void swap() {
		int temp = array.get(curIndex);
		array.set(curIndex, array.get(curIndex + 1));
		array.set(curIndex+1, temp);		
	}
	
	//Merge sort methods
	public void startMerge() {
		generateRandomUnsorted();
		s1i1 = 0;
		s1i2 = 0; 
		s2i1 = 1;
		s2i2 = 1;
		view.updateMerge(array, array2, s1i1, s1i2, s2i1, s2i2, score);	
	}
	
	public void runMerge(boolean left) {
		merge(left);
		if(array.get(s1i2) == null && array.get(s2i2) == null) {
			updateSelected();
		}
		view.updateMerge(array, array2, s1i1, s1i2, s2i1, s2i2, score);	
	}
	
	private void merge(boolean left){
		int index;
		if(left)
			index = s1i1;
		else
			index = s2i1;
		
		Integer val = array.get(index);
		while(val == null) {
			index++;
			if((index > s1i2 && left )|| (index > s2i2 && !left))
				return;
			val = array.get(index);
		}
		array.set(index, null);
		array2.set(curIndex, val);
		curIndex++;
	}

	private void updateSelected() {
		if(s1i1 == 0 && s2i2 == 7) {
			array = array2;
			array2 = new ArrayList<>();
			for(int i = 0; i < 8; i++)
				array2.add(null);
			if(sorted())
				score ++;
				generateRandomUnsorted();
			s1i1 = 0;
			s1i2 = 0; 
			s2i1 = 1;
			s2i2 = 1;
			curIndex = 0;
			return;
		}
		int size = s1i2-s1i1+1;
		if(s2i2 == 7) {
			array = array2;
			array2 = new ArrayList<>();
			for(int i = 0; i < 8; i++)
				array2.add(null);
			s1i1 = 0;
			size = size*2;
			curIndex = 0;
		}
		else {
			s1i1 = s2i2+1;
		}
		s1i2 = s1i1 + size - 1;
		s2i1 = s1i2 + 1;
		s2i2 = s2i1 + size - 1;
		return;
	}
	
	//Quick sort methods
	public void startQuick() {
		generateRandomUnsorted();
		view.updateQuick();
	}
	
	public void runQuick() {
		
	}


}
