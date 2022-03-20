/*
 * adolfo.bo@gmail.com
 */
package it.ab.welld.bo;

import it.ab.welld.pojo.PointDto;
import it.ab.welld.pojo.SegmentDto;
import it.ab.welld.pojo.SegmentWithPointsDto;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import org.springframework.stereotype.Component;

/**
 *
 * @author adolfo
 */
@Component
public class Service {

	/**
	 * trOva le rette con i punti appartenenti ad essa
	 * @param points punti da processare
	 * @param nrPoint numero minimo di punti presenti per retta
	 * @return Mappa rette con  suoi punti di appartenenza
	 */
	
	public Collection<SegmentWithPointsDto> getAllSegment(Collection<PointDto> points, Integer nrPoint) {
		if (points==null || points.size()<=1 || nrPoint==null || nrPoint<2) {
			return new ArrayList<>();
		}
		List<SegmentDto> presentSegment = getPresentSegment(points); 	
		Collection<SegmentWithPointsDto>  result = findPointIntoSegment(presentSegment, points);
		result.removeIf(segments -> segments.getPoints()==null || segments.getPoints().size()<nrPoint);
		return result;
	}

	/**
	 * Trova tutte le rette passanti in almeno due punti
	 * 
	 * @param points punti in esame
	 * @return 
	 */		
	private List<SegmentDto> getPresentSegment(Collection<PointDto> points) {
		List<SegmentDto> result = new ArrayList<>();
		points.stream()
			.forEach(p1 -> { points.stream()
								.filter(p2 -> p2.compareTo(p1)>0)
								.forEach(p2 -> result.add(getSegment(p1,p2)));
							});
		return result;
	}

	/**
	 * Crea la retta passante per due punti 
	 * @param p1
	 * @param p2
	 * @return 
	 */
	private SegmentDto getSegment(PointDto p1, PointDto p2) {
		if (p1.getX()==p2.getX()) {
			SegmentDto segmentDto = new SegmentDto();
			segmentDto.setVertical(true);
			segmentDto.setConstantX(p1.getX());
			return segmentDto;
		}
		// y = mx +q
		// m = (p2.y-p1.y)/(p2.x-p1.x)
		// q = p1.y - m p1.x
		double m = (p2.getY()-p1.getY())/(p2.getX()-p1.getX());
		double q = p1.getY() - (m*p1.getX());

		return new SegmentDto(m,q);
	}

	private Collection<SegmentWithPointsDto> findPointIntoSegment(List<SegmentDto> presentSegment, Collection<PointDto> points) {
		Collection<SegmentWithPointsDto>  result = new HashSet<>();
		presentSegment.stream()
			.filter(segment -> segment!=null)
			.forEach(segment -> result.add(new SegmentWithPointsDto(segment, findPoint(segment, points))));
		return result;
	}

	/**
	 * Trova i punti passante per la retta
	 * @param segment retta
	 * @param pointspunti da processare
	 * @return 
	 */
	private  List<PointDto> findPoint(SegmentDto segment, Collection<PointDto> points) {
		return points.stream()
			.filter (p -> segment.isVertical() ?
								segment.getConstantX()==p.getX() :
								p.getY()==Math.round( (p.getX()*segment.getM()) + segment.getQ())
					)
			.collect(Collectors.toList());
		
	}
	
}
