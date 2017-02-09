/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.asgteach.familytree.genderviewer;

import com.asgteach.familytree.capabilities.RefreshCapability;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import javax.swing.Action;
import org.openide.nodes.AbstractNode;
import org.openide.nodes.Children;
import org.openide.util.NbBundle;
import org.openide.util.Utilities;
import org.openide.util.lookup.AbstractLookup;
import org.openide.util.lookup.InstanceContent;

/**
 *
 * @author Developer
 */
@NbBundle.Messages({
    "HINT_RootNode=Show all people",
    "LBL_RootNode=People"
})

public class RootNode extends AbstractNode {
    private final InstanceContent instanceContent;
    public RootNode(){
        this(new InstanceContent());
    }
    
    public RootNode(InstanceContent ic) {
        super(Children.create(new PersonChildFactory(), false));
        AbstractLookup abstractLookup = new AbstractLookup(ic);
        instanceContent=ic;
        setIconBaseWithExtension("com/asgteach/familytree/genderviewer/resources/personIcon.png");
        setDisplayName(Bundle.LBL_RootNode());
        setShortDescription(Bundle.HINT_RootNode());
        instanceContent.add((RefreshCapability) () -> {
            setChildren(Children.create(new PersonChildFactory(), false));
        });
    }
    
     @SuppressWarnings("unchecked")
    @Override
    public Action[] getActions(boolean context) {
        List<Action> actions = new ArrayList<>(Arrays.asList(
                     super.getActions(context)));
        actions.addAll(Utilities.actionsForPath("Actions/RootNode"));
        return actions.toArray(new Action[actions.size()]);
    }
       
}
