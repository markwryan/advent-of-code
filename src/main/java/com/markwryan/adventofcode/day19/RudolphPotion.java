package com.markwryan.adventofcode.day19;

import java.util.Arrays;
import java.util.Comparator;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.ConcurrentSkipListSet;

/**
 * Created by maryan on 12/21/15.
 */
public class RudolphPotion {
    Map<String, List<String>> replacements;

    RudolphPotion() {
        replacements = new HashMap<>();
        /*
        List<String> h = Arrays.asList("HO", "OH");
        List<String> o = Arrays.asList("HH");
        replacements.put("H", h);
        replacements.put("O", o);
        */

        /*
        H => CRnAlAr
H => CRnFYFYFAr
H =>
H =>
H => HCa
H =>
H =>
H =>
H => OB
H => ORnFAr
         */

        List<String> al = Arrays.asList("ThRnFAr","ThF");
        List<String> b = Arrays.asList("TiRnFAr","BCa", "TiB");
        List<String> ca = Arrays.asList("PRnFAr", "SiRnFYFAr", "SiRnMgAr", "SiTh", "CaCa", "PB");
        List<String> f = Arrays.asList("SiAl", "CaF", "PMg");
        List<String> h =
                Arrays.asList("CRnAlAr", "CRnFYFYFAr", "CRnFYMgAr", "CRnMgYFAr", "HCa", "NRnFYFAr", "NRnMgAr", "NTh",
                        "OB", "ORnFAr");
        List<String> mg = Arrays.asList("BF", "TiMg");
        List<String> n = Arrays.asList("CRnFAr", "HSi");
        List<String> o = Arrays.asList("CRnFYFAr", "CRnMgAr", "HP", "NRnFAr", "OTi");
        List<String> p = Arrays.asList("CaP", "PTi", "SiRnFAr");
        List<String> si = Arrays.asList("CaSi");
        List<String> th = Arrays.asList("ThCa");
        List<String> ti = Arrays.asList("BP", "TiTi");
        List<String> e = Arrays.asList("HF", "NAl", "OMg");

        al.sort((one, two) -> two.length() - one.length());
        b.sort((one, two) -> two.length() - one.length());
        ca.sort((one, two) -> two.length() - one.length());
        f.sort((one, two) -> two.length() - one.length());
        h.sort((one, two) -> two.length() - one.length());
        mg.sort((one, two) -> two.length() - one.length());
        n.sort((one, two) -> two.length() - one.length());
        o.sort((one, two) -> two.length() - one.length());
        p.sort((one, two) -> two.length() - one.length());
        si.sort((one, two) -> two.length() - one.length());
        th.sort((one, two) -> two.length() - one.length());
        ti.sort((one, two) -> two.length() - one.length());
        e.sort((one, two) -> two.length() - one.length());

        replacements.put("Al", al);
        replacements.put("B", b);
        replacements.put("Ca", ca);
        replacements.put("F", f);
        replacements.put("H", h);
        replacements.put("Mg", mg);
        replacements.put("N", n);
        replacements.put("O", o);
        replacements.put("P", p);
        replacements.put("Si", si);
        replacements.put("Th", th);
        replacements.put("Ti", ti);
        replacements.put("e", e);
    }


    public static void main(String[] args) {
        RudolphPotion potion = new RudolphPotion();
        System.out.println(potion.findNewMolecules(
                "CRnSiRnCaPTiMgYCaPTiRnFArSiThFArCaSiThSiThPBCaCaSiRnSiRnTiTiMgArPBCaPMgYPTiRnFArFArCaSiRnBPMgArP" +
                        "RnCaPTiRnFArCaSiThCaCaFArPBCaCaPTiTiRnFArCaSiRnSiAlYSiThRnFArArCaSiRnBFArCaCaSiRnSiThCaCaCa" +
                        "FYCaPTiBCaSiThCaSiThPMgArSiRnCaPBFYCaCaFArCaCaCaCaSiThCaSiRnPRnFArPBSiThPRnFArSiRnMgArCaFYF" +
                        "ArCaSiRnSiAlArTiTiTiTiTiTiTiRnPMgArPTiTiTiBSiRnSiAlArTiTiRnPMgArCaFYBPBPTiRnSiRnMgArSiThCaF" +
                        "ArCaSiThFArPRnFArCaSiRnTiBSiThSiRnSiAlYCaFArPRnFArSiThCaFArCaCaSiThCaCaCaSiRnPRnCaFArFYPMg" +
                        "ArCaPBCaPBSiRnFYPBCaFArCaSiAl"));
    }

    public long findNewMolecules(String mole) {
        Set<String> molecules = new HashSet<>();
        molecules.add(mole);
        long i = 0;
        while(!molecules.contains("e")) {
            HashSet<String> levels = new HashSet<>();
            for(String input : molecules) {
                for (String key : replacements.keySet()) {
                    List<String> values = replacements.get(key);
                    for (String value : values) {
                        int instance = input.indexOf(key);
                        while (instance >= 0) {
                            String pre = input.substring(0, instance);
                            String post = input.substring(instance + key.length());
                            levels.add(pre + value + post);
                            instance = input.indexOf(key, instance + 1);
                        }
                    }
                }
            }
            molecules.clear();
            molecules.addAll(levels);
            levels.clear();
            i++;
        }

        return i;
    }


}
