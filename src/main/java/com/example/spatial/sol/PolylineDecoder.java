package com.example.spatial.sol;

import org.locationtech.jts.geom.*;
import org.locationtech.jts.geom.impl.CoordinateArraySequence;

import java.io.IOException;
import java.io.StringReader;
import java.util.ArrayList;
import java.util.List;

/**
 * Decodes polyline encoded strings.
 *
 * @see <a href="https://developers.google.com/maps/documentation/utilities/polylinealgorithm">Polyline Algorithm</a>
 */
public class PolylineDecoder {

    /**
     * The maximum number of points in a decoded polyline.
     */
    private static final int MAX_POINTS = 100000;

    /**
     * The shift value for decoding the encoded values.
     */
    private static final int SHIFT = 5;

    /**
     * The mask value for decoding the encoded values.
     */
    private static final int MASK = (1 << SHIFT) - 1;

    /**
     * Decodes the polyline encoded string into a list of points.
     *
     * @param encoded The polyline encoded string.
     * @return The list of points.
     */
    public List<double[]> decode(String encoded) {
        if (encoded == null) {
            return null;
        }

        List<double[]> points = new ArrayList<>();
        int index = 0;
        while (index < encoded.length()) {
            int lat = decode(encoded, index);
            int lng = decode(encoded, index + 6);
            points.add(new double[]{lat / 100000.0, lng / 100000.0});

            if (points.size() >= MAX_POINTS) {
                break;
            }
        }

        return points;
    }

    /**
     * Decodes the encoded value at the specified index in the string.
     *
     * @param encoded The encoded string.
     * @param index The index of the encoded value to decode.
     * @return The decoded value.
     */
    private int decode(String encoded, int index) {
        int value = 0;
        int shift = 0;
        int digit;

        while ((digit = encoded.charAt(index++)) != ' ') {
            value |= (digit - '0') << shift;
            shift += SHIFT;
        }

        return value | (-1 << (shift - 1));
    }



    public static Geometry decodePolyline2(String encodedPolyline) throws IOException {
        List<double[]> points = decodePolyline(encodedPolyline);
        List<Coordinate> coordinates = new ArrayList<>();
        for (double[] point : points) {
            coordinates.add(new Coordinate(point[0], point[1]));
        }
        GeometryFactory factory = new GeometryFactory();
      //  CoordinateSequenceFactory coordinateSequenceFactory = factory.getCoordinateSequenceFactory();

        Coordinate[] coordinatesArray = coordinates.stream()
                .map(Coordinate::new)
                .toArray(Coordinate[]::new);

       // CoordinateArraySequence coordinateArraySequence = new CoordinateArraySequence(coordinatesArray);
        CoordinateSequence coordinateSequence = new CoordinateArraySequence(coordinatesArray);
        return new LineString(coordinateSequence, factory);
    }


    private static List<double[]> decodePolyline(String encodedPolyline) throws IOException {
        List<double[]> points = new ArrayList<>();
        StringReader reader = new StringReader(encodedPolyline);
        int lat = 0;
        int lon = 0;
        int index = 0;
        while (index < encodedPolyline.length()) {
            int b = reader.read();
            index += 1;
            // shift
            int shift = 0;
            // length
            int length = 1;
            while ((b & 0x20) > 0) {
                b = b << 1;
                length += 1;
                index += 1;
                b = reader.read();
            }
            // coordinate
            int coordinate = ((b & 0x1f) << shift) | (b >> (5 - shift));
            lat += coordinate * Math.pow(10, -length);
            shift = 5;
            if (length == 0) {
                points.add(new double[] {lat, lon});
                lat = 0;
                lon = 0;
            }
        }
        return points;
    }
}
