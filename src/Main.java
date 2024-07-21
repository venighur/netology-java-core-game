public class Main {
    public static void main(String[] args) {
        // УСТАНОВКА ИГРЫ
        Installer installer = new Installer("E:\\Games\\");
        installer.install();
        String saveFolder = installer.getRoot() + "savegames\\"; // папка для файлов сохранений

        // СОХРАНЕНИЕ ИГРЫ
        // создаем три экзепляра сохранений
        GameProgress gameProgress1 = new GameProgress(100, 23, 8, 230.44);
        GameProgress gameProgress2 = new GameProgress(200, 35, 10, 125.58);
        GameProgress gameProgress3 = new GameProgress(300, 44, 13, 87.96);

        Saver saver = new Saver();
        // выполняем сохранение каждой игры в отдельный файл
        String save1 = saver.saveGame(saveFolder + "save1.dat", gameProgress1);
        String save2 = saver.saveGame(saveFolder + "save2.dat", gameProgress2);
        String save3 = saver.saveGame(saveFolder + "save3.dat", gameProgress3);

        String[] saves = { save1, save2, save3 }; // массив с файлами сохранений
        // архивируем сохранения
        saver.zipFiles(saveFolder + "zip.zip", saves);
        // удаляем файлы вне архива zip.zip
        saver.clearSaveFolder(saves);

        // ЗАГРУЗКА ИГРЫ
        Loader loader = new Loader();
        // разархивирем архив с сохранениями
        loader.openZip(saveFolder + "zip.zip", saveFolder);
        // получаем данные о состоянии игры из файла сохранения
        GameProgress gameProgressLoaded = loader.openProgress(saveFolder + "save2.dat");
        // выводим данные в консоль
        System.out.println();
        System.out.println("---=== Данные из объекта сохраненной игры gameProgress2 ===---");
        System.out.println(gameProgressLoaded.toString());
    }
}