/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Scanner;
import java.util.TreeMap;
import java.util.TreeSet;

import javax.swing.plaf.basic.BasicTreeUI.TreeCancelEditingAction;

/**
 *
 * @author 08103842
 */
public class Tb3 {
    final static int COL = 0;
    final static int LINE = 1;
    public static String setOfMovesThatLeadToScape[] = new String[10];
    public static String levels[] = new String[10]; // PARA DEBUG
    
    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) throws IOException {
    	levels = new String[10];
        Tab initTab = readInputs();
        levels[0] = initTab.getCordenates()+"\n";
        if(generateSetOfMoves(initTab,0)){
        	System.out.print("Há pelo menos uma maneira da galinha escapar. Seguem os tabuleiros de cada movimento da Galinha:\n");
        	System.out.print("Tabuleiro inicial: \n"+initTab.toString()+"\n");
            for(int i=0;i <10;i++){
            	System.out.print(setOfMovesThatLeadToScape[i]);
            }
        }else{
        	
        	System.out.print("Não há uma maneira da galinha escapar\n");
        }
        
        // PARA FAZER DEBUG
        /*for(int i=0;i <10;i++){
        	if(levels[i]!= null){
        		System.out.print(levels[i]+"\n");
        	}
        }  */    
    }
    
    public static Tab readInputs() throws IOException{
        
        // TODO code application logic here
        String cl;
        String p[]; // str de colunas e linhas que os lobos estÃ£o

        Scanner sc= new Scanner(System.in); 
        Tab initTab = new Tab();
        
        initTab.n = sc.nextInt(); // Tamanho do tabuleiro
        //inicializa o tabuleiro
        initTab.tab = new String[initTab.n][initTab.n];
        initTab.initTab();

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        cl = br.readLine();
        String acl[] = cl.split(" ");
        initTab.g = new int[2];
        initTab.g[COL] = Integer.parseInt(acl[0])-1;
        initTab.g[LINE] = Integer.parseInt(acl[1])-1;
        initTab.tab[initTab.g[COL]][initTab.g[LINE]] = "G ";
        
        initTab.k = Integer.parseInt(sc.next()); // NÃºmero de lobos
        
    
        initTab.l = new int[initTab.k][2]; // inicializa array que guarda os lobos
        
        for(int i=0; i< initTab.k;i++){
        	String strWolvesPositions = br.readLine();
            String strWolvesPositionsSplited[] = strWolvesPositions.split(" ");
            
            initTab.l[i][COL] = Integer.parseInt(strWolvesPositionsSplited[COL])-1;
            initTab.l[i][LINE] = Integer.parseInt(strWolvesPositionsSplited[LINE])-1;
            initTab.tab[initTab.l[i][COL]][initTab.l[i][LINE]] = "L"+i;
        } 
        
        return initTab;
    }
    
    public static boolean generateSetOfMoves(Tab t,int rounds){

    	if(t.isChickenAlive()){
    		
    		if(rounds < 10){
    			levels[rounds] += "\t\t";
    			Tab tabLeft = t.moveChickenLeft();
    			if(tabLeft != null && tabLeft.isChickenAlive()){
	    			tabLeft.movesWolfs();
	    			levels[rounds] += tabLeft.getCordenates()+",";
		    		if(generateSetOfMoves(tabLeft,rounds+1)){
		    			saveTab(tabLeft,rounds);
		    			return true;
		    		}
    			}else{
    				levels[rounds] += "XXXXX,";
    			}
	    		
	    		Tab tabRight = t.moveChickenRight();
	    		if(tabRight != null && tabRight.isChickenAlive()){
		    		tabRight.movesWolfs();
		    		levels[rounds] += "\t"+tabRight.getCordenates()+",";
	    			if(generateSetOfMoves(tabRight,rounds+1)){
		    			saveTab(tabRight,rounds);
		    			return true;
		    		}
	    		}else{
	    			levels[rounds] += "\t"+"XXXXX,";
	    		}
	    		
	    		Tab tabTop = t.moveChickenTop();
	    		if(tabTop != null && tabTop.isChickenAlive()){
	    			tabTop.movesWolfs();
	    			levels[rounds] += "\t"+tabTop.getCordenates()+",";
		    		if(generateSetOfMoves(tabTop,rounds+1)){
		    			saveTab(tabTop,rounds);
		    			return true;
		    		}
	    		}else{
	    			levels[rounds] += "\t"+"XXXXX,";
	    		}
	    		
	    		Tab tabBottom = t.moveChickenBottom();
	    		if(tabBottom != null && tabBottom.isChickenAlive()){
	    			tabBottom.movesWolfs();
	    			levels[rounds] += "\t"+tabBottom.getCordenates()+",";
		    		if(generateSetOfMoves(tabBottom,rounds+1)){
		    			saveTab(tabBottom,rounds);
		    			return true;
		    		}
	    		}else{
	    			levels[rounds] += "\t"+"XXXXX";
	    		}
	    		
	    		return false;
    		}else{

    			return true;
    		}
    		
    	}else{
    		return false;
    	}
    	
    	
    }
    
    
    private static void saveTab(Tab t,int rounds){
		String out;
    	out = "Rodada "+(rounds+1)+"\n";
        out +=t.toString()+"\n";
        setOfMovesThatLeadToScape[rounds]= out+"\n";
    }
}
