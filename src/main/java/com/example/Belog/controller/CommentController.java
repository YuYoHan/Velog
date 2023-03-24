package com.example.Belog.controller;

import com.example.Belog.domain.CommentDTO;
import com.example.Belog.service.CommentService;
import lombok.AllArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.List;

@Controller
@Log4j2
@AllArgsConstructor
public class CommentController {

    private CommentService commentService;

    // 댓글작성
    @PostMapping("/comment/add")    //?
    public String addComment(@RequestParam String comment, HttpServletRequest request) {
        log.info("add Comment");

        // userId 세션에서 값 얻어오기
        HttpSession session = request.getSession();
        Long userId = (Long) session.getAttribute("userId");

        if (userId != null) {
            //boardNum을 얻어오는 값
            Long boardNum = Long.parseLong(request.getParameter("boardNum"));

            try {
                if (!comment.equals("") && !comment.isEmpty()) {
                    CommentDTO commentDTO = CommentDTO.builder()
                            .userId(userId)
                            .boardNum(boardNum)
                            .comment(comment)
                            .build();
                    commentService.addComment(commentDTO);
                }
                log.info("Add success");
            } catch (Exception e) {
                log.info("error");
                e.printStackTrace();
            }
        }
        return "redirect:/board/{boardNum}";
    }// addComment

    // 댓글수정
    @PostMapping("/comment/edit")
    public String editComment(@RequestParam String comment, HttpServletRequest request) {
        Long boardNum = Long.parseLong(request.getParameter("boardNum"));
        Long commentNum = Long.parseLong(request.getParameter("commentNum"));

        CommentDTO commentDTO = CommentDTO.builder()
                .boardNum(boardNum)
                .commentNum(commentNum)
                .comment(comment)
                .build();

        commentService.editComment(commentDTO);
        log.info("edit");
        return "redirect:/board/{boardNum}";
    }

    // 댓글삭제
    @PostMapping("/comment/delete")
    public String deleteComment(HttpServletRequest request) {
        Long boardNum = Long.parseLong(request.getParameter("boardNum"));
        Long commentNum = Long.parseLong(request.getParameter("commentNum"));

        CommentDTO commentDTO = CommentDTO.builder()
                .boardNum(boardNum)
                .commentNum(commentNum)
                .build();

        commentService.deleteComment(commentDTO);
        log.info("delete");
        return "redirect:/board/{boardNum}";
    }

    // 전체 댓글보여주기
    @GetMapping("/comment/{boardNum}")
    public String findAllComment(@PathVariable Long boardNum, HttpServletRequest request, Model model) {
        log.info("find");

        List<CommentDTO> commentList = commentService.findAllComment(boardNum);
        model.addAttribute("commentList", commentList);

        return "redirect:/board/{boardNum}";
    }

    @GetMapping("/comment/count")
    public int count(int boardNum) {
        return commentService.countComment(boardNum);
    }
}
