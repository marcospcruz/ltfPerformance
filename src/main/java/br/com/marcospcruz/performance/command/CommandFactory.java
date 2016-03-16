package br.com.marcospcruz.performance.command;

import java.io.IOException;
import java.util.Properties;

import br.com.marcospcruz.performance.util.ConstantesEnum;
import br.com.marcospcruz.performance.util.FileUtils;

public class CommandFactory {

	public static BaseCommand createCommand(String actionCommand)
			throws IOException, InstantiationException, IllegalAccessException,
			ClassNotFoundException {
		// TODO Auto-generated method stub
		String className = actionCommand.replace(" ", "_");
		String fileName = ConstantesEnum.ConfigPropertiesFile.getValue()
				.toString();

		FileUtils utilitario = new FileUtils();

		Properties properties = utilitario.loadPropertiesFile(fileName);

		Class clazz = Class.forName(properties.getProperty(className));

		BaseCommand cmd = (BaseCommand) clazz.newInstance();

		return cmd;
	}
}
