package maowcraft.jumpinthecaac;

import com.sun.jna.Library;
import com.sun.jna.Native;
import com.sun.jna.win32.W32APIOptions;

import javax.sound.sampled.*;
import javax.swing.*;
import java.awt.*;
import java.io.IOException;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

public class Main {
    private static final String USERNAME = System.getProperty("user.name");
    private static final Path MEME_FOLDER = Paths.get("C:/Users/" + USERNAME + "/Desktop/Meme");
    private static final String RUN_DIR = System.getProperty("user.dir");

    public static void main(String[] args) {
        try {
            UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
            playMusic(getResourceURL("/caac.wav"));
            createDesktopFolder("Meme");
            Thread.sleep(3000);
            Desktop.getDesktop().open(MEME_FOLDER.toFile());
            createFile("Gun", false);
            Path girls = createFile("Girls", false);
            Thread.sleep(1000);
            Thread.sleep(1500);
            createFile("Gunshots", false);
            Thread.sleep(1000);
            Files.delete(girls);
            Thread.sleep(1000);
            changeWallpaper("1");
            Thread.sleep(1000);
            changeWallpaper("2");
            Thread.sleep(500);
            changeWallpaper("3");
            Thread.sleep(500);
            changeWallpaper("4");
            Thread.sleep(500);
            changeWallpaper("5");
            Thread.sleep(500);
            infoBox("Jeans", "Baby", JOptionPane.INFORMATION_MESSAGE);
            Thread.sleep(1000);
            changeWallpaper("6");
            Thread.sleep(500);
            changeWallpaper("7");
            Thread.sleep(1000);
            changeWallpaper("8");
            Thread.sleep(1250);
            changeWallpaper("9");
            Path jump = createFile("Jump", true);
            Thread.sleep(2000);
            Path caac = createDesktopFolder("The CAAC");
            Desktop.getDesktop().open(caac.toFile());
            Thread.sleep(500);
            Files.move(jump, Paths.get(caac.toAbsolutePath() + "/Jump"));
            Thread.sleep(1000);
            infoBox("yea", "", JOptionPane.ERROR_MESSAGE);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private static void playMusic(URL path) throws IOException, UnsupportedAudioFileException, LineUnavailableException {
        AudioInputStream audioInputStream = AudioSystem.getAudioInputStream(path);
        Clip clip = AudioSystem.getClip();
        clip.open(audioInputStream);
        clip.start();
    }

    private static Path createDesktopFolder(String name) throws IOException {
        Path path = Paths.get("C:/Users/" + USERNAME + "/Desktop/" + name);
        Files.createDirectory(path);
        return path;
    }

    private static Path createFile(String name, boolean desktop) throws IOException {
        Path path;
        if (desktop) {
            path = Paths.get( "C:/Users/" + USERNAME + "/Desktop/" + name);
        } else {
            path = Paths.get(MEME_FOLDER.toAbsolutePath().toString() + "/" + name);
        }
        Files.createFile(path);
        return path;
    }

    private static URL getResourceURL(String name) {
        return Main.class.getResource(name);
    }

    private static void changeWallpaper(String name) {
        User32.INSTANCE.SystemParametersInfo(0x0014, 0, getWallpaper(name), 1);
    }

    private static String getWallpaper(String name) {
        return RUN_DIR + "\\wallpapers\\" + name + ".png";
    }

    private static void infoBox(String infoMessage, String titleBar, int type) {
        JOptionPane.showMessageDialog(null, infoMessage, titleBar, type);
    }

    // Thanks StackOverflow.
    public interface User32 extends Library {
        User32 INSTANCE = Native.load("user32", User32.class, W32APIOptions.DEFAULT_OPTIONS);
        @SuppressWarnings("UnusedReturnValue") boolean SystemParametersInfo (int one, int two, String s, int three);
    }
}
