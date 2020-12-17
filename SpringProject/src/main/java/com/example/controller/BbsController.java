package com.example.controller;

import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.example.exception.BbsException;
import com.example.service.BbsService;
import com.example.vo.BbsVO;
import com.example.vo.UserVO;

import lombok.extern.java.Log;

@Controller
@Log
@RequestMapping("/bbs")
public class BbsController {
	@Autowired
	private BbsService bbsService;
	
	@GetMapping("/list")
	public String list(Model model) {
		List<BbsVO> list = this.bbsService.readAll();
		model.addAttribute("bbslist", list);
		// list.forEach(bbs -> log.info(bbs.toString()));
		//log.info("글 갯수 = "+list.size());
		return "/bbs/list"; // WEB-INF/views/bbs/list.jsp
	}
	
	@GetMapping("/write")
	public String write(HttpSession session) throws BbsException{
		Object obj = session.getAttribute("userInfo");
		if(obj==null) {
			throw new BbsException("게시판에 글을 쓰시려면 먼저 로그인해 주세요.");
		}
		else return "/bbs/write"; // WEB-INF/views/bbs/write.jsp
	}
	
	@PostMapping("/write")
	public String write1(BbsVO bbsVO, HttpSession session) {
		UserVO userVO = (UserVO)session.getAttribute("userInfo");
		bbsVO.setUserid(userVO.getUserid());
		this.bbsService.create(bbsVO);
		return "redirect:/bbs/list";
	}
}
