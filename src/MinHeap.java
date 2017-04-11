import java.util.ArrayList;


public class MinHeap {
	private ArrayList<Vertex> vertices;
	
	public MinHeap(){
		this.vertices = new ArrayList<Vertex>();
	}
	
	public MinHeap(ArrayList<Vertex> vertices){
		this.vertices = vertices;
		this.BuildMinHeap();
	}
	
	public void swap(int indexA, int indexB){
		Vertex temp = this.vertices.get(indexA);
		this.vertices.set(indexA, this.vertices.get(indexB));
		this.vertices.set(indexB, temp);
	}
	
	public void FloatUp(int index){
		int parent = ((index + 1) / 2) - 1;
		
		while(this.vertices.get(parent).distance > this.vertices.get(index).distance){
			swap(parent, index);
			index = parent;
		}
	}
	
	public void insert(Vertex V){
		this.vertices.add(V);
		if(this.vertices.size() > 1){
			FloatUp(this.vertices.size() - 1);
		}
	}
	
	public void MinHeapify(int index){
		int left = 2 * index + 1;
		int right = 2 * index + 2;
		
		int smallest = index;
		
		if(left < this.vertices.size() && this.vertices.get(left).distance < this.vertices.get(index).distance){
			smallest = left;
		}
		
		if(right < this.vertices.size() && this.vertices.get(right).distance < this.vertices.get(smallest).distance){
			smallest = right;
		}
		
		if(smallest != index){
			swap(smallest, index);
			MinHeapify(smallest);
		}
	}
	
	public Vertex extractMin(){
		if(this.vertices.size() == 0){
			return null;
		}
		
		Vertex V = this.vertices.get(0);
		
		this.vertices.set(0, this.vertices.get(this.vertices.size() - 1));
		
		this.vertices.remove(vertices.size() - 1);
		
		this.MinHeapify(0);
		
		return V;
	}
	
	public void BuildMinHeap(){
		int middle = (this.vertices.size() + 1) / 2 - 1;
		
		for(int i = middle; i >= 0; i--){
			this.MinHeapify(i);
		}
	}

	@Override
	public String toString() {
		return vertices.toString();
	}
}
