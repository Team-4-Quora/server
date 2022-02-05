package com.example.qna.controller;

import com.example.qna.dto.CommentDto;
import com.example.qna.entity.Comment;
import com.example.qna.service.CommentService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.time.Instant;
import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/qna/comment")
public class CommentController {
    @Autowired
    private CommentService commentService;

    @GetMapping("/fetch/{id}/{parentId}")
    List<CommentDto> fetchByAnswerIdAndParentId(@PathVariable(value = "id") String id,@PathVariable(value = "parentId") String parent){
        List<Comment> comments=commentService.findByAnswerIdAndParentComment(id,parent);
        List<CommentDto> commentDtos=new ArrayList<>();
        for(Comment comment:comments){
            CommentDto commentDto=new CommentDto();
            BeanUtils.copyProperties(comment,commentDto);
            commentDtos.add(commentDto);
        }
        return commentDtos;
    }

    @GetMapping("/fetch/{id}")
    List<CommentDto> fetchByAnswerId(@PathVariable(value = "id") String id){
        List<Comment> comments=commentService.findByAnswerId(id);
        List<CommentDto> commentDtos=new ArrayList<>();
        for(Comment comment:comments){
            CommentDto commentDto=new CommentDto();
            BeanUtils.copyProperties(comment,commentDto);
            commentDtos.add(commentDto);
        }
        return commentDtos;
    }

    @PostMapping("/delete/{id}")
    void deletebyId(@PathVariable(value = "id")String id){
        commentService.deleteById(id);
    }

    @PostMapping("/add")
    void save(@RequestBody CommentDto commentDto){
        Comment comment=new Comment();
        BeanUtils.copyProperties(commentDto,comment);
        comment.setPostedOn(Instant.now().getEpochSecond());
        commentService.save(comment);
    }
}
