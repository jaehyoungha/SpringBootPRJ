package kopo.poly.controller;

import kopo.poly.util.CmmUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import javax.servlet.http.HttpServletRequest;

@Slf4j
@Controller
public class NoticeController {

    @GetMapping(value = "index")
    public String indexPage() throws Exception {
        log.info(this.getClass().getName() + " .indexPage Start!!");
        log.info(this.getClass().getName() + " .indexPage End!!");

        return "index";
    }

    @GetMapping(value = "noticeInfo")
    public String noticeInfo() throws Exception {
        log.info(this.getClass().getName() + " .noticeInfo Start !!");
        log.info(this.getClass().getName() + " .noticeInfo End !!");

        return "form";
    }
    @PostMapping(value = "getNoticeData")
    public String getNoticeData (HttpServletRequest request, Model model) throws Exception {
        log.info(this.getClass().getName() + " .getNoticeData Start !!");
        String title = CmmUtil.nvl(request.getParameter("title"));
        String nickname = CmmUtil.nvl(request.getParameter("nickname"));
        String contents = CmmUtil.nvl(request.getParameter("contents"));

        log.info("title : " + title);
        log.info("nickname : " + nickname);
        log.info("content : " + contents);

        model.addAttribute("title", title);
        model.addAttribute("nickname", nickname);
        model.addAttribute("contents", contents);

        log.info(this.getClass().getName() + " .getNoticeData End !!");
        return "getNoticeData";
    }
}