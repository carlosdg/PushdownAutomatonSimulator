package me.carlosdg.pda.word.readers;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import me.carlosdg.pda.word.Word;

/**
 * Word Reader for custom word files, where each symbol is separated by spaces
 *
 * @author Carlos Domínguez García
 */
public class WordFileReader implements WordReader {

	/** Word instance */
	private Word word;

	/** Parses the file and initializes the word */
	public WordFileReader(String filePath) throws FileNotFoundException {
		Scanner reader = new Scanner(new BufferedReader(new FileReader(filePath)));
		List<String> tokens = new ArrayList<>();

		try {
			while (reader.hasNext()) {
				tokens.add(reader.next());
			}
		} finally {
			reader.close();
		}

		word = new Word(tokens);
	}

	@Override
	public Word getWord() {
		return word;
	}

}
