package utilities;
// mutable 2D vectors
public final class Vector2D {
public double x, y;

// constructor for zero vector
public Vector2D() {
	this.x=0;
	this.y=0;
	}

// constructor for vector with given coordinates
public Vector2D(double x, double y) {
	this.x = x;
	this.y = y;
	}

// constructor that copies the argument vector 
public Vector2D(Vector2D v) {
	this.x=v.x;
	this.y=v.y;
	
}

// set coordinates
public Vector2D set(double x, double y) {
	this.x=x;
	this.y=y;
	
	return this;
}

// set coordinates based on argument vector 
public Vector2D set(Vector2D v) {
	this.x = v.x;
	this.y = v.y;
	
	return this;
}

// compare for equality (note Object type argument)
//could be done with a hash, dont know if it would be more efficient.
@Override
public boolean equals(Object o) {
	if (o instanceof Vector2D){
		Vector2D oVector = (Vector2D)o;
		if (this.x == oVector.x )
			if(this.y==oVector.y) return true;
			else return false;
		else return false;
	}
	else return false;
		
} 

// String for displaying vector as text 
@Override
public String toString() {
	return "Vector " + (char)this.x +", "+ (char)this.y +".";
}
		
//  magnitude (= "length") of this vector 
public double mag() {
	double mag = Math.sqrt( (this.x*this.x + this.y*this.y) );
	return mag;
}

// angle between vector and horizontal axis in radians
// can be calculated using Math.atan2 
public double angle() {
	double x = this.x;
	double y = this.y;
	
	return Math.atan2(y, x);
}

// angle between this vector and another vector
// take difference of angles, add 2*Math.PI if result is negative 
public double angle(Vector2D other) {
	double ang1, ang2, angdif;
	ang1 = this.angle();
	ang2 = other.angle();
	angdif = ang2-ang1;
	if (angdif>=0) return angdif;
	else return (angdif + 2 * Math.PI);
}

// add argument vector 
public Vector2D add(Vector2D v) {
	this.x += v.x;
	this.y += v.y;
	
	return this;
}

// add values to coordinates 
public Vector2D add(double x, double y) {
	this.x+=x;
	this.y+=y;
	
	return this;
}

// weighted add - surprisingly usef1ul
public Vector2D addScaled(Vector2D v, double fac) {
	this.x += fac * v.x;
	this.y += fac * v.y;
	
	return this;
}

// subtract argument vector 
public Vector2D subtract(Vector2D v) {
	this.x -= v.x;
	this.y -= v.y;
	
	return this;
}

// subtract values from coordinates 
public Vector2D subtract(double x, double y) {
	this.x -= x;
	this.y -= y;
	 
	return this;
}

// multiply with factor 
public Vector2D mult(double fac) {
	this.x *=fac; 
	this.y *=fac;
	
	return this;
}

// rotate by angle given in radians 
public Vector2D rotate(double angle) {
	//angle = Math.toDegrees(angle);
	double x,y;
	x = this.x;
	y = this.y;
	
	this.x = x * Math.cos(angle) - y * Math.sin(angle);
	this.y = x * Math.sin(angle) + y * Math.cos(angle);
	
	return this;
}

// "dot product" ("scalar product") with argument vector 
public double dot(Vector2D v) {
	double xx,yy;
	xx= this.x * v.x;
	yy= this.y * v.y;
	
	return xx+yy;
}

// distance to argument vector 
public double dist(Vector2D v) {
	double difx, dify, totdif;
	difx = this.x-v.x;
	dify = this.y-v.y;
	totdif = Math.pow(difx, 2) + Math.pow(dify, 2);
	
	return Math.sqrt(totdif);
	
}

// normalise vector so that magnitude becomes 1 
public Vector2D normalise() {
	double length = this.mag();
	if(length>1){
		this.x /=length;
		this.y /=length;
		return this.normalise();
	}else
	return this;
	
}

// wrap-around operation, assumes w> 0 and h>0
public Vector2D wrap(double w, double h) {
	Vector2D TL = new Vector2D(0,0);
	Vector2D BR = new Vector2D(w,h);
	
	if(x > BR.x)  {
		x = TL.x + (x - BR.x);
	}

	else if(x < TL.x)  {
		x = BR.x + x;
	}
	if(y < TL.y)  {
		y = BR.y + y;
	}

	else if(y > BR.y)  {
		y = TL.y + (y - BR.y);
	}
	return this;
}

// construct vector with given polar coordinates  
public static Vector2D polar(double angle, double mag) {
	double x,y;
	x = mag * Math.cos(angle);
	y = mag * Math.sin(angle);
	
	return new Vector2D(x,y);
}

}