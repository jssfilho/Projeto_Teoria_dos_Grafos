package algoritmosemgrafos.controller;

import java.awt.AlphaComposite;
import java.awt.Color;
import java.awt.Frame;
import java.awt.Graphics2D;
import java.awt.SplashScreen;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintStream;
import javax.swing.JOptionPane;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;

/**
 *
 * @author joseph
 */
class ConfigController extends Frame {

    private File dirFile;
    private File configFile;
    
    private String lastDir;
    private String lastLook;
    
    final SplashScreen splash = SplashScreen.getSplashScreen();
    private Graphics2D g;
    
    private int step = 0;
    
    public ConfigController() {
        
        startSplash();
        initConfig();
        stopSplashScreen();
        
    }
    
    private void initConfig() {
        renderSplashFrame("Universidade Federal do Ceará");
        renderSplashFrame("Campus Avançado de Sobral");
        renderSplashFrame("Curso de Engenharia da Computação");
        renderSplashFrame("Disciplina de Algoritmos em Grafos");
        renderSplashFrame("Professora: Andréa Linhares");
        renderSplashFrame("Autor: Joseph Soares Alcântara");
        renderSplashFrame("Estudante do sétimo período");
        
        dirFile = new File(System.getProperty("java.io.tmpdir") + File.separatorChar + "Grafos");
        
        if (!dirFile.exists()) {
            renderSplashFrame("Creating config directory");
            System.out.println("Creating " + dirFile.getAbsolutePath() + " directory");
            
            if (dirFile.mkdir())
                System.out.println("Success");
            else
                System.out.println("Erro trying to create " + dirFile.getAbsolutePath() + " directory");
        }
        
        configFile = new File(dirFile.getAbsolutePath() + File.separatorChar + "config.txt");
        
        if (!configFile.exists()) {
            try {
                renderSplashFrame("Creating config file");
                System.out.println("Creating " + configFile.getAbsolutePath() + " file");
                if (configFile.createNewFile()) {
                    System.out.println("Success");
                } else {
                    System.out.println("Erro trying to create " + configFile.getAbsolutePath() + " file");
                }
                
                PrintStream print = new PrintStream(configFile);
                lastDir = System.getProperty("user.home");
                print.println(lastDir); // dir

                lastLook = UIManager.getSystemLookAndFeelClassName();
                print.println(lastLook); // look
                
            } catch (IOException ex) {
                JOptionPane.showMessageDialog(null, ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
            }
        } else {
            String line;
            try {
                renderSplashFrame("Reading config file");
                BufferedReader in;
                
                in = new BufferedReader(new FileReader(configFile));
                line = in.readLine();
                lastDir = line;
                
                line = in.readLine();
                lastLook = line;
            } catch (FileNotFoundException ex) {
                JOptionPane.showMessageDialog(null, ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
            } catch (IOException ex) {
                JOptionPane.showMessageDialog(null, ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
            }
        }
        
        try {
            renderSplashFrame("Setting Look and Feel");
            UIManager.setLookAndFeel(lastLook);
        } catch (ClassNotFoundException ex) {
            System.err.println(ex.getMessage());
        } catch (IllegalAccessException ex) {
            System.err.println(ex.getMessage());
        } catch (InstantiationException ex) {
            System.err.println(ex.getMessage());
        } catch (UnsupportedLookAndFeelException ex) {
            System.err.println(ex.getMessage());
        }
        
        renderSplashFrame("initializing");
    }
    
    private void startSplash() {
        // splash screen
        if (splash == null) {
            System.out.println("SplashScreen.getSplashScreen() returned null");
            return;
        }
        
        g = splash.createGraphics();
        if (g == null) {
            System.out.println("g is null");
            return;
        }
    }
    
    void renderSplashFrame(String text) {
        if (g == null)
            return;
        
        g.setComposite(AlphaComposite.Clear);
        g.fillRect(0,0,430,300);
        g.setPaintMode();
        g.setColor(Color.WHITE);
        g.drawString("Loading "+ text +"...", 20, 280);
        
        splash.update();
        
        try {
            Thread.sleep(500);
        }
        catch(InterruptedException e) {
        }
    }
    
    private void stopSplashScreen() {
        if (splash == null)
            return;
        
        splash.close();
    }
    
    public void setLastDir(String absolutePath) {
        lastDir = absolutePath;
        PrintStream print;
        try {
            print = new PrintStream(configFile);
            
            print.println(lastDir);
            print.println(lastLook);
            
        } catch (FileNotFoundException ex) {
            JOptionPane.showMessageDialog(null, ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    public String getLastDir() {
        return lastDir;
    }

    public void setLastLook(String actionCommand) {
        lastLook = actionCommand;
        PrintStream print;
        try {
            print = new PrintStream(configFile);
            
            print.println(lastDir);
            print.println(lastLook);
            
        } catch (FileNotFoundException ex) {
            JOptionPane.showMessageDialog(null, ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        }
    }
}
