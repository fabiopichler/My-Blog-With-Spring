/*-------------------------------------------------------------------------------

Copyright (c) 2023 Fábio Pichler

Permission is hereby granted, free of charge, to any person obtaining a copy
of this software and associated documentation files (the "Software"), to deal
in the Software without restriction, including without limitation the rights
to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
copies of the Software, and to permit persons to whom the Software is
furnished to do so, subject to the following conditions:

The above copyright notice and this permission notice shall be included in all
copies or substantial portions of the Software.

THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE
SOFTWARE.

-------------------------------------------------------------------------------*/

package net.fabiopichler.myblogwithspring.service.impl;

import net.fabiopichler.myblogwithspring.dto.PostDTO;
import net.fabiopichler.myblogwithspring.model.Post;
import net.fabiopichler.myblogwithspring.model.User;
import net.fabiopichler.myblogwithspring.repository.PostRepository;
import net.fabiopichler.myblogwithspring.service.PostService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@Service
public class PostServiceImpl implements PostService {

    @Autowired
    private PostRepository repository;

    @Override
    public Page<Post> findPaginated(int page, int size) {
        return repository.findAll(getPageable(page, size));
    }

    @Override
    public Page<Post> findAllByUserPaginated(User user, int page, int size) {
        return repository.findAllByUser(user, getPageable(page, size));
    }

    @Override
    public Page<Post> searchPaginated(String body, int page, int size) {
        return repository.findByBodyContaining(body, getPageable(page, size));
    }

    @Override
    public Post add(PostDTO postDTO, User user) {
        var post = new Post();

        post.setUser(user);
        post.setPostname(postDTO.getPostname());
        post.setTitle(postDTO.getTitle());
        post.setBody(postDTO.getBody());

        return repository.save(post);
    }

    @Override
    public Post findById(Long id) {
        return repository.findById(id).orElseThrow(() ->
                new ResponseStatusException(HttpStatus.NOT_FOUND, "Not found"));
    }

    @Override
    public Post findByPostname(String postname) {
        return repository.findByPostname(postname).orElseThrow(() ->
                new ResponseStatusException(HttpStatus.NOT_FOUND, "Not found"));
    }

    @Override
    public List<Post> findLastPosts() {
        return repository.findFirst5AllByOrderByCreatedAtDesc();
    }

    private Pageable getPageable(int page, int size) {
        return PageRequest.of(page - 1, size, Sort.by("createdAt").descending());
    }
}
