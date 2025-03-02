package umbcs681.hw03;

import java.util.function.Function;
import java.util.stream.IntStream;

public class Images {
    public static Image transform(Image image, Function<Color, Color> adjuster) {
        Image adjusted = new Image(image.getHeight(), image.getWidth());

        // Use IntStream to iterate through rows
        IntStream.range(0, image.getHeight()).forEach(y -> {
            // Use nested IntStream to iterate through columns for each row
            IntStream.range(0, image.getWidth()).forEach(x -> {
                Color originalColor = image.getColor(x, y);
                Color adjustedColor = adjuster.apply(originalColor);
                adjusted.setColor(x, y, adjustedColor);
            });
        });

        return adjusted;
    }
}
