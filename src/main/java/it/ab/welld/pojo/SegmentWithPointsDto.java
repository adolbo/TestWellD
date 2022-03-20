/*
 * adolfo.bo@gmail.com
 */
package it.ab.welld.pojo;

import java.util.Collection;
import lombok.Data;

/**
 *
 * @author adolfo
 */
@Data
public class SegmentWithPointsDto {
	private SegmentDto segment;
	private Collection<PointDto> points;

	public SegmentWithPointsDto() {
		this(null,null);
	}

	
	public SegmentWithPointsDto(SegmentDto segment, Collection<PointDto> points) {
		this.segment = segment;
		this.points = points;
	}

	
}
