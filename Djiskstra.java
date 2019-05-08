import java.util.ArrayList;
import java.util.List;

public class Djiskstra {
	
// Initializing all the global variables(All variables are self explanatory)
// No of vertices is hard-coded and so is the startNode
// Value could be changed directly from the global variable 
	public static final int noOfVertices = 6;
	public static int[][] costGraph = new int[noOfVertices][noOfVertices];
	public static int [] finalValues = new int [noOfVertices];
	public static int [] finalNodes = new int [noOfVertices];
	public static List<Integer> visited = new ArrayList<>();
	public static List<Integer> unvisited = new ArrayList<>();
	public static int stepNO = 0 ;
	public static final int startNode = 0 ;
	
	//Initializing the graph(adjacency matrix)
	//The cost to same node is 0 and to all other nodes is 100(high value)
	public static void initialize() {
		int i,j;
		for(i=0;i<noOfVertices;i++)
		{
			for(j=0;j<noOfVertices;j++) {
				if(i==j) {
					costGraph[i][j] = 0;
				}
				else {
					costGraph[i][j] = 100;
				}
			}
		}
	}
	
	//Could be used to print the graph (adjacency matrix)	
	public static void printGraph() {
		int i,j;
		for(i=0;i<noOfVertices;i++)
		{
			for(j=0;j<noOfVertices;j++) {
				System.out.print(costGraph[i][j]+"\t");
			}
			System.out.println("\n");
		}
	}
	//Printing the first row (Header)
	public static void printFirst() {
		int i;
		System.out.print("Step\t"+"VisitedNodes\t");
		for(i=0;i<noOfVertices;i++) {
				System.out.print("D("+intToChar(i)+"),p("+intToChar(i)+")\t");
			}
		System.out.println("\n");
	}
	// Printing the Table after each and every step
	public static void printTable() {
		int i;
		stepNO++;
		System.out.print(stepNO+"\t");
		for(i=0;i<visited.size();i++)
		System.out.print(intToChar(visited.get(i)));
		System.out.print("\t\t   ");
		for(i=0;i<noOfVertices;i++) {
				System.out.print(finalValues[i]+","+intToChar(finalNodes[i])+"\t\t");
			}
		System.out.println("\n");
	}
	// Adding the Edge to the graph
	public static void addEdges(int from , int to, int cost) {
		if(from != to) {
		costGraph[from][to] = cost;
		costGraph[to][from] = cost;
		}
		else {
			System.err.println("Adding for same node");
		}
	}
	//Initializing the visited and unvisited Lists and calling the djikstra's algorithm
	public static void intdjik(int startNode) {
	
		visited.add(startNode);
		for(int i=0;i<noOfVertices;i++) {
			if(i != startNode) {
				unvisited.add(i);	
			}
			finalValues[i] = costGraph[visited.get(0)][i];
			finalNodes[i] = visited.get(0); 
		}
		printFirst();
		printTable();	
		djikstra();
	}
	//Actual Djikstra's algorithm
	public static void djikstra() {
		while(unvisited.size() >0 ) {
			findMinimum();
			int length = visited.size();
			int index = visited.get(length-1);
			int currentValue = finalValues[index];
			for(int i = 0;i<unvisited.size();i++) {
			int ind = unvisited.get(i);
			int firstValue = finalValues[ind];
			int secondValue = costGraph[index][ind];
			if(firstValue > (currentValue+secondValue)) {
				finalValues[ind] = currentValue+secondValue;
				finalNodes[ind] = index; 
				}
			}
	printTable();
		}
			
	}
	// Finding the minimum value
	public static void findMinimum() {
		int min = 100;
		int minNode = 0;
		for(int i = 0; i<noOfVertices;i++) {
			int value = 100;
			if(!visited.contains(i)) {
				value = finalValues[i];
				if(value < min) {
				min = value;	
				minNode = i;	
				}
			}
		}
		visited.add(minNode);
		for(int i=0;i<unvisited.size();i++) {
			int value = unvisited.get(i);
			if(value == minNode) {
				unvisited.remove(i);
			}
		}		
	}
	
	//Used to convert indices to characters
	public static String intToChar(int index) {
		
		switch(index) {
		//case 0 : return "T";
		case 0 : return "U";
		case 1 : return "V";
		case 2: return "W";
		case 3 : return "X";
		case 4: return "Y";
		case 5: return "Z";
			default: return "Invalid"; 
		}
	
	}
	

	//Main function to add edges and call initialize methods
	public static void main(String[] args) {
		
		initialize();
		addEdges(0, 1, 2);
		addEdges(0, 2, 5);
		addEdges(0, 3, 1);
		addEdges(1, 2, 3);
		addEdges(1, 3, 2);
		addEdges(2, 3, 3);
		addEdges(2, 4, 1);
		addEdges(2, 5, 5);
		addEdges(3, 4, 1);
		addEdges(4, 5, 2);
		intdjik(startNode);
		
	}
}
