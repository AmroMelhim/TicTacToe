import java.awt.EventQueue;

import javax.swing.JFrame;
import java.awt.BorderLayout;
import javax.swing.JPanel;
import javax.swing.border.BevelBorder;
import javax.swing.border.EmptyBorder;
import javax.swing.border.LineBorder;
import java.awt.Color;
import java.awt.GridLayout;
import java.awt.List;

import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JButton;
import java.awt.Font;
import javax.swing.SwingConstants;
import javax.swing.JTextField;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Random;
import java.awt.event.ActionEvent;
import javax.swing.JLayeredPane;
import javax.swing.JTabbedPane;
import java.awt.CardLayout;

public class TicTacToe extends JFrame implements ActionListener {

	JFrame frame;
	String startGame = "X";
	JButton[] multi = new JButton[9];
	JButton[] easy = new JButton[9];
	JButton[] hard = new JButton[9];
	JPanel title_panel = new JPanel();
	static JLabel textfield = new JLabel();
	char mode;
	static char win = 't';
	static int move = 0;
	boolean player1_turn = false;
	boolean ai_turn = true;

	private JPanel contentPane;
	private JPanel Menu;
	private JPanel multiPanel;
	private JPanel easyPanel;
	private JPanel hardPanel;
	static char[][] board = { { 0, 0, 0 }, { 0, 0, 0 }, { 0, 0, 0 } };
	static char player = 'O', opponent = 'X';

	static class Move {
		int row, col;
	};

	// Launch the application.

	public static void main(String[] args) {

		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					TicTacToe frame = new TicTacToe();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	public TicTacToe() {
		initialize();

	}

	// intilialize the board
	public void initialize() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 825, 546);
		contentPane = new JPanel();
		contentPane.setBackground(Color.BLACK);
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);

		JLayeredPane layeredPane = new JLayeredPane();
		layeredPane.setBounds(45, 61, 739, 425);
		contentPane.add(layeredPane);
		layeredPane.setLayout(new CardLayout(0, 0));

		Menu = new JPanel();
		layeredPane.add(Menu, "name_1451010390181500");
		Menu.setLayout(null);

		JLabel menulabel = new JLabel("Main Menu");
		menulabel.setFont(new Font("Tahoma", Font.BOLD, 26));
		menulabel.setHorizontalAlignment(SwingConstants.CENTER);
		menulabel.setBounds(208, 23, 300, 155);
		Menu.add(menulabel);

		JButton btnMulti = new JButton("Multiplayer");
		btnMulti.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				layeredPane.removeAll();
				layeredPane.add(multiPanel);
				layeredPane.repaint();
				layeredPane.revalidate();
				mode = 'm';
			}
		});
		btnMulti.setFont(new Font("Tahoma", Font.PLAIN, 15));
		btnMulti.setBounds(298, 157, 128, 57);
		Menu.add(btnMulti);

		JButton btnEasy = new JButton("Easy");
		btnEasy.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				mode = 'e';
				layeredPane.removeAll();
				layeredPane.add(easyPanel);
				layeredPane.repaint();
				layeredPane.revalidate();

			}
		});
		btnEasy.setFont(new Font("Tahoma", Font.PLAIN, 15));
		btnEasy.setBounds(298, 229, 128, 57);
		Menu.add(btnEasy);

		JButton btnHard = new JButton("Hard");
		btnHard.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				mode = 'h';
				layeredPane.removeAll();
				layeredPane.add(hardPanel);
				layeredPane.repaint();
				layeredPane.revalidate();

			}
		});
		btnHard.setFont(new Font("Tahoma", Font.PLAIN, 15));
		btnHard.setBounds(298, 296, 128, 57);
		Menu.add(btnHard);

		JButton Xplayer1 = new JButton("X");
		Xplayer1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				player1_turn = true;
				ai_turn = false;

			}
		});
		Xplayer1.setBounds(10, 367, 85, 21);
		Menu.add(Xplayer1);

		JButton Oplayer1 = new JButton("O");
		Oplayer1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				player1_turn = false;
				ai_turn = true;

			}
		});
		Oplayer1.setBounds(10, 394, 85, 21);
		Menu.add(Oplayer1);

		JLabel lblNewLabel = new JLabel("First Player");
		lblNewLabel.setFont(new Font("Tahoma", Font.PLAIN, 12));
		lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel.setBounds(10, 332, 85, 21);
		Menu.add(lblNewLabel);

		multiPanel = new JPanel();
		layeredPane.add(multiPanel, "name_1451982353861700");

		easyPanel = new JPanel();
		layeredPane.add(easyPanel, "name_1452014823448900");

		hardPanel = new JPanel();
		layeredPane.add(hardPanel, "name_1452029424713600");

		JButton btnNewButton = new JButton("Back to Main Menu");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				layeredPane.removeAll();
				layeredPane.add(Menu);
				layeredPane.repaint();
				layeredPane.revalidate();
				for (int i = 0; i < 9; i++) {
					multi[i].setText("");
					easy[i].setText("");
					hard[i].setText("");
				}
				mode = 0;
			}
		});
		btnNewButton.setBounds(641, 488, 142, 21);
		contentPane.add(btnNewButton);
		title_panel.setBackground(Color.BLACK);

		title_panel.setBounds(204, 0, 408, 77);
		contentPane.add(title_panel);
		textfield.setBackground(Color.BLACK);
		textfield.setForeground(Color.RED);
		textfield.setFont(new Font("Ink Free", Font.BOLD, 50));
		textfield.setHorizontalAlignment(JLabel.CENTER);
		textfield.setText("Tic-Tac-Toe");
		textfield.setOpaque(true);
		title_panel.add(textfield);

		multiPanel.setLayout(new GridLayout(3, 3));
		multiPanel.setBackground(new Color(150, 150, 150));

		for (int i = 0; i < 9; i++) {
			multi[i] = new JButton();
			multiPanel.add(multi[i]);
			multi[i].setFont(new Font("MV Boli", Font.BOLD, 120));
			multi[i].setFocusable(false);
			multi[i].addActionListener(this);

		}

		easyPanel.setLayout(new GridLayout(3, 3));
		easyPanel.setBackground(new Color(150, 150, 150));

		for (int i = 0; i < 9; i++) {
			easy[i] = new JButton();
			easyPanel.add(easy[i]);
			easy[i].setFont(new Font("MV Boli", Font.BOLD, 120));
			easy[i].setFocusable(false);

			easy[i].addActionListener(this);

		}

		if (player1_turn == false) {
			random_ai(easy);

			easy[move].setForeground(new Color(0, 0, 255));
			easy[move].setText("O");
			player1_turn = true;
			textfield.setText("X turn");
			check(easy);
		}

		hardPanel.setLayout(new GridLayout(3, 3));
		hardPanel.setBackground(new Color(150, 150, 150));

		for (int i = 0; i < 9; i++) {
			hard[i] = new JButton();
			hardPanel.add(hard[i]);
			hard[i].setFont(new Font("MV Boli", Font.BOLD, 120));
			hard[i].setFocusable(false);
			hard[i].addActionListener(this);

		}

		if (ai_turn == true) {
			random_ai(hard);

			toBoard(hard);
			Move Best = findBestMove(board);

			toButton(Best);
			hard[move].setForeground(new Color(0, 0, 255));
			hard[move].setText("O");
			ai_turn = false;
			textfield.setText("X turn");
			check(hard);
			checktie(hard);

			toBoard(hard);
		}

	}

	@Override
	public void actionPerformed(ActionEvent e) {
		// multiplayer mode
		if (mode == 'm') {
			for (int i = 0; i < 9; i++) {
				if (e.getSource() == multi[i]) {
					if (player1_turn) {
						if (multi[i].getText() == "") {
							multi[i].setForeground(new Color(255, 0, 0));
							multi[i].setText("X");
							player1_turn = false;
							textfield.setText("O turn");
							check(multi);
							checktie(multi);
						}
					} else {

						if (multi[i].getText() == "") {
							multi[i].setForeground(new Color(0, 0, 255));
							multi[i].setText("O");
							player1_turn = true;
							textfield.setText("X turn");
							check(multi);
							checktie(multi);
						}
					}
				}
			}
		}
		// easy mode
		else if (mode == 'e') {

			if (player1_turn) {
				for (int i = 0; i < 9; i++) {

					if (e.getSource() == easy[i]) {
						if (easy[i].getText() == "") {
							easy[i].setForeground(new Color(255, 0, 0));
							easy[i].setText("X");
							player1_turn = false;
							textfield.setText("O turn");
							easy[i].doClick();
							check(easy);
							checktie(easy);
						}

					}

				}
			} else if (player1_turn == false) {

				random_ai(easy);

				easy[move].setForeground(new Color(0, 0, 255));
				easy[move].setText("O");
				player1_turn = true;
				textfield.setText("X turn");
				check(easy);
				checktie(easy);

			}

		}

		else if (mode == 'h') {

			if (ai_turn == false) {
				for (int i = 0; i < 9; i++) {

					if (e.getSource() == hard[i]) {
						if (hard[i].getText() == "") {
							hard[i].setForeground(new Color(255, 0, 0));
							hard[i].setText("X");
							ai_turn = true;
							textfield.setText("O turn");
							hard[i].doClick();
							check(hard);
							checktie(hard);
						}

					}

				}
			} else if (ai_turn == true) {

				toBoard(hard);
				Move Best = findBestMove(board);

				toButton(Best);
				hard[move].setForeground(new Color(0, 0, 255));
				hard[move].setText("O");
				ai_turn = false;
				textfield.setText("X turn");
				check(hard);
				checktie(hard);

				toBoard(hard);

			}

		}

	}

	// method that transforms button array to 2D Int array
	public static void toBoard(JButton[] buttons) {
		int count = 0;
		for (int i = 0; i < 3; i++) {
			for (int j = 0; j < 3; j++) {
				if (buttons[count].getText() == "")
					board[i][j] = '_';
				if (buttons[count].getText() == "X")
					board[i][j] = 'X';
				if (buttons[count].getText() == "O")
					board[i][j] = 'O';

				count++;
				System.out.print(board[i][j]);
			}
			System.out.println();

		}

	}

	static Boolean isMovesLeft(char board[][]) {
		for (int i = 0; i < 3; i++)
			for (int j = 0; j < 3; j++)
				if (board[i][j] == '_')
					return true;
		return false;
	}

	static int evaluate(char b[][]) {
		// Checking for Rows for X or O victory.
		for (int row = 0; row < 3; row++) {
			if (b[row][0] == b[row][1] && b[row][1] == b[row][2]) {
				if (b[row][0] == player)
					return +10;
				else if (b[row][0] == opponent)
					return -10;
			}
		}

		// Checking for Columns for X or O victory.
		for (int col = 0; col < 3; col++) {
			if (b[0][col] == b[1][col] && b[1][col] == b[2][col]) {
				if (b[0][col] == player)
					return +10;

				else if (b[0][col] == opponent)
					return -10;
			}
		}

		// Checking for Diagonals for X or O victory.
		if (b[0][0] == b[1][1] && b[1][1] == b[2][2]) {
			if (b[0][0] == player)
				return +10;
			else if (b[0][0] == opponent)
				return -10;
		}

		if (b[0][2] == b[1][1] && b[1][1] == b[2][0]) {
			if (b[0][2] == player)
				return +10;
			else if (b[0][2] == opponent)
				return -10;
		}

		// Else if none of them have won then return 0
		return 0;
	}

	static int minimax(char board[][], int depth, Boolean isMax) {
		int score = evaluate(board);

// If Maximizer has won the game
// return his/her evaluated score
		if (score == 10)
			return score;

// If Minimizer has won the game
// return his/her evaluated score
		if (score == -10)
			return score;

// If there are no more moves and
// no winner then it is a tie
		if (isMovesLeft(board) == false)
			return 0;

// maximizer move
		if (isMax) {
			int best = -1000;

			for (int i = 0; i < 3; i++) {
				for (int j = 0; j < 3; j++) {
					// Check if cell is empty
					if (board[i][j] == '_') {

						board[i][j] = player;

						best = Math.max(best, minimax(board, depth + 1, !isMax));

						board[i][j] = '_';
					}
				}
			}
			return best;
		}

// minimizer move
		else {
			int best = 1000;

			for (int i = 0; i < 3; i++) {
				for (int j = 0; j < 3; j++) {
					// Check if cell is empty
					if (board[i][j] == '_') {
						// Make the move
						board[i][j] = opponent;

						
						best = Math.min(best, minimax(board, depth + 1, !isMax));

						
						board[i][j] = '_';
					}
				}
			}
			return best;
		}
	}

	static Move findBestMove(char board[][]) {
		int bestVal = -1000;
		Move bestMove = new Move();
		bestMove.row = 0;
		bestMove.col = 0;

		
		for (int i = 0; i < 3; i++) {
			for (int j = 0; j < 3; j++) {
				
				if (board[i][j] == '_') {
					
					board[i][j] = player;

					
					int moveVal = minimax(board, 0, false);

					
					board[i][j] = '_';

					
					if (moveVal > bestVal) {
						bestMove.row = i;
						bestMove.col = j;
						bestVal = moveVal;
					}
				}
			}
		}

		System.out.printf("The value of the best Move " + "is : %d\n\n", bestVal);

		return bestMove;
	}

	public static void toButton(Move bestMove) {

		move = (bestMove.row * 3) + bestMove.col;

	}

//method that scans every legal move then returns one at random
	public void random_ai(JButton[] temp) {
		ArrayList<Integer> legalmoves = new ArrayList<>();

		for (int i = 0; i < 9; i++) {
			if (temp[i].getText() == "") {
				legalmoves.add(i);

			}

		}

		Collections.shuffle(legalmoves);
		if (legalmoves.size() > 0)
			move = legalmoves.get(0);

		legalmoves.clear();
	}

//method to check if X or O win
	public static void check(JButton[] buttons) {
		// check X win conditions
		if ((buttons[0].getText() == "X") && (buttons[1].getText() == "X") && (buttons[2].getText() == "X")) {
			win = 'x';
			xWins(0, 1, 2, buttons);
		}
		if ((buttons[3].getText() == "X") && (buttons[4].getText() == "X") && (buttons[5].getText() == "X")) {
			win = 'x';
			xWins(3, 4, 5, buttons);
		}
		if ((buttons[6].getText() == "X") && (buttons[7].getText() == "X") && (buttons[8].getText() == "X")) {
			win = 'x';
			xWins(6, 7, 8, buttons);
		}
		if ((buttons[0].getText() == "X") && (buttons[3].getText() == "X") && (buttons[6].getText() == "X")) {
			win = 'x';
			xWins(0, 3, 6, buttons);
		}
		if ((buttons[1].getText() == "X") && (buttons[4].getText() == "X") && (buttons[7].getText() == "X")) {
			win = 'x';
			xWins(1, 4, 7, buttons);
		}
		if ((buttons[2].getText() == "X") && (buttons[5].getText() == "X") && (buttons[8].getText() == "X")) {
			win = 'x';
			xWins(2, 5, 8, buttons);
		}
		if ((buttons[0].getText() == "X") && (buttons[4].getText() == "X") && (buttons[8].getText() == "X")) {
			win = 'x';
			xWins(0, 4, 8, buttons);
		}
		if ((buttons[2].getText() == "X") && (buttons[4].getText() == "X") && (buttons[6].getText() == "X")) {
			win = 'x';
			xWins(2, 4, 6, buttons);
		}
		// check O win conditions
		if ((buttons[0].getText() == "O") && (buttons[1].getText() == "O") && (buttons[2].getText() == "O")) {
			win = 'o';
			oWins(0, 1, 2, buttons);
		}
		if ((buttons[3].getText() == "O") && (buttons[4].getText() == "O") && (buttons[5].getText() == "O")) {
			win = 'o';
			oWins(3, 4, 5, buttons);
		}
		if ((buttons[6].getText() == "O") && (buttons[7].getText() == "O") && (buttons[8].getText() == "O")) {
			win = 'o';
			oWins(6, 7, 8, buttons);
		}
		if ((buttons[0].getText() == "O") && (buttons[3].getText() == "O") && (buttons[6].getText() == "O")) {
			win = 'o';
			oWins(0, 3, 6, buttons);
		}
		if ((buttons[1].getText() == "O") && (buttons[4].getText() == "O") && (buttons[7].getText() == "O")) {
			win = 'o';
			oWins(1, 4, 7, buttons);
		}
		if ((buttons[2].getText() == "O") && (buttons[5].getText() == "O") && (buttons[8].getText() == "O")) {
			win = 'o';
			oWins(2, 5, 8, buttons);
		}
		if ((buttons[0].getText() == "O") && (buttons[4].getText() == "O") && (buttons[8].getText() == "O")) {
			win = 'o';
			oWins(0, 4, 8, buttons);
		}
		if ((buttons[2].getText() == "O") && (buttons[4].getText() == "O") && (buttons[6].getText() == "O")) {
			win = 'o';
			oWins(2, 4, 6, buttons);
		}

	}

// method to check if tied
	public static void checktie(JButton[] buttons) {
		int temp = 0;
		for (int i = 0; i < 9; i++) {
			if (buttons[i].getText() != "") {
				temp++;
			}

		}

		if (temp == 9 && win == 't') {

			textfield.setText("Tie!!!");
		}

	}

	public static void xWins(int a, int b, int c, JButton[] buttons) {
		buttons[a].setBackground(Color.GREEN);
		buttons[b].setBackground(Color.GREEN);
		buttons[c].setBackground(Color.GREEN);

		for (int i = 0; i < 9; i++) {
			buttons[i].setEnabled(false);
		}
		textfield.setText("X wins");
	}

	public static void oWins(int a, int b, int c, JButton[] buttons) {
		buttons[a].setBackground(Color.GREEN);
		buttons[b].setBackground(Color.GREEN);
		buttons[c].setBackground(Color.GREEN);

		for (int i = 0; i < 9; i++) {
			buttons[i].setEnabled(false);
		}
		textfield.setText("O wins");
	}
}
