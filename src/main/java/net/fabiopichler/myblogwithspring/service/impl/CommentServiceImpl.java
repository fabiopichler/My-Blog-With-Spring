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

import net.fabiopichler.myblogwithspring.dto.CommentDTO;
import net.fabiopichler.myblogwithspring.model.Comment;
import net.fabiopichler.myblogwithspring.model.Post;
import net.fabiopichler.myblogwithspring.model.User;
import net.fabiopichler.myblogwithspring.repository.CommentRepository;
import net.fabiopichler.myblogwithspring.service.CommentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CommentServiceImpl implements CommentService {

    @Autowired
    private CommentRepository repository;

    @Override
    public Comment add(CommentDTO commentDTO, Post post, User user) {
        var comment = new Comment();

        comment.setPost(post);
        comment.setUser(user);
        comment.setAuthorName(user != null ? user.getName() : commentDTO.getAuthorName());
        comment.setAuthorEmail(user != null ? user.getEmail() : commentDTO.getAuthorEmail());
        comment.setBody(commentDTO.getBody());

        return repository.save(comment);
    }
}
