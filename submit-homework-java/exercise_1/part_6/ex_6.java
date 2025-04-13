package part_6;

abstract class Animal {
	protected String name;

	public Animal(String name) {
		this.name = name;
	}

	public abstract void greets();
}

class Cat extends Animal {
	public Cat(String name) {
		super(name);
	}

	@Override
	public void greets() {
		System.out.println("Meow");
	}
}

class Dog extends Animal {
	public Dog(String name) {
		super(name);
	}

	@Override
	public void greets() {
		System.out.println("Woof");
	}

	public void greets(Dog another) {
		System.out.println("Wooof");
	}
}

class BigDog extends Dog {
	public BigDog(String name) {
		super(name);
	}

	@Override
	public void greets() {
		System.out.println("Wooow");
	}

	@Override
	public void greets(Dog another) {
		System.out.println("Wooooow");
	}

	public void greets(BigDog another) {
		System.out.println("Wooooooooow");
	}
}
