import java.util.*;

/**
* The Oblig2 program implements an application that
* calculates a minimum spanning tree (Prim's algorithm)
* from an undirected, weighted graph.
*
* @author  Bendik Iversen
* @version 1.0
* @since   27.03.2019
*/

public class Oblig2
{
	public static void main(String[] args)
	{
		/* ========== SYSTEM VARIABLES, DO NOT EDIT!  ========== */

		//Store user selection in an int
		int selection;

		//String for storing calulated MST
		String mst;

		/* ========== SYSTEM VARIABLES END ========== */

		/* ========== SITUATION VARIABLES ========== */

		//Declare price multiplier and unit as final constants
		final int PRICE_MULTIPLIER = 1000;
		final String PRICE_UNIT = "kr";

		//Welcome message to be printed at launch
		final String welcomeMessage = "Welcome to Oblig 2 - MST!\n"
		+ "This program computes the most economically edges \n"
		+ "between vertices in an undirected, weighted graph.\n"
		+ "The program is based on Prim's algorithm (Greedy algorithm).";

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

		/* ========== SITUATION-SPECIFIC VARIABLES END ========== */

		/* PROGRAM FUNCTIONS STARTS BELOW THIS LINE, DO NOT EDIT! */

		//Greet user with welcome message
		System.out.println(welcomeMessage + "\n");

		//Call method taking user data and store it in a variable
		selection = userInput(array.length);

		//Call computeMST method passing array, starting vertex and price properties
		mst = computeMST(array, selection, PRICE_MULTIPLIER, PRICE_UNIT);

		//Print MST
		System.out.println("\n" + mst);

		//Program finished
		System.out.println("\nProgram complete, exiting...");


	}//End of main

	//Prompts user for starting vertex
	public static int userInput(int limit)
	{
		//Use a Scanner for user interaction
		Scanner input = new Scanner(System.in);
		int data;

		char a = (char) (65), b = (char) (65+limit-1);

		do
		{
			System.out.print("Select starting vertex for MST (" + a + "-" + b + "): ");

			//Takes a String input, converts it to uppercase and selects the first (index 0) character
			//the char is converted to an int and subtracts by 65 (ASCII-value of A). A = 0, B = 1, C = 2...
			data = (int) input.next().toUpperCase().charAt(0) - 65;
			//REPORT//					STRING			CHAR	INT

			//Prints an error if the input is out of bounds
			if(!(data >= 0 && data < limit))
				System.out.println("Starting vertex is out of bounds, try again!\n"
					+"Please use a single character only (" + a + "-" + b + ").\n");
		}while(!(data >= 0 && data < limit));

		return data;
	}//End of userInput

	//Calculate minimum spanning tree with root at specified vertex
	//Returns a String with information about MST cost and edges
	public static String computeMST(int[][] graph, int startingVertex, int multiplier, String unit)
	{
		//int variables storing path and total cost
		int current = 0, next = 0, cost = 0;

		//String storing the used edges in graph
		String edges = "";

		//Use an ArrayList to store the visited vertices
		List<Integer> unity = new ArrayList<>();

		//Add the starting vertex to unity
		unity.add(startingVertex);

		//Repeat operations until unity contains all vertices in graph
		while(unity.size() < graph.length)
		{
			//Temporary int for minimum value
			//The constant Integer.MAX_VALUE is largest int
			int min = Integer.MAX_VALUE;

			//Rows: from-vertex in unity
			for(int u = 0; u < unity.size(); u++)
			{
				//Get rows in unity
				int r = unity.get(u);

				//Colums: to-vertex
				for(int c = 0; c < graph[r].length; c++)
				{
					//Vertex not in unity and 0 < edge < min
					if(!unity.contains(c) && graph[r][c] < min && graph[r][c] != 0)
					{
						min = graph[r][c];
						current = r;
						next = c;
					}
				}//End of to (columns)
			}//End of from (rows in unity)

			//Found the most economic edge, add minimum cost
			cost += min*multiplier;

			//Remove the edge from graph (must set both ways)
			graph[current][next] = graph[next][current] = 0;

			//Add the new vertex to unity
			unity.add(next);

			//Add edge to history
			edges += "From " + (char) (65+current) + " to " + (char) (65+next) + "\tcost: " + min*multiplier + " " + unit + "\n";

		}

		//Build MST result and return to calling method
		return "Minimum Spanning Tree (MST) from " + (char) (65+startingVertex)
				+ ":\nThe total cost is " + cost + " " + unit +"\n\nUsing these edges:\n" + edges;
	}//End of calculateMST

}//End of Oblig2
