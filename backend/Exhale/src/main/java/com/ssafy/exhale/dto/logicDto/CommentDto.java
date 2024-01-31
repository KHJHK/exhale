package com.ssafy.exhale.dto.logicDto;

import com.ssafy.exhale.domain.Comment;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class CommentDto {
    Long id;
    String content;
    Boolean isDelete;
    LocalDateTime createDate;
    LocalDateTime modifyDate;
    ArticleDto articleDto;
    MemberDto memberDto;
    CommentDto parentCommentDto;

    public static CommentDto of(Long id, String content, Boolean isDelete,
                                LocalDateTime createDate, LocalDateTime modifyDate,
                                ArticleDto articleDto, MemberDto memberDto, CommentDto commentDto)
    {
        return new CommentDto(id, content, isDelete, createDate, modifyDate,
                articleDto, memberDto, commentDto);
    }

    public static CommentDto from(Comment entity){
        if(entity.getComment() != null){
            return new CommentDto(
                    entity.getId(),
                    entity.getContent(),
                    entity.getIsDelete(),
                    entity.getCreateDate(),
                    entity.getModifyDate(),
                    ArticleDto.from(entity.getArticle()),
                    MemberDto.from(entity.getMember()),
                    CommentDto.from(entity.getComment())
            );
        }
        return new CommentDto(
                entity.getId(),
                entity.getContent(),
                entity.getIsDelete(),
                entity.getCreateDate(),
                entity.getModifyDate(),
                ArticleDto.from(entity.getArticle()),
                MemberDto.from(entity.getMember()),
                null
        );
    }
    public CommentDto toEntity(){
        return CommentDto.of(
                id,
                content,
                isDelete,
                createDate,
                modifyDate,
                articleDto,
                memberDto,
                parentCommentDto
        );
    }
}
