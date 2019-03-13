import java.util.*;
public class Oblig2
{
	public static void main(String[] args)
	{
		//VARIABLES

		String welcomeMessage = "Welcome to (program title here)!\n"
		+ "This program will find the most economically efficient edges between vertices in a weighted graph.\n"
		+ "The program is based on Prim's algorithm (Greedy algorithm), using a 2D array representation.";

		//Store user selection in an int
		int selection;

		//String for storing method output
		String MST;

		//Declare price multiplier and unit as final constants
		final int PRICE_MULTIPLIER = 1000;
		final String PRICE_UNIT = "kr";

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

		// PROGRAM FUNCTIONS STARTS BELOW THIS LINE, DO NOT EDIT

		//Welcome message
		System.out.println(welcomeMessage + "\n");

		//Call method taking user data and store in a variable
		selection = userInput(array.length-1);

		//Sen
		MST = calculateMST(array,selection, PRICE_MULTIPLIER, PRICE_UNIT);

		System.out.println("\n" + MST);


	}//End of main

	//Prompts user for starting vertex
	public static int userInput(int limit)
	{
		//Use a Scanner for user interaction
		Scanner input = new Scanner(System.in);
		int data;

		do
		{
			System.out.print("Select starting vertex for MST (" + (char) (65) + "-" + (char) (65+limit) + "): ");

			//Takes a String input, converts it to uppercase and selects the first (index 0) character
			//the char is converted to an int and subtracts by 65 (ASCII-value of A). A = 0, B = 1, C = 2...
			data = (int) input.next().toUpperCase().charAt(0) - 65;

			//Prints an error if the input is out of bounds
			if(!(data >= 0 && data <= limit))
				System.out.println("Starting vertex is out of bounds, try again!\n"
								+"Please use a single character only.\n");
		}while(!(data >= 0 && data <= limit));

		return data;
	}

	//Calculate minimum spanning tree with root at specified vertex
	//Returns a String with information about MST cost and edges
	public static String calculateMST(int[][] graph, int startingVertex, int multiplier, String unit)
	{
		int from = -1, to = -1, cost = 0;
		String edges = "";
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
			edges += "From " + (char) (65+from) + " to " + (char) (65+to) + "\tcost: " + min*multiplier + unit + "\n";

		}

		return "Minimum Spanning Tree (MST) from " + (char) (65+startingVertex)
				+ "\nThe total cost is " + cost + " " + unit +"\n\nUsing these edges:\n" + edges;
	}//End of calculateMST

}//End of Oblig2
