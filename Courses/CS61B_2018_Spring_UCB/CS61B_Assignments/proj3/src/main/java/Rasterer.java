import java.util.ArrayDeque;
import java.util.Deque;
import java.util.HashMap;
import java.util.Map;

/**
 * This class provides all code necessary to take a query box and produce
 * a query result. The getMapRaster method must return a Map containing all
 * seven of the required fields, otherwise the front end code will probably
 * not draw the output correctly.
 */
public class Rasterer {

    private static final double ULON = -122.2998046875;
    private static final double LLON = -122.2119140625;
    private static final double ULAT = 37.892195547244356;
    private static final double LLAT = 37.82280243352756;
    private static final double LEVELZEROLONDPP = Math.abs(ULON - LLON) / 256;
    private static double rasterUlLon;
    private static double rasterUlLat;
    private static double rasterLrLon;
    private static double rasterLrLat;

    public Rasterer() {
    }

    /**
     * Takes a user query and finds the grid of images that best matches the query. These
     * images will be combined into one big image (rastered) by the front end. <br>
     * <p>
     * The grid of images must obey the following properties, where image in the
     * grid is referred to as a "tile".
     * <ul>
     *     <li>The tiles collected must cover the most longitudinal distance per pixel
     *     (LonDPP) possible, while still covering less than or equal to the amount of
     *     longitudinal distance per pixel in the query box for the user viewport size. </li>
     *     <li>Contains all tiles that intersect the query bounding box that fulfill the
     *     above condition.</li>
     *     <li>The tiles must be arranged in-order to reconstruct the full image.</li>
     * </ul>
     *
     * @param params Map of the HTTP GET request's query parameters - the query box and
     *               the user viewport width and height.
     * @return A map of results for the front end as specified: <br>
     * "render_grid"   : String[][], the files to display. <br>
     * "raster_ul_lon" : Number, the bounding upper left longitude of the rastered image. <br>
     * "raster_ul_lat" : Number, the bounding upper left latitude of the rastered image. <br>
     * "rasterLrLon" : Number, the bounding lower right longitude of the rastered image. <br>
     * "raster_lr_lat" : Number, the bounding lower right latitude of the rastered image. <br>
     * "depth"         : Number, the depth of the nodes of the rastered image <br>
     * "query_success" : Boolean, whether the query was able to successfully complete; don't
     * forget to set this to true on success! <br>
     */
    public Map<String, Object> getMapRaster(Map<String, Double> params) {
        Map<String, Object> results = new HashMap<>();
        System.out.println(params);
        if (checkOutline(params.get("ullon"), params.get("lrlat"), params.get("lrlon"), params.get("ullat"))) {
            int degree = getDegree(params.get("ullon"), params.get("lrlon"), params.get("w"));
            String[][] renderGrid = getPictureList(params.get("ullon"), params.get("lrlon"), params.get("ullat"), params.get("lrlat"), degree);
            results.put("render_grid", renderGrid);
            results.put("raster_ul_lon", rasterUlLon);
            results.put("raster_lr_lon", rasterLrLon);
            results.put("raster_ul_lat", rasterUlLat);
            results.put("raster_lr_lat", rasterLrLat);
            results.put("depth", degree);
            results.put("query_success", true);
        } else {
            String[][] renderGrid = {{"d0_x0_y0.png"}};
            results.put("render_grid", renderGrid);
            results.put("raster_ul_lon", ULON);
            results.put("raster_lr_lon", LLON);
            results.put("raster_ul_lat", ULAT);
            results.put("raster_lr_lat", LLAT);
            results.put("depth", 0);
            results.put("query_success", false);
        }
        for (String[] ss : (String[][]) results.get("render_grid")){
            for (String s: ss){
                System.out.println(s);
            }

        }
        System.out.println(results);
        return results;
    }

    private static boolean checkOutline(double a, double b, double c, double d) {
        if (a < ULON && b < LLAT && c > LLON && d > ULAT) {
            return false;
        }
        return true;
    }

    private static int getDegree(double ullon, double lrlon, double width) {
        double xDist = Math.abs(ullon - lrlon);
        double lonDPP = xDist / width;
        int degree = 0;
        for (; degree < 7; degree++) {
            if (LEVELZEROLONDPP / Math.pow(2, degree) < lonDPP) {
                break;
            }
        }
        return degree;
    }

    private static String[][] getPictureList(double ullon, double lrlon, double ullat, double lrlat, int degree) {
        boolean lonFlag = true;
        boolean latFlag = true;
        Deque<Integer> lonList = new ArrayDeque<>();
        Deque<Integer> latList = new ArrayDeque<>();
        int maxPicIndex = (int) Math.pow(2, degree);
        for (int i = 0; i < maxPicIndex; i++) {
            double lon = index2lonCoordinate(i, degree);
            if (ullon < lon && lon < lrlon) {
                lonList.add(i);
            }
            if (i + 1 < maxPicIndex && ullon >= lon && lrlon <= index2lonCoordinate(i + 1, degree)) {
                lonList.add(i);
                lonFlag = false;
            }
        }
        if (lonFlag && !lonList.contains(0)) {
            if (lonList.peek() != null) {
                lonList.addFirst(lonList.peek() - 1);
            } else {
                lonList.addFirst(0);
            }
        }
        for (int i = 0; i < maxPicIndex; i++) {
            double lat = index2latCoordinate(i, degree);
            if (ullat >= lat && lat >= lrlat) {
                latList.add(i);
            }
            if (i + 1 < maxPicIndex && ullat <= lat && lrlat >= index2latCoordinate(i + 1, degree)) {
                latList.add(i);
                latFlag = false;
            }
        }
        if (latFlag && !lonList.contains(maxPicIndex)) {
            if (latList.peek() != null) {
                latList.push(latList.peek() - 1);
            } else {
                latList.addFirst(0);
            }
        }
        String[][] pictureList = new String[latList.size()][lonList.size()];
        int curLatPos = 0;

        for (int latIndex : latList) {
            int curLonPos = 0;
            for (int lonIndex : lonList) {
                String curImageName = "d" + degree + "_x" + lonIndex + "_y" + latIndex + ".png";
                pictureList[curLatPos][curLonPos] = curImageName;
                curLonPos++;
            }
            curLatPos++;
        }

        rasterUlLon = index2lonCoordinate(lonList.peek(), degree);
        rasterLrLon = rasterUlLon + lonList.size() * Math.abs(ULON - LLON) / (int) Math.pow(2, degree);
        rasterUlLat = index2latCoordinate(latList.peek(), degree);
        rasterLrLat = rasterUlLat - latList.size() * Math.abs(ULAT - LLAT) / (int) Math.pow(2, degree);
        return pictureList;

    }

    private static double index2lonCoordinate(double index, int degree) {
        int slicesNum = (int) Math.pow(2, degree);
        double lonPerPic = Math.abs(ULON - LLON) / slicesNum;
        return ULON + lonPerPic * index;
    }

    private static double index2latCoordinate(double index, int degree) {
        int slicesNum = (int) Math.pow(2, degree);
        double latPerPic = Math.abs(ULAT - LLAT) / slicesNum;
        return ULAT - latPerPic * index;
    }

    public static void main(String[] args) {
        Rasterer test = new Rasterer();
        System.out.println(index2lonCoordinate(96, 7));
        System.out.println(index2latCoordinate(85, 7));
    }

}
