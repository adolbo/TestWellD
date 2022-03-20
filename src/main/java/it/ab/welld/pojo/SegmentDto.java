/*
 * adolfo.bo@gmail.com
 */
package it.ab.welld.pojo;

import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 *
 * @author adolfo
 * parameter for for y=mx+q
 */
@Data
@EqualsAndHashCode(callSuper = false, of={"m","q"})
public class SegmentDto {
	private double m; //angle
	private double q; //constant

	/**
	 * segmant vertical;
	 */
	private boolean vertical;
	/**
	 * constant X
	 */
	private double constantX;
	

	public SegmentDto() {
		this(0,0);
	}
	public SegmentDto(double m, double q) {
		this.m = m;
		this.q = q;
	}

	
}
