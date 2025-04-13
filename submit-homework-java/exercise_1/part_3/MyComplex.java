package part_3;

public class MyComplex {
	private double real;
	private double imag;

	// Default constructor
	public MyComplex() {
		this.real = 0.0;
		this.imag = 0.0;
	}

	// Constructor with parameters
	public MyComplex(double real, double imag) {
		this.real = real;
		this.imag = imag;
	}

	// Getters and Setters
	public double getReal() {
		return real;
	}

	public void setReal(double real) {
		this.real = real;
	}

	public double getImag() {
		return imag;
	}

	public void setImag(double imag) {
		this.imag = imag;
	}

	public void setValue(double real, double imag) {
		this.real = real;
		this.imag = imag;
	}

	@Override
	public String toString() {
		return real + "+" + imag + "i";
	}

	public boolean isReal() {
		return imag == 0;
	}

	public boolean isImaginary() {
		return real == 0;
	}

	public boolean equals(double real, double imag) {
		return this.real == real && this.imag == imag;
	}

	public boolean equals(MyComplex another) {
		return this.real == another.real && this.imag == another.imag;
	}

	public double magnitude() {
		return Math.sqrt(real * real + imag * imag);
	}

	public MyComplex addInto(MyComplex right) {
		this.real += right.real;
		this.imag += right.imag;
		return this;
	}

	public MyComplex addNew(MyComplex right) {
		return new MyComplex(this.real + right.real, this.imag + right.imag);
	}

	public static void main(String[] args) {
		MyComplex num1 = new MyComplex(3.1, 4.05);
		MyComplex num2 = new MyComplex(1.5, 2.5);

		System.out.println("Number 1: " + num1);
		System.out.println("Number 2: " + num2);
		System.out.println("Magnitude of num1: " + num1.magnitude());
		System.out.println("Is num1 real? " + num1.isReal());
		System.out.println("Is num1 imaginary? " + num1.isImaginary());

		MyComplex sum = num1.addNew(num2);
		System.out.println("Sum (new instance): " + sum);

		num1.addInto(num2);
		System.out.println("Sum (modified num1): " + num1);
	}
}
