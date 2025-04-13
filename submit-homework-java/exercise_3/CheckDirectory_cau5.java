package Bai_tap_2;
import java.io.File;
import java.util.Scanner;

public class CheckDirectory_cau5 {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Nhập đường dẫn thư mục: ");
        String folderPath = scanner.nextLine();
        scanner.close();

        File folder = new File(folderPath);

        if (folder.exists() && folder.isDirectory()) {
            File[] files = folder.listFiles();
            System.out.println("Thư mục hợp lệ. Danh sách file/thư mục bên trong là:");

            for (File file : files) {
                if (file.isFile()) {
                    System.out.println("[FILE] " + file.getName());
                } else if (file.isDirectory()) {
                    System.out.println("[FOLDER] " + file.getName());
                }
            }
        } else {
            System.out.println("Thư mục không tồn tại. Nhập lại điiii");
        }
    }
}
// nhập đường dẫn
// đọc các file có trong đường dẫn
