import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.util.zip.ZipEntry;
import java.util.zip.ZipInputStream;

public class Loader {
    private final String saveFolder;

    public Loader(String saveFolder) {
        this.saveFolder = saveFolder;
    }

    public void openZip(String file, String folder) {
        try (ZipInputStream zin = new ZipInputStream(new FileInputStream(saveFolder + file))) {
            ZipEntry entry;
            String name;
            while ((entry = zin.getNextEntry()) != null) {
                name = entry.getName();

                FileOutputStream fout = new FileOutputStream(folder + name);
                for (int c = zin.read(); c != -1; c = zin.read()) {
                    fout.write(c);
                }
                fout.flush();
                zin.closeEntry();
                fout.close();
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    public GameProgress openProgress(String file) {
        GameProgress gameProgress = null;

        try (FileInputStream fis = new FileInputStream(saveFolder + file)) {
            ObjectInputStream ois = new ObjectInputStream(fis);
            gameProgress = (GameProgress) ois.readObject();
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }

        return gameProgress;
    }
}
