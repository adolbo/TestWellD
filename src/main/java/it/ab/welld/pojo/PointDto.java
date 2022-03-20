/*
 * adolfo.bo@gmail.com
 */
package it.ab.welld.pojo;

import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 *
 * @author adolfo
 */
@EqualsAndHashCode(callSuper = false, of={"x", "y"})
@Data
public class PointDto implements Comparable<PointDto>{

	private double x;
	private double y;

	public PointDto() {
		this(0,0);
	}
	
	public PointDto(double x, double y) {
		this.x=x;
		this.y=y;
	}

	@Override
	public int compareTo(PointDto o) {
		if(o ==null ) {
			return -1;
		}
		return x==o.x ? Double.compare(y, o.y) :  Double.compare(x, o.x);
	}

}
