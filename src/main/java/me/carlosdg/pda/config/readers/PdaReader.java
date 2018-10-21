package me.carlosdg.pda.config.readers;

import me.carlosdg.pda.config.PdaConfiguration;

/**
 * This interface is to abstract away the strategy used to read the PDA
 * configuration. This way the PDA configuration can be read from a file with
 * custom format, with JSON format, with XML format, from memory, from Internet,
 * etc. If these strategies implement this interface we can later forget about
 * how the configuration is read and just use the PdaConfiguration object
 * resulting from reading the configuration
 *
 * @author Carlos Domínguez García
 */
public interface PdaReader {
	/**
	 * Get the PdaConfiguration object instantiated with the configuration read.
	 *
	 * @return The PdaConfiguration instance representing the configuration read
	 */
	public PdaConfiguration getConfig();
}
