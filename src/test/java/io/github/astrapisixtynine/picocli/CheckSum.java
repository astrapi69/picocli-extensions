package io.github.astrapisixtynine.picocli;

import java.io.File;
import java.math.BigInteger;
import java.nio.file.Files;
import java.security.MessageDigest;
import java.util.concurrent.Callable;

import lombok.Builder;
import lombok.Data;
import picocli.CommandLine.Command;
import picocli.CommandLine.Option;
import picocli.CommandLine.Parameters;

@Command(name = "checksum", mixinStandardHelpOptions = true, version = "checksum 4.0", description = "Prints the checksum (SHA-256 by default) of a file to STDOUT.")
@Data
@Builder
class CheckSum implements Callable<Integer>
{

	@Parameters(index = "0", description = "The file whose checksum to calculate.")
	private File file;

	@Option(names = { "-a", "--algorithm" }, description = "MD5, SHA-1, SHA-256, ...")
	@Builder.Default
	private String algorithm = "SHA-256";

	private String checksum;

	@Override
	public Integer call() throws Exception
	{ // your business logic goes here...
		byte[] fileContents = Files.readAllBytes(file.toPath());
		byte[] digest = MessageDigest.getInstance(algorithm).digest(fileContents);
		checksum = String.format("%0" + (digest.length * 2) + "x", new BigInteger(1, digest));
		return 0;
	}

}