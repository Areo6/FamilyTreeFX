/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.asgteach.familytree.managerfx.impl;

import com.asgteach.familytreefx.model.FamilyTreeManagerSwing;

import com.asgteach.familytreefx.model.PersonSwing;
import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.openide.util.lookup.ServiceProvider;

/**
 *
 * @author Developer
 */
@ServiceProvider(service = FamilyTreeManagerSwing.class)
public class FamilyTreeManagerImpl implements FamilyTreeManagerSwing {
    
    private final Map<Long, PersonSwing> personMap = new HashMap<>();
    private PropertyChangeSupport propChangeSupport = null;
    
    
    private PropertyChangeSupport getPropertyChangeSupport() {
        if (this.propChangeSupport == null) {
            this.propChangeSupport = new PropertyChangeSupport(this);
        }
        return this.propChangeSupport;
    }


    @Override
    public void addPropertyChangeListener(PropertyChangeListener listener) {
        getPropertyChangeSupport().addPropertyChangeListener(listener);
    }

    @Override
    public void removePropertyChangeListener(PropertyChangeListener listener) {
        getPropertyChangeSupport().removePropertyChangeListener(listener);

    }

    @Override
    public void addPerson(PersonSwing p) {
         PersonSwing person = new PersonSwing(p);
        personMap.put(person.getId(), person);
        getPropertyChangeSupport().firePropertyChange(
                FamilyTreeManagerSwing.PROP_PERSON_ADDED, null, person);
    }

    @Override
    public void updatePerson(PersonSwing p) {
        PersonSwing person = new PersonSwing(p);
        personMap.put(person.getId(), person);
        getPropertyChangeSupport().firePropertyChange(
                FamilyTreeManagerSwing.PROP_PERSON_UPDATED, null, person);

    }

    @Override
    public void deletePerson(PersonSwing p) {
         PersonSwing person = personMap.remove(p.getId());
        if (person != null) {
            getPropertyChangeSupport().firePropertyChange(
                    FamilyTreeManagerSwing.PROP_PERSON_DESTROYED, null, person);
        }

    }

    @Override
    public List<PersonSwing> getAllPeople() {
         List<PersonSwing> copyList = new ArrayList<>();
        personMap.values().stream().forEach((p) -> {
            copyList.add(new PersonSwing(p));
        });
        return copyList;
    };    

         
}
