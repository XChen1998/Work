import edu.princeton.cs.algs4.Bag;
import org.xml.sax.SAXException;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;
import java.util.HashMap;
import java.util.Set;

/**
 * Graph for storing all of the intersection (vertex) and road (edge) information.
 * Uses your GraphBuildingHandler to convert the XML files into a graph. Your
 * code must include the vertices, adjacent, distance, closest, lat, and lon
 * methods. You'll also need to include instance variables and methods for
 * modifying the graph (e.g. addNode and addEdge).
 *
 * @author Alan Yao, Josh Hug
 */
public class GraphDB {
    /**
     * Your instance variables for storing the graph. You should consider
     * creating helper classes, e.g. Node, Edge, etc.
     */
    private HashMap<Long, Node> nodes = new HashMap<>();
    private Bag<Edge> tempEdges = new Bag<>();
    private HashMap<Long, Bag<WeightedEdge>> adjacentList = new HashMap<>();

    private class Node {
        private long index;
        private double longitude;
        private double latitude;
        private String name;

        private Node(long index, double longitude, double latitude, String name) {
            this.index = index;
            this.longitude = longitude;
            this.latitude = latitude;
            this.name = name;
        }


        public long getIndex() {
            return index;
        }

        public double getLongitude() {
            return longitude;
        }

        public double getLatitude() {
            return latitude;
        }
    }

    private class Edge{
        private long originIndex;
        private long otherIndex;

        private Edge(long originIndex, long otherIndex){
            this.originIndex = originIndex;
            this.otherIndex = otherIndex;
        }
    }

    private class WeightedEdge {
        private Node origin;
        private Node other;
        private double weight;

        private WeightedEdge(Node origin, Node other) {
            this.origin = origin;
            this.other = other;
            this.weight = distance(origin.getIndex(), other.getIndex());
        }
    }

    /**
     * Example constructor shows how to create and start an XML parser.
     * You do not need to modify this constructor, but you're welcome to do so.
     *
     * @param dbPath Path to the XML file to be parsed.
     */

    public GraphDB(String dbPath) {
        try {
            File inputFile = new File(dbPath);
            FileInputStream inputStream = new FileInputStream(inputFile);
            // GZIPInputStream stream = new GZIPInputStream(inputStream);

            SAXParserFactory factory = SAXParserFactory.newInstance();
            SAXParser saxParser = factory.newSAXParser();
            GraphBuildingHandler gbh = new GraphBuildingHandler(this);
            saxParser.parse(inputStream, gbh);
        } catch (ParserConfigurationException | SAXException | IOException e) {
            e.printStackTrace();
        }
        addEdges();
        clean();
    }


    void addNode(long index, double longitude, double latitude, String name){
        nodes.put(index, new Node(index, longitude, latitude, name));
    }

    void removeNode(long index){
        nodes.remove(index);
    }

    void addEdge(long origin, long other){
        tempEdges.add(new Edge(origin, other));
    }

    void addEdges(){
        for (Edge e : tempEdges){
            adjacentList.put(e.originIndex, new Bag<>());
            adjacentList.put(e.otherIndex, new Bag<>());
        }
        for (Edge e : tempEdges){
            adjacentList.get(e.originIndex).add(new WeightedEdge(nodes.get(e.originIndex), nodes.get(e.otherIndex)));
            adjacentList.get(e.otherIndex).add(new WeightedEdge(nodes.get(e.otherIndex), nodes.get(e.originIndex)));
        }
        tempEdges = new Bag<>();
    }



    /**
     * Helper to process strings into their "cleaned" form, ignoring punctuation and capitalization.
     *
     * @param s Input string.
     * @return Cleaned string.
     */
    static String cleanString(String s) {
        return s.replaceAll("[^a-zA-Z ]", "").toLowerCase();
    }

    /**
     * Remove nodes with no connections from the graph.
     * While this does not guarantee that any two nodes in the remaining graph are connected,
     * we can reasonably assume this since typically roads are connected.
     */
    private void clean() {
        Set<Long> nodeSet = nodes.keySet();
        Bag<Long> invalidNodes = new Bag<>();
        for (Long nodeIndex : nodeSet) {
            if (!adjacentList.containsKey(nodeIndex)) {
                invalidNodes.add(nodeIndex);
            }
        }
        for (Long invalidNodeIndex : invalidNodes){
            removeNode(invalidNodeIndex);
        }
    }

    /**
     * Returns an iterable of all vertex IDs in the graph.
     *
     * @return An iterable of id's of all vertices in the graph.
     */
    Iterable<Long> vertices() {
        Set<Long> vertices = nodes.keySet();
        return vertices;
    }

    /**
     * Returns ids of all vertices adjacent to v.
     *
     * @param v The id of the vertex we are looking adjacent to.
     * @return An iterable of the ids of the neighbors of v.
     */
    Iterable<Long> adjacent(long v) {
        Bag<WeightedEdge> adjacentEdges = adjacentList.get(v);
        Bag<Long> adjacent = new Bag<>();
        for (WeightedEdge e : adjacentEdges) {
            adjacent.add(e.other.getIndex());
        }
        return adjacent;
    }

    /**
     * Returns the great-circle distance between vertices v and w in miles.
     * Assumes the lon/lat methods are implemented properly.
     * <a href="https://www.movable-type.co.uk/scripts/latlong.html">Source</a>.
     *
     * @param v The id of the first vertex.
     * @param w The id of the second vertex.
     * @return The great-circle distance between the two locations from the graph.
     */
    double distance(long v, long w) {
        return distance(lon(v), lat(v), lon(w), lat(w));
    }

    static double distance(double lonV, double latV, double lonW, double latW) {
        double phi1 = Math.toRadians(latV);
        double phi2 = Math.toRadians(latW);
        double dphi = Math.toRadians(latW - latV);
        double dlambda = Math.toRadians(lonW - lonV);

        double a = Math.sin(dphi / 2.0) * Math.sin(dphi / 2.0);
        a += Math.cos(phi1) * Math.cos(phi2) * Math.sin(dlambda / 2.0) * Math.sin(dlambda / 2.0);
        double c = 2 * Math.atan2(Math.sqrt(a), Math.sqrt(1 - a));
        return 3963 * c;
    }

    /**
     * Returns the initial bearing (angle) between vertices v and w in degrees.
     * The initial bearing is the angle that, if followed in a straight line
     * along a great-circle arc from the starting point, would take you to the
     * end point.
     * Assumes the lon/lat methods are implemented properly.
     * <a href="https://www.movable-type.co.uk/scripts/latlong.html">Source</a>.
     *
     * @param v The id of the first vertex.
     * @param w The id of the second vertex.
     * @return The initial bearing between the vertices.
     */
    double bearing(long v, long w) {
        return bearing(lon(v), lat(v), lon(w), lat(w));
    }

    static double bearing(double lonV, double latV, double lonW, double latW) {
        double phi1 = Math.toRadians(latV);
        double phi2 = Math.toRadians(latW);
        double lambda1 = Math.toRadians(lonV);
        double lambda2 = Math.toRadians(lonW);

        double y = Math.sin(lambda2 - lambda1) * Math.cos(phi2);
        double x = Math.cos(phi1) * Math.sin(phi2);
        x -= Math.sin(phi1) * Math.cos(phi2) * Math.cos(lambda2 - lambda1);
        return Math.toDegrees(Math.atan2(y, x));
    }

    /**
     * Returns the vertex closest to the given longitude and latitude.
     *
     * @param lon The target longitude.
     * @param lat The target latitude.
     * @return The id of the node in the graph closest to the target.
     */
    long closest(double lon, double lat) {
        double minDistance = Double.MAX_VALUE;
        Set<Long> nodeSet = nodes.keySet();
        long closestNodeIndex = 0;
        for (Long nodeIndex : nodeSet) {
            double curLon = nodes.get(nodeIndex).getLongitude();
            double curLat = nodes.get(nodeIndex).getLatitude();
            double curDistance = distance(curLon, curLat, lon, lat);
            if (curDistance < minDistance) {
                minDistance = curDistance;
                closestNodeIndex = nodeIndex;
            }
        }
        return closestNodeIndex;
    }

    /**
     * Gets the longitude of a vertex.
     *
     * @param v The id of the vertex.
     * @return The longitude of the vertex.
     */
    double lon(long v) {
        Node n = nodes.get(v);
        return n.getLongitude();
    }

    /**
     * Gets the latitude of a vertex.
     *
     * @param v The id of the vertex.
     * @return The latitude of the vertex.
     */
    double lat(long v) {
        Node n = nodes.get(v);
        return n.getLatitude();
    }
}
