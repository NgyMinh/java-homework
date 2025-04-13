package part_8;

public class MyIntStack {
	private int[] contents;
	private int tos;

	public MyIntStack(int capacity) {
		contents = new int[capacity];
		tos = -1;
	}

	public boolean push(int element) {
		if (isFull()) {
			expandCapacity();
		}
		contents[++tos] = element;
		return true;
	}

	public int pop() {
		if (isEmpty()) {
			throw new IllegalStateException("Stack is empty!");
		}
		return contents[tos--];
	}

	public int peek() {
		if (isEmpty()) {
			throw new IllegalStateException("Stack is empty!");
		}
		return contents[tos];
	}

	public boolean isEmpty() {
		return tos < 0;
	}

	public boolean isFull() {
		return tos == contents.length - 1;
	}

	private void expandCapacity() {
		int newCapacity = contents.length * 2;
		int[] newContents = new int[newCapacity];
		System.arraycopy(contents, 0, newContents, 0, contents.length);
		contents = newContents;
	}
}

class TestMyIntStack {
	public static void main(String[] args) {
		MyIntStack stack = new MyIntStack(5);

		for (int i = 1; i <= 10; i++) {
			stack.push(i);
		}

		System.out.println("Peek: " + stack.peek());

		while (!stack.isEmpty()) {
			System.out.println("Pop: " + stack.pop());
		}

		try {
			stack.pop();
		} catch (IllegalStateException e) {
			System.out.println("Exception: " + e.getMessage());
		}
	}
}
