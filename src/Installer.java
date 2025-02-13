import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.time.LocalDateTime;

public class Installer {
    private final String root;
    private final String[] FOLDERS_LIST = { "\\src", "\\src\\main", "\\src\\test", "\\res", "\\res\\drawables", "\\res\\vectors", "\\res\\icons", "\\savegames", "\\temp" };
    private final String[] FILES_LIST = { "\\src\\main\\Main.java", "\\src\\main\\Utils.java" };

    public Installer(String root) {
        this.root = root;
    }

    public String getRoot() {
        return root;
    }

    public void install() {
        StringBuilder logs = new StringBuilder();

        // создадем каталоги
        for (String item : FOLDERS_LIST) {
            File dir = new File(root + item);
            LocalDateTime curDateTime = LocalDateTime.now();
            if (dir.mkdir()) {
                String log = curDateTime + " [INFO]: Каталог " + dir.getName() + " успешно создан\n";
                logs.append(log);
            } else {
                String log = curDateTime + " [ERROR]: Ошибка при создании калалога " + dir.getName() + "\n";
                logs.append(log);
            }
        }

        // создаем файлы
        for (String item : FILES_LIST) {
            File file = new File(root + item);
            LocalDateTime curDateTime = LocalDateTime.now();
            try {
                if (file.createNewFile()) {
                    String log = curDateTime + " [INFO]: Файл " + file.getName() + " успешно создан\n";
                    logs.append(log);
                }
            } catch (IOException e) {
                String log = curDateTime + " [ERROR]: Ошибка при создании файла " + file.getName() + ": " + e.getMessage() + "\n";
                logs.append(log);
            }
        }

        try (FileOutputStream fos = new FileOutputStream(root + "\\temp\\temp.txt")) {
            byte[] bytes = logs.toString().getBytes();
            fos.write(bytes, 0, bytes.length);
        } catch (IOException e) {
            System.out.println("Ошибка при записи логов установки: " + e.getMessage());
        }

        System.out.println("Установка успешно завершена!");
    }
}
