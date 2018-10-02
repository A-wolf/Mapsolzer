package map;

import java.util.List;

public class DepthInterpreter {
    
    public static boolean searchPath(char[][] map, int x, int y
            , List<Integer> path) {
        
        if (map[y][x] == 9) {
            path.add(x);
            path.add(y);
            return true;
        }
        
        if (map[y][x] == 0) {
            map[y][x] = 2;
            
            int dx = -1;
            int dy = 0;
            if (searchPath(map, x + dx, y + dy, path)) {
                path.add(x);
                path.add(y);
                return true;
            }

            dx = 1;
            dy = 0;
            if (searchPath(map, x + dx, y + dy, path)) {
                path.add(x);
                path.add(y);
                return true;
            }

            dx = 0;
            dy = -1;
            if (searchPath(map, x + dx, y + dy, path)) {
                path.add(x);
                path.add(y);
                return true;
            }

            dx = 0;
            dy = 1;
            if (searchPath(map, x + dx, y + dy, path)) {
                path.add(x);
                path.add(y);
                return true;
            }
        }
        return false;
    }
    
}
