package com.example.firstproject.controller;

import com.example.firstproject.dto.CommentDto;
import com.example.firstproject.entity.Comment;
import com.example.firstproject.service.CommentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class CommentApiController {

    @Autowired
    private CommentService commentService;

    //댓글 목록 조회
    @GetMapping("/api/articles/{articleId}/comments")
    public ResponseEntity<List<CommentDto>> comments(@PathVariable Long articleId){
        //서비스에 위임
       List<CommentDto> dtos= commentService.comments(articleId);

       //결과에 대한 응답
        return ResponseEntity.status(HttpStatus.OK).body(dtos);
    }


    //댓글 생성
    @PostMapping("/api/articles/{articleId}/comments")
    public ResponseEntity<CommentDto> create(@PathVariable Long articleId ,
                                             @RequestBody CommentDto dto){
        //서비스스에게 위임
        CommentDto createdDto= commentService.create(articleId, dto);

        // 결과 응답
        return ResponseEntity.status(HttpStatus.OK).body(createdDto);
    }


    //댓글 수정
    @PatchMapping("/api/comments/{id}")
    public ResponseEntity<CommentDto> update(@PathVariable Long id,
                                             @RequestBody CommentDto dto){
        //@RequestBody CommentDto dto
        //Json 형식의 데이터를 자바 객체호 받겠다는 선언
        //@ModelAttribute -> html form 전송 name="준석"&body="안녕" 일반웹페이지
        // @RequestBody ->json 데이터전송 {"name":"준석","body":"안녕"} - React,vue
        // 서비스에게 위임
        CommentDto updatedDto = commentService.update(id, dto);
        // 결과 응답
        return ResponseEntity.status(HttpStatus.OK).body(updatedDto);
    }



    //댓글 삭제

    // 댓글 삭제
    @DeleteMapping("/api/comments/{id}")
    public ResponseEntity<CommentDto> delete(@PathVariable Long id) {
        // 서비스에게 위임
        CommentDto deletedDto = commentService.delete(id);
        // 결과 응답
        return ResponseEntity.status(HttpStatus.OK).body(deletedDto);
    }

}
