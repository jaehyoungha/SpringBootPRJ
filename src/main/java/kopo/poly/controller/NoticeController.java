package kopo.poly.controller;

import kopo.poly.dto.NoticeDTO;
import kopo.poly.service.INoticeService;
import kopo.poly.service.impl.NoticeService;
import kopo.poly.util.CmmUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.List;

@Slf4j
@Controller
public class NoticeController {
    @Resource(name = "NoticeService")
    private INoticeService noticeService;

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
    @GetMapping(value = "getNoticeData")
    public String getNoticeData (HttpServletRequest request, Model model) throws Exception {
        log.info(this.getClass().getName() + " .getNoticeData Start !!");
        String title = CmmUtil.nvl(request.getParameter("title"));
        String reg_id = CmmUtil.nvl(request.getParameter("reg_id"));
        String contents = CmmUtil.nvl(request.getParameter("contents"));

        log.info("title : " + title);
        log.info("reg_id : " + reg_id);
        log.info("content : " + contents);

        model.addAttribute("title", title);
        model.addAttribute("reg_id", reg_id);
        model.addAttribute("contents", contents);

        log.info(this.getClass().getName() + " .getNoticeData End !!");
        return "getNoticeData";
    }
    @RequestMapping(value = "getInsertNotice")
    public String getInsertNotice (HttpServletRequest request, Model model) throws Exception {
        log.info(this.getClass().getName() + " .getNoticeData Start !!");
        String reg_id = CmmUtil.nvl(request.getParameter("reg_id"));
        String title = CmmUtil.nvl(request.getParameter("title"));
        String contents = CmmUtil.nvl(request.getParameter("contents"));

        log.info("reg_id : " + reg_id);
        log.info("title : " + title);
        log.info("content : " + contents);

        NoticeDTO pDTO = new NoticeDTO();
        pDTO.setTitle(title);
        pDTO.setContents(contents);
        pDTO.setReg_id(reg_id); // 서비스에 편하게 담으려고 만듬

        int res = noticeService.InsertNoticeInfo(pDTO); // 객체. <- .은 접근자 이다. noticeService 안에 있는 InsertNoticeInfo 를 사용할수 있다.
        //저장되면 1의 값을 안되면 0의 값을 가져옴
        String msg;
        String url = "/getNoticeList";

        if (res > 0) {
            msg = "등록에 성공하셨습니다.";
        } else {
            msg = "등록에 실패하셨습니다.";
        }
        model.addAttribute("msg", msg);
        model.addAttribute("url", url);


        log.info(this.getClass().getName() + " .getNoticeData End !!");
        return "Redirect";
    }
    @RequestMapping(value = "getNoticeList")
    public String getNoticeList(HttpServletRequest request, Model model) throws Exception {
        log.info(this.getClass().getName() + " .getNoticeList Start !!");

        List<NoticeDTO> rList = noticeService.getNoticeList();
        for(NoticeDTO aDTO : rList) {
            log.info("sysdate : " + aDTO.getRegdate());
            log.info("seq :" + aDTO.getNotice_seq());
        }


        if(rList == null) {
            rList = new ArrayList<>();
        }
        model.addAttribute("rList", rList);

        log.info(this.getClass().getName() + " .getNoticeList End !!");

        return "noticelist";
    }
    @RequestMapping(value = "getNoticeDetail")
    public String NoticeDetail (HttpServletRequest request, Model model) throws  Exception {
        log.info(this.getClass().getName() + " .getNoticeDetail Start !!");
        String notice_seq = CmmUtil.nvl(request.getParameter("no"));

        NoticeDTO pDTO = new NoticeDTO();
        pDTO.setNotice_seq(notice_seq);

        NoticeDTO rDTO = noticeService.getNoticeDetail(pDTO);
        if (rDTO == null) {
            model.addAttribute("msg","존재하지않는 게시물입니다.");
            model.addAttribute("url","getNoticeList");
            return "redirect";
        }
        model.addAttribute("rDTO",rDTO);
        log.info(this.getClass().getName() + ".NoticeDetail End !!");
        return "noticeDetail";
    }
    @GetMapping(value = "NoticeDelete")
    public String noticeDelete(HttpServletRequest request, Model model) throws Exception {
        log.info(this.getClass().getName() + " .noticeDelete Start !!");
        String notice_seq = CmmUtil.nvl(request.getParameter("no"));
        log.info("notice_seq : " + notice_seq);

        NoticeDTO nDTO = new NoticeDTO();
        nDTO.setNotice_seq(notice_seq);

        int res = noticeService.noticeDelete(nDTO);
        log.info("res :" + res);
        String msg;
        String url;
        if (res == 1) {
            msg = " 삭제 성공 !!";
            url = "getNoticeList";
        } else {
            msg = "삭제 실패 !!";
            url = "noticeDetail?no=" + notice_seq;
        }
        model.addAttribute("msg",msg);
        model.addAttribute("url",url);
        return "Redirect";
    }
    @GetMapping(value = "NoticeUpdate")
    public String NoticeUpdate(HttpServletRequest request, Model model) throws Exception {
        String Notice_seq = request.getParameter("no");
        log.info(Notice_seq);

        model.addAttribute("Notice",Notice_seq);
        return "editform";
    }
    @GetMapping(value = "DoNoticeUpdate")
    public String DoNoticeUpdate(HttpServletRequest request,Model model) throws Exception {
        String title = request.getParameter("title");
        String contents = request.getParameter("contents");
        String notice_seq = request.getParameter("notice_seq");

        log.info("받아온 번호 :" +notice_seq);
        log.info("받아온 제목 :" +title);
        log.info("받아온 내용 :" +contents);

        NoticeDTO nDTO = new NoticeDTO();
        nDTO.setNotice_seq(notice_seq);
        nDTO.setTitle(title);
        nDTO.setContents(contents);

        int res = noticeService.noticeUpdate(nDTO);

        String msg;
        String url;
        if (res == 1) {
            msg = " 수정 성공 !!";
            url = "getNoticeList";
        } else {
            msg = "수정 실패 !!";
            url = "getNoticeList";
        }
        model.addAttribute("msg",msg);
        model.addAttribute("url",url);

        return "Redirect";
    }
}
