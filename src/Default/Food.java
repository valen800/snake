package Default;

import java.awt.Color;
import java.awt.Graphics2D;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
/**
 *
 * @author alu54279423k
 */
public class Food extends Node {

    public Food(Snake snake, Wall wall) {
        super(0, 0);
        int row;
        int col;
        do {
            row = (int) (Math.random() * SingleObject.getSingleObject().getNumRows());
            col = (int) (Math.random() * SingleObject.getSingleObject().getNumCols());
        } while (snake.isOnSnake(row, col) || wall.isOnWall(row, col));
        setRow(row);
        setCol(col);
    }
    //paint food
    public void paintFood(Graphics2D g, int squareWidth, int squareHeight, Color color) {
        Board.drawSquare(g, squareWidth, squareHeight, getCol(), getRow(), color);
    }
    //Prevent food from being put in a wall or body of snake and then he puts it on the board
    public boolean FoodDetected(int rowHead, int colHead, Snake snake, Wall wall) {
        int[] positionsHead = {rowHead, colHead};
        int[] positionsFood = {getRow(), getCol()};

        int newRowFood = 0;
        int newColFood = 0;

        if (positionsHead[0] == positionsFood[0] && positionsHead[1] == positionsFood[1]) {
            do {
                System.out.println("Recolocando");
                newRowFood = (int) (Math.random() * SingleObject.getSingleObject().getNumRows());
                newColFood = (int) (Math.random() * SingleObject.getSingleObject().getNumCols());
            } while (snake.isOnSnake(newRowFood, newColFood) || wall.isOnWall(newRowFood, newColFood));
            setRow(newRowFood);
            setCol(newColFood);
            return true;
        }
        return false;
    }

}
