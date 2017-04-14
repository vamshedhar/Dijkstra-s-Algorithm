/**
 * 
 * @author Vamshedhar Reddy Chintala
 */

import java.util.ArrayList;

/**
 * Implementation of Min-Heap for vertices
 * with priority based on their distances from the source vertex
 * 
 * @author vamshedhar
 *
 */
public class MinHeap {
	private ArrayList<Vertex> vertices;		// List of vertices
	
	/**
	 * Initialize Min-Heap with no elements
	 */
	public MinHeap(){
		this.vertices = new ArrayList<Vertex>();
	}
	
	/**
	 * Initialize Binary tree with List of vertices and build Min-Heap
	 */
	public MinHeap(ArrayList<Vertex> vertices){
		this.vertices = vertices;
		this.buildMinHeap();
	}
	
	/**
	 * Swaps both the vertex position in the array
	 * 
	 * @param indexA Vertex A index
	 * @param indexB Vertex B index
	 */
	private void swap(int indexA, int indexB){
		Vertex vertexA = this.vertices.get(indexA);
		Vertex vertexB = this.vertices.get(indexB);
		
		vertexB.position = indexA;
		this.vertices.set(indexA, vertexB);
		
		vertexA.position = indexB;
		this.vertices.set(indexB, vertexA);
	}
	
	/**
	 * Float the vertex up the Heap till we restore it to valid Min-Heap
	 * 
	 * @param index
	 */
	private void floatUp(int index){
		int parent = ((index + 1) / 2) - 1;
		
		while(parent >= 0 && this.vertices.get(parent).compareTo(this.vertices.get(index)) > 0){
			swap(parent, index);
			index = parent;
			parent = ((index + 1) / 2) - 1;
		}
	}
	
	/**
	 * Increase priority of a Vertex in the Min-Heap
	 * Updates distance of the Vertex from source vertex
	 * 
	 * @param index 	Index of the vertex in the Heap ArrayList
	 * @param distance  New distance of the source vertex
	 */
	public void increasePriority(int index, double distance){
		Vertex V = this.vertices.get(index);
		V.distance = distance;
		
		this.floatUp(index);
	}
	
	/**
	 * Insert new vertex to the heap and float up till valid Min-Heap is formed
	 * 
	 * @param V New vertex to the Heap
	 */
	public void insert(Vertex V){
		
		this.vertices.add(V);
		V.position = this.vertices.size() - 1;
		
		this.floatUp(this.vertices.size() - 1);
	}
	
	/**
	 * Restore to min heap by floating the element at given index down the tree to its valid position
	 * 
	 * @param index
	 */
	private void minHeapify(int index){
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
	
	/**
	 * Extract the Vertex with least distance from the source vertex.
	 * Restore to a valid Min-Heap.
	 * @return
	 */
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
	
	
	/**
	 * Convert a Binary tree to a valid Min-Heap
	 */
	private void buildMinHeap(){
		int middle = (this.vertices.size() + 1) / 2 - 1;
		
		for(int i = middle; i >= 0; i--){
			this.minHeapify(i);
		}
	}

	/**
	 * Print Min-Heap
	 */
	@Override
	public String toString() {
		return vertices.toString();
	}
}
