import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.ObjectOutputStream;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;

public class Saver {
    public String saveGame(String folder, GameProgress data) {
        try (FileOutputStream fos = new FileOutputStream(folder);
             ObjectOutputStream oos = new ObjectOutputStream(fos)) {
            oos.writeObject(data);
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }

        return folder;
    }

    public void zipFiles(String file, String[] list) {
        try (ZipOutputStream zout = new ZipOutputStream(new FileOutputStream(file))) {
            for (String save : list) {
                try (FileInputStream fis = new FileInputStream(save)) {
                    String[] elems = save.split("\\\\");
                    ZipEntry entry = new ZipEntry(elems[elems.length - 1]);
                    zout.putNextEntry(entry);


                    byte[] buffer = new byte[fis.available()];
                    fis.read(buffer);

                    zout.write(buffer);
                    zout.closeEntry();
                } catch (Exception e) {
                    System.out.println(e.getMessage());
                }
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    public void clearSaveFolder(String[] list) {
        for (String save : list) {
            File file = new File(save);
            if (!file.delete()) {
                System.out.println("Ошибка удаления файла " + file.getName());
            }
        }
    }
}
