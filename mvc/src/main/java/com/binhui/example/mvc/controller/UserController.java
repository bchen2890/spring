package com.binhui.example.mvc.controller;

import com.binhui.example.mvc.models.entity.Order;
import com.binhui.example.mvc.models.entity.OrderItem;
import com.binhui.example.mvc.models.entity.Product;
import com.binhui.example.mvc.models.entity.User;
import com.binhui.example.mvc.service.IUploadFileService;
import com.binhui.example.mvc.service.IUserService;
import com.binhui.example.mvc.service.PageRender;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.bind.support.SessionStatus;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.io.IOException;
import java.net.MalformedURLException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.Map;

@Controller
@SessionAttributes("user")
public class UserController {
    @Autowired
    private IUserService userService;

   /* @GetMapping("/list")
    public String list(Model model){
        model.addAttribute("users", userService.findAll());
        return "list";
    }*/
    @Autowired
    private IUploadFileService uploadFileService;

    @GetMapping("/list")
    public String list(@RequestParam(name="page", defaultValue="0") int page, Model model) {

        //Data sample

        User user = new User();
        user.setUsername("bchen");
        user.setEmail("bchen@example.com");
        userService.save(user);
        Pageable pageRequest = PageRequest.of(page, 8);

        Page<User> users = userService.findAll(pageRequest);

        PageRender<User> pageRender = new PageRender<User>("/list", users);
        model.addAttribute("users", users);
        model.addAttribute("page", pageRender);
        return "list";
    }

    @RequestMapping(value="/form")
    public String create (Map<String, Object> model){
        User user = new User();
        user.setCreatedAt(new Date());
        user.setUsername("bchen");
        user.setEmail("hello@example.com");
        model.put("user", user);
        return "form";
    }

    @RequestMapping(value = "/user/{id}")
    public String edit(@PathVariable(value="id") Long id, Map<String, Object> model) {

        User user = null;

        if(id > 0) {
            user = userService.findOne(id);
        } else {
            return "redirect:/list";
        }
        model.put("user", user);
        return "form";
    }

    @PostMapping("/user")
    public String save(@Validated User user, BindingResult result, Model model, 
                       @RequestParam("file") MultipartFile file, RedirectAttributes flash,
                       SessionStatus status) {
        if(result.hasErrors()) {
            System.out.println("error");
            result.getAllErrors().forEach(objectError -> System.out.println("error: " + objectError.toString()));
            return "form";
        }
        if (!file.isEmpty()) {

            if (user.getId() != null && user.getId() > 0 && user.getImage() != null
                    && user.getImage().length() > 0) {
                System.out.println("delete");
                uploadFileService.delete(user.getImage());
            }

            String uniqueFilename = null;
            try {
                uniqueFilename = uploadFileService.copy(file);
            } catch (IOException e) {
                e.printStackTrace();
            }

            flash.addFlashAttribute("info", "Uploaded successfully'" + uniqueFilename + "'");

            user.setImage(uniqueFilename);
        }

        String flashMsg = (user.getId() != null) ? "User edited successfully!" : "User created successfully!";

        userService.save(user);
        status.setComplete();
        flash.addFlashAttribute("success", flashMsg);
        return "redirect:/list";
    }

    @RequestMapping(value="/delete/{id}")
    public String delete(@PathVariable(value="id") Long id, RedirectAttributes flash) {
        if(id > 0) {
            User user = userService.findOne(id);
            userService.delete(id);
            flash.addFlashAttribute("success", "User deleted successfully!");

            if (uploadFileService.delete(user.getImage())) {
                flash.addFlashAttribute("info", "Image " + user.getImage() + " deleted successfully!");
            }
        }
        return "redirect:/list";
    }

    @GetMapping(value = "/uploads/{filename:.+}")
    public ResponseEntity<Resource> viewImage(@PathVariable String filename) {

        Resource resource = null;

        try {
            resource = uploadFileService.load(filename);
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }

        return ResponseEntity.ok()
                .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"" + resource.getFilename() + "\"")
                .body(resource);
    }

    @GetMapping(value = "/view/{id}")
    public String view(@PathVariable(value = "id") Long id, Map<String, Object> model, RedirectAttributes flash) {

        User user = userService.findOne(id);
        if (user == null) {
            flash.addFlashAttribute("error", "This user doesn't exist");
            return "redirect:/list";
        }

        model.put("user", user);
        return "view";
    }
}
