package Default;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.*;
import java.util.*;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.Timer;
import javax.swing.*;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
/**
 *
 * @author alu54279423k
 */
public class Board extends JPanel {

    public Timer timer;
    private Snake snake;
    private Snake snake2;
    private Wall wall;
    private Food food;
    private SpecialFood specialFood;
    private int deltaTime;
    private MyKeyAdapter keyAdepter;
    private ScoreBoard score;
    private ScoreBoard score2;
    private boolean turning = false;
    private boolean turning2 = false;
    private int ccSpecialFood = 0;
    private boolean twoPlayers = false;

    public void setScoreBoard(ScoreBoard scoreBoard) {
        this.score = scoreBoard;
    }
    public void setScoreBoard2(ScoreBoard scoreBoard) {
        this.score2 = scoreBoard;
    }
    //boolean to set the two players mode
    public void setTwoPlayers(boolean twoPlayers) {
        this.twoPlayers = twoPlayers;
        if (twoPlayers == true) {
            reset2();
        }
    }

    class MyKeyAdapter extends KeyAdapter {

        @Override
        public void keyPressed(KeyEvent e) {
            switch (e.getKeyCode()) {
                case KeyEvent.VK_LEFT:
                    if (snake.getDirection() != Direction.RIGHT && !turning) {
                        turning = true;
                        snake.setDirection(Direction.LEFT);
                    }
                    break;
                case KeyEvent.VK_RIGHT:
                    if (snake.getDirection() != Direction.LEFT && !turning) {
                        turning = true;
                        snake.setDirection(Direction.RIGHT);
                    }
                    break;
                case KeyEvent.VK_UP:
                    if (snake.getDirection() != Direction.DOWN && !turning) {
                        turning = true;
                        snake.setDirection(Direction.UP);
                    }
                    break;
                case KeyEvent.VK_DOWN:
                    if (snake.getDirection() != Direction.UP && !turning) {
                        turning = true;
                        snake.setDirection(Direction.DOWN);
                    }
                    break;
                case KeyEvent.VK_A:
                    if (twoPlayers == true) {
                        if (snake2.getDirection() != Direction.RIGHT && !turning2) {
                            turning2 = true;
                            snake2.setDirection(Direction.LEFT);
                        }
                    }
                    break;
                case KeyEvent.VK_D:
                    if (twoPlayers == true) {
                        if (snake2.getDirection() != Direction.LEFT && !turning2) {
                            turning2 = true;
                            snake2.setDirection(Direction.RIGHT);
                        }
                    }
                    break;
                case KeyEvent.VK_W:
                    if (twoPlayers == true) {
                        if (snake2.getDirection() != Direction.DOWN && !turning2) {
                            turning2 = true;
                            snake2.setDirection(Direction.UP);
                        }
                    }
                    break;
                case KeyEvent.VK_S:
                    if (twoPlayers == true) {
                        if (snake2.getDirection() != Direction.UP && !turning2) {
                            turning2 = true;
                            snake2.setDirection(Direction.DOWN);
                        }
                    }
                    break;
                case KeyEvent.VK_P:
                    pauseGame();
                    break;
                default:
                    break;
            }
            repaint();
        }
    }
    //Create all objects
    public Board() {
        super();
        wall = new Wall();
        snake = new Snake(SingleObject.getSingleObject().getSnakeNodes(), 2);
        food = new Food(snake, wall);
        specialFood = new SpecialFood(snake, wall);
        ccSpecialFood = 0;

        keyAdepter = new MyKeyAdapter();
        addKeyListener(keyAdepter);
        setFocusable(true);

        deltaTime = 150;
        timer = new Timer(deltaTime, new ActionListener() {
            public void actionPerformed(ActionEvent evt) {
                if (twoPlayers == true) {
                    tick2();
                } else {
                    tick();
                }
            }
        });
        timer.start();
    }
    //method for a singleplayer
    public void tick() {
        levelsWalls();
        gameOverConditions();
        allFoodDetectedConditions();

        repaint();
        Toolkit.getDefaultToolkit().sync();
        turning = false;
    }
    //method for a two players
    public void tick2() {
        levelsWalls();
        gameOverConditions();
        allFoodDetectedConditions();

        repaint();
        Toolkit.getDefaultToolkit().sync();
        turning2 = false;
        turning = false;
    }
    //check if there has been a collision between snakes (bodys and heads)
    public boolean collisionsBetweenSnakes() {
        int[] positionsSnakeHead = {snake.getRowHead(), snake.getColHead()};
        int[] positionsSnake2Head = {snake2.getRowHead(), snake2.getColHead()};

        if (positionsSnakeHead[0] + 1 == positionsSnake2Head[0] && positionsSnakeHead[1] + 1 == positionsSnake2Head[1]
                || positionsSnake2Head[0] + 1 == positionsSnakeHead[0] && positionsSnake2Head[1] + 1 == positionsSnakeHead[0]
                || collisionsBetweenBodys()/*method collision body*/ == true) {
            return true;
        }
        return false;
    }
    //method to check collision between bodys
    private boolean collisionsBetweenBodys() {
        int[] positionsSnakeHead = {snake.getRowHead(), snake.getColHead()};
        int[] positionsSnake2Head = {snake2.getRowHead(), snake2.getColHead()};
        
        for (int i = 1; i < snake.getListNodes().size() - 1; i++) {
            if (positionsSnake2Head[0] == snake.getListNodes().get(i).getRow()
                    && positionsSnake2Head[1] == snake.getListNodes().get(i).getCol()) {
                return true;
            }
        }
        for (int i = 1; i < snake2.getListNodes().size() - 1; i++) {
            if (positionsSnakeHead[0] == snake2.getListNodes().get(i).getRow()
                    && positionsSnakeHead[1] == snake2.getListNodes().get(i).getCol()) {
                return true;
            }
        }
        return false;
    }
    
    //All conditions to lose the game (in a singleplayer and two players)
    private void gameOverConditions() {
        if (twoPlayers == true) {
            snake.movementSnake();
            snake2.movementSnake();

            if (snake2.SnakeBodyDetected() == true
                    || snake2.BoardEgdeDetected() == true
                    || collisionsBetweenSnakes() == true
                    || snake.BoardEgdeDetected() == true
                    || snake.SnakeBodyDetected() == true
                    || snake.getListNodes().size() - 1 < 1
                    || snake2.getListNodes().size() - 1 < 1
                    || wall.collisionsWall(wall, snake) == true
                    || wall.collisionsWall(wall, snake2) == true) {
                gameOver();
            }
        } else {
            snake.movementSnake();
            if (snake.SnakeBodyDetected() == true 
                    || snake.BoardEgdeDetected() == true 
                    || snake.getListNodes().size() - 1 < 1
                    || wall.collisionsWall(wall, snake) == true 
                    || score.getScore() < 0) {
                gameOver();
            }
        }
    }
    //All conditions to eat a food and specialFood
    private void allFoodDetectedConditions() {
        if (twoPlayers == true) {
            normalFoodConditions(snake2);
            normalFoodConditions(snake);
            specialFoodConditions(snake2);
            specialFoodConditions(snake);
        } else {
            normalFoodConditions(snake);
            specialFoodConditions(snake);
        }
    }
    //if condition is true, increase score, snake nodes and increase counter of specialFood
    private void normalFoodConditions(Snake snake) {
        if (food.FoodDetected(snake.getRowHead(), snake.getColHead(), snake, wall) == true) {
                score.incrementScore();
                snake.addNodes(2);
                ccSpecialFood++;
            }
    }
    //if condition is true and counter greater than fifteen increase 5 score point and nodes,if not descrease two 
    private void specialFoodConditions(Snake snake) {
        if (specialFood.FoodDetected(snake.getRowHead(), snake.getColHead(), snake, wall) == true) {
            if (ccSpecialFood > 15) {
                for (int i = 0; i < 5; i++) {
                    score.incrementScore();
                }
                snake.addNodes(5);
                ccSpecialFood = 0;
            } else {
                score.decreaseScore();
                snake.subtractNodes(2);
                ccSpecialFood++;
            }
        }
    }
    //message GAME OVER and reset all.
    private void gameOver() {
        timer.stop();
        if (twoPlayers == true) {
            JOptionPane.showMessageDialog(null, "GAME OVER" + "\n" +"Player 1: " +score.getScore()
            + "\n" + "Player2: " + score2.getScore());
            reset2();
            score2.resetScore();
            score.resetScore();
        } else {
            JOptionPane.showMessageDialog(null, "GAME OVER" + "\n" +"Player 1: " +score.getScore());
            reset();
        }
        timer.start();
    }
    //Conditions for levels of walls to appear
    public void levelsWalls() {
        if (score.getScore() >= 0 && score.getScore() <= 20) {
            wall.levelWalls(0);

        } else if (score.getScore() >= 20 && score.getScore() <= 60) {
            wall.levelWalls(1);

        } else if (score.getScore() >= 60 && score.getScore() <= 90) {
            wall.levelWalls(2);

        } else if (score.getScore() >= 90 && score.getScore() <= 130) {
            wall.levelWalls(3);
        }
    }
    //Conditions for levels of walls to paint
    public void paintWallCondition(Graphics2D g2d) {
        if (wall != null && score != null) {
            if (score.getScore() >= 0 && score.getScore() <= 20) {
                wall.paintWall(g2d, getSquareWidth(), getSquareHeight());

            } else if (score.getScore() >= 20 && score.getScore() <= 60) {
                wall.paintWall(g2d, getSquareWidth(), getSquareHeight());

            } else if (score.getScore() >= 60 && score.getScore() <= 90) {
                wall.paintWall(g2d, getSquareWidth(), getSquareHeight());

            } else if (score.getScore() > 90) {
                wall.paintWall(g2d, getSquareWidth(), getSquareHeight());
            }
        }

    }
    //paint all objects into board
    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2d = (Graphics2D) g;
        paintBoard(g2d);
        paintWallCondition(g2d);
        snake.paintSnake(g2d, getSquareWidth(), getSquareHeight());
        if (twoPlayers == true) {
            snake2.paintSnake(g2d, getSquareWidth(), getSquareHeight());
        }
        food.paintFood(g2d, getSquareWidth(), getSquareHeight(), Color.RED);
        if (ccSpecialFood > 15) {
            specialFood.paintFood(g2d, getSquareWidth(), getSquareHeight(), Color.CYAN);
        } else {
            specialFood.paintFood(g2d, getSquareWidth(), getSquareHeight(), Color.PINK);
        }
    }
    
    public int getSquareWidth() {
        return getWidth() / SingleObject.getSingleObject().getNumCols();
    }

    public int getSquareHeight() {
        return getHeight() / SingleObject.getSingleObject().getNumRows();
    }
    
    public void paintBoard(Graphics2D g2d) {
        for (int row = 0; row < SingleObject.getSingleObject().getNumRows(); row++) {
            for (int col = 0; col < SingleObject.getSingleObject().getNumCols(); col++) {
                drawSquare(g2d, getSquareWidth(), getSquareHeight(), col, row, Color.GRAY);
            }
        }
    }

    public static void drawSquare(Graphics2D g, int squareWidth, int squareHeight,
            int col, int row, Color color) {

        int x = col * squareWidth;
        int y = row * squareHeight;
        g.setColor(color);
        g.fillRect(x + 1, y + 1, squareWidth - 2, squareHeight - 2);
        g.setColor(color.brighter());
        g.drawLine(x, y + squareHeight - 1, x, y);
        g.drawLine(x, y, x + squareWidth - 1, y);
        g.setColor(color.darker());
        g.drawLine(x + 1, y + squareHeight - 1, x + squareWidth - 1, y + squareHeight - 1);
        g.drawLine(x + squareWidth - 1, y + squareHeight - 1, x + squareWidth - 1, y + 1);
    }

    private void pauseGame() {
        timer.stop();
        JOptionPane.showMessageDialog(null, "Pause, press OK");
        timer.start();
    }
    //Create all the other time to start over (singleplayer)
    public void reset() {
        wall = new Wall();
        snake = new Snake(SingleObject.getSingleObject().getSnakeNodes(), 2);
        food = new Food(snake, wall);
        specialFood = new SpecialFood(snake, wall);
        ccSpecialFood = 0;
        score.resetScore();

        keyAdepter = new MyKeyAdapter();
        addKeyListener(keyAdepter);
        setFocusable(true);

        deltaTime = 150;
    }
    //Create all the other time to start over (Two players)
    public void reset2() {
        snake2 = new Snake(SingleObject.getSingleObject().getSnakeNodes(), 6);
        snake = new Snake(SingleObject.getSingleObject().getSnakeNodes(), 2);
        wall = new Wall();
        specialFood = new SpecialFood(snake, wall);
        food = new Food(snake, wall);
        ccSpecialFood = 0;

        keyAdepter = new MyKeyAdapter();
        addKeyListener(keyAdepter);
        setFocusable(true);
        deltaTime = 150;
    }
}
