/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author Adiel
 */
import static com.googlecode.javacv.cpp.opencv_contrib.*;
import com.googlecode.javacv.cpp.opencv_contrib.FaceRecognizer;
import com.googlecode.javacv.cpp.opencv_core;

import static com.googlecode.javacv.cpp.opencv_core.*;
import com.googlecode.javacv.cpp.opencv_core.IplImage;
import static com.googlecode.javacv.cpp.opencv_core.IplImage.createFrom;
import com.googlecode.javacv.cpp.opencv_core.MatVector;
import static com.googlecode.javacv.cpp.opencv_highgui.*;
import static com.googlecode.javacv.cpp.opencv_imgproc.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FilenameFilter;

import org.opencv.core.Mat;

public class FaceRecognition {

    public void checkFace(String imagePath, BufferedImage frame) {
        // Video Camera Frame       
        IplImage getVCFrame = new IplImage();
        getVCFrame = createFrom(frame);

        // Frame from Storage
        IplImage img;
        IplImage grayImg;
        int numberOfImages = 1;
        int label;

        MatVector images = new MatVector(numberOfImages);
        int[] labels = new int[numberOfImages];

        img = cvLoadImage(imagePath);
        label = 1;
        grayImg = IplImage.create(img.width(), img.height(), IPL_DEPTH_8U, 1);
        cvCvtColor(img, grayImg, CV_BGR2GRAY);

        images.put(0, img);
        labels[0] = label;

        IplImage GrayVCFrame = IplImage.create(getVCFrame.width(), getVCFrame.height(), IPL_DEPTH_8U, 1);


        FaceRecognizer fr = createFisherFaceRecognizer();
        //FaceRecognizer faceRecognizer = createEigenFaceRecognizer();
        // FaceRecognizer faceRecognizer = createLBPHFaceRecognizer();

        fr.train(images, labels);
        cvCvtColor(getVCFrame, GrayVCFrame, CV_BGR2GRAY);
    }
}
