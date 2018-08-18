package cn.e3mall.item.controller;

import freemarker.template.Configuration;
import freemarker.template.Template;
import freemarker.template.TemplateException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.view.freemarker.FreeMarkerConfigurer;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.Writer;
import java.util.HashMap;
import java.util.Map;

/**
 * 生成静态页面测试
 *
 * @author VicterTian
 * @version V1.0
 * @Date 2018/8/17
 */
@Controller
public class HtmlGenController {
	@Autowired
	private FreeMarkerConfigurer freeMarkerConfigurer;

	@RequestMapping("/genhtml")
	@ResponseBody
	public String genHtml() throws IOException, TemplateException {

		Configuration configuration = freeMarkerConfigurer.getConfiguration();
		//加载模板对象
		Template template = configuration.getTemplate("hello.ftl");
		Map data = new HashMap<>(10);
		data.put("hello",12345);
		//指定文件输出的路径和文件名
		Writer writer = new FileWriter(new File("F:\\IDEA\\e3\\e3-item-web\\src\\main\\webapp\\WEB-INF\\ftl\\hello2.html"));
		//输出文件
		template.process(data,writer);
		//关闭流
		writer.close();
		return "ok";
	}
}
