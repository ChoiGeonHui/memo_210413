package com.memo.timeline;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class TimelineController {
	
	@RequestMapping("/timeline")
	public String timeline(Model model) {
		model.addAttribute("viewName", "timeline/timeline");
		return "/templete/layout";
	}

}
