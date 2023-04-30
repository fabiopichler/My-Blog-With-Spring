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

import jakarta.validation.Valid;
import net.fabiopichler.myblogwithspring.dto.PostDto;
import net.fabiopichler.myblogwithspring.model.Post;
import net.fabiopichler.myblogwithspring.model.User;
import net.fabiopichler.myblogwithspring.service.PostService;
import net.fabiopichler.myblogwithspring.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@Controller
public class PostController {

    @Autowired
    private PostService postService;

    @Autowired
    private UserService userService;

    @GetMapping("/")
    public String home(Model model, @RequestParam(required = false) Optional<Integer> index) {

        int currentIndex = index.orElse(1);
        Page<Post> postsPage = postService.findPaginated(currentIndex, 5);

        model.addAttribute("baseUrlPagination", "/?");
        model.addAttribute("currentPage", currentIndex);
        model.addAttribute("totalPages", postsPage.getTotalPages());
        model.addAttribute("totalItems", postsPage.getTotalElements());
        model.addAttribute("posts", postsPage.getContent());

        return "home";
    }

    @GetMapping("/search")
    public String search(Model model,
                         @RequestParam(required = false) Optional<Integer> index,
                         @RequestParam String q) {

        int currentIndex = index.orElse(1);
        Page<Post> postsPage = postService.searchPaginated(q, currentIndex, 5);

        model.addAttribute("baseUrlPagination", "/search?q=" + q + "&");
        model.addAttribute("currentPage", currentIndex);
        model.addAttribute("totalPages", postsPage.getTotalPages());
        model.addAttribute("totalItems", postsPage.getTotalElements());
        model.addAttribute("posts", postsPage.getContent());
        model.addAttribute("search_q", q);

        return "search";
    }

    @GetMapping("/post/{postname}")
    public String show(Model model, @PathVariable String postname) {
        Post post = postService.findByPostname(postname);

        model.addAttribute("post", post);

        return "post-show";
    }

    @GetMapping("/post/new")
    @PreAuthorize("hasRole('ADMIN')")
    public String create() {
        return "post-new";
    }

    @PostMapping("/post/new")
    @PreAuthorize("hasRole('ADMIN')")
    public String store(@Valid PostDto postDto, @ModelAttribute("authUser") User authUser) {
        Post post = postService.add(postDto, authUser);

        return "redirect:/post/" + post.getPostname();
    }
}
