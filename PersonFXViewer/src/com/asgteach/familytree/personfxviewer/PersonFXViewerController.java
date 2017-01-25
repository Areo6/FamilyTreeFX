/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.asgteach.familytree.personfxviewer;

import java.net.URL;
import java.util.ResourceBundle;
import java.util.logging.Logger;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TreeView;
import com.asgteach.familytreefx.model.FamilyTreeManager;
import com.asgteach.familytreefx.model.Person;
import java.io.IOException;
import java.util.Collections;
import java.util.logging.ConsoleHandler;
import java.util.logging.FileHandler;
import java.util.logging.Handler;
import java.util.logging.Level;
import javafx.application.Platform;
import javafx.beans.value.ChangeListener;
import javafx.collections.MapChangeListener;
import javafx.scene.control.TreeItem;
import org.openide.LifecycleManager;
import org.openide.util.Lookup;
import org.openide.util.lookup.AbstractLookup;
import org.openide.util.lookup.InstanceContent;

/**
 *
 * @author Developer
 */
public class PersonFXViewerController implements Initializable {
    
    @FXML
    private TreeView<Person> personTreeView;
    
    private static final Logger logger = Logger.getLogger(PersonFXViewerController.class.getName());
    private FamilyTreeManager ftm = null;
    private Person selectedPerson = null;
    private final InstanceContent instanceContent=new InstanceContent();
    
    
    
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
          logger.setLevel(Level.FINE);
        Handler handler = new ConsoleHandler();
        handler.setLevel(Level.FINE);
        logger.addHandler(handler);
        try {
            FileHandler fileHandler = new FileHandler();
            // records sent to file javaN.log in user's home directory
            fileHandler.setLevel(Level.FINE);
            logger.addHandler(fileHandler);
            logger.log(Level.FINE, "Created File Handler");
        } catch (IOException | SecurityException ex) {
            logger.log(Level.SEVERE, "Couldn't create FileHandler", ex);
        }
        ftm = Lookup.getDefault().lookup(FamilyTreeManager.class);
        if (ftm == null) {
            logger.log(Level.SEVERE, "Cannot get FamilyTreeManager object");
            LifecycleManager.getDefault().exit();
        }
         buildData();
        ftm.addListener(familyTreeListener);
        // Create a root node and populate the TreeView control
        TreeItem<Person> rootNode =
            new TreeItem<>(new Person("People", "", Person.Gender.UNKNOWN));
        buildTreeView(rootNode);
        // Configure the TreeView control
        personTreeView.setRoot(rootNode);
        personTreeView.getRoot().setExpanded(true);
        personTreeView.getSelectionModel().selectedItemProperty().addListener(treeSelectionListener);
                  
    
    }
    

    private void buildData() {
        ftm.addPerson(new Person("Malaba", "Mashauri", Person.Gender.MALE));
        ftm.addPerson(new Person("Liz", "Mashauri", Person.Gender.FEMALE));
        ftm.addPerson(new Person("Allan", "Mashauri", Person.Gender.MALE));
        ftm.addPerson(new Person("Sasha", "Simpson", Person.Gender.FEMALE));
        ftm.addPerson(new Person("Maggie", "Simpson", Person.Gender.FEMALE));
        logger.log(Level.FINE, ftm.getAllPeople().toString());
    }

    private void buildTreeView(TreeItem<Person> root) {
        // listen for changes to the familytreemanager's map
        ftm.addListener(familyTreeListener);
        // Populate the TreeView control
        ftm.getAllPeople().stream().forEach((p) -> {
            root.getChildren().add(new TreeItem<>(p));
        });
    }
      private final ChangeListener<TreeItem<Person>> treeSelectionListener =
            (ov, oldValue, newValue) -> {
        TreeItem<Person> treeItem = newValue;
        logger.log(Level.FINE, "selected item = {0}", treeItem);
        if (treeItem == null || treeItem.equals(personTreeView.getRoot())) {
            instanceContent.remove(selectedPerson);
            return;
        }
        // set selectedPerson to the selected treeItem value
        selectedPerson = new Person(treeItem.getValue());
        logger.log(Level.FINE, "selected person = {0}", selectedPerson);
       instanceContent.set(Collections.singleton(selectedPerson), null);
    };
  
      private final MapChangeListener<Long, Person> familyTreeListener =
                                          (change) -> {
      if (Platform.isFxApplicationThread()) {
         logger.log(Level.FINE, "is JavaFX Application Thread");
         updateTree(change);
      }else {
            logger.log(Level.FINE, "Is BACKGROUND Thread");
            Platform.runLater(()-> updateTree(change));
            }
        };
          private void updateTree(MapChangeListener.Change<? extends Long,? extends Person> change) {
                                          
        if (change.getValueAdded() != null) {
            
         for (TreeItem<Person> node : personTreeView.getRoot().getChildren()) {
            if (change.getKey().equals(node.getValue().getId())) {
               
               node.setValue(change.getValueAdded());
               return;
            }
         }
      }
   };

    AbstractLookup.Content getInstanceContent() {
        logger.log(Level.FINE,"return InstanceContent");
        return instanceContent;
    }

}
