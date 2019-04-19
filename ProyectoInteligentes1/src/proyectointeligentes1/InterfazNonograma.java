package proyectointeligentes1;

import jade.gui.GuiEvent;
import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.util.StringTokenizer;
import javax.swing.JButton;

/**
 * Clase principal, recibe la entrada de datos del nonograma ya sea directo o
 * inverso.
 *
 * @author Santiago
 */
public class InterfazNonograma extends javax.swing.JFrame {

    private Agente1 agente;
    private int matriz[][];
    private String datosEnviar = ""; //Cadena de texto que contiene los números de entrada 
            //para resolver el nonograma que será enviada del Agente1 al Agente2

    public InterfazNonograma(Agente1 agente) {
        initComponents();
        this.jTextField1.setText("0");
        this.jTextField2.setText("0");
        this.agente = agente;
    }

//    public InterfazNonograma() {
//        initComponents();
//        this.jTextField1.setText("0");
//        this.jTextField2.setText("0");
//    }

    public void pintarSolucion(String solucion) {
         int contadorX = 0;
        int contadorY = 0;
        String[] elementos= solucion.split("-");
        for (int i = 0; i < elementos.length; i++) {
            String[] nums = elementos[i].split(",");
            
            for (int j = 0; j < nums.length; j++) {
                int num = Integer.parseInt(nums[j]);
                
                JButton boton = new JButton();
                boton.setBounds(contadorX, contadorY, 35, 35);
                if(num==0){
                    boton.setBackground(Color.white);
                }
                else if(num==1){
                    boton.setBackground(Color.BLACK);
                }
                String nombre = i + " " + j;
                boton.setName(nombre);
                boton.setText("");
                contadorX += 35;
                jPanel1.add(boton);
            }
            contadorX = 0;
            contadorY += 35;
        }

        jPanel1.repaint();
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jTextField1 = new javax.swing.JTextField();
        jLabel4 = new javax.swing.JLabel();
        jTextField2 = new javax.swing.JTextField();
        jLabel5 = new javax.swing.JLabel();
        jButton1 = new javax.swing.JButton();
        jButton2 = new javax.swing.JButton();
        jPanel1 = new javax.swing.JPanel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setPreferredSize(new java.awt.Dimension(1500, 700));
        setSize(new java.awt.Dimension(1500, 700));

        jLabel1.setText("Sisigramas");

        jLabel2.setText("Ingrese tamaño de nonograma: ");

        jLabel3.setText("Filas:");

        jLabel4.setText("Columnas:");

        jTextField2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jTextField2ActionPerformed(evt);
            }
        });

        jLabel5.setText("¿Como quieres resolver tu nonograma?");

        jButton1.setText("Directo");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        jButton2.setText("Inverso");
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 700, Short.MAX_VALUE)
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 700, Short.MAX_VALUE)
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel1)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jLabel3)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jTextField1, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(jLabel4)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jTextField2, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(jLabel2)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(10, 10, 10)
                        .addComponent(jLabel5))
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jButton1)
                        .addGap(18, 18, 18)
                        .addComponent(jButton2)))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap(794, Short.MAX_VALUE)
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jLabel2)
                .addGap(4, 4, 4)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel3)
                    .addComponent(jTextField1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel4)
                    .addComponent(jTextField2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jLabel5)
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jButton1)
                    .addComponent(jButton2))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jTextField2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jTextField2ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jTextField2ActionPerformed

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
        int x = Integer.parseInt(this.jTextField1.getText());
        int y = Integer.parseInt(this.jTextField2.getText());
        setMatriz(new int[x][y]);
        int contadorX = 0;
        int contadorY = 0;
        for (int i = 0; i < x; i++) {
            for (int j = 0; j < y; j++) {
                getMatriz()[i][j] = 0;
                JButton boton = new JButton();
                boton.addActionListener(cambiarColorBoton(boton));
                boton.setBounds(contadorX, contadorY, 35, 35);
                boton.setBackground(Color.white);
                String nombre = i + " " + j;
                boton.setName(nombre);
                boton.setText(" ");
                contadorX += 35;
                jPanel1.add(boton);
            }
            contadorX = 0;
            contadorY += 35;
        }
        jPanel1.repaint();
    }//GEN-LAST:event_jButton2ActionPerformed

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        // TODO add your handling code here:

        
//        try {
//            File archivo = new File("./archivos/Futbol.txt");
//            BufferedReader datos = new BufferedReader(new FileReader(archivo));
//
//            String str = "";
//            int lineas = 0;
//
//            while ((str = datos.readLine()) != null) {
//
//                if (str.length() > 0 && str.charAt(0) == '#') {
//                    continue; // Este símbolo se ignora, por lo tanto se le dice 
//                    //que continúe a la siguiente iteración del ciclo while
//                }
//
//                StringTokenizer st = new StringTokenizer(str);
//
//                while (st.hasMoreTokens()) {
//
//                    int dato = Integer.parseInt(st.nextToken());
//
//                    datosEnviar += dato + ","; //Se usa coma para separar los números
//                }
//
//                datosEnviar += "-"; //Se usa guión para distinguir cada línea que se lee del archivo
//                lineas++;
//            }
//
//            if (lineas % 2 != 0) {
//                Exception e = new Exception("Los nonogramas deben tener igual cantidad de filas y columnas (NxN).");
//                throw e;
//            }
//            System.out.println(getDatosEnviar());
//
//        } catch (Exception e) {
//            System.out.println(e.getMessage());
//        }
        
    }//GEN-LAST:event_jButton1ActionPerformed

    private void mostrarMatriz() {

        for (int i = 0; i < getMatriz().length; i++) {
            for (int j = 0; j < getMatriz()[i].length; j++) {
                System.out.print(getMatriz()[i][j] + " ");
            }
            System.out.println("");
        }
        System.out.println("\n");
    }

    private ActionListener cambiarColorBoton(JButton boton) {

        ActionListener action = new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String[] vectorPosicion;
                if (boton.getBackground().equals(Color.white) && boton.getText().equals(" ")) {
                    boton.setBackground(Color.black);
                    vectorPosicion = boton.getName().split(" ");

                    getMatriz()[Integer.parseInt(vectorPosicion[0])][Integer.parseInt(vectorPosicion[1])] = 1;
                } else if (boton.getBackground().equals(Color.black) && boton.getText().equals(" ")) {
                    boton.setBackground(Color.white);
                    boton.setText("X");
                    vectorPosicion = boton.getName().split(" ");
                    getMatriz()[Integer.parseInt(vectorPosicion[0])][Integer.parseInt(vectorPosicion[1])] = 2;

                } else if (boton.getBackground().equals(Color.white) && boton.getText().equals("X")) {
                    boton.setText(" ");
                    vectorPosicion = boton.getName().split(" ");
                    getMatriz()[Integer.parseInt(vectorPosicion[0])][Integer.parseInt(vectorPosicion[1])] = 0;

                }
                mostrarMatriz();

            }
        };
        return action;
    }

    /**
     * @param args the command line arguments
     */
//    public static void main(String args[]) {
//        /* Set the Nimbus look and feel */
//        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
//        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
//         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
//         */
//        try {
//            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
//                if ("Nimbus".equals(info.getName())) {
//                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
//                    break;
//                }
//            }
//        } catch (ClassNotFoundException ex) {
//            java.util.logging.Logger.getLogger(InterfazNonograma.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
//        } catch (InstantiationException ex) {
//            java.util.logging.Logger.getLogger(InterfazNonograma.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
//        } catch (IllegalAccessException ex) {
//            java.util.logging.Logger.getLogger(InterfazNonograma.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
//        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
//            java.util.logging.Logger.getLogger(InterfazNonograma.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
//        }
//        //</editor-fold>
//        //</editor-fold>
//
//        /* Create and display the form */
//        java.awt.EventQueue.invokeLater(new Runnable() {
//            public void run() {
//                new InterfazNonograma().setVisible(true);
//            }
//        });
//    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton2;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JTextField jTextField1;
    private javax.swing.JTextField jTextField2;
    // End of variables declaration//GEN-END:variables


    /**
     * @return the matriz
     */
    public int[][] getMatriz() {
        return matriz;
    }


    /**
     * @param matriz the matriz to set
     */
    public void setMatriz(int[][] matriz) {
        this.matriz = matriz;
    }

    /**
     * @return the datosEnviar
     */
    public String getDatosEnviar() {
        return datosEnviar;
    }
}
