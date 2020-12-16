// Written by: Keanu, Natchev

import java.awt.*;
import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Random;

public class BinaryTreeGUI extends JFrame implements ActionListener {
	// Binary tree object.
	private static BinaryTree tree = new BinaryTree();

	// All traversal strings.
	private static String BFS = "";
	private static String dfsInorder = "";
	private static String dfsPreorder = "";
	private static String dfsPostorder = "";

	// Input field description and label for all BST traversals.
	private JTextField field = new JTextField();
	private JLabel bstTraversals = new JLabel(
			"<html>DFS Inorder: " + dfsInorder + "<br/>" + "DFS Preorder: " + dfsPreorder + "<br/>" + "DFS Postorder: "
					+ dfsPostorder + "<br/>" + "BFS Level Order: " + BFS + "<br/>" + "</html>");

	public BinaryTreeGUI() {
		// Setting the logo, the window size, the background color and the title of the
		// applet.
		this.setIconImage(new ImageIcon(getClass().getResource("logo.png")).getImage());
		setSize(1010, 600);
		getContentPane().setBackground(Color.GRAY);
		setTitle("Binary Search Tree Visualizer");

		// Creating the panels that will be used in the creation of the BST Visualizer.
		JPanel panelSouth = new JPanel();
		JPanel panelEast = new JPanel();

		// Creating all the buttons.
		JButton Add = new JButton("Add");
		JButton Remove = new JButton("Remove");
		JButton randomTree = new JButton("Random");
		JButton clearTree = new JButton("Clear");
		JButton circleBFS = new JButton("Level Order");
		JButton circleDFSInorder = new JButton("Inorder");
		JButton circleDFSPreorder = new JButton("Preorder");
		JButton circleDFSPostorder = new JButton("Postorder");

		// Creating all the panels
		JLabel input = new JLabel("Integer:");
		JLabel traversal = new JLabel("       Traversal:");

		// Changing the background color of all buttons and panels.
		Add.setBackground(Color.WHITE);
		Remove.setBackground(Color.WHITE);
		randomTree.setBackground(Color.WHITE);
		clearTree.setBackground(Color.WHITE);
		circleBFS.setBackground(Color.WHITE);
		circleDFSInorder.setBackground(Color.WHITE);
		circleDFSPreorder.setBackground(Color.WHITE);
		circleDFSPostorder.setBackground(Color.WHITE);
		panelEast.setBackground(Color.GRAY);
		panelSouth.setBackground(Color.GRAY);

		// Setting field size, bstTraversals and input text color to white.
		field.setPreferredSize(new Dimension(100, 25));
		bstTraversals.setForeground(Color.WHITE);
		input.setForeground(Color.WHITE);
		traversal.setForeground(Color.WHITE);

		// Computing all traversals of current BST.
		bfsLevelOrder();
		dfsInorder(tree.root);
		dfsPreorder(tree.root);
		dfsPostorder(tree.root);

		// Adding action of "Add" button when clicked.
		Add.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				// Getting input text.
				String input = field.getText();
				// Splitting input text into its different parts (i.e.: "1 2 3 4 5").
				String[] splited = input.split("\\s+");
				for (int i = 0; i < splited.length; i++) {
					try {
						// Getting each string and converting to integer.
						int addedNode = Integer.parseInt(splited[i]);

						// Checking if integer value falls within the desired values.
						if ((addedNode >= 100) || (addedNode < 0)) {
							throw new Exception();
						} else {
							// Add the valid integer and update applet.
							tree.add(addedNode);
							updateAndRepaint();
						}
					} catch (Exception e2) {
						// Message when input is invalid.
						drawErrorMessage("Input value needs to be an integer between 0 and 99.");
					}
				}
			}
		});

		// Adding action of "Remove" button when clicked.
		Remove.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				// Getting input text.
				String input = field.getText();
				// Splitting input text into its different parts (i.e.: "1 2 3 4 5").
				String[] splited = input.split("\\s+");
				for (int i = 0; i < splited.length; i++) {
					try {
						// Getting each string and converting to integer.
						int addedNode = Integer.parseInt(splited[i]);
						// Checking if integer value falls within the desired values.
						if ((addedNode >= 100) || (addedNode < 0)) {
							throw new Exception();
						} else {
							// Remove the valid integer and update applet.
							tree.remove(addedNode);
							updateAndRepaint();
						}
					} catch (Exception e2) {
						// Message when input is invalid.
						drawErrorMessage("Input value needs to be an integer between 0 and 99.");
					}
				}
			}
		});

		// Adding action of "Random" button when clicked.
		randomTree.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				// Creating a new empty tree and setting the current tree to the empty one.
				BinaryTree clearTree = new BinaryTree();
				tree = clearTree;
				// Adding 10 nodes with values 0 to 99 to the tree.
				for (int i = 0; i < 10; i++) {
					Random rand = new Random();
					tree.add(rand.nextInt(100));
				}
				// Update applet.
				updateAndRepaint();
			}
		});

		// Adding action of "Clear" button when clicked.
		clearTree.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				// Creating a new empty tree and setting the current tree to the empty one.
				BinaryTree clearTree = new BinaryTree();
				tree = clearTree;
				// Update applet.
				updateAndRepaint();
			}
		});

		// Adding action of "Level Order" button when clicked.
		circleBFS.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				// Getting BFS string and creating an array of all the integers.
				String[] splited = BFS.split("\\s+");
				// Integer variable size that represents an integer element of the BFS string.
				int size = 0;
				for (int i = 0; i < splited.length; i++) {
					// Checking for empty elements of splited.
					if (splited[i].length() != 0) {
						// Setting size to current integer value, painting empty circle with integer
						// value in white, and painting black node with white text.
						size = Integer.parseInt(splited[i]);
						emptyCircle(size * 4, Color.WHITE, 400);
						Node current = tree.get(tree.root, size);
						drawCircle(current.x, current.y, String.valueOf(size), Color.BLACK, Color.WHITE);
					}
				}
				try {
					// After the completion of the traversal wait 1 second and reset tree and
					// circles to initial state.
					Thread.sleep(1000);
					binaryTreePaint(tree.root, 300, 100, 0, Color.WHITE);
				} catch (Exception e2) {
					// Do nothing.
				}
			}
		});

		// Adding action of "Inorder" button when clicked.
		circleDFSInorder.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				// Getting dfsInorder string and creating an array of all the integers.
				String[] splited = dfsInorder.split("\\s+");
				// Integer variable size that represents an integer element of the BFS string.
				int size = 0;
				for (int i = 0; i < splited.length; i++) {
					// Checking for empty elements of splited.
					if (splited[i].length() != 0) {
						// Setting size to current integer value, painting empty circle with integer
						// value in white, and painting black node with white text.
						size = Integer.parseInt(splited[i]);
						emptyCircle(size * 4, Color.WHITE, 400);
						Node current = tree.get(tree.root, size);
						drawCircle(current.x, current.y, String.valueOf(size), Color.BLACK, Color.WHITE);
					}
				}
				try {
					// After the completion of the traversal wait 1 second and reset tree and
					// circles to initial state.
					Thread.sleep(1000);
					binaryTreePaint(tree.root, 300, 100, 0, Color.WHITE);
				} catch (Exception e2) {
					// Do nothing.
				}
			}
		});

		// Adding action of "Preorder" button when clicked.
		circleDFSPreorder.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				// Getting dfsPreorder string and creating an array of all the integers.
				String[] splited = dfsPreorder.split("\\s+");
				// Integer variable size that represents an integer element of the BFS string.
				int size = 0;
				for (int i = 0; i < splited.length; i++) {
					// Checking for empty elements of splited.
					if (splited[i].length() != 0) {
						// Setting size to current integer value, painting empty circle with integer
						// value in white, and painting black node with white text.
						size = Integer.parseInt(splited[i]);
						emptyCircle(size * 4, Color.WHITE, 400);
						Node current = tree.get(tree.root, size);
						drawCircle(current.x, current.y, String.valueOf(size), Color.BLACK, Color.WHITE);
					}
				}
				try {
					// After the completion of the traversal wait 1 second and reset tree and
					// circles to initial state.
					Thread.sleep(1000);
					binaryTreePaint(tree.root, 300, 100, 0, Color.WHITE);
				} catch (Exception e2) {
					// Do nothing.
				}
			}
		});

		// Adding action of "Postorder" button when clicked.
		circleDFSPostorder.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				// Getting dfsPostorder string and creating an array of all the integers.
				String[] splited = dfsPostorder.split("\\s+");
				// Integer variable size that represents an integer element of the BFS string.
				int size = 0;
				for (int i = 0; i < splited.length; i++) {
					// Checking for empty elements of splited.
					if (splited[i].length() != 0) {
						// Setting size to current integer value, painting empty circle with integer
						// value in white, and painting black node with white text.
						size = Integer.parseInt(splited[i]);
						emptyCircle(size * 4, Color.WHITE, 400);
						Node current = tree.get(tree.root, size);
						drawCircle(current.x, current.y, String.valueOf(size), Color.BLACK, Color.WHITE);
					}
				}
				try {
					// After the completion of the traversal wait 1 second and reset tree and
					// circles to initial state.
					Thread.sleep(1000);
					binaryTreePaint(tree.root, 300, 100, 0, Color.WHITE);
				} catch (Exception e2) {
					// Do nothing.
				}
			}
		});

		// Adding all labels and buttons to south panel.
		panelSouth.add(input);
		panelSouth.add(field);
		panelSouth.add(Add);
		panelSouth.add(Remove);
		panelSouth.add(randomTree);
		panelSouth.add(clearTree);
		panelSouth.add(traversal);
		panelSouth.add(circleDFSInorder);
		panelSouth.add(circleDFSPreorder);
		panelSouth.add(circleDFSPostorder);
		panelSouth.add(circleBFS);
		panelEast.add(bstTraversals);

		// Adding panels to applet.
		this.getContentPane().add(panelEast, BorderLayout.EAST);
		this.getContentPane().add(panelSouth, BorderLayout.SOUTH);
		setVisible(true);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
	}

	/**
	 * Updates the applet.
	 */
	private void updateAndRepaint() {
		BFS = "";
		bfsLevelOrder();
		dfsInorder = "";
		dfsInorder(tree.root);
		dfsPreorder = "";
		dfsPreorder(tree.root);
		dfsPostorder = "";
		dfsPostorder(tree.root);
		bstTraversals.setText("<html>DFS Inorder: " + dfsInorder + "<br/>" + "DFS Preorder: " + dfsPreorder + "<br/>"
				+ "DFS Postorder: " + dfsPostorder + "<br/>" + "BFS Level Order: " + BFS + "<br/>" + "</html>");
		field.setText("");
		repaint();
	}

	/**
	 * Paints all nodes of the binary tree.
	 * 
	 * @param node
	 * @param x
	 * @param y
	 * @param height
	 * @param c
	 */
	public void binaryTreePaint(Node node, int x, int y, int height, Color c) {
		if (node != null) {
			node.x = x;
			node.y = y;
			drawCircle(x, y, Integer.toString(node.integerValue), c, Color.BLACK);
			try {
				Thread.sleep(0);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			emptyCircle(node.integerValue * 4, Color.BLACK, 0);
			if (node.left != null) {
				binaryTreePaint(node.left, (int) (x - (290 / (Math.pow(2, height + 1)))), y + 40, height + 1, c);
			}
			if (node.right != null) {
				binaryTreePaint(node.right, (int) (x + (290 / (Math.pow(2, height + 1)))), y + 40, height + 1, c);
			}
		}
	}

	/**
	 * Paints all the lines between the nodes of the binary tree.
	 * 
	 * @param node
	 * @param x
	 * @param y
	 * @param height
	 */
	public void binaryTreeLinePaint(Node node, int x, int y, int height) {
		if (node != null) {
			if (node.left != null) {
				drawLine(x, y + 10, (int) (x - (300 / (Math.pow(2, height + 1)))), (y + 50));
				binaryTreeLinePaint(node.left, (int) (x - (290 / (Math.pow(2, height + 1)))), y + 40, height + 1);
			}
			if (node.right != null) {
				drawLine(x, y + 10, (int) (x + (300 / (Math.pow(2, height + 1)))), (y + 50));
				binaryTreeLinePaint(node.right, (int) (x + (290 / (Math.pow(2, height + 1)))), y + 40, height + 1);
			}
		}
	}

	/**
	 * Required method for implementing ActionListener.
	 */
	public void actionPerformed(ActionEvent ae) {
	}

	@Override
	public void paint(Graphics g) {
		super.paint(g);
		// Painting all the elements of the applet.
		setBackground(Color.BLACK);
		binaryTreeLinePaint(tree.root, 310, 100, 0);
		binaryTreePaint(tree.root, 300, 100, 0, Color.WHITE);
		BFS = "";
		bfsLevelOrder();
		dfsInorder = "";
		dfsInorder(tree.root);
		dfsPreorder = "";
		dfsPreorder(tree.root);
		dfsPostorder = "";
		dfsPostorder(tree.root);

		bstTraversals.setText("<html>DFS Inorder: " + dfsInorder + "<br/>" + "DFS Preorder: " + dfsPreorder + "<br/>"
				+ "DFS Postorder: " + dfsPostorder + "<br/>" + "BFS Level Order: " + BFS + "<br/>" + "</html>");
		field.setText("");
	}

	/**
	 * Draws a circle at coordinate (x,y) with text value, with circle color c, and
	 * text color cc.
	 * 
	 * @param x
	 * @param y
	 * @param value
	 * @param c
	 * @param cc
	 */
	public void drawCircle(int x, int y, String value, Color c, Color cc) {
		Graphics g = this.getGraphics();
		g.setColor(c);
		g.fillOval(x, y, 20, 20);
		g.setColor(cc);
		if (value.length() == 1) {
			g.drawString(value, x + 5, y + 15);
		} else if (value.length() == 2) {
			g.drawString(value, x + 3, y + 15);
		} else if (value.length() == 3) {
			g.drawString(value, x + 1, y + 15);
		}
	}

	/**
	 * Writes a specified error message at the top of the applet.
	 * 
	 * @param message
	 */
	public void drawErrorMessage(String message) {
		Graphics g = this.getGraphics();
		g.setColor(Color.RED);
		g.drawString(message, 300, 50);
	}

	/**
	 * Paints an empty circle of size size, color c, and pauses the thread for sleep
	 * time.
	 * 
	 * @param size
	 * @param c
	 * @param sleep
	 */
	public void emptyCircle(int size, Color c, int sleep) {
		Graphics g = this.getGraphics();
		try {
			Thread.sleep(sleep);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		g.setColor(c);
		g.drawOval(800 - size / 2, 320 - size / 2, size, size);
	}

	/**
	 * Paints a black line from (x1,y1) to (x2,y2).
	 * 
	 * @param x1
	 * @param y1
	 * @param x2
	 * @param y2
	 */
	public void drawLine(int x1, int y1, int x2, int y2) {
		Graphics g = this.getGraphics();
		g.drawLine(x1, y1, x2, y2);
	}

	/**
	 * Does a BFS Level Order traversal of the tree that updates the BFS string.
	 */
	public static void bfsLevelOrder() {
		int h = height(tree.root);
		for (int i = 1; i <= h; i++)
			bfsGivenLevel(tree.root, i);
	}

	/**
	 * Helper method to bfsLevelOrder that does the traversal and updates the BFS
	 * string.
	 * 
	 * @param bfsNode
	 * @param level
	 */
	public static void bfsGivenLevel(Node bfsNode, int level) {
		if (bfsNode == null)
			return;
		if (level == 1)
			BFS = BFS + " " + Integer.toString(bfsNode.integerValue);
		if (level > 1) {
			bfsGivenLevel(bfsNode.left, level - 1);
			bfsGivenLevel(bfsNode.right, level - 1);
		}
	}

	/**
	 * Returns the height of current node.
	 * 
	 * @param current
	 * @return
	 */
	public static int height(Node current) {
		if (current == null)
			return 0;
		else {
			int leftHeight = height(current.left), rightHeight = height(current.right);
			if (leftHeight > rightHeight) {
				return (leftHeight + 1);
			} else {
				return (rightHeight + 1);
			}
		}
	}

	/**
	 * Does a DFS Inorder traversal of the tree from node node.
	 * 
	 * @param node
	 */
	public static void dfsInorder(Node node) {
		if (node != null) {
			if (node.left != null)
				dfsInorder(node.left);
			dfsInorder = dfsInorder + " " + Integer.toString(node.integerValue);
			if (node.right != null)
				dfsInorder(node.right);
		}
	}

	/**
	 * Does a DFS Preorder traversal of the tree from node node.
	 * 
	 * @param node
	 */
	public static void dfsPreorder(Node node) {
		if (node != null) {
			dfsPreorder = dfsPreorder + " " + Integer.toString(node.integerValue);
			if (node.left != null)
				dfsPreorder(node.left);
			if (node.right != null)
				dfsPreorder(node.right);
		}
	}

	/**
	 * Does a DFS Postorder traversal of the tree from node node.
	 * 
	 * @param node
	 */
	public static void dfsPostorder(Node node) {
		if (node != null) {
			if (node.left != null)
				dfsPostorder(node.left);
			if (node.right != null)
				dfsPostorder(node.right);
			dfsPostorder = dfsPostorder + " " + Integer.toString(node.integerValue);
		}
	}

	public static void main(String[] args) {
		java.awt.EventQueue.invokeLater(new Runnable() {
			public void run() {
				tree.add(4);
				tree.add(5);
				tree.add(2);
				tree.add(1);
				tree.add(3);
				BinaryTreeGUI frame = new BinaryTreeGUI();
			}
		});
	}
}