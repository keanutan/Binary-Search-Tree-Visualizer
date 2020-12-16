// Written by: Keanu, Natchev

public class Node {
	int integerValue;
	int x = 0;
	int y = 0;
	Node left;
	Node right;
	
	Node(){
		right = null;
		left = null;
	}
	
	Node(int value) {
		this.integerValue = value;
		right = null;
		left = null;
	}
}
