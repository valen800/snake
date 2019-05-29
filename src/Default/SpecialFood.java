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
    
    public SpecialFood(Snake snake, Wall wall) {
        super(snake, wall);
        int row;
        int col;
        do {
            row = (int) (Math.random() * (Config.numRows - 5) + 5);
            col = (int) (Math.random() * (Config.numCols - 5) + 5);
        } while(snake.isOnSnake(row, col) && wall.isOnWall(row, col));
        setRow(row);
        setCol(col);
        
    }
}
