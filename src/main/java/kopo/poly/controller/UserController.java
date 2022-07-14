package kopo.poly.controller;

import kopo.poly.dto.UserInfoDTO;
import kopo.poly.service.IUserService;
import kopo.poly.util.CmmUtil;
import lombok.extern.slf4j.Slf4j;
import org.apache.catalina.User;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.swing.event.HyperlinkEvent;

@Slf4j
@Controller
public class UserController {
    @Resource (name = "UserService")
    private IUserService userService;

    @GetMapping(value = "UserInfo")
    public String UserInfo() throws Exception {
        log.info(this.getClass().getName() + " .UserInfo Start!!");
        log.info(this.getClass().getName() + " .UserInfo End!!");
        return "UserInfo";
    }
    @PostMapping(value = "getInsertUser")
    public String getInsertUser(HttpServletRequest request, Model model) throws Exception {
        log.info(this.getClass().getName() + " .getInsertUser Start !!");
        String name = CmmUtil.nvl(request.getParameter("name"));
        String id = CmmUtil.nvl(request.getParameter("id"));
        String pwd = CmmUtil.nvl(request.getParameter("pwd"));
        String email = CmmUtil.nvl(request.getParameter("email"));

        UserInfoDTO pDTO = new UserInfoDTO();
        pDTO.setUser_name(name);
        pDTO.setUser_id(id);
        pDTO.setUser_pwd(pwd);
        pDTO.setUser_email(email);

        int res = userService.InsertUserInfo(pDTO);

        String msg;
        String url = "/UserInfo";
        if (res > 0) {
            msg = "등록에 성공하셨습니다.";
        } else {
            msg = "등록에 실패하셨습니다.";
        }
        model.addAttribute("msg",msg);
        model.addAttribute("url",url);

        log.info(this.getClass().getName() + " .getInsertUser Start !!");

        return "Redirect";
    }
}
