package leetcode.design;

import java.util.Deque;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.Set;

public class DesignSnakeGame {
/*
Design a Snake game that is played on a device with screen size height x width. Play the game online if you are not familiar with the game.

The snake is initially positioned at the top left corner (0, 0) with a length of 1 unit.

You are given an array food where food[i] = (ri, ci) is the row and column position of a piece of food that the snake can eat. When a snake eats a piece of food, its length and the game's score both increase by 1.

Each piece of food appears one by one on the screen, meaning the second piece of food will not appear until the snake eats the first piece of food.

When a piece of food appears on the screen, it is guaranteed that it will not appear on a block occupied by the snake.

The game is over if the snake goes out of bounds (hits a wall) or if its head occupies a space that its body occupies after moving (i.e. a snake of length 4 cannot run into itself).

Implement the SnakeGame class:

SnakeGame(int width, int height, int[][] food) Initializes the object with a screen of size height x width and the positions of the food.
int move(String direction) Returns the score of the game after applying one direction move by the snake. If the game is over, return -1.
 */
    public static void main(String[] args) {
        int[][] food = {
                //food[i] = (ri, ci)
                {1, 2}, //appears first time here (row : 1, col : 2)
                {0, 1} //appears second time here
        };
        SnakeGame snakeGame = new SnakeGame(3, 2, food);
        System.out.println(snakeGame.move("R")); // return 0
        System.out.println(snakeGame.move("D")); // return 0
        System.out.println(snakeGame.move("R")); // return 1, snake eats the first piece of food. The second piece of food appears at (0, 1).
        System.out.println(snakeGame.move("U")); // return 1
        System.out.println(snakeGame.move("L")); // return 2, snake eats the second food. No more food appears.
        System.out.println(snakeGame.move("U")); // return -1, game over because snake collides with border
    }

    static class SnakeGame {
        int width;
        int height;
        int foodIndex;
        int[][] food;
        int score;
        //2D position info is encoded to 1D and stored as two copies
        Set<Integer> set; // this copy is good for fast loop-up for eating body case
        Deque<Integer> body; // this copy is good for updating tail

        public SnakeGame(int width, int height, int[][] food) {
            this.width = width;
            this.height = height;
            this.food = food;
            set = new HashSet<>();
            //The snake is initially positioned at the top left corner (0, 0)
            set.add(0); //intially at [0][0] where we start
            body = new LinkedList<>();
            //Inserts the specified element at the end of this deque
            body.offerLast(0);
        }

        public int move(String direction) {
            //Case 0: game already over do nothing
            if(score == -1) {
                return -1;
            }
            // compute new head
            //Retrieves, but does not remove, the first element of this deque
            int rowHead = body.peekFirst() / width;
            int colHead = body.peekFirst() % width;
            switch (direction) {
                case "U":
                    rowHead--;
                    break;
                case "D":
                    rowHead++;
                    break;
                case "L":
                    colHead--;
                    break;
                case "R":
                    colHead++;
                    break;
                default:
                    throw new IllegalStateException("Invalid");
            }
            int head = rowHead * width + colHead;
            //case 1: out of boundary or eating body
            set.remove(body.peekLast()); // new head is legal to be in old tail's position, remove from set temporarily
            if (rowHead < 0 || rowHead == height || colHead < 0 || colHead == width || set.contains(head)) {
                return score = -1;
            }
            // add head for case2 and case3
            set.add(head);
            body.offerFirst(head);
            //case2: eating food, keep tail, add head
            if (foodIndex < food.length && rowHead == food[foodIndex][0] && colHead == food[foodIndex][1]) {
                set.add(body.peekLast()); // old tail does not change, so add it back to set
                foodIndex++;
                return ++score;
            }
            //case3: normal move, remove tail, add head
            body.pollLast();
            return score;
        }
    }
}
