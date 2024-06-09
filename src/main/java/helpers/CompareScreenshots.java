package helpers;

import ru.yandex.qatools.ashot.comparison.ImageDiff;
import ru.yandex.qatools.ashot.comparison.ImageDiffer;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class CompareScreenshots {

    public static void main(String[] args) throws IOException {
        screenComparator("", "");
    }

    public static boolean screenComparator(String screenA, String screenB) throws IOException {

        File actualImageFile = new File("screenshot/a.png");
        File expectedlImageFile = new File("screenshot/c.png");

        BufferedImage actualImage = ImageIO.read(actualImageFile);
        BufferedImage expectedlImage = ImageIO.read(expectedlImageFile);

        ImageDiffer imageDiffer = new ImageDiffer();
        ImageDiff diff = imageDiffer.makeDiff(actualImage, expectedlImage);

        if (diff.hasDiff()) {
            System.out.println("Images are different...");
            File diffImageFile = new File("screenshot/diff.png");
            ImageIO.write(diff.getMarkedImage(), "PNG", diffImageFile);
            return false;
        } else {
            System.out.println("Images are the same!");
        }
        return true;

    }

}
