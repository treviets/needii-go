package util;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.web.multipart.MultipartFile;

import com.github.slugify.Slugify;

public class UtilsMedia {
    public static Map<String, String> uploadImage(MultipartFile file, String webappRoot){
        try {
            File folder = new File(webappRoot);
            Map<String, String> result = new HashMap<>();

            if(!folder.exists()) {
                folder.mkdirs();
            }
            // Get the file and save it somewhere
            byte[] bytes = file.getBytes();
            Slugify slg = new Slugify();
            Date date = new Date();
            List<String> ext = Arrays.asList(file.getOriginalFilename().split("\\.(?=[^\\.]+$)"));
            String fileName = date.getTime()+ "-" + slg.slugify(ext.get(0)) + "." + ext.get(1);
            Path path = Paths.get(webappRoot + fileName);
            Files.write(path, bytes);
            result.put("fileName", fileName);
            return result;
        } catch (IOException e) {
            e.printStackTrace();
            return new HashMap<>();
        }

    }
    public static String showImage(String image){
        File f = new File(image);
        if(f.exists() && !f.isDirectory()) {
            return image;
        }
        return Constants.RESOURCE_DOMAIN + image;
        //return "/static/images/noimage.png?" + Utils.createRandomString(3);
    }
}
