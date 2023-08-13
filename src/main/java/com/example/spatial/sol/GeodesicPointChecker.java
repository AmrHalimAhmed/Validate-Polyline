//package com.example.spatial.sol;
//
//import org.locationtech.proj4j.Geodesic;
//import org.locationtech.proj4j.GeodesicLine;
//import org.locationtech.proj4j.GeodesicPoint;
//
//public class GeodesicPointChecker {
//
//    public static boolean isPointOnPolyline(double latitude, double longitude, double[] polyline) {
//        Geodesic geodesic = new Geodesic();
//        GeodesicPoint point = new GeodesicPoint(latitude, longitude);
//
//        for (int i = 0; i < polyline.length - 1; i += 2) {
//            GeodesicLine line = geodesic.createLine(polyline[i], polyline[i + 1]);
//            if (line.contains(point)) {
//                return true;
//            }
//        }
//
//        return false;
//    }
//
//
//}