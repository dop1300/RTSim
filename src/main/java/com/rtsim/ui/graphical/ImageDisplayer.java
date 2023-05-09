package com.rtsim.ui.graphical;

import java.awt.Canvas;
import java.awt.Graphics;
import java.util.ArrayList;
import java.util.HashMap;
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
import com.rtsim.engine.graphics.raytracing.behavior.BodyBehavior;
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
                g.drawRect(c, r, 2, 2);
            }
        }
    }

    public static void main(String[] args) {
        Viewport viewport = new RectangularProjection(256, 256, 0.2f, 60, 1, new VectorD(new double[] {0, 0, -5}), new VectorD(new double[]{0,0,0}));
        Engine engine = new Engine(viewport);
        Map<String, Body> bodies = new HashMap<>();
        bodies.put("a", new Triangle(new VectorD[] {
            new VectorD(new double[] {0, 0, 0d}),
            new VectorD(new double[] {0d, 1d, 0d}),
            new VectorD(new double[] {1d, 1d, 0d}) 
        }, new SimpleMaterial(new Color(0, 1, 0), new Color(0, 0,1), 0.7f), new BodyBehavior[0]));
        Map<String, Light> lights = new HashMap<>();
        lights.put("b", new RadialLight(new VectorD(new double[]{0, 2, -2}), new Color(0, 0, 1), 0.5f));
        Scene scene = new Scene(engine, new Settings(), bodies, lights);
        ImageDisplayer canvas = new ImageDisplayer(scene.getEngine().createImage(scene.getSettings(), bodies.values(), lights.values()));

        JFrame frame = new JFrame();
        frame.setSize(1280, 768);
        frame.setTitle("Render");
        frame.add(canvas);
        frame.setVisible(true);
    }
    
}
