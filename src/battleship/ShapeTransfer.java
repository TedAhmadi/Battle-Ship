/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package battleship;
/**
 *
 * @author Mohammad
 */
import java.io.*;
import java.net.*;
import javax.swing.*;

public class ShapeTransfer extends Thread {
    
    Game game;
    private ServerSocket sSocket = null;
    private Socket socket;
    private BufferedReader in;
    private PrintWriter out;
    BattleShipView View;
    private final int gamePort = 1983;

public ShapeTransfer(Game game,BattleShipView view) {
     this.game = game;         
     this.View = view;
        if(game.thisPlayerIsServer()){
            try{
                sSocket = new ServerSocket(gamePort);
            }catch(IOException e){
                System.out.println("Game Error:Could not listen on port: "+ gamePort);
                JOptionPane.showMessageDialog(null, "Game Error:Could not listen on port: "+ gamePort, null, 0);
                System.exit(1);
            }
            try {
                JOptionPane.showMessageDialog(view.getFrame(), "Waiting for Player2 to connect...");
                socket = sSocket.accept();
            } catch (IOException e) {
                System.out.println("Game Error:Accept failed on port: "+ gamePort);
                JOptionPane.showMessageDialog(null, "Game Error:Accept failed on port: "+ gamePort, null, 0);
                System.exit(1);
            }
        }
        else{
            try {
                socket = new Socket(BattleShipView.getPlayer1IpAddress(), gamePort);
            } catch (UnknownHostException e) {
                System.err.println("Game Error:Unknown host: "+BattleShipView.getPlayer1IpAddress());
                JOptionPane.showMessageDialog(null, "Game Error:Unknown host: "+BattleShipView.getPlayer1IpAddress(), null, 0);
                System.exit(1);
            } catch (IOException e) {
                System.err.println("Game Error:Could not get I/O from server: "+BattleShipView.getPlayer1IpAddress());
                JOptionPane.showMessageDialog(null, "Game Error:Could not get I/O from server: "+BattleShipView.getPlayer1IpAddress(), null, 0);
                System.exit(1);
            }
        }
        
        try {
            in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            out = new PrintWriter(socket.getOutputStream(), true);

        }  catch (IOException e) {
                System.err.println("Game Error:Could not get I/O from server: "+socket.getPort());
                JOptionPane.showMessageDialog(null, "Game Error:Could not get I/O from server: "+socket.getPort(), null, 0);
                System.exit(1);
        }
        
    }

    public void run() {
        String line;
            try {
                while(true){
                
                    line = in.readLine();             
                    if(this.game.thisPlayerIsServer())
                    {
                        View.ship1(line);
                        //View.hitFind(line);
                    }else{
                        View.ship2(line);
                        //View.hitFind(line);
                    }
              }
            } catch (IOException ex) {
                System.err.println("Chat Error:Could not read from chat input stream!");
                JOptionPane.showMessageDialog(null, "Chat Error:Could not read from chat input stream!", null, 0);
            }
    }

   
    public void shipt(String jb) {

     out.println(jb);
    }  
    
    protected void finalize(){
        try{
            in.close();
            out.close();
        } catch (IOException e) {
            System.out.println("Chat Error:Could not close socket");
            JOptionPane.showMessageDialog(null, "Chat Error:Could not close socket", null, 0);
            System.exit(1);
        }
    }
}
