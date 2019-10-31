package model;

import java.util.ArrayList;
import java.util.Random;

import view.ArrayAttackView;

public class ArrayAttackModel {
	
	private ArrayList<Integer> array;
	private ArrayAttackView view;
	private int curIndex, score;
	private Random rand;

	public ArrayAttackModel(ArrayAttackView view) {
		this.view = view;
		rand = new Random();
		score = 0;
		curIndex = 0;
	}
	
	private void generateRandomUnsorted() {
		array = new ArrayList<>();
		do {
			for(int i = 0; i < 8; i++)
				array.add(rand.nextInt(100));
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
		view.updateMerge();	
	}
	
	//Quick sort methods
	public void startQuick() {
		generateRandomUnsorted();
		view.updateQuick();
	}


}
