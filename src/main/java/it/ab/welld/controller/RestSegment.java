/*
 * adolfo.bo@gmail.com
 */
package it.ab.welld.controller;

import it.ab.welld.bo.Service;
import it.ab.welld.pojo.PointDto;
import it.ab.welld.pojo.SegmentDto;
import it.ab.welld.pojo.SegmentWithPointsDto;
import it.ab.welld.repository.Repository;
import java.util.Collection;
import java.util.List;
import java.util.Map;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 *
 * @author adolfo
 */
@RestController
@RequestMapping("/points")
public class RestSegment {
	@Autowired Repository repository;
	@Autowired Service segmentBO;

	@GetMapping("/all")
    public Collection<PointDto> readAll() {
        return repository.getPoints();
    }
	@GetMapping("/segment/{nrPoint}")
	public Collection<SegmentWithPointsDto> allSegmennt(@PathVariable Integer nrPoint) {
		return segmentBO.getAllSegment(repository.getPoints(), nrPoint);
	}


	
	
}
