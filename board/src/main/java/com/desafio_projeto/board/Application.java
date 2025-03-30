package com.desafio_projeto.board;

import com.desafio_projeto.board.ui.MainMenu;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class Application {


	public static void main(String[] args) {
		var context = SpringApplication.run(Application.class, args);
		MainMenu mainMenu = context.getBean(MainMenu.class);
		mainMenu.execute();

	}

}
