package model;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Random;
import java.util.Timer;
import java.util.TimerTask;

import javafx.application.Platform;
import view.ArrayAttackView;

public class ArrayAttackModel {
	
	private static int INTERVAL_MAX = 120;
	
	private ArrayList<Integer> array;
	private ArrayAttackView view;
	private int score;
	private Random rand;
	private int curIndex;
	private int interval;

	//Merge variables
	private ArrayList<Integer> array2;
	private int s1i1, s1i2, s2i1, s2i2;
	
	//Quick variables
	private int quickIndex, pivot;
	private LinkedList<ArrayList<Integer>> toPartition;
	private ArrayList<Integer> arrayLeft;
	private ArrayList<Integer> arrayRight;
	
	public ArrayAttackModel(ArrayAttackView view) {
		this.view = view;
		rand = new Random();
		score = 0;
		curIndex = 0;
		interval = 60;
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
		view.updateBubble(array, curIndex, curIndex + 1, score, interval);
		setTimer();
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
		view.updateBubble(array, curIndex, curIndex + 1, score, interval);	
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
		view.updateMerge(array, array2, s1i1, s1i2, s2i1, s2i2, score, interval);
		setTimer();
	}
	
	public void runMerge(boolean left) {
		merge(left);
		if(array.get(s1i2) == null && array.get(s2i2) == null) {
			updateSelected();
		}
		view.updateMerge(array, array2, s1i1, s1i2, s2i1, s2i2, score, interval);	
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
			if(sorted()) {
				score ++;
				generateRandomUnsorted();
			}
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
		pivot = getPivot(array);
		quickIndex = 0;
		toPartition = new LinkedList<>();
		array2 = array;
		array = new ArrayList<>();
		arrayLeft = new ArrayList<>();
		arrayRight = new ArrayList<>();
		view.updateQuick(array2, compositeArray(), pivot, score, interval);
		setTimer();
	}

	public void runQuick(boolean left) {
		if (left)
			arrayLeft.add(array2.remove(0));
		else
			arrayRight.add(array2.remove(0));
		if(array2.size() == 0) {
			toPartition.add(arrayRight);
			toPartition.add(arrayLeft);
			arrayLeft = new ArrayList<>();
			arrayRight  = new ArrayList<>();
			array2 = toPartition.pollLast();
			while(array2 != null && array2.size() <= 1) {
				if(array2.size() != 0) {
					array.add(quickIndex, array2.get(0));
					quickIndex++;
				}
				array2 = toPartition.pollLast();
			}
			if(array2 == null) {
				if(sorted()) {
					score++;
					generateRandomUnsorted();
				}
				quickIndex = 0;
				toPartition = new LinkedList<>();
				array2 = array;
				array = new ArrayList<>();
				arrayLeft = new ArrayList<>();
				arrayRight = new ArrayList<>();
			}
			pivot = getPivot(array2);
		}
		view.updateQuick(array2, compositeArray(), pivot, score, interval);
	}

	private int getPivot(ArrayList<Integer> array) {
		if (array == null || array.size() == 0)
			return -1;
		int v1 = array.get(0);
		int v2 = array.get(array.size()/2);
		int v3 = array.get(array.size()-1);
		if(v1 >= v2) {
			if (v2 >= v3)
				return v2;
			else if (v1 >= v3)
				return v3;
			else
				return v1;
		}
		else {
			if(v2 <= v3)
				return v2;
			else if (v1 <= v3)
				return v3;
			else
				return v1;
		}
	}
	
	private ArrayList<Integer> compositeArray() {
		ArrayList<Integer> comp = new ArrayList<>();
		comp.addAll(array);
		comp.addAll(arrayLeft);
		comp.addAll(array2);
		comp.addAll(arrayRight);
		return comp;
	}
	
	private void setTimer() {
		interval = INTERVAL_MAX;
		Timer timer = new Timer(true);

		timer.scheduleAtFixedRate(new TimerTask() {
			public void run() {
				if(interval > 0) {
					interval--;
					Platform.runLater(() -> view.setTimerText(interval));
					
				} else {
					timer.cancel();
					view.timeUpScreen(score);
					System.out.println("0 reached, break");
					interval = INTERVAL_MAX;
				}
			}
		}, 1000, 1000);
	}

}
