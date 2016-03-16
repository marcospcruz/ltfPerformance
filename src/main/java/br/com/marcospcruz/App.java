package br.com.marcospcruz;

import br.com.marcospcruz.performance.util.ConstantesEnum;
import br.com.marcospcruz.performance.view.MainGui;

/**
 * Hello world!
 *
 */
public class App {
	public static void main(String args[]) {

		appInit();

		// constantTest();

	}

	private static void appInit() {
		// TODO Auto-generated method stub

		// iniciando a interface principal
		MainGui mainGui = new MainGui();
		//
		mainGui.start();
		// testeConexaoBanco();

	}

	private static void testeConexaoBanco() {
		// TODO Auto-generated method stub

		// Database dao = new TransacaoDao();
		//
		// try {
		// List<TransacaoTO> objetos = dao.readAll(1);
		// System.out.println("Length: " + objetos.size());
		// } catch (SQLException e) {
		// // TODO Auto-generated catch block
		// e.printStackTrace();
		// }

	}

}
