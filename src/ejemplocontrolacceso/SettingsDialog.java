/*
 * Copyright (C) 2014 Javier García Escobedo <javiergarbedo.es>
 *
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with this program.  If not, see <http://www.gnu.org/licenses/>.
 */
package ejemplocontrolacceso;

import java.io.FileWriter;
import java.io.IOException;
import java.text.MessageFormat;
import java.util.Arrays;
import java.util.Locale;
import java.util.Properties;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JOptionPane;

/**
 *
 * @author Javier García Escobedo <javiergarbedo.es>
 */
public class SettingsDialog extends javax.swing.JDialog {

    private Properties properties;
    private boolean okButtonPressed = false;

    /**
     * Creates new form JgChangePrivateKeyPropertyDialog
     */
    public SettingsDialog(java.awt.Frame parent, boolean modal) {
        super(parent, modal);
        initComponents();
        setResizable(false);
        setLocationRelativeTo(parent);
        getRootPane().setDefaultButton(jButtonOk);    
    }    
    
    public void setProperties(Properties properties) {
        this.properties = properties;

        fillComboBoxLanguages();
                
        //Si no hay una contraseña en el archivo de propiedades, activar campos 
        //  para obligar nueva contraseña
        if(properties.getProperty(Globals.PROPERTY_KEY_PASSWORD)==null) {
            jCheckBox1.setSelected(true);
            jCheckBox1.setEnabled(false);
            jPasswordFieldOld.setEnabled(false);
            jPasswordFieldNew.setEnabled(true);
            jPasswordFieldConfirm.setEnabled(true);
        }
    }

    public boolean isOkButtonPressed() {
        return okButtonPressed;
    }       
    
    public void fillComboBoxLanguages() {
        //Crear un array con los nombres completos de los idiomas
        String[] labelLanguages = new String[Globals.LANGUAGES.length];
        for (int i = 0; i < labelLanguages.length; i++) {
            //Obtener el nombre de cada idioma
            labelLanguages[i] = (new Locale(Globals.LANGUAGES[i])).getDisplayName();
        }
        //Pasar al JComboBox el array de nombres de idiomas
        DefaultComboBoxModel cbModel = new DefaultComboBoxModel(labelLanguages);
        jComboBoxLanguage.setModel(cbModel);

        //Leer el idioma que se encuentra en el archivo de propiedades
        String languageInProperties = properties.getProperty(Globals.PROPERTY_KEY_LANGUAGE);                        
        //Obtener la posición en la que se encuentra dentro del array <languages> el idioma que 
        //  se ha leído en el archivo de propiedades
        int indexOfLanguage = Arrays.asList(Globals.LANGUAGES).indexOf(languageInProperties);
        if(indexOfLanguage==-1) {
            indexOfLanguage = 0;
        }
        //Marcar como seleccionado el idioma del archivo de propiedades
        jComboBoxLanguage.setSelectedIndex(indexOfLanguage);
    }
    
    public static boolean isPasswordValid(char[] password, int minLength, 
            boolean digitRequired, boolean lowerCaseRequired, 
            boolean upperCaseRequired, boolean specialCharRequired) {
        
        boolean hasDigit = false;
        boolean hasLower = false;
        boolean hasUpper = false;
        boolean hasSpecialChar = false;
        
        if(password.length >= minLength) {
            for(int i=0; i<password.length; i++) {
                char c = password[i];
                if(Character.isDigit(c)) {
                    hasDigit = true;
                }
                if(Character.isLetter(c) && Character.isLowerCase(c)) {
                    hasLower = true;
                }
                if(Character.isLetter(c) && !Character.isLowerCase(c)) {
                    hasUpper = true;
                }
                if(!Character.isDigit(c) && !Character.isAlphabetic(c)) {
                    hasSpecialChar = true;
                }
            }
            //Comprobar si se cumplen todas las condiciones impuestas
            if(digitRequired == hasDigit && lowerCaseRequired == hasLower &&
                    upperCaseRequired == hasUpper && 
                    specialCharRequired == hasSpecialChar) {
                return true;
            } 
        }
        return false;
    }
    
    
    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jTextField1 = new javax.swing.JTextField();
        jButtonCancel = new javax.swing.JButton();
        jButtonOk = new javax.swing.JButton();
        jLabel4 = new javax.swing.JLabel();
        jComboBoxLanguage = new javax.swing.JComboBox();
        jLabel1 = new javax.swing.JLabel();
        jPasswordFieldOld = new javax.swing.JPasswordField();
        jLabel2 = new javax.swing.JLabel();
        jPasswordFieldNew = new javax.swing.JPasswordField();
        jLabel3 = new javax.swing.JLabel();
        jPasswordFieldConfirm = new javax.swing.JPasswordField();
        jCheckBox1 = new javax.swing.JCheckBox();

        jTextField1.setText("jTextField1");

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        java.util.ResourceBundle bundle = java.util.ResourceBundle.getBundle("res/strings"); // NOI18N
        setTitle(bundle.getString("settings")); // NOI18N

        jButtonCancel.setText(bundle.getString("cancel")); // NOI18N
        jButtonCancel.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonCancelActionPerformed(evt);
            }
        });

        jButtonOk.setText(bundle.getString("ok")); // NOI18N
        jButtonOk.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonOkActionPerformed(evt);
            }
        });

        jLabel4.setText(bundle.getString("language")); // NOI18N

        jComboBoxLanguage.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));

        jLabel1.setText(bundle.getString("oldPassword")); // NOI18N

        jPasswordFieldOld.setColumns(10);
        jPasswordFieldOld.setEnabled(false);

        jLabel2.setText(bundle.getString("newPassword")); // NOI18N

        jPasswordFieldNew.setColumns(10);
        jPasswordFieldNew.setEnabled(false);

        jLabel3.setText(bundle.getString("confirmPassword")); // NOI18N

        jPasswordFieldConfirm.setColumns(10);
        jPasswordFieldConfirm.setEnabled(false);

        jCheckBox1.setText(bundle.getString("changePassword")); // NOI18N
        jCheckBox1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jCheckBox1ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jLabel4)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jComboBoxLanguage, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(88, 88, 88)
                        .addComponent(jButtonOk)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jButtonCancel))
                    .addComponent(jCheckBox1)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel1)
                            .addComponent(jLabel2)
                            .addComponent(jLabel3))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jPasswordFieldOld, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jPasswordFieldNew, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jPasswordFieldConfirm, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel4)
                    .addComponent(jComboBoxLanguage, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jCheckBox1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel1)
                    .addComponent(jPasswordFieldOld, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel2)
                    .addComponent(jPasswordFieldNew, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel3)
                    .addComponent(jPasswordFieldConfirm, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jButtonCancel)
                    .addComponent(jButtonOk))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jCheckBox1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jCheckBox1ActionPerformed
        jPasswordFieldOld.setEnabled(jCheckBox1.isSelected());
        jPasswordFieldNew.setEnabled(jCheckBox1.isSelected());
        jPasswordFieldConfirm.setEnabled(jCheckBox1.isSelected());
    }//GEN-LAST:event_jCheckBox1ActionPerformed

    private void jButtonOkActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonOkActionPerformed
        boolean error = false;
        char[] oldPassword = null, newPassword = null, confirmPassword = null;

        //Obtener la posición del idioma seleccionado en la lista desplegable
        int indexLanguageSelected = jComboBoxLanguage.getSelectedIndex();
        //Establecer la propiedad del idioma
        properties.setProperty(Globals.PROPERTY_KEY_LANGUAGE, Globals.LANGUAGES[indexLanguageSelected]);

        if (jCheckBox1.isSelected()) {
            //Obtener las contraseñas introducidas por el usuario
            oldPassword = jPasswordFieldOld.getPassword();
            newPassword = jPasswordFieldNew.getPassword();
            confirmPassword = jPasswordFieldConfirm.getPassword();

            //Codificar la contraseña anterior para compararla después
            String oldPasswordMd5 = Util.toMd5(oldPassword, false);

            //Obtener la contraseña codificada que hay en el archivo de propiedades
            String passwordPropertyMd5 = null;
            passwordPropertyMd5 = properties.getProperty(Globals.PROPERTY_KEY_PASSWORD);

            //Comprobar si no había contraseña en el archivo de propiedades y se ha
            //  dejado en blanco la contraseña anterior en la ventana
            if (passwordPropertyMd5 == null && oldPassword.length == 0
                    || //O bien, si hay una contraseña en el archivo de propiedades y
                    //  coincide con la contraseña anterior en la ventana
                    passwordPropertyMd5 != null && passwordPropertyMd5.equals(oldPasswordMd5)) {

                //Comprobar si coincinden la nueva contraseña y la confirmación
                if (Arrays.equals(newPassword, confirmPassword)) {

                    //Comprobar si la contraseña cumple con los requisitos mínimos
                    if (isPasswordValid(newPassword,
                            Globals.PASSWORD_MIN_LENGTH, Globals.PASSWORD_REQUIRE_DIGIT,
                            Globals.PASSWORD_REQUIRE_LOWERCASE, Globals.PASSWORD_REQUIRE_UPPERCASE,
                            Globals.PASSWORD_REQUIRE_SPECIALCHAR)) {
                        //Codificar la nueva contraseña
                        String newPasswordMd5 = Util.toMd5(newPassword, true);
                        //Guardar la contraseña codificada en el archivo de propiedades
                        properties.setProperty(Globals.PROPERTY_KEY_PASSWORD, newPasswordMd5);

                    } else { //No se cumplen las condiciones mínimas de la nueva contraseña
                        error = true;
                        //Informar al usuario que no se cumplen las condiciones mínimas para la contraseña
                        String message = Globals.BUNDLE.getString("passwordNotValid")
                                + "\n - " + MessageFormat.format(Globals.BUNDLE.getString("passwordMinLength"), Globals.PASSWORD_MIN_LENGTH)
                                + (Globals.PASSWORD_REQUIRE_DIGIT ? "\n - " + Globals.BUNDLE.getString("passwordRequireDigit") : "")
                                + (Globals.PASSWORD_REQUIRE_LOWERCASE ? "\n - " + Globals.BUNDLE.getString("passwordRequireLowerCase") : "")
                                + (Globals.PASSWORD_REQUIRE_UPPERCASE ? "\n - " + Globals.BUNDLE.getString("passwordRequireUpperCase") : "")
                                + (Globals.PASSWORD_REQUIRE_SPECIALCHAR ? "\n - " + Globals.BUNDLE.getString("passwordrequireSpecialChar") : "");
                        JOptionPane.showMessageDialog(this, message, Globals.BUNDLE.getString("appTitle"), JOptionPane.WARNING_MESSAGE);
                    }

                } else { //La nueva contraseña y la confirmación no coinciden
                    error = true;
                    JOptionPane.showMessageDialog(this, 
                            Globals.BUNDLE.getString("passwordsMismatch"), 
                            Globals.BUNDLE.getString("appTitle"), 
                            JOptionPane.WARNING_MESSAGE);
                }

            } else { //La contraseña anterior introducida por el usuario no coincide
                //  con la que se encuentra en el archivo de propiedades
                error = true;
                JOptionPane.showMessageDialog(this, 
                        Globals.BUNDLE.getString("wrongPassword"), 
                        Globals.BUNDLE.getString("appTitle"), 
                        JOptionPane.ERROR_MESSAGE);
            }
        }
        
        if(!error) {
            //Limpiar de la memoria las contraseñas
            Arrays.fill(oldPassword, '\u0000');
            Arrays.fill(newPassword, '\u0000');
            Arrays.fill(confirmPassword, '\u0000');

            //Preguntar al usuario si desea reiniciar ahora para que los cambios tengan efecto
            int response = JOptionPane.showConfirmDialog(this, 
                    Globals.BUNDLE.getString("mustRestart"), 
                    Globals.BUNDLE.getString("appTitle"), 
                    JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE);
            if (response == JOptionPane.YES_OPTION) {
                //Guardar las propiedades en el archivo y reiniciar
                try {
                    properties.store(new FileWriter(Globals.PROPERTIES_FILE_NAME), "");
                    Util.restartJarApplication(SettingsDialog.class);
                } catch (IOException ex) {
                    Logger.getLogger(SettingsDialog.class.getName()).log(Level.SEVERE, null, ex);
                }
            } else {
                okButtonPressed = true;
                dispose();
            }
        }
    }//GEN-LAST:event_jButtonOkActionPerformed

    private void jButtonCancelActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonCancelActionPerformed
        dispose();
    }//GEN-LAST:event_jButtonCancelActionPerformed

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
            java.util.logging.Logger.getLogger(SettingsDialog.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(SettingsDialog.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(SettingsDialog.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(SettingsDialog.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the dialog */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                SettingsDialog dialog = new SettingsDialog(new javax.swing.JFrame(), true);
                dialog.addWindowListener(new java.awt.event.WindowAdapter() {
                    @Override
                    public void windowClosing(java.awt.event.WindowEvent e) {
                        System.exit(0);
                    }
                });
                dialog.setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jButtonCancel;
    private javax.swing.JButton jButtonOk;
    private javax.swing.JCheckBox jCheckBox1;
    private javax.swing.JComboBox jComboBoxLanguage;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JPasswordField jPasswordFieldConfirm;
    private javax.swing.JPasswordField jPasswordFieldNew;
    private javax.swing.JPasswordField jPasswordFieldOld;
    private javax.swing.JTextField jTextField1;
    // End of variables declaration//GEN-END:variables
}
