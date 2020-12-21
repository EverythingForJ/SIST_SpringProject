package com.example.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.example.service.QnaService;
import com.example.vo.QnaVO;

import lombok.extern.java.Log;

@Controller
@RequestMapping("/qna")
@Log
public class QnaController {
	@Autowired
	private QnaService qnaService;

	@GetMapping("/write")
	public String write() {
		return "/qna/write";   //WEB-INF/views/qna/write.jsp
	}
	
	@PostMapping("/write")
	public String write(QnaVO qnaVO, 
							@RequestParam("company") String company) {
		String email = qnaVO.getEmail();
		if(!email.equals("")) {
			email += "@" + company;
			qnaVO.setEmail(email);
		}
		this.qnaService.create(qnaVO);
		return "redirect:/qna/list";
	}
	
	@GetMapping("/list")
	public String list(Model model) {
		List<QnaVO> list = this.qnaService.readAll();
		model.addAttribute("qnalist", list);
		return "/qna/list";     //WEB-INF/views/qna/list.jsp
	}
	
	@GetMapping("/read")
	public String read(@RequestParam("bno") int bno , Model model) {
		QnaVO qnaVO = this.qnaService.read(bno);
		model.addAttribute("qna", qnaVO);
		return "/qna/read";   //WEB-INF/views/qna/read.jsp
	}
	
	@GetMapping("/readnumUpdate/{bno}")
	public void readnumUpdate(@PathVariable int bno) {
		this.qnaService.updateReadnum(bno);
	}
	
	@GetMapping("/delete")
	public String delete(@RequestParam("bno") int bno) {
		this.qnaService.delete(bno);
		return "redirect:/qna/list";
	}
	
	@GetMapping("/update")
	public String update(@RequestParam("bno") int bno, Model model) {
		QnaVO qnaVO = this.qnaService.read(bno);
		model.addAttribute("qna", qnaVO);
		return "/qna/update";    //WEB-INF/views/qna/update.jsp
	}
	
	@PostMapping("/update")
	public String update(QnaVO qnaVO, 
								@RequestParam("company") String company) {
		String email = qnaVO.getEmail();
		email += "@" + company;
		qnaVO.setEmail(email);
		this.qnaService.update(qnaVO);
		return "redirect:/qna/read?bno=" + qnaVO.getBno();
	}

	@GetMapping("/reply")
	public String reply(@RequestParam("bno") int bno, Model model) {
		QnaVO qnaVO = this.qnaService.read(bno);
		model.addAttribute("qna", qnaVO);
		return "/qna/reply";     //WEB-INF/views/qna/reply.jsp
	}
	
	@PostMapping("/reply")
	public String reply(QnaVO qnaVO, 
							@RequestParam("company") String company) {
		String email = qnaVO.getEmail();
		if(!email.equals("")) {
			email += "@" + company;
			qnaVO.setEmail(email);
		}
		this.qnaService.reply(qnaVO);
		return "redirect:/qna/list";
	}
}
