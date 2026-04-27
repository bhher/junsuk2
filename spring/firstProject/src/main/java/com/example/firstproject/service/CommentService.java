package com.example.firstproject.service;

import com.example.firstproject.dto.CommentDto;
import com.example.firstproject.entity.Article;
import com.example.firstproject.entity.Comment;
import com.example.firstproject.repository.ArticleRepository;
import com.example.firstproject.repository.CommentRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.transaction.support.ResourceTransactionManager;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
@Transactional
public class CommentService {

    @Autowired
    private CommentRepository commentRepository;

    @Autowired
    private ArticleRepository articleRepository;


    @Transactional(readOnly = true)
    public List<CommentDto> comments(Long articleId) {
        //조회 : 댓글 목록
       //List<Comment> comments = commentRepository.findByArticleId(articleId);
        //변환 :  엔티티 -> dto
      //List<CommentDto> dtos =  new ArrayList<CommentDto>();
      //for(int i = 0; i < comments.size();i++){
     //     Comment c = comments.get(i);
      //   CommentDto dto = CommentDto.createCommentDto(c);
     //    dtos.add(dto);
    //  }
      //반환
     // return dtos;
      //stream(),map(), collect(),collectors.toList

    return commentRepository.findByArticleId(articleId) //commentRepository 목록을 조회
            .stream() //stream 변경
            .map(comment -> CommentDto.createCommentDto(comment)) //createCommentDto 를 통해 comment를 하나하나 전달하여 dto 변환
            .collect(Collectors.toList());
    }


    public CommentDto create(Long articleId, CommentDto dto) {
        //게시글 조회 및 예외발생
      Article article = articleRepository.findById(articleId)
                .orElseThrow(()-> new IllegalArgumentException("댓글생성 실패! 대상게시글이 없음"));
      //댓글 엔티티 생성
     Comment comment=  Comment.createComment(dto, article);

     //댓글 엔티티를 db로 저장
       Comment created = commentRepository.save(comment);

       //dto로 변경해서 반환
        return CommentDto.createCommentDto(created);
    }

    public CommentDto update(Long id, CommentDto dto) {
        //댓글 조회 및 예외발생
       Comment target = commentRepository.findById(id)
                .orElseThrow(()->new IllegalArgumentException("댓글 수정실패! 대상 댓글 없습니다."));
        //댓글 수정
        target.patch(dto);
        //DB 갱신
        Comment updated= commentRepository.save(target);
        //댓글 엔티를 DTO로 변환 및 반환
        return  CommentDto.createCommentDto(updated);

    }


    public CommentDto delete(Long id) {
        //댓글 조회(및 예외발생)
        Comment target = commentRepository.findById(id)
                .orElseThrow(()->new IllegalArgumentException("댓글 삭제실패! 대상 댓글 없습니다."));
        //댓글 삭제
        commentRepository.delete(target);
        //삭제 댓글을 Dto로 반환
        return CommentDto.createCommentDto(target);
    }
}
