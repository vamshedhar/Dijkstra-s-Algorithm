import java.util.ArrayList;
import java.util.Collections;

public class Vertex implements Comparable<Vertex>{
	public String name;
	public ArrayList<Edge> adjacent;
	public Vertex previous;
	public double distance;
	public boolean active;
	
	// these arguments are for performing BFS/DFS
	public String color;
	
	// to store position in MinHeap
	public int position;
	
	public Vertex(String name) {
		this.name = name;
		this.adjacent = new ArrayList<Edge>();
		this.active = true;
		this.reset();
	}

	public boolean isAdjacent(Vertex V){
		return this.adjacent.contains(V);
	}
	
	public void reset(){
		this.distance = Double.POSITIVE_INFINITY;
		this.previous = null;
		this.color = "White";
	}
	
	@Override
	public int compareTo(Vertex V) {
		// TODO Auto-generated method stub
		return Double.compare(this.distance, V.distance);
	}
	
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((name == null) ? 0 : name.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Vertex other = (Vertex) obj;
		if (name == null) {
			if (other.name != null)
				return false;
		} else if (!name.equals(other.name))
			return false;
		return true;
	}
	
	@Override
	public String toString() {
		String output = this.name;
		
		if(!this.active){
			output += " DOWN";
		}
		
		output += "\n";
		
		Collections.sort(this.adjacent);
		
		for(Edge e : this.adjacent){
			output += e.toString();
			output += "\n";
		}
		
		
		return output;
	}
	
}
