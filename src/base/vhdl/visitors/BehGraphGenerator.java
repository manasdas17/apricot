package base.vhdl.visitors;

import base.vhdl.structure.nodes.*;

import java.util.Collection;

/**
 * <br><br>User: Anton Chepurov
 * <br>Date: 06.02.2008
 * <br>Time: 22:55:30
 */
public class BehGraphGenerator extends GraphGenerator {
    private Collection<String> registers;

    @Deprecated
    public BehGraphGenerator(boolean useSameConstants) {
        super(useSameConstants, true /*bla bla, filler */, Type.Beh);
    }

    public BehGraphGenerator(boolean useSameConstants, boolean doExpandConditions, Collection<String> registers) {
        super(useSameConstants, doExpandConditions, Type.Beh);
        this.registers = registers;
    }

    public void visitProcess(base.vhdl.structure.Process process) throws Exception {

        if (partialSettingsMapByProcess.containsKey(process)) {
            /* At first, process partial settings, like "Parity(7) <= smth;" */
            processPartialSettings(process);
        }

        /* (Re)Traverse the root node until all the variables
        *  that are set within this process are processed. */
        while (true) {
            if (!couldProcessNextGraphVariable(null, process.getRootNode()))
                break;
            else
                processedGraphVars.add(graphVariable.getName()); //todo: when partial settings are introduced, graphVariable may have to be substituted with newGraphVariable

        }

    }

    protected void doCheckTruePart(IfNode ifNode) throws Exception { /* do nothing */ }

    protected void doCheckFalsePart(IfNode ifNode) throws Exception { /* do nothing */ }

    protected void doCheckWhenTransitions(WhenNode whenNode) throws Exception { /* do nothing */ }

    protected boolean isDelay(String variableName) {
        return registers.contains(variableName);
    }

//    public void replaceArchitectureTransitions() throws Exception {
//        for (AbstractNode replacindNode : replacingChildren) {
//            if (replacindNode instanceof TransitionNode) {
//                TransitionNode transitionNode = (TransitionNode) replacindNode;
//                AbstractVariable replacingVariable = modelCollector.getVariable(transitionNode.getVariableName());
//                AbstractVariable variableToReplace = modelCollector.getVariable(((OperandImpl) transitionNode.getValueOperand()).getName());
//                modelCollector.replace(variableToReplace, replacingVariable);
//            }
//        }
//        /* Replace to replacingVariable */
//        AbstractVariable replacingVariable = getReplacingVariable(graphVariable);
//        if (replacingVariable != null) {
//            modelCollector.replace(graphVariable, replacingVariable);
//        }
//    }
}
