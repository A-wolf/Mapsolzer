package map;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.event.KeyEvent;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JFrame;
import javax.swing.SwingUtilities;

public class MazeSolver extends JFrame {
	
	public int x = 0;
	
    private char [][] maze = 
        { {1,1,1,1,1,1,1,1,1,1,1,1,1},
          {1,0,1,0,1,0,1,0,0,0,0,0,1},
          {1,0,1,0,0,0,0,0,1,1,1,0,1},
          {1,0,1,0,1,1,1,1,0,0,0,0,1},
          {1,0,1,0,1,0,0,0,0,1,1,0,1},
          {1,0,1,0,1,0,1,1,1,0,0,0,1},
          {1,0,1,0,1,0,0,0,1,1,1,1,1},
          {1,0,1,0,1,1,1,0,1,0,0,0,1},
          {1,0,0,0,1,0,0,0,0,0,1,9,1},
          {1,1,1,1,1,1,1,1,1,1,1,1,1}
        };
    
    private final List<Integer> path = new ArrayList<Integer>();
    private int pathIndex;
    
    public MazeSolver() {
        setTitle("Simple Maze Solver");
        setSize(640, 480);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        
        DepthInterpreter.searchPath(maze, 1, 1, path);
        pathIndex = path.size() - 2;
    }

    @Override
    public void paint(Graphics g) {
        super.paint(g);
        
        g.translate(50, 50);
        
        // draw the maze
        for (char row = 0; row < maze.length; row++) {
            for (char col = 0; col < maze[0].length; col++) {
                Color color;
                switch (maze[row][col]) {
                    case 1 : color = Color.CYAN; break;
                    case 9 : color = Color.RED; break;
                    default : color = Color.YELLOW;
                }
                g.setColor(color);
                g.fillRect(30 * col, 30 * row, 30, 30);
                g.setColor(Color.BLACK);
                g.drawRect(30 * col, 30 * row, 30, 30);
            }
        }
        
        // draw the path list
        for (char p = 0; p < path.size(); p += 2) {
            int pathX = path.get(p);
            int pathY = path.get(p + 1);
            g.setColor(Color.GREEN);
            g.fillRect(pathX * 30, pathY * 30, 30, 30);
        }

        // draw the ball on path
        int pathX = path.get(pathIndex);
        int pathY = path.get(pathIndex + 1);
        g.setColor(Color.PINK);
        g.fillOval(pathX * 30, pathY * 30, 30, 30);
    }
    
    @Override
    protected void processKeyEvent(KeyEvent ke) {	
    	
        if (ke.getID() != KeyEvent.KEY_PRESSED) {
        	
            return;
        }
        if (ke.getKeyCode() == KeyEvent.VK_RIGHT) {
            pathIndex -= 2;
            
            x++;
            
            System.out.println(x);
            
            if (pathIndex < 0) {
                pathIndex = 0;
            }
        }
        else if (ke.getKeyCode() == KeyEvent.VK_LEFT) {
            pathIndex += 2;
            
            x--;
            
            if(x<0) {
            	
            	x=0;
            	
            	}
            
            System.out.println(x);
            
            if (pathIndex > path.size() - 2) {
                pathIndex = path.size() - 2;
            }
        }
        repaint(); 
    }
    
    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                MazeSolver view = new MazeSolver();
                view.setVisible(true);
            }
        });
    }
    
}
