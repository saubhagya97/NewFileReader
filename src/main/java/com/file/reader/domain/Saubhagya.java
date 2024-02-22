package com.file.reader.domain;

import java.util.ArrayList;
import java.util.EmptyStackException;

public class Saubhagya<T> {

	private ArrayList<T> arr;
	private int index = 0;

	public Saubhagya() {
		arr = new ArrayList<>();
	}

	public void push(T element) {
		arr.add(element);
		index++;
	}

	public T pop() {
		if (isEmpty()) {
			throw new EmptyStackException();
		}
		index--;
		return arr.remove(index);
	}

	public boolean isEmpty() {
		return index == 0;
	}

	public int size() {
		return index;
	}
}
