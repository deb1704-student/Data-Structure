import java.util.*;

class Solution {

    static class State {
        int row;
        int col;
        int energy;
        int mask;

        State(int row, int col, int energy, int mask) {
            this.row = row;
            this.col = col;
            this.energy = energy;
            this.mask = mask;
        }
    }

    public int minMoves(String[] classroom, int energy) {
        int m = classroom.length;
        int n = classroom[0].length();
        int startRow = 0;
        int startCol = 0;
        int totalLitter = 0;
        int[][] litterNumber = new int[m][n];

        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                litterNumber[i][j] = -1;
            }
        }

        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                char cell = classroom[i].charAt(j);

                if (cell == 'S') {
                    startRow = i;
                    startCol = j;
                }

                if (cell == 'L') {
                    litterNumber[i][j] = totalLitter;
                    totalLitter++;
                }
            }
        }

        int allCollected = (1 << totalLitter) - 1;

        Queue<State> queue = new LinkedList<>();

        // Handle case if starting cell 'S' also happens to be a litter 'L' cell
        int initialMask = 0;
        if (classroom[startRow].charAt(startCol) == 'L') {
            initialMask |= (1 << litterNumber[startRow][startCol]);
        }

        queue.offer(new State(startRow, startCol, energy, initialMask));

        boolean[][][][] visited = new boolean[m][n][energy + 1][1 << totalLitter];
        visited[startRow][startCol][energy][initialMask] = true;

        int[][] directions = {{1, 0}, {-1, 0}, {0, 1}, {0, -1}};
        int moves = 0;

        while (!queue.isEmpty()) {
            int size = queue.size();
            while (size-- > 0) {
                State current = queue.poll();

                if (current.mask == allCollected) {
                    return moves;
                }
                
                // FIX: If energy is 0, only skip if we are NOT on a Reset tile 'R'
                if (current.energy == 0 && classroom[current.row].charAt(current.col) != 'R') {
                    continue;
                }

                for (int[] direction : directions) {
                    int newRow = current.row + direction[0];
                    int newCol = current.col + direction[1];

                    if (newRow < 0 || newRow >= m || newCol < 0 || newCol >= n) {
                        continue;
                    }

                    char cell = classroom[newRow].charAt(newCol);
                    
                    // FIX: Prevent walking into obstacles
                    if (cell == 'X') {
                        continue;
                    }

                    int newEnergy = current.energy - 1;
                    
                    // If we drop below 0 energy mid-transit, this move is impossible
                    if (newEnergy < 0) {
                        continue;
                    }
                    
                    int newMask = current.mask;

                    if (cell == 'L') {
                        int litterNumberAtCell = litterNumber[newRow][newCol];
                        newMask |= (1 << litterNumberAtCell);
                    }
                    
                    // If we step onto a reset tile, energy fills right back up
                    if (cell == 'R') {
                        newEnergy = energy;
                    }
                    
                    if (visited[newRow][newCol][newEnergy][newMask]) {
                        continue;
                    }
                    visited[newRow][newCol][newEnergy][newMask] = true;

                    queue.offer(new State(newRow, newCol, newEnergy, newMask));
                }
            }
            moves++;
        }
        return -1;
    }
}
