package com.example.spatial.sol;

import com.google.maps.internal.PolylineEncoding;
import com.google.maps.model.LatLng;
import lombok.extern.slf4j.Slf4j;

import java.util.List;

@Slf4j
public class Solution1 {

    static public void validatePointOnPolyLine() {

        double latitude = 21.39789;
        double longitude = 39.78866;

        String polylineEncodedData = "qxsaCysyqF{ARXzABJF\\rA`GkC\\uCX}BJiCDc@B}Cd@qAD_Cn@O\\Dp@Jt@NJlBI^@`Em@~@WhHwB~D_@zGcAtBe@lD{@`C{@dBw@|CeBbD}Bz@u@`AcAtBiCbBmChBkDt@mBd@{@Vk@X]@C@KFYh@kCNs@n@yEJw@ViBHu@DUdAsI`AgIFyBDaAFyH@gB@oF?sM?KDeGAkBRoAr@_B|@iApAy@tG{Dv@cApA_ApAo@dAUrAOfDTWfBuAQ}B[Gl@]zC|Ad@bB~@XsBBWtB^hA^`AJzD`@F@LDb@Ld@LvCzBVLvAt@DDtAr@nAn@^P\\PZPtBfAHBzDJhFzAbAXZHrGhBbBLfG?RA~G?j@AzAB`HD|@JbA\\dAr@j@r@@B`@`ANfADl@Ax@Kj@a@bAs@~@NRs@bA}@pAg@~@mAxBwArDg@xAc@hBy@bE]~CGl@C\\IxAEtAAnC?d@D`DJvB@X@J\\lIBf@TpF@THjAJxBJ`CFhAL|D@b@[B{LrAiO|Ac@DgDXcBT{BVs@No@T}@\\i@Vw@l@a@b@oA`BuBxCsEjFIJcCzAq@^aAh@kLlGaFnCYLk@TuA`@{Bb@aBPwJx@cJx@iFf@a@D@\\J|CLzGHtE@b@B~@@TFvCNdGD~BHxCDrBN`HDnBDzAl@bMj@jApAhATt@KZeAv@iEX}@FyD`@eC`@gCl@mEdBgEpBeLbGiEtCqCnBmEbDeNnMoCjCaGjGwB~@sEfCsAxAi@hA[bAMx@MxCAdDLtD|@hF~IxSjAzEBbLc@dMi@xNo@fP[~IKbF@fBNxDRjCb@bCh@|Bv@zCjApCdAzBdBjCpBlCfDjDnHjFhKlHn@b@pMlJbD~CrJfHhA`An@n@h@f@t@~@b@f@lApATRPLT]pBgDLUdEaH\\i@dAgBfGnEJJd@^tA_Clb@|YgArB";

        List<LatLng> polylinePoints = PolylineEncoding.decode(polylineEncodedData);

        LatLng pointToCheck = new LatLng(latitude, longitude);

        double marginLat = 0.001;
        double marginLon = 0.001;

        boolean pointExistsInPolyline = containsLatLngWithMargins(polylinePoints, pointToCheck, marginLat, marginLon);

        log.info("Point exists in the polyline: " + pointExistsInPolyline);
    }
//    k v
//    1 2 3 4 5 6

    static public boolean containsLatLngWithMargins(List<LatLng> polyline, LatLng point, double marginLat, double marginLon) {
        for (LatLng polylinePoint : polyline) {
            if (Math.abs(polylinePoint.lat - point.lat) <= marginLat &&
                    Math.abs(polylinePoint.lng - point.lng) <= marginLon) {
                return true;
            }
        }
        return false;
    }
}
