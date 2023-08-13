package com.example.spatial.sol;



import java.util.List;

public class Solution5 {

//    public static boolean isPointOnLine(List<LatLng> polylinePoints, LatLng pointToCheck) {
//        Geodesic geodesic = Geodesic.WGS84; // WGS84 ellipsoid
//
//        LatLng prevPoint = polylinePoints.get(0);
//        GeodesicLine line = geodesic.InverseLine(prevPoint.lat, prevPoint.lng, pointToCheck.lat, pointToCheck.lng);
//
//        for (int i = 1; i < polylinePoints.size(); i++) {
//            LatLng nextPoint = polylinePoints.get(i);
//            GeodesicData data = line.Direct(nextPoint.lat, nextPoint.lng);
//            double distance = data.s12; // Distance in meters
//
//            if (distance < toleranceInMeters) {
//                return true; // Point is close to the geodesic line
//            }
//        }
//
//        return false; // Point is not on the geodesic line
//    }

//    public static class Solution6 {
//        public static boolean isLocationOnPath(LatLng point, List<LatLng> polyline, boolean geodesic, double tolerance) {
//            return PolyUtil.isLocationOnPath(point, polyline, geodesic, tolerance);
//        }
//    }
//
//    }
}


