/*
 * adolfo.bo@gmail.com
 */
package it.ab.welld.controller;

import it.ab.welld.pojo.PointDto;
import it.ab.welld.repository.Repository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 *
 * @author adolfo
 */
@Controller
public class Segment {
	
	@Autowired 
	Repository repository;

	@RequestMapping(value = "/add", method = RequestMethod.POST)
    public String point(Model model, @ModelAttribute("point") PointDto point) {
        model.addAttribute("pointDto", point);
		repository.insertPoint(point);
		return "index";
    }
	@RequestMapping(value = "/", method = RequestMethod.GET)
    public String point(Model model) {
        model.addAttribute("pointDto", getPoint(model.getAttribute("point")));

        return "index"; 
    }
	@RequestMapping(value = "/deleteall", method = RequestMethod.DELETE)
    public String deleteAll(Model model) {
		model.addAttribute("pointDto", getPoint(model.getAttribute("point")));
		repository.deletePoints();


        return "index"; 
    }

	private PointDto getPoint(Object attribute) {
		return (attribute instanceof PointDto p)  ? p : new PointDto(2,3);
	}
}
