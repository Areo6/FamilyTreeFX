/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.asgteach.familytree.genderviewer;

import com.asgteach.familytreefx.model.PersonSwing;
import com.asgteach.familytreefx.model.PersonSwing.Gender;
import java.awt.datatransfer.Transferable;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.io.IOException;
import org.openide.nodes.AbstractNode;
import org.openide.nodes.Children;
import org.openide.util.NbBundle;
import org.openide.util.datatransfer.ExTransferable;
import org.openide.util.lookup.Lookups;


/**
 *
 * @author Developer
 */
@NbBundle.Messages({
    "HINT_PersonNode=Person"
})
public class PersonNode extends AbstractNode implements PropertyChangeListener {

    public PersonNode(PersonSwing person) {
        super(Children.LEAF, Lookups.singleton(person));
        setIconBaseWithExtension("com/asgteach/familytree/genderviewer/resources/personIcon.png");
        setName(String.valueOf(person.getId()));
        setDisplayName(person.toString());
        setShortDescription(Bundle.HINT_PersonNode());
    }
     @Override
    public String getHtmlDisplayName() {
        PersonSwing person = getLookup().lookup(PersonSwing.class);
        StringBuilder sb = new StringBuilder();
        if (person == null) {
            return null;
        }
         switch (person.getGender()) {
            case MALE:
                sb.append("<font color='#5588FF'><b>| ");
                break;
            case FEMALE:
                sb.append("<font color='#FF8855'><b>* ");
                break;
            case UNKNOWN:
                sb.append("<b>? ");
                break;
        }
        sb.append(person.toString()).append("</b></font>");
        return sb.toString();
    }


    @Override
    public void propertyChange(PropertyChangeEvent evt) {
        PersonSwing person=(PersonSwing) evt.getSource();
        fireDisplayNameChange(null, getDisplayName());

    }

    @Override
    public boolean canCut() {
         return true; //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public boolean canCopy() {
        return true; //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public boolean canRename() {
        return super.canRename(); //To change body of generated methods, choose Tools | Templates.
    }

    
    

    @Override
    public boolean canDestroy() {
        return true; //To change body of generated methods, choose Tools | Templates.
    }
     
    
}
