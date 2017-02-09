/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.asgteach.familytree.capabilities;

import com.asgteach.familytreefx.model.PersonSwing;
import java.io.IOException;

/**
 *
 * @author Developer
 */
public interface SavablePersonCapability {
    public void save(PersonSwing person)throws IOException;
}
