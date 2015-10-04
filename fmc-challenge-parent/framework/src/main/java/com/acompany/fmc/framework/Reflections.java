package com.acompany.fmc.framework;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.lang.annotation.Annotation;
import java.lang.reflect.Method;
import java.net.URL;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.acompany.fmc.framework.annotation.RequetMapping;
import com.acompany.fmc.framework.annotation.ViewMapping;

public class Reflections {

	private final String COMPONENT_SCAN;
	private final String SEPARATOR = File.separator;
	private final String CLASS_EXTENSION = ".class";
	private final String DOT = ".";
	private final int EXTENSION_STR_LENGTH = 6;

	private static Logger LOGGER = LoggerFactory.getLogger(Reflections.class);
	
	public Reflections(final String componentScan) throws IOException {
		LOGGER.info("Inside Reflection");
		this.COMPONENT_SCAN = componentScan;
	}

	public Set<Class<?>> getTypesAnnotatedWith(final Class<? extends Annotation> annotation)
			throws ClassNotFoundException, FileNotFoundException, IOException {
		Set<Class<?>> classes = new HashSet<>();

		ClassLoader classLoader = Thread.currentThread().getContextClassLoader();
		String path = COMPONENT_SCAN.replace(DOT, SEPARATOR);
		Enumeration<URL> resources = classLoader.getResources(path);
		List<File> dirs = new ArrayList<File>();
		while (resources.hasMoreElements()) {
			URL resource = resources.nextElement();
			dirs.add(new File(resource.getFile()));
			classes.addAll(getTypesAnnotatedWith(annotation, resource, COMPONENT_SCAN));
		}
		return classes;
	}

	private Set<Class<?>> getTypesAnnotatedWith(final Class<? extends Annotation> annotation, URL url,
			String packageName) throws ClassNotFoundException, FileNotFoundException, IOException {
		Set<Class<?>> classes = new HashSet<>();

		if (FileUtils.isJar(url)) {
			classes.addAll(FileUtils.loadClassesFromJar(url, packageName, annotation));
		} else {

			File directory = new File(url.getFile());
			if (!directory.exists()) {
				return classes;
			}
			File[] files = directory.listFiles();
			for (File file : files) {
				URL subURL = file.toURI().toURL();
				if (file.isDirectory()) {
					classes.addAll(getTypesAnnotatedWith(annotation, subURL, packageName + DOT + file.getName()));
				} else if (file.getName().endsWith(CLASS_EXTENSION)) {
					Class<?> claz = Class.forName(packageName + DOT
							+ file.getName().substring(0, file.getName().length() - EXTENSION_STR_LENGTH));
					if ((claz.isAnnotationPresent(annotation))) {
						classes.add(claz);
					}
				}
			}
		}
		return classes;
	}

	public Map<String, Method> getMethodsAnnotatedWithRequestMapping(final Class<?> type) {
		Map<String, Method> map = new HashMap<>();
		Class<?> claz = type;
		while (claz != Object.class) {
			final List<Method> allMethods = new ArrayList<Method>(Arrays.asList(claz.getDeclaredMethods()));
			for (final Method method : allMethods) {
				if (method.isAnnotationPresent(RequetMapping.class)) {
					map.put(method.getAnnotation(RequetMapping.class).name(), method);
				}
			}
			claz = claz.getSuperclass();
		}
		return map;
	}

	public Map<String, Method> getMethodsAnnotatedWithViewMapping(final Class<?> type) {
		Map<String, Method> map = new HashMap<>();
		Class<?> claz = type;
		while (claz != Object.class) {
			final List<Method> allMethods = new ArrayList<Method>(Arrays.asList(claz.getDeclaredMethods()));
			for (final Method method : allMethods) {
				if (method.isAnnotationPresent(ViewMapping.class)) {
					map.put(method.getAnnotation(ViewMapping.class).name(), method);
				}
			}
			claz = claz.getSuperclass();
		}
		return map;
	}

}
