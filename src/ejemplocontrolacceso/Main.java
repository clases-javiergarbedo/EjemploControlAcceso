/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ejemplocontrolacceso;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Locale;
import java.util.Properties;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;

/**
 *
 * @author Javier García Escobedo <javiergarbedo.es>
 */
public class Main extends javax.swing.JFrame {

    private Properties properties = new Properties();

    /**
     * Creates new form Main
     */
    public Main() {
        loadProperties();
        initComponents();
        setLocationRelativeTo(null);
        if(!isPasswordCorrect()) {
            System.exit(0);
        }
    }
    
    private boolean isPasswordCorrect() {
        String passwordPropertyMd5 = properties.getProperty(Globals.PROPERTY_KEY_PASSWORD);
        //Comprobar si hay configurada una contraseña de acceso
        if(passwordPropertyMd5!=null) {
            //Solicitar contraseña de acceso
            PasswordDialog passwordDialog = new PasswordDialog(this, true);
            passwordDialog.setVisible(true);
            if(passwordDialog.isOkButtonPressed()) {
                String passwordMd5 = passwordDialog.getPasswordMd5();
                //Comprobar si la contraseña es correcta 
                if(!passwordPropertyMd5.equals(passwordMd5)) {
                    JOptionPane.showMessageDialog(this, 
                            Globals.BUNDLE.getString("wrongPassword"), 
                            Globals.BUNDLE.getString("appTitle"), 
                            JOptionPane.ERROR_MESSAGE);
                    return false;
                }
            } else { //Se ha pulsado en botón Cancelar en la ventana de solicitud de contraseña
                return false;
            }            
        } else { //No hay contraseña establecida aún
            //Abrir la ventana de configuración para pedir nueva contraseña
            SettingsDialog settingsDialog = new SettingsDialog(this, true);
            settingsDialog.setProperties(properties);
            settingsDialog.setVisible(true); 
            //Si no se ha pulsado el botón OK, no se acepta la password introducida
            if(!settingsDialog.isOkButtonPressed()) {
                return false;
            }
        }
        //Todo ha ido bien. Se considera que la contraseña introducida es correcta 
        return true;
    }

    private void loadProperties() {
        try {
            properties.load(new FileReader(Globals.PROPERTIES_FILE_NAME));
            String propLanguage = properties.getProperty(Globals.PROPERTY_KEY_LANGUAGE, Globals.DEFAULT_LANGUAGE);
            Locale.setDefault(new Locale(propLanguage));
        } catch (FileNotFoundException ex) {
            //No hay problema si no existe el fichero de propiedades, se creará después
        } catch (IOException ex) {
            Logger.getLogger(SettingsDialog.class.getName()).log(Level.SEVERE, null, ex);
        }
    }   

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jButtonSettings = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        addWindowListener(new java.awt.event.WindowAdapter() {
            public void windowClosing(java.awt.event.WindowEvent evt) {
                formWindowClosing(evt);
            }
        });

        jButtonSettings.setText("Settings");
        jButtonSettings.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonSettingsActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jButtonSettings)
                .addContainerGap(299, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jButtonSettings)
                .addContainerGap(265, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jButtonSettingsActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonSettingsActionPerformed
        SettingsDialog settingsDialog = new SettingsDialog(this, true);
        settingsDialog.setProperties(properties);
        settingsDialog.setVisible(true); 
    }//GEN-LAST:event_jButtonSettingsActionPerformed

    private void formWindowClosing(java.awt.event.WindowEvent evt) {//GEN-FIRST:event_formWindowClosing
        try {
            //Guardar las propiedades en el archivo
            properties.store(new FileWriter(Globals.PROPERTIES_FILE_NAME), "");
        } catch (IOException ex) {
            Logger.getLogger(Main.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_formWindowClosing

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(Main.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(Main.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(Main.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Main.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new Main().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jButtonSettings;
    // End of variables declaration//GEN-END:variables
}
