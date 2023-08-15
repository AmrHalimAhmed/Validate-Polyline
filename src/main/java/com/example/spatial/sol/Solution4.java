package com.example.spatial.sol;

import com.google.maps.internal.PolylineEncoding;
import com.google.maps.model.LatLng;
import org.locationtech.jts.geom.Coordinate;
import org.locationtech.jts.geom.Geometry;
import org.locationtech.jts.geom.GeometryFactory;
import org.locationtech.jts.geom.LineString;
import org.locationtech.spatial4j.context.SpatialContext;
import org.locationtech.spatial4j.shape.Point;
import org.locationtech.spatial4j.shape.ShapeFactory;
import org.locationtech.spatial4j.shape.impl.PointImpl;

import java.util.List;
import java.util.stream.Collectors;

public class Solution4 {

   static public void validatePointOnPolyLine() {
        // second solution
        double latitude = 21.39789;
        double longitude = 39.78866;

        Point point = new PointImpl(latitude, longitude, SpatialContext.GEO);

        String polylineEncodedData = "qxsaCysyqF{ARXzABJF\\rA`GkC\\uCX}BJiCDc@B}Cd@qAD_Cn@O\\Dp@Jt@NJlBI^@`Em@~@WhHwB~D_@zGcAtBe@lD{@`C{@dBw@|CeBbD}Bz@u@`AcAtBiCbBmChBkDt@mBd@{@Vk@X]@C@KFYh@kCNs@n@yEJw@ViBHu@DUdAsI`AgIFyBDaAFyH@gB@oF?sM?KDeGAkBRoAr@_B|@iApAy@tG{Dv@cApA_ApAo@dAUrAOfDTWfBuAQ}B[Gl@]zC|Ad@bB~@XsBBWtB^hA^`AJzD`@F@LDb@Ld@LvCzBVLvAt@DDtAr@nAn@^P\\PZPtBfAHBzDJhFzAbAXZHrGhBbBLfG?RA~G?j@AzAB`HD|@JbA\\dAr@j@r@@B`@`ANfADl@Ax@Kj@a@bAs@~@NRs@bA}@pAg@~@mAxBwArDg@xAc@hBy@bE]~CGl@C\\IxAEtAAnC?d@D`DJvB@X@J\\lIBf@TpF@THjAJxBJ`CFhAL|D@b@[B{LrAiO|Ac@DgDXcBT{BVs@No@T}@\\i@Vw@l@a@b@oA`BuBxCsEjFIJcCzAq@^aAh@kLlGaFnCYLk@TuA`@{Bb@aBPwJx@cJx@iFf@a@D@\\J|CLzGHtE@b@B~@@TFvCNdGD~BHxCDrBN`HDnBDzAl@bMj@jApAhATt@KZeAv@iEX}@FyD`@eC`@gCl@mEdBgEpBeLbGiEtCqCnBmEbDeNnMoCjCaGjGwB~@sEfCsAxAi@hA[bAMx@MxCAdDLtD|@hF~IxSjAzEBbLc@dMi@xNo@fP[~IKbF@fBNxDRjCb@bCh@|Bv@zCjApCdAzBdBjCpBlCfDjDnHjFhKlHn@b@pMlJbD~CrJfHhA`An@n@h@f@t@~@b@f@lApATRPLT]pBgDLUdEaH\\i@dAgBfGnEJJd@^tA_Clb@|YgArB";

        SpatialContext spatialContext = SpatialContext.GEO;
//        ShapeFactory shapeFactory = spatialContext.getShapeFactory();


       String[] coordinates = polylineEncodedData.split("[^0-9.-]+");
       Coordinate[] coords = new Coordinate[coordinates.length / 2];
       for (int i = 0; i < coordinates.length; i += 2) {
           double lon = Double.parseDouble(coordinates[i + 1]);
           double lat = Double.parseDouble(coordinates[i]);
           coords[i / 2] = new Coordinate(lon, lat);
       }

       // Create LineString
       GeometryFactory geometryFactory = new GeometryFactory();
       LineString lineString = geometryFactory.createLineString(coords);

        // Convert Spatial4j point to JTS Point
        Geometry jtsPoint = geometryFactory.createPoint(new Coordinate(point.getX(), point.getY()));

        // Check if the point exists on the LineString
        boolean pointExistsOnPolyline = lineString.relate(jtsPoint).isIntersects();


        System.out.println("Point exists on polyline: " + pointExistsOnPolyline);
        // second solution
    }

//    public static boolean isLocationOnPath(LatLng point, List<LatLng> polyline, boolean geodesic, double tolerance) {
//        return PolyUtil.isLocationOnPath(point, polyline, geodesic, tolerance);
//    }
}
