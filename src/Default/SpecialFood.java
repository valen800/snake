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
    
    public SpecialFood() {
        super();
        int row = (int) (Math.random() * (Config.numRows - 5) + 5);
        int col = (int) (Math.random() * (Config.numCols - 5) + 5);
        setRow(row);
        setCol(col);
        
    }
}
