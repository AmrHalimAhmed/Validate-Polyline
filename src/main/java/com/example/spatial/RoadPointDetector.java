//package com.example.spatial;
//
//import com.spatial4j.core.context.SpatialContext;
//import com.spatial4j.core.shape.Point;
//
//import java.util.ArrayList;
//import java.util.List;
//
//public class RoadPointDetector {
//
//    // Create a SpatialContext (should be shared, thread-safe)
//    SpatialContext spatialContext = SpatialContext.GEO;
//
//    // Simulate the old road as a list of points (latitude and longitude coordinates)
//    ArrayList<Point> oldRoadPoints = new ArrayList<>();
//        oldRoadPoints.add(spatialContext.makePoint(-74.005974, 40.712776)); // New York City
//        oldRoadPoints.add(spatialContext.makePoint(-118.243683, 34.052235)); // Los Angeles
//    // Add more points to represent the old road...
//
//    // Simulate the new points you want to check
//    Point newPoint1 = spatialContext.makePoint(-74.005974, 40.712776); // New York City (existing point)
//    Point newPoint2 = spatialContext.makePoint(-87.629798, 41.878114); // Chicago (non-existing point)
//
//    // Check if the new points exist in the old road
//    boolean existsInOldRoad1 = oldRoadPoints.contains(newPoint1);
//    boolean existsInOldRoad2 = oldRoadPoints.contains(newPoint2);
//
//    // Output the results
//        System.out.println("Point 1 exists in the old road: " + existsInOldRoad1);
//        System.out.println("Point 2 exists in the old road: " + existsInOldRoad2);
//}
