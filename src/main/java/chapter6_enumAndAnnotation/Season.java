package chapter6_enumAndAnnotation;

import java.util.ArrayList;

/**
 * @author fatiaoyezi
 * @title:
 * @projectName effective-java-example
 * @description: 用enum代替int常量----第30条
 * @date 2019/7/12 14:55
 */
public enum Season {


    //春夏秋冬
    UN_DEFINITION(-1,"未知"),
    SPRING(1,"清爽"),
    SUMMER(2,"炎热"),
    AUTUMN(3,"凉爽"),
    WINTER(4,"寒冷");

    int status;
    String weather;

    Season(int status, String weather) {
        this.status = status;
        this.weather = weather;
    }


    /**
     * 根据状态得到Season类型
     * @param status
     * @return
     */
    public Season getSeason(int status) {
        for (Season season : values()) {
            if (season.status == status) {
                return season;
            }
        }
        return UN_DEFINITION;
    }



}
