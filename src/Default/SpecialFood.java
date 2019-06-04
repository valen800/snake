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
 * @author Valentin
 */
public class SpecialFood extends Food{
    
    //Is the same that normal food, the conditions to be a specialFood are in board class
    public SpecialFood(Snake snake, Wall wall) {
        super(snake, wall);
        int row;
        int col;
        do {
            System.out.println("Recolando comida especial");
            row = (int) (Math.random() * (SingleObject.getSingleObject().getNumRows()));
            col = (int) (Math.random() * (SingleObject.getSingleObject().getNumCols()));
        } while(snake.isOnSnake(row, col) || wall.isOnWall(row, col));
        setRow(row);
        setCol(col);
        
    }
}
