
package ec.edu.espe.dailyDev.view;

import ec.edu.espe.dailyDev.model.Task;
import java.awt.Point;

/**
 *
 * @author Olivier Paspuel
 */
public class FrmCompleteTask extends javax.swing.JFrame {

    private Point mPoint;

    /**
     * Creates new form FrmCompleteTask
     */
    public FrmCompleteTask() {
        initComponents();
        this.setLocationRelativeTo(null);
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        actionBar = new javax.swing.JPanel();
        exitBtn = new javax.swing.JPanel();
        jLabel5 = new javax.swing.JLabel();
        minimizeBtn = new javax.swing.JPanel();
        jLabel6 = new javax.swing.JLabel();
        draggablePnl = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        idTxt = new javax.swing.JTextField();
        completeBtn = new javax.swing.JPanel();
        jLabel3 = new javax.swing.JLabel();
        cancelBtn = new javax.swing.JPanel();
        jLabel4 = new javax.swing.JLabel();
        jLabel1 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setUndecorated(true);
        getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jPanel1.setBackground(new java.awt.Color(255, 255, 255));
        jPanel1.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        actionBar.setBackground(new java.awt.Color(255, 255, 255));
        actionBar.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        exitBtn.setBackground(new java.awt.Color(255, 255, 255));
        exitBtn.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                exitBtnMousePressed(evt);
            }
        });

        jLabel5.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel5.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imgs/closeIcon.png"))); // NOI18N

        javax.swing.GroupLayout exitBtnLayout = new javax.swing.GroupLayout(exitBtn);
        exitBtn.setLayout(exitBtnLayout);
        exitBtnLayout.setHorizontalGroup(
            exitBtnLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 40, Short.MAX_VALUE)
            .addGroup(exitBtnLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addComponent(jLabel5, javax.swing.GroupLayout.DEFAULT_SIZE, 40, Short.MAX_VALUE))
        );
        exitBtnLayout.setVerticalGroup(
            exitBtnLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 41, Short.MAX_VALUE)
            .addGroup(exitBtnLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addComponent(jLabel5, javax.swing.GroupLayout.DEFAULT_SIZE, 41, Short.MAX_VALUE))
        );

        actionBar.add(exitBtn, new org.netbeans.lib.awtextra.AbsoluteConstraints(50, 0, 40, 41));

        minimizeBtn.setBackground(new java.awt.Color(255, 255, 255));
        minimizeBtn.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                minimizeBtnMousePressed(evt);
            }
        });

        jLabel6.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel6.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imgs/minimizeIcon.png"))); // NOI18N

        javax.swing.GroupLayout minimizeBtnLayout = new javax.swing.GroupLayout(minimizeBtn);
        minimizeBtn.setLayout(minimizeBtnLayout);
        minimizeBtnLayout.setHorizontalGroup(
            minimizeBtnLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jLabel6, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 40, Short.MAX_VALUE)
        );
        minimizeBtnLayout.setVerticalGroup(
            minimizeBtnLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jLabel6, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 41, Short.MAX_VALUE)
        );

        actionBar.add(minimizeBtn, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 0, 40, -1));

        jPanel1.add(actionBar, new org.netbeans.lib.awtextra.AbsoluteConstraints(400, 0, -1, -1));

        draggablePnl.addMouseMotionListener(new java.awt.event.MouseMotionAdapter() {
            public void mouseDragged(java.awt.event.MouseEvent evt) {
                draggablePnlMouseDragged(evt);
            }
        });
        draggablePnl.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                draggablePnlMousePressed(evt);
            }
        });
        jPanel1.add(draggablePnl, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 410, 40));

        jLabel2.setFont(new java.awt.Font("Roboto Light", 0, 14)); // NOI18N
        jLabel2.setText("Task ID");
        jPanel1.add(jLabel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(6, 62, -1, -1));
        jPanel1.add(idTxt, new org.netbeans.lib.awtextra.AbsoluteConstraints(156, 59, 318, -1));

        completeBtn.setBackground(new java.awt.Color(51, 51, 51));
        completeBtn.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        completeBtn.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                completeBtnMousePressed(evt);
            }
        });

        jLabel3.setFont(new java.awt.Font("Roboto Medium", 0, 14)); // NOI18N
        jLabel3.setForeground(new java.awt.Color(255, 255, 255));
        jLabel3.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel3.setText("COMPLETE");

        javax.swing.GroupLayout completeBtnLayout = new javax.swing.GroupLayout(completeBtn);
        completeBtn.setLayout(completeBtnLayout);
        completeBtnLayout.setHorizontalGroup(
            completeBtnLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jLabel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        completeBtnLayout.setVerticalGroup(
            completeBtnLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jLabel3, javax.swing.GroupLayout.DEFAULT_SIZE, 30, Short.MAX_VALUE)
        );

        jPanel1.add(completeBtn, new org.netbeans.lib.awtextra.AbsoluteConstraints(262, 110, 90, 30));

        cancelBtn.setBackground(new java.awt.Color(51, 51, 51));
        cancelBtn.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        cancelBtn.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                cancelBtnMousePressed(evt);
            }
        });

        jLabel4.setFont(new java.awt.Font("Roboto Medium", 0, 14)); // NOI18N
        jLabel4.setForeground(new java.awt.Color(255, 255, 255));
        jLabel4.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel4.setText("CANCEL");

        javax.swing.GroupLayout cancelBtnLayout = new javax.swing.GroupLayout(cancelBtn);
        cancelBtn.setLayout(cancelBtnLayout);
        cancelBtnLayout.setHorizontalGroup(
            cancelBtnLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jLabel4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        cancelBtnLayout.setVerticalGroup(
            cancelBtnLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jLabel4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        jPanel1.add(cancelBtn, new org.netbeans.lib.awtextra.AbsoluteConstraints(382, 110, 80, 30));

        jLabel1.setFont(new java.awt.Font("Roboto Light", 0, 18)); // NOI18N
        jLabel1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel1.setText("Complete Task");
        jPanel1.add(jLabel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 490, 41));

        getContentPane().add(jPanel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 490, -1));

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void completeBtnMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_completeBtnMousePressed

        Task.completeTask(idTxt.getText());
    }//GEN-LAST:event_completeBtnMousePressed

    private void exitBtnMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_exitBtnMousePressed
        this.dispose();
    }//GEN-LAST:event_exitBtnMousePressed

    private void minimizeBtnMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_minimizeBtnMousePressed
        this.setState(FrmCompleteTask.ICONIFIED);
    }//GEN-LAST:event_minimizeBtnMousePressed

    private void draggablePnlMouseDragged(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_draggablePnlMouseDragged
        int currentX = this.getLocation().x;
        int currentY = this.getLocation().y;
        int moveX = (currentX + evt.getX()) - (currentX + mPoint.x);
        int moveY = (currentY + evt.getY()) - (currentY + mPoint.y);

        int x = currentX + moveX;
        int y = currentY + moveY;
    }//GEN-LAST:event_draggablePnlMouseDragged

    private void draggablePnlMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_draggablePnlMousePressed
        mPoint = evt.getPoint();
        getComponentAt(mPoint);
    }//GEN-LAST:event_draggablePnlMousePressed

    private void cancelBtnMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_cancelBtnMousePressed
        this.dispose();
    }//GEN-LAST:event_cancelBtnMousePressed

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
            java.util.logging.Logger.getLogger(FrmCompleteTask.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(FrmCompleteTask.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(FrmCompleteTask.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(FrmCompleteTask.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new FrmCompleteTask().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JPanel actionBar;
    private javax.swing.JPanel cancelBtn;
    private javax.swing.JPanel completeBtn;
    private javax.swing.JLabel draggablePnl;
    private javax.swing.JPanel exitBtn;
    private javax.swing.JTextField idTxt;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel minimizeBtn;
    // End of variables declaration//GEN-END:variables
}
