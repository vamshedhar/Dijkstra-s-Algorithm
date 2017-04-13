/**
 * 
 * @author Vamshedhar Reddy Chintala
 */

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.Scanner;
import java.util.StringTokenizer;

public class Graph {

	public HashMap<String, Vertex> vertices = new HashMap<String, Vertex>();
	
	public Vertex getVertex(String name){
		Vertex V = this.vertices.get(name);
		
		if(V == null){
			V = new Vertex(name);
			this.vertices.put(name, V);
		}
		
		return V;
	}
	
	public void addEdge(String tailVertexName, String headVertexName, double weight){
		Vertex tailVertex = this.getVertex(tailVertexName);
		Vertex headVertex = this.getVertex(headVertexName);
		
		Edge E = new Edge(tailVertex, headVertex, weight);
		
		int index = tailVertex.adjacent.indexOf(E);
		if(index == -1){
			tailVertex.adjacent.add(E);
		} else{
			tailVertex.adjacent.get(index).weight = E.weight;
		}
	}
	
	
	public void deleteEdge(String tailVertexName, String headVertexName){
		Vertex tailVertex = this.vertices.get(tailVertexName);
		Vertex headVertex = this.vertices.get(headVertexName);
		
		if(tailVertex == null){
			System.out.println("Invalid Edge. Source vertex not found!");
		} else if(headVertex == null){
			System.out.println("Invalid Edge. Destination vertex not found!");
		} else{
			Edge E = new Edge(tailVertex, headVertex, 0.0);
			tailVertex.adjacent.remove(E);
		}
		
		
	}
	
	/**
     * @param String TailVertexName
     * @param String HeadVertexName
     * 
     * Updates the status of the Edge to specified status
     */
	public void updateEdgeStatus(String tailVertexName, String headVertexName, boolean status){
		Vertex tailVertex = this.vertices.get(tailVertexName);
		Vertex headVertex = this.vertices.get(headVertexName);
		
		if(tailVertex == null){
			System.out.println("Invalid Edge. Source vertex not found!");
		} else if(headVertex == null){
			System.out.println("Invalid Edge. Destination vertex not found!");
		} else{
			Edge E = new Edge(tailVertex, headVertex, 0.0);
			
			int index = tailVertex.adjacent.indexOf(E);
			if(index == -1){
				System.out.println("Edge not found!");
			} else{
				tailVertex.adjacent.get(index).active = status;
			}
		}
	}
	
	/**
     * @param String TailVertexName
     * @param String HeadVertexName
     * 
     * Marks the Edge DOWN
     */
	public void edgeDown(String tailVertexName, String headVertexName){
		this.updateEdgeStatus(tailVertexName, headVertexName, false);
	}
	
	/**
     * @param String TailVertexName
     * @param String HeadVertexName
     * 
     * Marks the Edge UP
     */
	public void edgeUp(String tailVertexName, String headVertexName){
		this.updateEdgeStatus(tailVertexName, headVertexName, true);
	}
	
	/**
     * @param String VertexName
     * @param Boolean Status
     * 
     * Updates the status of the Vertex to specified status
     */
	public void updateVertexStatus(String vertexName, boolean status){
		Vertex V = this.vertices.get(vertexName);
		
		if(V == null){
			System.out.println("Vertex not found!");
			return;
		}
		
		V.active = status;
	}
	
	/**
     * @param String VertexName
     * 
     * Marks the Vertex DOWN
     */
	public void vertexDown(String vertexName){
		this.updateVertexStatus(vertexName, false);
	}
	
	/**
     * @param String VertexName
     * 
     * Marks the Vertex UP
     */
	public void vertexUp(String vertexName){
		this.updateVertexStatus(vertexName, true);
	}
	
	public void printReachable(){
		ArrayList<String> vertexNames = new ArrayList<String>(this.vertices.keySet());
		
		Collections.sort(vertexNames);
		
		for(String vertexName : vertexNames){
			Vertex vertex = this.vertices.get(vertexName);
			if(vertex.active){
				Reachable reachable = new Reachable(this, vertex);
				System.out.println(reachable);
			}	
		}
	}
	
	@Override
	public String toString() {
		
		ArrayList<String> vertexNames = new ArrayList<String>(this.vertices.keySet());
		
		Collections.sort(vertexNames);
		
		String output = "";
		
		for(String vertex : vertexNames){
			output += this.vertices.get(vertex);
		}
		
		return output;
	}
	
	public static void main(String[] args){
		// System arguments. Will change
		String filename = "network.txt";
		
		Graph G = new Graph();
		
		try{
			// Read data from file
			BufferedReader reader = new BufferedReader(new FileReader(filename));
			
			String line;
			while((line = reader.readLine()) != null) {
				String[] lineData = line.split(" ");
				
				G.addEdge(lineData[0], lineData[1], Double.parseDouble(lineData[2]));
				G.addEdge(lineData[1], lineData[0], Double.parseDouble(lineData[2]));
			}
			
			reader.close();
		} catch(IOException e){
			System.err.println(e);
		}
		
		Scanner s = new Scanner(System.in);
		
		while(true){
			String command = s.nextLine();
			
			StringTokenizer st = new StringTokenizer(command);
			
			int tokenCount = st.countTokens();
			
			if(tokenCount == 0){
				continue;
			}
			
			String query = st.nextToken();
			
			if(query.equals("quit") && tokenCount == 1){
				s.close();
				return;
			} else if(query.equals("print") && tokenCount == 1){
				System.out.println(G);
			} else if(query.equals("reachable") && tokenCount == 1){
				G.printReachable();
			} else if(query.equals("addedge") && tokenCount == 4){

				String tailVertex = st.nextToken();
				String headVertex = st.nextToken();
				
				try{
					Double weight = Double.parseDouble(st.nextToken());
					G.addEdge(tailVertex, headVertex, weight);
				} catch(NumberFormatException e){
					System.out.println("Invalid Transmission Time!");
				}
				

			} else if(query.equals("deleteedge") && tokenCount == 3){

				String tailVertex = st.nextToken();
				String headVertex = st.nextToken();
				G.deleteEdge(tailVertex, headVertex);

			} else if(query.equals("edgedown") && tokenCount == 3){

				String tailVertex = st.nextToken();
				String headVertex = st.nextToken();
				G.edgeDown(tailVertex, headVertex);

			} else if(query.equals("edgeup") && tokenCount == 3){

				String tailVertex = st.nextToken();
				String headVertex = st.nextToken();
				G.edgeUp(tailVertex, headVertex);

			} else if(query.equals("vertexdown") && tokenCount == 2){

				String vertex = st.nextToken();
				G.vertexDown(vertex);

			} else if(query.equals("vertexup") && tokenCount == 2){

				String vertex = st.nextToken();
				G.vertexUp(vertex);

			} else if(query.equals("path") && tokenCount == 3){

				Dijkstra dj = new Dijkstra(G);
				String startVertex = st.nextToken();
				String endVertex = st.nextToken();
				dj.findShortestPath(startVertex, endVertex);
				
			} else{
				System.out.println("Invalid Command!");
			}
		}
	
	}
}
