package tech.vrcat.vrc.photo.wall;

import com.alibaba.fastjson2.JSON;
import com.alibaba.fastjson2.JSONObject;
import com.alibaba.fastjson2.JSONReader;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import tech.vrcat.vrc.photo.wall.entity.Score;

import java.io.*;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.util.Iterator;
import java.util.List;

@SpringBootTest
class VrcPhotoWallApplicationTests {

    @Test
    void contextLoads() {
    }

//    @Test
//    void jsonDo() {
//        String fileName = "lists.json";
//        File jsonFile = new File(fileName);
//        StringBuilder sb = new StringBuilder();
//
//        try {
//            Reader reader = new InputStreamReader(Files.newInputStream(jsonFile.toPath()), StandardCharsets.UTF_8);
//
//            try {
//
//                int ch;
//                while((ch = reader.read()) != -1) {
//                    sb.append((char)ch);
//                }
//            } catch (Throwable var12) {
//                try {
//                    reader.close();
//                } catch (Throwable var11) {
//                    var12.addSuppressed(var11);
//                }
//
//                throw var12;
//            }
//
//            reader.close();
//        } catch (IOException var13) {
//            var13.printStackTrace();
//        }
//
//        JSONObject json = JSON.parseObject(sb.toString());
//        json = (JSONObject)json.get("data");
//        String j = json.get("list").toString();
//        List<Score> list = JSON.parseArray(j, Score.class, new JSONReader.Feature[0]);
//        Iterator var7 = list.iterator();
//
//        while(var7.hasNext()) {
//            Score s = (Score)var7.next();
//            String rank = s.getRank_range();
//            String[] ranges = rank.split("-");
//            s.setHeightRank(Integer.valueOf(ranges[0]));
//            s.setLowRank(Integer.valueOf(ranges[1]));
//            PrintStream var10000 = System.out;
//            String var10001 = s.getScore();
//            var10000.println("insert into scored_segmentation (area, year, candidate_category, score, paragraph_headcount, accumulate_headcount, highest_ranking, lowest_ranking) VALUES ('山西', '2022', '理科', '" + var10001 + "', " + s.getNum() + ", " + s.getTotal() + ", " + s.getHeightRank() + ", " + s.getLowRank() + ");");
//        }
//
//    }
}
