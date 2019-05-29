/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Default;

/**
 *
 * @author alu54279423k
 */
public class SingleObject {
    private static SingleObject single;
    private int numRows = 25;
    private int numCols = 25;
    private int score = 0;
    private int snakeNodes = 4;
    
    public static SingleObject getSingleObject() {
        if(single == null) {
            single = new SingleObject();
        }
        return single;
    }

    private SingleObject() {        
    }

    public int getNumRows() {
        return numRows;
    }

    public void setNumRows(int numRows) {
        this.numRows = numRows;
    }

    public int getNumCols() {
        return numCols;
    }

    public void setNumCols(int numCols) {
        this.numCols = numCols;
    }

    public int getScore() {
        return score;
    }

    public void setScore(int score) {
        this.score = score;
    }

    public int getSnakeNodes() {
        return snakeNodes;
    }

    public void setSnakeNodes(int snakeNodes) {
        this.snakeNodes = snakeNodes;
    }
    
    
    
    
    
}
