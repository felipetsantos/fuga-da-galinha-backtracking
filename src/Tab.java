
public class Tab {
	
	final int COL = 0;
	final int LINE = 1;
	public  int n; // Numero de linhas e colunas do tabuleiro
	public  int g[]; // Coluna da galinha
	public  int k; // Numero de lobos
	public  int l[][]; // linhas que os lobos estao
	public  String tab[][]; // tabuleiro
	
  public Tab(){
		
  }
    
  public Tab(Tab tab){
    	n = tab.n;
    	g = new int[2];
    	initTab();
    	g[COL] = tab.g[COL];
    	g[LINE] = tab.g[LINE];
    	k = tab.k;
    	l = new int[k][2];
    	for(int i=0; i<k;i++){
    		l[i][COL] = tab.l[i][COL];
    		l[i][LINE] = tab.l[i][LINE];
    	}
    	this.tab[g[COL]][g[LINE]] = "G_";
   
  }
 
  public void initTab(){
        tab = new String[n][n];
    	for(int i=0;i<n;i++){
            for(int j=0;j<n;j++){
               tab[j][i] = "__";
            }
        }
  }
  public String toString(){
        String str = "";
        for(int i=0;i<n;i++){
            for(int j=0;j<n;j++){
               str+= tab[j][i]+" ";
            }
            str+= "\n";
        }
        return str;
  }
  public  boolean isChickenAlive(){
  	  for(int i=0;i<k;i++){
  		  if(l[i][COL] == g[COL] && l[i][LINE] == g[LINE] ){
  			  return false;
  		  }
  	  }
  	  
  	  return true;
  }
  
  public String getCordenates(){
	  String str =  "["+(g[COL]+1)+","+(g[LINE]+1)+"]";
  	  for(int i=0;i<k;i++){
  		  str +=",["+(l[i][COL]+1)+","+(l[i][LINE]+1)+"]";
  	  }
  	  return str;
  }
  public void movesWolfs(){

	  for(int wolf_index =0;wolf_index < k;wolf_index++){
		  
	  	if(g[COL] == l[wolf_index][COL]){
	  		
	  		// lobo esta na mesma coluna
	  		if(l[wolf_index][LINE] > g[LINE]){
	  			// lobo esta acima da galinha
	  			l[wolf_index][LINE]--;
	  		}else{
	  			// lobo esta abaixo da galinha
	  			l[wolf_index][LINE]++;
	  		}
	  		tab[l[wolf_index][COL]][l[wolf_index][LINE]] = "L"+(wolf_index+1);
	  	}else if(g[LINE] == l[wolf_index][LINE]){
	  		// lobo esta na mesma linha
	  		if(l[wolf_index][COL] > g[COL]){
	  			// lobo esta acima da galinha
	  			l[wolf_index][COL]--;
	  		}else{
	  			// lobo esta abaixo da galinha
	  			l[wolf_index][COL]++;
	  		}
	  		tab[l[wolf_index][COL]][l[wolf_index][LINE]] = "L"+(wolf_index+1);
	  	}else{
	  		//Lobo nao esta na mesma coluna e linha
	  		
	  		if(l[wolf_index][LINE] > g[LINE]){
	  			// lobo esta acima da galinha
	  			l[wolf_index][LINE]--;
	  		}else{
	  			// lobo esta abaixo da galinha
	  			l[wolf_index][LINE]++;
	  		}
	  		
	  		if(l[wolf_index][COL] > g[COL]){
	  			// lobo esta a direita
	  			l[wolf_index][COL]--;
	  		}else{
	  			// lobo esta a esquerda da galinha
	  			l[wolf_index][COL]++;
	  		}
	  		tab[l[wolf_index][COL]][l[wolf_index][LINE]] = "L"+(wolf_index+1);
	  	}
	  	
	  	
	}
  	
  }
  
  public Tab moveChickenLeft(){
	  Tab newTab = new Tab(this);
	  if(newTab.g[COL] != 0){
		  newTab.tab[newTab.g[COL]][newTab.g[LINE]] = "__";
		  newTab.g[COL]--;
		  newTab.tab[newTab.g[COL]][newTab.g[LINE]] = "G_";
		  return newTab;
	  }else{
		  return null;
	  }
  }

  public Tab moveChickenRight(){
	  Tab newTab = new Tab(this);
	  if(newTab.g[COL] < n-1){
		  newTab.tab[newTab.g[COL]][newTab.g[LINE]] = "__";
		  newTab.g[COL]++;
		  newTab.tab[newTab.g[COL]][newTab.g[LINE]] = "G_";
		  return newTab;
	  }else{
		  return null;
	  }
  }
  public Tab moveChickenTop(){
	  Tab newTab = new Tab(this);
	  if(newTab.g[LINE] != 0){
		  newTab.tab[newTab.g[COL]][newTab.g[LINE]] = "__";
		  newTab.g[LINE]--;
		  newTab.tab[newTab.g[COL]][newTab.g[LINE]] = "G_";
		  return newTab;
	  }else{
		  return null;
	  }
  }
  public Tab moveChickenBottom(){
	  Tab newTab = new Tab(this);
	  if(newTab.g[LINE] < n-1){
		  newTab.tab[newTab.g[COL]][newTab.g[LINE]] = "__";
		  newTab.g[LINE]++;
		  newTab.tab[newTab.g[COL]][newTab.g[LINE]] = "G_";
		  return newTab;
	  }else{
		  return null;
	  }

  }
}
