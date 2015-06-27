/**
 * This file is part of VisiCut.
 * Copyright (C) 2011 - 2013 Thomas Oster <thomas.oster@rwth-aachen.de>
 * RWTH Aachen University - 52062 Aachen, Germany
 *
 *     VisiCut is free software: you can redistribute it and/or modify
 *     it under the terms of the GNU Lesser General Public License as published by
 *     the Free Software Foundation, either version 3 of the License, or
 *     (at your option) any later version.
 *
 *     VisiCut is distributed in the hope that it will be useful,
 *     but WITHOUT ANY WARRANTY; without even the implied warranty of
 *     MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 *     GNU Lesser General Public License for more details.
 *
 *     You should have received a copy of the GNU Lesser General Public License
 *     along with VisiCut.  If not, see <http://www.gnu.org/licenses/>.
 **/
package com.t_oster.visicut.gui;

import com.t_oster.visicut.Preferences;
import com.t_oster.visicut.VisicutModel;
import com.t_oster.visicut.managers.MappingManager;
import com.t_oster.visicut.model.mapping.MappingSet;
import java.awt.Component;
import java.awt.Dimension;
import javax.swing.DefaultListCellRenderer;
import javax.swing.JLabel;
import javax.swing.JList;

/**
 *
 * @author Thomas Oster <thomas.oster@rwth-aachen.de>
 */
public class PreferencesDialog extends javax.swing.JDialog
{

  /**
   * Creates new form PreferencesDialog
   */
  public PreferencesDialog(java.awt.Frame parent, boolean modal)
  {
    super(parent, modal);
    initComponents();
    final String NONE =  java.util.ResourceBundle.getBundle("com/t_oster/visicut/gui/mapping/resources/PredefinedMappingBox").getString("NONE");
    cbDefaultMapping.setRenderer(new DefaultListCellRenderer(){
      @Override
      public Component getListCellRendererComponent(JList list, Object value, int index, boolean isSelected, boolean cellHasFocus)
      {
        Component c = super.getListCellRendererComponent(list, value, index, isSelected, cellHasFocus); //To change body of generated methods, choose Tools | Templates.
        if (c instanceof JLabel && value == null)
        {
          ((JLabel) c).setText(NONE);
        }
        return c;
      }
    });
    cbDefaultMapping.removeAllItems();
    cbDefaultMapping.addItem(null);
    for (MappingSet ms : MappingManager.getInstance().generateDefaultMappings())
    {
      cbDefaultMapping.addItem(ms.getName());
    }
    for (MappingSet ms : MappingManager.getInstance().getAll())
    {
      cbDefaultMapping.addItem(ms.getName());
    }
    cbDefaultMapping.setSelectedItem(preferences != null ? preferences.getDefaultMapping() : null);
    
    // resize everything to content
    pack();
  }

  private Preferences preferences = new Preferences();
  public static final String PROP_PREFERENCES = "preferences";

  public Preferences getPreferences()
  {
    return preferences;
  }

  public void setPreferences(Preferences preferences)
  {
    Preferences oldPreferences = this.preferences;
    this.preferences = preferences;
    firePropertyChange(VisicutModel.PROP_PREFERENCES, oldPreferences, preferences);
  }

  
  /**
   * This method is called from within the constructor to initialize the form.
   * WARNING: Do NOT modify this code. The content of this method is always
   * regenerated by the Form Editor.
   */
  @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {
        bindingGroup = new org.jdesktop.beansbinding.BindingGroup();

        tpSettings = new javax.swing.JTabbedPane();
        pnlGeneral = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        cbDefaultMapping = new javax.swing.JComboBox();
        jLabel2 = new javax.swing.JLabel();
        cbSandboxLaserScript = new javax.swing.JCheckBox();
        lbFilenamesForJobs = new javax.swing.JLabel();
        cbSandboxLaserScript1 = new javax.swing.JCheckBox();
        jTextFieldLabName = new javax.swing.JTextField();
        jLabel3 = new javax.swing.JLabel();
        lbEnableQRCodes = new javax.swing.JLabel();
        lbFastQRCodes = new javax.swing.JLabel();
        cbEnableQRCodes = new javax.swing.JCheckBox();
        cbFastQRCodes = new javax.swing.JCheckBox();
        pnlFacebook = new javax.swing.JPanel();
        lblFabLabLocationFacebookId = new javax.swing.JLabel();
        txtfFabLabLocationFacebookId = new javax.swing.JTextField();
        pnlThingiverse = new javax.swing.JPanel();
        lblLasercutterTags = new javax.swing.JLabel();
        txtfLasercutterTags = new javax.swing.JTextField();
        lblSupportedExtensions = new javax.swing.JLabel();
        txtfSupportedExtensions = new javax.swing.JTextField();
        pnlFabqr = new javax.swing.JPanel();
        lblFabqrUrl = new javax.swing.JLabel();
        txtFabqrUrl = new javax.swing.JTextField();
        cbFabqrActive = new javax.swing.JCheckBox();
        btOk = new javax.swing.JButton();
        btCancel = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setLocationByPlatform(true);
        setMinimumSize(new Dimension(450, 300));

        tpSettings.setToolTipText(""); // NOI18N

        pnlGeneral.setToolTipText("General settings regarding the functionality."); // NOI18N

        java.util.ResourceBundle bundle = java.util.ResourceBundle.getBundle("com/t_oster/visicut/gui/resources/PreferencesDialog"); // NOI18N
        jLabel1.setText(bundle.getString("DEFAULT_MAPPING")); // NOI18N
        jLabel1.setToolTipText(bundle.getString("DEFAULT_MAPPING_TOOLTIP")); // NOI18N

        cbDefaultMapping.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));

        org.jdesktop.beansbinding.Binding binding = org.jdesktop.beansbinding.Bindings.createAutoBinding(org.jdesktop.beansbinding.AutoBinding.UpdateStrategy.READ_WRITE, this, org.jdesktop.beansbinding.ELProperty.create("${preferences.defaultMapping}"), cbDefaultMapping, org.jdesktop.beansbinding.BeanProperty.create("selectedItem"), "defMap");
        bindingGroup.addBinding(binding);

        jLabel2.setText(bundle.getString("SANDBOX_LASERSCRIPT")); // NOI18N
        jLabel2.setToolTipText(bundle.getString("SANDBOX_TOOLTIP")); // NOI18N

        binding = org.jdesktop.beansbinding.Bindings.createAutoBinding(org.jdesktop.beansbinding.AutoBinding.UpdateStrategy.READ_WRITE, this, org.jdesktop.beansbinding.ELProperty.create("${preferences.disableSandbox}"), cbSandboxLaserScript, org.jdesktop.beansbinding.BeanProperty.create("selected"));
        bindingGroup.addBinding(binding);

        lbFilenamesForJobs.setText(bundle.getString("USE_FILENAMES_FOR_JOBS")); // NOI18N
        lbFilenamesForJobs.setToolTipText(bundle.getString("FILENAMES_FOR_JOBS_TOOLTIP")); // NOI18N

        binding = org.jdesktop.beansbinding.Bindings.createAutoBinding(org.jdesktop.beansbinding.AutoBinding.UpdateStrategy.READ_WRITE, this, org.jdesktop.beansbinding.ELProperty.create("${preferences.useFilenamesForJobs}"), cbSandboxLaserScript1, org.jdesktop.beansbinding.BeanProperty.create("selected"));
        bindingGroup.addBinding(binding);

        binding = org.jdesktop.beansbinding.Bindings.createAutoBinding(org.jdesktop.beansbinding.AutoBinding.UpdateStrategy.READ_WRITE, this, org.jdesktop.beansbinding.ELProperty.create("${preferences.labName}"), jTextFieldLabName, org.jdesktop.beansbinding.BeanProperty.create("text"));
        bindingGroup.addBinding(binding);

        jTextFieldLabName.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jTextFieldLabNameActionPerformed(evt);
            }
        });

        jLabel3.setText("Lab name:");
        jLabel3.setText(bundle.getString("LAB_NAME")); // NOI18N

        jLabel3.setToolTipText(bundle.getString("LAB_NAME_TOOLTIP")); // NOI18N

        lbEnableQRCodes.setText(bundle.getString("ENABLE_QR_CODES_TEXT")); // NOI18N
        lbEnableQRCodes.setToolTipText(bundle.getString("ENABLE_QR_CODES_TOOLTIP")); // NOI18N

        lbFastQRCodes.setText(bundle.getString("FAST_QR_CODES_TEXT")); // NOI18N
        lbFastQRCodes.setToolTipText(bundle.getString("FAST_QR_CODES_TOOLTIP")); // NOI18N

        binding = org.jdesktop.beansbinding.Bindings.createAutoBinding(org.jdesktop.beansbinding.AutoBinding.UpdateStrategy.READ_WRITE, this, org.jdesktop.beansbinding.ELProperty.create("${preferences.enableQRCodes}"), cbEnableQRCodes, org.jdesktop.beansbinding.BeanProperty.create("selected"));
        bindingGroup.addBinding(binding);

        binding = org.jdesktop.beansbinding.Bindings.createAutoBinding(org.jdesktop.beansbinding.AutoBinding.UpdateStrategy.READ_WRITE, this, org.jdesktop.beansbinding.ELProperty.create("${preferences.fastQRCodes}"), cbFastQRCodes, org.jdesktop.beansbinding.BeanProperty.create("selected"));
        bindingGroup.addBinding(binding);

        javax.swing.GroupLayout pnlGeneralLayout = new javax.swing.GroupLayout(pnlGeneral);
        pnlGeneral.setLayout(pnlGeneralLayout);
        pnlGeneralLayout.setHorizontalGroup(
            pnlGeneralLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlGeneralLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(pnlGeneralLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel2)
                    .addComponent(jLabel1)
                    .addComponent(lbFilenamesForJobs)
                    .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 130, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lbEnableQRCodes)
                    .addComponent(lbFastQRCodes))
                .addGap(47, 47, 47)
                .addGroup(pnlGeneralLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(cbSandboxLaserScript1)
                    .addComponent(cbSandboxLaserScript)
                    .addComponent(jTextFieldLabName, javax.swing.GroupLayout.DEFAULT_SIZE, 258, Short.MAX_VALUE)
                    .addComponent(cbDefaultMapping, 0, 258, Short.MAX_VALUE)
                    .addComponent(cbEnableQRCodes)
                    .addComponent(cbFastQRCodes))
                .addContainerGap())
        );
        pnlGeneralLayout.setVerticalGroup(
            pnlGeneralLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlGeneralLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(pnlGeneralLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel1)
                    .addComponent(cbDefaultMapping, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(pnlGeneralLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(cbSandboxLaserScript)
                    .addComponent(jLabel2))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(pnlGeneralLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(lbFilenamesForJobs)
                    .addComponent(cbSandboxLaserScript1))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(pnlGeneralLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jTextFieldLabName, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel3))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(pnlGeneralLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(lbEnableQRCodes)
                    .addComponent(cbEnableQRCodes))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(pnlGeneralLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(cbFastQRCodes)
                    .addComponent(lbFastQRCodes))
                .addGap(23, 23, 23))
        );

        tpSettings.addTab("General", pnlGeneral);

        pnlFacebook.setToolTipText(""); // NOI18N

        lblFabLabLocationFacebookId.setText("FabLab location facebook id:");

        txtfFabLabLocationFacebookId.setToolTipText("<html>Specify the id of your FabLab Facebook site. It will be referenced <br>\n in each of your posts. Leave empty to disable the referenciation. The id can be found in the URL<br>\nof the Facebook site, e.g. if your URL is https://www.facebook.com/pages/Fablab-Aachen/191850277562397 <br>\nenter 191850277562397 in this field.</html>"); // NOI18N

        binding = org.jdesktop.beansbinding.Bindings.createAutoBinding(org.jdesktop.beansbinding.AutoBinding.UpdateStrategy.READ_WRITE, this, org.jdesktop.beansbinding.ELProperty.create("${preferences.fabLabLocationFacebookId}"), txtfFabLabLocationFacebookId, org.jdesktop.beansbinding.BeanProperty.create("text"));
        bindingGroup.addBinding(binding);

        javax.swing.GroupLayout pnlFacebookLayout = new javax.swing.GroupLayout(pnlFacebook);
        pnlFacebook.setLayout(pnlFacebookLayout);
        pnlFacebookLayout.setHorizontalGroup(
            pnlFacebookLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlFacebookLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(lblFabLabLocationFacebookId)
                .addGap(18, 18, 18)
                .addComponent(txtfFabLabLocationFacebookId, javax.swing.GroupLayout.DEFAULT_SIZE, 280, Short.MAX_VALUE)
                .addContainerGap())
        );
        pnlFacebookLayout.setVerticalGroup(
            pnlFacebookLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlFacebookLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(pnlFacebookLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lblFabLabLocationFacebookId)
                    .addComponent(txtfFabLabLocationFacebookId, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(160, Short.MAX_VALUE))
        );

        tpSettings.addTab("Facebook", pnlFacebook);

        pnlThingiverse.setToolTipText(""); // NOI18N

        lblLasercutterTags.setText("Lasercutter tags:");

        txtfLasercutterTags.setToolTipText("<html>Specify the id of your FabLab Facebook site. It will be referenced <br>\n in each of your posts. Leave empty to disable the referenciation. The id can be found in the URL<br>\nof the Facebook site, e.g. if your URL is https://www.facebook.com/pages/Fablab-Aachen/191850277562397 <br>\nenter 191850277562397 in this field.</html>"); // NOI18N

        binding = org.jdesktop.beansbinding.Bindings.createAutoBinding(org.jdesktop.beansbinding.AutoBinding.UpdateStrategy.READ_WRITE, this, org.jdesktop.beansbinding.ELProperty.create("${preferences.laserCutterTags}"), txtfLasercutterTags, org.jdesktop.beansbinding.BeanProperty.create("text"));
        bindingGroup.addBinding(binding);

        lblSupportedExtensions.setText("Supported extensions:");

        txtfSupportedExtensions.setToolTipText("<html>Specify the id of your FabLab Facebook site. It will be referenced <br>\n in each of your posts. Leave empty to disable the referenciation. The id can be found in the URL<br>\nof the Facebook site, e.g. if your URL is https://www.facebook.com/pages/Fablab-Aachen/191850277562397 <br>\nenter 191850277562397 in this field.</html>"); // NOI18N

        binding = org.jdesktop.beansbinding.Bindings.createAutoBinding(org.jdesktop.beansbinding.AutoBinding.UpdateStrategy.READ_WRITE, this, org.jdesktop.beansbinding.ELProperty.create("${preferences.supportedExtensions}"), txtfSupportedExtensions, org.jdesktop.beansbinding.BeanProperty.create("text"));
        bindingGroup.addBinding(binding);

        javax.swing.GroupLayout pnlThingiverseLayout = new javax.swing.GroupLayout(pnlThingiverse);
        pnlThingiverse.setLayout(pnlThingiverseLayout);
        pnlThingiverseLayout.setHorizontalGroup(
            pnlThingiverseLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlThingiverseLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(pnlThingiverseLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(lblSupportedExtensions)
                    .addComponent(lblLasercutterTags))
                .addGap(18, 18, 18)
                .addGroup(pnlThingiverseLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(txtfLasercutterTags, javax.swing.GroupLayout.DEFAULT_SIZE, 308, Short.MAX_VALUE)
                    .addComponent(txtfSupportedExtensions, javax.swing.GroupLayout.DEFAULT_SIZE, 308, Short.MAX_VALUE))
                .addContainerGap())
        );
        pnlThingiverseLayout.setVerticalGroup(
            pnlThingiverseLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlThingiverseLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(pnlThingiverseLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lblLasercutterTags)
                    .addComponent(txtfLasercutterTags, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(pnlThingiverseLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lblSupportedExtensions)
                    .addComponent(txtfSupportedExtensions, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(134, Short.MAX_VALUE))
        );

        tpSettings.addTab("Thingiverse", pnlThingiverse);

        pnlFabqr.setToolTipText(""); // NOI18N

        lblFabqrUrl.setText("FabQR URL, with ending slash:");

        txtFabqrUrl.setToolTipText("<html>Specify the id of your FabLab Facebook site. It will be referenced <br>\n in each of your posts. Leave empty to disable the referenciation. The id can be found in the URL<br>\nof the Facebook site, e.g. if your URL is https://www.facebook.com/pages/Fablab-Aachen/191850277562397 <br>\nenter 191850277562397 in this field.</html>"); // NOI18N

        binding = org.jdesktop.beansbinding.Bindings.createAutoBinding(org.jdesktop.beansbinding.AutoBinding.UpdateStrategy.READ_WRITE, this, org.jdesktop.beansbinding.ELProperty.create("${preferences.fabqrURL}"), txtFabqrUrl, org.jdesktop.beansbinding.BeanProperty.create("text"));
        bindingGroup.addBinding(binding);

        cbFabqrActive.setText("FabQR active");

        binding = org.jdesktop.beansbinding.Bindings.createAutoBinding(org.jdesktop.beansbinding.AutoBinding.UpdateStrategy.READ_WRITE, this, org.jdesktop.beansbinding.ELProperty.create("${preferences.fabqrActive}"), cbFabqrActive, org.jdesktop.beansbinding.BeanProperty.create("selected"));
        bindingGroup.addBinding(binding);

        javax.swing.GroupLayout pnlFabqrLayout = new javax.swing.GroupLayout(pnlFabqr);
        pnlFabqr.setLayout(pnlFabqrLayout);
        pnlFabqrLayout.setHorizontalGroup(
            pnlFabqrLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlFabqrLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(pnlFabqrLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(lblFabqrUrl)
                    .addComponent(txtFabqrUrl, javax.swing.GroupLayout.DEFAULT_SIZE, 435, Short.MAX_VALUE)
                    .addComponent(cbFabqrActive))
                .addContainerGap())
        );
        pnlFabqrLayout.setVerticalGroup(
            pnlFabqrLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlFabqrLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(lblFabqrUrl)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(txtFabqrUrl, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(cbFabqrActive)
                .addContainerGap(99, Short.MAX_VALUE))
        );

        tpSettings.addTab("FabQR", pnlFabqr);

        btOk.setText("OK");
        btOk.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btOkActionPerformed(evt);
            }
        });

        btCancel.setText("Cancel");
        btCancel.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btCancelActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(tpSettings, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 460, Short.MAX_VALUE)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap(320, Short.MAX_VALUE)
                .addComponent(btCancel)
                .addGap(18, 18, 18)
                .addComponent(btOk)
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(tpSettings, javax.swing.GroupLayout.DEFAULT_SIZE, 219, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(btOk)
                    .addComponent(btCancel))
                .addContainerGap())
        );

        tpSettings.getAccessibleContext().setAccessibleName(bundle.getString("GENERAL")); // NOI18N

        bindingGroup.bind();

        pack();
    }// </editor-fold>//GEN-END:initComponents

  private void btCancelActionPerformed(java.awt.event.ActionEvent evt)//GEN-FIRST:event_btCancelActionPerformed
  {//GEN-HEADEREND:event_btCancelActionPerformed
    this.preferences = null;
    this.setVisible(false);
  }//GEN-LAST:event_btCancelActionPerformed

  private void btOkActionPerformed(java.awt.event.ActionEvent evt)//GEN-FIRST:event_btOkActionPerformed
  {//GEN-HEADEREND:event_btOkActionPerformed
    this.setVisible(false);
  }//GEN-LAST:event_btOkActionPerformed

  private void jTextFieldLabNameActionPerformed(java.awt.event.ActionEvent evt)//GEN-FIRST:event_jTextFieldLabNameActionPerformed
  {//GEN-HEADEREND:event_jTextFieldLabNameActionPerformed
    // TODO add your handling code here:
  }//GEN-LAST:event_jTextFieldLabNameActionPerformed

  
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btCancel;
    private javax.swing.JButton btOk;
    private javax.swing.JComboBox cbDefaultMapping;
    private javax.swing.JCheckBox cbEnableQRCodes;
    private javax.swing.JCheckBox cbFabqrActive;
    private javax.swing.JCheckBox cbFastQRCodes;
    private javax.swing.JCheckBox cbSandboxLaserScript;
    private javax.swing.JCheckBox cbSandboxLaserScript1;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JTextField jTextFieldLabName;
    private javax.swing.JLabel lbEnableQRCodes;
    private javax.swing.JLabel lbFastQRCodes;
    private javax.swing.JLabel lbFilenamesForJobs;
    private javax.swing.JLabel lblFabLabLocationFacebookId;
    private javax.swing.JLabel lblFabqrUrl;
    private javax.swing.JLabel lblLasercutterTags;
    private javax.swing.JLabel lblSupportedExtensions;
    private javax.swing.JPanel pnlFabqr;
    private javax.swing.JPanel pnlFacebook;
    private javax.swing.JPanel pnlGeneral;
    private javax.swing.JPanel pnlThingiverse;
    private javax.swing.JTabbedPane tpSettings;
    private javax.swing.JTextField txtFabqrUrl;
    private javax.swing.JTextField txtfFabLabLocationFacebookId;
    private javax.swing.JTextField txtfLasercutterTags;
    private javax.swing.JTextField txtfSupportedExtensions;
    private org.jdesktop.beansbinding.BindingGroup bindingGroup;
    // End of variables declaration//GEN-END:variables
}
