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

package net.fabiopichler.myblogwithspring.controller;

import net.fabiopichler.myblogwithspring.model.Post;
import net.fabiopichler.myblogwithspring.model.Principal;
import net.fabiopichler.myblogwithspring.model.User;
import net.fabiopichler.myblogwithspring.service.PostService;
import net.fabiopichler.myblogwithspring.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.stereotype.Controller;

import java.util.List;

@ControllerAdvice(annotations = Controller.class)
public class AdviceController {

    @Autowired
    private UserService userService;

    @Autowired
    private PostService postService;

    @ModelAttribute("authUser")
    public User getAuthUser() {
        Object principalObject = SecurityContextHolder.getContext().getAuthentication().getPrincipal();

        if (principalObject instanceof Principal principal)
            return userService.findByPrincipal(principal);

        return null;
    }

    @ModelAttribute("lastPosts")
    public List<Post> getLastPosts() {
        return postService.findLastPosts();
    }
}
