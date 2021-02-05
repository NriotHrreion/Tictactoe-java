package net.nocpiun.tictactoe;

import java.awt.Color;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.*;
import javax.swing.*;

public class Game extends JFrame {
	private final int width = 350;
	private final int height = 350;
	
	private boolean isGameFinish = false;
	private Turn turn = Turn.X;
	private Turn[] map = new Turn[9];
	
	public Game() {
		this.setTitle("Tictactoe");
		this.setResizable(false);
		this.setLocation(500, 100);
		this.setSize(this.width, this.height);
		
		this.addWindowListener(new WindowAdapter() {
			@Override
			public void windowClosing(WindowEvent e) {
				System.exit(0);
			}
		});
		
		this.initGui();
	}
	
	private void initGui() {
		this.setLayout(new GridLayout(3, 3));
		for(int i = 0; i < 9; i++) {
			JButton jb = this.getButton();
			jb.setName(String.valueOf(i));
			
			this.add(jb);
		}
	}
	
	private JButton getButton() {
		JButton button = new JButton("");
		Font font = new Font("Arial", Font.BOLD, 45);
		
		button.setFont(font);
		button.setBackground(Color.WHITE);
		button.setFocusPainted(false);
		
		Game g = this;
		button.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				if(e.getSource() instanceof JButton) {
					JButton btn = (JButton)e.getSource();
					String btnContent = btn.getText();
					int btnId = Integer.valueOf(btn.getName());
					
					if(btnContent == "" && !isGameFinish) {
						map[btnId] = turn;
						btn.setText(turn.toString());
						turn = turn == Turn.X ? Turn.O : Turn.X;
						
						Turn winner = g.getWinner();
						if(winner != Turn.N) {
							g.isGameFinish = true;
							System.out.println(winner +" Won");
							JOptionPane.showMessageDialog(null, winner +" Won");
						}
					}
				}
			}
		});
		
		return button;
	}
	
	private Turn getWinner() {
		Turn winner = Turn.N;
		
		if(this.map[0] == this.map[1] && this.map[0] == this.map[2] && this.map[0] != null) {
			winner = this.map[0];
		} else if(this.map[3] == this.map[4] && this.map[3] == this.map[5] && this.map[3] != null) {
			winner = this.map[3];
		} else if(this.map[6] == this.map[7] && this.map[6] == this.map[8] && this.map[6] != null) {
			winner = this.map[6];
		} else if(this.map[0] == this.map[3] && this.map[0] == this.map[6] && this.map[0] != null) {
			winner = this.map[0];
		} else if(this.map[1] == this.map[4] && this.map[1] == this.map[7] && this.map[1] != null) {
			winner = this.map[1];
		} else if(this.map[2] == this.map[5] && this.map[2] == this.map[8] && this.map[2] != null) {
			winner = this.map[2];
		} else if(this.map[0] == this.map[4] && this.map[0] == this.map[8] && this.map[0] != null) {
			winner = this.map[0];
		} else if(this.map[2] == this.map[4] && this.map[2] == this.map[6] && this.map[2] != null) {
			winner = this.map[2];
		}
		
		return winner;
	}
	
	public void showGui() {
		this.setVisible(true);
	}
	
	public static void start() {
		Game game = new Game();
		game.showGui();
		
		System.out.println("Game Start");
	}
}
