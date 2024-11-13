package io.github.astrapisixtynine.picocli;

import static org.junit.jupiter.api.Assertions.*;

import java.io.ByteArrayOutputStream;
import java.io.File;

import org.junit.jupiter.api.Test;

import io.github.astrapi69.file.search.PathFinder;
import picocli.CommandLine;

class CheckSumTest
{

	@Test
	void checkSum()
	{
		String actual;
		String expected;
		// Redirecting output
		ByteArrayOutputStream outContent = new ByteArrayOutputStream();
		ByteArrayOutputStream errContent = new ByteArrayOutputStream();


		File srcTestResourcesDir = PathFinder.getSrcTestResourcesDir();
		File file = PathFinder.getRelativePath(srcTestResourcesDir, "spotless",
			"importorder.properties");
		CheckSum checkSumCommand = CheckSum.builder().file(file).build();
		CommandLine cmd = new CommandLine(checkSumCommand)
		// .setOut(new PrintWriter(outContent)) // Set custom output
		// .setErr(new PrintWriter(errContent))
		;
		int exit = cmd.execute(checkSumCommand.getFile().getAbsolutePath());
		expected = "c9bb661d95999b471fc6b0691e216968c6854dae917823b91f685bf0df04beeb";
		assertEquals(expected, checkSumCommand.getChecksum());
	}

}