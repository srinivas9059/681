package umbcs681.hw03;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class ImagesTest {
    private Image testImage;

    @BeforeEach
    void setUp() {
        testImage = new Image(3, 3);

        testImage.setColor(0, 0, new Color(255, 0, 0));
        testImage.setColor(1, 1, new Color(0, 255, 0));
        testImage.setColor(2, 2, new Color(0, 0, 255));
    }

    @Test
    void verifyGrayScaleAdjuster() {
        Image resultImage = Images.transform(testImage, (Color color) -> {
            int r = color.getRedScale();
            int g = color.getGreenScale();
            int b = color.getBlueScale();

            int avg = (r + g + b) / 3;
            return new Color(avg, avg, avg);
        });

        Color grayFromRed = resultImage.getColor(0, 0);
        assertEquals(85, grayFromRed.getRedScale());
        assertEquals(85, grayFromRed.getGreenScale());
        assertEquals(85, grayFromRed.getBlueScale());

        Color grayFromGreen = resultImage.getColor(1, 1);
        assertEquals(85, grayFromGreen.getRedScale());
        assertEquals(85, grayFromGreen.getGreenScale());
        assertEquals(85, grayFromGreen.getBlueScale());

        Color grayFromBlue = resultImage.getColor(2, 2);
        assertEquals(85, grayFromBlue.getRedScale());
        assertEquals(85, grayFromBlue.getGreenScale());
        assertEquals(85, grayFromBlue.getBlueScale());

        Color grayFromBlack = resultImage.getColor(1, 0);
        assertEquals(0, grayFromBlack.getRedScale());
        assertEquals(0, grayFromBlack.getGreenScale());
        assertEquals(0, grayFromBlack.getBlueScale());
    }

    @Test
    void verifyBinarizationAdjuster() {
        final int THRESHOLD = 125;
        Image resultImage = Images.transform(testImage, (Color color) -> {
            int r = color.getRedScale();
            int g = color.getGreenScale();
            int b = color.getBlueScale();
            int avg = (r + g + b) / 3;

            int value = avg <= THRESHOLD ? 0 : 255;
            return new Color(value, value, value);
        });

        Color binaryFromRed = resultImage.getColor(0, 0);
        assertEquals(0, binaryFromRed.getRedScale());
        assertEquals(0, binaryFromRed.getGreenScale());
        assertEquals(0, binaryFromRed.getBlueScale());

        Color binaryFromGreen = resultImage.getColor(1, 1);
        assertEquals(0, binaryFromGreen.getRedScale());
        assertEquals(0, binaryFromGreen.getGreenScale());
        assertEquals(0, binaryFromGreen.getBlueScale());

        Color binaryFromBlue = resultImage.getColor(2, 2);
        assertEquals(0, binaryFromBlue.getRedScale());
        assertEquals(0, binaryFromBlue.getGreenScale());
        assertEquals(0, binaryFromBlue.getBlueScale());

        Color binaryFromWhite = resultImage.getColor(0, 2);
        assertEquals(0, binaryFromWhite.getRedScale());
        assertEquals(0, binaryFromWhite.getGreenScale());
        assertEquals(0, binaryFromWhite.getBlueScale());
    }
}