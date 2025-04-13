package part_3;

class MyPolynomial {
	private double[] coeffs;

	public MyPolynomial(double... coeffs) {
		this.coeffs = coeffs;
	}

	public int getDegree() {
		return coeffs.length - 1;
	}

	@Override
	public String toString() {
		StringBuilder sb = new StringBuilder();
		for (int i = getDegree(); i >= 0; i--) {
			if (i < getDegree())
				sb.append(" + ");
			sb.append(coeffs[i]).append("x^").append(i);
		}
		return sb.toString();
	}

	public double evaluate(double x) {
		double result = 0;
		for (int i = 0; i < coeffs.length; i++) {
			result += coeffs[i] * Math.pow(x, i);
		}
		return result;
	}

	public MyPolynomial add(MyPolynomial right) {
		int maxDegree = Math.max(this.getDegree(), right.getDegree());
		double[] newCoeffs = new double[maxDegree + 1];
		for (int i = 0; i <= maxDegree; i++) {
			double thisCoeff = (i <= this.getDegree()) ? this.coeffs[i] : 0;
			double rightCoeff = (i <= right.getDegree()) ? right.coeffs[i] : 0;
			newCoeffs[i] = thisCoeff + rightCoeff;
		}
		return new MyPolynomial(newCoeffs);
	}

	public MyPolynomial multiply(MyPolynomial right) {
		double[] newCoeffs = new double[this.getDegree() + right.getDegree() + 1];
		for (int i = 0; i <= this.getDegree(); i++) {
			for (int j = 0; j <= right.getDegree(); j++) {
				newCoeffs[i + j] += this.coeffs[i] * right.coeffs[j];
			}
		}
		return new MyPolynomial(newCoeffs);
	}

	public static void main(String[] args) {
		MyPolynomial poly1 = new MyPolynomial(1, 2, 3); // 3x^2 + 2x + 1
		MyPolynomial poly2 = new MyPolynomial(2, 3, 4); // 4x^2 + 3x + 2

		System.out.println("Polynomial 1: " + poly1);
		System.out.println("Polynomial 2: " + poly2);
		System.out.println("Sum: " + poly1.add(poly2));
		System.out.println("Product: " + poly1.multiply(poly2));
		System.out.println("Evaluate poly1 at x=2: " + poly1.evaluate(2));
	}
}
