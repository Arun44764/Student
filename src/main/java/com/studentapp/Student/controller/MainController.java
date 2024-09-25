package com.studentapp.Student.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.studentapp.Student.Repository.StudentInfoRepo;
import com.studentapp.Student.dto.StudentInfoDto;
import com.studentapp.Student.model.StudentInfo;

@Controller
public class MainController {

	@Autowired
	StudentInfoRepo stdrepo;

	@GetMapping("/home")
	public String showIndex(Model model) {
		List<StudentInfo> slist = stdrepo.findAll();
		model.addAttribute("slist", slist);
		return "index";
	}

	@GetMapping("/home/delete")
	public String Deletestd(@RequestParam int id) {
		StudentInfo stdid = stdrepo.findById(id).get();
		stdrepo.delete(stdid);
		return "redirect:/home";
	}

	@GetMapping("/home/edit")
	public String ShowEditStudentInfo(@RequestParam int id, Model model) {
		Optional<StudentInfo> opestdinfo = stdrepo.findById(id);
		if (opestdinfo.isPresent()) {
			model.addAttribute("opestdinfo", opestdinfo);
			return "editstd";
		} else {
			return "return:/";
		}
	}

	@PostMapping("/home/edit")
	public String UpdateStudent(@ModelAttribute("opestdinfo") StudentInfo studentinfo) {
		StudentInfo std = stdrepo.findById(studentinfo.getStdid()).get();
		std.setStdid(studentinfo.getStdid());
		std.setBranch(studentinfo.getBranch());
		std.setCourse(studentinfo.getCourse());
		std.setName(studentinfo.getName());
		std.setYear(studentinfo.getYear());
		return "redirect:/";

	}

	@GetMapping("/register")
	public String ShowRegistration(Model model) {
		StudentInfoDto dto = new StudentInfoDto();
		model.addAttribute("dto", dto);
		return "registration";
	}

	@PostMapping("/register")
	public String doRegistration(@ModelAttribute StudentInfoDto dto) {
		StudentInfo s = new StudentInfo();
		s.setStdid(dto.getStdid());
		s.setBranch(dto.getBranch());
		s.setCourse(dto.getCourse());
		s.setName(dto.getName());
		s.setYear(dto.getYear());
		stdrepo.save(s);
		return "redirect:/";

	}

}
