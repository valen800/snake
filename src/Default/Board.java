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
    //public Timer timer2;
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

    public Board() {
        super();
        wall = new Wall();
        snake = new Snake(Config.snakeNodes, 2);
        food = new Food();
        specialFood = new SpecialFood();
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

    public void tick() {
        levelsWalls();
        
        if(snake.getListNodes().size()-1 < 1) {
            gameOver();
        } else {
            snake.movementSnake();
        }
        
        if (snake.SnakeBodyDetected() == true || snake.BoardEgdeDetected() == true || snake.getListNodes().size()-1 < 1 ||
                CollisionWalls() == true || score.getScore() < 0) {
            gameOver();
        }
        
        if (food.FoodDetected(snake.getRowHead(), snake.getColHead()) == true) {
            score.incrementScore();
            snake.addNodes(2);
            ccSpecialFood++;
        }
        
        if (specialFood.FoodDetected(snake.getRowHead(), snake.getColHead())) {
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
        repaint();
        turning = false;
    }

    public void tick2() {
        if(snake.getListNodes().size()-1 < 1 || snake2.getListNodes().size()-1 < 1) {
            gameOver();
        } else {
            snake.movementSnake();
            snake2.movementSnake();
        }
        if (snake2.SnakeBodyDetected() == true || snake2.BoardEgdeDetected() == true || CollisionsBetweenSnakes() == true
                || snake.BoardEgdeDetected() == true || snake.SnakeBodyDetected() == true || snake.getListNodes().size()-1 < 1 || snake2.getListNodes().size() -1< 1) {
            gameOver();
        }

        if (food.FoodDetected(snake2.getRowHead(), snake2.getColHead()) == true) {
            score2.incrementScore();
            snake2.addNodes(2);
        }
        if (food.FoodDetected(snake.getRowHead(), snake.getColHead()) == true) {
            score.incrementScore();
            snake.addNodes(2);
        }
        if (specialFood.FoodDetected(snake2.getRowHead(), snake2.getColHead())) {
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
        if (specialFood.FoodDetected(snake.getRowHead(), snake.getColHead())) {
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
        repaint();
        turning2 = false;
        turning = false;
    }
    
    

    public boolean CollisionsBetweenSnakes() {
        int[] positionsSnakeHead = {snake.getRowHead(), snake.getColHead()};
        int[] positionsSnake2Head = {snake2.getRowHead(), snake2.getColHead()};

        if (positionsSnakeHead[0]+1 == positionsSnake2Head[0] && positionsSnakeHead[1]+1 == positionsSnake2Head[1]
                || positionsSnake2Head[0]+1 == positionsSnakeHead[0] && positionsSnake2Head[1]+1 == positionsSnakeHead[0]) {
            return true;
        }
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
    
    public boolean CollisionWalls() {
        int[] positionsSnakeHead = {snake.getRowHead(), snake.getColHead()};
        
        for (int i=0; i<wall.getListWalls().size(); i++) {
            if (positionsSnakeHead[0] == wall.getListWalls().get(i).getRow() && positionsSnakeHead[1] == wall.getListWalls().get(i).getCol()) {
                return true;
            }
        }
        return false;
    }

    private void gameOver() {
        timer.stop();
        JOptionPane.showMessageDialog(null, "Game Over, press Ok to continue");
        if (twoPlayers == true) {
            reset2();
            score2.resetScore();
            score.resetScore();
        } else {
            reset();
        }
        timer.start();
    }
    
    public void levelsWalls() {
        if(score.getScore() >= 0 && score.getScore() <= 20) {
            wall.levelWalls(0);
        } else if(score.getScore() >= 20 && score.getScore() <= 60) {
            wall.levelWalls(1);
        } else if(score.getScore() >= 60 && score.getScore() <= 90) {
            wall.levelWalls(2);
        }   else if(score.getScore() >= 90 && score.getScore() <= 130) {
            wall.levelWalls(3);
        }
    }
    
    public void paintWallCondition(Graphics2D g2d) {
        if(score.getScore() >= 0 && score.getScore() <= 20) {
            wall.paintWall(g2d, getSquareWidth(), getSquareHeight());
        } else if(score.getScore() >= 20 && score.getScore() <= 60) {
            wall.paintWall(g2d, getSquareWidth(), getSquareHeight());
        } else if(score.getScore() >= 60 && score.getScore() <= 90) {
            wall.paintWall(g2d, getSquareWidth(), getSquareHeight());
        }   else if(score.getScore() > 90) {
            wall.paintWall(g2d, getSquareWidth(), getSquareHeight());
        }
    }
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
        return getWidth() / Config.numCols;
    }

    public int getSquareHeight() {
        return getHeight() / Config.numRows;
    }

    public void paintBoard(Graphics2D g2d) {
        for (int row = 0; row < Config.numRows; row++) {
            for (int col = 0; col < Config.numCols; col++) {
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

    private void reset() {
        snake = new Snake(Config.snakeNodes, 2);
        food = new Food();
        specialFood = new SpecialFood();
        ccSpecialFood = 0;
        score.resetScore();
        wall = new Wall();

        keyAdepter = new MyKeyAdapter();
        addKeyListener(keyAdepter);
        setFocusable(true);

        deltaTime = 150;
    }

    private void reset2() {
        snake2 = new Snake(Config.snakeNodes, 3);
        snake = new Snake(Config.snakeNodes, 2);
        specialFood = new SpecialFood();
        food = new Food();
        ccSpecialFood = 0;

        keyAdepter = new MyKeyAdapter();
        addKeyListener(keyAdepter);
        setFocusable(true);
        deltaTime = 150;
        
    }
}
