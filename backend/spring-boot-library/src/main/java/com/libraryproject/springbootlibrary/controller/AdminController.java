package com.libraryproject.springbootlibrary.controller;

import com.libraryproject.springbootlibrary.controller.model.AddBookRequest;
import com.libraryproject.springbootlibrary.service.AdminService;
import com.libraryproject.springbootlibrary.utils.ExtractJWT;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@CrossOrigin("http://localhost:3000")
@RestController
@RequestMapping("/api/admin")
public class AdminController {

    private AdminService adminService;

    @Autowired
    public AdminController(AdminService adminService) {
        this.adminService = adminService;
    }

    @PostMapping("/secure/add/book")
    public void postBook(@RequestHeader(value = "Authorization") String token,
                         @RequestBody AddBookRequest addBookRequest) throws Exception {
        String admin = ExtractJWT.payloadExtraction(token, "\"userType\"");

        if (admin == null || !admin.equals("admin")) {
            throw new Exception("Administration page only.");
        }

        adminService.postBook(addBookRequest);
    }
}
