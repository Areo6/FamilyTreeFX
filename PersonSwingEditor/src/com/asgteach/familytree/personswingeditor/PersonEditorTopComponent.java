/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.asgteach.familytree.personswingeditor;

import com.asgteach.familytreefx.model.FamilyTreeManagerSwing;
import com.asgteach.familytreefx.model.PersonSwing.Gender;
import com.asgteach.familytreefx.model.PersonSwing;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Collection;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import org.netbeans.api.settings.ConvertAsProperties;
import org.openide.LifecycleManager;
import org.openide.awt.ActionID;
import org.openide.awt.ActionReference;
import org.openide.util.Lookup;
import org.openide.util.LookupEvent;
import org.openide.util.LookupListener;
import org.openide.windows.TopComponent;
import org.openide.util.NbBundle.Messages;
import org.openide.util.Utilities;
import org.openide.windows.WindowManager;

/**
 * Top component which displays something.
 */
@ConvertAsProperties(
        dtd = "-//com.asgteach.familytree.personswingeditor//PersonEditor//EN",
        autostore = false
)
@TopComponent.Description(
        preferredID = "PersonEditorTopComponent",
        iconBase = "com/asgteach/familytree/personswingeditor/personIcon.png",
        persistenceType = TopComponent.PERSISTENCE_ALWAYS
)
@TopComponent.Registration(mode = "editor", openAtStartup = true)
@ActionID(category = "Window", id = "com.asgteach.familytree.personswingeditor.PersonEditorTopComponent")
@ActionReference(path = "Menu/Window" /*, position = 333 */)
@TopComponent.OpenActionRegistration(
        displayName = "#CTL_PersonEditorAction",
        preferredID = "PersonEditorTopComponent"
)
@Messages({
    "CTL_PersonEditorAction=PersonEditor",
    "CTL_PersonEditorTopComponent=PersonEditor Window",
    "HINT_PersonEditorTopComponent=This is a PersonEditor window"
})
public final class PersonEditorTopComponent extends TopComponent {

    private FamilyTreeManagerSwing ftm;
    private PersonSwing thePerson = null;
    private static final Logger logger = Logger.getLogger(
            PersonEditorTopComponent.class.getName());
    private boolean changeOK = false;
    private Lookup.Result<PersonSwing> lookupResult = null;

    public PersonEditorTopComponent() {
        initComponents();
        setName(Bundle.CTL_PersonEditorTopComponent());
        setToolTipText(Bundle.HINT_PersonEditorTopComponent());

    }

    private void configureListeners() {
        // add action listener to Update button
        updateButton.addActionListener(updateListener);
        // add document listeners to textfields, textarea
        firstTextField3.getDocument().addDocumentListener(docListener);
        middleTextField3.getDocument().addDocumentListener(docListener);
        lastTextField3.getDocument().addDocumentListener(docListener);
        suffixTextField3.getDocument().addDocumentListener(docListener);
        notesTextArea3.getDocument().addDocumentListener(docListener);
        // add action listeners to radiobuttons
        maleButton3.addActionListener(radioButtonListener);
        femaleButton3.addActionListener(radioButtonListener);
        unknownButton3.addActionListener(radioButtonListener);
    }

     private void updateForm() {
        changeOK = false;
        updateButton.setEnabled(false);
        firstTextField3.setText(thePerson.getFirstname());
        middleTextField3.setText(thePerson.getMiddlename());
        lastTextField3.setText(thePerson.getLastname());
        suffixTextField3.setText(thePerson.getSuffix());
        switch (thePerson.getGender()) {
            case MALE:
                maleButton3.setSelected(true);
                break;
            case FEMALE:
                femaleButton3.setSelected(true);
                break;
            case UNKNOWN:
                unknownButton3.setSelected(true);
                break;
            default:
                break;
        }
        notesTextArea3.setText(thePerson.getNotes());
        changeOK = true;
    }

    private void clearForm() {
        updateButton.setEnabled(false);
        changeOK = false;
        firstTextField3.setText("");
        middleTextField3.setText("");
        lastTextField3.setText("");
        suffixTextField3.setText("");
        maleButton3.setSelected(false);
        femaleButton3.setSelected(false);
        unknownButton3.setSelected(false);
        genderButtonGroup.clearSelection();
        notesTextArea3.setText("");
    }

    private void updateModel() {
        if (!changeOK) {
            return;
        }
        updateButton.setEnabled(false);
        thePerson.setFirstname(firstTextField3.getText());
        thePerson.setMiddlename(middleTextField3.getText());
        thePerson.setLastname(lastTextField3.getText());
        thePerson.setSuffix(suffixTextField3.getText());
        if (maleButton3.isSelected()) {
            thePerson.setGender(Gender.MALE);
        } else if (femaleButton3.isSelected()) {
            thePerson.setGender(Gender.FEMALE);
        } else if (unknownButton3.isSelected()) {
            thePerson.setGender(Gender.UNKNOWN);
        }
        thePerson.setNotes(notesTextArea3.getText());
    }

    private void modify() {
        updateButton.setEnabled(true);
    }

    
    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        genderButtonGroup = new javax.swing.ButtonGroup();
        jPanel4 = new javax.swing.JPanel();
        jLabel17 = new javax.swing.JLabel();
        jLabel18 = new javax.swing.JLabel();
        jLabel19 = new javax.swing.JLabel();
        jLabel20 = new javax.swing.JLabel();
        firstTextField3 = new javax.swing.JTextField();
        middleTextField3 = new javax.swing.JTextField();
        lastTextField3 = new javax.swing.JTextField();
        suffixTextField3 = new javax.swing.JTextField();
        maleButton3 = new javax.swing.JRadioButton();
        femaleButton3 = new javax.swing.JRadioButton();
        unknownButton3 = new javax.swing.JRadioButton();
        jLabel4 = new javax.swing.JLabel();
        jScrollPane5 = new javax.swing.JScrollPane();
        notesTextArea3 = new javax.swing.JTextArea();
        updateButton = new javax.swing.JButton();

        org.openide.awt.Mnemonics.setLocalizedText(jLabel17, org.openide.util.NbBundle.getMessage(PersonEditorTopComponent.class, "PersonEditorTopComponent.jLabel17.text")); // NOI18N

        org.openide.awt.Mnemonics.setLocalizedText(jLabel18, org.openide.util.NbBundle.getMessage(PersonEditorTopComponent.class, "PersonEditorTopComponent.jLabel18.text")); // NOI18N

        org.openide.awt.Mnemonics.setLocalizedText(jLabel19, org.openide.util.NbBundle.getMessage(PersonEditorTopComponent.class, "PersonEditorTopComponent.jLabel19.text")); // NOI18N

        org.openide.awt.Mnemonics.setLocalizedText(jLabel20, org.openide.util.NbBundle.getMessage(PersonEditorTopComponent.class, "PersonEditorTopComponent.jLabel20.text")); // NOI18N

        genderButtonGroup.add(maleButton3);
        org.openide.awt.Mnemonics.setLocalizedText(maleButton3, org.openide.util.NbBundle.getMessage(PersonEditorTopComponent.class, "PersonEditorTopComponent.maleButton3.text")); // NOI18N

        genderButtonGroup.add(femaleButton3);
        org.openide.awt.Mnemonics.setLocalizedText(femaleButton3, org.openide.util.NbBundle.getMessage(PersonEditorTopComponent.class, "PersonEditorTopComponent.femaleButton3.text")); // NOI18N

        genderButtonGroup.add(unknownButton3);
        org.openide.awt.Mnemonics.setLocalizedText(unknownButton3, org.openide.util.NbBundle.getMessage(PersonEditorTopComponent.class, "PersonEditorTopComponent.unknownButton3.text")); // NOI18N

        org.openide.awt.Mnemonics.setLocalizedText(jLabel4, org.openide.util.NbBundle.getMessage(PersonEditorTopComponent.class, "PersonEditorTopComponent.jLabel4.text")); // NOI18N

        notesTextArea3.setColumns(20);
        notesTextArea3.setRows(5);
        jScrollPane5.setViewportView(notesTextArea3);

        javax.swing.GroupLayout jPanel4Layout = new javax.swing.GroupLayout(jPanel4);
        jPanel4.setLayout(jPanel4Layout);
        jPanel4Layout.setHorizontalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                    .addComponent(jScrollPane5, javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(jPanel4Layout.createSequentialGroup()
                            .addContainerGap()
                            .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel4Layout.createSequentialGroup()
                                        .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addComponent(jLabel18)
                                            .addComponent(jLabel19))
                                        .addGap(35, 35, 35))
                                    .addGroup(jPanel4Layout.createSequentialGroup()
                                        .addComponent(jLabel17)
                                        .addGap(28, 28, 28)))
                                .addGroup(jPanel4Layout.createSequentialGroup()
                                    .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                        .addComponent(jLabel4)
                                        .addComponent(jLabel20))
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)))
                            .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                .addComponent(lastTextField3, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, 290, Short.MAX_VALUE)
                                .addComponent(middleTextField3, javax.swing.GroupLayout.Alignment.LEADING)
                                .addComponent(firstTextField3, javax.swing.GroupLayout.Alignment.LEADING)
                                .addComponent(suffixTextField3)))
                        .addGroup(jPanel4Layout.createSequentialGroup()
                            .addGap(49, 49, 49)
                            .addComponent(maleButton3)
                            .addGap(44, 44, 44)
                            .addComponent(femaleButton3)
                            .addGap(47, 47, 47)
                            .addComponent(unknownButton3))))
                .addContainerGap(22, Short.MAX_VALUE))
        );
        jPanel4Layout.setVerticalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel17)
                    .addComponent(firstTextField3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(37, 37, 37)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel18)
                    .addComponent(middleTextField3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(46, 46, 46)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel19)
                    .addComponent(lastTextField3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(43, 43, 43)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(suffixTextField3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel20))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(unknownButton3)
                    .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(femaleButton3)
                        .addComponent(maleButton3)))
                .addGap(4, 4, 4)
                .addComponent(jLabel4)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane5, javax.swing.GroupLayout.PREFERRED_SIZE, 66, javax.swing.GroupLayout.PREFERRED_SIZE))
        );

        org.openide.awt.Mnemonics.setLocalizedText(updateButton, org.openide.util.NbBundle.getMessage(PersonEditorTopComponent.class, "PersonEditorTopComponent.updateButton.text")); // NOI18N
        updateButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                updateButtonActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jPanel4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 28, Short.MAX_VALUE))
            .addGroup(layout.createSequentialGroup()
                .addGap(36, 36, 36)
                .addComponent(updateButton)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jPanel4, javax.swing.GroupLayout.PREFERRED_SIZE, 329, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(29, 29, 29)
                .addComponent(updateButton)
                .addGap(0, 16, Short.MAX_VALUE))
        );
    }// </editor-fold>//GEN-END:initComponents

    private void updateButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_updateButtonActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_updateButtonActionPerformed

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JRadioButton femaleButton;
    private javax.swing.JRadioButton femaleButton1;
    private javax.swing.JRadioButton femaleButton2;
    private javax.swing.JRadioButton femaleButton3;
    private javax.swing.JTextField firstTextField;
    private javax.swing.JTextField firstTextField1;
    private javax.swing.JTextField firstTextField2;
    private javax.swing.JTextField firstTextField3;
    private javax.swing.ButtonGroup genderButtonGroup;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel14;
    private javax.swing.JLabel jLabel15;
    private javax.swing.JLabel jLabel16;
    private javax.swing.JLabel jLabel17;
    private javax.swing.JLabel jLabel18;
    private javax.swing.JLabel jLabel19;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel20;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JScrollPane jScrollPane4;
    private javax.swing.JScrollPane jScrollPane5;
    private javax.swing.JTextField lastTextField;
    private javax.swing.JTextField lastTextField1;
    private javax.swing.JTextField lastTextField2;
    private javax.swing.JTextField lastTextField3;
    private javax.swing.JRadioButton maleButton;
    private javax.swing.JRadioButton maleButton1;
    private javax.swing.JRadioButton maleButton2;
    private javax.swing.JRadioButton maleButton3;
    private javax.swing.JTextField middleTextField;
    private javax.swing.JTextField middleTextField1;
    private javax.swing.JTextField middleTextField2;
    private javax.swing.JTextField middleTextField3;
    private javax.swing.JTextArea notesTextArea;
    private javax.swing.JTextArea notesTextArea1;
    private javax.swing.JTextArea notesTextArea2;
    private javax.swing.JTextArea notesTextArea3;
    private javax.swing.JTextField suffixTextField;
    private javax.swing.JTextField suffixTextField1;
    private javax.swing.JTextField suffixTextField2;
    private javax.swing.JTextField suffixTextField3;
    private javax.swing.JRadioButton unknownButton;
    private javax.swing.JRadioButton unknownButton1;
    private javax.swing.JRadioButton unknownButton2;
    private javax.swing.JRadioButton unknownButton3;
    private javax.swing.JButton updateButton;
    // End of variables declaration//GEN-END:variables
    @Override
    public void componentOpened() {
        ftm = Lookup.getDefault().lookup(FamilyTreeManagerSwing.class);
        if (ftm == null) {
            logger.log(Level.SEVERE, "Cannot get FamilyTreeManager object");
            LifecycleManager.getDefault().exit();

        }
        configureListeners();
        
        // Listen for Person objects in the Global Selection lookup
        lookupResult = Utilities.actionsGlobalContext().lookupResult (PersonSwing.class);
        lookupResult.addLookupListener(lookupListener);
        checkLookup();
                TopComponent tc = WindowManager.getDefault().findTopComponent(
               "PersonViewerTopComponent");
        if (tc != null) {
            lookupResult = tc.getLookup().lookupResult(PersonSwing.class);
           checkLookup();
            lookupResult.addLookupListener(lookupListener);
        }

    }

    @Override
    public void componentClosed() {
        lookupResult.removeLookupListener(lookupListener);

    }

    void writeProperties(java.util.Properties p) {
        // better to version settings since initial version as advocated at
        // http://wiki.apidesign.org/wiki/PropertyFiles
        p.setProperty("version", "1.0");
        // TODO store your settings
    }

    void readProperties(java.util.Properties p) {
        String version = p.getProperty("version");
        // TODO read your settings according to their version
    }
    private final DocumentListener docListener = new DocumentListener() {

        @Override
        public void insertUpdate(DocumentEvent evt) {
            if (changeOK) {
                modify();
            }
        }

        @Override
        public void removeUpdate(DocumentEvent evt) {
            if (changeOK) {
                modify();
            }
        }

        @Override
        public void changedUpdate(DocumentEvent evt) {
            if (changeOK) {
                modify();
            }
        }

    };

    // ActionListener for Radio Buttons
    private final ActionListener radioButtonListener = (ActionEvent e) -> {
        if (changeOK) {
            modify();
        }
    };

    // ActionListener for Update button
    private final ActionListener updateListener = (ActionEvent e) -> {
        updateModel();
        ftm.updatePerson(thePerson);
    };

    // LookupListener to detect changes in PersonViewer's Lookup
    LookupListener lookupListener = (LookupEvent le) -> checkLookup();
    
    private void checkLookup()  {
        Collection<? extends PersonSwing> allPeople = lookupResult.allInstances();
        
        // Make sure that the TopComponent with focus isn't this one
        TopComponent tc = TopComponent.getRegistry().getActivated();
        if (tc != null && tc.equals(this)) {
            logger.log(Level.FINER, "PersonEditorTopComponent has focus.");
            return;
        }
               
        if (!allPeople.isEmpty()) {
            thePerson = allPeople.iterator().next();
            logger.log(Level.FINE, "{0} selected", thePerson);
            updateForm();
        } else {
            logger.log(Level.FINE, "No selection");
            clearForm();
        }
    }

}
