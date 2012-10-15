/**
 * This file is part of VisiCut.
 * Copyright (C) 2012 Thomas Oster <thomas.oster@rwth-aachen.de>
 * RWTH Aachen University - 52062 Aachen, Germany
 * 
 *     VisiCut is free software: you can redistribute it and/or modify
 *     it under the terms of the GNU Lesser General Public License as published by
 *     the Free Software Foundation, either version 3 of the License, or
 *     (at your option) any later version.
 * 
 *    VisiCut is distributed in the hope that it will be useful,
 *     but WITHOUT ANY WARRANTY; without even the implied warranty of
 *     MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 *     GNU Lesser General Public License for more details.
 * 
 *     You should have received a copy of the GNU Lesser General Public License
 *     along with VisiCut.  If not, see <http://www.gnu.org/licenses/>.
 **/
/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

/*
 * CamCalibrationDialog.java
 *
 * Created on 28.08.2011, 15:35:33
 */
package com.t_oster.visicut.gui;

import com.t_oster.liblasercut.LaserCutter;
import com.t_oster.liblasercut.LaserJob;
import com.t_oster.liblasercut.LaserProperty;
import com.t_oster.liblasercut.VectorPart;
import com.t_oster.liblasercut.platform.Util;
import com.t_oster.visicut.VisicutModel;
import com.t_oster.visicut.managers.LaserPropertyManager;
import com.t_oster.visicut.misc.Helper;
import com.t_oster.visicut.model.LaserDevice;
import com.t_oster.visicut.model.VectorProfile;
import java.awt.Dimension;
import java.awt.Point;
import java.awt.event.MouseWheelEvent;
import java.awt.geom.AffineTransform;
import java.awt.geom.Point2D;
import java.awt.geom.Rectangle2D;
import java.awt.image.BufferedImage;
import java.net.URL;
import javax.imageio.ImageIO;
import javax.swing.JOptionPane;

/**
 *
 * @author Thomas Oster <thomas.oster@rwth-aachen.de>
 */
public class CamCalibrationDialog extends javax.swing.JDialog
{

  protected String imageURL = null;

  /**
   * Get the value of imageURL
   *
   * @return the value of imageURL
   */
  public String getImageURL()
  {
    return imageURL;
  }

  /**
   * Set the value of imageURL
   *
   * @param imageURL new value of imageURL
   */
  public void setImageURL(String imageURL)
  {
    this.imageURL = imageURL;
  }

  protected BufferedImage backgroundImage = null;
  public static final String PROP_BACKGROUNDIMAGE = "backgroundImage";
  private LaserDevice ld = VisicutModel.getInstance().getSelectedLaserDevice();
  private Point2D.Double laserUpperLeft = new Point2D.Double(0, 0);
  private Point2D.Double laserLowerRight = new Point2D.Double(ld.getLaserCutter().getBedWidth(), ld.getLaserCutter().getBedHeight());


  /**
   * Get the value of backgroundImage
   *
   * @return the value of backgroundImage
   */
  public BufferedImage getBackgroundImage()
  {
    return backgroundImage;
  }

  /**
   * Set the value of backgroundImage
   *
   * @param backgroundImage new value of backgroundImage
   */
  public void setBackgroundImage(BufferedImage backgroundImage)
  {
    this.backgroundImage = backgroundImage;
    this.calibrationPanel1.setBackgroundImage(backgroundImage);
    //Check if a point is not in the Image (thus not reachable anymore)
    for (Point p :this.calibrationPanel1.getPointList())
    {
      if (p.x >= backgroundImage.getWidth() || p.y >= backgroundImage.getHeight())
      {
       calibrationPanel1.setPointList(new Point[]{
         new Point(20*backgroundImage.getWidth()/100,20*backgroundImage.getHeight()/100),
         new Point(80*backgroundImage.getWidth()/100,80*backgroundImage.getHeight()/100)
       });
       break;
      }
    }
  }
  
  protected AffineTransform currentTransformation = currentTransformation = AffineTransform.getScaleInstance(0.01, 0.01);
  public static final String PROP_CURRENTTRANSFORMATION = "currentTransformation";

  private void refreshImagePoints()
  {
    Point imageUpperLeft = (Point) laserUpperLeft.clone();
    Point imageLowerRight = (Point) laserLowerRight.clone();
    if (this.getResultingTransformation() != null)
    {
      AffineTransform laser2img = this.getResultingTransformation();
      laser2img.transform(imageUpperLeft, imageUpperLeft);
      laser2img.transform(imageLowerRight, imageLowerRight);
    }
    this.calibrationPanel1.setPointList(new Point[]
      {
        imageUpperLeft, imageLowerRight
      });
  }

  /**
   * Get the value of currentTransformation
   *
   * @return the value of currentTransformation
   */
  public AffineTransform getCurrentTransformation()
  {
    Point[] img = this.calibrationPanel1.getPointList();
    return Helper.getTransform(
      new Rectangle2D.Double(laserUpperLeft.x, laserUpperLeft.y, laserLowerRight.x - laserUpperLeft.x, laserLowerRight.y - laserUpperLeft.y),
      new Rectangle2D.Double(img[0].x, img[0].y, img[1].x - img[0].x, img[1].y - img[0].y));
  }
  protected AffineTransform resultingTransformation = null;
  public static final String PROP_RESULTINGTRANSFORMATION = "resultingTransformation";

  /**
   * Get the value of resultingTransformation
   *
   * @return the value of resultingTransformation
   */
  public AffineTransform getResultingTransformation()
  {
    return resultingTransformation;
  }

  private VectorProfile profile = null;
  public void setVectorProfile(VectorProfile p)
  {
    this.profile = p;
  }
  
  /**
   * Set the value of resultingTransformation
   *
   * @param resultingTransformation new value of resultingTransformation
   */
  public void setResultingTransformation(AffineTransform resultingTransformation)
  {
    AffineTransform oldResultingTransformation = this.resultingTransformation;
    this.resultingTransformation = resultingTransformation;
    firePropertyChange(PROP_RESULTINGTRANSFORMATION, oldResultingTransformation, resultingTransformation);
    this.refreshImagePoints();
  }

  public CamCalibrationDialog()
  {
    this(null, true);
  }

  /** Creates new form CamCalibrationDialog */
  public CamCalibrationDialog(java.awt.Frame parent, boolean modal)
  {
    super(parent, modal);
    initComponents();
    LaserCutter lc = VisicutModel.getInstance().getSelectedLaserDevice().getLaserCutter();
    this.calibrationPanel1.setAreaSize(new Point2D.Double(lc.getBedWidth(), lc.getBedHeight()));
  }

  /** This method is called from within the constructor to
   * initialize the form.
   * WARNING: Do NOT modify this code. The content of this method is
   * always regenerated by the Form Editor.
   */
  @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {
        bindingGroup = new org.jdesktop.beansbinding.BindingGroup();

        jPanel1 = new javax.swing.JPanel();
        calibrationPanel1 = new com.t_oster.visicut.gui.beans.CalibrationPanel();
        okButton = new javax.swing.JButton();
        cancelButton = new javax.swing.JButton();
        sendButton = new javax.swing.JButton();
        captureButton = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        java.util.ResourceBundle bundle = java.util.ResourceBundle.getBundle("com/t_oster/visicut/gui/resources/CamCalibrationDialog"); // NOI18N
        setTitle(bundle.getString("TITLE")); // NOI18N
        setName("Form"); // NOI18N

        org.jdesktop.application.ResourceMap resourceMap = org.jdesktop.application.Application.getInstance(com.t_oster.visicut.gui.VisicutApp.class).getContext().getResourceMap(CamCalibrationDialog.class);
        jPanel1.setBorder(javax.swing.BorderFactory.createTitledBorder(resourceMap.getString("jPanel1.border.title"))); // NOI18N
        jPanel1.setName("jPanel1"); // NOI18N

        calibrationPanel1.setName("calibrationPanel1"); // NOI18N

        org.jdesktop.beansbinding.Binding binding = org.jdesktop.beansbinding.Bindings.createAutoBinding(org.jdesktop.beansbinding.AutoBinding.UpdateStrategy.READ, this, org.jdesktop.beansbinding.ELProperty.create("${laserCam.capturedImage}"), calibrationPanel1, org.jdesktop.beansbinding.BeanProperty.create("backgroundImage"), "CamImageToPanel");
        bindingGroup.addBinding(binding);

        calibrationPanel1.addMouseWheelListener(new java.awt.event.MouseWheelListener() {
            public void mouseWheelMoved(java.awt.event.MouseWheelEvent evt) {
                calibrationPanel1MouseWheelMoved(evt);
            }
        });

        javax.swing.GroupLayout calibrationPanel1Layout = new javax.swing.GroupLayout(calibrationPanel1);
        calibrationPanel1.setLayout(calibrationPanel1Layout);
        calibrationPanel1Layout.setHorizontalGroup(
            calibrationPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 826, Short.MAX_VALUE)
        );
        calibrationPanel1Layout.setVerticalGroup(
            calibrationPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 367, Short.MAX_VALUE)
        );

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(calibrationPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addComponent(calibrationPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );

        okButton.setText(resourceMap.getString("okButton.text")); // NOI18N
        okButton.setName("okButton"); // NOI18N
        okButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                okButtonActionPerformed(evt);
            }
        });

        cancelButton.setText(resourceMap.getString("cancelButton.text")); // NOI18N
        cancelButton.setName("cancelButton"); // NOI18N
        cancelButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cancelButtonActionPerformed(evt);
            }
        });

        sendButton.setText(resourceMap.getString("sendButton.text")); // NOI18N
        sendButton.setName("sendButton"); // NOI18N
        sendButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                sendButtonActionPerformed(evt);
            }
        });

        captureButton.setText(resourceMap.getString("captureButton.text")); // NOI18N
        captureButton.setName("captureButton"); // NOI18N
        captureButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                captureButtonActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap(473, Short.MAX_VALUE)
                .addComponent(captureButton)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(sendButton)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(cancelButton)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(okButton, javax.swing.GroupLayout.PREFERRED_SIZE, 90, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(15, 15, 15))
            .addGroup(layout.createSequentialGroup()
                .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(cancelButton)
                    .addComponent(okButton, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(sendButton)
                    .addComponent(captureButton)))
        );

        bindingGroup.bind();

        pack();
    }// </editor-fold>//GEN-END:initComponents

private void okButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_okButtonActionPerformed
  this.setResultingTransformation(this.getCurrentTransformation());
  this.setVisible(false);
}//GEN-LAST:event_okButtonActionPerformed

private void cancelButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cancelButtonActionPerformed
  this.refreshImagePoints();
  this.setVisible(false);
}//GEN-LAST:event_cancelButtonActionPerformed

private void sendButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_sendButtonActionPerformed

  try
  {
    VisicutModel vm = VisicutModel.getInstance();
    LaserDevice laserDevice = vm.getSelectedLaserDevice();
    if (laserDevice == null)
    {
      throw new Exception(java.util.ResourceBundle.getBundle("com/t_oster/visicut/gui/resources/CamCalibrationDialog").getString("NO LASERCUTTER SELECTED"));
    }
    VectorPart vp = null;
    for (LaserProperty lp : LaserPropertyManager.getInstance().getLaserProperties(laserDevice, vm.getMaterial(), profile, vm.getMaterialThickness()))
    {
      if (vp == null)
      {
        vp = new VectorPart(lp, profile.getDPI());
      }
      else
      {
        vp.setProperty(lp);
      }
      int size = (int) Util.mm2px(100, profile.getDPI());
      for (Point2D p : new Point2D[]
        {
          laserUpperLeft, laserLowerRight
        })
      {
        int x = (int) Util.mm2px(p.getX(), profile.getDPI());
        int y = (int) Util.mm2px(p.getY(), profile.getDPI());
        vp.moveto(x - size / 2, y);
        vp.lineto(x + size / 2, y);
        vp.moveto(x, y - size / 2);
        vp.lineto(x, y + size / 2);
      }
    }
    LaserJob job = new LaserJob(java.util.ResourceBundle.getBundle("com/t_oster/visicut/gui/resources/CamCalibrationDialog").getString("CALIBRATION"), java.util.ResourceBundle.getBundle("com/t_oster/visicut/gui/resources/CamCalibrationDialog").getString("VISICUT CALIBRATION PAGE"), "visicut");
    job.addPart(vp);
    laserDevice.getLaserCutter().sendJob(job);
    JOptionPane.showMessageDialog(this, java.util.ResourceBundle.getBundle("com/t_oster/visicut/gui/resources/CamCalibrationDialog").getString("PLEASE PRESS 'START' ON THE LASERCUTTER"));
  }
  catch (Exception e)
  {
    JOptionPane.showMessageDialog(this, java.util.ResourceBundle.getBundle("com/t_oster/visicut/gui/resources/CamCalibrationDialog").getString("ERROR SENDING PAGE: ") + e.getLocalizedMessage(), java.util.ResourceBundle.getBundle("com/t_oster/visicut/gui/resources/CamCalibrationDialog").getString("ERROR"), JOptionPane.ERROR_MESSAGE);
  }
}//GEN-LAST:event_sendButtonActionPerformed

private void calibrationPanel1MouseWheelMoved(java.awt.event.MouseWheelEvent evt) {//GEN-FIRST:event_calibrationPanel1MouseWheelMoved
  if (evt.getScrollType() == MouseWheelEvent.WHEEL_UNIT_SCROLL)
  {
    this.calibrationPanel1.setZoom(this.calibrationPanel1.getZoom() - (evt.getUnitsToScroll() * this.calibrationPanel1.getZoom() / 32));
  }
}//GEN-LAST:event_calibrationPanel1MouseWheelMoved

private void captureButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_captureButtonActionPerformed
new Thread()
    {

      @Override
      public void run()
      {
        CamCalibrationDialog.this.captureButton.setEnabled(false);
        try
        {
          URL src = new URL(imageURL);
          if (src != null)
          {
            BufferedImage back = ImageIO.read(src);
            CamCalibrationDialog.this.setBackgroundImage(back);
          }
        }
        catch (Exception ex)
        {
          JOptionPane.showMessageDialog(CamCalibrationDialog.this, java.util.ResourceBundle.getBundle("com/t_oster/visicut/gui/resources/CamCalibrationDialog").getString("ERROR LOADING IMAGE:") + ex.getLocalizedMessage(), java.util.ResourceBundle.getBundle("com/t_oster/visicut/gui/resources/CamCalibrationDialog").getString("ERROR"), JOptionPane.ERROR_MESSAGE);
        }
        CamCalibrationDialog.this.captureButton.setEnabled(true);
      }
    }.start();
}//GEN-LAST:event_captureButtonActionPerformed

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private com.t_oster.visicut.gui.beans.CalibrationPanel calibrationPanel1;
    private javax.swing.JButton cancelButton;
    private javax.swing.JButton captureButton;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JButton okButton;
    private javax.swing.JButton sendButton;
    private org.jdesktop.beansbinding.BindingGroup bindingGroup;
    // End of variables declaration//GEN-END:variables
}
