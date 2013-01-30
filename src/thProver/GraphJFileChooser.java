package thProver;

import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.Reader;
import java.io.StringReader;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JDialog;
import javax.swing.JFileChooser;
import javax.swing.JOptionPane;
import javax.swing.filechooser.FileNameExtensionFilter;
import thProver.parser.CNFParser;
import thProver.parserTptp.CNFParserTptp;

/**
 * Frame to export graph with gui.
 * 
 * @author Alessandro Gottoli vr352595
 */
public class GraphJFileChooser extends javax.swing.JFrame {

    //private final String GRAFO_TEXT_INIT = "Parsing...";
    //private final String CC_TEXT_INIT = "Given Clause Cicle...";
    //private final String SAT_TEXT_INIT = " La formula risulta";
    //private final String START_BUTTON_INIT = "Parse";
    //private final String START_BUTTON_SAT = "Prove";
    //private Boolean stop;
    //private String formula;
    //private File file;
    private String dir;
    private String name;
    private GivenClauseProver prover;
    //private boolean useFile = false; // c'è già interactive...

    /**
     * Creates new form JFileChooser
     */
    public GraphJFileChooser(GivenClauseProver prover, String grafo, String dir, String name) {
        initComponents();
        log.setText(grafo);
        this.prover = prover;
        if (dir == null)
            this.dir = ".";
        else
            this.dir = dir;
        if (name == null)
            this.name = "input.txt";
        else
            this.name = name;
        setLocationRelativeTo(null); // mette al centro 
        //PropertyChangeSupport pcs = new PropertyChangeSupport(stop);
/*		fc = new JFileChooserDemo();
         FileNameExtensionFilter filter = new FileNameExtensionFilter("Formule", "txt", "cnf", "formula");
         fc.setFileFilter(filter);
         */
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        //if (dir != null) {
            //    fc = new JFileChooser(dir);
            //} else {
            fc = new JFileChooser(System.getProperty("user.dir"));
            //}
        inserText = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        log = new javax.swing.JTextArea();
        jMenuBar1 = new javax.swing.JMenuBar();
        fileMenu = new javax.swing.JMenu();
        saveItem = new javax.swing.JMenuItem();
        exportDot2Jpg = new javax.swing.JMenuItem();
        exportDot2Ps = new javax.swing.JMenuItem();
        exportDot2Pdf = new javax.swing.JMenuItem();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("Dot Graph Prove");

        inserText.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        inserText.setText("graph in 'dot' format:");

        log.setColumns(20);
        log.setLineWrap(true);
        log.setRows(5);
        log.addContainerListener(new java.awt.event.ContainerAdapter() {
            public void componentAdded(java.awt.event.ContainerEvent evt) {
                logComponentAdded(evt);
            }
        });
        log.addPropertyChangeListener(new java.beans.PropertyChangeListener() {
            public void propertyChange(java.beans.PropertyChangeEvent evt) {
                logPropertyChange(evt);
            }
        });
        log.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                logKeyPressed(evt);
            }
            public void keyReleased(java.awt.event.KeyEvent evt) {
                logKeyReleased(evt);
            }
        });
        jScrollPane1.setViewportView(log);

        fileMenu.setText("File");
        fileMenu.addAncestorListener(new javax.swing.event.AncestorListener() {
            public void ancestorAdded(javax.swing.event.AncestorEvent evt) {
                fileMenuAncestorAdded(evt);
            }
            public void ancestorRemoved(javax.swing.event.AncestorEvent evt) {
            }
            public void ancestorMoved(javax.swing.event.AncestorEvent evt) {
            }
        });

        saveItem.setText("Save");
        saveItem.setActionCommand("Save dot file");
        saveItem.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                saveItemActionPerformed(evt);
            }
        });
        fileMenu.add(saveItem);

        exportDot2Jpg.setText("Export to .jpg");
        exportDot2Jpg.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                exportDot2JpgActionPerformed(evt);
            }
        });
        fileMenu.add(exportDot2Jpg);

        exportDot2Ps.setText("Export to .ps");
        exportDot2Ps.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                exportDot2PsActionPerformed(evt);
            }
        });
        fileMenu.add(exportDot2Ps);

        exportDot2Pdf.setText("Export to .pdf");
        exportDot2Pdf.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                exportDot2PdfActionPerformed(evt);
            }
        });
        fileMenu.add(exportDot2Pdf);

        jMenuBar1.add(fileMenu);

        setJMenuBar(jMenuBar1);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 441, Short.MAX_VALUE)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(inserText, javax.swing.GroupLayout.PREFERRED_SIZE, 202, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 0, Short.MAX_VALUE)))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(inserText, javax.swing.GroupLayout.PREFERRED_SIZE, 22, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 264, Short.MAX_VALUE)
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

	private void fileMenuAncestorAdded(javax.swing.event.AncestorEvent evt) {//GEN-FIRST:event_fileMenuAncestorAdded
            // do nothing
	}//GEN-LAST:event_fileMenuAncestorAdded

	private void logComponentAdded(java.awt.event.ContainerEvent evt) {//GEN-FIRST:event_logComponentAdded
            // do nothing
	}//GEN-LAST:event_logComponentAdded

	private void saveItemActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_saveItemActionPerformed
            String nameNoExt = name;
            int index = name.lastIndexOf('.');
            if (index != -1)
                nameNoExt = name.substring(0, index);
            File file = new File(dir + "/" + nameNoExt + ".dot");
            fc.setSelectedFile(file);
            FileNameExtensionFilter filter = new FileNameExtensionFilter("DOT file", ".dot", ".DOT");
            fc.setFileFilter(filter);     

            int returnVal = fc.showSaveDialog(GraphJFileChooser.this);
            if (returnVal == JFileChooser.APPROVE_OPTION) {
                BufferedWriter out = null;
                //try {
                file = fc.getSelectedFile();
                Boolean savefile = true;
                if (file.exists()) {
                    //System.out.println("Il file " + file + " esiste già.");

                    // if file exists create a JOptionPane confirm dialog
                    // if user chooses YES then set savefile flag to true,
                    // otherwise leave it as false

                    Object[] options = {"YES", "NO"};

                    JOptionPane pane = new JOptionPane(file.toString()
                            + " already exists.\n"
                            + "Do you want to replace it?",
                            JOptionPane.WARNING_MESSAGE,
                            JOptionPane.DEFAULT_OPTION,
                            null,
                            options,
                            options[1]);

                    JDialog dialog = pane.createDialog(null, "Warning");

                    dialog.setVisible(true);
                    try {
                        Object selectedValue = pane.getValue();
                        if (!selectedValue.toString().equals("YES"))
                            savefile = false;
                    } catch (NullPointerException e) { // se chiudo la finestra dell'alert
                        savefile = false;
                    }
                }

                if (savefile)
                    try {
                        out = new BufferedWriter(new FileWriter(file));
                        out.write(log.getText());
                    } catch (IOException e) {
                    } finally {
                        try {
                            out.close();
                        } catch (IOException ex) {
                        }
                    }
            }
            
            fc.removeChoosableFileFilter(filter);

	}//GEN-LAST:event_saveItemActionPerformed

	private void logKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_logKeyPressed
	}//GEN-LAST:event_logKeyPressed

	private void logKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_logKeyReleased
            if (evt != null && log.isEditable()) {
                if (log.getText().length() == 0) {
                    saveItem.setEnabled(false);
                } else {
                    saveItem.setEnabled(true);
                }

            }
	}//GEN-LAST:event_logKeyReleased

	private void logPropertyChange(java.beans.PropertyChangeEvent evt) {//GEN-FIRST:event_logPropertyChange
	}//GEN-LAST:event_logPropertyChange

        /**
         * Export the graph in given format.
         * @param format 
         */
        private void exportDot2Format(String format) {
            //if (format == null || format == "")
            //    format = "jpg";
            String nameNoExt = name;
        int index = name.lastIndexOf('.');
        if (index != -1)
            nameNoExt = name.substring(0, index);
        File file = new File(dir + "/" + nameNoExt + "." + format);
        fc.setSelectedFile(file);
        
        int returnVal = fc.showSaveDialog(GraphJFileChooser.this);
        if (returnVal == JFileChooser.APPROVE_OPTION) {
            //BufferedWriter out = null;
            //try {
            file = fc.getSelectedFile();
            Boolean savefile = true;
            if (file.exists()) {
                //System.out.println("Il file " + file + " esiste già.");

                // if file exists create a JOptionPane confirm dialog
                // if user chooses YES then set savefile flag to true,
                // otherwise leave it as false

                Object[] options = {"YES", "NO"};

                JOptionPane pane = new JOptionPane(file.toString()
                        + " already exists.\n"
                        + "Do you want to replace it?",
                        JOptionPane.WARNING_MESSAGE,
                        JOptionPane.DEFAULT_OPTION,
                        null,
                        options,
                        options[1]);

                JDialog dialog = pane.createDialog(null, "Warning");

                dialog.setVisible(true);
                try {
                    Object selectedValue = pane.getValue();
                    if (!selectedValue.toString().equals("YES"))
                        savefile = false;
                } catch (NullPointerException e) { // se chiudo la finestra dell'alert
                    savefile = false;
                }
            }

            if (savefile) {
                //try {
                //out = new BufferedWriter(new FileWriter(file));
                //out.write(log.getText());
                //} catch (IOException e) {
                //} finally {
                //    try {
                //        out.close();
                //    } catch (IOException ex) {
                //    }
                //}
                prover.exportDot(file.getParent(), file.getName(), format, log.getText());
            }
    }
        }
    
    /**
     * Export the graph in JPG format.
     * @param evt 
     */
    private void exportDot2JpgActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_exportDot2JpgActionPerformed
        FileNameExtensionFilter filterJpg = new FileNameExtensionFilter("Image JPG", ".jpg", ".JPG", ".jpeg", ".JPEG");
        fc.setFileFilter(filterJpg);
        exportDot2Format("jpg");
        fc.removeChoosableFileFilter(filterJpg);
    }//GEN-LAST:event_exportDot2JpgActionPerformed

    /**
     * Export the graph in PS format.
     * @param evt 
     */
    private void exportDot2PsActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_exportDot2PsActionPerformed
        FileNameExtensionFilter filterPS = new FileNameExtensionFilter("PS file", ".ps", ".PS");
        fc.setFileFilter(filterPS);
        exportDot2Format("ps");
        fc.removeChoosableFileFilter(filterPS);
    }//GEN-LAST:event_exportDot2PsActionPerformed

    /**
     * Export the graph in PDF format.
     * @param evt 
     */
    private void exportDot2PdfActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_exportDot2PdfActionPerformed
        FileNameExtensionFilter filterPDF = new FileNameExtensionFilter("PDF file", ".pdf", ".PDF");  
        fc.setFileFilter(filterPDF);
        exportDot2Format("pdf");
        fc.removeChoosableFileFilter(filterPDF);
    }//GEN-LAST:event_exportDot2PdfActionPerformed

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JMenuItem exportDot2Jpg;
    private javax.swing.JMenuItem exportDot2Pdf;
    private javax.swing.JMenuItem exportDot2Ps;
    private javax.swing.JFileChooser fc;
    private javax.swing.JMenu fileMenu;
    private javax.swing.JLabel inserText;
    private javax.swing.JMenuBar jMenuBar1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTextArea log;
    private javax.swing.JMenuItem saveItem;
    // End of variables declaration//GEN-END:variables
}
