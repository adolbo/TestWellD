/*
 * adolfo.bo@gmail.com
 */
package it.ab.welld.repository;

import it.ab.welld.pojo.PointDto;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Optional;
import java.util.TreeSet;
import javax.annotation.PostConstruct;
import org.springframework.stereotype.Component;

/**
 *
 * @author adolfo
 */
@Component
public class Repository {
	
	private Collection<PointDto> points;
	

	@PostConstruct
	void init() {
		points = getPoints();
	}

	public Collection<PointDto> getPoints() {
		points = Optional.ofNullable(points).orElse(first());
		return points;
	}

	

	public boolean insertPoint(PointDto p) {
		return getPoints().add(p);
	}

	public void deletePoints() {
		getPoints().clear();
	}
	public boolean deletePoint(PointDto p) {
		return getPoints().remove(p);
	}

	private Collection<PointDto> first() {
		Collection<PointDto> result = new TreeSet<>();
		//result.add(new PointDto(1,1));
		//result.add(new PointDto(2,2));
		return result;
	}





	
	
}
