/**
 * 
 * @author Vamshedhar Reddy Chintala
 */

import java.util.ArrayList;


public class MinHeap {
	private ArrayList<Vertex> vertices;
	
	public MinHeap(){
		this.vertices = new ArrayList<Vertex>();
	}
	
	public MinHeap(ArrayList<Vertex> vertices){
		this.vertices = vertices;
		this.buildMinHeap();
	}
	
	public void swap(int indexA, int indexB){
		Vertex vertexA = this.vertices.get(indexA);
		Vertex vertexB = this.vertices.get(indexB);
		
		vertexB.position = indexA;
		this.vertices.set(indexA, vertexB);
		
		vertexA.position = indexB;
		this.vertices.set(indexB, vertexA);
	}
	
	public void floatUp(int index){
		int parent = ((index + 1) / 2) - 1;
		
		while(parent >= 0 && this.vertices.get(parent).compareTo(this.vertices.get(index)) > 0){
			swap(parent, index);
			index = parent;
			parent = ((index + 1) / 2) - 1;
		}
	}
	
	public void increasePriority(int index, double distance){
		Vertex V = this.vertices.get(index);
		V.distance = distance;
		
		this.floatUp(index);
	}
	
	public void insert(Vertex V){
		
		this.vertices.add(V);
		V.position = this.vertices.size() - 1;
		
		this.floatUp(this.vertices.size() - 1);
	}
	
	public void minHeapify(int index){
		int left = 2 * index + 1;
		int right = 2 * index + 2;
		
		int smallest = index;
		
		if(left < this.vertices.size() && this.vertices.get(left).compareTo(this.vertices.get(index)) < 0){
			smallest = left;
		}
		
		if(right < this.vertices.size() && this.vertices.get(right).compareTo(this.vertices.get(smallest)) < 0){
			smallest = right;
		}
		
		if(smallest != index){
			swap(smallest, index);
			minHeapify(smallest);
		}
	}
	
	public Vertex extractMin(){
		if(this.vertices.size() == 0){
			return null;
		}
		
		Vertex V = this.vertices.get(0);
		
		Vertex last = this.vertices.get(this.vertices.size() - 1);
		
		last.position = 0;
		
		this.vertices.set(0, last);
		
		this.vertices.remove(vertices.size() - 1);
		
		this.minHeapify(0);
		
		return V;
	}
	
	public void buildMinHeap(){
		int middle = (this.vertices.size() + 1) / 2 - 1;
		
		for(int i = middle; i >= 0; i--){
			this.minHeapify(i);
		}
	}

	@Override
	public String toString() {
		return vertices.toString();
	}
}
