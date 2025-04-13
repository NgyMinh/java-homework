package Bai_tap_2;

import java.util.concurrent.Semaphore;

class TrietGia extends Thread {
	private int id;
	private Semaphore[] dua;
	private Semaphore banAn;

	public TrietGia(int id, Semaphore[] dua, Semaphore banAn) {
		this.id = id;
		this.dua = dua;
		this.banAn = banAn;
	}

	public void run() {
		try {
			while (true) {
				suyNghi();

				banAn.acquire();

				// Lấy đũa bên trái
				dua[id].acquire();
				System.out.println("Triết gia " + id + " đã cầm đũa bên trái.");

				// Lấy đũa bên phải
				dua[(id + 1) % 5].acquire();
				System.out.println("Triết gia " + id + " đã cầm đũa bên phải.");

				// Ăn
				an();

				// Trả đũa bên trái
				dua[id].release();
				System.out.println("Triết gia " + id + " đã đặt đũa bên trái.");

				// Trả đũa bên phải
				dua[(id + 1) % 5].release();
				System.out.println("Triết gia " + id + " đã đặt đũa bên phải.");

				banAn.release();
			}
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}

	private void suyNghi() throws InterruptedException {
		System.out.println("Triết gia " + id + " đang suy nghĩ...");
		Thread.sleep((int) (Math.random() * 1000));
	}

	private void an() throws InterruptedException {
		System.out.println("Triết gia " + id + " đang ăn...");
		Thread.sleep((int) (Math.random() * 1000));
	}

	public static void main(String[] args) {
		Semaphore[] dua = new Semaphore[5];
		Semaphore banAn = new Semaphore(4);

		for (int i = 0; i < 5; i++) {
			dua[i] = new Semaphore(1);
		}

		for (int i = 0; i < 5; i++) {
			new TrietGia(i, dua, banAn).start();
		}
	}
}
