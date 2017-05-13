/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package battleship;

/**
 *
 * @author Mohammad
 */

public class Game {
    
    private xplayer player1, player2;
    private xplayer activePlayer;
    private xplayer thisPlayer;


    public Game() {
        player1 = new xplayer();
        player2 = new xplayer();
        activePlayer = player1;
        thisPlayer = null;
    }
    
    public Game(xplayer p1, xplayer p2) {
        player1 = p1;
        player2 = p2;
        activePlayer = player1;
        thisPlayer = null;
    }
    
    public xplayer getPlayer1(){
        return player1;
    }
    
    public xplayer getPlayer2(){
        return player2;
    }

    public xplayer getActivePlayer() {
        return activePlayer;
    }

    public void setActivePlayer(xplayer activePlayer) {
        this.activePlayer = activePlayer;
    }
    
    public void changeActivePlayer() {
        if(activePlayer == player1)
            activePlayer = player2;
        else
            activePlayer = player1;
    }
    
    public xplayer getThisPlayer() {
        return thisPlayer;
    }

    public void setThisPlayer(xplayer thisPlayer) {
        this.thisPlayer = thisPlayer;
    }
    
    public boolean thisPlayerIsServer(){
        if(thisPlayer==player1)
            return true;
        else 
            return false;
    }
   
}
