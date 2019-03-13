import java.util.*;
public class Oblig2
{
	public static void main(String[] args)
	{
		String welcomeMessage = "Welcome to MST!\n";

		//Use a Scanner for user interaction
		Scanner input = new Scanner(System.in);
		int selection;

		//Declare price multiplier as a final constant
		final int PRICE_MULTIPLIER = 1000;

		//Create an array representation of the weighted graph
		int[][] array = new int[9][9]; //9*9 array filled with zeros

		//Declare and set value of variables representing vertices
		int a = 0, b = 1, c = 2, d = 3, e = 4, f = 5, g = 6, h = 7, i = 8;

		//Add the graph edges to array
		array[a][b] = array[b][a] = 9;	//A - B
		array[a][f] = array[f][a] = 14;	//A - F
		array[b][c] = array[c][b] = 10;	//B - C
		array[b][e] = array[e][b] = 14;	//B - E
		array[b][f] = array[f][b] = 12; //B - F
		array[c][d] = array[d][c] = 8;	//C - D
		array[c][e] = array[e][c] = 13; //C - E
		array[d][e] = array[e][d] = 16; //D - E
		array[d][h] = array[h][d] = 17;	//D - H
		array[e][g] = array[g][e] = 15;	//E - G
		array[e][i] = array[i][e] = 11; //E - I
		array[f][g] = array[g][f] = 5;	//F - G
		array[f][i] = array[i][f] = 7;	//F - I
		array[g][h] = array[h][g] = 12; //G - H
		array[g][i] = array[i][g] = 6;	//G - I

		//Debugging: Check values in array
		//visualizeGraph(array);

		//Welcome message
		System.out.println(welcomeMessage);

		do
		{
			System.out.print("Select starting vertex (" + (char) (65) + "-" + (char) (65+array.length-1) + "): ");

			//Takes a String input, selects the 0th character (uppercase)
			//and converts it to an int and subtracts by 65 (A)
			selection = (int) input.next().toUpperCase().charAt(0)-65;

			//Prints an error if the input is out of bounds
			if(!(selection >= 0 && selection <= array.length-1))
				System.out.println("Starting vertex is out of bounds, try again!\n");
		}while(!(selection >= 0 && selection <= array.length-1));

		System.out.println("\n" + calculateMST(array,selection, PRICE_MULTIPLIER));


	}//End of main

	//Method to visualize the weighted graph and paths
	public static void visualizeGraph(int[][] graph)
	{
		//Print column names for graph:
		//Converts corresponding int ASCII value to a CHAR
		for(int i = 0; i<graph.length; i++)
			System.out.print("\t[" + (char) (65+i) + "]");
		System.out.print("\n\n");

		//Print table rows
		for(int row = 0; row<graph.length; row++) //Loops through rows
		{
			//Print row names for graph, int ASCII -> CHAR
			System.out.print("[" + (char) (65+row) + "]\t");

			//Fills the columns in each row
			for(int col = 0; col<graph[row].length; col++)
			if(graph[row][col] == 0)
				System.out.print(" .\t");
			else
				System.out.print(" " + graph[row][col] + "\t");

			//Spacing for next row
			System.out.print("\n\n\n");
		}
	}//End of visualizeGraph

	//Calculate minimum spanning tree with root at specified vertex
	//Returns a String with information about MST cost and path
	public static String calculateMST(int[][] graph, int startingVertex, int multiplier)
	{
		int from = -1, to = -1, cost = 0;
		String paths = "";
		List<Integer> unity = new ArrayList<>();
		unity.add(startingVertex);

		//Repeat operations until unity contains all vertices in graph
		while(unity.size() < graph.length)
		{
			//Temporary int for minimum value
			//Using the constant Integer.MAX_VALUE for comparison
			int min = Integer.MAX_VALUE;

			//Rows: from-vertex
			for(int r = 0; r < graph.length; r++)
			{
				//Check whether the current from-vertex is in the unity
				if(unity.contains(r))
				{
					//Colums: to-vertex
					for(int c = 0; c < graph[r].length; c++)
					{
						//Vertex not in unity and 0 < edge < min
						if(!unity.contains(c) && graph[r][c] < min && graph[r][c] != 0)
						{
							min = graph[r][c];
							from = r;
							to = c;
						}
					}//End of to (columns)
				}
			}////End of from(rows)

			//Has found cheapest path
			cost += min*multiplier;
			graph[from][to] = graph[to][from] = 0;
			unity.add(to);
			paths += "From " + (char) (65+from) + " to " + (char) (65+to) + "\tcost: " + min + "\n";

		}

		return "Minimum Spanning Tree (MST) from " + (char) (65+startingVertex)
				+ "\nThe total cost is: " + cost + " kr\n\nUsing these edges:\n" + paths;
	}//End of calculateMST

}//End of Oblig2
