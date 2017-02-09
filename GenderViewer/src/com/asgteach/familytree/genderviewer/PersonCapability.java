/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.asgteach.familytree.genderviewer;

import com.asgteach.familytree.capabilities.RefreshCapability;
import com.asgteach.familytreefx.model.FamilyTreeManagerSwing;
import com.asgteach.familytreefx.model.PersonSwing;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.openide.LifecycleManager;
import org.openide.util.Lookup;
import org.openide.util.lookup.AbstractLookup;
import org.openide.util.lookup.InstanceContent;

/**
 *
 * @author Developer
 */
public class PersonCapability implements Lookup.Provider {

    private final Lookup lookup;
    private final InstanceContent instanceContent=new InstanceContent();
     private static final Logger logger = Logger.getLogger(
            PersonCapability.class.getName());
    private final List<PersonSwing> personList = new ArrayList<>();
    private FamilyTreeManagerSwing ftm = null;

    public PersonCapability() {
        lookup = new AbstractLookup(instanceContent);
        ftm = Lookup.getDefault().lookup(FamilyTreeManagerSwing.class);
        if (ftm == null) {
            logger.log(Level.SEVERE, "Cannot get FamilyTreeManager object");
            LifecycleManager.getDefault().exit();
        }
         instanceContent.add((RefreshCapability) () -> {
             if (ftm != null) {
                 personList.clear();
                 personList.addAll(ftm.getAllPeople());
             } else {
                 logger.log(Level.SEVERE, "Cannot get FamilyTreeManager");
             }
        });
    }
    public List<PersonSwing> getPersonList() {
        return personList;
    }
    @Override
    public Lookup getLookup() {
        return lookup;
    }
    
}
