package Default;

import java.awt.Color;
import java.awt.Graphics2D;
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
public class Wall extends Node {

    private List<Node> list;

    public Wall() {
        super(0, 0);
        list = new ArrayList<>();
    }
    //paint wall
    public void paintWall(Graphics2D g, int squareWidth, int squareHeight) {
        for (Node node : list) {
            Board.drawSquare(g, squareWidth, squareHeight, node.getCol(), node.getRow(), Color.WHITE);
        }
    }
    //retrurn list of walls
    public List<Node> getListWalls() {
        return this.list;
    }
    //return true if node are in the same position of wall
    public boolean isOnWall(int row, int col) {
        for (Node node : list) {
            if (row == node.getRow() && col == node.getCol()) {
                return true;
            }
        }
        return false;
    }
    //Check if there is a collison between the wall and the snake's head
    public boolean collisionsWall(Wall wall, Snake snake) {
        int[] positionsSnakeHead = {snake.getRowHead(), snake.getColHead()};

        for (int i = 0; i < wall.getListWalls().size(); i++) {
            if (positionsSnakeHead[0] == wall.getListWalls().get(i).getRow() && positionsSnakeHead[1] == wall.getListWalls().get(i).getCol()) {
                return true;
            }
        }
        return false;
    }
    //All walls in levels to add in list and paint in the board.
    public void levelWalls(int level) {
        switch (level) {
            case 3:
                this.list.add(new Node(12, 5));
                this.list.add(new Node(12, 6));
                this.list.add(new Node(12, 7));
                this.list.add(new Node(12, 8));
                this.list.add(new Node(13, 5));
                this.list.add(new Node(13, 6));
                this.list.add(new Node(13, 7));
                this.list.add(new Node(13, 8));
                this.list.add(new Node(14, 5));
                this.list.add(new Node(14, 6));
                this.list.add(new Node(14, 7));
                this.list.add(new Node(14, 8));
                this.list.add(new Node(15, 5));
                this.list.add(new Node(15, 6));
                this.list.add(new Node(15, 7));
                this.list.add(new Node(15, 8));
            case 2:
                this.list.add(new Node(5, 17));
                this.list.add(new Node(5, 18));
                this.list.add(new Node(5, 19));
                this.list.add(new Node(5, 20));
                this.list.add(new Node(6, 17));
                this.list.add(new Node(6, 18));
                this.list.add(new Node(6, 19));
                this.list.add(new Node(6, 20));
                this.list.add(new Node(7, 17));
                this.list.add(new Node(7, 18));
                this.list.add(new Node(7, 19));
                this.list.add(new Node(7, 20));
                this.list.add(new Node(8, 17));
                this.list.add(new Node(8, 18));
                this.list.add(new Node(8, 19));
                this.list.add(new Node(8, 20));
            case 1:
                this.list.add(new Node(19, 17));
                this.list.add(new Node(19, 18));
                this.list.add(new Node(19, 19));
                this.list.add(new Node(19, 20));
                this.list.add(new Node(20, 17));
                this.list.add(new Node(20, 18));
                this.list.add(new Node(20, 19));
                this.list.add(new Node(20, 20));
                this.list.add(new Node(21, 17));
                this.list.add(new Node(21, 18));
                this.list.add(new Node(21, 19));
                this.list.add(new Node(21, 20));
                this.list.add(new Node(22, 17));
                this.list.add(new Node(22, 18));
                this.list.add(new Node(22, 19));
                this.list.add(new Node(22, 20));
            case 0:
                this.list.add(new Node(5, 5));
                this.list.add(new Node(5, 6));
                this.list.add(new Node(5, 7));
                this.list.add(new Node(5, 8));
                this.list.add(new Node(6, 5));
                this.list.add(new Node(6, 6));
                this.list.add(new Node(6, 7));
                this.list.add(new Node(6, 8));
                this.list.add(new Node(7, 5));
                this.list.add(new Node(7, 6));
                this.list.add(new Node(7, 7));
                this.list.add(new Node(7, 8));
                this.list.add(new Node(8, 5));
                this.list.add(new Node(8, 6));
                this.list.add(new Node(8, 7));
                this.list.add(new Node(8, 8));
                break;
        }
    }
}
