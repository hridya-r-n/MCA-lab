/*
 * Copyright (c) 2024, Oracle and/or its affiliates. All rights reserved.
 * ORACLE PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 *
 *
 *
 *
 *
 *
 *
 *
 *
 *
 *
 *
 *
 *
 *
 *
 *
 *
 *
 *
 */

package com.sun.tools.javac.comp;

import com.sun.tools.javac.code.Symbol;
import com.sun.tools.javac.code.Symbol.VarSymbol;
import com.sun.tools.javac.tree.JCTree;
import com.sun.tools.javac.tree.TreeScanner;
import com.sun.tools.javac.util.List;

import java.util.HashSet;
import java.util.LinkedHashSet;
import java.util.SequencedSet;
import java.util.Set;

import static com.sun.tools.javac.code.Kinds.Kind.MTH;
import static com.sun.tools.javac.code.Kinds.Kind.VAR;

/**
 * A visitor which collects the set of local variables "captured" by a given tree.
 */
public class CaptureScanner extends TreeScanner {

    /**
     * The tree under analysis.
     */
    private final JCTree tree;

    /**
     * The set of local variable declarations encountered in the tree under analysis.
     */
    private final Set<Symbol.VarSymbol> seenVars = new HashSet<>();

    /**
     * The set of captured local variables accessed from within the tree under analysis.
     */
    private final SequencedSet<VarSymbol> fvs = new LinkedHashSet<>();

    public CaptureScanner(JCTree ownerTree) {
        this.tree = ownerTree;
    }

    @Override
    public void visitIdent(JCTree.JCIdent tree) {
        Symbol sym = tree.sym;
        if (sym.kind == VAR && sym.owner.kind == MTH) {
            Symbol.VarSymbol vsym = (Symbol.VarSymbol) sym;
            if (vsym.getConstValue() == null && !seenVars.contains(vsym)) {
                addFreeVar(vsym);
            }
        }
    }

    /**
     * Add free variable to fvs list unless it is already there.
     */
    protected void addFreeVar(Symbol.VarSymbol v) {
        fvs.add(v);
    }

    @Override
    public void visitVarDef(JCTree.JCVariableDecl tree) {
        if (tree.sym.owner.kind == MTH) {
            seenVars.add(tree.sym);
        }
        super.visitVarDef(tree);
    }

    /**
     * Obtains the list of captured local variables in the tree under analysis.
     */
    List<Symbol.VarSymbol> analyzeCaptures() {
        scan(tree);
        return List.from(fvs);
    }
}
