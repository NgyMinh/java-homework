package part_4;

class Animal {
	private String name;

	public Animal(String name) {
		this.name = name;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	@Override
	public String toString() {
		return "Animal[name=\"" + name + "\"]";
	}
}

//Lớp Mammal kế thừa từ Animal
class Mammal extends Animal {
	public Mammal(String name) {
		super(name);
	}

	@Override
	public String toString() {
		return "Mammal[" + super.toString() + "]";
	}
}

//Lớp Cat kế thừa từ Mammal
class Cat extends Mammal {
	public Cat(String name) {
		super(name);
	}

	public void greets() {
		System.out.println("Meow");
	}

	@Override
	public String toString() {
		return "Cat[" + super.toString() + "]";
	}
}

//Lớp Dog kế thừa từ Mammal
class Dog extends Mammal {
	public Dog(String name) {
		super(name);
	}

	public void greets() {
		System.out.println("Woof");
	}

	public void greets(Dog another) {
		System.out.println("Woooof");
	}

	@Override
	public String toString() {
		return "Dog[" + super.toString() + "]";
	}

	public static void main(String[] args) {
		Animal animal = new Animal("Generic Animal");
		System.out.println(animal);

		Mammal mammal = new Mammal("Generic Mammal");
		System.out.println(mammal);

		Cat cat = new Cat("Kitty");
		System.out.println(cat);
		cat.greets();

		Dog dog = new Dog("Buddy");
		System.out.println(dog);
		dog.greets();

		Dog anotherDog = new Dog("Rex");
		dog.greets(anotherDog);
	}
}
