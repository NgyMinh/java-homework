package part_2;

public class Main {
	static class Author { 
		private String name;
		private String email;
		private char gender;

		public Author(String name, String email, char gender) {
			this.name = name;
			this.email = email;
			this.gender = gender;
		}

		@Override
		public String toString() {
			return "Author[name=" + name + ",email=" + email + ",gender=" + gender + "]";
		}
	}

	static class Book { 
		private String name;
		private Author author;
		private double price;
		private int qty = 0;

		public Book(String name, Author author, double price, int qty) {
			this.name = name;
			this.author = author;
			this.price = price;
			this.qty = qty;
		}

		@Override
		public String toString() {
			return "Book[name=" + name + "," + author.toString() + ",price=" + price + ",qty=" + qty + "]";
		}
	}

	public static void main(String[] args) {
		Author author = new Author("J.K. Rowling", "jkrowling@gmail.com", 'f');
		Book book = new Book("Harry Potter", author, 39.99, 10);

		System.out.println(author);
		System.out.println(book);
	}
}
