package com.acompany.fmc.view;

import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.image.BufferedImage;
import java.io.IOException;

public class ViewUtil {

	private static final String ANSI_RESET = "  \u001B[0m";
	private static final String ANSI_BLACK = "\u001B[30m ";
	private static final String ANSI_RED = "\u001B[31m ";
	private static final String ANSI_GREEN = "\u001B[32m ";
	private static final String ANSI_YELLOW = "\u001B[33m ";
	public static final String ANSI_BLUE = "\u001B[34m ";
	private static final String ANSI_PURPLE = "\u001B[35m ";
	private static final String ANSI_CYAN = "\u001B[36m ";
	private static final String ANSI_WHITE = "\u001B[37m ";
	
	
	
	public static void displayASCII(String title, int size) throws IOException {

		int width = 100;
		int height = 30;

		BufferedImage image = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);
		Graphics g = image.getGraphics();
		g.setFont(new Font("SansSerif", Font.BOLD, size));

		Graphics2D graphics = (Graphics2D) g;
		graphics.setRenderingHint(RenderingHints.KEY_TEXT_ANTIALIASING, RenderingHints.VALUE_TEXT_ANTIALIAS_ON);
		graphics.drawString(title, 10, 20);

		for (int y = 0; y < height; y++) {
			StringBuilder sb = new StringBuilder();
			for (int x = 0; x < width; x++) {

				sb.append(image.getRGB(x, y) == -16777216 ? " " : "$");

			}

			if (sb.toString().trim().isEmpty()) {
				continue;
			}

			System.out.println(sb);
		}
	}
	
	public static void displayErrorMessage(String message){
		System.out.println();
		
		System.out.println(ANSI_RED+message+ANSI_RESET);
		System.out.println();
	}
	
	public static void displayConsoleTextIn(String ansicode){
		System.out.println(ansicode);
	}
	
	public static void resetConsoleText(){
		System.out.println(ANSI_RESET);
	}
}
