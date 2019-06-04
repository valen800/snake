package Default;


import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Toolkit;
import java.util.ArrayList;
import java.util.List;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author alu54279423k
 */
public class Snake {
    
    private List<Node> body;
    private Direction direction = Direction.RIGHT;
    long tInit;
    
    public Snake(int numNodes, int position) {
        body = new ArrayList<>();
        for(int i=1; i <= numNodes; i++) {
            body.add(new Node((SingleObject.getSingleObject().getNumRows()/position), (SingleObject.getSingleObject().getNumCols()/position) - i));
        }
    }
    //move the snake's head to next position and his own body
    public void movementSnake() {
        tInit = System.currentTimeMillis();
        float t;
        t = (System.currentTimeMillis() - tInit)/100.0f;
            Toolkit.getDefaultToolkit().sync();
        Node head = body.get(0);
        int[] positions = {head.getRow(),head.getCol()};
        Direction direction = this.direction;
        switch (direction) {
            case DOWN:
                head.setRow(head.getRow() +1);
                body.set(0, head);
                break;
            case UP:
                head.setRow(head.getRow() -1);
                body.set(0, head);
                break;
            case LEFT:
                head.setCol(head.getCol() -1);
                body.set(0, head);
                break;
            case RIGHT:
                head.setCol(head.getCol() +1);
                body.set(0, head);
                break;
            default:
                break;
        }
        Node node = new Node(positions[0], positions[1]);
        Node nextNode = body.get(1);
        body.set(1, node);
        
        for (int i=2; i < body.size(); i++) {
            positions[0] = nextNode.getRow();
            positions[1] = nextNode.getCol();
            node = new Node(positions[0], positions[1]);
            nextNode = body.get(i);
            body.set(i, node);
        }
    }
    //check if the snake's head hits his own body
    public boolean SnakeBodyDetected() {
        int[] positionsHead = {getRowHead(), getColHead()};
        
        for (int i=1; i<getListNodes().size() -1; i++) {
            if(positionsHead[0] == getListNodes().get(i).getRow() 
                    && positionsHead[1] == getListNodes().get(i).getCol()) {
                return true;
            }
        }
        return false;
    }
    //detects if the snake hits an edge
    public boolean BoardEgdeDetected() {
        int[] positionsHead = {getRowHead(), getColHead()};
        
        for (int row=0; row<SingleObject.getSingleObject().getNumRows(); row++) {
            if(positionsHead[0] == row && positionsHead[1] == (SingleObject.getSingleObject().getNumCols() - SingleObject.getSingleObject().getNumCols()) - 1) {
                return true;
            }
            for (int col=0; col<SingleObject.getSingleObject().getNumCols(); col++) {
                if(positionsHead[0] == SingleObject.getSingleObject().getNumRows() && positionsHead[1] == col) {
                    return true;
                }
                for (int row2=0; row2<SingleObject.getSingleObject().getNumRows(); row2++) {
                    if(positionsHead[0] == row2 && positionsHead[1] == SingleObject.getSingleObject().getNumCols()) {
                        return true;
                    }
                    for (int col2=0; col2<SingleObject.getSingleObject().getNumRows(); col2++) {
                        if(positionsHead[0] == (SingleObject.getSingleObject().getNumRows() - SingleObject.getSingleObject().getNumRows())-1 && positionsHead[1] == col2) {
                            return true;
                        }
                    }
                }
            }
        }
        return false;
    }
    //increase nodes of list
    public void addNodes(int node) {
        if (node > 0) {
            for(int i = 0; i<node; i++) {
                getListNodes().add(getListNodes().get(getListNodes().size() - 1));
            }
        }
    }
    //Decrease nodes of list
    public void subtractNodes(int node) {
        if (node > 0) {
            for(int i = 0; i<node; i++) {
                getListNodes().remove(getListNodes().get(getListNodes().size() - 1));
            }
        }
    }
    
    public void setDirection(Direction direction) {
        this.direction = direction;
    }
    
    public Direction getDirection() {
        return this.direction;
    }
    //paint snake and change the color of head
    public void paintSnake(Graphics2D g, int squareWidth, int squareHeight) {
        boolean headColor = false;
        for (Node node: body) {
            if(!headColor) {
                Board.drawSquare(g, squareWidth, squareHeight, node.getCol(),node.getRow() , Color.yellow);
                headColor = true;
            } else {
                Board.drawSquare(g, squareWidth, squareHeight, node.getCol(),node.getRow() , Color.green);
            }
        }
    }
    //return true if node are in the same position of snake
    public boolean isOnSnake(int row, int col) {
        for (Node node : body) {
            if (row == node.getRow() && col == node.getCol()) {
                return true;
            }
        }
        return false;
    }
    
    public int getRowHead() {
        return body.get(0).getRow();
    }
    
    public int getColHead() {
        return body.get(0).getCol();
    }
    //return body of snake
    public List<Node> getListNodes() {
        return body;
    }
            
}
