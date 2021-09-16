package com.nekromant.finance.controller;

import com.nekromant.finance.service.ActualStatPhotoHolderService;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.*;

@RestController
public class StatRestController {

    @Autowired
    private ActualStatPhotoHolderService actualStatPhotoHolderService;

    private static final List<String> colors = new ArrayList<>();

    static {
        colors.add("rgba(255, 8, 8, 1)");
        colors.add("rgba(255, 127, 8, 1)");
        colors.add("rgba(255, 247, 8, 1)");
        colors.add("rgba(103, 255, 8, 1)");
        colors.add("rgba(8, 255, 234, 1)");
        colors.add("rgba(16, 8, 255, 1)");
        colors.add("rgba(119, 8, 255, 1)");
        colors.add("rgba(230, 8, 255, 1)");
        colors.add("rgba(230, 8, 255, 1)");
        colors.add("rgba(255, 8, 144, 1)");
        colors.add("rgba(0, 0, 0, 1)");
        colors.add("rgba(37, 44, 69, 1)");
    }


    @PostMapping("/updatePerDayPhoto")
    public void setPerDayGraphEncodedPhoto(@RequestBody PhotoData photoData) {
        actualStatPhotoHolderService.setEncodedPerDayGraph(photoData.encodedPhoto.replaceFirst("^.*;base64,", ""));
    }


    @Data
    @Builder
    private static class Stat {
        private List<String> labels;
        private List<UserStat> userStats;
    }

    @Data
    @Builder
    private static class UserStat {
        private String label;
        private List<Integer> data;
        private String borderColor;
        private String backgroundColor;
        private Integer borderWidth;

    }

    @Data
    @AllArgsConstructor
    @NoArgsConstructor
    private static class PhotoData {
        private String encodedPhoto;
    }
}
