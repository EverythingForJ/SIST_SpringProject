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

	@GetMapping("/list")
	public String list(Model model) {
		List<QnaVO> list = this.qnaService.readAll();
		//log.info("갯수 = " + list.size());
		model.addAttribute("qnalist", list);
		return "/qna/list";     //WEB-INF/views/qna/list.jsp
	}
	
	@GetMapping("/read")
	public String read(@RequestParam("bno") int bno , Model model) {
		//int bno = Integer.parseInt(request.getParameter("bno"));
		QnaVO qnaVO = this.qnaService.read(bno);
		//log.info(qnaVO.toString());
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
//		log.info(qnaVO.toString());
//		log.info(company);
		String email = qnaVO.getEmail();
		email += "@" + company;
		qnaVO.setEmail(email);
		this.qnaService.update(qnaVO);
		return "redirect:/qna/read?bno=" + qnaVO.getBno();
	}

}
