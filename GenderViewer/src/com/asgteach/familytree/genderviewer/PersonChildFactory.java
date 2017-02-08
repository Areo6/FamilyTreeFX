/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.asgteach.familytree.genderviewer;

import com.asgteach.familytreefx.model.FamilyTreeManagerSwing;
import com.asgteach.familytreefx.model.PersonSwing;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
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

     private final FamilyTreeManagerSwing ftm;
    private static final Logger logger = Logger.getLogger(PersonChildFactory.class.getName());
    private final PersonSwing.Gender gender;

    public PersonChildFactory(PersonSwing.Gender gender) {
        this.gender=gender;
        this.ftm = Lookup.getDefault().lookup(FamilyTreeManagerSwing.class);
        if (ftm == null) {
            logger.log(Level.SEVERE, "Cannot get FamilyTreeManager object");
            LifecycleManager.getDefault().exit();
        }
        ftm.addPropertyChangeListener(familyTreeListener);
    }


    @Override
    protected boolean createKeys(List<PersonSwing> list) {
        ftm.getAllPeople().stream().forEach((PersonSwing p) -> {
            if (p.getGender().equals(gender)) {
                list.add(p);
            }
        });
        
        logger.log(Level.FINER, "createKeys called: {0}", ftm.getAllPeople());
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
        if (evt.getPropertyName().equals(FamilyTreeManagerSwing.PROP_PERSON_UPDATED)
                && evt.getNewValue() != null) {
            PersonSwing personChange = (PersonSwing) evt.getNewValue();
            logger.log(Level.INFO, "Person updated: {0}", personChange);
            this.refresh(true);   
        }
    };

    
}
