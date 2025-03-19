package org.openjfx.inf122finalproject;

import javafx.beans.property.ObjectProperty;
import javafx.geometry.Pos;

import java.util.*;

public class CandyBoardChecker {

    /** When perform swap up and down direction */
    public static ArrayList<ArrayList<Position>> upDownChecker(Block[][] board, Position up, Position down) {
        ArrayList<ArrayList<Position>> upDownMatchedPos = new ArrayList<>();

        Position upDir = Position.upDirection();
        Position downDir = Position.downDirection();
        Position leftDir = Position.leftDirection();
        Position rightDir = Position.rightDirection();

        /* check up */
        CheckMatchThreeWay(board, up, upDownMatchedPos, upDir, leftDir, rightDir);

        /* down part */
        CheckMatchThreeWay(board, down, upDownMatchedPos, downDir, leftDir, rightDir);

        return upDownMatchedPos;
    }

    /** When perform swap left and right direction */
    public static ArrayList<ArrayList<Position>> leftRightChecker(Block[][] board, Position left, Position right) {
        ArrayList<ArrayList<Position>> leftRightMatchedPos = new ArrayList<>();

        Position upDir = Position.upDirection();
        Position downDir = Position.downDirection();
        Position leftDir = Position.leftDirection();
        Position rightDir = Position.rightDirection();

        /* check left */
        CheckMatchThreeWay(board, left, leftRightMatchedPos, leftDir, upDir, downDir);

        /* down right */
        CheckMatchThreeWay(board, right, leftRightMatchedPos, rightDir, upDir, downDir);

        return leftRightMatchedPos;
    }

    private static void CheckMatchThreeWay(Block[][] board, Position pos,
                                           ArrayList<ArrayList<Position>> upDownMatchedPos,
                                           Position d1,
                                           Position d2, Position d3)
    {
        ArrayList<Position> matched = new ArrayList<>();
        BlockType type = board[pos.getRow()][pos.getColumn()].getType();
        boolean isMatched = false;
        int totalMatched = 0;
        int numMatch = checkDirection(board, type, pos.nextPos(d1), d1);
        if(numMatch >= 2) {
            isMatched = true;
            addPosToArray(matched, pos, d1, numMatch);
            totalMatched += numMatch;
        }
        int numMatch2 = checkDirection(board, type, pos.nextPos(d2), d2);
        if(numMatch2 >= 2) {
            isMatched = true;
            addPosToArray(matched, pos, d2, numMatch2);
            totalMatched += numMatch2;
        }
        int numMatch3 = checkDirection(board, type, pos.nextPos(d3), d3);
        if(numMatch3 >= 2) {
            isMatched = true;
            addPosToArray(matched, pos, d3, numMatch3);
            totalMatched += numMatch3;
        }
        if(numMatch2 == 1 && numMatch3 == 1) {
            isMatched = true;
            totalMatched += 2;
            matched.add(pos.nextPos(d2));
            matched.add(pos.nextPos(d3));
        }
        else if(numMatch2 >= 2 && numMatch3 == 1) {
            totalMatched += 1;          // one more match on d3 direction
            matched.add(pos.nextPos(d3));
        }
        else if(numMatch2 == 1 && numMatch3 >= 2) {
            totalMatched += 1;          // one more match on d2 direction
            matched.add(pos.nextPos(d2));
        }
        if(isMatched) {
            totalMatched += 1;          // finally add the center one
            matched.add(pos);
            upDownMatchedPos.add(matched);
        }

        System.out.println("Number of candy matched: " + totalMatched);
    }


    private static int checkDirection(Block[][] board, BlockType type, Position pos, Position dir) {
        int rows = board.length;
        int cols = board[0].length;
        System.out.println("checking: " + pos + " with type " + type + " and dir " + dir);
        if(isInsideBoard(rows, cols, pos)) {
            Block block = board[pos.getRow()][pos.getColumn()];
            if (block.getType() != BlockType.EMPTY_BLOCK) {
                CandyBlock b = (CandyBlock) block;
                if (b.isMatchable() && type == block.getType())
                    return 1 + checkDirection(board, type, pos.nextPos(dir), dir);
            }
        }
        return 0;
    }

    private static boolean isInsideBoard(int maxRow, int maxCol, Position pos) {
        if( pos.getRow() < maxRow && pos.getRow() >= 0 && pos.getColumn() < maxCol && pos.getColumn() >= 0 ) {
            return true;
        }
        return false;
    }

    private static void addPosToArray(ArrayList<Position> matchedPos, Position pos, Position dir, int count) {
        Position position = pos.nextPos(dir);
        for (int i = 0; i < count; ++i) {
            matchedPos.add(position);
            position = position.nextPos(dir);
        }
    }

    /** May return an empty list */
    public static ArrayList<Position> flattener(ArrayList<ArrayList<Position>> board) {
        ArrayList<Position> flattened = new ArrayList<>();
        for (ArrayList<Position> row : board) {
            flattened.addAll(row);
        }
        for(Position pos : flattened) {
            System.out.println("removed: " + pos);
        }
        return flattened;
    }

    public static void findMatches(ObjectProperty<Block[][]> blocks, ArrayList<Position> matchedPos) {
        final int minClearCount = 3;
        Block[][] board = blocks.get();
        int boardWidth = board[0].length;
        int boardHeight = board.length;
        boolean[][] toClear = new boolean[boardHeight][boardWidth];

        // Horizontal check
        for (int row = 0; row < boardHeight; row++) {
            int count = 1;
            for (int col = 1; col < boardWidth; col++) {
                Block prev = board[row][col - 1];
                Block current = board[row][col];
                if (blocksMatch(prev, current)) {
                    count++;
                } else {
                    if (count >= minClearCount) {
                        for (int c = col - count; c < col; c++) {
                            toClear[row][c] = true;
                        }
                    }
                    count = 1;
                }
            }
            if (count >= minClearCount) {
                for (int c = boardWidth - count; c < boardWidth; c++) {
                    toClear[row][c] = true;
                }
            }
        }

        // Vertical check
        for (int col = 0; col < boardWidth; col++) {
            int count = 1;
            for (int row = 1; row < boardHeight; row++) {
                Block prev = board[row - 1][col];
                Block current = board[row][col];
                if (blocksMatch(prev, current)) {
                    count++;
                } else {
                    if (count >= minClearCount) {
                        for (int r = row - count; r < row; r++) {
                            toClear[r][col] = true;
                        }
                    }
                    count = 1;
                }
            }
            if (count >= minClearCount) {
                for (int r = boardHeight - count; r < boardHeight; r++) {
                    toClear[r][col] = true;
                }
            }
        }

        bool2DToPositionList(toClear, matchedPos);
    }

    private static boolean blocksMatch(Block prev, Block curr) {
        if(prev.isEmptyType() || curr.isEmptyType()) { return false ; }

        return prev.getType() == curr.getType();
    }

    private static void bool2DToPositionList(boolean[][] toClear, ArrayList<Position> matchedPos) {
        for(int i = 0; i < toClear.length; i++) {
            for(int j = 0; j < toClear[i].length; j++) {
                if(toClear[i][j]) {
                    matchedPos.add(new Position(j, i));
                }
            }
        }

//        return positions;
    }
}
