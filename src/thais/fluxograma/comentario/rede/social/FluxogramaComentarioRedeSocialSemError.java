package thais.fluxograma.comentario.rede.social;

import java.util.Scanner;

public class FluxogramaComentarioRedeSocialSemError {

	public static void main(String[] args) {

		Scanner teclado = new Scanner(System.in);

		boolean algumComentarioFoiFeito = false;
		int qtdeComentarios = perguntaValorInteiro("Quantos comentários você quer fazer?");

		for (int i = 0; i < qtdeComentarios; i++) {
			String comentario = perguntaValorString("Qual o comentário " + (i + 1) + "?");
			boolean comentarioFoiFeito = decideSeComenta(comentario);
			algumComentarioFoiFeito = algumComentarioFoiFeito || comentarioFoiFeito;
			System.out.println("-------------");
		}
		if (algumComentarioFoiFeito) {
			System.out.println("Ok, ao menos um comentário você pode fazer");
		} else {
			System.out.println("Você não fez nenhum comentário");
		}
	}

	static int perguntaValorInteiro(String pergunta) {
		return Integer.valueOf(perguntaValorString(pergunta));
	}

	static String perguntaValorString(String pergunta) {
		System.out.print(pergunta + " ");
		Scanner teclado = new Scanner(System.in);
		return teclado.nextLine();
	}

	static boolean perguntaComRespostaSimOuNao(String pergunta) {
		do {
			System.out.print(pergunta + " ");
			Scanner teclado = new Scanner(System.in);
			String resposta = teclado.next().toLowerCase();
			switch (resposta) {
			case "sim":
				return true;
			case "não":
				return false;
			default:
				System.out.println("Resposta inválida! responda 'sim' ou 'não'");
			}
		} while (true);
	}

	public static boolean decideSeComenta(String comentario) {
		boolean precisaDizer = perguntaComRespostaSimOuNao("Precisa mesmo dizer isso (" + comentario + ")?");
		if (precisaDizer) {
			return fluxoVontadeDeComentar();
		} else {
			return instrucaoGuardeParaVc();
		}
	}

	static boolean fluxoVontadeDeComentar() {
		boolean ehUtil = perguntaComRespostaSimOuNao("É útil?");
		if (ehUtil) {
			return fluxoComentarioUtil();
		} else {
			return instrucaoGuardeParaVc();
		}
	}

	static boolean fluxoComentarioUtil() {
		boolean podeOfenderAlguem = perguntaComRespostaSimOuNao("Pode ofender alguém?");
		if (podeOfenderAlguem) {
			return instrucaoGuardeParaVc();
		} else {
			return fluxoComentarioUtilNaoOfensivo();
		}
	}

	static boolean fluxoComentarioUtilNaoOfensivo() {
		boolean ehPalpite = perguntaComRespostaSimOuNao("É um palpite?");
		if (ehPalpite) {
			return instrucaoGuardeParaVc();
		} else if (perguntaComRespostaSimOuNao("Você gostaria de ouvir?")) {
			return instrucaoComente();
		} else {
			return instrucaoGuardeParaVc();
		}
	}

	static boolean instrucaoComente() {
		System.out.println("Pode comentar!!");
		return true;
	}

	static boolean instrucaoGuardeParaVc() {
		System.out.println("Guarde para você e não comente!");
		return false;
	}
}