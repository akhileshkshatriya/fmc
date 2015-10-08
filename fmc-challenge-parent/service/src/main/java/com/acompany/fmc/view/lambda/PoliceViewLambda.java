package com.acompany.fmc.view.lambda;

import java.util.function.Supplier;

public class PoliceViewLambda {

	
	public static Supplier<String> policeViewOptions = () -> {
		return "\n"+
				"Please Select...\n"+
				"[1] Start Mission\n"+
				"[2] Buy Second Hand Car \n"	+
				"[3] Exit" +
				"\n"+
				"\n"
				;
	};
	
	public static Supplier<String> error =() ->{
		return  "\n"+
				"Sorry Worng Input, Please choose from the option provided"+
				"\n";
	};
				
	public static Supplier<String> dialog = ()->{
	return "\n"+
			"I informed Police about it, They said my Car has been Stolen by TheVillain \n"+
			"Police is also looking for TheVillain for a long time \n"+
			"But Police can't help me much, because they doon't know where TheVillain is. \n"+
			"It seems like it is going to take a little long  \n"+
			"Now Either I can sit back and wait for Police to find TheVillain, and hopefully my Car  \n"+
			"OR"+
			"I can take the control of situation and start a mission to FIND MY CAR own my own. \n"+
			"Ohh yes, Meanwhile ...I need a commute, Temporarily...I can simply buy a second hand car too. \n"
			;	
	};
	
	public static Supplier<String> secondHandCarDialog = ()->{
		return "\n"+
				"Hmm....So I have bought a really nice car at effordable price here...\n"+
				"Thank god, at least for that matter there is a wondeful site where I can buy nice cars at effordable prices. \n"+
				"www.acompany.com \n"+
				"Here... buying and selling is really easy. \n";
	};
	
	public static Supplier<String> secondHandCarOptions = ()->{
		return  "\n"+
				"[1] Back \n" +
				"[2] Exit" +
				"\n"	;
	};
	
	public static void display(Supplier<String> options){
		System.out.println(options.get());
	}
}
