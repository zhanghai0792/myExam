package com.jm.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.jm.pojo.ClassInfo;
import com.jm.query.ClassInfoQueryParams;
import com.jm.serviceDao.ClassInfoServiceDao;
@Controller
@RequestMapping("/classInfo")
public class ClassInfoController extends actionTemplate<ClassInfo, ClassInfoServiceDao, ClassInfoQueryParams>{

}
