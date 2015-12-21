package com.markwryan.adventofcode.day17;

import com.google.common.collect.Sets;

import java.util.*;

/**
 * Created by mark on 12/20/15.
 */
public class NogStorage {
    public static void main(String[] args) {

        int minContainer = 999;
        Set<Set<Container>> choices = new HashSet<>();
        Set<Container> c = Sets.newHashSet(new Container(33), new Container(14), new Container(18), new Container(20),
                new Container(45), new Container(35), new Container(16), new Container(35), new Container(1),
                new Container(13), new Container(18), new Container(13), new Container(50), new Container(44),
                new Container(48), new Container(6), new Container(24), new Container(41), new Container(30),
                new Container(42));
        Set<Set<Container>> containers = Sets.powerSet(c);
        for (Set<Container> combo : containers) {
            int total = 0;
            for (Container container : combo) {

                total += container.size;
            }

            if (total == 150 && minContainer > combo.size()) {
                choices.clear();
                choices.add(combo);
                minContainer = combo.size();
            }
            if( total == 150 && minContainer == combo.size()) {
                choices.add(combo);
            }
        }

        System.out.println(choices.size());
        System.out.println(minContainer);
    }
}

class Container {
    int size;

    Container(int s) {
        this.size =s;
    }
}
