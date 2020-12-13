package com.wjh.controller;

import com.wjh.ExcelUtil;
import com.wjh.domain.SheetHeader;
import com.wjh.domain.User;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

/**
 * 测试用网页上传、下载excel
 */
@RestController
@RequestMapping("/test")
public class TestController {
    @RequestMapping("/uploadFile")
    public List<User> uploadFile(MultipartFile file) throws Exception {
        SheetHeader sheetHeader = new SheetHeader();
        sheetHeader.add("姓名", "name", 0);
        sheetHeader.add("性别", "gender", 1);
        sheetHeader.add("身高", "height", 2);

        List<User> beans = ExcelUtil.parseSheetToBeanList(
                file.getInputStream(),
                0,
                sheetHeader,
                User.class
        );
        System.out.println("ExcelUtil.parseSheetToBeanList" + beans);

        return beans;
    }
}
