package com.example.spatial.sol;

import com.google.maps.internal.PolylineEncoding;
import org.locationtech.jts.geom.*;
import org.locationtech.jts.io.ParseException;
import org.locationtech.spatial4j.context.jts.JtsSpatialContext;
import org.locationtech.spatial4j.shape.Point;
import org.locationtech.spatial4j.shape.ShapeFactory;
import org.locationtech.spatial4j.shape.jts.JtsGeometry;

import java.io.IOException;
import java.util.List;
import java.util.stream.Collectors;

public class Solution7 {

    public static void validatePointOnPolygon() throws ParseException, IOException {

        String polylineEncodedData = "qxsaCysyqF{ARXzABJF\\rA`GkC\\uCX}BJiCDc@B}Cd@qAD_Cn@O\\Dp@Jt@NJlBI^@`Em@~@WhHwB~D_@zGcAtBe@lD{@`C{@dBw@|CeBbD}Bz@u@`AcAtBiCbBmChBkDt@mBd@{@Vk@X]@C@KFYh@kCNs@n@yEJw@ViBHu@DUdAsI`AgIFyBDaAFyH@gB@oF?sM?KDeGAkBRoAr@_B|@iApAy@tG{Dv@cApA_ApAo@dAUrAOfDTWfBuAQ}B[Gl@]zC|Ad@bB~@XsBBWtB^hA^`AJzD`@F@LDb@Ld@LvCzBVLvAt@DDtAr@nAn@^P\\PZPtBfAHBzDJhFzAbAXZHrGhBbBLfG?RA~G?j@AzAB`HD|@JbA\\dAr@j@r@@B`@`ANfADl@Ax@Kj@a@bAs@~@mAxBwArDg@xAc@hBy@bE]~CGl@C\\IxAEtAAnC?d@D`DJvB@X@J\\lIBf@TpF@THjAJxBJ`CFhAL|D@b@[B{LrAiO|Ac@DgDXcBT{BVs@No@T}@\\i@Vw@l@a@b@oA`BuBxCsEjFIJcCzAq@^aAh@kLlGaFnCYLk@TuA`@{Bb@aBPwJx@cJx@iFf@a@D@\\J|CLzGHtE@b@B~@@TFvCNdGD~BHxCDrBN`HDnBDzAl@bMj@jApAhATt@KZeAv@iEX}@FyD`@eC`@gCl@mEdBgEpBeLbGiEtCqCnBmEbDeNnMoCjCaGjGwB~@sEfCsAxAi@hA[bAMx@MxCAdDLtD|@hF~IxSjAzEBbLc@dMi@xNo@fP[~IKbF@fBNxDRjCb@bCh@|Bv@zCjApCdAzBdBjCpBlCfDjDnHjFhKlHn@b@pMlJbD~CrJfHhA`An@n@h@f@t@~@b@f@lApATRPLT]pBgDLUdEaH\\i@dAgBfGnEJJd@^tA_Clb@|YgArB";

        JtsSpatialContext jtsSpatialContext = JtsSpatialContext.GEO;
        ShapeFactory shapeFactory = jtsSpatialContext.getShapeFactory();

        List<Point> polylinePoints = PolylineEncoding.decode(polylineEncodedData)
                .stream()
                .map(latLng -> shapeFactory.pointXY(latLng.lng, latLng.lat))
                .collect(Collectors.toList());

        // Convert Spatial4j points to JTS Coordinate array
        Coordinate[] jtsCoords = new Coordinate[polylinePoints.size()];
        for (int i = 0; i < polylinePoints.size(); i++) {
            org.locationtech.spatial4j.shape.Point point1 = polylinePoints.get(i);
            jtsCoords[i] = new Coordinate(point1.getX(), point1.getY());
        }


        GeometryFactory jtsGeometryFactory = new GeometryFactory();
        LineString lineString = jtsGeometryFactory.createLineString(jtsCoords);

        // Convert JTS LineString to a JTS Geometry
        Geometry polylineGeometry = jtsGeometryFactory.createGeometry(lineString);

        // Buffer distance in meters
        double bufferDistance = 30.0;

        // Buffer the polyline
        Geometry bufferedPolyline = polylineGeometry.buffer(bufferDistance);

        // Convert buffered polyline to JtsGeometry
        JtsGeometry bufferedGeometry = new JtsGeometry(bufferedPolyline,  jtsSpatialContext, true, true);

        // Convert to LinearRing
        LinearRing shell = jtsGeometryFactory.createLinearRing(bufferedGeometry.getGeom().getCoordinates());

        // Create Polygon with shell and no holes
        Polygon polygon = new Polygon(shell, null, jtsGeometryFactory);

        double latitude = 40.40596;
        double longitude = 23.784960000000005;
        Coordinate pointCoordinate = new Coordinate(longitude, latitude);
        org.locationtech.jts.geom.Point point = new GeometryFactory().createPoint(pointCoordinate);

        // Check if the point is within the polygon
        boolean isPointInsidePolygon = polygon.contains(point);

        if (isPointInsidePolygon) {
            System.out.println("The point is inside the polygon.");
        } else {
            System.out.println("The point is outside the polygon.");
        }

        System.out.println(polygon);

    }
}
