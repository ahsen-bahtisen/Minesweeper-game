import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Container;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JOptionPane;


public class MayýnTarlasý implements ActionListener{

	JFrame cerceve = new JFrame("MayýnTarlasý");
	JButton reset = new JButton("Reset");
	JButton[][] butonlar  = new JButton[16][16];
	int[][] kutu = new int[16][16]; 
	Container izgara = new Container();
	final int MAYIN = 40;
	
	public MayýnTarlasý(){
		cerceve.setSize(700,500); 
		cerceve.setLayout(new BorderLayout());
		cerceve.add(reset, BorderLayout.NORTH);
		reset.addActionListener(this);
		// Button grid
		izgara.setLayout(new GridLayout(16,16));
		for (int a = 0; a < butonlar.length; a++){
			for (int b = 0; b < butonlar[0].length; b++){
				butonlar[a][b] = new JButton();
				butonlar[a][b].addActionListener(this);
				izgara.add(butonlar[a][b]);
			}
		}
		cerceve.add(izgara, BorderLayout.CENTER);
		rastgeleMayýnOlustur();
		
		cerceve.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		cerceve.setVisible(true);	
	}
	
	public static void main(String[] args){
		new MayýnTarlasý();	
	}
    public void rastgeleMayýnOlustur(){
    	//Ýnitialize list of random pairs
    	ArrayList<Integer> list = new ArrayList<Integer>();
    	for (int x = 0; x < kutu.length; x++){
    		for (int y = 0; y < kutu[0].length; y++ ){
    			list.add(x*100+y);
    		}
    	}
    	//reset counts, pick out 40 mines 
    	kutu = new int[16][16];
    	for (int a = 0; a < 40; a++){
    		int sec = (int)(Math.random() * list.size());
    		kutu[list.get(sec)/100][list.get(sec)%100] = MAYIN;
    		list.remove(sec);   		
    	}
    	//initialize neighbor counts
    	for (int x = 0; x < kutu.length; x++){
    		for (int y = 0; y < kutu[0].length; y++){
    			if (kutu[x][y] != MAYIN){
	    			int komsukutu = 0;
	    			if (x > 0 && y > 0 && kutu[x-1][y-1] == MAYIN){//up left
	    				komsukutu++;	
	    			}
	    			if (y > 0 && kutu[x][y-1] == MAYIN){//up
	    				komsukutu++;	
	    			}
	    			if (x < kutu.length - 1 && y < kutu[0].length - 1 && kutu[x+1][y+1] == MAYIN){//down right
	    				komsukutu++;
	    			}
	    			if (x > 0 && kutu[x-1][y] == MAYIN){//left
	    				komsukutu++;
	    			}
	    			if (x < kutu.length - 1 && kutu[x+1][y] == MAYIN){//right
	    				komsukutu++;			
	    			}
	    			if (x > 0 && y < kutu[0].length - 1 && kutu[x-1][y+1] == MAYIN){
	    				komsukutu++;
	    			}
	    			if (y < kutu[0].length - 1 && kutu[x][y+1] == MAYIN){//down
	    				komsukutu++;	
	    			}
	    			if (x < kutu.length - 1 && y < kutu[0].length - 1 && kutu[x][y] == MAYIN ){
	    				komsukutu++;
	    			}
	    			kutu[x][y] = komsukutu;
	    			}
    			}
    		}
    	}
    
    public void oyunBasarisiz(){
    	for (int x = 0; x < butonlar.length; x++){
    		for (int y = 0; y < butonlar[0].length; y++){
    			if (butonlar[x][y].isEnabled()){
    				if (kutu[x][y] != MAYIN){
    					butonlar[x][y].setText(kutu[x][y] + "");
    					butonlar[x][y].setEnabled(false);
    				}
    				else{
    					butonlar[x][y].setText("X");
    					butonlar[x][y].setEnabled(false);
    				}
    			}
    			
    		}
    	}
    }
    
    public void sifirlariTemizle(ArrayList<Integer> toClear){
    	if (toClear.size() == 0){
    		return;
    	}
    	else{
    		int x = toClear.get(0) / 100;
    		int y = toClear.get(0) % 100;
    		toClear.remove(0);
    			if (x > 0 && y > 0 && butonlar[x-1][y-1].isEnabled()){//up left
    				butonlar[x-1][y-1].setText(kutu[x-1][y-1] + "");
    				butonlar[x-1][y-1].setEnabled(false);
    				if (kutu[x-1][y-1] == 0 ){
    					toClear.add((x-1) * 100 + (y-1));
    				}
    			}
    			if (y > 0 && butonlar[x][y-1].isEnabled()){//up
    				butonlar[x][y-1].setText(kutu[x][y-1] + "");
    				butonlar[x][y-1].setEnabled(false);	
    				if (kutu[x][y-1] == 0 ){
    					toClear.add(x * 100 + (y-1));
    				}
    			}
    			if (x < kutu.length - 1 && y > 0 && butonlar[x+1][y-1].isEnabled()){//up right
    				butonlar[x+1][y-1].setText(kutu[x+1][y-1] + "");
    				butonlar[x+1][y-1].setEnabled(false);
    				if (kutu[x+1][y-1] == 0 ){
    					toClear.add((x+1) * 100 + (y-1));
    				}
    			}
    			if (x > 0 && butonlar[x-1][y].isEnabled()){//left
    				butonlar[x-1][y].setText(kutu[x-1][y] + "");
    				butonlar[x-1][y].setEnabled(false);
    				if (kutu[x-1][y] == 0 ){
    					toClear.add((x-1) * 100 + y);
    				}
    			}
    			if (x < kutu.length - 1 && butonlar[x+1][y].isEnabled()){//right
    				butonlar[x+1][y].setText(kutu[x+1][y] + "");
    				butonlar[x+1][y].setEnabled(false);
    				if (kutu[x+1][y] == 0 ){
    					toClear.add((x+1) * 100 + y);
    				}
    			}
    			if (x > 0 && y < kutu[0].length - 1 && butonlar[x-1][y+1].isEnabled()){//down left
    				butonlar[x-1][y+1].setText(kutu[x-1][y+1] + "");
    				butonlar[x-1][y+1].setEnabled(false);
    				if (kutu[x-1][y+1] == 0 ){
    					toClear.add((x-1) * 100 + (y+1));
    				}
    			}
    			if (y < kutu[0].length - 1 && butonlar[x][y+1].isEnabled()){//down
    				butonlar[x][y+1].setText(kutu[x][y+1] + "");
    				butonlar[x][y+1].setEnabled(false);	
    				if (kutu[x][y+1] == 0 ){
    					toClear.add(x * 100 + (y+1));
    				}
    			}
    			if (x < kutu.length - 1 && y < kutu[0].length - 1 && butonlar[x+1][y+1].isEnabled()){//down right
    				butonlar[x+1][y+1].setText(kutu[x+1][y+1] + "");
    				butonlar[x+1][y+1].setEnabled(false);
    				if (kutu[x+1][y+1] == 0 ){
    					toClear.add((x+1) * 100 + (y+1));
    				}
    			}
    		sifirlariTemizle(toClear);
    	}	
    }
    
    public void kazKontrol(){
    	boolean kazandi = true;
    	for (int x = 0; x < kutu.length; x++){
    		for (int y = 0; y < kutu[0].length; y++){
    			if (kutu[x][y] != MAYIN && butonlar[x][y].isEnabled() == true){
    				kazandi = false;
    			}	
    		}
    	}
    	if (kazandi == true){
    		JOptionPane.showMessageDialog(cerceve, "Kazandýn!");	
    	}
    }
    
	@Override
	public void actionPerformed(ActionEvent event) {
		if (event.getSource().equals(reset)){
			for (int x = 0; x < butonlar.length; x++ ){
				for (int y = 0; y < butonlar[0].length; y++){
					butonlar[x][y].setEnabled(true);
					butonlar[x][y].setText("");
				}
			}
			rastgeleMayýnOlustur();
		}
		else{
			for (int x = 0; x < butonlar.length; x++){
				for (int y = 0; y < butonlar[0].length; y++){
					if (event.getSource().equals(butonlar[x][y])){
						if (kutu[x][y] == MAYIN){
							butonlar[x][y].setForeground(Color.red);
							butonlar[x][y].setText("X");
							oyunBasarisiz();
						}
						else if (kutu[x][y] == 0){
							butonlar[x][y].setText(kutu[x][y] + "");	
							butonlar[x][y].setEnabled(false);
							ArrayList<Integer> toClear = new ArrayList<Integer>();
							toClear.add(x*100+y);
							sifirlariTemizle(toClear);
							kazKontrol();
						}
						else{
							butonlar[x][y].setText(kutu[x][y] + "");	
							butonlar[x][y].setEnabled(false);
							kazKontrol();
						}
					}
				}	
			}
		}
	}
}
