package com.rtsim.ui.graphical;

import java.awt.Graphics;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.swing.JFrame;
import javax.swing.JPanel;

import com.rtsim.Scene;
import com.rtsim.engine.Engine;
import com.rtsim.engine.VectorD;
import com.rtsim.engine.graphics.Color;
import com.rtsim.engine.graphics.light.Light;
import com.rtsim.engine.graphics.light.RadialLight;
import com.rtsim.engine.graphics.material.Material;
import com.rtsim.engine.graphics.material.SimpleMaterial;
import com.rtsim.engine.graphics.raytracing.SimpleRaytracer;
import com.rtsim.engine.graphics.raytracing.ThreadedRaytracer;
import com.rtsim.engine.graphics.raytracing.behavior.BodyBehavior;
import com.rtsim.engine.graphics.raytracing.behavior.ReflectBehavior;
import com.rtsim.engine.graphics.view.RectangularProjection;
import com.rtsim.engine.graphics.view.Viewport;
import com.rtsim.engine.physics.body.Body;
import com.rtsim.engine.physics.body.Triangle;
import com.rtsim.engine.settings.Settings;

public class ImageDisplayer extends JPanel {
    private transient Color[][] image;

    public ImageDisplayer(Color[][] image) {
        this.image = image;
    }

    @Override
    public void paintComponent(Graphics g) {
        for (int c = 0; c < image.length; c++) {
            for (int r = 0; r < image[c].length; r++) {
                g.setColor(new java.awt.Color(image[c][r].getRed(), image[c][r].getGreen(), image[c][r].getBlue()));
                g.drawRect(c, r, 1, 1);
            }
        }
    }

    public static String readFile(String path) {
        try { 
            FileReader reader = new FileReader(path);
            StringBuilder text = new StringBuilder();
            while(reader.ready()) {
                text.append((char) reader.read());
            }
            reader.close();
            return text.toString();
        } catch(IOException e) {

        }
        return "";
    }

    public static List<Triangle> parseCSV(String text, Material material, BodyBehavior[] behaviors) {
        List<Triangle> triangles = new ArrayList<>();
        for(String line : text.split("\n")) {
            VectorD[] vertices = new VectorD[3];
            String[] raw = line.split(",");
            for (int v = 0; v < 3; v++) {
                vertices[v] = new VectorD(new double[]{Double.parseDouble(raw[3 * v]) /10, Double.parseDouble(raw[3 * v + 1]) / 10, Double.parseDouble(raw[3 * v + 2]) / 10});

            }
            triangles.add(new Triangle(vertices, material, behaviors));
        }
        return triangles;
    }

    public static void main(String[] args) {
        Viewport viewport = new RectangularProjection(512, 512, 0.2f, 60, 1, new VectorD(new double[] {0, 0, -5}), new VectorD(new double[]{0, 0,Math.PI}));
        Engine engine = new Engine(viewport, new ThreadedRaytracer(viewport, 16));
        BodyBehavior behavior = new ReflectBehavior();
        Map<String, Body> bodies = new HashMap<>();
        List<Triangle> triangles = parseCSV(readFile("/home/sarah/Documents/Projects/RTSim/test.csv"),  new SimpleMaterial(new Color(1, 1, 0), new Color(1, 0,1), 0.3f), new BodyBehavior[] {behavior});
        // for (int i = 0; i < triangles.size(); i++) {
        //     bodies.put("h" + i, triangles.get(i));
        // }
        bodies.put("a", new Triangle(new VectorD[] {
            new VectorD(new double[] {0, 0, 0d}),
            new VectorD(new double[] {0d, 1, 0d}),
            new VectorD(new double[] {1, 1, 2.5d}) 
        }, new SimpleMaterial(new Color(0, 1, 0), new Color(0, 0,1), 0.7f), new BodyBehavior[] {behavior}));
        bodies.put("b", new Triangle(new VectorD[] {
            new VectorD(new double[] {-1, 0, 0d}),
            new VectorD(new double[] {0d, 1d, 0d}),
            new VectorD(new double[] {1, 1, 2.5d}) 
        }, new SimpleMaterial(new Color(1, 0, 0), new Color(0, 0,1), 0.7f), new BodyBehavior[] {behavior}));

        
        Map<String, Light> lights = new HashMap<>();
        lights.put("b", new RadialLight(new VectorD(new double[]{0,0, 4}), new Color(0, 0, 1), 0.5f));
        Scene scene = new Scene(engine, new Settings(), bodies, lights);
        long start = System.currentTimeMillis();
        Color[][] image = scene.getEngine().createImage(scene.getSettings(), bodies.values(), lights.values());
        long duration = System.currentTimeMillis() - start;
        System.out.println(duration / 1000 + "s");
        ImageDisplayer canvas = new ImageDisplayer(image);

        JFrame frame = new JFrame();
        frame.setSize(1280, 768);
        frame.setTitle("Render");
        frame.add(canvas);
        frame.setVisible(true);
    }
    
}
