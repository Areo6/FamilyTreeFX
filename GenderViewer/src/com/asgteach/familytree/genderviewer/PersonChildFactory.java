/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.asgteach.familytree.genderviewer;

import com.asgteach.familytree.capabilities.RefreshCapability;
import com.asgteach.familytreefx.model.FamilyTreeManagerSwing;
import com.asgteach.familytreefx.model.PersonSwing;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.io.IOException;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.openide.LifecycleManager;
import org.openide.nodes.ChildFactory;
import org.openide.nodes.Node;
import org.openide.util.Lookup;
import org.openide.util.WeakListeners;

/**
 *
 * @author Developer
 */
public class PersonChildFactory extends ChildFactory<PersonSwing>{

     private static final Logger logger = Logger.getLogger(
                     PersonChildFactory.class.getName());
    private final PersonCapability personCapability = new PersonCapability();
    private FamilyTreeManagerSwing ftm = null;
    public PersonChildFactory() {
        ftm = Lookup.getDefault().lookup(FamilyTreeManagerSwing.class);
        if (ftm == null) {
            logger.log(Level.SEVERE, "Cannot get FamilyTreeManager object");
            LifecycleManager.getDefault().exit();
        } else {
            ftm.addPropertyChangeListener(
                  WeakListeners.propertyChange(familyTreeListener, ftm));
        }
    }


    @Override
    protected boolean createKeys(List<PersonSwing> list) {
         RefreshCapability refreshCapability =
               personCapability.getLookup().lookup(RefreshCapability.class);
        if (refreshCapability != null) {
            try {
                refreshCapability.refresh();
                list.addAll(personCapability.getPersonList());
                logger.log(Level.FINER, "createKeys called: {0}", list);
            } catch (IOException ex) {
                logger.log(Level.WARNING, null, ex);
            }
        }
        return true;
    }

    @Override
    protected Node createNodeForKey(PersonSwing key) {
        logger.log(Level.FINER, "createNodeForKey: {0}", key);
        PersonNode node = new PersonNode(key);
        key.addPropertyChangeListener(WeakListeners.propertyChange(node, key));
        return node;
 //To change body of generated methods, choose Tools | Templates.
    }
     private final PropertyChangeListener familyTreeListener = (PropertyChangeEvent evt) -> {
        if (evt.getPropertyName().equals(FamilyTreeManagerSwing.PROP_PERSON_ADDED)|| evt.getPropertyName().equals(
            FamilyTreeManagerSwing.PROP_PERSON_DESTROYED)) {
            this.refresh(true);
        }
    };

    
}
