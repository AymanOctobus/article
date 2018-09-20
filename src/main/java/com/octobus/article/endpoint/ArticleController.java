package com.octobus.article.endpoint;

import java.io.IOException;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.octobus.article.model.Article;
import com.octobus.article.model.ArticleUploadRequest;
import com.octobus.article.model.ArticleUploadResponse;
import com.octobus.article.model.Comments;
import com.octobus.article.model.Point;
import com.octobus.article.model.UploadFileResponse;
import com.octobus.article.service.ArticleService;
import com.octobus.article.service.FileStorageService;

@RestController
public class ArticleController {

	@Autowired
	private ArticleService service;
	
	@Autowired
    private FileStorageService fileStorageService;
	
	@PostMapping("site/article/upload")
	public ResponseEntity<ArticleUploadResponse> updateArticle(@RequestBody ArticleUploadRequest arcticle){
		ArticleUploadResponse createdArcticle = service.uploadArticle(arcticle);
		return new ResponseEntity<>(createdArcticle,HttpStatus.CREATED);
	}
	
	@PostMapping("site/article")
	public ResponseEntity<Article> createArticle(@RequestBody Article article){
		Article createdArtile = service.createArticle(article);
		return new ResponseEntity<>(createdArtile,HttpStatus.CREATED);
	}
	
	@GetMapping("site/articles")
	public ResponseEntity<List<Article>> getArticles(){
		List<Article> arcticles =service.getArticles();
		return new ResponseEntity<>(arcticles,HttpStatus.OK);
	}
	
	@PostMapping("site/article/like/{articleId}")
	public ResponseEntity<Object> getArticleById(@PathVariable("articleId") String articleId, @RequestBody Point point){
		Integer totalPoint = service.postLike(articleId,point);
		return new ResponseEntity<>("totalPoint :"+totalPoint,HttpStatus.OK);
	}
	
	@GetMapping("/site/article/comment/{articleId}")
	public ResponseEntity<List<Comments>> getComments(@PathVariable("articleId") String articleId){
		List<Comments> comments =service.getCommentsByArticleId(articleId);
		return new ResponseEntity<>(comments,HttpStatus.OK);
	}
	
	@PostMapping("/site/article/comment/{articleId}")
	public ResponseEntity<Comments> postComments(@PathVariable("articleId") String articleId,@RequestBody Comments comment){
		Comments postedComment = service.postComment(articleId,comment);
		return new ResponseEntity<>(postedComment,HttpStatus.OK);
	}
	
	@PostMapping(value="site/article/video/{articleId}")
	public ResponseEntity<UploadFileResponse> uploadArticleVideo(@RequestParam("file") MultipartFile file,@PathVariable("articleId") String articleId){
		String fileName = fileStorageService.storeFile(file,articleId,false);
		String videoUri = ServletUriComponentsBuilder.fromCurrentContextPath()
                .path("/site/article/video/"+articleId+"/")
                .path(fileName)
                .toUriString();
		service.updateArticleVideo(articleId,videoUri);
        return new ResponseEntity<>(new UploadFileResponse(fileName, videoUri,
                file.getContentType(), file.getSize()), HttpStatus.OK);
	}
	
	@GetMapping(value="site/article/video/{articleId}/{fileName}")
	public ResponseEntity<Resource> downloadArticleVideo(@PathVariable("articleId") String articleId, @PathVariable("fileName") String fileName, HttpServletRequest request) {
        Resource resource = fileStorageService.loadFileAsResource(fileName,articleId,false);
        String contentType = null;
        try {
            contentType = request.getServletContext().getMimeType(resource.getFile().getAbsolutePath());
        } catch (IOException ex) {
        }
        if(contentType == null) {
            contentType = "application/octet-stream";
        }
        return ResponseEntity.ok()
                .contentType(MediaType.parseMediaType(contentType))
                .header(HttpHeaders.CONTENT_DISPOSITION, "inline; filename=\"" + resource.getFilename() + "\"")
                .body(resource);
    }
}
