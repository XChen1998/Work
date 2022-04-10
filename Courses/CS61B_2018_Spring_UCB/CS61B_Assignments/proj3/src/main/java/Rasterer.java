import edu.princeton.cs.algs4.Queue;
import edu.princeton.cs.algs4.Stack;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.Map;

/**
 * This class provides all code necessary to take a query box and produce
 * a query result. The getMapRaster method must return a Map containing all
 * seven of the required fields, otherwise the front end code will probably
 * not draw the output correctly.
 */
public class Rasterer {

    private final static double ulon = -122.2998046875;
    private final static double llon = -122.2119140625;
    private final static double ulat = 37.892195547244356;
    private final static double llat = 37.82280243352756;
    private final static double levelZeroLonDPP = Math.abs(ulon - llon) / 256;
    private static double raster_ul_lon;
    private static double raster_ul_lat;
    private static double raster_lr_lon;
    private static double raster_lr_lat;

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
     * "raster_lr_lon" : Number, the bounding lower right longitude of the rastered image. <br>
     * "raster_lr_lat" : Number, the bounding lower right latitude of the rastered image. <br>
     * "depth"         : Number, the depth of the nodes of the rastered image <br>
     * "query_success" : Boolean, whether the query was able to successfully complete; don't
     * forget to set this to true on success! <br>
     */
    public Map<String, Object> getMapRaster(Map<String, Double> params) {
        System.out.println(params);
        Map<String, Object> results = new HashMap<>();
        int degree = getDegree(params.get("ullon"), params.get("lrlon"), params.get("w"));
        String[][] render_grid = getPictureList(params.get("ullon"), params.get("lrlon"), params.get("ullat"), params.get("lrlat"), degree);
        results.put("render_grid", render_grid);
        results.put("raster_ul_lon", raster_ul_lon);
        results.put("raster_lr_lon", raster_lr_lon);
        results.put("raster_ul_lat", raster_ul_lat);
        results.put("raster_lr_lat", raster_lr_lat);
        results.put("depth", degree);
        results.put("query_success", true);
        return results;
    }

    private static int getDegree(double ullon, double lrlon, double width) {
        double xDist = Math.abs(ullon - lrlon);
        double lonDPP = xDist / width;
        int degree = 0;
        for (; degree <= 7; degree++) {
            if (levelZeroLonDPP / Math.pow(2, degree) < lonDPP) {
                break;
            }
        }
        return degree;
    }

    private static String[][] getPictureList(double ullon, double lrlon, double ullat, double lrlat, int degree) {
        Stack<Integer> lonList = new Stack<Integer>();
        Queue<Integer> latList = new Queue<Integer>();
        int maxPicIndex = (int) Math.pow(2, degree);
        for (int i = maxPicIndex - 1; i >= 0; i--) {
            double lon = index2lonCoordinate(i, degree);
            if (ullon <= lon && lon <= lrlon) {
                lonList.push(i);
            }
        }
        lonList.push(lonList.peek() - 1);
        for (int i = 0; i < maxPicIndex; i++) {
            double lat = index2latCoordinate(i, degree);
            if (ullat <= lat && lat >= lrlat) {
                latList.enqueue(i);
            }
        }
        latList.enqueue(latList.peek() + 1);
        String[][] pictureList = new String[lonList.size()][latList.size()];
        int curLatPos = 0;

        for (int latIndex : lonList) {
            int curLonPos = 0;
            for (int lonIndex : latList) {
                String curImageName = "d" + degree + "_x" + lonIndex + "_y" + latIndex + ".png";
                pictureList[curLatPos][curLonPos] = curImageName;
                curLonPos++;
            }
            curLatPos++;
        }

        raster_ul_lon = index2lonCoordinate(lonList.peek(), degree);
        raster_lr_lon = index2lonCoordinate(lonList.peek() - lonList.size() + 1, degree);
        raster_ul_lat = index2latCoordinate(latList.peek(), degree);
        raster_lr_lat = index2latCoordinate(latList.peek() - lonList.size() + 1, degree);
        return pictureList;

    }

    private static double index2lonCoordinate(double index, int degree) {
        int slicesNum = (int) Math.pow(2, degree);
        double lonPerPic = Math.abs(ulon - llon) / slicesNum;
        return ulon + lonPerPic * index;
    }

    private static double index2latCoordinate(double index, int degree) {
        int slicesNum = (int) Math.pow(2, degree);
        double latPerPic = Math.abs(ulat - llat) / slicesNum;
        return ulat - latPerPic * index;
    }

    public static void main(String[] args) {
        Rasterer test = new Rasterer();
        System.out.println(index2lonCoordinate(3, 4));
        System.out.println(index2latCoordinate(1, 4));
    }

}
