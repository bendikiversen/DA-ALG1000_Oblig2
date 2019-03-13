import java.util.*;
public class Oblig2
{
	public static void main(String[] args)
	{
		//Declare and set value of variables representing nodes
		int a = 0, b = 1, c = 2, d = 3, e = 4, f = 5, g = 6, h = 7, i = 8;

		//Create an array representation of the weighted graph
		int[][] array = new int[9][9]; //9*9 array filled with zeros

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
		visualizeGraph(array);

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
	}//visualizeGraph

}//End of Oblig2
