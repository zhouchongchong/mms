package cn.d9ing.controller;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import cn.d9ing.domain.Rule;
import cn.d9ing.service.IRuleService;

@Controller
@RequestMapping("/rule")
public class RuleController {
	@Resource
	private IRuleService ruleService;
	@RequestMapping("getrules")
	@ResponseBody
	public List<Rule> getrules(){
		return ruleService.getRules();
	}
	@RequestMapping("getrule")
	@ResponseBody
	public Rule getRule(){
		Rule  r= ruleService.getRule(1);
		return r;
	}
}
