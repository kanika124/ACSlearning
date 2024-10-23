package com.example.LoginPage.Asn.controller;

import com.example.LoginPage.Asn.dto.NoticeInfoDto;
import com.example.LoginPage.Asn.entity.AsnInfo;
import com.example.LoginPage.Asn.impl.AsnImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/asn")
public class AsnController {
    @Autowired
    AsnImpl asn;
    @PostMapping("/createAsn")
    public AsnInfo createAsn(@RequestBody NoticeInfoDto noticeInfo){
        return asn.createAsn(noticeInfo);
    }
}
