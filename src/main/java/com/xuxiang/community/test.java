package com.xuxiang.community;

import java.util.ArrayList;
import java.util.List;

public class test {
    public static void main(String[] args) {
        //显示页数集合
        List<Integer> pages = new ArrayList<>();
        int page = 3;
        int tootlePage = 10;

        pages.add(page);
        int p = page;
        int b=page;
        for (int i = 0; i < 2; i++) {
            if (p + 1 <= tootlePage) {
                System.out.println("进来");
                pages.add(p + 1);
                p += 1;
                System.out.println(p);
            }
        }

        for (int i = 0; i < 2; i++) {
            if ( (b - 1 )< tootlePage&&(b-1)>0) {
                pages.add(0, b - 1);
                b -= 1;
            }
        }

        System.out.println(pages);
    }
}
