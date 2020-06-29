package b2_p;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class B2_c {

	public static class Frame extends JFrame {

		private final long serialVersionUID = (long) 1.1;
		private JPanel jPanel1 = new JPanel();
		private JButton jButton1 = new JButton();
		private int ancho = 17;
		private int alto = 17;
		private int contadorJuego = 1;
		public JButton metroCuadrado[][] = new JButton[ancho][alto];
		public String[][] mensajeClik = new String[ancho][alto];

		// —- Dependiendo del ancho también asignaré el número de bombas

		public static void main(String[] args) {
			Frame pruebafinaljava = new Frame();
		}

		public Frame() {
			try {
				jbInit();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}

		private void jbInit() throws Exception {

			getContentPane();
			setSize(new Dimension(526, 579));
			setTitle("Angry Eggs");

			jPanel1.setBounds(new Rectangle(0, 0, 501, 580));
			jPanel1.setLayout(null);

			jButton1.setText("Reinicio");
			jButton1.setBounds(new Rectangle(00, 510, 510, 30));
			jButton1.setFont(new Font("Tahoma", 0, 12));
			jButton1.setHorizontalTextPosition(SwingConstants.CENTER);
			// jButton1.setMargin(new Insets(22, 22, 22, 22));
			jButton1.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					jButton1_actionPerformed(e);
				}
			});
			getContentPane().add(jButton1, null);
			getContentPane().add(jPanel1, null);
			cargarTablero();
			colocarBomba(getAncho());
			comprueba();
			setVisible(true);

			// —- Define que los contornos no participane en el juego.

			cordenadametroCuadrado();
		}

		private void jButton1_actionPerformed(ActionEvent e) {
			for (int i = 1; i < ancho - 1; i++) {
				for (int z = 1; z < alto - 1; z++) {
					mensajeClik[i][z] = "";
					metroCuadrado[i][z].setEnabled(true);
					metroCuadrado[i][z].setText("");
				}
			}
			colocarBomba(getAncho());
			comprueba();
			this.setTitle("Angry Eggs");
			contadorJuego = contadorJuego + 1;
			jButton1.setText("Batalla " + contadorJuego + "... A la carga!!!");
		}
		// —– Inicializa el tablero a 0

		public void cargarTablero() {
			for (int i = 0; i < ancho; i++) {
				for (int z = 0; z < alto; z++) {

					metroCuadrado[i][z] = new JButton();
					jPanel1.add(metroCuadrado[i][z]);
					metroCuadrado[i][z].setBounds(i * 30, z * 30, 30, 30);
					metroCuadrado[i][z].setMargin(new Insets(0, 0, 0, 0));
					metroCuadrado[i][z].setFont(new Font("Tahoma", 0, 13));

					// —– Agrego un ActionListener a cada boton del Array de metroCuadrado
					// —– Ahora puede escuchar eventos.

					metroCuadrado[i][z].addActionListener(new ActionListener() {
						public void actionPerformed(ActionEvent escucharClik) {
							for (int i = 1; i < ancho - 1; i++) {
								for (int z = 1; z < alto - 1; z++) {
									if (escucharClik.getSource() == metroCuadrado[i][z]) {
										showTextTop(i, z);
										// —– Cada Evento llama al método pulsarBoton.
									}
								}
							}
						}
					});

				}
			}
		}
		// —- Coloca el numero de bombas dependiendo el ancho.

		public void colocarBomba(int numeroBombas) {
			double I = 0;
			double Z = 0;
			int condicion = 0;
			do {
				I = Math.random() * getAncho();
				Z = Math.random() * getAlto();
				int i = (int) I;
				int z = (int) Z;
				if (z != 0 && i != 0 && z != alto - 1 && i != ancho - 1) {
					mensajeClik[i][z] = "B";
					condicion++;
				}
			} while (condicion <= ancho);
		}

		public void setAncho(int sAncho) {
			ancho = sAncho;
		}

		public int getAncho() {
			return ancho;
		}

		public void setAlto(int sAlto) {
			alto = sAlto;
		}

		public int getAlto() {
			return alto;
		}

		// —- Asigna un número a cada boton dependiendo de las B que tenga al lado.
		// --- codigo Busca Minas
		public void comprueba() {
			for (int i = 1; i < ancho - 1; i++) {
				for (int z = 1; z < alto - 1; z++) {
					int numeroComprueba = 0; // el numero que voy a asignar al boton
					if (mensajeClik[i][z] != ("t")) {

						if (mensajeClik[i][z - 1] == "B") {
							numeroComprueba++;
						}
						if (mensajeClik[i - 1][z - 1] == "B") {
							numeroComprueba++;
						}
						if (mensajeClik[i + 1][z - 1] == "B") {
							numeroComprueba++;
						}
						if (mensajeClik[i][z + 1] == "B") {
							numeroComprueba++;
						}
						if (mensajeClik[i + 1][z + 1] == "B") {
							numeroComprueba++;
						}
						if (mensajeClik[i - 1][z + 1] == "B") {
							numeroComprueba++;
						}
						if (mensajeClik[i + 1][z] == "B") {
							numeroComprueba++;
						}
						if (mensajeClik[i - 1][z] == "B") {
							numeroComprueba++;
						}
						if (numeroComprueba != 0) {
							mensajeClik[i][z] = (String.valueOf(numeroComprueba));
						}

					}
				}
			}
		}

		// —- Escribe la coordenada en los laterales.
		public void cordenadametroCuadrado() {
			for (int i = 0; i < ancho; i++) {
				for (int z = 0; z < alto; z++) {

					if (z == 0 && i > 0 && i < alto - 1) {
						metroCuadrado[i][z].setText("" + i);
					}
					if (z == ancho - 1 && i > 0 && i < alto - 1) {
						metroCuadrado[i][z].setText("" + i);
					}
					if (i == 0 && z > 0 && z < ancho - 1) {
						metroCuadrado[i][z].setText("" + z);
					}
					if (i == alto - 1 && z > 0 && z < ancho - 1) {
						metroCuadrado[i][z].setText("" + z);
					}
				}
			}
		}

		// —- Muestra el texto en todos los metroCuadrado cuando hay bomba.

		public void textometroCuadrado() {
			for (int i = 1; i < ancho - 1; i++) {
				for (int z = 1; z < alto - 1; z++) {
					metroCuadrado[i][z].setText(mensajeClik[i][z]);
					metroCuadrado[i][z].setEnabled(false);
					jButton1.setText("Batalla Finalizada. ¿Reiniciar?");
					// codigo para mostrar el texto encima del boton.
				}
			}
		}

		// —- Muestra el Numero encima del boton.
		// —- Cambia las propiedades del boton mostrado.
		public void showTextTop(int i, int z) {
			metroCuadrado[i][z].setText(mensajeClik[i][z]);
			metroCuadrado[i][z].setEnabled(false);

			if (mensajeClik[i][z] == "") {
				metroCuadrado[i][z].setEnabled(false);
				metodoStackOverFlow(i, z);
			} else {
				metroCuadrado[i][z].setEnabled(false);
			}
			if (metroCuadrado[i][z].getText() == "B") {
				textometroCuadrado();
			}

		}

		// —- Pone el numero en los metroCuadrado cercanos.
		private void metodoStackOverFlow(int i, int z) {

			metroCuadrado[i - 1][z].setEnabled(false);
			metroCuadrado[i - 1][z - 1].setEnabled(false);
			metroCuadrado[i - 1][z + 1].setEnabled(false);
			metroCuadrado[i][z - 1].setEnabled(false);
			metroCuadrado[i][z + 1].setEnabled(false);
			metroCuadrado[i + 1][z].setEnabled(false);
			metroCuadrado[i + 1][z + 1].setEnabled(false);
			metroCuadrado[i + 1][z - 1].setEnabled(false);

			metroCuadrado[i - 1][z].setText(mensajeClik[i - 1][z]);
			metroCuadrado[i - 1][z - 1].setText(mensajeClik[i - 1][z - 1]);
			metroCuadrado[i - 1][z + 1].setText(mensajeClik[i - 1][z + 1]);
			metroCuadrado[i][z - 1].setText(mensajeClik[i][z - 1]);
			metroCuadrado[i][z + 1].setText(mensajeClik[i][z + 1]);
			metroCuadrado[i + 1][z].setText(mensajeClik[i + 1][z]);
			metroCuadrado[i + 1][z + 1].setText(mensajeClik[i + 1][z + 1]);
			metroCuadrado[i + 1][z - 1].setText(mensajeClik[i + 1][z - 1]);

		}

		// —- Este es el metodo que mostrara el final del game.
		// —- 17 columnas por 10 lineas visibles= 170 metroCuadrado[][]
		// —- 170 - 19 Bombas = 171 metroCuadrado con Texto
		// --- codigo Busca Minas
		public void finalGame() {
			int contadorFinal = 0;
			for (int i = 1; i < ancho - 1; i++) {
				for (int z = 1; z < alto - 1; z++) {
					if (metroCuadrado[i][z].getText() == " " || metroCuadrado[i][z].getText() == "1"
							|| metroCuadrado[i][z].getText() == "2" || metroCuadrado[i][z].getText() == "3"
							|| metroCuadrado[i][z].getText() == "5" || metroCuadrado[i][z].getText() == "4") {
						contadorFinal++;
						if (contadorFinal == 171) {
							this.setTitle("Eres nombrado ''Awake Emerito'' ");
							jButton1.setText("Destruiste todos los enemigos…¿Reinicia?");
						}
					}
				}
			}
		}
	}
}