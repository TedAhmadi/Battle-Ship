/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package battleship;
/**
 *
 * @author Mohammad
 */
public class xplayer {
    
    private String name;
    private int wins;
    
    public xplayer(){
        wins = 0;
    }
    
    public xplayer(String n) {
        name = n;
        wins = 0;
    }
    
     public void assign(xplayer p){
        this.name = p.name;
        this.wins = p.wins;
    }
    
    
    public void setName(String str){
        name = str;
    }
    
    public String getName(){
        return name;
    }
    

    public void increaseWin(){
        wins++;
    }
    
    public int getWins(){
        return wins;
    }
    
}
