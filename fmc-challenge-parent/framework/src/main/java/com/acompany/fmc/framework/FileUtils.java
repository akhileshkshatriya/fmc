package com.acompany.fmc.framework;

import java.io.File;
import java.io.IOException;
import java.lang.annotation.Annotation;
import java.net.JarURLConnection;
import java.net.URL;
import java.util.Enumeration;
import java.util.HashSet;
import java.util.Set;
import java.util.jar.JarEntry;
import java.util.jar.JarFile;

public class FileUtils {
	
	private static final String SEPARATOR = File.separator;
	private static final String CLASS_EXTENSION = ".class";
	private static final String DOT = ".";
	private static final int EXTENSION_STR_LENGTH = 6;
	private static final String JAR_PROTOCOL = "jar";
	
	public static Set<Class<?>> loadClassesFromJar(URL url, String packageName, final Class<? extends Annotation> annotation) throws IOException, ClassNotFoundException{
		Set<Class<?>> classes = new HashSet<>();
		
		JarFile jar = ((JarURLConnection) url.openConnection()).getJarFile();
		Enumeration<JarEntry> e = jar.entries();
		while (e.hasMoreElements()) {
			JarEntry entry = e.nextElement();
			if (entry.getName().endsWith(CLASS_EXTENSION)) {
				if (entry.getName().startsWith(packageName.replace(DOT, SEPARATOR))) {
					String className = entry.getName().substring(0, entry.getName().length() - EXTENSION_STR_LENGTH);
					Class<?> claz = Class.forName(className.replace(SEPARATOR, DOT));
					if ((claz.isAnnotationPresent(annotation))) {
						classes.add(claz);
					}
				}
			}
		}
		return classes;
	}
	
	public static boolean isJar(URL fileURL) {
		if (JAR_PROTOCOL.equals(fileURL.getProtocol())) {
			return true;
		}
		return false;
	}

}
