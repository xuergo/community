package com.xuxiang.community.dto;

import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Data
public class PageinationDto {
    private List<QuestionDto> question;

    private boolean showPrevious;//上一页
    private boolean showFirstPage;//首页
    private boolean showNext;//下一页
    private boolean showEndPage;//尾页

    private Integer page;//页数
    private List<Integer> pages = new ArrayList<>();
    private Integer tootlePage;//总页数


    public void setPageination(Integer tootle, Integer page, Integer size) {


        if (tootle % size != 0) {
            tootlePage = tootle / size + 1;
        } else {
            tootlePage = tootle / size;
        }

        if (page < 1) {
            page = 1;
        }
        if (page > tootlePage) {
            page = tootlePage;
        }
        this.page = page;

        //显示页数集合
        pages.add(page);
        for (int i = 1; i <= 3; i++) {
            if (page - i > 0) {
                pages.add(0, page - i);
            }
            if (page + i <= tootlePage) {
                pages.add(page + i);
            }
        }

        //是否显示上一页下一页
        if (page == 1) {
            showPrevious = false;
        } else {
            showPrevious = true;
        }
        //下一页
        if (page == tootlePage) {
            showNext = false;
        } else {
            showNext = true;
        }
        //是否显示首页
        if (pages.contains(1)) {
            showFirstPage = false;
        } else {
            showFirstPage = true;
        }

        //是否显示尾页
        if (pages.contains(tootlePage)) {
            showEndPage = false;
        } else {
            showEndPage = true;
        }


    }
}
