package class16.class16.study;

import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;

public class Granph {
    public HashMap<Integer,Node> nodes;
    public HashSet<Edge> edges;

    Granph(){
        nodes = new HashMap<>();
        edges = new HashSet<>();
    }
}
