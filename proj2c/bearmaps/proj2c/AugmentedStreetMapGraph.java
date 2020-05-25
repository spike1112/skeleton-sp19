package bearmaps.proj2c;

import bearmaps.proj2ab.KDTree;
import bearmaps.hw4.streetmap.Node;
import bearmaps.hw4.streetmap.StreetMapGraph;
import bearmaps.proj2ab.ArrayHeapMinPQ;
import bearmaps.proj2ab.Point;
import bearmaps.proj2ab.PointSet;
import bearmaps.proj2ab.WeirdPointSet;
import bearmaps.lab9.*;

import java.util.*;

/**
 * An augmented graph that is more powerful that a standard StreetMapGraph.
 * Specifically, it supports the following additional operations:
 *
 *
 * @author Alan Yao, Josh Hug, ________
 */
public class AugmentedStreetMapGraph extends StreetMapGraph {

    private List<Node> nodes;
    private MyTrieSet trieSet;
    private Map<String, String> cleanedToFull;

    private Map<String, List<Node>> nameToNodes;//one name may have(map) several nodes
    


    public AugmentedStreetMapGraph(String dbPath) {
        super(dbPath);
        // You might find it helpful to uncomment the line below:
        // List<Node> nodes = this.getNodes();
        nodes = this.getNodes();
        trieSet = new MyTrieSet();
        cleanedToFull = new HashMap<>();
        nameToNodes = new HashMap<>();
        for (Node node : nodes) {
            String name = node.name();
            if (name != null && !name.isEmpty()) {
                String cleanName = cleanString(name);
                trieSet.add(cleanName);
                cleanedToFull.put(cleanName, name);

                /* I think this part can replace by ~nameToNdes.computeIfAbsent(String, new List(node)).add(node)~ */
                if (nameToNodes.get(cleanName) == null) {
                    nameToNodes.put(cleanName, List.of(node));
                } else {
                    List<Node> newList = new ArrayList<>(nameToNodes.get(cleanName));
                    newList.add(node);
                    nameToNodes.put(cleanName, newList);
                    //nameToNodes.get(cleanName).add(node);
                }
            }
        }
    }


    /**
     * For Project Part II
     * Returns the vertex closest to the given longitude and latitude.
     * @param lon The target longitude.
     * @param lat The target latitude.
     * @return The id of the node in the graph closest to the target.
     */
    public long closest(double lon, double lat) {
        Map<Point, Node> pointNodeMap = new HashMap<>();
        List<Point> points = new ArrayList<>();
        for (Node node : nodes) {
            if (!neighbors(node.id()).isEmpty()) {
                Point p = new Point(node.lon(), node.lat());
                pointNodeMap.put(p, node);
                points.add(p);
            }
        }
        //PointSet pointSet = new WeirdPointSet(points);
        PointSet pointSet = new KDTree(points);
        Point p = pointSet.nearest(lon, lat);

        return pointNodeMap.get(p).id();
    }


    /**
     * For Project Part III (gold points)
     * In linear time, collect all the names of OSM locations that prefix-match the query string.
     * @param prefix Prefix string to be searched for. Could be any case, with our without
     *               punctuation.
     * @return A <code>List</code> of the full names of locations whose cleaned name matches the
     * cleaned <code>prefix</code>.
     */
    public List<String> getLocationsByPrefix(String prefix) {
        String cleanedName = cleanString(prefix);
        List<String> names = trieSet.keysWithPrefix(cleanedName);
        List<String> result = new ArrayList<>();

        for (String name : names) {
            result.add(cleanedToFull.get(name));
        }

        return result;
    }

    /**
     * For Project Part III (gold points)
     * Collect all locations that match a cleaned <code>locationName</code>, and return
     * information about each node that matches.
     * @param locationName A full name of a location searched for.
     * @return A list of locations whose cleaned name matches the
     * cleaned <code>locationName</code>, and each location is a map of parameters for the Json
     * response as specified: <br>
     * "lat" -> Number, The latitude of the node. <br>
     * "lon" -> Number, The longitude of the node. <br>
     * "name" -> String, The actual name of the node. <br>
     * "id" -> Number, The id of the node. <br>
     */
    public List<Map<String, Object>> getLocations(String locationName) {
        String cleanLocation = cleanString(locationName);
        List<Node> matchedNodes = nameToNodes.get(cleanLocation);
        List<Map<String, Object>> locations = new ArrayList<>();
        for (Node node : matchedNodes) {
            Map<String, Object> map = new HashMap<>();
            map.put("lat", node.lat());
            map.put("lon", node.lon());
            map.put("name", cleanedToFull.get(node.name()));
            map.put("id", node.id());
            locations.add(map);
        }
        return locations;
    }


    /**
     * Useful for Part III. Do not modify.
     * Helper to process strings into their "cleaned" form, ignoring punctuation and capitalization.
     * @param s Input string.
     * @return Cleaned string.
     */
    private static String cleanString(String s) {
        return s.replaceAll("[^a-zA-Z ]", "").toLowerCase();
    }

}
