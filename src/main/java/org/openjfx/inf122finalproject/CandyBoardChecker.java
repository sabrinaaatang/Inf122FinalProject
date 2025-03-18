package org.openjfx.inf122finalproject;

import java.util.ArrayList;

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

    /** need to check null list */
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
}
