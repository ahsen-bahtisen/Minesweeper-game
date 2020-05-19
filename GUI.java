import javax.swing.*;

import java.util.*;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;


public class GUI extends JFrame {
	
	public boolean resetleyici = false;
	
	public boolean byrk = false;
	
	Date basTarihi = new Date();
	Date bitTarihi;
	
	int aralik = 1;
	
	int kom = 0;
	
	String zafMes = "A";
	
	public int mx = -100;
	public int my = -100;
	
	public int gulucukX = 375;
	public int gulucukY = 10;
	
	public int gulenYuzX = gulucukX + 20;
	public int gulenYuzY = gulucukY + 20;
	
	
	public int byrkX = 455;
	public int byrkY = 15;
	
	public int byrkMerkezX = byrkX + 20;
	public int byrkMerkezY = byrkY + 20; 
	
	public int zamanX = 655;
	public int zamanY = 15;
	
	public int zafMesX = 740;
	public int zafMesY = -50;

	public int saniye = 0;

	public boolean mutlu = true;
	
	public boolean zafer = false;
	
	public boolean yenilgi = false;
	
	Random rnd = new Random();
	
	int[][] mayinlar = new int [16][12];
	int[][] komsu = new int[16][12];
	boolean[][] gorunen = new boolean [16][12];
	boolean[][] bayraklanmis = new boolean [16][12]; 
	
	public GUI(){
		this.setTitle("Mayýn Tarlasý");
		this.setSize(810, 750);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setVisible(true);
		this.setResizable(false);
		
		for (int i = 0; i < 16; i++){
			for (int j = 0; j < 12; j++){
				if (rnd.nextInt(100) < 20){
					mayinlar[i][j] = 1;
				}
				else{
					mayinlar[i][j] = 0;
				}
				gorunen[i][j] = false;
			}
		}
		for (int i = 0; i < 16; i++){
			for (int j = 0; j < 12; j++){
				kom = 0;
				for (int m = 0; m < 16; m++){
					for (int n = 0; n < 12; n++){
						if (!(m == i && n == j)){
							if (isN(i,j,m,n) == true)
								kom++;
						}
					}
			    }
				komsu[i][j] = kom;
		    }
		}
		Pano pano = new Pano();
		this.setContentPane(pano);
		
		Hareket hareket =  new Hareket();
		this.addMouseMotionListener(hareket);
		
		Click click = new Click();
		this.addMouseListener(click);
	}
	
	public class Pano extends JPanel {
		public void paintComponent(Graphics g){
			g.setColor(Color.GRAY);
			g.fillRect(0, 0, 800, 750);
			for (int i = 0; i < 16; i++){
				for (int j = 0; j < 12; j++){
					g.setColor(Color.LIGHT_GRAY);
					if (gorunen[i][j] == true){
						g.setColor(Color.white);
						if(mayinlar[i][j] == 1){
							g.setColor(Color.red);
						}
					}
					if (mx >= aralik+i*50 && mx < aralik+i*50+50-2*aralik && my >= aralik+j*50+86 && my < aralik+j*50+136-2*aralik ){
						g.setColor(Color.cyan);
					}
					g.fillRect(aralik+i*50, aralik+j*50+70, 50-2*aralik, 50-2*aralik);
					if (gorunen[i][j] == true){
						g.setColor(Color.black);
						if (mayinlar[i][j] == 0 && komsu[i][j] != 0){
							if (komsu[i][j] == 1){
								g.setColor(Color.blue);
							}
							else if(komsu[i][j] == 2){
								g.setColor(Color.green);
							}
							else if(komsu[i][j] == 3){
								g.setColor(Color.red);
							}
							else if(komsu[i][j] == 4){
								g.setColor(Color.magenta);
							}
							else if(komsu[i][j] == 5){
								g.setColor(new Color(178, 34, 34));
							}
							else if(komsu[i][j] == 6){
								g.setColor(Color.orange);
							}
							else if(komsu[i][j] == 8){
								g.setColor(Color.DARK_GRAY);
							}
							g.setFont(new Font("Tahoma", Font.BOLD, 23));
							g.drawString(Integer.toString(komsu[i][j]), i*50+17, j*50+105);
						}
						else if (mayinlar[i][j] == 1){
							g.fillRect(i*50+5+15, j*50+70+15, 10, 23);
							g.fillRect(i*50+15, j*50+70+5+15, 23, 10);
							g.fillRect(i*50+2+15, j*50+70+2+15, 19, 19);
							g.fillRect(i*50+25, j*50+70+15, 3, 20);
							g.fillRect(i*50+15, j*50+70+25, 20, 3);
						}
					}
					
					if (bayraklanmis[i][j] == true){
						g.setColor(Color.black);
						g.fillRect(i*50+5+25, j*50+70+10, 3, 27);
						g.fillRect(i*50+5+13, j*50+70+35, 21, 5);
						g.setColor(Color.red);
						g.fillRect(i*50+5+10, j*50+70+10, 16, 12);
					}
				}
			}
			
			g.setColor(Color.yellow);
			g.fillOval(gulucukX, gulucukY, 50, 50);
			g.setColor(Color.BLACK);
			g.fillOval(gulucukX+13, gulucukY+15, 8, 8);
			g.fillOval(gulucukX+30, gulucukY+15, 8, 8);
			if (mutlu == true){
				g.fillRect(gulucukX+14, gulucukY+38, 23, 4);
				g.fillRect(gulucukX+12, gulucukY+35, 4, 4);
				g.fillRect(gulucukX+35, gulucukY+35, 4, 4);
			}
			else{
				g.fillRect(gulucukX+14, gulucukY+35, 23, 4);
				g.fillRect(gulucukX+12, gulucukY+38, 4, 4);
				g.fillRect(gulucukX+35, gulucukY+38, 4, 4);
			}
			
			g.setColor(Color.black);
			g.fillRect(byrkX+72, byrkY+6, 4, 29);
			g.fillRect(byrkX+59, byrkY+34, 23, 7);
			g.setColor(Color.red);
			g.fillRect(byrkX+55, byrkY+6, 18, 14);
			g.setColor(Color.black);
			if (byrk == true){
				g.setColor(Color.red);
			}
			
			g.drawOval(byrkX+42, byrkY-5, 54, 54);
			g.drawOval(byrkX+44, byrkY-3, 52, 52);
			g.drawOval(byrkX+43, byrkY-4, 54, 54);
			
			g.setColor(Color.black);
			g.fillRect(zamanX, zamanY, 80, 50); //sayacýn boyutu
			if (yenilgi == false && zafer == false){
				saniye = (int) ((new Date().getTime()-basTarihi.getTime()) / 1000);
			}
			if (saniye >= 300){				
				g.setColor(Color.orange);
			
			if (saniye > 999){
				saniye = 999;
				}
			}
			else{
				g.setColor(Color.WHITE);
				if (zafer == true){
					g.setColor(Color.green);
				}
				else if (yenilgi == true){
					g.setColor(Color.red);
				}
				g.setFont(new Font("Tahoma", Font.PLAIN, 25));
				if (saniye < 10){
					g.drawString("00"+Integer.toString(saniye), zamanX+20, zamanY+35);//sayýlarýn konumu
				}
				else if (saniye < 100){
					g.drawString("0"+Integer.toString(saniye), zamanX+20, zamanY+35);
				}
				else{
					g.drawString(Integer.toString(saniye), zamanX+20, zamanY+35);
				}
				
				if (zafer == true){
					g.setColor(Color.green);
					zafMes = "KAZANDIN!";
				}
				else if (yenilgi == true){
					g.setColor(Color.red);
					zafMes = "KAYBETTÝN!";
				}
				if (zafer == true || yenilgi == true ){
					zafMesY = -50 + (int) (new Date().getTime() - bitTarihi.getTime()) / 10;
					if (zafMesY > 70){
						zafMesY = 70;
					}
					g.setFont(new Font("Tahoma", Font.PLAIN, 50));
					g.drawString(zafMes, zafMesY, zafMesY);
				}
			}
		}
	}
	public class Hareket implements MouseMotionListener{

		@Override
		public void mouseDragged(MouseEvent arg0) {
		}

		@Override
		public void mouseMoved(MouseEvent e) {
			mx = e.getX();
			my = e.getY();
		}	
	}
	
	public class Click implements MouseListener{

		@Override
		public void mouseClicked(MouseEvent e) {
			
			mx = e.getX();
			my = e.getY();
			
			if (inBoxX() != -1 && inBoxY() != -1){
				System.out.println("Mouse ["+ inBoxX() + "," + inBoxY() +"] içinde , mayýn komþu numarasý:"+ komsu[inBoxX()][inBoxY()] );
				if (byrk == true && gorunen[inBoxX()][inBoxY()] == false){
					if (bayraklanmis[inBoxX()][inBoxY()] == false){
						bayraklanmis[inBoxX()][inBoxY()] = true;
					}
					else{
						bayraklanmis[inBoxX()][inBoxY()] = false;
					}
				}
				else{
					if (bayraklanmis[inBoxX()][inBoxY()] == false){
						gorunen[inBoxX()][inBoxY()] = true;
					}
				}
			}
			else{
				System.out.println("Ýþaretçi hiçbir kutunun içinde deðil!" );
			}
			
			if (iGulucuk() == true){
				resetle();
				System.out.println("gülücüðün içinde = true!");
			}
			if (iByrk() == true){
				if (byrk == false){
					byrk = true;
					System.out.println("bayraðýn içinde = true!");
				}
				else{
					byrk = false;
					System.out.println("bayraðýn içinde = false!");
				}
			}
		}

		@Override
		public void mouseEntered(MouseEvent arg0) {
			// TODO Auto-generated method stub
		}

		@Override
		public void mouseExited(MouseEvent arg0) {
			// TODO Auto-generated method stub
			
		}

		@Override
		public void mousePressed(MouseEvent arg0) {
			// TODO Auto-generated method stub
			
		}

		@Override
		public void mouseReleased(MouseEvent arg0) {
			// TODO Auto-generated method stub
			
		}
	}
	
	public void zaferKontrol(){
		
		if (yenilgi == false){
		for (int i = 0; i < 16; i++){
			for (int j = 0; j < 12; j++){
				if(gorunen[i][j] == true && mayinlar[i][j] == 1){
						yenilgi = true;
						mutlu = false;
						bitTarihi = new Date();
						
					}
				}
			}
		}
		if (topAcigaCikanKutu() >= 192 - toplamMayin() && zafer == false){
			zafer = true;
			bitTarihi = new Date();

		}
	}
	
	public int toplamMayin(){
		
		int toplam = 0;
		for (int i = 0; i < 16; i++){
			for (int j = 0; j < 12; j++){
				if(mayinlar[i][j] == 1){
					toplam++;
				}
			}
		}
		return toplam;
	}
	
	public int topAcigaCikanKutu(){
		
		int toplam = 0;
		for (int i = 0; i < 16; i++){
			for (int j = 0; j < 12; j++){
				if(gorunen[i][j] == true){
					toplam++;
				}
			}
		}
		return toplam;
	}
	
	public void resetle(){
		
		resetleyici = true;
		
		byrk = false;
		
		basTarihi = new Date();
		
		zafMesY = -80;
		zafMes = "A";
		
		mutlu = true;
		zafer = false;
		yenilgi = false;
		
		for (int i = 0; i < 16; i++){
			for (int j = 0; j < 12; j++){
				if (rnd.nextInt(100) < 20){
					mayinlar[i][j] = 1;
				}
				else{
					mayinlar[i][j] = 0;
				}
				gorunen[i][j] = false;
				bayraklanmis[i][j] = false;
			}
		}
		
		for (int i = 0; i < 16; i++){
			for (int j = 0; j < 12; j++){
				kom = 0;
				for (int m = 0; m < 16; m++){
					for (int n = 0; n < 12; n++){
						if (!(m == i && n == j)){
							if (isN(i,j,m,n) == true)
								kom++;
						}
					}
			    }
				komsu[i][j] = kom;
		    }
		}
		resetleyici = false;
	}
	
	public boolean iGulucuk(){
		int fark = (int) Math.sqrt(Math.abs(mx-10 - gulenYuzX) * Math.abs(mx - gulenYuzX) + Math.abs(my-40 - gulenYuzY) * Math.abs(my - gulenYuzY));
		if (fark < 25){
			return true;
		}
		return false;
	}

	public boolean iByrk(){
		int fark = (int) Math.sqrt(Math.abs(mx-56 - byrkMerkezX) * Math.abs(mx - byrkMerkezY) + Math.abs(my-46 - byrkMerkezY) * Math.abs(my - byrkMerkezY));
		if (fark < 25){
			return true;
		}
		return false;
	}
	
	public int inBoxX(){
		for (int i = 0; i < 16; i++){
			for (int j = 0; j < 12; j++){
				if (mx >= aralik+i*50 && mx < aralik+i*50+50-2*aralik && my >= aralik+j*50+86 && my < aralik+j*50+136-2*aralik ){
					return i;
				}
			}
		}
		return -1;
	}
	public int inBoxY(){
		for (int i = 0; i < 16; i++){
			for (int j = 0; j < 12; j++){
				if (mx >= aralik+i*50 && mx < aralik+i*50+50-2*aralik && my >= aralik+j*50+86 && my < aralik+j*50+136-2*aralik ){
					return j;
				}
			}
		}
		return -1;
	}
	
	public boolean isN(int mX, int mY, int cX, int cY){
		if (mX - cX < 2 && mX - cX > -2 && mY - cY < 2 && mY - cY > -2 && mayinlar[cX][cY] == 1){
			return true;
		}
		return false;
	}
}
