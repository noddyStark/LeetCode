package CompanyWise.Tesla;

public class RectangleArea {

    void main() {

        int ax1 = -3;
        int ay1 = 0;
        int ax2 = 3;
        int ay2 = 4;
        int bx1 = 0;
        int by1 = -1;
        int bx2 = 9;
        int by2 = 2;

        computeArea(ax1, ay1, ax2, ay2, bx1, by1, bx2, by2);
    }

    public int computeArea(int ax1, int ay1, int ax2, int ay2, int bx1, int by1, int bx2, int by2) {


        /*
FIRST RECTANGLE

        ## bottom left
        x coordinate = ax1,
        y coordinate = ay1

        ## top right
        x coordinate = ax2,
        y coordinate = ay2

SECOND RECTANGLE
        ## bottom left
        x coordinate = bx1,
        y coordinate = by1

        ## top right
        x coordinate = bx1,
        y coordinate = by2

        total covered area = area of rectangle A + area of rectangle B - overlapping area

        */

        int areaA = (ax2 - ax1) * (ay2 - ay1);
        int areaB = (bx2 - bx1) * (by2 - by1);

        int overlapWidth = Math.max(0, Math.min(ax2, bx2) - Math.max(ax1, bx1));

        int overlapHeight = Math.max(0, Math.min(ay2, by2) - Math.max(ay1, by1));

        int overlapArea = overlapWidth * overlapHeight;

        return areaA + areaB - overlapArea;
    }

}
