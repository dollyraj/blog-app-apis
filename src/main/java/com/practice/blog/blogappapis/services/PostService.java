package com.practice.blog.blogappapis.services;

import com.practice.blog.blogappapis.entities.Category;
import com.practice.blog.blogappapis.entities.Comment;
import com.practice.blog.blogappapis.entities.Post;
import com.practice.blog.blogappapis.entities.User;
import com.practice.blog.blogappapis.exceptions.ResourceNotFoundException;
import com.practice.blog.blogappapis.payloads.CommentDTO;
import com.practice.blog.blogappapis.payloads.PostDTO;
import com.practice.blog.blogappapis.payloads.PostResponse;
import com.practice.blog.blogappapis.repositories.CategoryRepository;
import com.practice.blog.blogappapis.repositories.PostRepository;
import com.practice.blog.blogappapis.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class PostService {

    @Autowired
    PostRepository postRepository;

    @Autowired
    CommentService commentService;
    @Autowired
    UserRepository userRepository;
    @Autowired
    CategoryRepository categoryRepository;

    public PostDTO createPost(PostDTO postDTO,int userId,int categoryId){

        if(!this.userRepository.findById(userId).isPresent()){
            throw new ResourceNotFoundException("User","Id",userId);
        }

        User user=this.userRepository.findById(userId).get();

        if(!this.categoryRepository.findById(categoryId).isPresent()){
            throw new ResourceNotFoundException("Category","Id",categoryId);
        }

        Category category=this.categoryRepository.findById(categoryId).get();


        Post post=this.dtoToPost(postDTO);
        post.setImageName("default.png");
        post.setAddedDate(new Date());
        post.setCategory(category);
        post.setUser(user);
        Post savedPost=this.postRepository.save(post);

        return this.postToDto(savedPost);
    }

    public PostResponse getAllPosts(int pageNumber,int pageSize,String sortBy,String sortDir){

        Sort sort=sortDir.equalsIgnoreCase("asc")?Sort.by(sortBy).ascending():Sort.by(sortBy).descending();

        Pageable pageable= PageRequest.of(pageNumber,pageSize, sort);
        Page<Post> pagePostList= (Page<Post>) this.postRepository.findAll(pageable);

        List<PostDTO> postDTOList=new ArrayList<>();


        for(Post post:pagePostList){
            postDTOList.add(this.postToDto(post));
        }

        PostResponse postResponse=new PostResponse();
        postResponse.setContent(postDTOList);
        postResponse.setPageNumber(pagePostList.getNumber());
        postResponse.setPageSize(pagePostList.getSize());
        postResponse.setTotalElements(pagePostList.getTotalElements());
        postResponse.setTotalPages(pagePostList.getTotalPages());
        postResponse.setLastPage(pagePostList.isLast());
        return postResponse;
    }

    public PostDTO getPostById(int postId){
        if(!this.postRepository.findById(postId).isPresent()){
            throw new ResourceNotFoundException("Post","Id",postId);
        }
        return this.postToDto(this.postRepository.findById(postId).get());
    }

    public PostDTO updatePost(PostDTO postDTO,int postId){

        if(!this.postRepository.findById(postId).isPresent()){
            throw new ResourceNotFoundException("Post","Id",postId);
        }
        Post post=this.postRepository.findById(postId).get();
        post.setTitle(postDTO.getTitle());
        post.setContent(post.getContent());
        post.setImageName(postDTO.getImageName());

        Post savedPost=this.postRepository.save(post);
        return this.postToDto(savedPost);
    }

    public void deletePostById(int postId){
        if(!this.postRepository.findById(postId).isPresent()){
            throw new ResourceNotFoundException("Post","Id",postId);
        }
        this.postRepository.deleteById(postId);
    }

    public PostResponse getPostByCategory(Integer categoryId,int pageNumber,int pageSize,String sortBy,String sortDir){

        //System.out.println(categoryId);


        if(!this.categoryRepository.findById(categoryId).isPresent()){
            throw new ResourceNotFoundException("Category","Id",categoryId);
        }
        Category category=this.categoryRepository.findById(categoryId).get();

        //System.out.println(category);

        Sort sort=sortDir.equalsIgnoreCase("asc")?Sort.by(sortBy).ascending():Sort.by(sortBy).descending();

        Pageable pageable= PageRequest.of(pageNumber,pageSize, sort);
    //    Page<Post> pagePostList= (Page<Post>) this.postRepository.findAll(pageable);

        Page<Post> pagePostList=this.postRepository.findByCategory(category,pageable);
        List<PostDTO> postDTOList=new ArrayList<>();

        for(Post post:pagePostList){
            postDTOList.add(this.postToDto(post));
        }

        PostResponse postResponse=new PostResponse();
        postResponse.setContent(postDTOList);
        postResponse.setPageNumber(pagePostList.getNumber());
        postResponse.setPageSize(pagePostList.getSize());
        postResponse.setTotalElements(pagePostList.getTotalElements());
        postResponse.setTotalPages(pagePostList.getTotalPages());
        postResponse.setLastPage(pagePostList.isLast());
        return postResponse;

//        for(Post post:postList){
//            postDTOList.add(this.postToDto(post));
//        }
//
//        return postDTOList;
    }

    public PostResponse getPostByUser(int userId,int pageNumber,int pageSize,String sortBy,String sortDir){
        if(!this.userRepository.findById(userId).isPresent()){
            throw new ResourceNotFoundException("User","Id",userId);
        }
        User user=this.userRepository.findById(userId).get();
        Sort sort=sortDir.equalsIgnoreCase("asc")?Sort.by(sortBy).ascending():Sort.by(sortBy).descending();

        Pageable pageable= PageRequest.of(pageNumber,pageSize, sort);


       // Page<Post> postList=this.postRepository.findByCategory(category,pageable);
        Page<Post> pagePostList=this.postRepository.findByUser(user,pageable);

        List<PostDTO> postDTOList=new ArrayList<>();

        for(Post post:pagePostList){
            postDTOList.add(this.postToDto(post));
        }

        PostResponse postResponse=new PostResponse();
        postResponse.setContent(postDTOList);
        postResponse.setPageNumber(pagePostList.getNumber());
        postResponse.setPageSize(pagePostList.getSize());
        postResponse.setTotalElements(pagePostList.getTotalElements());
        postResponse.setTotalPages(pagePostList.getTotalPages());
        postResponse.setLastPage(pagePostList.isLast());
        return postResponse;

//        for(Post post:postList){
//            postDTOList.add(this.postToDto(post));
//        }
//
//        return postDTOList;

    }

    public List<PostDTO> searchPost(String keyword){

        List<Post> postList=this.postRepository.findByTitleContaining(keyword);
        List<PostDTO> postDTOList=new ArrayList<>();

        for(Post post:postList){
            postDTOList.add(this.postToDto(post));
        }

        return postDTOList;
       // return null;
    }


    private Post dtoToPost(PostDTO postDTO){
        Post post=new Post();
        post.setTitle(postDTO.getTitle());
        post.setContent(postDTO.getContent());

        return post;

    }

    private PostDTO postToDto(Post post){
        PostDTO postDTO=new PostDTO();
        postDTO.setId(post.getPostId());
        postDTO.setTitle(post.getTitle());
        postDTO.setContent(post.getContent());
        postDTO.setAddedDate(post.getAddedDate());
        postDTO.setImageName(post.getImageName());
        postDTO.setCategory(post.getCategory());
        postDTO.setUser(post.getUser());
        Set<Comment> comments=post.getComments();
        Set<CommentDTO> commentsDto=new HashSet<>();
        for(Comment c:comments){
            commentsDto.add(commentService.commentToDto(c));
        }
        postDTO.setComments(commentsDto);
        return postDTO;
    }
}
