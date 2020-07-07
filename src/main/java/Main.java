import com.luciad.imageio.webp.WebPReadParam;

import javax.imageio.ImageIO;
import javax.imageio.ImageReader;
import javax.imageio.stream.MemoryCacheImageInputStream;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Paths;

/**
 * Created by zabozhan on 7/7/20.
 */
public class Main {

    public static void main(String[] args) throws IOException {
        String path = "path";
        String name = "filename.jpg";
        String u = "url";
        URL url = new URL(u);
        HttpURLConnection con = (HttpURLConnection) url.openConnection();
        con.setRequestProperty("Accept", "text/html,application/xhtml+xml,application/xml;q=0.9,image/webp,image/apng,*/*;q=0.8,application/signed-exchange;v=b3;q=0.9");
        con.setRequestProperty("User-Agent", "Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/83.0.4103.116 Safari/537.36");

        InputStream is = con.getInputStream();
        //Files.copy(is, Paths.get("/home/zabozhan/Pictures/file.webp"));

        ImageReader reader = ImageIO.getImageReadersByMIMEType("image/webp").next();

        WebPReadParam readParam = new WebPReadParam();
        readParam.setBypassFiltering(true);

        reader.setInput(new MemoryCacheImageInputStream(is));

        BufferedImage image = reader.read(0,readParam);
        ImageIO.write(image,"jpg",new File(path + name));
    }
}
