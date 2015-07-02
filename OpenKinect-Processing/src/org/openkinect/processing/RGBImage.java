package org.openkinect.processing;

import java.nio.ByteBuffer;
import processing.core.PImage;

public class RGBImage {

	public static void data(ByteBuffer data, PImage img, boolean irMode) {
		if (irMode) {
			for(int y=0; y<img.height; y++) {
				for(int x=0; x<img.width; x++) {
					int offset = x+y*img.width;
					int d = data.get(offset);
					int pixel = (0xFF) << 24
							| (d & 0xFF) << 16
							| (d & 0xFF) << 8
							| (d & 0xFF) << 0;
					img.pixels[offset] = pixel;
				}
			}
		} else {
			for(int y=0; y<img.height; y++) {
				for(int x=0; x<img.width; x++) {
					int offset = 3*(y*img.width+x);

					int r = data.get( offset+2 ) & 0xFF;
					int g = data.get( offset+1 ) & 0xFF;
					int b = data.get( offset+0 ) & 0xFF;

					int pixel = (0xFF) << 24
							| (b & 0xFF) << 16
							| (g & 0xFF) << 8
							| (r & 0xFF) << 0;
					img.pixels[x+img.width*y] = pixel;
				}
			}
		}
		img.updatePixels();

	}
}
