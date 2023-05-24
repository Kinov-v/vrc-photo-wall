package tech.vrcat.vrc.photo.wall.entity;

import lombok.Data;

@Data
public class Score {
    private String score;

    private Integer num;

    private Integer total;

    private String rank_range;

    private String batch_name;

    private String controlscore;

    private String rank;

    private Integer heightRank;

    private Integer lowRank;
}
