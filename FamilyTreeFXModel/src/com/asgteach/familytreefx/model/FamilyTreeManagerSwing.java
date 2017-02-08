/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.asgteach.familytreefx.model;

import java.beans.PropertyChangeListener;
import java.util.List;

/**
 *
 * @author Developer
 */
public interface FamilyTreeManagerSwing {
     public static final String PROP_PERSON_DESTROYED = "removePerson";
    public static final String PROP_PERSON_ADDED = "addPerson";
    public static final String PROP_PERSON_UPDATED = "updatePerson";
    

    public void addPropertyChangeListener(PropertyChangeListener listener);

    public void removePropertyChangeListener(PropertyChangeListener listener);
    
    public void addPerson(PersonSwing p);

    public void updatePerson(PersonSwing p);

    public void deletePerson(PersonSwing p);
    
    public List<PersonSwing> getAllPeople();

}
