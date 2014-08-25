/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package opencv;

import static com.googlecode.javacv.cpp.opencv_core.*;
import static com.googlecode.javacv.cpp.opencv_highgui.*;
import com.googlecode.javacv.CanvasFrame;

/**
 *
 * @author Adiel
 */
public class FaceRecogsample {

//static Mat norm_0_255(InputArray _src) {
//    org.opencv.core.
//    Mat src = _src.getMat();
//    // Create and return normalized image:
//    Mat dst;
//    switch(src.channels()) {
//    case 1:
//        cv::normalize(_src, dst, 0, 255, NORM_MINMAX, CV_8UC1);
//        break;
//    case 3:
//        cv::normalize(_src, dst, 0, 255, NORM_MINMAX, CV_8UC3);
//        break;
//    default:
//        src.copyTo(dst);
//        break;
//    }
//    return dst;
//}
    public static void main(String[] argv) {
    // Read an image.
        //Load image img1 as IplImage
        final IplImage image = cvLoadImage("img1.png");
//create canvas frame named 'Demo'
        final CanvasFrame canvas = new CanvasFrame("Demo");
//Show image in canvas frame
        canvas.showImage(image);
//This will close canvas frame on exit
        canvas.setDefaultCloseOperation(javax.swing.JFrame.EXIT_ON_CLOSE);

    }

}
