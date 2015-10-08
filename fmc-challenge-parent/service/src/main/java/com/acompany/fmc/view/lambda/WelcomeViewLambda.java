package com.acompany.fmc.view.lambda;

import java.util.function.Supplier;

public class WelcomeViewLambda {
	
	public static Supplier<String> dialog = ()->{
		return "\n" +
				"Welcome To Find My Car Game" +
				"\n";
	};
	
	public static void display(Supplier<String> options){
		System.out.println(options.get());
	}

}
