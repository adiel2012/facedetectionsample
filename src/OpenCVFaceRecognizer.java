/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author Adiel
 */
import com.googlecode.javacv.cpp.opencv_core;
import static com.googlecode.javacv.cpp.opencv_highgui.*;
import static com.googlecode.javacv.cpp.opencv_core.*;
import static com.googlecode.javacv.cpp.opencv_imgproc.*;
import static com.googlecode.javacv.cpp.opencv_contrib.*;
import java.io.File;
import java.io.FileFilter;
import java.io.FilenameFilter;
import java.util.ArrayList;

public class OpenCVFaceRecognizer {

    public static void main(String[] args) {
        String afolderapp = System.getProperty("user.dir");
        String trainingDir = afolderapp + "/att_faces";   // args[0];
        String urltest = trainingDir + "/s3/2.pgm";
        System.out.println(urltest);
        IplImage testImage = cvLoadImage(trainingDir + "/s3/2.pgm");
        
        File[] imageFiles = loadimages(trainingDir);  // root.listFiles(pngFilter);

        MatVector images = new MatVector(imageFiles.length);
        
        int[] labels = new int[imageFiles.length];
        
        int counter = 0;
        int label;
        
        IplImage img;
        IplImage grayImg;
        
        for (File image : imageFiles) {
            img = cvLoadImage(image.getAbsolutePath());
            
            String name = image.getName();
            label = Integer.parseInt(name.substring(0, name.indexOf(".")));
            
            grayImg = IplImage.create(img.width(), img.height(), IPL_DEPTH_8U, 1);
            
            cvCvtColor(img, grayImg, CV_BGR2GRAY);
            
            images.put(counter, grayImg);
            
            labels[counter] = label;
            
            counter++;
        }
        
        IplImage greyTestImage = IplImage.create(testImage.width(), testImage.height(), IPL_DEPTH_8U, 1);
        
        FaceRecognizer faceRecognizer = createFisherFaceRecognizer();
        // FaceRecognizer faceRecognizer = createEigenFaceRecognizer();
        // FaceRecognizer faceRecognizer = createLBPHFaceRecognizer()

        faceRecognizer.train(images, labels);
        
        cvCvtColor(testImage, greyTestImage, CV_BGR2GRAY);
        
        int predictedLabel = faceRecognizer.predict(greyTestImage);
        
        System.out.println("Predicted label: " + predictedLabel);
    }
    
    private static File[] loadimages(String trainingDir) {
        File root = new File(trainingDir);
        ArrayList<File> result = new ArrayList<>();
        
        File[] roots = root.listFiles(new FileFilter() {
            
            @Override
            public boolean accept(File pathname) {
                return pathname.isDirectory();
            }
        });
        for (File file : roots) {
            File[] ff = file.listFiles(new FilenameFilter() {
                public boolean accept(File dir, String name) {
                    return name.toLowerCase().endsWith(".pgm");
                }
            });
            for (File file1 : ff) {
                result.add(file1);
            }
        }
        
        FilenameFilter pngFilter = new FilenameFilter() {
            public boolean accept(File dir, String name) {
                return name.toLowerCase().endsWith(".png");
            }
        };        
        
        return result.toArray(new File[0]);
        
    }
}
