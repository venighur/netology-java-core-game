public class Main {
    public static void main(String[] args) {
        Installer installer = new Installer();
        installer.install();

        GameProgress gameProgress1 = new GameProgress(100, 23, 8, 230.44);
        GameProgress gameProgress2 = new GameProgress(200, 35, 10, 125.58);
        GameProgress gameProgress3 = new GameProgress(300, 44, 13, 87.96);

        Saver saver = new Saver("E:\\Games\\savegames\\");

        String save1 = saver.saveGame("save1.dat", gameProgress1);
        String save2 = saver.saveGame("save2.dat", gameProgress2);
        String save3 = saver.saveGame("save3.dat", gameProgress3);

        String[] saves = { save1, save2, save3 };
        saver.zipFiles("zip.zip", saves);
        saver.clearSaveFolder(saves);

        Loader loader = new Loader("E:\\Games\\savegames\\");
        loader.openZip("zip.zip", "E:\\Games\\savegames\\");

        GameProgress gameProgressLoaded = loader.openProgress("save2.dat");
        System.out.println(gameProgressLoaded.toString());
    }
}