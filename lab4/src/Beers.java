import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Set;
import java.util.*;

public class Beers {
    private static Scanner input = new Scanner(System.in, "UTF-8");

    public static void main(String[] args) {
        String keyMenu = input.nextLine().toUpperCase();
        switch (keyMenu) {
            case "1":  {
                String[] in = new String[]{"YNN", "YNY", "YNY", "NYY", "NYY", "NYN"};
                int result = choose(in);
                System.out.println(result);
                break;
            }
            case "2": {
                String[] in = new String[]{"YN", "NY"};
                int result = choose(in);
                System.out.println(result);
                break;
            }
    }
    }



    private static int choose(String[] in) {

        int beers = in[0].length();
        List<Integer>[] adjacency = new List[beers];
        for (int i = 0; i < beers; i++)
            adjacency[i] = new LinkedList<>();
        for (int i = 0; i < in.length; i++) {
            for (int j = 0; j < in[i].length(); j++) {
                if (in[i].charAt(j) == 'Y')
                    adjacency[j].add(i);
            }
        }

        for (int length = 1; length <= beers; length++) {
            Set<Integer> chosenBeers = new HashSet<>();
            for (int i = 1; i <= length; i++) {
                chosenBeers.clear();
                if (isSatisfied(adjacency, length, 0, chosenBeers, in.length))
                    return length;
            }
        }
        return 0;
    }

    private static boolean isSatisfied(List<Integer>[] adj, int length, int nextBeer, Set<Integer> chosenBeers, int employeesCount) {
        if (chosenBeers.size() == length) {
            Set<Integer> employees = new HashSet<>();
            for (int beer : chosenBeers) {
                employees.addAll(adj[beer]);
            }
            return employees.size() == employeesCount;
        }

        if (nextBeer == adj.length)
            return false;

        if (isSatisfied(adj, length, nextBeer + 1, chosenBeers, employeesCount))
            return true;

        chosenBeers.add(nextBeer);
        if (isSatisfied(adj, length, nextBeer + 1, chosenBeers, employeesCount))
            return true;

        chosenBeers.remove(nextBeer);
        return false;
    }


}