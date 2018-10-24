package me.carlosdg.pda.word.readers;

import me.carlosdg.pda.word.Word;

/**
 * Interface that represents all the strategies that can be used to read an
 * input Word
 *
 * @author Carlos Domínguez García
 */
public interface WordReader {
	/** Returns the Word read */
	public Word getWord();
}
