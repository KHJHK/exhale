package com.ssafy.exhale.service;

import com.ssafy.exhale.domain.Article;
import com.ssafy.exhale.domain.ArticleFile;
import com.ssafy.exhale.domain.Board;
import com.ssafy.exhale.domain.Member;
import com.ssafy.exhale.dto.logicDto.ArticleDto;
import com.ssafy.exhale.dto.logicDto.ArticleFileDto;
import com.ssafy.exhale.dto.logicDto.BoardDto;
import com.ssafy.exhale.dto.logicDto.MemberDto;
import com.ssafy.exhale.dto.requestDto.ArticleRequest;
import com.ssafy.exhale.dto.requestDto.ArticleSearchRequest;
import com.ssafy.exhale.dto.responseDto.ArticleResponse;
import com.ssafy.exhale.repository.ArticleFileRepository;
import com.ssafy.exhale.repository.ArticleRepository;
import com.ssafy.exhale.repository.BoardRepository;
import com.ssafy.exhale.repository.MemberRepository;
import com.ssafy.exhale.util.S3Util;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ArticleService {
    private final ArticleRepository articleRepository;
    private final BoardRepository boardRepository;
    private final MemberRepository memberRepository;
    private final ArticleFileRepository articleFileRepository;
    private final S3Util s3Util;
    private final int PAGE_SIZE = 10;

    public List<ArticleResponse> getArticleListByBoardId(Integer boardId, int page){
        PageRequest pageRequest = PageRequest.of(page, PAGE_SIZE);
        List<Article> articleEntityList = articleRepository.findAllByBoardIdAndIsDelete(boardId, pageRequest, false);

        return articleEntityList.stream()
                .map(article -> {
                    ArticleDto articleDto = ArticleDto.from(article);
                    return ArticleResponse.from(articleDto);
                })
                .collect(Collectors.toList());
    }

    public ArticleResponse getArticle(Long articleId){
        try{
            Article article = articleRepository.findByIdAndIsDelete(articleId, false);
            return ArticleResponse.from(ArticleDto.from(article));
        }catch (Exception e){
            System.out.println(articleId + "번 게시글 존재 X");
            return null;
        }
    }
    //request로 받도록 설정
    public void postArticle(ArticleRequest articleRequest, Long memberId){
        try {
            Board board = boardRepository.getReferenceById(articleRequest.getBoardId());
            Member member = memberRepository.getReferenceById(memberId);

            ArticleDto articleDto = articleRequest.toDto(
                    BoardDto.from(board),
                    MemberDto.from(member)
            );

            Article article = articleDto.toEntity(
                    articleDto.getBoardDto().toEntity(),
                    articleDto.getMemberDto().toEntity()
            );

            articleRepository.save(article);
        } catch (Exception e){
            System.out.println("필요 데이터 존재 X");
        }
    }

    public void modifyArticle(Long articleId, ArticleRequest articleRequest, Long memberId){
        try{
            Article originalArticle = articleRepository.findByIdAndIsDelete(articleId, false);
            if(memberRepository.findById(memberId).isPresent()){
                Member member = memberRepository.findById(memberId).get();
                if(!Objects.equals(member.getId(), originalArticle.getMember().getId())){
                    System.out.println("권한 없는 사용자");
                    return;
                }
            }
            ArticleDto originalArticleDto = ArticleDto.from(originalArticle);

            originalArticleDto.setId(articleId);
            originalArticleDto.setModifyDate(LocalDateTime.now());
            originalArticleDto.setTitle(articleRequest.getTitle());
            originalArticleDto.setContent(articleRequest.getContent());
            originalArticleDto.setThumbnail(articleRequest.getThumbnail());


            Article modifyArticle = originalArticleDto.toEntity(
                    originalArticleDto.getBoardDto().toEntity(),
                    originalArticleDto.getMemberDto().toEntity()
            );
            articleRepository.save(modifyArticle);
        }catch (Exception e){
            System.out.println("필요 데이터 존재 X");
        }
    }

    public void deleteArticle(Long articleId, Long memberId){
        try{
            Article originalArticle = articleRepository.findByIdAndIsDelete(articleId,false);
            if(memberRepository.findById(memberId).isPresent()){
                Member member = memberRepository.findById(memberId).get();
                if(!Objects.equals(member.getId(), originalArticle.getMember().getId())){
                    System.out.println("권한 없는 사용자");
                    return;
                }
            }
            ArticleDto articleDto = ArticleDto.from(originalArticle);
            articleDto.setIsDelete(true);
            Article deleteArticle = articleDto.toEntity(
                    articleDto.getBoardDto().toEntity(),
                    articleDto.getMemberDto().toEntity()
            );
            articleRepository.save(deleteArticle);
        }catch (Exception e){
            System.out.println("필요 데이터 X");
        }
    }

    public List<ArticleResponse> search(ArticleSearchRequest searchRequest){
        try{
            PageRequest pageRequest = PageRequest.of(searchRequest.getPage(), PAGE_SIZE);
            List<Article> articleEntityList = articleRepository.search(searchRequest, pageRequest);
            return articleEntityList.stream()
                    .map(article -> {
                        ArticleDto articleDto = ArticleDto.from(article);
                        return ArticleResponse.from(articleDto);
                    })
                    .collect(Collectors.toList());
        } catch (Exception e){
            System.out.println("error");
            return null;
        }
    }

    public String saveImage(MultipartFile file, Long articleId){
        try{
            String imageURL = s3Util.saveImage(file);
            Article article = articleRepository.getReferenceById(articleId);
            articleFileRepository.save(ArticleFile.of(null, article, false, imageURL));

            return imageURL;
        }catch (Exception e){
            System.out.println("save error");
            e.printStackTrace();
            return null;
        }
    }

    public void deleteImage(Long articleFileId){
        try{
            ArticleFile articleFile = articleFileRepository.findById(articleFileId).get();
            ArticleFileDto articleFileDto = ArticleFileDto.from(articleFile);
            articleFileDto.setIsDelete(true);
            ArticleFile deleteArticleFile = articleFileDto.toEntity(articleFile.getArticle());
            articleFileRepository.save(deleteArticleFile);
        }catch (Exception e){
            System.out.println("save error");
        }
    }
}
