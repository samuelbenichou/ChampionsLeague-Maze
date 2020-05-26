package algorithms.search;

import java.util.PriorityQueue;
import java.util.Comparator;

public class BestFirstSearch extends BreadthFirstSearch {

    public BestFirstSearch() {
        super();
        Comparator<AState> comparator = new Comparator<AState>() {
            @Override
            public int compare(AState o1, AState o2) {
                if (o1.getCost() < o2.getCost())
                    return -1;
                if(o1.getCost() > o2.getCost())
                    return 1;

                return 0;
            }
        };
        visited = new PriorityQueue<AState>(comparator);

    }

}
